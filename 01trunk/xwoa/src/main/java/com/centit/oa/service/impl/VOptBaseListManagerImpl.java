package com.centit.oa.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.core.utils.PageDesc;
import com.centit.oa.dao.VOptBaseListDao;
import com.centit.oa.po.VOptBaseList;
import com.centit.oa.service.VOptBaseListManager;
import com.centit.powerruntime.dao.OptBaseInfoDao;

public class VOptBaseListManagerImpl extends
        BaseEntityManagerImpl<VOptBaseList> implements VOptBaseListManager {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(VOptBaseListManager.class);

    private VOptBaseListDao vOptBaseListDao;
    private OptBaseInfoDao optBaseInfoDao;

    public void setVOptBaseListDao(VOptBaseListDao baseDao) {
        this.vOptBaseListDao = baseDao;
        setBaseDao(this.vOptBaseListDao);
    }

    public void setOptBaseInfoDao(OptBaseInfoDao optBaseInfoDao) {
        this.optBaseInfoDao = optBaseInfoDao;
    }

    @Override
    public List<VOptBaseList> listOptBaseInfo(Map<String, Object> filterMap,
            PageDesc pageDesc) {
        // TODO Auto-generated method stub
        return vOptBaseListDao.listOptBaseInfo(filterMap, pageDesc);
        /*
         * List<VOptBaseList> l= vOptBaseListDao.listOptBaseInfo( filterMap,
         * pageDesc);
         * 
         * List<VOptBaseList> rel=new ArrayList<VOptBaseList>(); if(l!=null &&
         * l.size()>0){ for(VOptBaseList vOptBaseList :l){ VOptBaseList bean=new
         * VOptBaseList(); bean.copyNotNullProperty(vOptBaseList);
         * if(("C").equals(vOptBaseList.getBizstate())){
         * bean.setNodename("已办结"); bean.setBizusernames("-"); }else if
         * ("W".equals(vOptBaseList.getBizstate()) ||
         * "T".equals(vOptBaseList.getBizstate())){
         * bean.setNodename(optBaseInfoDao
         * .getNodeNamesByFlowinstid(vOptBaseList.getFlowInstId()));
         * bean.setBizusernames
         * (optBaseInfoDao.getuserNamesByFlowinstid(vOptBaseList
         * .getFlowInstId())); }else{ bean.setNodename("未提交");
         * bean.setBizusernames(CodeRepositoryUtil.getValue("usercode",
         * vOptBaseList.getUsercode())); } rel.add(bean); } } return rel;
         */

    }

    @Override
    public List<VOptBaseList> listOptBaseInfoLeader(
            Map<String, Object> filterMap, PageDesc pageDesc) {
        // TODO Auto-generated method stub
        return vOptBaseListDao.listOptBaseInfoLeader(filterMap, pageDesc);
    }

    @Override
    public List<VOptBaseList> listNotVOptBaseList(String djId, String itemtype) {
        // TODO Auto-generated method stub
        return vOptBaseListDao.listNotVOptBaseList(djId, itemtype);
    }

    @Override
    public List<VOptBaseList> listVOptBaseList(String djId, String itemtype,
            String usercode) {
        // TODO Auto-generated method stub
        return vOptBaseListDao.listVOptBaseList(djId, itemtype, usercode);
    }

    @Override
    public VOptBaseList getObjectByDjId(String djId) {
        // TODO Auto-generated method stub
        return vOptBaseListDao.getObjectByDjid(djId);
    }

    @Override
    public boolean isVailViewPower(String djId, String usercode) {
       
        return  vOptBaseListDao.isVailViewPower(djId,usercode);
    }

    @Override
    public List<String> findNeighbouringDjId(Map<String, Object> filterMap,
            String currDjId) {
        return vOptBaseListDao.findNeighbouringDjId(filterMap, currDjId);
    }

}
