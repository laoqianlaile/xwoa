package com.centit.bbs.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.service.OaLeaveMessageManager;
import com.centit.support.utils.DatetimeOpt;
import com.centit.bbs.po.OaBbsTheme;
import com.centit.bbs.dao.OaBbsThemeDao;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import com.centit.bbs.service.OaBbsAttentionManager;
import com.centit.bbs.service.OaBbsThemeManager;

public class OaBbsThemeManagerImpl extends BaseEntityManagerImpl<OaBbsTheme>
	implements OaBbsThemeManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaBbsThemeManager.class);

	private OaBbsThemeDao oaBbsThemeDao ;
	public void setOaBbsThemeDao(OaBbsThemeDao baseDao)
	{
		this.oaBbsThemeDao = baseDao;
		setBaseDao(this.oaBbsThemeDao);
	}
    
    private OaLeaveMessageManager oaLeaveMsgManager;
    public void setOaLeaveMsgManager(OaLeaveMessageManager oaLeaveMsgManager) {
        this.oaLeaveMsgManager = oaLeaveMsgManager;
    }
    
    private OaBbsAttentionManager oaBbsAttentionManager;
    public void setOaBbsAttentionManager(OaBbsAttentionManager oaBbsAttentionManager) {
        this.oaBbsAttentionManager = oaBbsAttentionManager;
    }

    /**
     * 主题序号
     */
    @Override
    @Transactional
    public void saveObject(OaBbsTheme o) {
        if (!StringUtils.isNotBlank(o.getThemeno())) {
            o.setThemeno("THEME"
                    + oaBbsThemeDao.getNextKeyBySequence("S_themeNo", 11));
        }
               
        super.saveObject(o);
    }
    
    /**
     * 将帖子和其所属的管理员保存到关联表中
     * @param o
     */
    /*public void setThemeRelations(OaBbsTheme o) {

        if (StringUtils.isNotBlank(o.getLayoutno())) {

            // 获取帖子所属的子模块
            OaBbsDiscussion discuss = oaBbsDiscussionManager.getObjectById(o.getLayoutno());
            if (null != discuss && StringUtils.isNotBlank(discuss.getLayoutcode())) {
                // 获取所属的大模块
                OaBbsDashboard dashboard = oaBbsDashboardManager.getObjectById(discuss.getLayoutcode());

                // 将大模块中设定的子模块管理员保存至关联表
                if (StringUtils.isNotBlank(dashboard.getApprovals())) {
                    String[] approvals = dashboard.getApprovals().split(",");
                    
                    Map<String, Object> filterMap = new HashMap<String, Object>();
                    filterMap.put("addrbookid", o.getThemeno());
                    List<AddressBookRelection> relations = addressBookRelectionManager.listObjects(filterMap);
                    
                    if(null != relations){
                        for(AddressBookRelection abr : relations){
                            addressBookRelectionManager.deleteObject(abr);
                        }
                    }

                    for (int i = 0; i < approvals.length; i++) {
                        // 关联表
                        AddressBookRelection relation = new AddressBookRelection();
                        relation.setAddrbookid(o.getThemeno());
                        relation.setShareman(approvals[i]);
                        // 类型是帖子
                        relation.setBizType("BT");
                        
                        addressBookRelectionManager.saveObject(relation);
                    }
                }
            }
        }
    }*/
    
    
    /**
     * 更新主题帖子回复数，回復時間
     * @param themeno
     */
    @Override
    public void updatePostsNum(String themeno) {
        if (StringUtils.isNotBlank(themeno)) {
            OaBbsTheme oaBbsTheme = this.getObjectById(themeno);
            if (null != oaBbsTheme) {
                oaBbsTheme.setPostsnum((oaBbsTheme.getPostsnum() ==null? 0:oaBbsTheme.getPostsnum()) + 1);
                oaBbsTheme.setLastmodifytime(new Date());
                oaBbsThemeDao.saveObject(oaBbsTheme);
            }
        }
        
    }
    
    /**
     * 更新主題帖子查看數 
     * @param themeno
     */
    @Override
    public void updatePostsViewNum(String themeno) {
        if (StringUtils.isNotBlank(themeno)) {
            OaBbsTheme oaBbsTheme = this.getObjectById(themeno);
            
            if (null != oaBbsTheme) {
                oaBbsTheme.setPostsviewnum((oaBbsTheme.getPostsviewnum()==null? 0:oaBbsTheme.getPostsviewnum()) + 1);
                 oaBbsThemeDao.saveObject(oaBbsTheme);
            }
        }
        
    }
    /**
     * 子模块当天主题数
     * @param layoutno
     * @return
     */
    @Override
    public Long getTodayThemeNum(String layoutno) {
        Map<String, Object> filterMap =new HashMap<String, Object>();
        filterMap.put("layoutno", layoutno);
        filterMap.put("createtimeEq", DatetimeOpt.currentDate());
        List<OaBbsTheme> todayTheme=this.listObjects(filterMap);
        
        return (long) (todayTheme==null?0:todayTheme.size());
    }
   
    @Override
    public void deleteObject(OaBbsTheme o) {
        OaBbsTheme theme = super.getObjectById(o.getThemeno());
        if(theme != null){
            theme.setState("D");
            deleteThemeDependencies(o.getThemeno());
        }
    }
   
    @Override
    public void deleteByLayoutNo(String layoutno) {
        Map<String, Object> filterMap =new HashMap<String, Object>();
        filterMap.put("layoutno", layoutno);
        filterMap.put("excludeStates", "D");
        List<OaBbsTheme> list=this.listObjects(filterMap);
        
        if(list != null && !list.isEmpty()){
            for(OaBbsTheme o : list){
                o.setState("D");
                deleteThemeDependencies(o.getThemeno());
            }
        }
    }

    @Override
    public void deleteThemeDependencies(String themeno) {
      //删除回复
        oaLeaveMsgManager.deleteByDjId(themeno, "BBS");
        //删除关注
        oaBbsAttentionManager.deleteObjectById(themeno);
        
    }
}

