package com.centit.oa.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.CollectionUtils;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.core.utils.DwzTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.AddressBookRelection;
//import com.centit.oa.po.OaCommonType;
import com.centit.oa.po.OaOnlineItem;
import com.centit.oa.po.OaOnlineItems;
import com.centit.oa.po.OaSurvey;
import com.centit.oa.po.OaSurveyType;
import com.centit.oa.po.OaSurveydetail;
import com.centit.oa.service.AddressBookRelectionManager;
import com.centit.oa.service.OaCommonTypeManager;
import com.centit.oa.service.OaOnlineItemManager;
import com.centit.oa.service.OaOnlineItemsManager;
import com.centit.oa.service.OaPowerrolergroupManager;
import com.centit.oa.service.OaSurveyManager;
import com.centit.oa.service.OaSurveyTypeManager;
import com.centit.oa.service.OaSurveydetailManager;
import com.centit.sys.po.FUnitinfo;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.service.CodeRepositoryUtil;

public class OaSurveyAction extends BaseEntityExtremeAction<OaSurvey>  {
    @SuppressWarnings("unused")
    private static final Log log = LogFactory.getLog(OaSurveyAction.class);
    private static final long serialVersionUID = 1L;
    private OaSurveyManager oaSurveyMag;
    private OaCommonTypeManager oaCommonTypeManager;
    private AddressBookRelectionManager addressBookRelectionManager;
    private OaSurveydetailManager oaSurveydetailManager;
    private OaPowerrolergroupManager oaPowerrolergroupManager;
//    private List<OaCommonType> oaCommonTypeList;
    public List<OaSurveyType> getOaSurveyTypeList() {
        return oaSurveyTypeList;
    }

    public void setOaSurveyTypeList(List<OaSurveyType> oaSurveyTypeList) {
        this.oaSurveyTypeList = oaSurveyTypeList;
    }

    private List<OaSurveyType> oaSurveyTypeList;
    private OaOnlineItemManager oaOnlineItemManager;
    private OaOnlineItemsManager oaOnlineItemsManager;
    private OaSurveyTypeManager oaSurveyTypeManager;
    public OaSurveyTypeManager getOaSurveyTypeManager() {
        return oaSurveyTypeManager;
    }

    public void setOaSurveyTypeManager(OaSurveyTypeManager oaSurveyTypeManager) {
        this.oaSurveyTypeManager = oaSurveyTypeManager;
    }

    private List<FUnitinfo> units;
    private String s_type;//new 新建调查 mgr 调查管理 vote 调查投票
    private String s_rEdit;//重新发布标识 T
    private String s_djid;//重新发布

    public void setOaSurveyManager(OaSurveyManager basemgr) {
        oaSurveyMag = basemgr;
        this.setBaseEntityManager(oaSurveyMag);
    }

    private List<OaOnlineItem> oaOnlineItems;
    private String userValue;// 人员编号

    public List<OaOnlineItem> getNewOaOnlineItems() {
        return this.oaOnlineItems;
    }

    public void setNewOaOnlineItems(List<OaOnlineItem> oaOnlineItems) {
        this.oaOnlineItems = oaOnlineItems;
    }

    private List<OaSurveydetail> oaSurveydetails;

    public List<OaSurveydetail> getNewOaSurveydetails() {
        return this.oaSurveydetails;
    }

    public void setNewOaSurveydetails(List<OaSurveydetail> oaSurveydetails) {
        this.oaSurveydetails = oaSurveydetails;
    }

    private List<OaSurvey> index(Map<String, Object> filterMap) {
        PageDesc pageDesc = makePageDesc();
        objList = oaSurveyMag.listObjects(filterMap, pageDesc);
        
        //根据调查djid，登录人员设置是否可以投票
        if (!CollectionUtils.isEmpty(objList)) {
            for (OaSurvey oaSurvey : objList) {
                Map<String, Object> filterMapTemp =new HashMap<String, Object>();
                filterMapTemp.put("djid", oaSurvey.getDjid());
                filterMapTemp.put("creater",getLoginUserCode());
                List<OaSurveydetail>  oaSurveydetail= oaSurveydetailManager.listObjects(filterMapTemp);
                if(CollectionUtils.isEmpty(oaSurveydetail)||oaSurveydetail.size()==0){
                    oaSurvey.setCanVote("T");
                }
                
            }
          }
        
        
        getSurTypeList();
        totalRows = pageDesc.getTotalRows();
        setbackSearchColumn(filterMap);
        this.pageDesc = pageDesc;
        return objList;

    }
    /**
     * 获取调查类型列表
     */
    public void getSurTypeList() {
        // 可选择调查类型
        Map<String, Object> filterMap= new HashMap<String, Object>();
        filterMap.put("state", "T");
        oaSurveyTypeList=oaSurveyTypeManager.listObjects(filterMap);
    }

