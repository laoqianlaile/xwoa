package com.centit.oa.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.oa.po.OaScheduleResponse;

public class OaScheduleResponseDao extends BaseDaoImpl<OaScheduleResponse> {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory
            .getLog(OaScheduleResponseDao.class);

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();

            filterField.put("no", CodeBook.EQUAL_HQL_ID);

            filterField.put("schno", CodeBook.LIKE_HQL_ID);

            filterField.put("resType", CodeBook.LIKE_HQL_ID);

            filterField.put("usercode", CodeBook.LIKE_HQL_ID);

            filterField.put("stopTime", CodeBook.LIKE_HQL_ID);

            filterField.put("remark", CodeBook.LIKE_HQL_ID);

            filterField.put("createdate", CodeBook.LIKE_HQL_ID);

        }
        return filterField;
    }

    /**
     * 获取自己的响应
     * 
     * @param object
     * @return
     */
    @SuppressWarnings("unchecked")
    public OaScheduleResponse getOwnRes(OaScheduleResponse object) {
        String hql = "from OaScheduleResponse where usercode=? and schno=? ";
        List<OaScheduleResponse> list = (List<OaScheduleResponse>) super
                .findObjectsByHql(hql, new Object[] { object.getUsercode(),
                        object.getSchno() });
        OaScheduleResponse oaScheduleResponse = new OaScheduleResponse();
        if (list != null && list.size() > 0) {
            oaScheduleResponse = (OaScheduleResponse) list.get(0);
        }
        return oaScheduleResponse;
    }

    @SuppressWarnings("unchecked")
    public List<OaScheduleResponse> getOaScheduleResponseListByresType(
            String schno, String resType) {
        String hql = "from OaScheduleResponse where schno=? and resType=? ";
        return  (List<OaScheduleResponse>) super
                .findObjectsByHql(hql, new Object[] { schno, resType });
    }
}
