package com.centit.oa.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.centit.core.action.BaseEntityDwzAction;
import com.centit.core.dao.CodeBook;
import com.centit.core.utils.DwzTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.EquipmentInfo;
import com.centit.oa.po.EquipmentUsing;
import com.centit.oa.service.EquipmentInfoManager;
import com.centit.oa.service.EquipmentUsingManager;
import com.centit.support.utils.Algorithm;
import com.centit.support.utils.Algorithm.ParentChild;
import com.centit.support.utils.DatetimeOpt;
import com.centit.support.utils.ExportExcelUtil;
import com.centit.support.utils.StringRegularOpt;
import com.centit.sys.po.FDatacatalog;
import com.centit.sys.po.FDatadictionary;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.service.CodeRepositoryUtilExtend;
import com.centit.sys.service.DictionaryManager;

public class EquipmentUsingAction extends BaseEntityDwzAction<EquipmentUsing> {
    private static final Log log = LogFactory
            .getLog(EquipmentUsingAction.class);

    private static final long serialVersionUID = 1L;

    private static final String String = null;
    private EquipmentUsingManager equipmentUsingMag;

    private EquipmentInfoManager equipmentInfoMag;

    private DictionaryManager dictManger;
    
    
    
    private static final String equ= "equipment";
    private List<FDatadictionary> dictDetails;
    
    private List<EquipmentInfo> equList;
    
    private Object json;
    
    private List<Map<String, String>> jsonDatas;

    public List<Map<String, String>> getJsonDatas() {
        return jsonDatas;
    }

    public void setJsonDatas(List<Map<String, String>> jsonDatas) {
        this.jsonDatas = jsonDatas;
    }
    
    private String[] fdesc;

    public String[] getFdesc() {
        return fdesc;
    }

    public void setFdesc(String[] fdesc) {
        this.fdesc = fdesc;
    }

    public DictionaryManager getDictManger() {
        return dictManger;
    }

    public void setDictManger(DictionaryManager dictManger) {
        this.dictManger = dictManger;
    }

    public List<FDatadictionary> getDictDetails() {
        return dictDetails;
    }

    public void setDictDetails(List<FDatadictionary> dictDetails) {
        this.dictDetails = dictDetails;
    }

    private String s_supEquipmentType;
    private String s_equipmentType;

    public void setEquipmentInfoManager(EquipmentInfoManager equipmentInfoMag) {
        this.equipmentInfoMag = equipmentInfoMag;
    }

    private String type;

    public void setEquipmentUsingManager(EquipmentUsingManager basemgr) {
        equipmentUsingMag = basemgr;
        this.setBaseEntityManager(equipmentUsingMag);
    }

