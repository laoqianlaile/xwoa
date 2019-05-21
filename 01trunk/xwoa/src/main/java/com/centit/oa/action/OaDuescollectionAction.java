package com.centit.oa.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.extremecomponents.table.limit.Limit;

import com.centit.core.utils.ExtremeTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.Innermsg;
import com.centit.oa.po.OaDuescollection;
import com.centit.oa.po.OaDuescollectioninfos;
import com.centit.oa.po.OaInformation;
import com.centit.oa.po.OaUserinfo;
import com.centit.oa.service.InnermsgManager;
import com.centit.oa.service.OaDuescollectionManager;
import com.centit.oa.service.OaDuescollectioninfosManager;
import com.centit.oa.service.OaInformationManager;
import com.centit.oa.service.OaPowerrolergroupManager;
import com.centit.oa.service.OaRemindInformationManager;
import com.centit.oa.service.OaSmssendManager;
import com.centit.oa.service.OaUserinfoManager;
import com.centit.powerruntime.util.BizCommUtil;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.po.FUnitinfo;
import com.centit.sys.po.FUserunit;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.service.SysUnitManager;
import com.centit.sys.service.SysUserManager;
import com.centit.sys.util.DateUtil;
	

public class OaDuescollectionAction  extends OACommonBizAction<OaDuescollection>  {
	private static final Log log = LogFactory.getLog(OaDuescollectionAction.class);
	private static final long serialVersionUID = 1L;
	private OaDuescollectionManager oaDuescollectionMag;
	private OaPowerrolergroupManager oaPowerrolergroupManager;
	private SysUnitManager sysUnitManager;
	private SysUserManager sysUserMgr;
	private OaUserinfoManager oaUserinfoManager;
	private OaInformationManager oaInformationManager;
	private OaRemindInformationManager oaRemindInformationManager;
	private InnermsgManager innermsgManager;
	private OaSmssendManager oaSmssendManager;
	
	
	public SysUserManager getSysUserMgr() {
        return sysUserMgr;
    }
    public void setSysUserMgr(SysUserManager sysUserMgr) {
        this.sysUserMgr = sysUserMgr;
    }
    private OaDuescollectioninfosManager oaDuescollectioninfosManager;
	
