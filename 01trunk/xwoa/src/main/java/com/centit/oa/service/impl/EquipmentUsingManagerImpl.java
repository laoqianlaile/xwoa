package com.centit.oa.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.core.utils.PageDesc;
import com.centit.oa.dao.EquipmentUsingDao;
import com.centit.oa.po.EquipmentUsing;
import com.centit.oa.service.EquipmentUsingManager;
import com.centit.support.utils.DatetimeOpt;

public class EquipmentUsingManagerImpl extends
        BaseEntityManagerImpl<EquipmentUsing> implements EquipmentUsingManager {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory
            .getLog(EquipmentUsingManager.class);

    // private static final SysOptLog sysOptLog =
    // SysOptLogFactoryImpl.getSysOptLog();

    private EquipmentUsingDao equipmentUsingDao;

    public void setEquipmentUsingDao(EquipmentUsingDao baseDao) {
        this.equipmentUsingDao = baseDao;
        setBaseDao(this.equipmentUsingDao);
    }

    // 获取下一个固定资产编号
    public Long genNextUseRequestId() {

        return equipmentUsingDao.getNextLongSequence("S_useRequestId");
    }

    @Override
    public void saveObject(EquipmentUsing o) {
        if (null == (o.getUseRequestId())) {
            o.setUseRequestId(equipmentUsingDao
                    .getNextLongSequence("S_useRequestId"));
        }
        super.saveObject(o);
    }

    @Override
    public List<EquipmentUsing> getEquipmentUsingList(String string,
            Long equipmentId, PageDesc pageDesc) {
        return equipmentUsingDao.listObjects(string, equipmentId, pageDesc);
    }

    @Override
    public List<EquipmentUsing> getEquipmentUsingList(Date beginTime,
            Date endTime, Long equipmentId) {
        String sql = "from EquipmentUsing where  1=1  and ("
                + "( planBeginTime >=  to_date('"
                + DatetimeOpt.convertDatetimeToString(beginTime)
                + "','yyyy-MM-dd hh24:mi:ss')"
                + " and planBeginTime <=to_date('"
                + DatetimeOpt.convertDatetimeToString(endTime)
                + "','yyyy-MM-dd hh24:mi:ss') ) "
                
                 + " or ( planEndTime >=  to_date('"
                + DatetimeOpt.convertDatetimeToString(beginTime)
                + "','yyyy-MM-dd hh24:mi:ss') "
                + " and planEndTime <=  to_date('"
                + DatetimeOpt.convertDatetimeToString(endTime)
                + "','yyyy-MM-dd hh24:mi:ss') ) "
                
                 + " or ( planBeginTime <=  to_date('"
                + DatetimeOpt.convertDatetimeToString(beginTime)
                + "','yyyy-MM-dd hh24:mi:ss') "
                + " and planEndTime >=  to_date('"
                + DatetimeOpt.convertDatetimeToString(endTime)
                + "','yyyy-MM-dd hh24:mi:ss') ) "
                
                  + " or ( beginTime >=  to_date('"
                + DatetimeOpt.convertDatetimeToString(beginTime)
                + "','yyyy-MM-dd hh24:mi:ss') "
                + " and beginTime <=  to_date('"
                + DatetimeOpt.convertDatetimeToString(endTime)
                + "','yyyy-MM-dd hh24:mi:ss') ) "
                
                + " or ( endTime >=  to_date('"
                + DatetimeOpt.convertDatetimeToString(beginTime)
                + "','yyyy-MM-dd hh24:mi:ss') "
                + " and endTime <=  to_date('"
                + DatetimeOpt.convertDatetimeToString(endTime)
                + "','yyyy-MM-dd hh24:mi:ss') ) "
                
                + "or ( beginTime <=  to_date('"
                + DatetimeOpt.convertDatetimeToString(beginTime)
                + "','yyyy-MM-dd hh24:mi:ss') "
                + " and  endTime >=  to_date('"
                + DatetimeOpt.convertDatetimeToString(endTime)
                + "','yyyy-MM-dd hh24:mi:ss'))) "
                + " and equipmentId = "
                + equipmentId
                + " and equipmentState!='4'   order by endTime , planBeginTime ";
        return equipmentUsingDao.listObjects(sql);
    }

}
