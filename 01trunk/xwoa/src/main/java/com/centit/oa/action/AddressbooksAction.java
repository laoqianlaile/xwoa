package com.centit.oa.action;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.extremecomponents.table.limit.Limit;
import org.springframework.util.StringUtils;

import com.centit.core.action.BaseEntityDwzAction;
import com.centit.core.dao.CodeBook;
import com.centit.core.utils.ExtremeTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.AddressBookRelection;
import com.centit.oa.po.Addressbooks;
import com.centit.oa.service.AddressbooksManager;
import com.centit.oa.service.OaPowerrolergroupManager;
import com.centit.support.utils.ExportExcelUtil;
import com.centit.sys.po.FUnitinfo;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.service.SysUnitManager;
import com.opensymphony.xwork2.ActionContext;

public class AddressbooksAction extends BaseEntityDwzAction<Addressbooks> {
    private static final Log log = LogFactory.getLog(AddressbooksAction.class);

    // private static final ISysOptLog sysOptLog =
    // SysOptLogFactoryImpl.getSysOptLog("optid");

    private static final long serialVersionUID = 1L;
    private AddressbooksManager addressbooksMag;
    private OaPowerrolergroupManager oaPowerrolergroupManager;
  
    
    private String selectedUserCode;// 弹出页面选中
    List<FUnitinfo> units;
    List<FUserinfo> users;
    private String bodycode;
    private String s_type;
    private  Addressbooks addressbooks;

    private SysUnitManager sysUnitManager;
    
    public void setSysUnitManager(SysUnitManager sysUnitManager) {
        this.sysUnitManager = sysUnitManager;
    }

    public void setAddressbooksManager(AddressbooksManager basemgr) {
        addressbooksMag = basemgr;
        this.setBaseEntityManager(addressbooksMag);
    }

 
    
    
    private List<AddressBookRelection> addressBookRelections;

    public List<AddressBookRelection> getNewAddressBookRelections() {
        return this.addressBookRelections;
    }

    public void setNewAddressBookRelections(
            List<AddressBookRelection> addressBookRelections) {
        this.addressBookRelections = addressBookRelections;
    }

    /**
     * 通讯录列表
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    public String list() {
        try {
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Limit limit = ExtremeTableUtils.getLimit(request);
            String orderField = request.getParameter("orderField");
            String orderDirection = request.getParameter("orderDirection");

            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            if (StringUtils.hasText(orderField)
                    && StringUtils.hasText(orderDirection)) {

                filterMap.put(CodeBook.SELF_ORDER_BY, orderField + " "
                        + orderDirection);

            }
            //获取类型
            object.setType(s_type);
            PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
            // creater为当前登录人员
            // filterMap.put("creater",getLoginUserCode());
            objList = addressbooksMag.listObjects(filterMap, pageDesc,
                    getLoginUserCode());
            totalRows = pageDesc.getTotalRows();

            setbackSearchColumn(filterMap);
            this.pageDesc = pageDesc;
            //return object.getType()+"List";
            return object.getType()+"ListV2";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    /**
     * 通讯录列表_new 包含机关，公共，个人
     * 
     * @return
     */
    public String list_new() {
        
        //返回一个lab页面组装这3类通讯
        return  "lab";

    }
    public String save() {

        // add新增
        if (!StringUtils.hasText(object.getAddrbookid())) {

            object.setCreater(getLoginUserCode());
            object.setCreatedate(new Date());
            object.setAddrbookid(addressbooksMag.genNextAddressbookId());
        }

        Addressbooks dbObject = baseEntityManager.getObject(object);
        if (dbObject != null) {
            baseEntityManager.copyObjectNotNullProperty(dbObject, object);
            object = dbObject;
        }

        addressbooksMag.saveObject(object);
     if("P".equals(object.getType())){
         return list();
     }else{
         return oaList();
     }
        

    }
    
