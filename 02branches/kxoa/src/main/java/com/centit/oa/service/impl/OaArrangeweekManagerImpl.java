package com.centit.oa.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.dao.OaArrangeweekDao;
import com.centit.oa.po.OaArrangeweek;
import com.centit.oa.po.OaWorkSummary;
import com.centit.oa.service.OaArrangeweekManager;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.po.FUnitinfo;
import com.centit.sys.util.DateTimeUtil;
import com.ibm.icu.text.SimpleDateFormat;

public class OaArrangeweekManagerImpl extends
        BaseEntityManagerImpl<OaArrangeweek> implements OaArrangeweekManager {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(OaArrangeweekManager.class);
    private OaArrangeweekDao oaArrangeweekDao;

    public void setOaArrangeweekDao(OaArrangeweekDao baseDao) {
        this.oaArrangeweekDao = baseDao;
        this.setBaseDao(oaArrangeweekDao);
    }

    @Override
    public String getNextKey() {
        return "YZAP"
                + oaArrangeweekDao.getNextKeyBySequence("S_OA_ARRANGEWEEK", 12);
    }

    @Override
    public void saveOaArrange(OaArrangeweek oaarrangeweek) {
        oaArrangeweekDao.saveObject(oaarrangeweek);
    }

    @Override
    public List<OaWorkSummary> getWorkSummaries(
            HashMap<String, Object> searchColumn) throws ParseException {
        List<OaWorkSummary> oaWorkSummaries = new ArrayList<OaWorkSummary>();
        String showType = String.valueOf(searchColumn.get("showType"));
        String firstDay = String.valueOf(searchColumn.get("beginDate"));
        searchColumn.remove("showType");
        searchColumn.remove("beginDate");
        int maxindex = 7;
        if ("workday".equals(showType)) {
            maxindex = 5;
        }
        for (int i = 0; i < maxindex; i++) {
            Date createtime = null;
            if (i != 0) {
                createtime = DatetimeOpt.addDays(new SimpleDateFormat(
                        "yyyy-MM-dd").parse(firstDay), i);
            } else {
                createtime = new SimpleDateFormat("yyyy-MM-dd").parse(firstDay);
            }
            OaWorkSummary workSummary = new OaWorkSummary(createtime);
            searchColumn.put("beginCreatetime", workSummary.getId()
                    + " 00:00:00");
            searchColumn
                    .put("endCreatetime", workSummary.getId() + " 23:59:59");
            List<OaArrangeweek> arrangeweeks = oaArrangeweekDao
                    .listObjects(searchColumn);
            for (int j = 0; j < arrangeweeks.size();) {
                OaArrangeweek oaArrangeweek = (OaArrangeweek) arrangeweeks
                        .get(j);
                if ("0".equals(String.valueOf(searchColumn.get("listType")))) {// 一般工作人员的列表
                    if ("0".equals(oaArrangeweek.getState())
                            && !oaArrangeweek.getDepno().equals(
                                    searchColumn.get("curDepno"))) {
                        arrangeweeks.remove(j);
                        continue;
                    }
                } else {// 办公室人员的列表
                    if ("0".equals(oaArrangeweek.getState())) {
                        arrangeweeks.remove(j);
                        continue;
                    }
                }
                fileWithOaArrangeweek(oaArrangeweek,
                        String.valueOf(searchColumn.get("listType")),
                        String.valueOf(searchColumn.get("curDepno")));
                j++;
            }
            workSummary.setArrangeweekJsons(arrangeweeks);
            if ("havePlan".endsWith(showType)) {
                if (!workSummary.isCheckempty()) {
                    oaWorkSummaries.add(workSummary);
                }
            } else {
                oaWorkSummaries.add(workSummary);
            }
        }
        return oaWorkSummaries;
    }

    private void fileWithOaArrangeweek(OaArrangeweek oaArrangeweek,
            String listType, String curDepno) throws ParseException {
        String canUpdated = "1", canDeleted = "1";
        if ("0".equals(listType)) {// 一般工作人员的列表
            if (!curDepno.equals(oaArrangeweek.getDepno())) {// 不能操作其他部门的工作安排
                canUpdated = "0";
                canDeleted = "0";
            } else {
                if ("1".equals(oaArrangeweek.getState())) { // 对于提交的安排，只能“删除”不能“编辑”
                    canUpdated = "0";
                    canDeleted = "0";
                }
            }
        } else {// 办公室人员的列表
            if (new SimpleDateFormat("yyyy-MM-dd").parse(
                    new SimpleDateFormat("yyyy-MM-dd").format(oaArrangeweek
                            .getCreatetime())).before(
                    new SimpleDateFormat("yyyy-MM-dd").parse(DateTimeUtil
                            .getWeekFirstDate()))) {
                canUpdated = "0";
                canDeleted = "1";
            }
        }
        String showTimeValue = "";
        if ("SW".equals(oaArrangeweek.getDateran())) {
            showTimeValue = "上午";
        } else if ("XW".equals(oaArrangeweek.getDateran())) {
            showTimeValue = "下午";
        } else {
            showTimeValue = "晚上";
        }
        oaArrangeweek.setShowTimeValue(showTimeValue
                + "<br/>"
                + new SimpleDateFormat("h:mm").format(oaArrangeweek
                        .getCreatetime()));
        if (StringUtils.isNotBlank(oaArrangeweek.getDepno())) {
            oaArrangeweek.setDepname(getUnitName(oaArrangeweek.getDepno()));
        }
        oaArrangeweek.setCanUpdated(canUpdated);
        oaArrangeweek.setCanDeleted(canDeleted);

    }

    @SuppressWarnings("unchecked")
    @Override
    public String getUnitName(String depno) {
        // TODO Auto-generated method stub
        List<FUnitinfo> unitinfos = ((List<FUnitinfo>) oaArrangeweekDao
                .findObjectsByHql("from FUnitinfo where unitcode='" + depno
                        + "'"));
        if (unitinfos.size() > 0) {
            return ((FUnitinfo) unitinfos.get(0)).getUnitname();
        }
        return null;
    }

}
