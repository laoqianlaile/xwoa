package com.centit.oa.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.struts2.ServletActionContext;
import org.extremecomponents.table.limit.Limit;
import org.springframework.util.StringUtils;

import com.centit.core.action.BaseEntityDwzAction;
import com.centit.core.dao.CodeBook;
import com.centit.core.utils.DwzTableUtils;
import com.centit.core.utils.ExtremeTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.OaLeaveOver;
import com.centit.oa.po.OaLeaveOverTime;
import com.centit.oa.po.oaAttendanceSummary;
import com.centit.oa.service.OaLeaveOverTimeManager;
import com.centit.support.utils.Algorithm;
import com.centit.support.utils.Algorithm.ParentChild;
import com.centit.support.utils.DatetimeOpt;
import com.centit.support.utils.StringBaseOpt;
import com.centit.sys.po.FDatadictionary;
import com.centit.sys.po.FRoleinfo;
import com.centit.sys.po.FUnitinfo;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.po.FUserunit;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.service.SysUnitManager;
import com.centit.sys.service.SysUserManager;
import com.centit.sys.util.DateUtil;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("deprecation")
public class OaLeaveOverTimeAction extends BaseEntityDwzAction<OaLeaveOverTime> {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private OaLeaveOverTimeManager oaLeaveOverTimeMgr;
    private SysUnitManager sysUnitManager;
    private SysUserManager sysUserManager; 
    private List<Map<String, String>> jsonDatas;
    private List<FUserinfo> userinfos;
    public List<Map<String, String>> getJsonDatas() {
        return jsonDatas;
    }
    public void setJsonDatas(List<Map<String, String>> jsonDatas) {
        this.jsonDatas = jsonDatas;
    }
    public void setUserinfos(List<FUserinfo> userinfos) {
        this.userinfos = userinfos;
    }
    public void setSysUnitManager(SysUnitManager sysUnitManager) {
        this.sysUnitManager = sysUnitManager;
    }
    public void setSysUserManager(SysUserManager sysUserManager) {
        this.sysUserManager = sysUserManager;
    }
    public void setOaLeaveOverTimeMgr(OaLeaveOverTimeManager baseMgr) {
        this.oaLeaveOverTimeMgr = baseMgr;
        setBaseEntityManager(oaLeaveOverTimeMgr);
    }
    private String filename;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    private InputStream inputStream;

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
    @SuppressWarnings("unchecked")
    public String list() {
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);

        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        String orderField = request.getParameter("orderField");
        String orderDirection = request.getParameter("orderDirection");