    public String  saveTree(){
        Addressbooks dbObject = addressbooksMag.getObjectByCode(
                object.getUnitcode(), object.getType());
       

        if (dbObject != null) {
            // add新增
            if (!StringUtils.hasText(dbObject.getAddrbookid())) {
                object.setCreater(getLoginUserCode());
                object.setCreatedate(new Date());
                object.setAddrbookid(addressbooksMag.genNextAddressbookId());
            }
            
            baseEntityManager.copyObjectNotNullProperty(dbObject, object);
            object = dbObject;
        }
        baseEntityManager.saveObject(object);
        return oaView();
    }
    @Override
    public String delete() {
        
        Addressbooks dbObj = addressbooksMag.getObject(object);
        
        super.delete();
        
        if(null != dbObj && "P".equals(dbObj.getType())){
            return list();
        }else{
            return oaList();
        }
    }
   
    /**
     * 批量删除功能
     * @return
     */
    public String deleteAdds() {
        //批量addrbookid
        String addrbookids = request.getParameter("addrbookids");
        if (StringUtils.hasText(addrbookids)) {
        for (String msgcode : addrbookids.split(",")) {
            object.setAddrbookid(msgcode);
            super.delete();
         }
        }
        
        if("P".equals(object.getType())){
            return list();
        }else{
            return oaList();
        }
    }
    @Override
    public String edit() {
        
        Addressbooks o = baseEntityManager.getObject(object);
        if (o != null) {
            // 将对象o copy给object，object自己的属性会保留
            baseEntityManager.copyObject(object, o);
            request.setAttribute("usercodes",
                    getShareUsercodes(object.getAddrbookid()));
        } else {
            String type=object.getType();
            baseEntityManager.clearObjectProperties(object);
            //设置隐藏值
            object.setType(type);
        }
   
        //当前登录人员主部门
//        String unitcode=CodeRepositoryUtil.getPrimaryUnit(getLoginUserCode());
        //默认为选着树形节点作为unitcode
        if("C".equals(object.getType())&&!StringUtils.hasText(object.getUnitcode())){
            object.setUnitcode(bodycode);
            object.setDeptName(CodeRepositoryUtil.getValue("unitcode", bodycode));
        
        putAllUserTree();
        //不同类型的通讯录备选信息不一样机构为所有机构下拉框，人员为所有人员信息
         units = CodeRepositoryUtil.getAllUnits("T");
         users = CodeRepositoryUtil.getAllUsers("T");
        }
        //根据通讯录类型确定返回页面
        return object.getType()+"Form";
    }

    public String oaEdit() {

        try {
            putUnitTree();
            if (object != null) {
                //根据部门编号，通讯录类型获取信息
                object.setUnitcode(bodycode);
                Addressbooks o = addressbooksMag.getObjectByCode(
                        object.getUnitcode(), object.getType());
                
                if (o != null && o.getAddrbookid() != null) {
                    // 将对象o copy给object，object自己的属性会保留
                    baseEntityManager.copyObject(object, o);
                }
            }
            return "oaUnitEdit";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    
    public String oaView() {
        putUnitTree();
        String turn = null;
        //这边人员和usercode无紧密联系,同时需要显示所在的部门信息
        if ("C".equals(object.getType())) {
            object.setUserName(bodycode);//username在做机关内人员通讯录是存放usercode字段
            Addressbooks o = addressbooksMag.getUserByCode(object.getUserName(),object.getType());
            if (o != null && o.getAddrbookid() != null) {
                // 将对象o copy给object，object自己的属性会保留
                baseEntityManager.copyObject(object, o);
                }
             addressbooks = addressbooksMag.getObjectByCode(
                    object.getUnitcode(), "O"); 
            request.setAttribute("addressbooks",addressbooks);
            turn = "oaUserView";
        } else if ("O".equals(object.getType())) {
                //根据部门编号，通讯录类型获取信息
          
                object.setUnitcode(bodycode);
                Addressbooks o = addressbooksMag.getObjectByCode(
                        object.getUnitcode(), object.getType());
                
                if (o != null && o.getAddrbookid() != null) {
                    // 将对象o copy给object，object自己的属性会保留
                    baseEntityManager.copyObject(object, o);
                }   
            turn = "oaUnitView";
        } else {
            turn = "oaUnitView";
        }
        return turn;
    }

    /**
     * 获取当前部门所有人员 该人员可能不在adderssbooks表中 部门编号bodycode
     * @return
     */
    @SuppressWarnings("unchecked")
    public String oaList(){
//        putAllUserTree();
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);

        PageDesc pageDesc = makePageDesc();
        objList=addressbooksMag.addressbooksCList(filterMap, pageDesc,bodycode);
        totalRows = pageDesc.getTotalRows();

        setbackSearchColumn(filterMap);
        
        Map<String,Object> unitFilter = new HashMap<String, Object>();
        unitFilter.put("isvalid", "T");
        List<FUnitinfo> units = sysUnitManager.listObjects(unitFilter);
        request.setAttribute("allUnits", units);
                
        return "CList";
    }
    HttpServletResponse response;
    
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }
    
