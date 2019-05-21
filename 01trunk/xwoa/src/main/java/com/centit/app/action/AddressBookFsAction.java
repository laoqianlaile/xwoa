package com.centit.app.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.centit.app.po.AddressBookFs;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.common.util.StringUtils;

import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.po.FUnitinfo;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.po.FUserunit;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.service.SysUserManager;

import com.centit.core.action.BaseEntityDwzAction;

import com.centit.app.service.AddressBookFsManager;
import com.opensymphony.xwork2.ActionContext;


public class AddressBookFsAction extends BaseEntityDwzAction<AddressBookFs> {
    private static final Log log = LogFactory.getLog(AddressBookFsAction.class);

    // private static final ISysOptLog sysOptLog =
    // SysOptLogFactoryImpl.getSysOptLog("optid");

    private static final long serialVersionUID = 1L;
    private AddressBookFsManager addressBookFsMag;
    private SysUserManager sysUserMgr;

    // 设置用户单位
    private FUserunit userUnit;
    

    // 设置用户
    private FUserinfo userInfo;

    public void setSysUserMgr(SysUserManager sysUserMgr) {
        this.sysUserMgr = sysUserMgr;
    }

    public void setAddressBookFsManager(AddressBookFsManager basemgr) {
        addressBookFsMag = basemgr;
        this.setBaseEntityManager(addressBookFsMag);
    }

    /**
     * 通讯录
     * 
     * @return
     */
    public String addressBookTree() {
        try {
            putInnermsgTree();
            return "addressBookTree";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    /**
     * 获取部门人员数据 树形结构
     * 
     * @return
     */
    public void putInnermsgTree() {
        List<FUnitinfo> units = CodeRepositoryUtil.getAllUnits("T");
        List<FUserinfo> users = CodeRepositoryUtil.getAllUsers("T");
        List<Map<String, String>> unit = new ArrayList<Map<String, String>>();

        for (FUnitinfo unitinfo : units) {
            Map<String, String> m = new HashMap<String, String>();
            m.put("id", unitinfo.getUnitcode());
            m.put("pId",
                    "0".equals(unitinfo.getParentunit()) ? null : unitinfo
                            .getParentunit());
            m.put("name", unitinfo.getUnitname());
            // 一级目录下菜单展开
            m.put("open", "0".equals(unitinfo.getParentunit()) ? "true"
                    : "false");
            m.put("p", "2");

            unit.add(m);
        }
        for (FUserinfo userinfo : users) {
            Map<String, String> m = new HashMap<String, String>();
            m.put("id", userinfo.getUsercode());
            if (StringUtils.isEmpty(userinfo.getPrimaryUnit())) {
                continue;
            }
            m.put("pId", userinfo.getPrimaryUnit());
            m.put("name", userinfo.getUsername());
            m.put("p", "1");

            unit.add(m);
        }

        JSONArray ja = new JSONArray();
        ja.addAll(unit);
        ActionContext.getContext().put("unit", ja.toString());
        totalRows = unit.size();
    }

    public String oaView() {
        oaEdit();
        String turn = null;
        if ("1".equals(object.getBodytype())) {
            turn = "oaUserView";
        } else if ("2".equals(object.getBodytype())) {
            turn = "oaUnitView";
        } else {
            turn = "oaUnitView";
        }
        return turn;
    }

    public String oaEdit() {

        try {
            putInnermsgTree();
            if (object != null) {

                AddressBookFs o = addressBookFsMag.getObjectByCode(
                        object.getBodycode(), object.getBodytype());
                
                if (o != null && o.getAddrbookid() != null) {
                    // 将对象o copy给object，object自己的属性会保留
                    baseEntityManager.copyObject(object, o);
                }
                FUserinfo dbobject = sysUserMgr.getObjectById(object
                        .getBodycode());
                if (dbobject != null) {
                    // 关联表重复字段，分别赋值
                    object.setBodycode(dbobject.getUsercode());

                    // 当新增时选定人员同时更新部门信息，编辑是直接取人员信息表中字段(主部门)
                    userUnit = (sysUserMgr.getUserPrimaryUnit(object
                            .getBodycode()));
                    if (userUnit != null) {
                        if (!StringUtils.isEmpty(userUnit.getUnitcode())) {
                            object.setDeptname(userUnit.getUnitcode());// 部门
                        }
                        if (!StringUtils.isEmpty(userUnit.getUserstation())) {
                            object.setUnitname(userUnit.getUserstation());// 单位
                        }
                        if (!StringUtils.isEmpty(userUnit.getUserrank())) {
                            object.setRankname(userUnit.getUserrank());// 职位
                        }
                    }
                }

            } else {
                object = getEntityClass().newInstance();

            }
            String turn = null;
            if ("1".equals(object.getBodytype())) {
                turn = "oaUserEdit";
            } else if ("2".equals(object.getBodytype())) {
                turn = "oaUnitEdit";
            } else {
                turn = "oaUnitEdit";
            }
            return turn;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    public String oaSave() {
        try {
            AddressBookFs dbobject = addressBookFsMag.getObject(object);
            if (dbobject == null) {
                dbobject = new AddressBookFs();
                baseEntityManager.copyObjectNotNullProperty(dbobject, object);
                Long no = addressBookFsMag.getNextLongSequence("F_ADDRESSBOOK");
                object.setAddrbookid(no);
                object.setCreatedate(DatetimeOpt.currentUtilDate());
            } else {
                dbobject.copyNotNullProperty(object);
                object = dbobject;
            }
            object.setLastmodifydate(DatetimeOpt.currentUtilDate());// 最后修改时间为当前时间
            baseEntityManager.saveObject(object);
            savedMessage();
            return "oaSave";
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            saveError(e.getMessage());
            return ERROR;
        }
    }

    public String delete() {
        super.delete();

        return "delete";
    }
}
