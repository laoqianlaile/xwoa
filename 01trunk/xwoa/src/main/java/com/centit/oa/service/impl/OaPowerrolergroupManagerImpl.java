package com.centit.oa.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.dao.OaPowergroupDetailDao;
import com.centit.oa.dao.OaPowerrolergroupDao;
import com.centit.oa.dao.ZtreeDao;
import com.centit.oa.po.OaPowergroupDetail;
import com.centit.oa.po.OaPowerrolergroup;
import com.centit.oa.service.OaPowerrolergroupManager;
import com.centit.powerruntime.dao.OaUnitsLeaderDao;
import com.centit.powerruntime.po.OaUnitsLeader;
import com.centit.sys.dao.UnitInfoDao;
import com.centit.sys.dao.UserInfoDao;
import com.centit.sys.dao.UserUnitDao;
import com.centit.sys.dao.VUserUnitsDao;
import com.centit.sys.po.FUnitinfo;
import com.centit.sys.po.FUserunit;
import com.centit.sys.po.VUserUnits;
import com.centit.sys.service.CodeRepositoryUtil;

public class OaPowerrolergroupManagerImpl extends
        BaseEntityManagerImpl<OaPowerrolergroup> implements
        OaPowerrolergroupManager {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory
            .getLog(OaPowerrolergroupManager.class);

    private OaPowerrolergroupDao oaPowerrolergroupDao;
    private OaPowergroupDetailDao oaPowergroupDetailDao;
    private UnitInfoDao sysunitdao;
    private VUserUnitsDao vUserUnitsDao;
    private UserInfoDao   sysUserDao;
    private UserUnitDao   unituserDao;
    private OaUnitsLeaderDao unitsLeaderDao;
    private ZtreeDao ztreeDao;
    
    public void setOaPowerrolergroupDao(OaPowerrolergroupDao baseDao) {
        this.oaPowerrolergroupDao = baseDao;
        setBaseDao(this.oaPowerrolergroupDao);
    }
   
    /**
     * 分组定义
     */
    @Override
    public void saveObject(OaPowerrolergroup o) {
        if (!StringUtils.hasText(o.getNo())) {
            o.setNo("FZDY"
                    + oaPowerrolergroupDao.getNextKeyBySequence(
                            "S_OA_ROLERGROUP_NO", 12));
        }
        super.saveObject(o);
    }

    /**
     * 获取系统所有人员及个人分组，公共分组
     * 
     * @return
     */
    public JSONArray putAllUserTree(String usercode) {
        List<Map<String, String>> unit = new ArrayList<Map<String, String>>();
        //基本结构
        unit.addAll(setTreeBase());
        //人员结构
        unit.addAll(setUnitUser());
        //分组数据
        unit.addAll(setTreeGrouple(usercode));
        JSONArray ja = new JSONArray();
        ja.addAll(unit);
        return ja;
    }
    /**
     *获取当前人员所在部门及下级部门人员及个人分组，公共分组
     * 
     * @return
     */
    public JSONArray putSubUnitsTree(String usercode) {
        List<Map<String, String>> unit = new ArrayList<Map<String, String>>();
        //基本结构
        unit.addAll(setTreeBase());
        //人员结构
        unit.addAll(setSubUnitsUser( usercode));
        //分组数据
        unit.addAll(setTreeGrouple(usercode));
        JSONArray ja = new JSONArray();
        ja.addAll(unit);
        return ja;
    }

    
   
    /**
     *获取系统所有部门及人员
     * 
     * @return
     */
    public JSONArray putUnitsUsersTree() {
        List<Map<String, String>> unit = new ArrayList<Map<String, String>>();
       
        //人员结构
        unit.addAll(setUnitUser());
       
        JSONArray ja = new JSONArray();
        ja.addAll(unit);
        return ja;
    }
    
    /**
     *获取系统所有部门
     * 
     * @return
     */
    public JSONArray putUnitsTree() {
        List<Map<String, String>> unit = new ArrayList<Map<String, String>>();
       
        //部门结构
        unit.addAll(setUnit());
       
        JSONArray ja = new JSONArray();
        ja.addAll(unit);
        return ja;
    }
    /**
     * 获取系统所有人员
     * 
     * @param unit
     * @param usercode
     */
    public List<Map<String, String>> setUnitUser() {
        return ztreeDao.findOrgUserTree();
    }
    /**
     * 获取系统所有党员人员
     * 
     * @param unit
     * @param usercode
     */
    public List<Map<String, String>> setUnitDyUser() {
        return ztreeDao.findOrgDyUserTree();
    }
    /**
     * 获取系统所有部门
     */
    public List<Map<String, String>> setUnit() {
        //添加排序
        Map<String, Object> filterMap =new HashMap<String, Object>();
        filterMap.put("ISVALID", "T");
       
        List<FUnitinfo> units =sysunitdao.listObjects(filterMap); 
        List<Map<String, String>> unit = new ArrayList<Map<String, String>>();

        for (FUnitinfo unitinfo : units) {
            Map<String, String> m = new HashMap<String, String>();
            m.put("id", unitinfo.getUnitcode());

            // 顶级父节点如果不存在，设置为null，否则ztree找不到顶级节点
            m.put("pId",
                    "0".equals(unitinfo.getParentunit()) ? "BM" : unitinfo
                            .getParentunit());
            // 一级目录下菜单展开
            m.put("open", "0".equals(unitinfo.getParentunit()) ? "true"
                    : "false");
            m.put("name", unitinfo.getUnitname());
            
            m.put("p", "O");
            m.put("unitorder", String.valueOf(unitinfo.getUnitorder()));//部门排序
            
            m.put("choosetype", "unit");//供龚元荣做控件数据选择排序用，用完注释可删除
//            if(unitinfo.getParentunit().equalsIgnoreCase("1")){//页面显示图标
              m.put("icon", "../scripts/inputZtree/img/diy/unit.png");               
//          }
            unit.add(m);
        }

        return unit;
    }
    
    /**
     * 获取当前人员所在部门及下级部门人员
     * 
     * @param unit
     * @param usercode
     */
    public List<Map<String, String>> setSubUnitsUser( String usercode) {
        List<Map<String, String>> unit = new ArrayList<Map<String, String>>();
        FUserunit dept = sysunitdao.getUserPrimaryUnit(usercode);
        String sParentUnit = dept.getUnitcode();
        List<FUnitinfo> units = sysunitdao.getAllSubUnits(sParentUnit);
        CodeRepositoryUtil.getSortedSubUnits(sParentUnit,null);
        if (null != units) {
            // 包含下級部門
            for (FUnitinfo unitinfo : units) {
                Map<String, String> m = new HashMap<String, String>();
                m.put("id", unitinfo.getUnitcode());
                m.put("pId", sParentUnit.equals(unitinfo.getUnitcode()) ? "BM"
                        : unitinfo.getParentunit());
                m.put("name", unitinfo.getUnitname());
                m.put("open", "true");
                m.put("p", "true");
                m.put("icon", "../scripts/inputZtree/img/diy/unit.png");
                m.put("unitorder", String.valueOf(unitinfo.getUnitorder()));//部门排序
                m.put("type", "false");// 部门不可被选中
                unit.add(m);
            }
        }

        if (null != units) {
            // 包含下級部門
            for (FUnitinfo unitinfo : units) {
                List<VUserUnits> users = vUserUnitsDao.getUnitUsers(unitinfo
                        .getUnitcode());
                // 當前部門下所有人員
                if (null != users) {
                    for (VUserUnits userinfo : users) {
                        Map<String, String> musers = new HashMap<String, String>();
                        musers.put("id", userinfo.getUserCode());
                        musers.put("pId", userinfo.getUnitCode());
                        musers.put("name", userinfo.getUserName());
                        musers.put("p", "false");
                        musers.put("icon", "../scripts/inputZtree/img/diy/person.png");
                        musers.put("userorder", String.valueOf(userinfo.getUserOrder()));//人员排序
                        unit.add(musers);
                    }

                }
            }

        }
        return unit;
    }
    
    /**
     * 获取部门及下级部门人员
     * 
     * @param unit
     * @param usercode
     */
    public List<Map<String, String>> setSubUnitsUserByUnitCode( String unitCode) {
        List<Map<String, String>> unit = new ArrayList<Map<String, String>>();
      
        List<FUnitinfo> units = sysunitdao.getAllSubUnits(unitCode);
        CodeRepositoryUtil.getSortedSubUnits(unitCode,null);
        if (null != units) {
            // 包含下級部門
            for (FUnitinfo unitinfo : units) {
                Map<String, String> m = new HashMap<String, String>();
                m.put("id", unitinfo.getUnitcode());
                m.put("pId", unitCode.equals(unitinfo.getUnitcode()) ? "BM"
                        : unitinfo.getParentunit());
                m.put("name", unitinfo.getUnitname());
                m.put("open",  unitCode.equals(unitinfo.getUnitcode())?"true":"false");
                m.put("icon", "../scripts/inputZtree/img/diy/unit.png");
                m.put("unitorder", String.valueOf(unitinfo.getUnitorder()));//部门排序
                m.put("type", "false");// 部门不可被选中
                unit.add(m);
            }
        }

        if (null != units) {
            // 包含下級部門
            for (FUnitinfo unitinfo : units) {
                List<VUserUnits> users = vUserUnitsDao.getUnitUsers(unitinfo
                        .getUnitcode());
                // 當前部門下所有人員
                if (null != users) {
                    for (VUserUnits userinfo : users) {
                        Map<String, String> musers = new HashMap<String, String>();
                        musers.put("id", userinfo.getUserCode());
                        musers.put("pId", userinfo.getUnitCode());
                        musers.put("name", userinfo.getUserName());
                        musers.put("icon", "../scripts/inputZtree/img/diy/person.png");
                        musers.put("userorder", String.valueOf(userinfo.getUserOrder()));//人员排序
                        unit.add(musers);
                    }

                }
            }

        }
        return unit;
    }
    /**
     * 分组数据
     * 
     * @param unit
     */
    public List<Map<String, String>> setTreeGrouple(String usercode) {
        List<Map<String, String>> unit = new ArrayList<Map<String, String>>();
        // 公共分组
        List<OaPowerrolergroup> publicGroups = oaPowerrolergroupDao
                .listObjects(
                        "From OaPowerrolergroup t where t.state='T' and t.groupType='2' ",
                        new HashMap<String, Object>());
        // 个人分组
        String sql = "From OaPowerrolergroup t where t.state='T' and t.groupType='1' and creater= "
                + usercode;
        List<OaPowerrolergroup> persionGroups = oaPowerrolergroupDao
                .listObjects(sql, new HashMap<String, Object>());
        // 与当前人员有关的全部分组详情
        List<OaPowergroupDetail> publicGroupDetails = oaPowergroupDetailDao
                .getDetailsByUsercode(usercode);

        if (null != publicGroups) {
            // 公共分组
            for (OaPowerrolergroup publicGroup : publicGroups) {
                Map<String, String> m = new HashMap<String, String>();
                m.put("id", publicGroup.getNo());
                m.put("name", publicGroup.getGroupName());
                m.put("pId", "GG");
                m.put("open", "true");
                m.put("p", "true");
                m.put("type", "false");// 部门不可被选中
                unit.add(m);
            }
        }

        if (null != persionGroups) {
            // 个人分组
            for (OaPowerrolergroup persionGroup : persionGroups) {
                Map<String, String> m = new HashMap<String, String>();
                m.put("id", persionGroup.getNo());
                m.put("name", persionGroup.getGroupName());
                m.put("pId", "GR");
                m.put("open", "true");
                m.put("p", "true");
                m.put("type", "false");// 部门不可被选中
                unit.add(m);
            }
        }
        // 分组详情
        for (OaPowergroupDetail publicGroupDetail : publicGroupDetails) {
            Map<String, String> m = new HashMap<String, String>();
            m.put("id", publicGroupDetail.getUsercode());

          /*  if (!StringUtils.hasText(publicGroupDetail.getNo())) {
            }*/
            m.put("pId", publicGroupDetail.getNo());
            m.put("name",
                    CodeRepositoryUtil.getValue("usercode",
                            publicGroupDetail.getUsercode()));
            m.put("p", "false");

            unit.add(m);
        }
        return unit;
    }
    /**
     * 基本结构
     * 
     * @param unit
     */
    public List<Map<String, String>> setTreeBase() {
        List<Map<String, String>> unit = new ArrayList<Map<String, String>>();
        Map<String, String> temp = new HashMap<String, String>();
        temp.put("id", "FZ");
        temp.put("name", "分组");
        temp.put("open", "true");
        temp.put("p", "true");
        temp.put("type", "false");// 部门不可被选中
        unit.add(temp);
        // GR:个人 GG:公共 BM:部门
        Map<String, String> temp1 = new HashMap<String, String>();
        temp1.put("id", "GG");
        temp1.put("name", "公共分组");
        temp1.put("open", "false");
        temp1.put("p", "true");
        temp1.put("pId", "FZ");
        temp1.put("type", "false");// 部门不可被选中
        unit.add(temp1);
        Map<String, String> temp2 = new HashMap<String, String>();
        temp2.put("id", "BM");
        temp2.put("name", "机构成员");
        temp2.put("open", "false");
        temp2.put("p", "true");
        temp2.put("pId", "FZ");
        temp2.put("type", "false");// 部门不可被选中
        unit.add(temp2);
        Map<String, String> temp3 = new HashMap<String, String>();
        temp3.put("id", "GR");
        temp3.put("name", "个人分组");
        temp3.put("open", "false");
        temp3.put("p", "true");
        temp3.put("pId", "FZ");
        temp3.put("type", "false");// 部门不可被选中
        unit.add(temp3);
        return unit;

    }



  

//    public void setSysUserDao(UserInfoDao dao) {
//        this.sysuserDao = dao;
//    }

    // public UserUnitDao getUnituserDao() {
    // return unituserDao;
    // }
    //
    // public void setUnituserDao(UserUnitDao unituserDao) {
    // this.unituserDao = unituserDao;
    // }
    public void setSysUserDao(UserInfoDao dao) {
        this.sysUserDao = dao;
    }
    public void setUnituserDao(UserUnitDao userunitdao) {
        this.unituserDao = userunitdao;
    }
    public OaPowergroupDetailDao getOaPowergroupDetailDao() {
        return oaPowergroupDetailDao;
    }

    public void setOaPowergroupDetailDao(
            OaPowergroupDetailDao oaPowergroupDetailDao) {
        this.oaPowergroupDetailDao = oaPowergroupDetailDao;
    }

    public UnitInfoDao getSysunitdao() {
        return sysunitdao;
    }

    public void setSysunitdao(UnitInfoDao sysunitdao) {
        this.sysunitdao = sysunitdao;
    }

    public VUserUnitsDao getvUserUnitsDao() {
        return vUserUnitsDao;
    }

    public void setvUserUnitsDao(VUserUnitsDao vUserUnitsDao) {
        this.vUserUnitsDao = vUserUnitsDao;
    }

    public void setUnitsLeaderDao(OaUnitsLeaderDao unitsLeaderDao) {
        this.unitsLeaderDao = unitsLeaderDao;
    }

    public void setZtreeDao(ZtreeDao ztreeDao) {
        this.ztreeDao = ztreeDao;
    }

    @Override
    public List<Map<String, String>> putSubUnits(String unitcode) {
        List<Map<String, String>> unit = new ArrayList<Map<String, String>>();
        
        List<FUnitinfo> units =  sysunitdao.getAllSubUnits(unitcode);
        if (null != units) {
            // 包含下級部門
            for (FUnitinfo unitinfo : units) {
                Map<String, String> m = new HashMap<String, String>();
                m.put("id", unitinfo.getUnitcode());
                m.put("pId", unitcode.equals(unitinfo.getUnitcode()) ? "BM"
                        : unitinfo.getParentunit());
                m.put("name", unitinfo.getUnitname());
                m.put("open",  unitcode.equals(unitinfo.getUnitcode())?"true":"false");
                m.put("icon", "../scripts/inputZtree/img/diy/unit.png");
                m.put("unitorder", String.valueOf(unitinfo.getUnitorder()));//部门排序
                m.put("type", "false");// 部门不可被选中
                unit.add(m);
            }
        }
        return unit;
    }
    
    /**
     * 将按照指定岗位分类的人员转成JsonArray格式
     */
    public JSONArray getStationtUsersTree(String[] stationsChosen) {
        List<Map<String, String>> stations = new ArrayList<Map<String, String>>();
       
        //人员结构
        stations.addAll(getStationUsers(stationsChosen));
       
        JSONArray ja = new JSONArray();
        ja.addAll(stations);
        return ja;
    }
    
    public List<Map<String, String>> getStationUsers(String[] stationsChosen) {
        Map<String, Object> filterMap =new HashMap<String, Object>();
        filterMap.put("isprimary", "T");
        
        List<Object[]> stationsInuser = unituserDao.getAllStationsInUse(stationsChosen);
        List<FUserunit> users = unituserDao.listObjects(filterMap);
        List<Map<String, String>> unit = new ArrayList<Map<String, String>>();

        for (Object[] station : stationsInuser) {
            
            Map<String, String> m = new HashMap<String, String>();
            m.put("id", station[0].toString());

            m.put("pId", null);
            // 一级目录下菜单展开
            m.put("open", "false");
            
            m.put("name", station[1].toString());
            m.put("type", "false");// 不可被选中
            m.put("p", "O");

           // m.put("unitorder", station[2]);//排序
            
            m.put("choosetype", "station");
            //m.put("icon", "../scripts/inputZtree/img/diy/unit.png");               
            unit.add(m);
        }

        for (FUserunit userunit : users) {
            Map<String, String> m = new HashMap<String, String>();
            m.put("id", userunit.getUsercode());

            m.put("pId", userunit.getUserstation());
            m.put("name", CodeRepositoryUtil.getValue("usercode", userunit.getUsercode()));
           // m.put("userorder", String.valueOf(userunit.getUserorder()));//人员排序
            
            m.put("choosetype", "stationUser");
          //  m.put("icon", "../scripts/inputZtree/img/diy/person.png");
            m.put("p", "C");

            unit.add(m);
        }
        return unit;
    }
    
    /**
     * 将按照分管领导分类的人员转成JsonArray格式
     */
    public JSONArray getUnitLeaderUsersTree() {
        List<Map<String, String>> unitLeaderUsers = new ArrayList<Map<String, String>>();
       
        //人员结构
        unitLeaderUsers.addAll(getUnitLeaderUsers());
       
        JSONArray ja = new JSONArray();
        ja.addAll(unitLeaderUsers);
        return ja;
    }
    
    public List<Map<String, String>> getUnitLeaderUsers(){
      //添加排序
        Map<String, Object> filterMap =new HashMap<String, Object>();
        filterMap.put("isuse", "T");

        List<OaUnitsLeader> unitLeaders = unitsLeaderDao.listObjects(filterMap);
        filterMap.clear();
        filterMap.put("isprimary", "T");
        List<FUserunit> users = unituserDao.listObjects(filterMap);
        List<Map<String, String>> unit = new ArrayList<Map<String, String>>();

        for (OaUnitsLeader leader : unitLeaders) {
            Map<String, String> m = new HashMap<String, String>();
            m.put("id", leader.getLeadercode() + "l");

            m.put("pId", null);
            // 一级目录下菜单展开
            m.put("open", "false");
            
            m.put("name", CodeRepositoryUtil.getValue("usercode", leader.getLeadercode()));
            m.put("type", "false");// 不可被选中
            m.put("p", "O");

            //m.put("unitorder", CodeRepositoryUtil.getDataPiece("StationType",station).getExtracode2());//排序
            
            m.put("choosetype", "unitLeader");
            //m.put("icon", "../scripts/inputZtree/img/diy/unit.png");               
            unit.add(m);
            
            String[] units = leader.getUnitcodes().split(",");
            
            for(int i = 0; i < units.length; i++){
                Map<String, String> map = new HashMap<String, String>();
                map.put("id", units[i]);
                map.put("pId", leader.getLeadercode() + "l");
                map.put("open", "false");
                map.put("name", CodeRepositoryUtil.getValue("unitcode", units[i]));
                map.put("type", "false");
                map.put("choosetype", "leaderUnit");
                unit.add(map);
            }
            
        }

        for (FUserunit userunit : users) {
            Map<String, String> m = new HashMap<String, String>();
            m.put("id", userunit.getUsercode());

            m.put("pId", userunit.getUnitcode());
            m.put("name", CodeRepositoryUtil.getValue("usercode", userunit.getUsercode()));
           // m.put("userorder", String.valueOf(userunit.getUserorder()));//人员排序
            
            m.put("choosetype", "unitLeaderUser");
          //  m.put("icon", "../scripts/inputZtree/img/diy/person.png");
            m.put("p", "C");

            unit.add(m);
        }
        return unit;
    }
    
    /**
     * 获取当前部门的所有下级部门（包含当前部门）并转换成JsonArray格式
     * @param unitcode 当前部门编码
     */
    @Override
    public JSONArray getSubUnits(String unitcode) {
        List<Map<String, String>> unit = new ArrayList<Map<String, String>>();
        
        List<FUnitinfo> units =  sysunitdao.getAllSubUnits(unitcode);
        if (null != units) {
            // 包含下級部門
            for (FUnitinfo unitinfo : units) {
                Map<String, String> m = new HashMap<String, String>();
                m.put("id", unitinfo.getUnitcode());
                m.put("pId", unitcode.equals(unitinfo.getUnitcode()) ? unitcode
                        : unitinfo.getParentunit());
                m.put("name", unitinfo.getUnitname());
                m.put("open",  unitcode.equals(unitinfo.getUnitcode())?"true":"false");
                m.put("icon", "../scripts/inputZtree/img/diy/unit.png");
                m.put("unitorder", String.valueOf(unitinfo.getUnitorder()));//部门排序
                m.put("type", "false");// 部门不可被选中
                unit.add(m);
            }
        }
        JSONArray ja = new JSONArray();
        ja.addAll(unit);
        return ja; 
    }
    
    /**
     * 获取当前部门的所有下级部门（包含当前部门）并转换成JsonArray格式
     * 发文分级配置获取部门
     * @param unitcode 当前部门编码
     */
    @Override
    public JSONArray getSubUnitsFWFJ(String unitcode) {
        List<Map<String, String>> unit = new ArrayList<Map<String, String>>();
        
        List<FUnitinfo> units =  sysunitdao.getAllSubUnitsFWFJ(unitcode);
        if (null != units) {
            // 包含下級部門
            for (FUnitinfo unitinfo : units) {
                Map<String, String> m = new HashMap<String, String>();
                m.put("id", unitinfo.getUnitcode());
                m.put("pId", unitcode.equals(unitinfo.getUnitcode()) ? unitcode
                        : unitinfo.getParentunit());
                m.put("name", unitinfo.getUnitname());
                m.put("open",  unitcode.equals(unitinfo.getUnitcode())?"true":"false");
                m.put("icon", "../scripts/inputZtree/img/diy/unit.png");
                m.put("unitorder", String.valueOf(unitinfo.getUnitorder()));//部门排序
                m.put("type", "false");// 部门不可被选中
                unit.add(m);
            }
        }
        JSONArray ja = new JSONArray();
        ja.addAll(unit);
        return ja; 
    }

    @Override
    public JSONArray getDyUnitsUsersTree() {
        List<Map<String, String>> unit = new ArrayList<Map<String, String>>();
        
        //人员结构
        unit.addAll(setUnitDyUser());
       
        JSONArray ja = new JSONArray();
        ja.addAll(unit);
        return ja;
    }
    /**
     * 获取系统所有人员List
     * 
     * @param usercode
     * @return
     */
    public List<Map<String, String>> setCommonUser(String loginName){
        return ztreeDao.findCommonUserTree(loginName);
    }
    /**
     * 获取系统所有人员
     * 
     * @param unit
     * @param usercode
     */
    public List<Map<String, String>> setRankUser() {
        return ztreeDao.findRankUserTree();
    }
}