   /* @Override
    public String exportExcel() {
        try {
            exportFileName = new String("内部通讯录列表.xls".getBytes("GBK"),
                    "ISO8859-1");// 将导出Excel的文件名进行ISO8859-1编码，否则中文文件名会乱码
        } catch (UnsupportedEncodingException e) {
        }
        return "exportExcel";
    }
    
    public InputStream getExportExcelFile() {
            @SuppressWarnings("unchecked")
            Map<Object, Object> paramMap = (Map<Object, Object>) request.getParameterMap();
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            List<Addressbooks> objectList = addressbooksMag.addressbooksCList(filterMap,bodycode);
            // 获取需要生成Excel的数据
            if (objectList != null) {
                int i = 1;
                for (Addressbooks o : objectList) {
                    o.setNo(String.valueOf(i++)); // 订单编号
                }
            }
            String[] header = { "编号", "姓名","部门","手机", "固定电话"};// 列头
            String[] property = { "no", "userName", "deptName","mobilephone","telphone"};// --订单内容
//            response.reset();
//            response.setContentType("APPLICATION/vnd.ms-excel");
            // 注意，如果去掉下面一行代码中的attachment; 那么也会使IE自动打开文件。
//            response.setHeader("Content-Disposition", "attachment;filename=" + new String(("通讯录列表.xls").getBytes("GBK"), "ISO8859-1"));
            return ExportExcelUtil.generateExcel(objectList, header, property);
        
    }*/
    
    /**
     * 导出个人通讯录
     * @return
     */
    public String exportExcelP() {
        try {
            exportFileName = new String("个人通讯录列表.xls".getBytes("GBK"),
                    "ISO8859-1");// 将导出Excel的文件名进行ISO8859-1编码，否则中文文件名会乱码
        } catch (UnsupportedEncodingException e) {
        }
        return "exportExcelP";
    }
    
    public InputStream getExportExcelFileP() {
            @SuppressWarnings("unchecked")
            Map<Object, Object> paramMap = (Map<Object, Object>) request.getParameterMap();
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            filterMap.put("creater", getLoginUserCode());//筛选自己个人通讯录的数据
            List<Addressbooks> objectList = addressbooksMag.listObjects(filterMap);
            // 获取需要生成Excel的数据
            if (objectList != null) {
                int i = 1;
                for (Addressbooks o : objectList) {
                    o.setNo(String.valueOf(i++)); // 订单编号
                }
            }
            String[] header = { "编号", "姓名","单位","手机", "固定电话"};// 列头
            String[] property = { "no", "userName", "unitName","mobilephone","telphone"};// --订单内容
            return ExportExcelUtil.generateExcel(objectList, header, property);
        
    }
    /**
     * 获取部门人员数据
     * 
     * @return
     */
    public void putAllUserTree() {
        JSONArray ja =oaPowerrolergroupManager.putUnitsUsersTree();
        request.setAttribute("unit", ja.toString());
    }
    
