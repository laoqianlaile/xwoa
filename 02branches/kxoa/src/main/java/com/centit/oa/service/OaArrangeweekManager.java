package com.centit.oa.service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import com.centit.core.service.BaseEntityManager;
import com.centit.oa.po.OaArrangeweek;
import com.centit.oa.po.OaWorkSummary;

public interface OaArrangeweekManager extends BaseEntityManager<OaArrangeweek> {
    // 获取主键
    public String getNextKey();

    public void saveOaArrange(OaArrangeweek oaarrangeweek);

    public List<OaWorkSummary> getWorkSummaries(
            HashMap<String, Object> searchColumn) throws ParseException;

    public String getUnitName(String depno);
}
