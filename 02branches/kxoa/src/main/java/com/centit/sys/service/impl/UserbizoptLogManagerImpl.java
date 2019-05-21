package com.centit.sys.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.sys.dao.UserbizoptLogDao;
import com.centit.sys.po.UserbizoptLog;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.UserbizoptLogManager;

public class UserbizoptLogManagerImpl extends BaseEntityManagerImpl<UserbizoptLog>
	implements UserbizoptLogManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(UserbizoptLogManager.class);

	private UserbizoptLogDao userbizoptLogDao ;
	public void setUserbizoptLogDao(UserbizoptLogDao baseDao)
	{
		this.userbizoptLogDao = baseDao;
		setBaseDao(this.userbizoptLogDao);
	}
	/**
	 * 查询待办是否已读
	 */
    public UserbizoptLog listObject(String djId,String usercode, Long nodeinstid) {
        List<UserbizoptLog> l=userbizoptLogDao.listObject(djId,usercode, nodeinstid);
        if(null==l||l.size()<1){
            return null;
        }else{
            return l.get(0);
        }
    }
    @Override
    public UserbizoptLog listObject(String djId, String usercode) {
        List<UserbizoptLog> l=userbizoptLogDao.listObject(djId,usercode);
        if(null==l||l.size()<1){
            return null;
        }else{
            return l.get(0);
        }
    }
    /**
     * 已读过的提醒保存入表中
     */
    @Override
    public void saveUserbizoptLog(String djId,String bizname,FUserDetail fuser,Long nodeinstid) {
        //UserbizoptLog o=new UserbizoptLog();
        UserbizoptLog u=listObject(djId, fuser.getUsercode());
        if(u==null){
            UserbizoptLog o=new UserbizoptLog();
            o.setCreateuser(fuser.getUsercode());
            o.setCreaterip(fuser.getLoginip());
            o.setDjId(djId);
            if(nodeinstid!=null){
                o.setNodeinstid(nodeinstid);
            }
            o.setBizname(bizname);
            o.setDjId(djId);
            o.setBiztype(StringUtils.substringBefore(djId, "0"));
            o.setId("JL"+userbizoptLogDao.getNextKeyBySequence("S_F_USERBIZOPT_LOG",14));
            o.setCreatetime(new Date(System.currentTimeMillis()));
            userbizoptLogDao.saveObject(o);
        }else{
            u.setCreatetime(new Date(System.currentTimeMillis()));
            userbizoptLogDao.saveObject(u);
        }
        //o.setRemark("");
    }
    public void saveUserbizoptLog(UserbizoptLog u,FUserDetail fuser) {
        UserbizoptLog s=new UserbizoptLog();
        if(u.getNodeinstid()==null){
            s=listObject(u.getDjId(), fuser.getUsercode());
        }else
        {
            s=listObject(u.getDjId(), fuser.getUsercode(),u.getNodeinstid()); 
        }
        if(s==null){
            u.setCreaterip(fuser.getLoginip());
            u.setBiztype(StringUtils.substringBefore(u.getDjId(), "0"));
            u.setId("JL"+userbizoptLogDao.getNextKeyBySequence("S_F_USERBIZOPT_LOG",14));
            u.setCreatetime(new Date(System.currentTimeMillis()));
            u.setCreateuser(fuser.getUsercode());
            userbizoptLogDao.saveObject(u);
        }else{
            s.setCreatetime(new Date(System.currentTimeMillis()));
            userbizoptLogDao.saveObject(s);
        }
    }
    @Override
    public List<Object> remaveObjectList(List<Object> l) {
       /* for(int i=0;i<l.size();i++){//移除已读
            Object o=this.listObject(l.get(i).getDjId(),l.get(i).getUserCode(), DBList.get(i).getNodeInstId());
            if(l!=null){
                DBList.remove(i);
            }
        }*/
        return l;
    }
    @Override
    public List<UserbizoptLog> listObjectNotSelf(String djId, String usercode,
            Long nodeinstid) {
        
        return userbizoptLogDao.listObjectNotSelf(djId, usercode, nodeinstid);
    }
    @Override
    public UserbizoptLog listObject(String djId, String usercode, String remark) {
        List<UserbizoptLog> l=userbizoptLogDao.listObject(djId,usercode, remark);
        if(null==l||l.size()<1){
            return null;
        }else{
            return l.get(0);
        }
    }
    
	
}