        if (!StringUtils.hasText(orderField)
                && !StringUtils.hasText(orderDirection)) {
            filterMap.put(CodeBook.SELF_ORDER_BY, "usercode desc");// 默认按人员排序
        } else if (StringUtils.hasText(orderField)
                && StringUtils.hasText(orderDirection)) {

            filterMap.put(CodeBook.SELF_ORDER_BY, orderField + " "+ orderDirection);
        }
        PageDesc pageDesc = DwzTableUtils.makePageDesc(request);
//        List<VaskForLeaveTimeOfPerson> objects = VaskForLeaveTimeOfPersonMgr.listObjects(filterMap, pageDesc);
//        request.setAttribute("objList",objects);
        this.pageDesc = pageDesc;
        totalRows = pageDesc.getTotalRows();
        setbackSearchColumn(filterMap);
        return LIST;
    }
    private void getUnitTree() {
        List<FUnitinfo> units = new ArrayList<FUnitinfo>();
        // 1.取最上级的机构
        Map<String, Object> filterMap = new HashMap<String, Object>();
        filterMap.put("PARENTUNIT", "0");
        List<FUnitinfo> unitinfos = sysUnitManager.listObjects(filterMap);
        if (unitinfos != null && !unitinfos.isEmpty()) {
            FUnitinfo unitinfo = unitinfos.get(0);
            // 2.取最上级的机构的所有子机构
            units = sysUnitManager.getAllSubUnits(unitinfo.getUnitcode());
            List<FUnitinfo> delList = new ArrayList<FUnitinfo>();
            // 剔除非科室的部门
            if (units != null && units.size() > 0) {
                for (FUnitinfo fUnitinfo : units) {
                    if ("1".equals(fUnitinfo.getParentunit())
                            || "1".equals(fUnitinfo.getUnitcode())) {
                        continue;
                    } else {
                        delList.add(fUnitinfo);
                    }
                }
                units.removeAll(delList);
            }
            ParentChild<FUnitinfo> c = new Algorithm.ParentChild<FUnitinfo>() {
                public boolean parentAndChild(FUnitinfo p, FUnitinfo c) {
                    return p.getUnitcode().equals(c.getParentunit());
                }
            };
            Algorithm.sortAsTree(units, c);
            List<Integer> optIndex = Algorithm.makeJqueryTreeIndex(units, c);
            ServletActionContext.getContext().put("INDEX",getOptIndex(optIndex));
            request.setAttribute("units", units);
        }
    }
    // 树形结构
    private static String getOptIndex(List<Integer> optIndex) {
        JSONObject jo = new JSONObject();
        jo.put("indexes", optIndex);
        return jo.toString();
    }

    public String edit() {
        List<FUserunit> unitusers = new ArrayList<FUserunit>();
        if (object == null) {
            object = new OaLeaveOverTime();
        } else {
            OaLeaveOverTime dbobject = oaLeaveOverTimeMgr.getObject(object);
            if (dbobject != null)
                object.copyNotNullProperty(dbobject);
            else
                oaLeaveOverTimeMgr.clearObjectProperties(object);
        }
        // 2.生成左侧的机构树结构
        this.getUnitTree();
        // 3.生成相关用户
        String unitcode = request.getParameter("s_unitcode");// 用户点击的机构对应的机构编码
        if (StringUtils.hasText(unitcode)) {
            unitusers = this.getUsers(unitcode);
        }
        request.setAttribute("unitusers", unitusers);
        return EDIT;
    }
    /**
     * 取同一机构下的用户机构对象
     */
    private List<FUserunit> getUsers(String unitcode) {
        List<FUserunit> unitusers = new ArrayList<FUserunit>();
        if (StringUtils.hasText(unitcode))
            unitusers = sysUnitManager.getSysUsersByUnitId(unitcode);
        return unitusers;
    }
    /**
     * 生成请假信息
     */
    public String save() {
        FUserDetail user = (FUserDetail) getLoginUser();
        oaLeaveOverTimeMgr.save(object, user.getUsercode());
        return SUCCESS;
    }

    /**
     * 给某个指定用户请假
     */
    public String add() {

        return "add";
    }
    /**
     * 查看某个人的请假信息
     */
    public String view() {
        if(object!=null&&object.getLoroNo()!=null){
            OaLeaveOverTime object1=oaLeaveOverTimeMgr.getObjectById(object.getLoroNo());
            this.oaLeaveOverTimeMgr.setObject(object1,null);
            oaLeaveOverTimeMgr.setHoliday(object1);
            request.setAttribute("object", object1);
        }
         return VIEW;
     }
     /**
      * 取登陆者所在机构下的所有用户的用户编码
      */
