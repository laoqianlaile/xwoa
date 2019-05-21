package com.centit.sys.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.HQLUtils;
import com.centit.core.dao.HqlAndParams;
import com.centit.core.dao.SQLQueryCallBack;
import com.centit.core.service.ObjectException;
import com.centit.core.utils.PageDesc;
import com.centit.sys.po.FUserForgetPassword;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.po.FUserunit;
import com.centit.sys.security.FUserDetail;

public class UserInfoDao extends BaseDaoImpl<FUserinfo> {
    private static final long serialVersionUID = 1L;

    public boolean checkIfUserExists(FUserinfo user) {
        long hasExist = 0l;
        String hql;

        if (StringUtils.isNotBlank(user.getUsercode())) {
            hql = "SELECT COUNT(*) FROM FUserinfo WHERE usercode = "
                    + HQLUtils.buildHqlStringForSQL(user.getUsercode());
            hasExist = getSingleIntByHql(hql);
        }

        hql = "SELECT COUNT(*) FROM FUserinfo WHERE loginname = "
                + HQLUtils.buildHqlStringForSQL(user.getLoginname());

        if (StringUtils.isNotBlank(user.getUsercode())) {
            hql += " AND usercode <> "
                    + HQLUtils.buildHqlStringForSQL(user.getUsercode());
        }
        long size = getSingleIntByHql(hql);
        if (size >= 1) {
            throw new ObjectException("登录名：" + user.getLoginname() + " 已存在!!!");
        }

        return hasExist > 0L;
    }

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();
            filterField.put("USERCODE", CodeBook.EQUAL_HQL_ID);
            filterField.put("USERNAME", CodeBook.LIKE_HQL_ID);
            filterField.put("ISVALID", CodeBook.EQUAL_HQL_ID);
            filterField.put("LOGINNAME", CodeBook.LIKE_HQL_ID);
            filterField.put("USERSTATE", CodeBook.EQUAL_HQL_ID);
            filterField.put("USERORDER", CodeBook.EQUAL_HQL_ID);
            filterField.put("datasource", CodeBook.EQUAL_HQL_ID);
            filterField.put("userkind", CodeBook.EQUAL_HQL_ID);
            // filterField.put("", value);
            filterField
                    .put("byUnderUnit",
                            "usercode in ( select  usercode from f_userunit where unitcode = ? ) ");

            filterField
                    .put("queryByUnit",
                            "usercode in ( select  id.usercode from FUserunit where unitcode in (select unitcode from VHiunitinfo where topunitcode = ?) ) ");
            filterField
                    .put("queryByGW",
                            "usercode in ( select  id.usercode from FUserunit where id.userstation = ? )");
            filterField
                    .put("queryByXZ",
                            "usercode in ( select  id.usercode from FUserunit where id.userrank = ? )");
            filterField
                    .put("queryUnderUnit",
                            " usercode in ( select usercode from f_userunit  where unitcode in ( select unitcode from f_unitinfo connect by prior unitcode = parentunit start with unitcode= ? ) ) ");

            filterField
                    .put("queryByRole",
                            "usercode in (select r.id.usercode from FUserrole r, FRoleinfo i where r.id.rolecode = ? and (r.secededate is null or r.secededate > sysdate) and r.id.rolecode = i.rolecode and i.isvalid = 'T')");

            filterField
                    .put("queryByIsDeptT",
                            " usercode in (select distinct usercode from f_userunit  where  unitcode in (select unitcode from f_unitinfo  where unittype=?) )");
            filterField
                    .put("queryByIsDeptS",
                            " usercode in (select  usercode from f_userunit  where unitcode=? group by usercode) ");

