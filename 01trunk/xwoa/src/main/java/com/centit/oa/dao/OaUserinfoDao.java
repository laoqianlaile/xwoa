package com.centit.oa.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.HQLUtils;
import com.centit.oa.po.OaUserinfo;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.po.FUserunit;

public class OaUserinfoDao extends BaseDaoImpl<OaUserinfo> {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(OaUserinfoDao.class);

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();

            filterField.put("usercode", CodeBook.EQUAL_HQL_ID);

            // filterField.put("username" , CodeBook.LIKE_HQL_ID);
            filterField
                    .put("username",
                            " usercode in (select usercode f_userinfo where username like ?) ");

            filterField.put("headpicturename", CodeBook.LIKE_HQL_ID);

            filterField.put("headpicture", CodeBook.LIKE_HQL_ID);

            filterField.put("sex", CodeBook.LIKE_HQL_ID);

            filterField.put("placeofbirth", CodeBook.LIKE_HQL_ID);

            filterField.put("tolive", CodeBook.LIKE_HQL_ID);

            filterField.put("telephone", CodeBook.LIKE_HQL_ID);

            filterField.put("mobilephone", CodeBook.LIKE_HQL_ID);

            filterField.put("qq", CodeBook.LIKE_HQL_ID);

            filterField.put("msn", CodeBook.LIKE_HQL_ID);

            filterField.put("email", CodeBook.LIKE_HQL_ID);

            filterField.put("school", CodeBook.LIKE_HQL_ID);

            filterField.put("record", CodeBook.LIKE_HQL_ID);

            filterField.put("documenttype", CodeBook.LIKE_HQL_ID);

            filterField.put("idno", CodeBook.LIKE_HQL_ID);

            filterField.put("mailingaddress", CodeBook.LIKE_HQL_ID);

            filterField.put("personalsignature", CodeBook.LIKE_HQL_ID);

            filterField.put("introduce", CodeBook.LIKE_HQL_ID);

            filterField.put("hobbies", CodeBook.LIKE_HQL_ID);

            filterField.put("lastmodifydate", CodeBook.LIKE_HQL_ID);

            filterField.put("createdate", CodeBook.LIKE_HQL_ID);

            filterField.put("userlevel", CodeBook.LIKE_HQL_ID);

            filterField.put("levelnum", CodeBook.LIKE_HQL_ID);

            filterField.put("remark", CodeBook.LIKE_HQL_ID);

            filterField.put("picturename", CodeBook.LIKE_HQL_ID);

            filterField.put("pictureim", CodeBook.LIKE_HQL_ID);

            filterField.put("workplace", CodeBook.LIKE_HQL_ID);

            filterField.put("birthday", CodeBook.LIKE_HQL_ID);

            filterField.put("otherlinks", CodeBook.LIKE_HQL_ID);

            // filterField.put(CodeBook.ORDER_BY_HQL_ID,
            // " userorder asc  ,sortid asc  ");