	public OaDuescollectioninfosManager getOaDuescollectioninfosManager() {
        return oaDuescollectioninfosManager;
    }
    public void setOaDuescollectioninfosManager(
            OaDuescollectioninfosManager oaDuescollectioninfosManager) {
        this.oaDuescollectioninfosManager = oaDuescollectioninfosManager;
    }
    public void setSysUnitManager(SysUnitManager sysUnitManager) {
        this.sysUnitManager = sysUnitManager;
    }
    private String s_unitcode;// 部门编号
	public OaPowerrolergroupManager getOaPowerrolergroupManager() {
        return oaPowerrolergroupManager;
    }
    public void setOaPowerrolergroupManager(
            OaPowerrolergroupManager oaPowerrolergroupManager) {
        this.oaPowerrolergroupManager = oaPowerrolergroupManager;
    }
    public void setOaDuescollectionManager(OaDuescollectionManager basemgr)
	{
		oaDuescollectionMag = basemgr;
		this.setBaseEntityManager(oaDuescollectionMag);
	}
    /**
     * 列表数据
     */
    public String list() {
        try {
            FUserDetail user = ((FUserDetail) getLoginUser());// 获取当前用户的信息
            
            @SuppressWarnings("unchecked")
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            if(!"99999999".equals(user.getUsercode())){//超级管理员放开权限
            filterMap.put("creater",user.getUsercode());//获取创建人员本人创建的记录
            }
            Limit limit = ExtremeTableUtils.getLimit(request);
            PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
            
          //默认查询当前月份第一天到现在的记录
            if(StringUtils.isBlank((String)filterMap.get("begTime"))&&StringUtils.isBlank((String)filterMap.get("begTime"))){
                filterMap.put("begTime",DateUtil.getCurrentMonthOfDay());
                filterMap.put("endTime", DatetimeOpt.convertDateToString(DatetimeOpt.addDays(new Date(),30),"yyyy-MM-dd"));
            }
            objList = oaDuescollectionMag.listObjects(filterMap, pageDesc);
            if(objList!=null){
                for(int i=0;i<objList.size();i++){
                    
                    java.util.Date nowdate=new java.util.Date();
                    long day=0;
                    if(objList.get(i).getEndtime()!=null){
                        day= (objList.get(i).getEndtime().getTime()-nowdate.getTime())/(24*60*60*1000);
                        if(day<0){
                            objList.get(i).setFinflag("已截止");
                            }else if(day<5&&day>=0){
                            objList.get(i).setFinflag("即将截止");
                            }else{
                             objList.get(i).setFinflag("暂无提醒");
                            }
                    }else{
                        objList.get(i).setFinflag("暂无提醒");
                    }                   
                }
            }
            totalRows = pageDesc.getTotalRows();
            setbackSearchColumn(filterMap);
            
            return LIST;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
	
    /**
     * 2017-02-16 hx update
     * 保存收缴记录
     * 1、记录收缴基本信息
     * 2、记录收缴人员收缴初始信息
     * 3、收缴状态：初始状态，收缴状态为收缴中F，在编辑保存的时候，需要动态判断人员收缴是否全部完成；
     * 如果完成了，将状态设置成已完成。手动模式是确保数据的及时性，防止有时差，让用户产生误解。
     * 收缴状态的改变需要配合定时器或者存储过程来实现，系统默认采用定时器。
     * end
     */
    public String save() {
        try {
            FUserDetail user = ((FUserDetail) getLoginUser());// 获取当前用户的信息
            
            if (StringUtils.isBlank(object.getDjId())) {// 录入人员,录入时间
                object.setCreater(user.getUsercode());
                object.setCreatetime(DatetimeOpt.currentUtilDate());
                object.setDjId(oaDuescollectionMag.getNextKey());
                
              //只在第一次保存的时候 发送消息事件：发送通知 发送短信 发送提醒 发送邮件 
                //发送通知
                if("T".equals(object.getSendinfo())){
                    OaInformation oaInformation=new OaInformation();
                    oaInformation.setNo(oaInformationManager.getNextkey(""));
                    oaInformation.setInfoType("info");//通知
                    oaInformation.setTitle(object.getTransaffairname());
                    oaInformation.setAuthor(user.getUsername());
                    oaInformation.setReleaseDate(new Date());
                    oaInformation.setBodyContent(object.getRemark());
                    oaInformation.setMajorDegree("H");
                    oaInformation.setCreater(user.getUsercode());
                    oaInformation.setCreatertime(new Date(System.currentTimeMillis()));
                    oaInformation.setIsAllowComment("Y");
                    oaInformation.setDepno(user.getPrimaryUnit());
                    oaInformationManager.saveObject(oaInformation);
                }
                //发送提醒
                if("T".equals(object.getSendnotice())){
                    oaRemindInformationManager.sendOaRemindInformation(object.getDjId(), user.getUsercode(),object.getSendpersens(), object.getTransaffairname(), object.getRemark(),object.getEndtime());
                }
                //发送邮件
                if("T".equals(object.getSendemail())){
                    if (StringUtils.isNotBlank(object.getSendpersens())) {
                        Innermsg innermsg=new Innermsg();
                        innermsg.setMsgcode(innermsgManager.getNextKey());
                        innermsg.setMsgtype("P");//类型个人邮件
                        innermsg.setSender(user.getUsercode());
                        innermsg.setSenddate(new Date(System.currentTimeMillis()));
                        innermsg.setReceiveUserCode(object.getSendpersens());
                        innermsg.setTo(object.getSendpersens());
                        innermsg.setReceivename(object.getSendpersennames());
                        innermsg.setMsgcontent(object.getRemark());
                        innermsg.setMsgtitle(object.getTransaffairname());
                        innermsg.setMailtype(Innermsg.MAIL_TYPE_O);//发件箱标记
                        innermsgManager.saveMsg(innermsg);
                    } 
                }
                //发送短信
                if("T".equals(object.getSendshortnew())&&StringUtils.isNotBlank(object.getSendpersens())){
                   for(String receptcode:object.getSendpersens().split(",")){                    
                    String remindContent = "{username}，您好，您有一条关于{title}的提醒";
                    String s = CodeRepositoryUtil.getValue("sendMSgMod", "remind");
                    if(StringUtils.isNotBlank(s) && !"remind".equals(s)){
                        remindContent = s;
                    }
                    
                    remindContent = StringUtils.replace(remindContent, "{username}", CodeRepositoryUtil.getValue("usercode", user.getUsercode()));
                    remindContent = StringUtils.replace(remindContent, "{title}", object.getTransaffairname());
                    oaSmssendManager.saveMsgs(receptcode,user.getUsercode(),remindContent,"TX");
                    oaSmssendManager.executeSendMsg();
                   }
                }
            }
            
          //同步收缴人员明细
            oaDuescollectioninfosManager.updateInfos(object); 
            
            Map<String, Object> filterMap=new HashMap<String, Object>();
            filterMap.put("djId", object.getDjId());
            filterMap.put("isfinish", "F");//未收缴的
            
            //查询子表，判断此次收缴是否收缴结束
            List<OaDuescollectioninfos> oaDuescollectioninfos= oaDuescollectioninfosManager.listObjects(filterMap);
           if(oaDuescollectioninfos!=null&&oaDuescollectioninfos.size()>0){
               object.setIsfinish("F");//收缴中
           }else{
               object.setIsfinish("T");//已完成
           }
            super.save();
        
            
            return this.list();
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }   
    /**
     * 编辑
     */
    public String edit() {
        List<Map<String, String>> userjson = oaPowerrolergroupManager.putUnitsUsersTree();
        request.setAttribute("userjson",JSONArray.fromObject(userjson));
        super.edit();
        return EDIT;
        
    }   
    
    /**
     * 党员信息总入口
     * @return
     */
    public String editDY(){
       // JSONArray   units= getDyUnitList();      
        JSONArray   units= oaPowerrolergroupManager.getSubUnits("1");
        request.setAttribute("units", units);
        return "editDY";
    }
    
    /**
     * 党员信息 右侧页面入口
     * @return
     */
    public String mangeDyDetail(){
      //过滤条件
        Map<Object, Object> paramMap = request.getParameterMap();
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        Map<String, Object> filterMapNo = convertSearchColumn(paramMap);
        List<OaUserinfo>  oaUserinfos =new ArrayList<OaUserinfo>();
        List<OaUserinfo>  oaUserinfosNo =new ArrayList<OaUserinfo>();
        if(StringUtils.isEmpty(s_unitcode)){
            s_unitcode="1";
        }
            filterMap.put("unitcode",s_unitcode);
            filterMap.put("party", "T");
            filterMapNo.put("NP_party","T");
            filterMapNo.put("unitcode",s_unitcode);
            oaUserinfos = oaUserinfoManager.listUserInfos(filterMap);
            oaUserinfosNo = oaUserinfoManager.listUserInfos(filterMapNo);
       
        setbackSearchColumn(filterMap);
        request.setAttribute("oaUserinfos", oaUserinfos);
        request.setAttribute("oaUserinfosNo", oaUserinfosNo);
        request.setAttribute("s_unitcode", s_unitcode);
        return "mangeDyDetail";
    }
    /**
     * 添加党员
     * @return
     */
    public String doUserIn(){
        String usercode=request.getParameter("usercode");
        OaUserinfo oaUserinfo=oaUserinfoManager.getObjectById(usercode);
        oaUserinfo.setParty("T");
        oaUserinfoManager.saveObject(oaUserinfo);
        return this.mangeDyDetail();
    }
    /**
     * 批量添加党员
     * @return
     */
    public String doUsersIn(){
        String usercodes=request.getParameter("usercodes");
        if(StringUtils.isNotBlank(usercodes)){
           for(String usercode:usercodes.split(",")){ 
        OaUserinfo oaUserinfo=oaUserinfoManager.getObjectById(usercode);
        oaUserinfo.setParty("T");
        oaUserinfoManager.saveObject(oaUserinfo);
           }
        }
        return this.mangeDyDetail();
    }
    /**
     * 取消党员
     * @return
     */
    public String doUserOut(){
        String usercode=request.getParameter("usercode");
        OaUserinfo oaUserinfo=oaUserinfoManager.getObjectById(usercode);
        oaUserinfo.setParty("F");
        oaUserinfoManager.saveObject(oaUserinfo);
        return this.mangeDyDetail();
    }
    
    /**
     * 批量取消党员
     * @return
     */
    public String doUsersOut(){
        String usercodes=request.getParameter("usercodes");
        if(StringUtils.isNotBlank(usercodes)){
           for(String usercode:usercodes.split(",")){ 
        OaUserinfo oaUserinfo=oaUserinfoManager.getObjectById(usercode);
        oaUserinfo.setParty("F");
        oaUserinfoManager.saveObject(oaUserinfo);
           }
        }
        return this.mangeDyDetail();
    }
    
    /**
     * 导出党员信息
     * @throws IOException 
     */
    public void exportExcelUser() throws IOException{
        //过滤条件
        Map<Object, Object> paramMap = request.getParameterMap();
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        List<OaUserinfo> objectList =new ArrayList<OaUserinfo>();
        String unitcode=request.getParameter("unitcode");
        filterMap.put("party", "T");
        filterMap.put("unitcode",unitcode);
        objectList = oaUserinfoManager.listUserInfos(filterMap);       
        
        List<Object[]> chosedList = new ArrayList<Object[]>() ;
     // 获取需要生成Excel的数据
        if (objectList != null) {
            int i=1;
            for (OaUserinfo o : objectList) {                
                Object[] temp= new Object[6];
                temp[0]=i++;
                temp[1]=CodeRepositoryUtil.getValue("usercode",o.getUsercode());
                temp[2]=CodeRepositoryUtil.getValue("unitcode",o.getfUserunit().getUnitcode());
                temp[3]=CodeRepositoryUtil.getValue("sex",o.getSex());
                temp[4]=o.getfUserinfo().getUserdesc();
                temp[5]=o.getMobilephone();
                chosedList.add(temp);
               
            }
        }
        String[] header = {"序号","人员姓名","所属部门","性别","职务","联系电话"};// 列头
       BizCommUtil.doPoi2Excel(CodeRepositoryUtil.getValue("unitcode",unitcode)+"党员人员表", header, chosedList);
    }
    /**
     * 获取机构列表
     * @return
     */
    private JSONArray getDyUnitList(){
        List<Map<String, String>> unit = new ArrayList<Map<String, String>>();
        //根据业务编号，获取相关机构
        Map<String, Object> filterMap=new HashMap<String, Object>();
        filterMap.put("party","T");
        List<OaUserinfo> ous=oaUserinfoManager.listObjects(filterMap);
        if(ous!=null && ous.size()>0){
        
        String unitcode="1";
        List<FUnitinfo> units =  sysUnitManager.getAllSubUnits(unitcode);
        if (null != units) {
            // 包含下級部門
            for (FUnitinfo unitinfo : units) {
                for (OaUserinfo o: ous) {
                    FUserunit fUserinfo=sysUserMgr.getUserPrimaryUnit(o.getUsercode());    
           if("1".equals(unitinfo.getUnitcode())|| fUserinfo.getUnitcode().equals(unitinfo.getUnitcode())){
                Map<String, String> m = new HashMap<String, String>();
                m.put("id", unitinfo.getUnitcode());
                m.put("pId", unitcode.equals(unitinfo.getUnitcode()) ? unitcode
                        : unitinfo.getParentunit());
                m.put("name", unitinfo.getUnitname());
                m.put("open",  unitcode.equals(unitinfo.getUnitcode())?"true":"false");
                m.put("icon", "");
                m.put("unitorder", String.valueOf(unitinfo.getUnitorder()));//部门排序
                m.put("type", "false");// 部门不可被选中
                unit.add(m);
                break;
                    }
                }
            }
        }
        }else{
            FUnitinfo fUnitinfo=sysUnitManager.getObjectById("1");
            Map<String, String> m = new HashMap<String, String>();
            m.put("id", "1");
            m.put("pId","0");
            m.put("name", fUnitinfo.getUnitname());
            m.put("open","true");
            m.put("icon", "");
            m.put("unitorder", String.valueOf(fUnitinfo.getUnitorder()));//部门排序
            m.put("type", "false");// 部门不可被选中
            unit.add(m);
        }
        JSONArray ja = new JSONArray();
        ja.addAll(unit);
        return ja;
    }
    
    /**
     * 删除信息
     */
    @Override
    public String delete(){        
        oaDuescollectionMag.deleteDuescollectiont(object);        
        return this.list();        
    }
    /**
     * 批量删除功能
     * @return
     */
    public String deleteIds() {
        //批量ids
        String ids = request.getParameter("ids");
        if (StringUtils.isNotEmpty(ids)) {
        for (String djid : ids.split(",")) {
            //存放作修改字段
            object.setDjId(djid);
            oaDuescollectionMag.deleteObject(object);    
         }
        }
        return this.list();
    }
    /*
     * 获取人员
     */
    public void initUsers() {
        // JSONArray userjson
        // =oaPowerrolergroupManager.putAllUserTree(getLoginUserCode());
        JSONArray userjson = oaPowerrolergroupManager.putUnitsUsersTree();
        request.setAttribute("userjson", userjson);

        request.setAttribute("stationUsers",
                oaPowerrolergroupManager.getStationtUsersTree(null));
        request.setAttribute("unitLeaderuserjson",
                oaPowerrolergroupManager.getUnitLeaderUsersTree());
    }
    
    /**
     * 查看党费收缴详情
     */
    public String view() {
        super.view();

        List<OaDuescollectioninfos> duesUserCode = new ArrayList<OaDuescollectioninfos>();
        List<OaDuescollectioninfos> duesUserName = new ArrayList<OaDuescollectioninfos>();
        String usercodes = object.getSendpersens();
        String usernames = object.getSendpersennames();
        if (StringUtils.isNotEmpty(usercodes)) {
            for (String usecode : usercodes.split(",")) {
      
                OaDuescollectioninfos bean = new OaDuescollectioninfos();
                if(usecode!=""||usecode!=null){
                bean.setUsercode(usecode);
                }
                duesUserCode.add(bean);

            }
        }

        if (StringUtils.isNotEmpty(usernames)) {
            for (String username : usernames.split(",")) {
      
                OaDuescollectioninfos namebean = new OaDuescollectioninfos();
                if(username!=""||username!=null){
                    namebean.setUsercode(username);;
                }
                duesUserName.add(namebean);

            }
        }
        request.setAttribute("duesUserCodeList", duesUserCode);
        request.setAttribute("duesUserNameList", duesUserName);
        manage();
        return VIEW;
    }
    /**
     * 展示已收缴和未收缴党费详细
     * T.已收缴显示
     * F.未收缴显示
     * 
     */
    public String mangeDetail(){
        //过滤条件
        Map<Object, Object> paramMap = request.getParameterMap();
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        Map<String, Object> filterMapNoFin = convertSearchColumn(paramMap);
        List<OaDuescollectioninfos>  oaDuesFinished =new ArrayList<OaDuescollectioninfos>();
        List<OaDuescollectioninfos>  oaDuesNoFinish =new ArrayList<OaDuescollectioninfos>(); 
        filterMap.remove("isvalid");
        filterMapNoFin.remove("isvalid");
        //当前用户信息
        FUserDetail loginUser = ((FUserDetail) getLoginUser());
        if(null==loginUser){
            return  "login";
        }
        
        if(StringUtils.isEmpty(s_unitcode)){
            s_unitcode="1";
        }
        filterMap.remove("s_unitcode");
        String djId = request.getParameter("s_djId");
        filterMap.put("djId", djId);
        if("1".equals(s_unitcode)){//所有部门下的缴费情况查询
            //已收缴查询
            filterMap.put("isfinish", "T");
            
            filterMap.remove("unitcode");
            filterMapNoFin.put("isfinish", "F");
            filterMapNoFin.remove("unitcode");
            oaDuesFinished = oaDuescollectioninfosManager.listObjects(filterMap);
            oaDuesNoFinish = oaDuescollectioninfosManager.listObjects(filterMapNoFin);
        }else{
            filterMap.put("isfinish", "T");
            filterMap.put("unitcode", s_unitcode);
            filterMapNoFin.put("isfinish", "F");
            filterMapNoFin.put("unitcode", s_unitcode);
            oaDuesFinished = oaDuescollectioninfosManager.listObjects(filterMap);
            oaDuesNoFinish = oaDuescollectioninfosManager.listObjects(filterMapNoFin);
        }
      
        setbackSearchColumn(filterMap);
        request.setAttribute("oaDuesFinished", oaDuesFinished);
        request.setAttribute("oaDuesNoFinish", oaDuesNoFinish);
        request.setAttribute("djId", djId);
        request.setAttribute("s_unitcode", s_unitcode);
        object=oaDuescollectionMag.getObjectById(djId);
        return "dcmangeDetail";
        
    } 
    
    /**
    * 党费收缴机构树
    * 1.左侧机构数 当前用户所在机构(包含子机构)当不包含子机构时不显示
    */
   public String manage(){
       //当前用户信息
       FUserDetail loginUser = ((FUserDetail) getLoginUser());
       if(null==loginUser){
           return  "login";
       }      
       
       JSONArray   units= getUnitList(object.getDjId());      
       request.setAttribute("units", units);
       request.setAttribute("djId", object.getDjId());
       return "dcmangeList";
       
   }
   
   private JSONArray getUnitList(String djId){
       List<Map<String, String>> unit = new ArrayList<Map<String, String>>();
       //根据业务编号，获取相关机构
       List<Object[]> unitsList=oaDuescollectioninfosManager.listUnitsDistinct(djId);
       if(unitsList!=null && unitsList.size()>0){
       
       String unitcode="0";
       List<FUnitinfo> units =  sysUnitManager.getAllSubUnits(unitcode);
       if (null != units) {
           // 包含下級部門
           for (FUnitinfo unitinfo : units) {
               
               for (Object[] o: unitsList) {
                   if("1".equals(unitinfo.getUnitcode())|| o[1].toString().equals(unitinfo.getUnitcode())){
               Map<String, String> m = new HashMap<String, String>();
               m.put("id", unitinfo.getUnitcode());
               m.put("pId", unitcode.equals(unitinfo.getUnitcode()) ? unitcode
                       : unitinfo.getParentunit());
               m.put("name", unitinfo.getUnitname());
               m.put("open",  unitcode.equals(unitinfo.getUnitcode())?"true":"false");
               m.put("icon", "");
               m.put("unitorder", String.valueOf(unitinfo.getUnitorder()));//部门排序
               m.put("type", "false");// 部门不可被选中
               unit.add(m);
               break;
                   }
               }
           }
       }
       }
       JSONArray ja = new JSONArray();
       ja.addAll(unit);
       return ja;
   }
   
   
   
   public String getS_unitcode() {
       return s_unitcode;
   }


   public void setOaUserinfoManager(OaUserinfoManager oaUserinfoManager) {
    this.oaUserinfoManager = oaUserinfoManager;
}
public void setOaInformationManager(OaInformationManager oaInformationManager) {
    this.oaInformationManager = oaInformationManager;
}
public void setOaRemindInformationManager(
        OaRemindInformationManager oaRemindInformationManager) {
    this.oaRemindInformationManager = oaRemindInformationManager;
}
public void setInnermsgManager(InnermsgManager innermsgManager) {
    this.innermsgManager = innermsgManager;
}
public void setOaSmssendManager(OaSmssendManager oaSmssendManager) {
    this.oaSmssendManager = oaSmssendManager;
}
public void setS_unitcode(String s_unitcode) {
       this.s_unitcode = s_unitcode;
   }
   
   public void exportExcelDues() throws IOException {
       //this.list();
     //过滤条件
       Map<Object, Object> paramMap = request.getParameterMap();
       Map<String, Object> filterMap = convertSearchColumn(paramMap);
       List<OaDuescollectioninfos> objectList =new ArrayList<OaDuescollectioninfos>();
       String djId=request.getParameter("djId");
       String unitcode=request.getParameter("unitcode");
       filterMap.put("isfinish", "T");
       filterMap.put("unitcode", unitcode);
       filterMap.put("djId", djId);

       objectList = oaDuescollectioninfosManager.listObjects(filterMap);         
       
       List<Object[]> chosedList = new ArrayList<Object[]>() ;
    // 获取需要生成Excel的数据
       if (objectList != null) {
           int i=1;
           for (OaDuescollectioninfos o : objectList) {                
               Object[] temp= new Object[5];
               temp[0]=i++;
               temp[1]=CodeRepositoryUtil.getValue("usercode",o.getUsercode());
               temp[2]=CodeRepositoryUtil.getValue("unitcode",o.getUnitcode());
               temp[3]=o.getAmount();
               temp[4]=DatetimeOpt.convertDateToString(
                       o.getCreatetime(), "yyyy年MM月dd日");
               chosedList.add(temp);
              
           }
       }
       String[] header = {"序号","人员姓名","所属部门","缴费金额","缴费时间"};// 列头
      BizCommUtil.doPoi2Excel("已收缴人员表", header, chosedList);
       
}
   //党费收缴
   public String updateFee() {
       try {
              //过滤条件
              Map<Object, Object> paramMap = request.getParameterMap();
              String usercode=request.getParameter("usercode");
              String amout=request.getParameter("amout");
              String djId=request.getParameter("djId");
              String unitcode=request.getParameter("unitcode");
              OaDuescollectioninfos bean = new OaDuescollectioninfos();
              bean.setUsercode(usercode);
              bean.setAmount(amout);
              bean.setIsfinish("T");
              bean.setDjId(djId);
              bean.setUnitcode(unitcode);
              bean.setCreatetime(DatetimeOpt.currentUtilDate());
              oaDuescollectioninfosManager.saveObject(bean);
              Map<String, Object> filterMap = convertSearchColumn(paramMap);
              Map<String, Object> filterMapNoFin = convertSearchColumn(paramMap);

              filterMap.put("isfinish", "T");
              filterMap.put("djId",djId);
              filterMap.put("unitcode", s_unitcode);
              filterMapNoFin.put("isfinish", "F");
              filterMapNoFin.put("unitcode", s_unitcode);
              filterMapNoFin.put("djId",djId);
              List<OaDuescollectioninfos> oaDuesFinished = oaDuescollectioninfosManager.listObjects(filterMap);
              List<OaDuescollectioninfos> oaDuesNoFinish = oaDuescollectioninfosManager.listObjects(filterMapNoFin);
              request.setAttribute("oaDuesFinished", oaDuesFinished);
              request.setAttribute("oaDuesNoFinish", oaDuesNoFinish);
              request.setAttribute("djId", djId);
              request.setAttribute("s_unitcode", s_unitcode);
              
              
              //巡检父表收缴状态 同步
            //查询子表，判断此次收缴是否收缴结束
              Map<String, Object> filterMap1=new HashMap<String, Object>();
              filterMap1.put("djId", object.getDjId());
              filterMap1.put("isfinish", "F");//未收缴的
              List<OaDuescollectioninfos> oaDuescollectioninfos= oaDuescollectioninfosManager.listObjects(filterMap1);
              OaDuescollection oaDuescollection=oaDuescollectionMag.getObjectById(djId);
              if(oaDuescollectioninfos!=null&&oaDuescollectioninfos.size()>0){
                  oaDuescollection.setIsfinish("F");//收缴中
             }else{
                 oaDuescollection.setIsfinish("T");//已完成
             }
              oaDuescollectionMag.saveObject(oaDuescollection);
              object=oaDuescollectionMag.getObjectById(djId);
              return "dcmangeDetail";
       } catch (Exception e) {
           e.printStackTrace();
           return ERROR;
       }
   }   
}
