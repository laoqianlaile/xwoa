package com.centit.dispatchdoc.service.impl;

import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.core.utils.PageDesc;
import com.centit.dispatchdoc.po.VDispatchDocList;
import com.centit.dispatchdoc.po.VIncomeDocList;
import com.centit.dispatchdoc.dao.VDispatchDocListDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.dispatchdoc.service.VDispatchDocListManager;
import com.centit.powerruntime.dao.OptBaseInfoDao;
import com.centit.sys.service.CodeRepositoryUtil;

public class VDispatchDocListManagerImpl extends
        BaseEntityManagerImpl<VDispatchDocList> implements
        VDispatchDocListManager {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory
            .getLog(VDispatchDocListManager.class);

    private VDispatchDocListDao VDispatchDocListDao;
    private OptBaseInfoDao optBaseInfoDao;

    public void setVDispatchDocListDao(VDispatchDocListDao baseDao) {
        this.VDispatchDocListDao = baseDao;
        setBaseDao(this.VDispatchDocListDao);
    }

    public void setOptBaseInfoDao(OptBaseInfoDao optBaseInfoDao) {
        this.optBaseInfoDao = optBaseInfoDao;
    }

    @Override
    public List<VDispatchDocList> listDispatchDoc(
            Map<String, Object> filterMap, PageDesc pageDesc) {
        return VDispatchDocListDao.listDispatchDoc(filterMap, pageDesc);
        /*
         * List<VDispatchDocList>
         * l=VDispatchDocListDao.listDispatchDoc(filterMap,pageDesc);
         * 
         * if(l!=null && l.size()>0){ for(VDispatchDocList vDispatchDocList :l){
         * if(("C").equals(vDispatchDocList.getBizstate())){
         * vDispatchDocList.setNodename("已办结");
         * vDispatchDocList.setBizusernames("-"); }else if
         * ("W".equals(vDispatchDocList.getBizstate()) ||
         * "T".equals(vDispatchDocList.getBizstate())){
         * vDispatchDocList.setNodename
         * (optBaseInfoDao.getNodeNamesByFlowinstid(vDispatchDocList
         * .getFlowInstId()));
         * vDispatchDocList.setBizusernames(optBaseInfoDao.getuserNamesByFlowinstid
         * (vDispatchDocList.getFlowInstId())); }else{
         * vDispatchDocList.setNodename("未提交");
         * vDispatchDocList.setBizusernames(
         * CodeRepositoryUtil.getValue("usercode",
         * vDispatchDocList.getUsercode())); } } } return l;
         */
    }
    @Override
    public List<VDispatchDocList> listDispatchDocV2(
            Map<String, Object> filterMap, PageDesc pageDesc) {
        return VDispatchDocListDao.listDispatchDocV2(filterMap, pageDesc);
    }

    @Override
    public List<String> findNeighbouringDjId(Map<String, Object> params,
            String currDjId) {
        return VDispatchDocListDao.findNeighbouringDjId(params, currDjId);
    }

    @Override
    public List<VDispatchDocList> listDispatchDocForExcel(
            Map<String, Object> filterMap) {
        return VDispatchDocListDao.listDispatchDocForExcel(filterMap);
    }
}
