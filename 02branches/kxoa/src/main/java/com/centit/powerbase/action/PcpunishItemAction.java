package com.centit.powerbase.action;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.extremecomponents.table.limit.Limit;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.core.utils.ExtremeTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.powerbase.po.Pcfreeumpiredegree;
import com.centit.powerbase.po.PcpunishItem;
import com.centit.powerbase.po.PcpunishStandard;
import com.centit.powerbase.service.PcpunishItemManager;

public class PcpunishItemAction  extends BaseEntityExtremeAction<PcpunishItem>  {
	private static final long serialVersionUID = 1L;
	private PcpunishItemManager pcpunishItemManager;


    private List<PcpunishStandard> pcpunishStandardsList=new ArrayList<PcpunishStandard>();
    private List<Pcfreeumpiredegree> pcfreeumpiredegrees;
    @SuppressWarnings("rawtypes")
    private List punishobjectList=new ArrayList();//获取处罚对象  0表示个人，1表示机构
    @SuppressWarnings("rawtypes")
    private List pcpunishItemList=new ArrayList();
    
    @SuppressWarnings("rawtypes")
    public List getPunishobjectList() {
        return punishobjectList;
    }
    public void setPunishobjectList(@SuppressWarnings("rawtypes") List punishobjectList) {
        this.punishobjectList = punishobjectList;
    }
    @SuppressWarnings("rawtypes")
    public List getPcpunishItemList() {
        return pcpunishItemList;
    }
    public void setPcpunishItemList(@SuppressWarnings("rawtypes") List pcpunishItemList) {
        this.pcpunishItemList = pcpunishItemList;
    }
    public void setPcpunishItemManager(PcpunishItemManager pcpunishItemManager) {
            this.pcpunishItemManager = pcpunishItemManager;
        }
    public List<PcpunishStandard> getPcpunishStandardsList() {
        return pcpunishStandardsList;
    }

    public void setPcpunishStandardsList(List<PcpunishStandard> pcpunishStandardsList) {
        this.pcpunishStandardsList = pcpunishStandardsList;
    }


    public List<Pcfreeumpiredegree> getNewPcfreeumpiredegrees() {
        return this.pcfreeumpiredegrees;
    }
    public void setNewPcfreeumpiredegrees(List<Pcfreeumpiredegree> pcfreeumpiredegrees) {
        this.pcfreeumpiredegrees = pcfreeumpiredegrees;
    }
    
    /**
     *     保存处罚项目类别
     */
    @Override   
    public String save(){
        PcpunishItem pcpunishItem = pcpunishItemManager.getObjectByItemId(object.getPunishclasscode()); 
        
        String[] punishobject_type = request.getParameterValues("punishobjectList"); 
        if(punishobject_type.length==2){
            object.setPunishobject(Long.parseLong("2"));//2表示个人和机构
        }
        else{
            object.setPunishobject(Long.parseLong(punishobject_type[0]));
        }
        //pcpunishItem不为空，则为更新
        if(pcpunishItem !=null){
            pcpunishItemManager.copyObjectNotNullProperty(pcpunishItem,
                    object);
            object = pcpunishItem;
            
        }
        else {
            // 生成处罚项目类别编号   PDM里为8位
            object.setPunishclassid(pcpunishItemManager.generateNextPunishClassID());
        }
        pcpunishItemManager.saveObject(object);
        
        
        return this.edit();
    }
    /**
     * 处罚编辑
     * @author sj
     */
    
    
    @SuppressWarnings("unchecked")
    @Override
    public String edit() {
         String punishclasscode=object.getPunishclasscode();
         String version=object.getVersion();
         String punishclassid=object.getPunishclassid();
         object = pcpunishItemManager.getObjectByItemId(punishclasscode); 
         if(object==null&&punishclasscode==null&&punishclassid!=null){
             object=pcpunishItemManager.getObjectById(punishclassid);
             
         }
         if(object==null){
             object = new PcpunishItem();
             object.setPunishclasscode(punishclasscode);
             object.setVersion(version);
             object.setItemId(punishclasscode);
             
         }
         else{
             if(object.getPunishobject()==2){
                 punishobjectList.add("0");
                 punishobjectList.add("1");
             }
             else {
                 punishobjectList.add(String.valueOf(object.getPunishobject()));
             }
         }
         return EDIT;
    }
    /**
     * 获取处罚项目类别列表
     */
    public String listPcpunishItem() {
        @SuppressWarnings("unchecked")
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        Limit limit = ExtremeTableUtils.getLimit(request);
        PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
        pcpunishItemList = pcpunishItemManager.listPcpunishItem(filterMap,pageDesc);
        totalRows = pageDesc.getTotalRows();
        
        return "listPcpunishItem";
    }
}