            filterField.put("party", CodeBook.EQUAL_HQL_ID);
            filterField.put("NP_party", " party='F' or party is null ");
        }
        return filterField;
    }

    public List<OaUserinfo> listOauserinfoObjects(Map<String, Object> filterMap) {
        String shql = "select o.usercode ,u.username ,o.headpicturename ,o.headpicture,o.sex,o.placeofbirth,o.tolive ,o.telephone,o.mobilephone,o.qq,o.msn,"
                + "o.email,o.school,o.record, o.documenttype,o.idno,o.mailingaddress,o.personalsignature,o.introduce,o.hobbies,o.lastmodifydate,"
                + "o.createdate,o.userlevel,o.levelnum,o.remark,o.picturename,o.pictureim,o.workplace,o.birthday,o.otherlinks,o.isusepicture,"
                + "o.isshowsex,o.isshowplaceofbirth,o.isshowtolive,o.isshowtelephone,o.isshowmobilephone,o.isshowschool,o.isshowemail,"
                + "o.isshowmsn,o.isshowqq,o.isshowintroduce,o.isshowhobbies,o.isshowidno,o.isshowrecord"
                + " from oa_userinfo o "
                + " join f_userinfo u on o.usercode=u.usercode "
                + " where 1 = 1 ";
        StringBuffer sb = new StringBuffer();
        if (StringUtils.isNotBlank((String) filterMap.get("sex"))) {
            sb.append(" and o.sex="
                    + HQLUtils.buildHqlStringForSQL((String) filterMap
                            .get("sex")));
        }
        if (StringUtils.isNotBlank((String) filterMap.get("userName"))) {
            sb.append(" and u.username like "
                    + HQLUtils.buildHqlStringForSQL("%"
                            + (String) filterMap.get("userName") + "%"));
        }
        if (StringUtils.isNotBlank((String) filterMap.get("telephone"))) {
            sb.append(" and o.telephone like "
                    + HQLUtils.buildHqlStringForSQL("%"
                            + (String) filterMap.get("telephone") + "%"));
        }
        if (StringUtils.isNotBlank((String) filterMap.get("mobilephone"))) {
            sb.append(" and o.mobilephone like "
                    + HQLUtils.buildHqlStringForSQL("%"
                            + (String) filterMap.get("mobilephone") + "%"));
        }
        if (StringUtils.isNotBlank((String) filterMap.get("usercode"))) {
            sb.append(" and o.usercode="
                    + HQLUtils.buildHqlStringForSQL((String) filterMap
                            .get("usercode")));
        }
        String sq = shql + sb.toString();
        List<Object[]> l = (List<Object[]>) findObjectsBySql(sq);
        List<OaUserinfo> oauserinfolist = new ArrayList<OaUserinfo>();
        if (l != null && l.size() > 0) {
            for (Object[] o : l) {
                OaUserinfo bean = new OaUserinfo();
                bean.setUsercode(o[0] != null ? o[0].toString() : "");
                bean.setUsername(o[1] != null ? o[1].toString() : "");
                bean.setSex(o[4] != null ? o[4].toString() : "");
                bean.setTelephone(o[7] != null ? o[7].toString() : "");
                bean.setMobilephone(o[8] != null ? o[8].toString() : "");
                bean.setWorkplace(o[27] != null ? o[27].toString() : "");
                oauserinfolist.add(bean);
            }
        }
        return oauserinfolist;
    }

    public OaUserinfo getUserInfo(String userCode) {
        OaUserinfo bean = new OaUserinfo();
        String shql = "select o.usercode ,u.username ,o.sex,,o.telephone,o.mobilephone"
                + " from oa_userinfo o where o.usercode=" + userCode;
        List<Object[]> l = (List<Object[]>) findObjectsBySql(shql);
        if (l != null && l.size() > 0) {
            for (Object[] o : l) {

                bean.setUsercode(o[0] != null ? o[0].toString() : "");
                bean.setUsername(o[1] != null ? o[1].toString() : "");
                bean.setSex(o[2] != null ? o[2].toString() : "");
                bean.setTelephone(o[3] != null ? o[3].toString() : "");
                bean.setMobilephone(o[4] != null ? o[4].toString() : "");
            }
        }
        return bean;
    }

    public List<OaUserinfo> listUserInfos(Map<String, Object> filterMap) {
        List<OaUserinfo> oaUserinfos=new ArrayList<OaUserinfo>();
        String shql = "select o.usercode,o.sex,o.mobilephone,f.unitcode,ff.userdesc from oa_userinfo o "
                + "left join V_USER_UNITS  f on f.usercode=o.usercode "
                + "left join f_userinfo  ff on ff.usercode=o.usercode "
                + "where f.unitcode is not null ";
                if(StringUtils.isNotBlank((String) filterMap.get("unitcode"))&&!"1".equals((String) filterMap.get("unitcode"))){
                    shql+=" and unitcode="+HQLUtils.buildHqlStringForSQL((String) filterMap.get("unitcode"));
                }
                if(StringUtils.isNotBlank((String) filterMap.get("party"))){
                    shql+=" and party="+HQLUtils.buildHqlStringForSQL((String) filterMap.get("party"));
                }
                if(StringUtils.isNotBlank((String) filterMap.get("NP_party"))){
                    shql+=" and (party='F' or party is null )";
                }
                shql+= " order by f.unitorder asc ,f.userorder asc,ff.sortid asc";
        List<Object[]> l = (List<Object[]>) findObjectsBySql(shql);
        if (l != null && l.size() > 0) {
            for (Object[] o : l) {
                if(!"1".equals(o[3].toString())){
                OaUserinfo userinfo=new OaUserinfo();
                FUserunit fUserunit=new FUserunit();
                FUserinfo fUserinfo=new FUserinfo();
                userinfo.setfUserunit(fUserunit);
                userinfo.setfUserinfo(fUserinfo);
                userinfo.setUsercode(o[0] != null ? o[0].toString() : "");
                userinfo.setSex(o[1] != null ? o[1].toString() : "");
                userinfo.setMobilephone(o[2] != null ? o[2].toString() : "");
                userinfo.getfUserunit().setUnitcode(o[3] != null ? o[3].toString() : "");
                userinfo.getfUserinfo().setUserdesc(o[4] != null ? o[4].toString() : "");
                oaUserinfos.add(userinfo);
                }
            }
        }
        return oaUserinfos;
    }
}
