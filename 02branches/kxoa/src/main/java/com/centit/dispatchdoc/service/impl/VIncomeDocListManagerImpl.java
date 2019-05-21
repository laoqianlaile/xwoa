package com.centit.dispatchdoc.service.impl;

import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.core.utils.PageDesc;
import com.centit.dispatchdoc.po.VIncomeDocList;
import com.centit.dispatchdoc.dao.VIncomeDocListDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.dispatchdoc.service.VIncomeDocListManager;
import com.centit.powerruntime.dao.OptBaseInfoDao;
import com.centit.sys.service.CodeRepositoryUtil;

public class VIncomeDocListManagerImpl extends
        BaseEntityManagerImpl<VIncomeDocList> implements VIncomeDocListManager {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory
            .getLog(VIncomeDocListManager.class);

    private VIncomeDocListDao VIncomeDocListDao;

    private OptBaseInfoDao optBaseInfoDao;

    public void setVIncomeDocListDao(VIncomeDocListDao baseDao) {
        this.VIncomeDocListDao = baseDao;
        setBaseDao(this.VIncomeDocListDao);
    }

    public List<VIncomeDocList> getDocRelativeList(String dispatchNo) {
        return VIncomeDocListDao.getDocRelativeList(dispatchNo);
    }

    public void setOptBaseInfoDao(OptBaseInfoDao optBaseInfoDao) {
        this.optBaseInfoDao = optBaseInfoDao;
    }

    @Override
    public List<VIncomeDocList> listIncomeDocList(
            Map<String, Object> filterMap, PageDesc pageDesc) {
        return VIncomeDocListDao.listIncomeDoc(filterMap, pageDesc);

        /*
         * List<VIncomeDocList> l= VIncomeDocListDao.listIncomeDoc(filterMap,
         * pageDesc); if(l!=null && l.size()>0){ for(VIncomeDocList
         * vIncomeDocList :l){ if(("C").equals(vIncomeDocList.getBizstate())){
         * vIncomeDocList.setNodename("已办结");
         * vIncomeDocList.setBizusernames("-"); }else if
         * ("W".equals(vIncomeDocList.getBizstate()) ||
         * "T".equals(vIncomeDocList.getBizstate())){
         * vIncomeDocList.setNodename(
         * optBaseInfoDao.getNodeNamesByFlowinstid(vIncomeDocList
         * .getFlowInstId()));
         * vIncomeDocList.setBizusernames(optBaseInfoDao.getuserNamesByFlowinstid
         * (vIncomeDocList.getFlowInstId())); }else{
         * vIncomeDocList.setNodename("未提交");
         * vIncomeDocList.setBizusernames(CodeRepositoryUtil
         * .getValue("usercode", vIncomeDocList.getUsercode())); } } } return l;         
         */
    }

    @Override
    public List<VIncomeDocList> listIncomeDocListV2(
            Map<String, Object> filterMap, PageDesc pageDesc) {
        return VIncomeDocListDao.listIncomeDocV2(filterMap, pageDesc);
    }

    @Override
    public List<String> findNeighbouringDjId(Map<String, Object> params,
            String currDjId) {
        return VIncomeDocListDao.findNeighbouringDjId(params, currDjId);
    }

    @Override
    public List<String> findNeighbouringDjId2(Map<String, Object> params,
            String currDjId) {
        return VIncomeDocListDao.findNeighbouringDjId2(params, currDjId);
    }

    @Override
    public List<VIncomeDocList> listIncomeDocForExcel(
            Map<String, Object> filterMap) {
        return VIncomeDocListDao.listIncomeDocForExcel(filterMap);
    }
}
