package com.centit.oa.service.impl;

import java.util.Date;
import java.util.List;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.po.OaPowergroupDetail;
import com.centit.oa.po.OaPowerrolergroup;
import com.centit.oa.dao.OaPowergroupDetailDao;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.centit.oa.service.OaPowergroupDetailManager;
import com.centit.sys.dao.UnitInfoDao;
import com.centit.sys.po.FUserunit;

public class OaPowergroupDetailManagerImpl extends BaseEntityManagerImpl<OaPowergroupDetail>
	implements OaPowergroupDetailManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaPowergroupDetailManager.class);

	private OaPowergroupDetailDao oaPowergroupDetailDao ;
	public void setOaPowergroupDetailDao(OaPowergroupDetailDao baseDao)
	{
		this.oaPowergroupDetailDao = baseDao;
		setBaseDao(this.oaPowergroupDetailDao);
	}
	  private UnitInfoDao sysUnitDao;
	  
	
   

    /**
     * 分组详情
     */
    @Override
    public void saveObject(OaPowergroupDetail o) {
        if (StringUtils.isBlank(o.getId())) {
            o.setId("FZDT"
                    + oaPowergroupDetailDao.getNextKeyBySequence("S_OA_ROLERGROUPDETAIL_NO", 12));
        }
        super.saveObject(o);
    }
    
    public void saveDetails(OaPowerrolergroup rolergroup,String usercodes){
        if(StringUtils.isNotBlank(usercodes)){
              String[] values = usercodes.split(",");// 分割字符串
              for (int i = 0; i < values.length; i++) {
                  FUserunit userUnit=  sysUnitDao.getUserPrimaryUnit(values[i]);
                  OaPowergroupDetail info = new OaPowergroupDetail();
                  info.setNo(rolergroup.getNo());
                  info.setUsercode(values[i]);
                  info.setCreatertime(new Date());
                  if(null!=userUnit){
                    info.setDepid(userUnit.getUnitcode());
                  }
                 
                  this.saveObject(info);
              }
          } 
        
        
    }
   
    @Override
    public void deleteDetails(OaPowerrolergroup rolergroup) {
        // TODO Auto-generated method stub
        oaPowergroupDetailDao.deleteDetails(rolergroup);
    }
    
    public UnitInfoDao getSysUnitDao() {
        return sysUnitDao;
    }

    public void setSysUnitDao(UnitInfoDao sysUnitDao) {
        this.sysUnitDao = sysUnitDao;
    }

    @Override
    public List<String> listUsercodesByNo(String no) {
        // TODO Auto-generated method stub
        return   oaPowergroupDetailDao.listUsercodesByNo(no);
    }
    
    

    @Override
    public List<OaPowergroupDetail> getUserlist(OaPowerrolergroup rolergroup) {
        // TODO Auto-generated method stub
        return   oaPowergroupDetailDao.getUserlist(rolergroup);
    }

    @Override
    public List<OaPowergroupDetail> getDetailsByUsercode(String usercode) {
        
        return oaPowergroupDetailDao.getDetailsByUsercode( usercode);
    }

   

}