    @SuppressWarnings({ "unchecked", "static-access" })
    public String list() {
        try {
            
            PageDesc pageDesc = DwzTableUtils.makePageDesc(this.request);
            Map<String, Object> filterMap = convertSearchColumn(request
                    .getParameterMap());
            String orderField = request.getParameter("orderField");
            String orderDirection = request.getParameter("orderDirection");
            if (!StringUtils.isBlank(orderField)
                    && !StringUtils.isBlank(orderDirection)) {
                filterMap.put(CodeBook.SELF_ORDER_BY, orderField + " "
                        + orderDirection);
                request.setAttribute("orderDirection", orderDirection);
                request.setAttribute("orderField", orderField);
            }
            if (StringUtils.isBlank(orderField)) {
                filterMap.put(CodeBook.SELF_ORDER_BY, "useRequestId" + " "
                        + "desc");
            }
            if (!StringUtils.isEmpty(type) && type.equals("mine")) {
                FUserDetail user = ((FUserDetail) getLoginUser());// 获取当前用户的信息我的预约
                filterMap.put("applicanteq", user.getUsercode());
                if(StringUtils.isEmpty((String)filterMap.get("equipmentId"))){
                    filterMap.remove("equipmentId");
                }                
             
                

            }
            //新增产看单个资源的申请历史信息
            if (null!=filterMap.get("equipmentId")) {
                filterMap.put("equipmentId", Long.valueOf(String.valueOf(filterMap.get("equipmentId"))));
            }
            
            String extraCode = null;

            if (StringUtils.isEmpty(s_equipmentType)) {
                extraCode = s_supEquipmentType;
            } else {
                extraCode = s_equipmentType;
            }
            String shql = "";
            StringBuffer types = new StringBuffer();
            if (!StringUtils.isEmpty(extraCode)) {
                List<FDatadictionary> l = CodeRepositoryUtilExtend
                        .getTreeDictionaryStartBy("equipment", extraCode, true);
                for (FDatadictionary f : l) {
                    types.append(", '" + f.getDatacode() + "'");
                }
                if (!StringUtils.isBlank(types.toString())) {
                    String str = types.substring(1, types.length());
                    String sql="select equipmentId from EquipmentInfo where equipmentType in( "
                            + str + ") and equipmentState='T'";
                    if (!StringUtils.isEmpty(type) && type.equals("mineConfirm")) {
                        FUserDetail user = ((FUserDetail) getLoginUser());// 获取当前用户的信息我的确认
                        sql+=" and equipmentCharge = '"+user.getUsercode()+"'";

                    }
                    shql = " From EquipmentUsing where equipmentId in ("+sql+" ) ";
                }
                
            }

            objList = baseEntityManager.listObjects(shql, filterMap, pageDesc);
            setbackSearchColumn(filterMap);
            putTree("equipment");
            jsonDate((String)filterMap.get("applicant"));
            totalRows = pageDesc.getTotalRows();
            return LIST;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    /**
     * 返回页面不一样
     * 
     * @return
     */
    public String listMine() {
        setType("mine");
        list();
        equlist();
//        request.setAttribute("equList", "equList");
        
        return "listMine";
    }
    
    /**
     * 我的确认
     * @return
     */
    public String listMineConfirm() {
        setType("mineConfirm");
        list();
        return "listMineConfirm";
    }
    
    /**
     * 固定资产列表，默认查询在用的固定资产
     * 
     * @return
     */
    @SuppressWarnings("rawtypes")
    public List equlist() {
        
            Map<String, Object> filterMap = new HashMap<String, Object>();
            filterMap.put("equipmentState", "T");
            String extraCode = null;
            //s_equipmentType用来标识资源种类：会议室，车辆，资源
            if (StringUtils.isEmpty(s_equipmentType)) {
                extraCode = s_supEquipmentType;
            } else {
                extraCode = s_equipmentType;
            }
            String shql = "";
            StringBuffer types = new StringBuffer();
            if (!StringUtils.isEmpty(extraCode)) {
                List<FDatadictionary> l = CodeRepositoryUtilExtend
                        .getTreeDictionaryStartBy(equ, extraCode, true);
                for (FDatadictionary f : l) {
                    types.append(", '" + f.getDatacode() + "'");
                }
                if (!StringUtils.isBlank(types.toString())) {
                    String str = types.substring(1, types.length());
                    shql = "  From EquipmentInfo where equipmentType in( "
                            + str + ")  ";
                }
            }
            equList = equipmentInfoMag.listObjects(shql, filterMap);
            return equList;
    }
    
    

    public String edit() {

        Long equipmentId = object.getEquipmentId();
        
        
                
        super.edit();
        if (object.getEquipmentId() == null) {
            object.setEquipmentId(equipmentId);
        }
        
        if (object.getEquipmentId() != null) {
            EquipmentInfo info = equipmentInfoMag.getObjectById(object
                    .getEquipmentId());
            if (info != null) {
                object.setEquipmentInfo(info);
            }
        }
        putTree("equipment");
        return EDIT;
    }

    public String view() {

        putTree("equipment");
        
        EquipmentUsing o = baseEntityManager.getObject(object);
        if (object == null) {

            return LIST;
        }
        if (o != null)
            baseEntityManager.copyObject(object, o);
       
        
        if (object.getEquipmentId() != null) {
            EquipmentInfo info = equipmentInfoMag.getObjectById(object
                    .getEquipmentId());
            if (info != null) {
                object.setEquipmentInfo(info);
            }
        }
        return VIEW;
    }

    public String infoDetailview() {
        view();
        putTree("equipment");
        return "infoDetailview";
    }

    /**
     * 验证申请时间是否空闲
     * 
     * @throws IOException
     */
    public void isFree() throws IOException {
       
        ServletActionContext.getResponse().getWriter().print(isExist());
      
    }

   public String isTFree() throws IOException {
        json=true;
       
      if ( !isExist()){
          json="时间已被申请";
          return "json";  
      }
       
      return "json";
     
   }
    

    @SuppressWarnings("rawtypes")
    private boolean isExist() {
        if (StringRegularOpt.isNvl(object.getEquipmentState())) {
            
            if (object.getPlanBeginTime() != null
                    && object.getPlanEndTime() != null) {
                Map<String, Object> filterMap = new HashMap<String, Object>();
                Map<String, Object> filterMap1 = new HashMap<String, Object>();
                Map<String, Object> filterMap2 = new HashMap<String, Object>();
                Map<String, Object> filterMap3 = new HashMap<String, Object>();
                filterMap.put("equipmentId", object.getEquipmentId());
                filterMap.put("planBeginTimeBegin", DatetimeOpt
                        .convertDatetimeToString(object.getPlanBeginTime()));
                filterMap.put("planBeginTimeEnd", DatetimeOpt
                        .convertDatetimeToString(object.getPlanEndTime()));
                String shql = " From EquipmentUsing where equipmentState in (1,2) ";// 资产使用状态1.申请;2.审核（接受申请）

                filterMap1.put("equipmentId", object.getEquipmentId());
                filterMap1.put("planEndTimeBegin", DatetimeOpt
                        .convertDatetimeToString(object.getPlanBeginTime()));
                filterMap1.put("planEndTimeEnd", DatetimeOpt
                        .convertDatetimeToString(object.getPlanEndTime()));

                filterMap2.put("equipmentId", object.getEquipmentId());
                filterMap2.put("planBeginTimeEnd", DatetimeOpt
                        .convertDatetimeToString(object.getPlanBeginTime()));
                filterMap2.put("planEndTimeBegin", DatetimeOpt
                        .convertDatetimeToString(object.getPlanEndTime()));

                filterMap3.put("equipmentId", object.getEquipmentId());
                filterMap3.put("planBeginTimeBegin", DatetimeOpt
                        .convertDatetimeToString(object.getPlanBeginTime()));
                filterMap3.put("planEndTimeEnd", DatetimeOpt
                        .convertDatetimeToString(object.getPlanEndTime()));

                objList = baseEntityManager.listObjects(shql, filterMap);
                List objList1 = baseEntityManager.listObjects(shql, filterMap1);
                List objList2 = baseEntityManager.listObjects(shql, filterMap2);
                List objList3 = baseEntityManager.listObjects(shql, filterMap3);
                if (objList != null && objList.size() > 0 || objList1 != null
                        && objList1.size() > 0 || objList2 != null
                        && objList2.size() > 0 || objList3 != null
                        && objList3.size() > 0) {
                    return false;
                   

                } else {
                    return true;
                   
                }
            } else {
                return true;
              
            }
        }
        
        return true;
    }

    public String save() {
        // object.replaceEquipmentUsings( equipmentUsings);
        // 先判断是否可以申请
       if(!isExist()) {
           
           request.setAttribute("errorMessage", "抱歉时间段已被申请，请重新填写时间");
           
           return ERROR;
       }
           
                FUserDetail user = ((FUserDetail) getLoginUser());// 获取当前用户的信息
                if (object.getUseRequestId() == null) {
                    // object.setUseRequestId(equipmentUsingMag.genNextUseRequestId());
                    object.setApplicant(user.getUsercode());
                    object.setApplicantTime(DatetimeOpt.currentUtilDate());
                }
                if (StringRegularOpt.isNvl(object.getEquipmentState())) {
                    object.setEquipmentState("1");// 申请
                } else if ("1".equals(object.getEquipmentState())) {// 确认完成
                    if ("1".equals(object.getBeAccepted())) {// beAccepted=1.接受申请等待确认进入反馈State=2
                        object.setAuditor(user.getUsercode());
                        object.setAuditTime(DatetimeOpt.currentUtilDate());
                        object.setEquipmentState("2");
                    } else if ("0".equals(object.getBeAccepted())) {// beAccepted=0.不接受申请
                        object.setAuditor(user.getUsercode());
                        object.setAuditTime(DatetimeOpt.currentUtilDate()); // 使用结束拒绝申请State=4
                        object.setEquipmentState("4");
                    }

                } else if ("2".equals(object.getEquipmentState())) {
                    object.setFeedbackUser(user.getUsercode());
                    object.setFeedbackTime(DatetimeOpt.currentUtilDate());
                    object.setEquipmentState("3");// 完成反馈，使用结束 State=3
                }

                super.save();
          
        

        if ("1".equals(object.getEquipmentState())) {
            return "infolist";

        } else {
            return list();
        }

    }

    public String delete() {
        super.delete();

        return "delete";
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getS_supEquipmentType() {
        return s_supEquipmentType;
    }

    public void setS_supEquipmentType(String s_supEquipmentType) {
        this.s_supEquipmentType = s_supEquipmentType;
    }

    public String getS_equipmentType() {
        return s_equipmentType;
    }

    public void setS_equipmentType(String s_equipmentType) {
        this.s_equipmentType = s_equipmentType;
    }

    // 树形结构
    public void putTree(String catalogCode) {
        FDatacatalog dbobject = dictManger.getObjectById(catalogCode);
        setFdesc(dictManger.getFieldsDesc(dbobject.getFielddesc(),
                dbobject.getCatalogtype()));
        dictDetails = CodeRepositoryUtilExtend.getTreeDictionaryStartBy(catalogCode,
                s_supEquipmentType, true);
        ParentChild<FDatadictionary> c = new Algorithm.ParentChild<FDatadictionary>() {
            public boolean parentAndChild(FDatadictionary p, FDatadictionary c) {
                return p.getDatacode().equals(c.getExtracode());
            }
        };

        Algorithm.sortAsTree(dictDetails, c);
        List<Integer> optIndex = Algorithm.makeJqueryTreeIndex(dictDetails, c);
        ServletActionContext.getContext().put("INDEX", getOptIndex(optIndex));
    }

    private static String getOptIndex(List<Integer> optIndex) {
        JSONObject jo = new JSONObject();
        jo.put("indexes", optIndex);
        return jo.toString();
    }

    @Override
    public InputStream getExportExcelFile() {
        list();
        // List<EquipmentInfo> infos = baseEntityManager.listObjects();//
        // 获取需要生成Excel的数据
        if (objList != null) {
            for (EquipmentUsing o : objList) {
                o.setApplicant(CodeRepositoryUtil.getValue("usercode",
                        o.getApplicant()));
                o.setEquipmentState(CodeRepositoryUtil.getValue(
                        "EQUIPMENTSTATE", o.getEquipmentState()));
                // o.setPurposeType(CodeRepositoryUtil.getValue("equipment",
                // o.getPurposeType()));
            }
        }
        String[] header = { "固定资产编号", "申请人", "用途类别", "预计开始时间", "预计结束时间", "资产状态" };// 列头
        String[] property = { "equipmentId", "applicant", "purposeType",
                "planBeginTime", "planEndTime", "equipmentState" };
        return ExportExcelUtil.generateExcel(objList, header, property);// 获取导出Excel的字节流
    }

    @Override
    public String exportExcel() {
        try {
            exportFileName = new String("固定资产列表.xls".getBytes("GBK"),
                    "ISO8859-1");// 将导出Excel的文件名进行ISO8859-1编码，否则中文文件名会乱码
        } catch (UnsupportedEncodingException e) {
        }
        return "exportExcel";
    }

    public Object getJson() {
        return json;
    }

    public void setJson(Object json) {
        this.json = json;
    }

    public List<EquipmentInfo> getEquList() {
        return equList;
    }

    public void setEquList(List<EquipmentInfo> equList) {
        this.equList = equList;
    }
    
 public static Log getLog() {
        return log;
    }

public void jsonDate(String usercode){
        
        JSONArray ja = new JSONArray();
        jsonDatas = new ArrayList<Map<String, String>>();
        if (StringUtils.isNotEmpty(usercode)) {
        String[] usercodes = usercode.split(",");
        for (String s : usercodes) {
        Map<String, String> m = new HashMap<String, String>();
        m.put("id", s);
        m.put("showName",CodeRepositoryUtil.getValue("usercode",s));
        jsonDatas.add(m);
        ja.addAll(jsonDatas);
            }
        }
       request.setAttribute("populate", ja.toString()); 
    }
}
