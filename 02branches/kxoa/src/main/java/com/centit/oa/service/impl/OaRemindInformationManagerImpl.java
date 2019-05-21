package com.centit.oa.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import com.centit.core.dao.HQLUtils;
import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.core.utils.PageDesc;
import com.centit.oa.dao.AddressBookRelectionDao;
import com.centit.oa.dao.OaRemindInformationDao;
import com.centit.oa.po.AddressBookRelection;
import com.centit.oa.po.OaRemindInformation;
import com.centit.oa.service.OaRemindInformationManager;
import com.centit.powerruntime.util.BizCommUtil;
import com.centit.support.utils.DatetimeOpt;

public class OaRemindInformationManagerImpl extends BaseEntityManagerImpl<OaRemindInformation>
	implements OaRemindInformationManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaRemindInformationManager.class);

	private OaRemindInformationDao oaRemindInformationDao ;
	private AddressBookRelectionDao addressBookRelectionDao;
	public void setOaRemindInformationDao(OaRemindInformationDao baseDao)
	{
		this.oaRemindInformationDao = baseDao;
		setBaseDao(this.oaRemindInformationDao);
	}
    @Override
    public String getNextkey() {
        // TODO Auto-generated method stub
        return "TX"+oaRemindInformationDao.getNextKeyBySequence("s_OaRemindInformation", 14);
    }
	
    /**
     * type标记中间表提醒状态： 0：未读1：已读 2：删除
     * @param filterMap
     * @param pageDesc
     * @param usercode
     * @param type
     * @return
     */
    //type标记存放在中间表，OaRemindInformation中无这个字段
    public List<OaRemindInformation> recipientList(Map<String, Object> filterMap, PageDesc pageDesc,String usercode) {
        //creater为当前登录人的，或者AddressBookRelection 共享里面sharename为登录人
        String type="";
        if(null!=filterMap.get("bizType")){
            type = (String) filterMap.get("bizType");
        }
        String sHql=" from OaRemindInformation t where 1=1 ";
       if(StringUtils.hasText(usercode)){
           sHql+="and t.no in (" +
           		"select r.cid.addrbookid from AddressBookRelection r where r.cid.shareman= "+HQLUtils.buildHqlStringForSQL(usercode);
         if(StringUtils.hasText(type)){
             sHql+=" and r.cid.bizType="+HQLUtils.buildHqlStringForSQL(type);
         }
         sHql+=")   ";
         		//"--and t.thesign='1'   " ;//排除草稿箱未发送的提醒
       }
       //排查在首页已查看提醒 by dk 2016-2-18========================
       String F_userBizopt_log="";
       if(null!=filterMap.get("notInUserBizoptLog")){
           F_userBizopt_log=(String)filterMap.get("F_userBizopt_log");
       }
       if(F_userBizopt_log!=null&&!"".equals(F_userBizopt_log)){
           sHql+="and t.no not in (" +
                 "select f.dj_id from F_userBizopt_log f where f.createUser= "+HQLUtils.buildHqlStringForSQL(F_userBizopt_log)+" ) ";
       }
       //=======================================================
       if(null!=filterMap.get("orderbegtime")){
           sHql+=" order by begtime desc ";
       }
        List<OaRemindInformation> oaRemindInformationList=baseDao.listObjects(sHql, filterMap, pageDesc);
        return oaRemindInformationList;
    }
    @Override
    public boolean sendOaRemindInformation(String djId, String sender,
            String reser, String title, String remark) {
        // TODO Auto-generated method stub
        try{
            if (StringUtils.hasText(reser)) {   
        OaRemindInformation bean=new OaRemindInformation();
        bean.setNo(getNextkey());
        bean.setDjid(djId);
        bean.setCreater(sender);
        bean.setUsercode(reser);
        bean.setCreatetime(new Date(System.currentTimeMillis()));
        bean.setFrequency(Long.parseLong("5"));
        bean.setTitle(title);
        bean.setRemark(remark);
        bean.setBegtime(DatetimeOpt.currentUtilDate());
        bean.setEndtime(DatetimeOpt.addDays(DatetimeOpt.currentUtilDate(), 1));
        bean.setThesign("1");//已发送
        bean.setReType(BizCommUtil.getPrefix4Biz(djId, 1));
        oaRemindInformationDao.save(bean);
        
        if (StringUtils.hasText(reser)) {
            String[] values =reser.split(",");// 分割字符串
            for (int i = 0; i < values.length; i++) {
                AddressBookRelection oainfo = new AddressBookRelection();
                oainfo.setAddrbookid(bean.getNo());
                oainfo.setShareman(values[i]);
                oainfo.setBizType("0");// 类型各业务自行解释
                addressBookRelectionDao.saveObject(oainfo);
            }
          }
        }
        return true;
        }catch (Exception e) {
            // TODO: handle exception
            return false;
        }
    }
    public void setAddressBookRelectionDao(
            AddressBookRelectionDao addressBookRelectionDao) {
        this.addressBookRelectionDao = addressBookRelectionDao;
    }
    
}

