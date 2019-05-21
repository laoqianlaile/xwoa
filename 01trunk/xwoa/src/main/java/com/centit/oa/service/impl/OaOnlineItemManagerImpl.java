package com.centit.oa.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.po.OaOnlineItem;
import com.centit.oa.po.OaOnlineItems;
import com.centit.oa.dao.OaOnlineItemDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.centit.oa.service.OaOnlineItemManager;

public class OaOnlineItemManagerImpl extends BaseEntityManagerImpl<OaOnlineItem>
	implements OaOnlineItemManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaOnlineItemManager.class);

	private OaOnlineItemDao oaOnlineItemDao ;
	public void setOaOnlineItemDao(OaOnlineItemDao baseDao)
	{
		this.oaOnlineItemDao = baseDao;
		setBaseDao(this.oaOnlineItemDao);
	}
	 @Override
	    public String getNextKey() {
	        // TODO Auto-generated method stub
	   
	        return "TMXX"
                    + oaOnlineItemDao.getNextKeyBySequence("S_OA_ONLINE_ITEM_NO", 12);
	    }
    
    /**
     * 保存题目信息
     * @param oaOnlineItem
     * @param oaOnlineItems 选项信息
     */
    @Override
    public void saveObject(OaOnlineItem oaOnlineItem,
            List<OaOnlineItems> oaOnlineItems) {

        //问答
       if("3".equals(oaOnlineItem.getChosetype())){
            OaOnlineItems  questionItem=new OaOnlineItems();//问答题页面没有oaOnlineItems，单独处理
            questionItem.setItemid("TMXS"
                    + oaOnlineItemDao.getNextKeyBySequence("S_OA_ONLINE_ITEMS_NO", 12));
            questionItem.setNo(oaOnlineItem.getNo());
            if(CollectionUtils.isEmpty(oaOnlineItems)) {
                oaOnlineItems=new ArrayList<OaOnlineItems>();
            }
            oaOnlineItems.add(questionItem);
        }else
            //单选多选
           {
                String itemnames="";//题目选项
                if(!CollectionUtils.isEmpty(oaOnlineItems)) {
                    for (OaOnlineItems items : oaOnlineItems) {
                        if(!StringUtils.hasText(items.getItemid())){
                            items.setItemid("TMXS"
                                    + oaOnlineItemDao.getNextKeyBySequence("S_OA_ONLINE_ITEMS_NO", 12));
                        }
                        itemnames+=items.getTitle()+";";
                    }
                    oaOnlineItem.setItemnames(itemnames);
                }  
            }
       
       oaOnlineItemDao.deleteitemsByNo(oaOnlineItem);
       if(!CollectionUtils.isEmpty(oaOnlineItems)) {
       oaOnlineItem.replaceOaOnlineItemss(oaOnlineItems);
       }
       
       this.saveObject(oaOnlineItem); 
        
        
    }
}

