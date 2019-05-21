package com.centit.bbs.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.bbs.po.OaBbsDashboard;
import com.centit.bbs.po.OaBbsDiscussion;
import com.centit.bbs.dao.OaBbsDiscussionDao;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.bbs.service.OaBbsDiscussionManager;
import com.centit.bbs.service.OaBbsThemeManager;

public class OaBbsDiscussionManagerImpl extends
        BaseEntityManagerImpl<OaBbsDiscussion> implements
        OaBbsDiscussionManager {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory
            .getLog(OaBbsDiscussionManager.class);

    private OaBbsDiscussionDao oaBbsDiscussionDao;
    
    private OaBbsThemeManager oaBbsThemeManager;
    
    
    public void setOaBbsThemeManager(OaBbsThemeManager oaBbsThemeManager) {
        this.oaBbsThemeManager = oaBbsThemeManager;
    }

    public void setOaBbsDiscussionDao(OaBbsDiscussionDao baseDao) {
        this.oaBbsDiscussionDao = baseDao;
        setBaseDao(this.oaBbsDiscussionDao);
    }
    
    /**
     * 获取序列生成的主键
     * 
     * @return
     */
    public String getNextKey() {

        return oaBbsDiscussionDao.getNextValueOfSequence();
    }

    public void saveObject(OaBbsDiscussion o) {
        if (StringUtils.isBlank(o.getLayoutno())) {
            o.setLayoutno("SM" + this.getNextKey().substring(1));
        }

        super.saveObject(o);
    }

    /**
     * 更新讨论区子模块帖子回复数
     */
    @Override
    public void updatePostsNum(String layoutno) {
        if (StringUtils.isNotBlank(layoutno)) {
            OaBbsDiscussion oaBbsDiscussion = this.getObjectById(layoutno);
            if (null != oaBbsDiscussion) {
                oaBbsDiscussion.setPostsnum((oaBbsDiscussion.getPostsnum() ==null? 0:oaBbsDiscussion.getPostsnum())+ 1);
                this.oaBbsDiscussionDao.saveObject(oaBbsDiscussion);
            }
        }

    }
    
    
    /**
     * 更新讨论区子模块主題數
     * @param layoutno
     */
    @Override
    public void updateSubjectnum(String layoutno) {
        if (StringUtils.isNotBlank(layoutno)) {
            OaBbsDiscussion oaBbsDiscussion = this.getObjectById(layoutno);
            if (null != oaBbsDiscussion) {
                oaBbsDiscussion.setSubjectnum((oaBbsDiscussion.getSubjectnum()==null? 0:oaBbsDiscussion.getSubjectnum()) + 1);
                this.oaBbsDiscussionDao.saveObject(oaBbsDiscussion);
            }
        }
        
    }
    
    @Override
    public void deleteObject(OaBbsDiscussion o) {
        OaBbsDiscussion oaBbsDiscussion = this.getObjectById(o.getLayoutno());
        if(null != oaBbsDiscussion){
            oaBbsDiscussion.setState("D");
            //删除主题
            oaBbsThemeManager.deleteByLayoutNo(o.getLayoutno());
        }
    }

    @Override
    public void deleteByLayoutCode(String layoutcode) {
        Map<String, Object> filterMap =new HashMap<String, Object>();
        filterMap.put("layoutcode", layoutcode);
        filterMap.put("excludeStates", "D");
        List<OaBbsDiscussion> list = super.listObjects(filterMap);
        if(list!=null && !list.isEmpty()){
            for(OaBbsDiscussion o:list){
                o.setState("D");
              //删除主题
                oaBbsThemeManager.deleteByLayoutNo(o.getLayoutno());
            }
        }
    }

    @Override
    public JSONArray putLayoutTree(String usercode, String sParentUnit) {
        List<Map<String, String>> layoutTree = new ArrayList<Map<String, String>>();
        layoutTree.addAll(setLayoutTree(usercode, sParentUnit));
        
        JSONArray ja = new JSONArray();
        ja.addAll(layoutTree);
        return ja;
    }
    
    /**
     * 获取版面树
     */
    public List<Map<String, String>> setLayoutTree(String usercode, String sParentUnit) {
        //添加排序
        Map<String, Object> filterMap =new HashMap<String, Object>();
        filterMap.put("ISVALID", "T");
        filterMap.put("oaBbsDashboard.approvals", usercode);
        filterMap.put("excludeStates", "D");//在用板块
        
        List<OaBbsDiscussion> units =oaBbsDiscussionDao.listObjects(filterMap); 
        List<Map<String, String>> unit = new ArrayList<Map<String, String>>();
        Map<String, String> mtemp = new HashMap<String, String>();
        
        for (OaBbsDiscussion oaBbsDiscussion : units) {
            Map<String, String> m = new HashMap<String, String>();
            m.put("id", oaBbsDiscussion.getLayoutno());

            // 顶级父节点如果不存在，设置为null，否则ztree找不到顶级节点
            m.put("pId",oaBbsDiscussion.getLayoutcode());//版面代码
            // 一级目录下菜单展开
            m.put("open", "true");
            m.put("name", oaBbsDiscussion.getSublayouttitle());
            m.put("unitorder", String.valueOf(oaBbsDiscussion.getOrderno()));//部门排序
            
            m.put("choosetype", "dis");
//              m.put("icon", "../scripts/inputZtree/img/diy/unit.png");               
            unit.add(m);
        }

        return unit;
    }

}
