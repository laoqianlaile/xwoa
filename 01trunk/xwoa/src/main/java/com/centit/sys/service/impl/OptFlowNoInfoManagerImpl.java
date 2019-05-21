package com.centit.sys.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.core.utils.PageDesc;
import com.centit.support.utils.DatetimeOpt;
import com.centit.support.utils.StringBaseOpt;
import com.centit.sys.dao.OptFlowNoInfoDao;
import com.centit.sys.dao.OptFlowNoPoolDao;
import com.centit.sys.po.FDatadictionary;
import com.centit.sys.po.FUnitinfo;
import com.centit.sys.po.OptFlowNoInfo;
import com.centit.sys.po.OptFlowNoInfoId;
import com.centit.sys.po.OptFlowNoPool;
import com.centit.sys.po.OptFlowNoPoolId;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.service.OptFlowNoInfoManager;

public class OptFlowNoInfoManagerImpl extends
        BaseEntityManagerImpl<OptFlowNoInfo> implements OptFlowNoInfoManager {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(OptFlowNoInfoManager.class);

    private OptFlowNoInfoDao optFlowNoInfoDao;
    private OptFlowNoPoolDao optFlowNoPoolDao;
    
    public void setOptFlowNoInfoDao(OptFlowNoInfoDao baseDao) {
        this.optFlowNoInfoDao = baseDao;
        setBaseDao(this.optFlowNoInfoDao);
    }

    public void setOptFlowNoPoolDao(OptFlowNoPoolDao baseDao) {
        this.optFlowNoPoolDao = baseDao;
    }

    /**
     * 获取最新的流水号，并标记+1
     */
    @Override
    public synchronized long newNextLsh(String ownerCode, String codeCode, Date codeBaseDate) {
        Date codeDate = codeBaseDate; // DatetimeOpt.convertSqlDate(codeBaseDate);
        OptFlowNoInfoId noId = new OptFlowNoInfoId(ownerCode, codeDate, codeCode);
        OptFlowNoInfo noInfo = optFlowNoInfoDao.getObjectById(noId);
        long nextCode = 1l;
        if (noInfo == null) {
            noInfo = new OptFlowNoInfo(noId, 1l, DatetimeOpt.currentUtilDate());
        } else {
            nextCode = noInfo.getCurNo() + 1;
            //检查新生产的号是否已经被预留
            while(true){
                 OptFlowNoPoolId poolId =  new OptFlowNoPoolId(ownerCode, codeDate, codeCode,nextCode);
                 OptFlowNoPool poolNo = optFlowNoPoolDao.getObjectById( poolId);
                 //没有被预留
                 if(poolNo==null){
                      break;
                 }
                 nextCode ++;
            }
            noInfo.setCurNo(nextCode);
            noInfo.setLastCodeDate(DatetimeOpt.currentUtilDate());
            noInfo.setCuryear(String.valueOf(DatetimeOpt.getYear(DatetimeOpt.currentUtilDate())));
            
        }
        optFlowNoInfoDao.saveObject(noInfo);
        return nextCode;
    }

    /**
     * 以天为单位记录流水号
     */
    @Override
    public long newNextLshBaseDay(String ownerCode, String codeCode, Date codeBaseDate) {
        return newNextLsh(ownerCode, codeCode, DatetimeOpt.truncateToDay(codeBaseDate));
    }

    /**
     * 以月为单位记录流水号
     */
    @Override
    public long newNextLshBaseMonth(String ownerCode, String codeCode, Date codeBaseDate) {
        return newNextLsh(ownerCode, codeCode, DatetimeOpt.truncateToMonth(codeBaseDate));
    }

    /**
     * 以年为单位记录流水号
     */
    @Override
    public long newNextLshBaseYear(String ownerCode, String codeCode, Date codeBaseDate) {
        return newNextLsh(ownerCode, codeCode, DatetimeOpt.truncateToYear(codeBaseDate));
    }

    @Override
    public long newNextLsh(String codeCode) {
        return newNextLsh(DefaultOwnerCode, codeCode, DefaultCodeDate);
    }

    @Override
    public long newNextLsh(String ownerCode, String codeCode) {
        return newNextLsh(ownerCode, codeCode, DefaultCodeDate);
    }

    /**
     * 查看最新流水号
     */
    @Override
    public long viewNextLsh(String ownerCode, String codeCode, Date codeBaseDate) {
        Date codeDate = codeBaseDate; // DatetimeOpt.convertSqlDate(codeBaseDate);
        OptFlowNoInfoId noId = new OptFlowNoInfoId(ownerCode, codeDate, codeCode);
        OptFlowNoInfo noInfo = optFlowNoInfoDao.getObjectById(noId);
        long nextCode = 1l;
        if (noInfo != null)
            nextCode = noInfo.getCurNo() + 1;
        return nextCode;
    }

    @Override
    public long viewNextLshBaseDay(String ownerCode, String codeCode, Date codeBaseDate) {
        return viewNextLsh(ownerCode, codeCode, DatetimeOpt.truncateToDay(codeBaseDate));
    }

    @Override
    public long viewNextLshBaseMonth(String ownerCode, String codeCode, Date codeBaseDate) {
        return viewNextLsh(ownerCode, codeCode, DatetimeOpt.truncateToMonth(codeBaseDate));
    }

    @Override
    public long viewNextLshBaseYear(String ownerCode, String codeCode, Date codeBaseDate) {
        return viewNextLsh(ownerCode, codeCode, DatetimeOpt.truncateToYear(codeBaseDate));
    }

    @Override
    public long viewNextLsh(String codeCode) {
        return viewNextLsh(DefaultOwnerCode, codeCode, DefaultCodeDate);
    }

    @Override
    public long viewNextLsh(String ownerCode, String codeCode) {
        return viewNextLsh(ownerCode, codeCode, DefaultCodeDate);
    }

    @Override
    public synchronized void recordNextLsh(String ownerCode, String codeCode, Date codeBaseDate, long currCode) {
        Date codeDate = codeBaseDate;// DatetimeOpt.convertSqlDate(codeBaseDate);
        // 如果是从池中取出的，在池中删除
        OptFlowNoPoolId poolId = new OptFlowNoPoolId(ownerCode, codeDate, codeCode, currCode);

        OptFlowNoPool poolNo = optFlowNoPoolDao.getObjectById(poolId);
        if (poolNo != null) {
            optFlowNoPoolDao.deleteObject(poolNo);
            return;
        }

        OptFlowNoInfoId noId = new OptFlowNoInfoId(ownerCode, codeDate, codeCode);
        OptFlowNoInfo noInfo = optFlowNoInfoDao.getObjectById(noId);
        if (noInfo == null) {
            noInfo = new OptFlowNoInfo(noId, currCode, DatetimeOpt.currentUtilDate());
            optFlowNoInfoDao.saveObject(noInfo);
        } else {
            if (noInfo.getCurNo() < currCode) {
                noInfo.setCurNo(currCode);
                noInfo.setLastCodeDate(DatetimeOpt.currentUtilDate());
                optFlowNoInfoDao.saveObject(noInfo);
            }
        }
    }

    @Override
    public void recordNextLshBaseDay(String ownerCode, String codeCode, Date codeBaseDate, long currCode) {
        recordNextLsh(ownerCode, codeCode, DatetimeOpt.truncateToDay(codeBaseDate), currCode);
    }

    @Override
    public void recordNextLshBaseMonth(String ownerCode, String codeCode, Date codeBaseDate, long currCode) {
        recordNextLsh(ownerCode, codeCode, DatetimeOpt.truncateToMonth(codeBaseDate), currCode);
    }

    @Override
    public void recordNextLshBaseYear(String ownerCode, String codeCode, Date codeBaseDate, long currCode) {
        recordNextLsh(ownerCode, codeCode, DatetimeOpt.truncateToYear(codeBaseDate), currCode);
    }

    @Override
    public void recordNextLsh(String codeCode, long currCode) {
        recordNextLsh(DefaultOwnerCode, codeCode, DefaultCodeDate, currCode);
    }

    @Override
    public void recordNextLsh(String ownerCode, String codeCode, long currCode) {
        recordNextLsh(ownerCode, codeCode, DefaultCodeDate, currCode);
    }

    @Override
    public synchronized long assignNextLsh(String ownerCode, String codeCode, Date codeBaseDate) {
        long minPoolNo = optFlowNoPoolDao.fetchFirstLsh(ownerCode, codeCode, codeBaseDate);
        if (minPoolNo > 0) {
            OptFlowNoPoolId obj = new OptFlowNoPoolId();
            obj.setOwnerCode(ownerCode);
            obj.setCodeDate(codeBaseDate);
            obj.setCodeCode(codeCode);
            obj.setCurNo(minPoolNo);
            optFlowNoPoolDao.deleteObjectById(obj);
            return minPoolNo;
        } else
            return newNextLsh(ownerCode, codeCode, codeBaseDate);
    }

    @Override
    public long assignNextLshBaseDay(String ownerCode, String codeCode, Date codeBaseDate) {
        return assignNextLsh(ownerCode, codeCode, DatetimeOpt.truncateToDay(codeBaseDate));
    }

    @Override
    public long assignNextLshBaseMonth(String ownerCode, String codeCode, Date codeBaseDate) {
        return assignNextLsh(ownerCode, codeCode, DatetimeOpt.truncateToMonth(codeBaseDate));
    }

    @Override
    public long assignNextLshBaseYear(String ownerCode, String codeCode, Date codeBaseDate) {
        return assignNextLsh(ownerCode, codeCode, DatetimeOpt.truncateToYear(codeBaseDate));
    }

    @Override
    public long assignNextLsh(String ownerCode, String codeCode) {
        return assignNextLsh(ownerCode, codeCode, DefaultCodeDate);
    }

    @Override
    public long assignNextLsh(String codeCode) {
        return assignNextLsh(DefaultOwnerCode, codeCode, DefaultCodeDate);
    }

    @Override
    public void releaseLsh(String ownerCode, String codeCode, Date codeBaseDate, long currCode) {
        OptFlowNoPool obj = new OptFlowNoPool();
        obj.setOwnerCode(ownerCode);
        obj.setCodeDate(codeBaseDate);
        obj.setCodeCode(codeCode);
        obj.setCurNo(currCode);
        obj.setCreateDate(DatetimeOpt.currentUtilDate());
        optFlowNoPoolDao.saveObject(obj);
    }

    @Override
    public void releaseLshBaseDay(String ownerCode, String codeCode, Date codeBaseDate, long currCode) {
        releaseLsh(ownerCode, codeCode, DatetimeOpt.truncateToDay(codeBaseDate), currCode);
    }

    @Override
    public void releaseLshBaseMonth(String ownerCode, String codeCode, Date codeBaseDate, long currCode) {
        releaseLsh(ownerCode, codeCode, DatetimeOpt.truncateToMonth(codeBaseDate), currCode);
    }

    @Override
    public void releaseLshBaseYear(String ownerCode, String codeCode, Date codeBaseDate, long currCode) {
        releaseLsh(ownerCode, codeCode, DatetimeOpt.truncateToYear(codeBaseDate), currCode);
    }

    @Override
    public void releaseLsh(String ownerCode, String codeCode, long currCode) {
        releaseLsh(ownerCode, codeCode, DefaultCodeDate, currCode);
    }

    @Override
    public void releaseLsh(String codeCode, long currCode) {
        releaseLsh(DefaultOwnerCode, codeCode, DefaultCodeDate, currCode);
    }

    public List<OptFlowNoPool> listLshInPool(String ownerCode, String codeCode, Date codeBaseDate, PageDesc pageDesc) {
        Map<String, Object> filterMap = new HashMap<String, Object>();

        filterMap.put("ownerCode", ownerCode);
        filterMap.put("codeDate", codeBaseDate);
        filterMap.put("codeCode", codeCode);

        return optFlowNoPoolDao.listObjects(filterMap, pageDesc);
    }

    public List<OptFlowNoPool> listLshBaseDayInPool(String ownerCode, String codeCode, Date codeBaseDate, PageDesc pageDesc) {
        return listLshInPool(ownerCode, codeCode, DatetimeOpt.truncateToDay(codeBaseDate), pageDesc);
    }

    public List<OptFlowNoPool> listLshBaseMonthInPool(String ownerCode, String codeCode, Date codeBaseDate, PageDesc pageDesc) {
        return listLshInPool(ownerCode, codeCode, DatetimeOpt.truncateToMonth(codeBaseDate), pageDesc);
    }

    public List<OptFlowNoPool> listLshBaseYearInPool(String ownerCode, String codeCode, Date codeBaseDate, PageDesc pageDesc) {
        return listLshInPool(ownerCode, codeCode, DatetimeOpt.truncateToYear(codeBaseDate), pageDesc);
    }

    public List<OptFlowNoPool> listLshInPool(String ownerCode, String codeCode, PageDesc pageDesc) {
        return listLshInPool(ownerCode, codeCode, DefaultCodeDate, pageDesc);
    }

    public List<OptFlowNoPool> listLshInPool(String codeCode, PageDesc pageDesc) {
        return listLshInPool(DefaultOwnerCode, codeCode, DefaultCodeDate, pageDesc);
    }
    
    
    /**
     * 根据字典项，对应字典项的值、基准日期、流水号基准规则获取最新流水号、部门编号
     * @param zdx 字典项
     * @param zdxVal 字典项中某一条对应的值
     * @param codeDate 生成文号的基准日期，默认为当前日期
     * @param standard 生成文号的规则。day：以天生成流水号；month：以月生成流水号；默认：以年生成流水号
     * @return
     * @throws ParseException
     */
    public String genLsH(String zdx, String zdxVal, Date codeDate, String standard ,String  unitcode) {
        return this.genLsH(CodeRepositoryUtil.getDataPiece(zdx, zdxVal).getDatadesc(), codeDate, standard,unitcode);
    }
    
    
    /**
     * 根据置文号规则中的某一记录值、基准日期、流水号基准规则获取最新流水号
     * @param codeCode ZWHGZ字典项中的某一记录值
     * @param codeDate 生成文号的基准日期，默认为当前日期
     * @param standard 生成文号的规则。day：以天生成流水号；month：以月生成流水号；默认：以年生成流水号
     * @return
     * @throws ParseException
     */
    public String genLsH(String codeCode, Date codeDate, String standard,String  ownerCode) {
        codeDate = (null == codeDate) ? DatetimeOpt.currentUtilDate() : codeDate;
        ownerCode=null==ownerCode?"xtzwhgz":ownerCode;
        long lsh = 1;
        if ("day".equalsIgnoreCase(standard)) { // 天
            lsh = viewNextLshBaseDay(ownerCode, codeCode, codeDate);
        } else if ("month".equalsIgnoreCase(standard)) { // 月
            lsh = viewNextLshBaseMonth(ownerCode, codeCode, codeDate);
        } else { // 年
            lsh = viewNextLshBaseYear(ownerCode, codeCode, codeDate);
        }
        
        return String.valueOf(lsh);
    }
    
    // add by hll begin
    
    /**
     * 根据字典项，对应字典项的值、基准日期、流水号基准规则获取最新流水号
     * @param zdx 字典项
     * @param zdxVal 字典项中某一条对应的值
     * @param codeDate 生成文号的基准日期，默认为当前日期
     * @param standard 生成文号的规则。day：以天生成流水号；month：以月生成流水号；默认：以年生成流水号
     * @return
     * @throws ParseException
     */
    public String genLsH(String zdx, String zdxVal, Date codeDate, String standard) {
        return this.genLsH(CodeRepositoryUtil.getDataPiece(zdx, zdxVal).getDatadesc(), codeDate, standard);
    }
    
    
    /**
     * 根据置文号规则中的某一记录值、基准日期、流水号基准规则获取最新流水号
     * @param codeCode ZWHGZ字典项中的某一记录值
     * @param codeDate 生成文号的基准日期，默认为当前日期
     * @param standard 生成文号的规则。day：以天生成流水号；month：以月生成流水号；默认：以年生成流水号
     * @return
     * @throws ParseException
     */
    public String genLsH(String codeCode, Date codeDate, String standard) {
        codeDate = (null == codeDate) ? DatetimeOpt.currentUtilDate() : codeDate;
        
        long lsh = 1;
        if ("day".equalsIgnoreCase(standard)) { // 天
            lsh = viewNextLshBaseDay("xtzwhgz", codeCode, codeDate);
        } else if ("month".equalsIgnoreCase(standard)) { // 月
            lsh = viewNextLshBaseMonth("xtzwhgz", codeCode, codeDate);
        } else { // 年
            lsh = viewNextLshBaseYear("xtzwhgz", codeCode, codeDate);
        }
        
        return String.valueOf(lsh);
    }
    
    
    /**
     * 根据字典项，对应字典项的值、基准日期、流水号基准规则获取最新流水号并将表中的流水号+1
     * @param zdx 字典项
     * @param zdxVal 字典项中某一条对应的值
     * @param codeDate 生成文号的基准日期，默认为当前日期
     * @param standard 生成文号的规则。day：以天生成流水号；month：以月生成流水号；默认：以年生成流水号
     * @return
     * @throws ParseException
     */
    public String genRecordLsH(String zdx, String zdxVal, Date codeDate, String standard) {
        return this.genRecordLsH(CodeRepositoryUtil.getDataPiece(zdx, zdxVal).getDatadesc(), codeDate, standard);
    }
    
    
    /**
     * 根据置文号规则中的某一记录值、基准日期、流水号基准规则获取最新流水号并将表中的流水号+1
     * @param codeCode ZWHGZ字典项中的某一记录值
     * @param codeDate 生成文号的基准日期，默认为当前日期
     * @param standard 生成文号的规则。day：以天生成流水号；month：以月生成流水号；默认：以年生成流水号
     * @return
     * @throws ParseException
     */
    public String genRecordLsH(String codeCode, Date codeDate, String standard) {
        codeDate = (null == codeDate) ? DatetimeOpt.currentUtilDate() : codeDate;
        
        long lsh = 1;
        if ("day".equalsIgnoreCase(standard)) { // 天
            lsh = newNextLshBaseDay("xtzwhgz", codeCode, codeDate);
        } else if ("month".equalsIgnoreCase(standard)) { // 月
            lsh = newNextLshBaseMonth("xtzwhgz", codeCode, codeDate);
        } else { // 年
            lsh = newNextLshBaseYear("xtzwhgz", codeCode, codeDate);
        }
        
        return String.valueOf(lsh);
    }
    
    
    /**
     * 根据字典项，对应字典项的值、基准日期、流水号获取文号
     * @param zdx 字典项
     * @param zdxVal 字典项中某一条对应的值
     * @param codeDate 生成赋文号的基准日期，默认为当前日期
     * @param orgCode 主办部门编号
     * @param lsh 流水号
     * @return
     */
    public String genFWH(String zdx, String zdxVal, Date codeDate, String orgCode, String lsh) {
        FDatadictionary data = CodeRepositoryUtil.getDataPiece(zdx, zdxVal);
        
        return this.genFWH(data.getDatadesc(), codeDate, orgCode, lsh);
    }
    
    
    /**
     * 根据字典项，对应字典项的值、基准日期、主办部门编号获取文号并将表中的流水号+1
     * @param zdx 字典项
     * @param zdxVal 字典项中某一条对应的值
     * @param codeDate 生成赋文号的基准日期，默认为当前日期
     * @param orgCode 主办部门编号
     * @return
     */
    public String genRecordFWH(String zdx, String zdxVal, Date codeDate, String orgCode) {
        FDatadictionary data = CodeRepositoryUtil.getDataPiece(zdx, zdxVal);
        
        return this.genFWH(data.getDatadesc(), codeDate, orgCode, "recordLsh");
    }
    
    
    /**
     * 根据字典项，对应字典项的值、基准日期、主办部门编号获取文号并将表中的流水号+1
     * @param codeCode ZWHGZ字典项中的某一记录值
     * @param codeDate 生成赋文号的基准日期，默认为当前日期
     * @param orgCode 主办部门编号
     * @return
     */
    public String genRecordFWH(String codeCode, Date codeDate, String orgCode) {
        return this.genFWH(codeCode, codeDate, orgCode, "recordLsh");
    }
    
    
    /**
     * 根据字典项，对应字典项的值、基准日期、主办部门编号、流水号获取文号
     * @param codeCode ZWHGZ字典项中的某一记录值
     * @param codeDate 生成赋文号的基准日期，默认为当前日期
     * @param orgCode 主办部门编号
     * @param lsh 流水号
     * @return
     */
    public String genFWH(String codeCode, Date codeDate, String orgCode, String lsh) {
        FDatadictionary zwhgzData = CodeRepositoryUtil.getDataPiece("ZWHGZ", codeCode);
        int lshLen = 0;
        try {
            lshLen = StringUtils.isBlank(zwhgzData.getExtracode()) ? 0 : Integer.valueOf(zwhgzData.getExtracode());
        } catch (Exception e) {
            log.info("ZWHGZ中文号默认长度为不可解析的数字！");
            log.error(e);
        }
        
        codeDate = (null == codeDate) ? DatetimeOpt.currentUtilDate() : codeDate;
        if ("recordLsh".equals(lsh)) { // 标记在生成该文号后，表中的流水号记录+1
            lsh = this.genRecordLsH(codeCode, codeDate, "year");
        } else if (StringUtils.isBlank(lsh)) {
            lsh = this.genLsH(codeCode, codeDate, "year");
        }
        
        String sLsh = String.valueOf(lsh);
        if (sLsh.length() < lshLen) {
            sLsh = StringBaseOpt.fillZeroForString(sLsh, lshLen);
        }
        
        FUnitinfo unit = CodeRepositoryUtil.getUnitInfoByCode(orgCode);
        
        String sfwh = zwhgzData.getDatavalue().replaceAll("%nh%", String.valueOf(DatetimeOpt.getYear(codeDate)))
                                              .replaceAll("%zt%", StringUtils.isBlank(unit.getPrefix()) ? " " : unit.getPrefix())
                                              .replaceAll("%H%", "函")
                                              .replaceAll("%ls%", StringUtils.isBlank(sLsh) ? "" : sLsh);
        return sfwh;
    }
    
    // add bu hll end
}
