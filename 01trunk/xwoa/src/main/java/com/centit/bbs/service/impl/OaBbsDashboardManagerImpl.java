package com.centit.bbs.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.po.AddressBookRelection;
import com.centit.oa.service.AddressBookRelectionManager;
import com.centit.support.utils.DatetimeOpt;
import com.centit.bbs.po.OaBbsDashboard;
import com.centit.bbs.po.OaBbsDiscussion;
import com.centit.bbs.dao.OaBbsDashboardDao;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import com.centit.bbs.service.OaBbsDashboardManager;
import com.centit.bbs.service.OaBbsDiscussionManager;

public class OaBbsDashboardManagerImpl extends BaseEntityManagerImpl<OaBbsDashboard>
	implements OaBbsDashboardManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaBbsDashboardManager.class);

	private OaBbsDashboardDao oaBbsDashboardDao ;
	
	public void setOaBbsDashboardDao(OaBbsDashboardDao baseDao)
	{
		this.oaBbsDashboardDao = baseDao;
		setBaseDao(this.oaBbsDashboardDao);
	}
	
	private AddressBookRelectionManager addressBookRelectionManager;
    public void setAddressBookRelectionManager(AddressBookRelectionManager addressBookRelectionManager) {
        this.addressBookRelectionManager = addressBookRelectionManager;
    }
    private OaBbsDiscussionManager oaBbsDiscussionManager;
    public void setOaBbsDiscussionManager(
            OaBbsDiscussionManager oaBbsDiscussionManager) {
        this.oaBbsDiscussionManager = oaBbsDiscussionManager;
    }

    private List<OaBbsDashboard>  bbsDashboardList;
	/**
	 * 获取bbs首页统计数据今日发帖、昨日发帖、帖子总数
	 * @return
	 */
    @Override
    public OaBbsDashboard getTotalSum() {
       
        return  oaBbsDashboardDao.getTotalSum();
    }
	
    /**
     * 获取序列生成的主键
     * @return
     */
    public String getNextKey(){
        
        return oaBbsDashboardDao.getNextValueOfSequence();
    }
    
    @Transactional
    public void saveObject(OaBbsDashboard o){
        if(StringUtils.isBlank(o.getLayoutcode())){
            o.setLayoutcode("PM" + this.getNextKey().substring(1));
        }
        
        // 设置大模块人员关系
        setDashboardRelations(o);
        
        super.saveObject(o);
    }
    
    /**
     * 设置大模块人员关系
     * @param o
     */
    public void setDashboardRelations(OaBbsDashboard o){
        
        if (StringUtils.isNotBlank(o.getApprovals())) {
            
            String[] approvals = o.getApprovals().split(",");
            for (int i = 0; i < approvals.length; i++) {

                // 关联表
                AddressBookRelection relation = new AddressBookRelection();
                relation.setAddrbookid(o.getLayoutcode());

                relation.setShareman(approvals[i]);
                // 类型
                relation.setBizType("BD");
                addressBookRelectionManager.saveObject(relation);
            }
        }
        
    }
    
    /**
     * 删除原先大模块与子模块管理员间的关联
     * @param o
     */
    @Transactional
    public void deleteDashboardRelations(OaBbsDashboard o){
        
        Map<String, Object> filterMap = new HashMap<String, Object>();
        filterMap.put("addrbookid", o.getLayoutcode());
        filterMap.put("bizType", "BD");
        
        List<AddressBookRelection> relations = addressBookRelectionManager.listObjects(filterMap);
        
        // 清空之前相关的人员关联记录
        if(null != relations){
            for(AddressBookRelection abr : relations){
                addressBookRelectionManager.deleteObject(abr);
            }
        }
    }
    /**
     * int hhBeg,int hhEnd
     * 比较起始时间，结束时间
     */
    @Override
    public boolean isShowTime(Date begTime, Date now, Date endTime,int hhBeg,int hhEnd) {
        int hh = DatetimeOpt.getHour(now);
        int mm = DatetimeOpt.getMinute(now);
        int ss = hh * 60 * 60 + mm * 60;// 当前时间时分秒（无秒）

     
//        int hhBeg = 0;//起始时间为空，默认为最小时间
        int mmBeg = 0;
        if(null!=begTime){
             hhBeg = DatetimeOpt.getHour(begTime);
             mmBeg = DatetimeOpt.getMinute(begTime);
        }
       
        int ssBeg = hhBeg * 60 * 60 + mmBeg * 60;

//        int hhEnd=24;//结束时间为空，默认为最大时间
        int mmEnd=60;
        if(null!=endTime){
        hhEnd = DatetimeOpt.getHour(endTime);
         mmEnd = DatetimeOpt.getMinute(endTime);
        }
        
        int ssEnd = hhEnd * 60 * 60 + mmEnd * 60;

        if (ssBeg <= ss && ss <= ssEnd) {
            return true;
        } else
            return false;
    }
    /**
     *获取当前部门的所有版面
     * 
     * @return
     */
    public JSONArray putLayoutTree(String usercode, String sParentUnit) {
        List<Map<String, String>> layoutTree = new ArrayList<Map<String, String>>();
        layoutTree.addAll(setLayoutTree(usercode, sParentUnit));
        
        JSONArray ja = new JSONArray();
        ja.addAll(layoutTree);
        return ja;
    }
    
    /**
     * 根据管理员信息获取相应模板列表
     */
    public List<OaBbsDashboard> getDashboarListByUser(String usercode, String sParentUnit) {
        //添加排序
        Map<String, Object> filterMap =new HashMap<String, Object>();
        filterMap.put("ISVALID", "T");
        filterMap.put("unitcode", sParentUnit);
        filterMap.put("approvals", usercode);
        
        filterMap.put("excludeStates", "D");
        
        return oaBbsDashboardDao.listObjects(filterMap); 
    }
    /**
     * 获取版面树
     */
    public List<Map<String, String>> setLayoutTree(String usercode, String sParentUnit) {
        //添加排序
        Map<String, Object> filterMap =new HashMap<String, Object>();
        filterMap.put("ISVALID", "T");
        filterMap.put("unitcode", sParentUnit);
        filterMap.put("approvals", usercode);
        
        filterMap.put("excludeStates", "D");//在用板块
        
        List<OaBbsDashboard> units =oaBbsDashboardDao.listObjects(filterMap); 
        List<Map<String, String>> unit = new ArrayList<Map<String, String>>();
        Map<String, String> mtemp = new HashMap<String, String>();
        mtemp.put("id","0");
        mtemp.put("open", "true");
        mtemp.put("name", "子版块信息");
        mtemp.put("icon", "../scripts/inputZtree/img/diy/unit.png");
        unit.add(mtemp);
        for (OaBbsDashboard oaBbsDashboard : units) {
            Map<String, String> m = new HashMap<String, String>();
            m.put("id", oaBbsDashboard.getLayoutcode());

            // 顶级父节点如果不存在，设置为null，否则ztree找不到顶级节点
            m.put("pId","0");
            // 一级目录下菜单展开
            m.put("open", "true");
            m.put("name", oaBbsDashboard.getLayoutname());
            m.put("unitorder", String.valueOf(oaBbsDashboard.getOrderno()));//部门排序
            
            m.put("choosetype", "unit");
              m.put("icon", "../scripts/inputZtree/img/diy/unit.png");               
            unit.add(m);
        }

        return unit;
    }

    @Override
    public List<OaBbsDashboard> getDashboardList(Map<String, Object> filterMap) {
        // 模块信息（公开+模块类型）+子模块信息
       bbsDashboardList= oaBbsDashboardDao.listObjects(filterMap);
        
       setTimeTage();
        return bbsDashboardList;
    }
    
    public void setTimeTage(){
        if(null!=bbsDashboardList&&bbsDashboardList.size()>0){
            // 设置讨论区模块开放状态
            for (OaBbsDashboard o : bbsDashboardList) {
                Date now=new Date();
                //讨论区模块开放状态优先级高于子模块开放状态
                if(null!=o&&"T".equals(o.getOpenType())&& ("F".equals(o.getIsdocontral())||"T".equals(o.getIsdocontral())&&(this.isShowTime(o.getStarttime(),now,o.getEndtime(),0,12)||this.isShowTime(o.getStarttimepm(),now,o.getEndtimepm(),12,24))))  //是否设置开发时间+判断上午下午
                { o.setIsShowTime("T");
                
                if(null!=o.getOaBbsDiscussions()){
                    for(OaBbsDiscussion d:o.getOaBbsDiscussions()){
                        // 设置子讨论区模块开放状态
                        if("T".equals(d.getOpenType())&&("F".equals(d.getIsdocontral())||"T".equals(d.getIsdocontral())&&(this.isShowTime(d.getStarttime(),now,d.getEndtime(),0,12)||this.isShowTime(d.getStarttimepm(),now,d.getEndtimepm(),12,24))))  //是否设置开发时间+判断上午下午 
                        d.setIsShowTime("T");
                      
                    }
                  } 
                }
            } 
        }
    }

    public List<OaBbsDashboard> getBbsDashboardList() {
        return bbsDashboardList;
    }

    public void setBbsDashboardList(List<OaBbsDashboard> bbsDashboardList) {
        this.bbsDashboardList = bbsDashboardList;
    }
    
    @Override
    public void deleteObject(OaBbsDashboard o) {
        OaBbsDashboard dashbord = super.getObjectById(o.getLayoutcode());
        if(dashbord != null){
            dashbord.setState("D");
            //删除子版块
            oaBbsDiscussionManager.deleteByLayoutCode(o.getLayoutcode());
        }
        deleteDashboardRelations(dashbord);
    }
}