//     public List<String> getUserCodes(){
//         List<String> usercodes=new ArrayList<String>();
//         FUserDetail user=(FUserDetail) getLoginUser();
//         List<FUserinfo> fellows=sysUserManager.getUserUnderUnit(user.getPrimaryUnit());
//         if(fellows!=null&&!fellows.isEmpty())
//         for (FUserinfo obj : fellows) {
//             usercodes.add(obj.getUsercode());
//         }
//         return usercodes;
//     }
     /**
      * 显示请假列表
      */
     @SuppressWarnings("unchecked")
     public String listDetail(){
         Map<Object, Object> paramMap = request.getParameterMap();
         resetPageParam(paramMap);
         Map<String, Object> filterMap = convertSearchColumn(paramMap);
         Limit limit = ExtremeTableUtils.getLimit(request);
         PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
         String auditDate=(String) filterMap.get("auditDate");//统计时间
         if(!StringUtils.hasText(auditDate)){
             //filterMap.put("auditDate", DatetimeOpt.convertDateToString(new Date(), "yyyy-MM"));
         }
         
         //取同事编码集合
         List<String> usercodes=new ArrayList<String>();
         usercodes=this.getUserinfos();
         //默认查询当前月份第一天到现在的记录
         if(StringBaseOpt.isNvl((String)filterMap.get("duringDateBeg"))&&StringBaseOpt.isNvl((String)filterMap.get("duringDateEnd"))){
             filterMap.put("duringDateBeg",DateUtil.getCurrentMonthOfDay() );
             filterMap.put("duringDateEnd", DatetimeOpt.convertDateToString(new Date(), "yyyy-MM-dd"));
         }
         //展示搜索年月条件
         this.showNow(filterMap.get("duringDateBeg"), filterMap.get("duringDateEnd"));
         List<OaLeaveOverTime> objects=oaLeaveOverTimeMgr.listOaLeaveOvers(filterMap, pageDesc, usercodes);
         objList = this.oaLeaveOverTimeMgr.setOaLeaveOverTimes(objects,(String) filterMap.get("auditDate"));
         totalRows = pageDesc.getTotalRows();
         setbackSearchColumn(filterMap);
         //return "listdetail";
         return "listdetailV2";
     }
     /**
      * 显示提示当前时间
      */
     public void showNow(Object duringDateBeg, Object duringDateEnd){
         String nowStr = "";
         String duringDateBegTemp = (String)duringDateBeg;
         String duringDateEndTemp = (String)duringDateEnd;
         nowStr=DatetimeOpt.convertDateToString(DatetimeOpt.convertStringToDate(duringDateBegTemp, "yyyy-MM-dd"), "yyyy年MM月dd日");
         nowStr += "~" + DatetimeOpt.convertDateToString(DatetimeOpt.convertStringToDate(duringDateEndTemp, "yyyy-MM-dd"), "yyyy年MM月dd日"); 
         FUserDetail user=(FUserDetail) getLoginUser();
         if(user!=null){
             Map<String,Object> filterMap=new HashMap<String, Object>();
             filterMap.put("UNITCODE", user.getPrimaryUnit());
             List<FUnitinfo> unitinfos=sysUnitManager.listObjects(filterMap);
             if(unitinfos!=null){
                 FUnitinfo unit=unitinfos.get(0);
                 nowStr+=" ,"+unit.getUnitname()+"的考勤汇总";
             }
         }
         request.setAttribute("nowStr", nowStr);
     }
     /**
      * 删除假期
      */
     public String delete(){
         
         return SUCCESS;
     }
     /**
      * 查出登陆者所在机构的所有用户的用户编码
      */
     public List<String> getUserinfos(){
         List<String> usercodes=new ArrayList<String>();
         FUserDetail user=(FUserDetail) getLoginUser();
         String unitcode=user.getPrimaryUnit();
         List<FUserunit> userunits=this.getUsers(unitcode);
         if(userunits!=null&&!userunits.isEmpty())
         for (FUserunit userunit : userunits) {
        	 if ("T".equals(userunit.getIsprimary())) {
        		 String usercode=userunit.getUsercode();
                 //获取人员信息 是否在用
                 FUserinfo userinfo=sysUserManager.getObjectById(usercode) ;
                 if(null!=userinfo&&"T".equals(userinfo.getIsvalid())){
                     
                     if(usercodes.isEmpty()||!usercodes.contains(usercode))
                         usercodes.add(userunit.getUsercode());
                     
                 }
        	 }
         }
         if ("001821".equals(unitcode)) {//办公室负责考勤处领导
        	 List<FUserunit> userunits2=this.getUsers("001801");
             if(userunits2!=null&&!userunits2.isEmpty())
             for (FUserunit userunit : userunits2) {
            	 if ("T".equals(userunit.getIsprimary())) {
            		 String usercode=userunit.getUsercode();
                     //获取人员信息 是否在用
                     FUserinfo userinfo=sysUserManager.getObjectById(usercode) ;
                     if(null!=userinfo&&"T".equals(userinfo.getIsvalid())){
                         
                         if(usercodes.isEmpty()||!usercodes.contains(usercode))
                             usercodes.add(userunit.getUsercode());
                         
                     }
            	 }
             }
         }
         return usercodes;
     }
     /**
      * 查出登陆者所在机构的所有用户的用户编码
      */
     public List<String> getUserinfos(String unitcode){
    	 if (org.apache.commons.lang.StringUtils.isBlank(unitcode) || "undefined".equals(unitcode)) {
    		 FUserDetail user=(FUserDetail) getLoginUser();
             unitcode=user.getPrimaryUnit();
    	 }
         List<String> usercodes=new ArrayList<String>();
         List<FUserunit> userunits=this.getUsers(unitcode);
         if(userunits!=null&&!userunits.isEmpty())
         for (FUserunit userunit : userunits) {
        	 if ("T".equals(userunit.getIsprimary())) {
        		 String usercode=userunit.getUsercode();
                 //获取人员信息 是否在用
                 FUserinfo userinfo=sysUserManager.getObjectById(usercode) ;
                 if(null!=userinfo&&"T".equals(userinfo.getIsvalid())){
                     
                     if(usercodes.isEmpty()||!usercodes.contains(usercode))
                         usercodes.add(userunit.getUsercode());
                     
                 }
        	 }
         }
         /*
         if ("001821".equals(unitcode)) {//办公室负责考勤处领导
        	 List<FUserunit> userunits2=this.getUsers("001801");
             if(userunits2!=null&&!userunits2.isEmpty())
             for (FUserunit userunit : userunits2) {
            	 if ("T".equals(userunit.getIsprimary())) {
            		 String usercode=userunit.getUsercode();
                     //获取人员信息 是否在用
                     FUserinfo userinfo=sysUserManager.getObjectById(usercode) ;
                     if(null!=userinfo&&"T".equals(userinfo.getIsvalid())){
                         
                         if(usercodes.isEmpty()||!usercodes.contains(usercode))
                             usercodes.add(userunit.getUsercode());
                         
                     }
            	 }
             }
         }
         */
         return usercodes;
     }
     /**
      * 新增（给登陆者的下级用户）
      */
     public String addDetail(){
         //查出登陆者的下级机构的所有用户
        request.setAttribute("userCodes", this.getUserinfos());
         return "addDetail";
     }
     /**
      * 对应addDetail的保存方法
      */
     public String saveDetail(){
         FUserDetail user=(FUserDetail) getLoginUser();
         // 获取数据库中原来的请假信息，并设置请假开始时间 和结束时间
         OaLeaveOverTime dbObject = oaLeaveOverTimeMgr.getObjectById(object.getLoroNo());
         if(null!= dbObject){
             dbObject = oaLeaveOverTimeMgr.setObject(dbObject, null);
         }
         oaLeaveOverTimeMgr.save(object, user.getUsercode());
         
         // 更新对应的考勤信息
         oaLeaveOverTimeMgr.updateAttendanceDetails(object, dbObject, "1");
         
         return "saveSuccess";
     }
     /**
      * 对应addDetail的保存方法
      */
     public String editDetail(){
         //查出登陆者的下级机构的所有用户
         request.setAttribute("userCodes", this.getUserinfos());
         //查出要修改的对象
         OaLeaveOverTime dbObject=new OaLeaveOverTime();
         if(object.getLoroNo()!=null){
              dbObject=oaLeaveOverTimeMgr.getObjectById(object.getLoroNo());
              if(dbObject!=null)
                  dbObject=oaLeaveOverTimeMgr.setObject(dbObject,null);
         }
         request.setAttribute("dbObject", dbObject);
         return "addDetail";
     }
     /**
      * 删除请假对象
      */
     public String deleteDetail(){
         //查出要删除的对象
         OaLeaveOverTime dbObject=new OaLeaveOverTime();
         if(object.getLoroNo()!=null){
             dbObject=oaLeaveOverTimeMgr.getObjectById(object.getLoroNo());
             if(dbObject!=null){
                 // 获取开始时间和结束时间
                 dbObject=oaLeaveOverTimeMgr.setObject(dbObject,null);
                 // 更新对应的考勤信息
                 oaLeaveOverTimeMgr.updateAttendanceDetails(dbObject, null, "0");
                 
                 this.oaLeaveOverTimeMgr.deleteBatch(dbObject.getCreateId());
             }
        }
         return "saveSuccess";
     }
    public String editDraft3() throws IOException {
        List<String> Users = this.getUserinfos(request.getParameter("selUnit"));
        @SuppressWarnings("unchecked")
        Map<String, Object> filterMap = convertSearchColumn(request
                .getParameterMap());
        Limit limit = ExtremeTableUtils.getLimit(request);
        PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
        List<String> list = new ArrayList<String>();
        String ReasonType=request.getParameter("ReasonType");
        if(!StringUtils.hasText(ReasonType)){
             list.add("N");
        }
        else{
            String[] value =ReasonType.split(",");
            for(int i=0;i<value.length;i++){
                list.add(value[i]);
            }
            request.setAttribute("ReasonType", ReasonType);
        }
         //将页面上的选择字符串进行切割
        String auditDate=request.getParameter("auditDate");//统计时间
        if(!StringUtils.hasText(auditDate)){
            filterMap.put("auditDate", DatetimeOpt.convertDateToString(new Date(), "yyyy-MM"));
        }else{
            filterMap.put("auditDate", auditDate);
            request.setAttribute("auditDate", auditDate);
        }
        List<oaAttendanceSummary> objectl = new ArrayList<oaAttendanceSummary>();
        int rolnum=0;
        int nums=0;
        for (String user : Users) {
            
            List<String> usercode=new ArrayList<String>();
            usercode.add(user);
            List<OaLeaveOverTime> objects=oaLeaveOverTimeMgr.listOaLeaveOverTime(filterMap, pageDesc, usercode);//汇总时间的全部数据
            oaAttendanceSummary obj = new oaAttendanceSummary();
                List<OaLeaveOverTime> objectLs = new ArrayList<OaLeaveOverTime>();
                for (String l : list) {//根据请假原因进行分类
                    rolnum=list.size();
                    List<OaLeaveOverTime> objectss =new ArrayList<OaLeaveOverTime>();//在一个汇总事情的同一个原因类型的集合
                    String m=l.trim();//去掉空白部分
                    for (OaLeaveOverTime OLo : objects) {
                        if(m.equals(OLo.getReasonType())){
                            objectss.add(OLo);
                        }
                    }
                    OaLeaveOverTime o = new OaLeaveOverTime();
                    String duringDates = "";
                    int num = 0;
                    if (objectss != null && !objectss.isEmpty()) {//进行同一原因类型的时间汇总
                        for (OaLeaveOverTime ob : objectss) {
                            SimpleDateFormat sdf = new SimpleDateFormat(
                                    "yyyy年MM月dd日");
                            String duringDate = sdf.format(ob.getDuringDate());

                            if (StringUtils.hasText(duringDates)) {
                                duringDates += "," + duringDate;
                                num++;
                            } else {
                                duringDates = duringDate;
                                num++;
                            }
                        }
                    }
                    o.setReasonType(m);
                    o.setDuringDates(duringDates);
                    o.setNum(num);
                    nums=nums+num;
                    objectLs.add(o);
                }
                obj.setOaLeaveOverTime(objectLs);
                obj.setUsercode(user);
            
           if(obj!=null){
            objectl.add(obj);
           }
        }
        String type=request.getParameter("type");
        if("D".equals(type)){
        return this.downexcel(objectl);
        }else
        setbackSearchColumn(filterMap);
        request.setAttribute("rolnum", rolnum);
        request.setAttribute("nums", nums);
        request.setAttribute("objList", objectl);
        
        FUserDetail user=(FUserDetail) getLoginUser();
        if ("001843".equals(user.getPrimaryUnit())) {//人事科
        	boolean isKz = false;
        	List<FRoleinfo> roleinfoList = sysUserManager.getSysRolesByUsid(user.getUsercode());
        	if (roleinfoList != null && !roleinfoList.isEmpty()) {
				for (FRoleinfo roleinfo : roleinfoList) {
					if ("G-OAKZ".equals(roleinfo.getRolecode())) {
						isKz = true;
						break;
					}
				}
        	}
        	if (isKz) {
        		request.setAttribute("unitList", this.ruturnUnits());
        		request.setAttribute("rskUnit", "1");
        	}
        }
        
        return "hz";
    }

    // 导出excel
    public String downexcel(List<oaAttendanceSummary> objectL)
            throws IOException {
        HSSFWorkbook wb = new HSSFWorkbook();// 建立新HSSFWorkbook对象
        HSSFSheet sheet = wb.createSheet("sheet1");// 建立新的sheet对象
        HSSFRow row = sheet.createRow(0);
        // 将定义好的第一行单元格添加到工作表中
        HSSFCell cellContext = row.createCell(0);
        cellContext.setCellValue("姓名");
        HSSFCell cellContext1 = row.createCell(1);
        cellContext1.setCellValue("休假类型");
        HSSFCell cellContext2 = row.createCell(2);
        cellContext2.setCellValue("休假时间");
        HSSFCell cellContext3 = row.createCell(3);
        cellContext3.setCellValue("已休");
        int Firstrows = 0;
        int LastRows = 0;
        int nums=0;
        if (objectL != null && !objectL.isEmpty()) {
            for (int j = 0; j < objectL.size(); j++) {
                oaAttendanceSummary oAs = objectL.get(j);
                if( oAs.getOaLeaveOverTime()!=null&&!oAs.getOaLeaveOverTime().isEmpty()){
                for (int i = 0; i < oAs.getOaLeaveOverTime().size(); i++) {
                    HSSFRow row1 = sheet.createRow(i + 1+Firstrows);
                    List<OaLeaveOverTime> objectLs = oAs.getOaLeaveOverTime();
                    FDatadictionary dictPiece =CodeRepositoryUtil.getDataPiece("LeaveOvertime",objectLs.get(i).getReasonType());//获取其他原因名字
                    HSSFCell cellContext4 = row1.createCell(1);
                    cellContext4.setCellValue(dictPiece.getDatavalue());
                    HSSFCell cellContext5 = row1.createCell(2);
                    cellContext5.setCellValue(objectLs.get(i).getDuringDates());
                    HSSFCell cellContext6 = row1.createCell(3);
                    cellContext6.setCellValue(objectLs.get(i).getNum());
                    nums=nums+(int)(objectLs.get(i).getNum());
                    String username =CodeRepositoryUtil.getValue("usercode",oAs.getUsercode());//获取用户名
                    HSSFCell cellContext7 = row1.createCell(0);
                    cellContext7.setCellValue(username);
                }
             LastRows = Firstrows + oAs.getOaLeaveOverTime().size();
                sheet.addMergedRegion(new CellRangeAddress(Firstrows+1, LastRows, 0, 0));
                Firstrows=LastRows;
                }
                }
            HSSFRow row2 = sheet.createRow(Firstrows+1);
            HSSFCell cellContext8 = row2.createCell(0);
            cellContext8.setCellValue("合计");
            HSSFCell cellContext9 = row2.createCell(3);
            cellContext9.setCellValue(nums);
        }
        ByteArrayOutputStream fileOut = new ByteArrayOutputStream();
        try {
            wb.write(fileOut);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
            String fn ="人事考勤统计表样表.xls";
            byte[] b = fileOut.toByteArray();
            inputStream = new ByteArrayInputStream(b);
            this.setFilename(new String(fn.getBytes("GBK"), "ISO8859-1"));
            fileOut.close();
            return "download";
    }
   public String summarList(){
        FUserDetail user=(FUserDetail) getLoginUser();
        if ("001843".equals(user.getPrimaryUnit())) {//人事科
        	boolean isKz = false;
        	List<FRoleinfo> roleinfoList = sysUserManager.getSysRolesByUsid(user.getUsercode());
        	if (roleinfoList != null && !roleinfoList.isEmpty()) {
				for (FRoleinfo roleinfo : roleinfoList) {
					if ("G-OAKZ".equals(roleinfo.getRolecode())) {
						isKz = true;
						break;
					}
				}
        	}
        	if (isKz) {
        		request.setAttribute("unitList", this.ruturnUnits());
        		request.setAttribute("rskUnit", "1");
        	}
        } else if ("001821".equals(user.getPrimaryUnit())) {//办公室要统计处领导的
        	boolean isKz = false;
        	List<FRoleinfo> roleinfoList = sysUserManager.getSysRolesByUsid(user.getUsercode());
        	if (roleinfoList != null && !roleinfoList.isEmpty()) {
				for (FRoleinfo roleinfo : roleinfoList) {
					if ("G-KQ".equals(roleinfo.getRolecode())) {
						isKz = true;
						break;
					}
				}
        	}
        	if (isKz) {
        		List<FUnitinfo> unitlist = new ArrayList<FUnitinfo>();
        		FUnitinfo unitinfo = new FUnitinfo();
        		unitinfo.setUnitcode("001801");
        		unitinfo.setUnitname("处领导");
        		unitlist.add(unitinfo);
        		
        		unitinfo = new FUnitinfo();
        		unitinfo.setUnitcode("001821");
        		unitinfo.setUnitname("办公室");
        		unitlist.add(unitinfo);
        		
        		request.setAttribute("unitList", unitlist);
        		request.setAttribute("rskUnit", "1");
        	}
        }
        request.setAttribute("unitcode", user.getPrimaryUnit());
        return "summarList";
    }

    private JSONObject note;

    public JSONObject getNote() {
        return note;
    }

    public void setNote(JSONObject note) {
        this.note = note;
    }
   /**
    * 检查是否已请过假
    */
    public String isExist() {
        String usercode = request.getParameter("usercode");
        String beginTime = request.getParameter("beginTime");
        String endTime = request.getParameter("endTime");
        if (StringUtils.hasText(usercode) && StringUtils.hasText(beginTime)
                && StringUtils.hasText(endTime)) {
          String info=oaLeaveOverTimeMgr.isExist(usercode, beginTime, endTime);
          note=new JSONObject();
          if(StringUtils.hasText(info)){//已存在
              note.put("result", info);
          }else{//不存在
              note.put("result", "1"); 
          }
        }
        return "info";
    }
    /*
     * 列表查询时获取人员
     */
    public String selectUser() {
        List<FUserinfo> temp = CodeRepositoryUtil.getAllUsers("T");
        jsonDatas = new ArrayList<Map<String, String>>();
        userinfos = new ArrayList<FUserinfo>();
        String matchInfo = request.getParameter("q");
        String matchCount = "5";

        int count = (null == matchInfo || "".equals(matchInfo)) ? 0 : Integer
                .parseInt(matchCount);

        if (null == matchInfo || "".equals(matchInfo)) {
            return "userinfos";
        }

        matchInfo = matchInfo.toLowerCase();
        int index = 0;
        for (FUserinfo user : temp) {
            if (index >= count)
                break;

            if (user.getUsername().toLowerCase().contains(matchInfo)) {
                index++;
                userinfos.add(copyUserInfo(user));
            } else if (user.getLoginname().toLowerCase().contains(matchInfo)) {
                index++;
                userinfos.add(copyUserInfo(user));
            } else if (user.getUsercode().toLowerCase().contains(matchInfo)) {
                index++;
                userinfos.add(copyUserInfo(user));
            }
        }
        for (FUserinfo userinfo : userinfos) {
            Map<String, String> m = new HashMap<String, String>();
            m.put("id", userinfo.getUsercode());
            m.put("name", userinfo.getUsername() + "[" + userinfo.getUsercode()
                    + "]" + "[" + userinfo.getLoginname() + "]");
            m.put("showName", userinfo.getUsername());
            jsonDatas.add(m);
        }
        return "userinfos";
    }

    private static FUserinfo copyUserInfo(FUserinfo info) {
        FUserinfo temp = new FUserinfo();

        temp.setUsercode(info.getUsercode());
        temp.setUsername(info.getUsername());
        temp.setLoginname(info.getLoginname());

        return temp;
    }
    
    /**
     * 根据考勤表模板导出Excel数据
     * 
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    public String doExportByModel() throws FileNotFoundException, IOException {
        // excel模板路径
        File fi = new File(request.getRealPath("/doc/attendance.xls"));
        POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(fi));
        // 读取excel模板
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        // 读取了模板内所有sheet内容
        HSSFSheet sheet = wb.getSheetAt(0);
        // 在相应的单元格进行赋值

        //一、 获取科室人员考勤情况
        Map<String, Object> filterMap = convertSearchColumn(request.getParameterMap());
        // 将页面上的选择字符串进行切割
        String auditDate = request.getParameter("auditDate");// 统计时间        
        if (!StringUtils.hasText(auditDate)) {
            filterMap.put("auditDate",
                    DatetimeOpt.convertDateToString(new Date(), "yyyy-MM"));
            auditDate=DatetimeOpt.convertDateToString(new Date(), "yyyy-MM");
        } else {
            filterMap.put("auditDate", auditDate.substring(0, 7));
            request.setAttribute("auditDate", auditDate.substring(0, 7));
        }
        //获取本科室下的所有在用人员，待用
        FUserDetail user=(FUserDetail) getLoginUser();
        String selUnitcode = request.getParameter("selUnitcode");
        if (org.apache.commons.lang3.StringUtils.isBlank(selUnitcode)) {
        	selUnitcode = user.getPrimaryUnit();
        }
        List<FUserinfo> Users =oaLeaveOverTimeMgr.getUsersByUnitCode(selUnitcode);
        //插入 模板中的对应内容：
        //1、给部门赋值
        HSSFCell cell1 = sheet.getRow(1).getCell(4);//插入部门赋值所在位置 2E
        cell1.setCellValue(CodeRepositoryUtil.getValue("unitcode", selUnitcode));
        //2、给考勤月份赋值
        HSSFCell cell2 = sheet.getRow(1).getCell(23);//插入年份赋值所在位置 2y 25 
        cell2.setCellValue(auditDate.substring(0, 4)+" 年 "+Integer.parseInt(auditDate.substring(5, 7))+" 月份");
              
        //根据人员迭代，循环列表，准备插入对应人员考勤记录信息
        if (Users != null && Users.size() > 0) {
            for (int i = 5,m=0; m < Users.size(); i++,m++) {
                // 3、插入列姓名
                HSSFCell cell3 = sheet.getRow(i).getCell(0);
                cell3.setCellValue(Users.get(m).getUsername());
                //获取单个人员的一个月内的考勤情况信息
                filterMap.put("usercode", Users.get(m).getUsercode());
                List<OaLeaveOver> objects = oaLeaveOverTimeMgr.listOaLeaveOverTime(filterMap);
                //4、横向，按照人员、日期插入对应日期的考勤情况
                if (objects != null && objects.size() > 0) {
                    for (int y = 0; y <=31; y++) {// 日期
                        for (int n = 0; n < objects.size(); n++) {
                            OaLeaveOver oaLeaveOver = objects.get(n);
                            if (y == oaLeaveOver.getDaynum()) {//按考勤天 判断
                                HSSFCell cell = sheet.getRow(i).getCell(y);
                                cell.setCellValue(oaLeaveOver.getState());
                            }
                        }
                    }
                    
                }
            }
        }
        try {
            ActionContext ctx = ActionContext.getContext();
            HttpServletResponse response = (HttpServletResponse) ctx
                    .get(ServletActionContext.HTTP_RESPONSE);
            response.setHeader("Content-Disposition", "attachment;filename="
                    + new String((CodeRepositoryUtil.getValue("unitcode",selUnitcode)+auditDate.substring(0, 4)+"年"+Integer.parseInt(auditDate.substring(5, 7))+"月份考勤记录表.xls").getBytes(), "ISO8859-1"));// 设定输出文件头
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");// 定义输出类型

            OutputStream output = response.getOutputStream();
            output.flush();
            wb.write(output);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Output is closed ");
        }
        return null;
    }
    
    private  List<FUnitinfo> ruturnUnits() {
        
        List<FUnitinfo> unitlist = sysUnitManager.getAllSubUnits("1");
        List<FUnitinfo> delList = new ArrayList<FUnitinfo>();
        // 剔除非科室的部门
        if (unitlist != null && unitlist.size() > 0) {
            for (FUnitinfo fUnitinfo : unitlist) {
                if ("1".equals(fUnitinfo.getParentunit())
                        || "1".equals(fUnitinfo.getUnitcode())) {
                    continue;
                } else {
                    delList.add(fUnitinfo);
                }
            }
            unitlist.removeAll(delList);
        }
        
        return unitlist;
    }
}
