package com.centit.oa.service.impl;

import java.util.List;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.po.OaMeetingmaterialinfos;
import com.centit.oa.dao.OaMeetingmaterialinfosDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.oa.service.OaMeetingmaterialinfosManager;

public class OaMeetingmaterialinfosManagerImpl extends BaseEntityManagerImpl<OaMeetingmaterialinfos>
	implements OaMeetingmaterialinfosManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaMeetingmaterialinfosManager.class);

	private OaMeetingmaterialinfosDao oaMeetingmaterialinfosDao ;
	public void setOaMeetingmaterialinfosDao(OaMeetingmaterialinfosDao baseDao)
	{
		this.oaMeetingmaterialinfosDao = baseDao;
		setBaseDao(this.oaMeetingmaterialinfosDao);
	}
    @Override
    public List<OaMeetingmaterialinfos> findStuffIdByCode(String meetcode,
            String djid) {
        return oaMeetingmaterialinfosDao.findStuffIdByCode(meetcode, djid);
    }
    @Override
    public void deleteBydjId(String djId) {
        oaMeetingmaterialinfosDao.deleteBydjId(djId);
    }
    @Override
    public void deleteByInitalStuffId(String initalStuffId) {
        List<OaMeetingmaterialinfos> list = oaMeetingmaterialinfosDao.listObjects("from OaMeetingmaterialinfos where initalStuffId = ?", initalStuffId);
        if(list!=null && !list.isEmpty()){
            for(OaMeetingmaterialinfos o : list){
                oaMeetingmaterialinfosDao.delete(o);
            }
        }
    }
    
    @Override
    public List<OaMeetingmaterialinfos> findListByDjId(String djId) {
        return oaMeetingmaterialinfosDao.listObjects("from OaMeetingmaterialinfos where  cid.djId=?", djId);
    }
    @Override
    public OaMeetingmaterialinfos findObjectBy(String djId, String usercode,
            String initalStuffId) {
        List<OaMeetingmaterialinfos> list = oaMeetingmaterialinfosDao.listObjects("from OaMeetingmaterialinfos where cid.djId=? and cid.meetingAttendee=? and initalStuffId = ?", new Object[]{djId,usercode,initalStuffId});
        if(list!=null && !list.isEmpty()){
            return list.get(0);
        }
        return null;
    }
	
}