            filterField.put(CodeBook.ORDER_BY_HQL_ID, " userorder asc  ,sortid asc  ");
        }
        return filterField;
    }

    public String getNextKey() {
        return getNextKeyByHqlStrOfMax("usercode",
                "FUserinfo WHERE usercode !='U0000000'", 7);
    }

    @Override
    public void saveObject(FUserinfo o) {
        if (!org.springframework.util.StringUtils.hasText(o.getUsercode())) {
            // o.setUsercode("u" + this.getNextKey());
            o.setUsercode(this.getNextKey());
        }

        // 无密码，初始化密码
        if (!org.springframework.util.StringUtils.hasText(o.getUserpin())) {
            o.setUserpin(new Md5PasswordEncoder().encodePassword("8888",
                    o.getUsercode()));
        }

        super.saveObject(o);
    }

    @SuppressWarnings("unchecked")
    public UserDetails loadUserByLoginname(String loginname)
            throws UsernameNotFoundException {
        if (StringUtils.isEmpty(loginname))
            throw new UsernameNotFoundException("loginname is null...");
        List<FUserinfo> l = null;
        try {
            l = getHibernateTemplate()
                    .find("FROM FUserinfo WHERE loginname = ? or usercode = ? or regemail = ? or username=?",
                            (Object[]) new String[] { loginname, loginname,
                                    loginname,loginname });
            // log.info(l.get(0));
        } catch (Exception e) {
            throw new UsernameNotFoundException("user not found...");
        }
        if (l != null && l.size() != 0) {
            // FUserDetail userDetail = new FUserDetail(l.get(0));
            return new FUserDetail(l.get(0));
        } else {
            log.error("user '" + loginname + "' not found...");
            throw new UsernameNotFoundException("user '" + loginname
                    + "' not found...");
        }
    }

    /*
     * public FUserinfo loginUser(String userName, String password) { return
     * (FUserinfo) getHibernateTemplate().find(
     * "FROM FUserinfo WHERE username = ? AND userpin = ? ", new Object[] {
     * userName, password }).get(0); }
     */

    public void deleteOtherPrimaryUnit(FUserunit object) {
        doExecuteHql(
                "update FUserunit set isPrimary='F' where id.usercode = ? and (id.unitcode <> ? or id.userstation <> ? or id.userrank <> ?) and isPrimary='T'",
                new Object[] { object.getUsercode(), object.getUnitcode(),
                        object.getUserstation(), object.getUserrank() });

    }
    /**
     * 通过登录名获取用户基本信息
     * @param filterMap
     * @return
     */
    @SuppressWarnings("unchecked")
    public FUserinfo getFUserInfoByLoginname(String loginname) {
        List<FUserinfo> list=new ArrayList<FUserinfo>();
        if(StringUtils.isNotBlank(loginname)){
            String hsql = "select f from  FUserinfo f where  f.loginname = ?";
            list=this.getHibernateTemplate().find(hsql,new Object[]{loginname});
        }
        if(list!=null && list.size()>0){
            System.out.println(list.get(0).toString());
            return list.get(0);
        }else{
            return null;
        }
    }
    @SuppressWarnings("unchecked")
    public List<FUserinfo> listUnderUnit(Map<String, Object> filterMap) {
        String shql = "from f_userinfo where 1=1 ";
        HqlAndParams hql = builderHqlAndParams(shql, filterMap);
        String hql1 = "select *  " + hql.getHql();
        System.out.println(1);
        List<FUserinfo> l = null;
        try {

            l = getHibernateTemplate()
                    .executeFind(
                            new SQLQueryCallBack(hql1, hql.getParams(),
                                    FUserinfo.class));
        } catch (Exception e) {
            log.error(e.getMessage());
            // return null;
        }

        return l;
    }

    @SuppressWarnings("unchecked")
    public List<FUserinfo> listUnderUnit(Map<String, Object> filterMap,
            PageDesc pageDesc) {

        String shql = "from f_userinfo where 1=1 ";
        HqlAndParams hql = builderHqlAndParams(shql, filterMap);
        String hql1 = "select *  " + hql.getHql();
        String hql2 = "select count(*)  " + hql.getHql();
        System.out.println(2);
        int startPos = 0;
        int maxSize;
        startPos = pageDesc.getRowStart();
        maxSize = pageDesc.getPageSize();

        List<FUserinfo> l = null;
        try {

            l = getHibernateTemplate().executeFind(
                    new SQLQueryCallBack(hql1, hql.getParams(), startPos,
                            maxSize, FUserinfo.class));
            pageDesc.setTotalRows(Integer.valueOf(getHibernateTemplate()
                    .executeFind(new SQLQueryCallBack(hql2, hql.getParams()))
                    .get(0).toString()));
        } catch (Exception e) {
            log.error(e.getMessage());
            // return null;
        }

        return l;
    }

    // 查询出部门下面的人员
    @SuppressWarnings("unchecked")
    public List<FUserinfo> getUserUnderUserUnit(Map<String, Object> filterMap,
            String unitcode) {
        String queryUnderUnit = (String) filterMap.get("queryUnderUnit");
        String shql = "";
        if ("true".equals(queryUnderUnit)) {
            shql = "select * from f_userinfo where usercode in ( select distinct usercode from f_userunit  where  usercode<>'0' and usercode<>'99999999')";
        } else {
            shql = "select * from f_userinfo where usercode in ( select distinct usercode from f_userunit  where unitcode = "
                    + unitcode
                    + " and usercode<>'0' and usercode<>'99999999')";
        }
        
        filterMap.remove("queryUnderUnit");
        filterMap.remove("isvalid");
        HqlAndParams hql = builderHqlAndParams(shql, filterMap);
        String hql1=hql.getHql();
        
        
        List<FUserinfo> l = null;
        try {
            l = getHibernateTemplate().executeFind(
                    new SQLQueryCallBack(hql1,hql.getParams(),FUserinfo.class));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return l;
    }

    @SuppressWarnings("unchecked")
    public List<FUserinfo> getUserUnderUnit(String unitcode) {
        String shql = "select * from f_userinfo where usercode in ( select usercode from f_userunit  where unitcode in ( select unitcode from f_unitinfo connect by prior unitcode = parentunit start with unitcode = "
                + unitcode + " ) )";
        System.out.println(3);
        List<FUserinfo> l = null;
        try {
            l = getHibernateTemplate().executeFind(
                    new SQLQueryCallBack(shql, FUserinfo.class));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return l;
    }

    @SuppressWarnings("unchecked")
    public List<FUserinfo> listUserinfoByUsercodes(List<String> usercodes) {
        return super.getHibernateTemplate().findByCriteria(
                DetachedCriteria.forClass(FUserinfo.class).add(
                        Restrictions.or(Restrictions.in("usercode", usercodes),
                                Restrictions.in("loginname", usercodes))));
    }

    @SuppressWarnings("unchecked")
    public List<FUserinfo> listUserinfoByLoginname(List<String> loginnames) {
        return super.getHibernateTemplate().findByCriteria(
                DetachedCriteria.forClass(FUserinfo.class).add(
                        Restrictions.in("loginname", loginnames)));
    }

    /**
     * 批量添加或更新
     * 
     * @param userinfos
     */
    public void batchSave(List<FUserinfo> userinfos) {
        for (int i = 0; i < userinfos.size(); i++) {
            this.saveObject(userinfos.get(i));

            if (19 == i % 20) {
                super.getHibernateTemplate().flush();
                super.getHibernateTemplate().clear();
            }
        }
    }

    public void batchMerge(List<FUserinfo> userinfos) {
        for (int i = 0; i < userinfos.size(); i++) {
            getHibernateTemplate().merge(userinfos.get(i));

            if (19 == i % 20) {
                super.getHibernateTemplate().flush();
                super.getHibernateTemplate().clear();
            }
        }
    }

    // 获取系统用户列表 除去 已被事权人员关联配置的人员
    @SuppressWarnings("unchecked")
    public List<FUserinfo> listUserCodes(String itemId,
            Map<String, Object> filterMap, PageDesc pageDesc) {
        String sql = "select t.usercode from c_power_user_info t where t.item_id = "
                + HQLUtils.buildHqlStringForSQL(itemId);
        List<Object[]> list = (List<Object[]>) super.findObjectsBySql(sql);

        String shql = "";
        if (list != null && list.size() > 0) {
            int size = list.size();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < size; i++) {
                if (i > 0) {
                    sb.append(",");
                }
                sb.append("'" + list.get(i) + "'");
            }

            String params = sb.toString();
            shql = " From FUserinfo where isvalid='T' and  usercode not in ("
                    + params + ") ";
        } else {
            shql = " From FUserinfo where 1=1 and isvalid='T' ";
        }

        return super.listObjects(shql, filterMap, pageDesc);
    }
    /**
     * 获取指定部门下的人员unitcodes："001662,001703"
     * @param unitcodes
     * @return
     */
    public List<FUserinfo> getUsersByUnits(String unitcodes) {
        List<FUserinfo> list=new ArrayList<FUserinfo>();
        if(StringUtils.isNotBlank(unitcodes)){
            String hsql = "select f from  FUserinfo f,VUserUnits u where  f.usercode = u.cid.userCode and u.cid.unitCode in "
                    + HQLUtils.convertStringsByQuote(unitcodes.trim().split(",")) ;
           list=this.getHibernateTemplate().find(hsql);
        }
        return list;

    }
    /**
     * 获取指定部门下特定岗位的人员unitcodes："001662,001703"
     * @param unitcodes
     * @param userstation
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<FUserinfo> getSysUsersByRoleAndUnit(String unitcodes,String userstation) {
        List<FUserinfo> ls = null;
        if (unitcodes != null && !"".equals(unitcodes)) {
          
                ls = getHibernateTemplate()
                        .find(" select f from  FUserinfo f, FUserunit u where "
                                + " f.usercode = u.id.usercode and u.id.unitcode in "
                                + HQLUtils.convertStringsByQuote(unitcodes.trim().split(",")) 
                                + " and u.id.userstation= "
                                +HQLUtils.buildHqlStringForSQL(userstation));
          
        }
        return ls;
    }
    /**
     * 依据联系方式获取用户信息
     * @param telephone
     * @return
     */
    public  List<FUserinfo> getUserbByPhone(String telephone) {
        List<FUserinfo> list=new ArrayList<FUserinfo>();
        if(StringUtils.isNotBlank(telephone)){
        String hsql = "select f from  FUserinfo f,OaUserinfo u where  f.usercode = u.usercode and u.mobilephone = ?";
        list=this.getHibernateTemplate().find(hsql,new Object[]{telephone});
        }
        return list;
    }
    
    
    /**
     * 依据数据来源系统获取太极用户信息
     * @return
     */
    @SuppressWarnings("unchecked")
    public  List<FUserinfo> listnew() {
        List<FUserinfo> list=new ArrayList<FUserinfo>();
        String hsql = "select c.rolecode,b.userrank,a.usercode,a.loginname,a.username,a.Isvalid,a.userdesc,a.datasource from f_userinfo a left join f_userunit b on a.usercode = b.usercode left join f_userrole c on a.usercode = c.usercode where a.datasource = '3'";
        list=this.getHibernateTemplate().find(hsql);
        return list;
    }
    

    public FUserForgetPassword createForgetPasswordInfo(String telephone) {
        // TODO Auto-generated method stub
        return null;
    }
}