    /**
     * 获取部门数据
     * 
     * @return
     */
    public void putUnitTree() {
        JSONArray ja =oaPowerrolergroupManager.putUnitsTree();
        request.setAttribute("unit", ja.toString());
    }
    /**
     * 获取部门人员数据 树形结构
     * 
     * @return
     */
    public void putInnermsgTree() {
        List<FUnitinfo> units = CodeRepositoryUtil.getAllUnits("T");
        List<Map<String, String>> unit = new ArrayList<Map<String, String>>();
//        List<Addressbooks> users=addressbooksMag.addressbooksCList();
        for (FUnitinfo unitinfo : units) {
            Map<String, String> m = new HashMap<String, String>();
            m.put("id", unitinfo.getUnitcode());
            m.put("pId",
                    "0".equals(unitinfo.getParentunit()) ? null : unitinfo
                            .getParentunit());
            m.put("name", unitinfo.getUnitname());
            // 一级目录下菜单展开
            m.put("open", "0".equals(unitinfo.getParentunit()) ? "true"
                    : "false");
            m.put("p", "O");
            m.put("choosetype", "unit");
//            if(unitinfo.getParentunit().equalsIgnoreCase("1")){
                m.put("icon", "../scripts/inputZtree/img/diy/unit.png");               
//            }
            unit.add(m);
        }
//        for (Addressbooks userinfo : users) {
//            Map<String, String> m = new HashMap<String, String>();
//            m.put("id", userinfo.getAddrbookid());
//            if (!StringUtils.hasText(userinfo.getUnitcode())) {
//                continue;
//            }
//            m.put("pId", userinfo.getUnitcode());
//            m.put("name", userinfo.getUserName());
//            m.put("p", "C");
//            m.put("choosetype", "user");
//            m.put("icon", "../scripts/inputZtree/img/diy/person.png");
//            unit.add(m);
//        }

        JSONArray ja = new JSONArray();
        ja.addAll(unit);
        ActionContext.getContext().put("unit", ja.toString());
        totalRows = unit.size();
    }
    /*
     * 当前登录人员usercode
     */
    private String getLoginUserCode() {
        return ((FUserinfo) this.getLoginUser()).getUsercode();
    }

    /*
     * 取共享人员
     */
    private String getShareUsercodes(String addrbookid) {
        List<String> usercodes = addressbooksMag
                .listUsercodesByAddressbookId(addrbookid);

        return StringUtils.collectionToDelimitedString(usercodes, ",");
    }

   
   
    
    
    
    public String getSelectedUserCode() {
        return selectedUserCode;
    }

    public void setSelectedUserCode(String selectedUserCode) {
        this.selectedUserCode = selectedUserCode;
    }

    public static Log getLog() {
        return log;
    }

    public OaPowerrolergroupManager getOaPowerrolergroupManager() {
        return oaPowerrolergroupManager;
    }

    public void setOaPowerrolergroupManager(OaPowerrolergroupManager oaPowerrolergroupManager) {
        this.oaPowerrolergroupManager = oaPowerrolergroupManager;
    }

    public List<FUnitinfo> getUnits() {
        return units;
    }

    public void setUnits(List<FUnitinfo> units) {
        this.units = units;
    }

    public List<FUserinfo> getUsers() {
        return users;
    }

    public void setUsers(List<FUserinfo> users) {
        this.users = users;
    }

    public String getBodycode() {
        return bodycode;
    }

    public void setBodycode(String bodycode) {
        this.bodycode = bodycode;
    }

    public Addressbooks getAddressbooks() {
        return addressbooks;
    }

    public void setAddressbooks(Addressbooks addressbooks) {
        this.addressbooks = addressbooks;
    }

    public String getS_type() {
        return s_type;
    }

    public void setS_type(String s_type) {
        this.s_type = s_type;
    }

}
