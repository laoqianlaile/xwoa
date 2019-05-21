package com.centit.attendance.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.struts2.ServletActionContext;
import org.extremecomponents.table.limit.Limit;

import com.centit.attendance.po.AttendanceDetailed;
import com.centit.attendance.po.AttendanceSetting;
import com.centit.attendance.service.AttendanceDetailedManager;
import com.centit.attendance.service.AttendanceSettingManager;
import com.centit.core.utils.ExtremeTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.dispatchdoc.action.IODocCommonBizAction;
import com.centit.efile.mgt.EfileManager;
import com.centit.efile.model.EfileStore;
import com.centit.oa.service.OaPowerrolergroupManager;
import com.centit.powerruntime.util.BizCommUtil;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.util.SysParametersUtils;
import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;

public class AttendanceDetailedAction extends
        IODocCommonBizAction<AttendanceDetailed> {
    private static final Log log = LogFactory
            .getLog(AttendanceDetailedAction.class);
    private static final long serialVersionUID = 1L;
    private AttendanceDetailedManager attendanceDetailedMag;
    private AttendanceSettingManager attendanceSettingManager;
    private OaPowerrolergroupManager oaPowerrolergroupManager;
    
    /**
     * 附件材料文件，供上传使用
     */
    private File stuffFile;

    private String stuffFileFileName;
    
    /**
     * 材料下载使用
     */
    private InputStream stuffStream;

    public InputStream getStuffStream() {
        return stuffStream;
    }

    public void setStuffStream(InputStream stuffStream) {
        this.stuffStream = stuffStream;
    }


    public void setAttendanceDetailedManager(AttendanceDetailedManager basemgr) {
        attendanceDetailedMag = basemgr;
        this.setBaseEntityManager(attendanceDetailedMag);
    }
    
    public void setAttendanceSettingManager(
            AttendanceSettingManager attendanceSettingManager) {
        this.attendanceSettingManager = attendanceSettingManager;
    }


    public void setOaPowerrolergroupManager(
            OaPowerrolergroupManager oaPowerrolergroupManager) {
        this.oaPowerrolergroupManager = oaPowerrolergroupManager;
    }
    
    public File getStuffFile() {
        return stuffFile;
    }

    public void setStuffFile(File stuffFile) {
        this.stuffFile = stuffFile;
    }

    public String getStuffFileFileName() {
        return stuffFileFileName;
    }

    public void setStuffFileFileName(String stuffFileFileName) {
        this.stuffFileFileName = stuffFileFileName;
    }

    /**
     * 重写新增方法
     * 
     */
    public String built() {
        try {
            object = new AttendanceDetailed();
            request.setAttribute("object",object);
            Date date = new Date();
            object.setCreatedate(date);// 设置创建时间为当前系统时间
            object.setDjid(attendanceDetailedMag.getNewCode());// 获取自动生成的id
            return BUILT;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    
    /**
     * 部门树
     * @return
     */
    public String oaView(){
        JSONArray ja =oaPowerrolergroupManager.putUnitsTree();//获取部门
        request.setAttribute("unit", ja.toString());
        return "oaUnitView";
    }
    
    /*
    * 重写保存方法
    * 
    */
       public String newsave() {
           try {
               AttendanceDetailed dbObject = baseEntityManager.getObject(object);
               if (dbObject != null) {
                   attendanceDetailedMag.copyObjectNotNullProperty(dbObject, object);
                   object = dbObject;
               }else{
                   Date date = new Date();
                   object.setCreatedate(date);//设置创建时间为当前系统时间
                   object.setDjid(attendanceDetailedMag.getNewCode());//获取自动生成的id
               }
               //获取上下班时间进行判断
               AttendanceSetting attendanceSetting= new AttendanceSetting();
               attendanceSetting=attendanceSettingManager.findTime();
               String sSettingTime =attendanceSetting.getSaattendancetime();//获取设置时间的上班时间
               String xSettingTime =attendanceSetting.getXaattendancetime();//获取设置时间的下班时间
               String sdate= object.getSaattendancetime().substring(10);//获取提交数据的上班时间
               String xdate= object.getXaattendancetime().substring(10);//获取提交数据的下班时间
               
               //在这里设置考勤状态，迟到早退,T迟到，F早退，Y迟到并早退
               DateFormat df = new SimpleDateFormat("hh:mm:ss");
               try {
                   Date date1 = df.parse(sSettingTime);
                   Date date2=df.parse(xSettingTime);  //将下班时间格式转换
                   Date date3=df.parse(sdate);         //将Excel上班时间格式转换
                   Date date4=df.parse(xdate);         //将Excel下班时间格式转换
                   if(date1.getTime()<date3.getTime()){ //判断如果设置的上班时间比Excel中上班时间小，则迟到
                       object.setAttenstate("T");
                   }else if(date2.getTime()>date4.getTime()){ //判断如果设置的下班时间大于Excel中下班时间，则早退
                       object.setAttenstate("F");
                   }else if(date1.getTime()<date3.getTime() && date2.getTime()>date4.getTime()){//既迟到，又早退
                       object.setAttenstate("Y");
                   }else{
                       object.setAttenstate("");
                   }
               } catch (ParseException e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();
               }  //将上班时间格式转换
               
               baseEntityManager.saveObject(object);
               savedMessage();
               return SUCCESS;
           } catch (Exception e) {
               log.error(e.getMessage(), e);
               saveError(e.getMessage());
               return ERROR;
           }
       }

    /**
     * 导入Excel数据
     */
    @SuppressWarnings({ "unused", "null" })
    public String importAtt() throws IOException {
        //String fpath = request.getQueryString();// 传过来的文件名称是中文，是乱码
        //String fpath = request.getSession().getServletContext().getRealPath(request.getQueryString());
        //String filepath = new String(fpath.getBytes("iso-8859-1"), "utf-8");// 将文件地址转码
        //String a = filepath.replaceAll("\\\\", "/");
        
        //路径规则，流程附件基本路径+办件类型+业务id
        String dirPath = SysParametersUtils.getWorkflowAffixHome() + File.separator + "SW" + File.separator + "1234566";
        EfileStore efileStore = new EfileStore(stuffFile, stuffFileFileName,  dirPath);
        EfileManager.store(efileStore);
        
        
        InputStream is = new FileInputStream(SysParametersUtils.getWorkflowAffixHome()+efileStore.getAbsolutePath().replace(SysParametersUtils.getWorkflowAffixHome(), ""));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        AttendanceDetailed attendanceDetailed = null;
        List<AttendanceDetailed> list = new ArrayList<AttendanceDetailed>();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        
        //获取上下班时间进行判断
        AttendanceSetting object= new AttendanceSetting();
        object=attendanceSettingManager.findTime();
        String sSettingTime =object.getSaattendancetime();//获取设置时间的上班时间
        String xSettingTime =object.getXaattendancetime();//获取设置时间的下班时间
        
        // 循环工作表Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 循环行Row
            for (int rowNum = 4; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                /*
                 * if (hssfRow == null) { continue; }
                 */
                attendanceDetailed = new AttendanceDetailed();
                // 循环列Cell ，这个需要对应
                // 0 用户id,1 名字,2 日期,3 上班时间 ，4 下班时间 ，5工作时间， 6 迟到时间 ， 7 早退时间 ， 8
                // 部门名称
                HSSFCell username = hssfRow.getCell(1); // 名字
                                                        // （读取文件中用户id没用，直接存用户名称）
                if (username == null) {
                    continue;
                }
                attendanceDetailed.setUsername((getValue(username).trim()));

                HSSFCell workdate = hssfRow.getCell(2); // 日期
                if (workdate == null) {
                    continue;
                }
                String newdate = workdate.toString(); // 日期 ,换成String
                int num = newdate.indexOf('('); // 获取字符串要截取的长度
                String wdate = newdate.substring(0, num - 1);// 获取日期，从0到括号的前一位，因为那里有个空格，根据模板来的
                    attendanceDetailed.setWorkdate(wdate); // 设置日期
                    
                // 上班时间
                HSSFCell saattendancetime = hssfRow.getCell(3); // 从Excel中读取上班时间
                String saa = saattendancetime.toString(); // 获取的值进行类型转换，判断是否存在数据
                String sdate = null;
                if (!saa.equals("--")) {// 判断没有上班，模板的数据是--
                    Date saatime = saattendancetime.getDateCellValue();// 类型转换，这步必不可少
                    sdate = formatter.format(saatime); // 把上班时间变为sring类型
                    String pjsdate = wdate + ' ' + sdate;// 上班日期与上班时间进行拼接
                    attendanceDetailed.setSaattendancetime(pjsdate);// 设置上班时间值
                    attendanceDetailed.setAmtime(sdate);// 设置上班时间值,String类型
                }

                // 下班时间
                HSSFCell xaattendancetime = hssfRow.getCell(4); // 从Excel中读取下班时间
                String xaa = saattendancetime.toString(); // 获取的值进行类型转换，判断是否存在数据
                String xdate;
                if (!xaa.equals("--")) {
                    Date xaatime = xaattendancetime.getDateCellValue();// 类型转换，这步必不可少
                    xdate = formatter.format(xaatime); // 把下班时间变为sring类型
                    String pjxdate = wdate + ' ' + xdate;// 上班日期与下班时间进行拼接
                    attendanceDetailed.setXaattendancetime(pjxdate);// 设置下班时间值
                    attendanceDetailed.setPmtime(xdate);// 设置下班时间值，String类型
                    
                    //在这里设置考勤状态，迟到早退,T迟到，F早退，Y迟到并早退
                    DateFormat df = new SimpleDateFormat("hh:mm:ss");
                    try {
                        Date date1 = df.parse(sSettingTime);
                        Date date2=df.parse(xSettingTime);  //将下班时间格式转换
                        Date date3=df.parse(sdate);         //将Excel上班时间格式转换
                        Date date4=df.parse(xdate);         //将Excel下班时间格式转换
                        if(date1.getTime()<date3.getTime()){ //判断如果设置的上班时间比Excel中上班时间小，则迟到
                            attendanceDetailed.setAttenstate("T");
                        }
                        if(date2.getTime()>date4.getTime()){ //判断如果设置的下班时间大于Excel中下班时间，则早退
                            attendanceDetailed.setAttenstate("F");
                        }
                        if(date1.getTime()<date3.getTime() && date2.getTime()>date4.getTime()){//既迟到，又早退
                            attendanceDetailed.setAttenstate("Y");
                        }
                    } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }  //将上班时间格式转换
                }

                // 工作时长
                HSSFCell overtimehours = hssfRow.getCell(5);
                String ove = overtimehours.toString(); // 获取的值进行类型转换，判断是否存在数据
                if (!ove.equals("--")) {
                    Date ovetime = overtimehours.getDateCellValue();
                    Date currentTime2 = new Date();
                    SimpleDateFormat formatter2 = new SimpleDateFormat(
                            "HH:mm:ss");
                    String dateString2 = formatter.format(ovetime);
                    attendanceDetailed.setOvertimehours(dateString2);
                }

                HSSFCell unitname = hssfRow.getCell(8); // 部门名称
                if (unitname == null) {
                    continue;
                }
                attendanceDetailed.setUnitname((getValue(unitname)));
                
                //根据姓名跟日期查询，如果当前天数跟当前人在数据库中有相同的值则修改，没有则添加
                String findDjid =  attendanceDetailedMag.findNumByUnameAndWorkdate((getValue(username).trim()), wdate);
                if(findDjid==null){//当数据库没有查询到结果时，新增
                    attendanceDetailed.setDjid(attendanceDetailedMag.getNewCode());// 获取自动生成的id
                    Date nd = new Date();// new一个新的时间
                    attendanceDetailed.setCreatedate(nd);// 设置创建时间为当前系统时间
                    attendanceDetailedMag.saveObject(attendanceDetailed);  
                }else{//当数据库有值时，做修改操作
                    attendanceDetailed.setDjid(findDjid);//将查询出来的djid赋值给
                    AttendanceDetailed dbObject = baseEntityManager.getObject(attendanceDetailed);
                    baseEntityManager.copyObjectNotNullProperty(dbObject, attendanceDetailed);
                    attendanceDetailed = dbObject;
                    baseEntityManager.saveObject(attendanceDetailed);
                }
                HSSFCell my = hssfRow.getCell(9); // 没有数据就结束当前循环
                if (my == null) {
                    continue;
                }
            }
        }
        return this.list();
    }

    /**
     * 得到Excel表中的值
     * 
     * @param hssfCell
     *            Excel中的每一个格子
     * @return Excel中每一个格子中的值
     */
    @SuppressWarnings("static-access")
    private String getValue(HSSFCell hssfCell) {
        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
            // 返回布尔类型的值
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
            // 返回数值类型的值
            return String.valueOf(hssfCell.getNumericCellValue());
        } else {
            // 返回字符串类型的值
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }

    /**
     * 重写list方法，如果使用父类的list分页会有问题,0考勤信息頁面，1個人考勤頁面，2部門考勤頁面
     */
    @SuppressWarnings("unchecked")
    public String list() {
        String listType = request.getParameter("listType");//传递的类型不同，查询的数据不同
        if ("0".equals(listType)) {
           // Map<Object, Object> paramMap = request.getParameterMap();
            Map<Object, Object> paramMap = new HashMap<Object, Object>();
            paramMap.put("listType", request.getParameter("listType"));
            paramMap.put("s_unitname", request.getParameter("unitName"));
            paramMap.put("s_username", request.getParameter("s_username"));
            paramMap.put("s_workdate", request.getParameter("s_workdate"));
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            Limit limit = ExtremeTableUtils.getLimit(request);
            PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
            List<AttendanceDetailed> attendanceDetailedlist = attendanceDetailedMag
                    .listObjects(filterMap, pageDesc);
            totalRows = pageDesc.getTotalRows();
            request.setAttribute("attendanceDetailedlist",
                    attendanceDetailedlist);
        } else if ("1".equals(listType)) {
            // 获取当前登录人用户信息
            FUserDetail user = ((FUserDetail) getLoginUser());
            // 获取当前登录人的登录名
            String username = user.getUsername();
            @SuppressWarnings("unchecked")
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            filterMap.put("username", username);// 将筛选条件设置到filterMap中的username中
            Limit limit = ExtremeTableUtils.getLimit(request);
            PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
            List<AttendanceDetailed> attendanceDetailedlist = attendanceDetailedMag
                    .listObjects(filterMap, pageDesc);
            totalRows = pageDesc.getTotalRows();
            request.setAttribute("attendanceDetailedlist",
                    attendanceDetailedlist);
        } else if("2".equals(listType)){
            // 获取当前登录人用户信息
            FUserDetail user = ((FUserDetail) getLoginUser());
            // 获取用户编号
            String ucode = user.getUsercode();
            // 需要根据用户编号查询部门名称
            String unitname = attendanceDetailedMag.findUnitnameByUcode(ucode);
            @SuppressWarnings("unchecked")
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            filterMap.put("unitname", unitname);// 将筛选条件设置到filterMap中的username中
            Limit limit = ExtremeTableUtils.getLimit(request);
            PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
            List<AttendanceDetailed> attendanceDetailedlist = attendanceDetailedMag
                    .listObjects(filterMap, pageDesc);
            totalRows = pageDesc.getTotalRows();
            request.setAttribute("attendanceDetailedlist",
                    attendanceDetailedlist);
        }else{
            @SuppressWarnings("unchecked")
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            Limit limit = ExtremeTableUtils.getLimit(request);
            PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
            List<AttendanceDetailed> attendanceDetailedlist = attendanceDetailedMag
                    .listObjects(filterMap, pageDesc);
            totalRows = pageDesc.getTotalRows();
            request.setAttribute("attendanceDetailedlist",
                    attendanceDetailedlist);
        }
        return "list";
    }

    /**
     * 根据年度、月度导出部门人员考勤情况
     * 
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void getExcelAttendance() throws Exception {
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        String month=(String)filterMap.get("workdate");
        if (StringUtils.isBlank(month)) {
            object.setMonth(DatetimeOpt.currentDate().substring(0, 4)+"/"+DatetimeOpt.currentDate().substring(5, 7));
        }else{
            object.setMonth(month.substring(0, 4)+"/"+Integer.parseInt(month.substring(5, 7))); 
        }
        // 获取 指定月份的考勤部门
        List<Object[]> depslist = attendanceDetailedMag
                .getExcelAttendanceDepsList(object);

        if (depslist != null && depslist.size() > 0) {

            HttpServletResponse response = ServletActionContext.getResponse();
            // 设置头信息
            response.setHeader("Content-Disposition", "attachment;filename="
                    + URLEncoder.encode("考勤记录", "utf-8") + ".xls");
            response.setContentType("application/x-download; charset=utf-8");
            HSSFWorkbook workbook = new HSSFWorkbook();

            int i = 0;
            for (Object[] o : depslist) {
                String[] usersHeaders = attendanceDetailedMag
                        .getUserNamesHeaders(object.getMonth(), o[0].toString());
                List<List<String>> ll = attendanceDetailedMag
                        .getExcelAttendanceDateList(object.getMonth(),
                                o[0].toString());
                BizCommUtil.exportExcel(workbook, i, o[0].toString(),
                        usersHeaders, ll);
                i++;
            }
            workbook.write(response.getOutputStream());
        } else {
            super.postAlertMessage("当前月份[" + object.getMonth()
                    + "]无考勤记录，请选择其他月份！", response);
        }
        object.setMonth("");
    }
    
    /**
     * 下载导入模板
     * @return
     */
    @SuppressWarnings({ "deprecation", "static-access" })
    public void exportExcelP() throws IOException {
        try {
            this.doPoi2ExcelByTemplate(request.getRealPath("/")+"writmodel/考勤导入模板.xls", "考勤导入模板",1);
        } catch (Exception e) {
            e.printStackTrace();
        }
}
    
    public static void doPoi2ExcelByTemplate(String srcPath, String excelName,int rowNum) throws IOException {
        // excel模板路径
        File fi = new File(srcPath);
        POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(fi));
        // 读取excel模板
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        // 修改模板内容导出新模板
        HttpServletResponse response = ServletActionContext.getResponse();
        // 设置头信息
        response.setHeader("Content-Disposition", "attachment;filename="
                + URLEncoder.encode(excelName, "utf-8") + ".xls");// 导出Excel弹窗附件名称
        response.setContentType("application/x-download; charset=utf-8");
        wb.write(response.getOutputStream());
    }
}
