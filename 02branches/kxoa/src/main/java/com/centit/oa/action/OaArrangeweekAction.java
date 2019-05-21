package com.centit.oa.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.centit.oa.po.OaArrangeweek;
import com.centit.oa.po.OaWorkSummary;
import com.centit.oa.service.OaArrangeweekManager;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.util.DateTimeUtil;

public class OaArrangeweekAction extends OACommonBizAction<OaArrangeweek> {
    private static final long serialVersionUID = 1L;
    private List<OaWorkSummary> workSummaries;
    private String result;
    private OaArrangeweekManager oaArrangeweekMgr;

    public void setOaArrangeweekMgr(OaArrangeweekManager basemgr) {
        oaArrangeweekMgr = basemgr;
        this.setBaseEntityManager(oaArrangeweekMgr);
    }

    public List<OaWorkSummary> getWorkSummaries() {
        return workSummaries;
    }

    public void setWorkSummaries(List<OaWorkSummary> workSummaries) {
        this.workSummaries = workSummaries;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String regiseter() {
        String popPage = request.getParameter("popPage");
        if (StringUtils.isBlank(popPage)) {
            popPage = "false";
        }
        request.setAttribute("popPage", popPage);
        String djId = request.getParameter("djId");
        if (StringUtils.isNotBlank(djId)) {
            object = oaArrangeweekMgr.getObjectById(djId);
        } else {
            if ("true".equals(popPage)) {
                request.setAttribute("unitlist",
                        sysUnitManager.getAllSubUnits("1"));
            }
        }
        if (StringUtils.isBlank(request.getParameter("listType"))) {
            request.setAttribute("listType", "0");
        } else {
            request.setAttribute("listType", request.getParameter("listType"));
        }
        return "regiseter";
    }

    public String saveOaArrange() throws UnsupportedEncodingException {
        OaArrangeweek oaarrangeweek = new OaArrangeweek();
        if (StringUtils.isBlank(request.getParameter("saveParams"))) {
            object.setCreatetime(request.getParameter("selectTime"));
            object.setWeektitle(request.getParameter("begindate"),
                    request.getParameter("enddate"));
            FUserDetail user = ((FUserDetail) getLoginUser());
            oaarrangeweek.copyNotNullProperty(object);
            oaarrangeweek.setDjId(oaArrangeweekMgr.getNextKey());
            oaarrangeweek.setCreater(user.getUsercode());
            oaarrangeweek.setDepno(user.getPrimaryUnit());
            oaarrangeweek.setCreatedate(new Date(System.currentTimeMillis()));
            oaArrangeweekMgr.saveOaArrange(oaarrangeweek);
            object.clearProperties();
            return "regiseter";
        } else {
            result = "OK";
            String saveParams = java.net.URLDecoder.decode(
                    request.getParameter("saveParams"), "UTF-8");
            HashMap<String, Object> searchColumn = new HashMap<String, Object>();
            for (int i = 0; i < saveParams.split(";").length; i++) {
                String tmp = saveParams.split(";")[i];
                int mark = tmp.indexOf(":");
                if (StringUtils.isNotBlank(tmp.substring(mark + 1))) {
                    String key = tmp.substring(0, mark);
                    String value = tmp.substring(mark + 1);
                    if (StringUtils.isNotBlank(value)) {
                        searchColumn.put(key, value);
                    }
                }
            }
            if (searchColumn.containsKey("djId")) {
                oaarrangeweek = oaArrangeweekMgr.getObjectById(String
                        .valueOf(searchColumn.get("djId")));
            } else {
                oaarrangeweek.setDjId(oaArrangeweekMgr.getNextKey());
            }
            oaarrangeweek.setWeektitle(
                    String.valueOf(searchColumn.get("begindate")),
                    String.valueOf(searchColumn.get("enddate")));
            oaarrangeweek.setCreatetime(String.valueOf(searchColumn
                    .get("selectTime")));
            oaarrangeweek.setWeektitle(
                    String.valueOf(searchColumn.get("begindate")),
                    String.valueOf(searchColumn.get("enddate")));
            oaarrangeweek.setAttendusers(String.valueOf(searchColumn
                    .get("attendusers")));
            oaarrangeweek.setAttendleaders(String.valueOf(searchColumn
                    .get("attendleaders")));
            oaarrangeweek
                    .setAddress(String.valueOf(searchColumn.get("address")));
            oaarrangeweek.setRemark(String.valueOf(searchColumn.get("remark")));
            if (searchColumn.containsKey("depno")) {
                FUserDetail user = ((FUserDetail) getLoginUser());
                oaarrangeweek
                        .setDepno(String.valueOf(searchColumn.get("depno")));
                oaarrangeweek.setCreater(user.getUsercode());
                oaarrangeweek
                        .setCreatedate(new Date(System.currentTimeMillis()));
            }
            oaarrangeweek.setState(String.valueOf(searchColumn.get("state")));
            try {
                oaArrangeweekMgr.saveOaArrange(oaarrangeweek);
            } catch (Exception e) {
                result = "False";
            }
            return "JsonResult";
        }
    }

    public String list() {
        String listType = request.getParameter("listType");
        request.setAttribute("listType", listType);
        request.setAttribute("beginDate", DateTimeUtil.getWeekFirstDate());
        request.setAttribute("endDate", DateTimeUtil.getWeekLastDate());
        request.setAttribute("unitlist", sysUnitManager.getAllSubUnits("1"));
        if ("0".equals(listType)) {
            FUserDetail user = ((FUserDetail) getLoginUser());
            request.setAttribute("curDepno", user.getPrimaryUnit());
        }
        return LIST;
    }

    public String getWorkSummariesJsons() throws ParseException, IOException {
        HashMap<String, Object> searchColumn = new HashMap<String, Object>();
        String conditions = java.net.URLDecoder.decode(
                request.getParameter("otherparams"), "UTF-8");
        for (int i = 0; i < conditions.split(";").length; i++) {
            String tmp = conditions.split(";")[i];
            int mark = tmp.indexOf(":");
            if (StringUtils.isNotBlank(tmp.substring(mark + 1))) {
                String key = tmp.substring(0, mark);
                String value = tmp.substring(mark + 1);
                if (StringUtils.isNotBlank(value)) {
                    searchColumn.put(key, value);
                }
            }
        }
        workSummaries = oaArrangeweekMgr.getWorkSummaries(searchColumn);
        return "workSummary";
    }

    public String view() {
        object = oaArrangeweekMgr.getObjectById(request.getParameter("djId"));
        String listType = request.getParameter("listType");
        FUserDetail user = ((FUserDetail) getLoginUser());
        if ("1".equals(listType)
                || ("0".equals(listType) && !object.getDepno().equals(
                        user.getPrimaryUnit()))) {
            object.setDepname(oaArrangeweekMgr.getUnitName(object.getDepno()));
        }
        return VIEW;
    }

    public String delete() {
        result = "OK";
        try {
            oaArrangeweekMgr.deleteObjectById(request.getParameter("djId"));
        } catch (Exception e) {
            result = "False";
        }
        return "JsonResult";
    }
}
