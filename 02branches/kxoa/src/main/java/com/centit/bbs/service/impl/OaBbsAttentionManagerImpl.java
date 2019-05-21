package com.centit.bbs.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.bbs.po.OaBbsAttention;
import com.centit.bbs.po.OaBbsAttentionId;
import com.centit.bbs.dao.OaBbsAttentionDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.bbs.service.OaBbsAttentionManager;

public class OaBbsAttentionManagerImpl extends BaseEntityManagerImpl<OaBbsAttention>
	implements OaBbsAttentionManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaBbsAttentionManager.class);

	private OaBbsAttentionDao oaBbsAttentionDao ;
	public void setOaBbsAttentionDao(OaBbsAttentionDao baseDao)
	{
		this.oaBbsAttentionDao = baseDao;
		setBaseDao(this.oaBbsAttentionDao);
	}
   
    public List<OaBbsAttention> getLaytypeNum(OaBbsAttention oaBbsAttention) {
        List<OaBbsAttention> oaBbsAttentionList=new ArrayList<OaBbsAttention>();
        String sql="select count(*),t.cid.laytype from OaBbsAttention t  where t.cid.usercode=? and t.cid.themeno=? group by t.cid.laytype,t.cid.themeno";
 
        List<?>  list=oaBbsAttentionDao.findObjectsByHql(sql,  new Object[] { oaBbsAttention.getCid().getUsercode(), oaBbsAttention.getCid().getThemeno()});
        if (list != null && list.size() > 0) {
            
            for (int i = 0; i < list.size(); i++) {
                Object[] objs = (Object[]) list.get(i);
                OaBbsAttention attention=new OaBbsAttention(new OaBbsAttentionId(null,null,objs[1].toString()));
                attention.setAttentionNum((Long) objs[0]);  //统计数据
//                attention.getCid().setLaytype(objs[1].toString());//attention 类型
                oaBbsAttentionList.add(attention);
            }
          }
      
        return oaBbsAttentionList;
    }
	
}