    /**
     * 调查管理
     * 
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    public String mgrList() {
        try {
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            // creater为当前登录人员 ownerCode只查询自己数据
            filterMap.put("ownerCode", getLoginUserCode());
            index(filterMap);
            return LIST;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    
    /**
     * 列表数据
     */
    @SuppressWarnings("unchecked")
    public String list() {
        try {
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            filterMap.put("ownerCode", getLoginUserCode());
            filterMap.put("NP_thesign", true);//不查询删除的调查
            //新建调查--未开始的调查
            if("new".equals(s_type)){
                filterMap.put("thesign", "1");
                filterMap.put("owner", "true");
            }
            //调查管理--creater为自己的所有调查
            if("mgr".equals(s_type)){
//                filterMap.put("thesign", "1");
                filterMap.put("owner", "true");
            }
            //调查投票--中间表有自己的进行中的调查 
            if("vote".equals(s_type)){
                filterMap.put("thesign", "2");
            }
            index(filterMap);
            return LIST;

        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    
    /**
     * 编辑，同时获取部门列表
     */
    public String edit() {
        // 获取全部在用的部门
        setUnits(CodeRepositoryUtil.getAllUnits("T"));

        getSurTypeList();// 可选择会议室类型
        
        super.edit();
        initUsers();// 获取人员列表
        getUser();// 中间表参加调查人员
      
        return EDIT;
    }
    
    public String save() {
        if(StringUtils.isBlank(object.getDjid())){
            object.setCreater(getLoginUserCode());//发起者
            object.setCreatetime(new Date());
            object.setThesign("1");//投票状态OATheSign   1.未开始 2.进行中 3.结束 
        }
//        object.replaceOaOnlineItems(oaOnlineItems);
//        object.replaceOaSurveydetails(oaSurveydetails);
        super.save();
        saveOthers();
        return SUCCESS ;
    }
    /**
     * A保存中间表
     */
    private void saveOthers() {
        // A中间表日志共享部分：biztype 0
        // 把参会人员记录到中间表m_address_book_relection
        addressBookRelectionManager.deleteuser(object.getDjid());

        if (StringUtils.isNotBlank(userValue)) {
            String[] values = this.userValue.split(",");// 分割字符串
            for (int i = 0; i < values.length; i++) {
                AddressBookRelection info = new AddressBookRelection();
                info.setAddrbookid(object.getDjid());
                info.setShareman(values[i]);
                info.setBizType("0");
                addressBookRelectionManager.saveObject(info);
            }
        }
    }
    

    
    /**
     * 跟新状态字段thesign  1.未开始 2.进行中 3.结束  4.删除
     * @return
     */
    public String updateSign() {
        super.save();
        return SUCCESS ;
    }

    
    public String republish(){

        if(StringUtils.isBlank(object.getDjid())){
            object.setCreater(getLoginUserCode());//发起者
            object.setCreatetime(new Date());
            object.setThesign("1");//投票状态OATheSign   1.未开始 2.进行中 3.结束 
        }
        
        oaSurveyMag.saveObject(object)  ;
        OaSurvey oaSurvey=oaSurveyMag.getObjectById(s_djid);
        
        // 题目信息
        Set<OaOnlineItem> oaOnlineItems = oaSurvey.getOaOnlineItems();
        OaOnlineItem item;
        for (OaOnlineItem itemOld : oaOnlineItems) {

            item = new OaOnlineItem();

            item.setChosenum(itemOld.getChosenum());
            item.setChosetype(itemOld.getChosetype());
            item.setDjid(object.getDjid());
            item.setLimitnum(itemOld.getLimitnum());
            item.setItemnames(itemOld.getItemnames());
            item.setThesign(itemOld.getThesign());
            item.setTitle(itemOld.getTitle());
            oaOnlineItemManager.saveObject(item);
            // 选项信息
            Set<OaOnlineItems> oaOnlineItemss = itemOld.getOaOnlineItemss();
            OaOnlineItems items;
            for (OaOnlineItems itemsOld : oaOnlineItemss) {
                items = new OaOnlineItems();

                items.setNo(item.getNo());
                items.setTitle(itemsOld.getTitle());
                oaOnlineItemsManager.saveObject(items);
            }

        }
        
        //人员中间表
       List<AddressBookRelection>   relectionOlds=  addressBookRelectionManager.getUserlist(s_djid,"0");
       for (AddressBookRelection relectionOld : relectionOlds) {
            AddressBookRelection relection = new AddressBookRelection();
            relection.setAddrbookid(object.getDjid());
            relection.setShareman(relectionOld.getShareman());
            relection.setBizType("0");
            addressBookRelectionManager.saveObject(relection);
        }

   
        return SUCCESS;
    }
    
    /**
     * 分页
     * 
     * @return
     */
    public PageDesc makePageDesc() {
        return DwzTableUtils.makePageDesc(request);
    }

    /**
     * 当前登录人员usercode
     * 
     * @return
     */
    private String getLoginUserCode() {
        return ((FUserinfo) this.getLoginUser()).getUsercode();
    }
    

    /**
     * 编辑的时候自动去人员中间表去获取usercode
     */
    private List<AddressBookRelection> listuser;
    /**
     * 中间表人员
     */
    private void getUser() {
        // 编辑的时候自动去人员中间表去获取usercode
        if (StringUtils.isNotBlank(object.getDjid())) {
            listuser = addressBookRelectionManager.getUserlist(object.getDjid(),
                    "0");
            if (null!=listuser  && listuser.size() > 0) {
                userValue = "";
                for (int i = 0; i < listuser.size(); i++) {
                    String value = listuser.get(i).getShareman() + ",";
                    userValue += value;
                }
            }
            if (StringUtils.isNotBlank(userValue)) {
                userValue = userValue.substring(0, userValue.length() - 1);
            }
        }

    }
    /*
     * 获取人员
     */
    public void initUsers() {
        JSONArray userjson =oaPowerrolergroupManager.putUnitsUsersTree();
        request.setAttribute("userjson", userjson);
    }
    public OaCommonTypeManager getOaCommonTypeManager() {
        return oaCommonTypeManager;
    }

    public void setOaCommonTypeManager(OaCommonTypeManager oaCommonTypeManager) {
        this.oaCommonTypeManager = oaCommonTypeManager;
    }

//    public List<OaCommonType> getOaCommonTypeList() {
//        return oaCommonTypeList;
//    }
//
//    public void setOaCommonTypeList(List<OaCommonType> oaCommonTypeList) {
//        this.oaCommonTypeList = oaCommonTypeList;
//    }

    public String getS_type() {
        return s_type;
    }

    public void setS_type(String s_type) {
        this.s_type = s_type;
    }

    public List<FUnitinfo> getUnits() {
        return units;
    }

    public void setUnits(List<FUnitinfo> units) {
        this.units = units;
    }

    public AddressBookRelectionManager getAddressBookRelectionManager() {
        return addressBookRelectionManager;
    }

    public void setAddressBookRelectionManager(
            AddressBookRelectionManager addressBookRelectionManager) {
        this.addressBookRelectionManager = addressBookRelectionManager;
    }
    
    public String getUserValue() {
        return userValue;
    }

    public void setUserValue(String userValue) {
        this.userValue = userValue;
    }

    public OaSurveydetailManager getOaSurveydetailManager() {
        return oaSurveydetailManager;
    }

    public void setOaSurveydetailManager(OaSurveydetailManager oaSurveydetailManager) {
        this.oaSurveydetailManager = oaSurveydetailManager;
    }

    public String getS_rEdit() {
        return s_rEdit;
    }

    public void setS_rEdit(String s_rEdit) {
        this.s_rEdit = s_rEdit;
    }

    public String getS_djid() {
        return s_djid;
    }

    public void setS_djid(String s_djid) {
        this.s_djid = s_djid;
    }

    public OaOnlineItemManager getOaOnlineItemManager() {
        return oaOnlineItemManager;
    }

    public void setOaOnlineItemManager(OaOnlineItemManager oaOnlineItemManager) {
        this.oaOnlineItemManager = oaOnlineItemManager;
    }

    public OaOnlineItemsManager getOaOnlineItemsManager() {
        return oaOnlineItemsManager;
    }

    public void setOaOnlineItemsManager(OaOnlineItemsManager oaOnlineItemsManager) {
        this.oaOnlineItemsManager = oaOnlineItemsManager;
    }

    public OaPowerrolergroupManager getOaPowerrolergroupManager() {
        return oaPowerrolergroupManager;
    }

    public void setOaPowerrolergroupManager(OaPowerrolergroupManager oaPowerrolergroupManager) {
        this.oaPowerrolergroupManager = oaPowerrolergroupManager;
    }

    
    // private List<OaSurvey> oaSurveys;
    // public List<OaSurvey> getOaSurveys() {
    // return oaSurveys;
    // }
    // public void setOaSurveys(List<OaSurvey> oaSurveys) {
    // this.oaSurveys = oaSurveys;
    // }
}
