package com.centit.powerbase.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.powerbase.po.Pcfreeumpiretype;
import com.centit.powerbase.po.PcpunishStandard;
import com.centit.powerbase.service.PcfreeumpiretypeManager;
import com.centit.powerbase.service.PcpunishStandardManager;
import com.centit.powerbase.service.SuppowerManager;

/**
 * 
 * 自由裁量标准
 * 
 * @author hx
 * @create 2013-9-10
 * @version
 */
public class PcpunishStandardAction  extends BaseEntityExtremeAction<PcpunishStandard>  {
	private static final long serialVersionUID = 1L;
	private PcpunishStandardManager pcpunishStandardMag;
	@SuppressWarnings("unused")
    private SuppowerManager suppowerManager;
  
    public void setSuppowerManager(SuppowerManager suppowerManager) {
        this.suppowerManager = suppowerManager;
    }

    private PcfreeumpiretypeManager pcfreeumpiretypeManager;
 
    private String punishclassname;//处罚项目名称
    private String punishclasscode;
    private String punishTypeStr;

    public String getPunishTypeStr() {
        return punishTypeStr;
    }
    public void setPunishTypeStr(String punishTypeStr) {
        this.punishTypeStr = punishTypeStr;
    }
  
    List<Pcfreeumpiretype> pcfreeumpiretypeList = new ArrayList<Pcfreeumpiretype>();
    private List<PcpunishStandard> pcpunishStandardsList = new ArrayList<PcpunishStandard>();

    public void setPcpunishStandardManager(PcpunishStandardManager basemgr) {
        pcpunishStandardMag = basemgr;
        this.setBaseEntityManager(pcpunishStandardMag);
    }

  
    public void setPcfreeumpiretypeManager(
            PcfreeumpiretypeManager pcfreeumpiretypeManager) {
        this.pcfreeumpiretypeManager = pcfreeumpiretypeManager;
    }

    public String getPunishclasscode() {
        return punishclasscode;
    }

    public void setPunishclasscode(String punishclasscode) {
        this.punishclasscode = punishclasscode;
    }


    public List<PcpunishStandard> getPcpunishStandardsList() {
        return pcpunishStandardsList;
    }

    public void setPcpunishStandardsList(List<PcpunishStandard> pcpunishStandardsList) {
        this.pcpunishStandardsList = pcpunishStandardsList;
    }

    private List<Pcfreeumpiretype> pcfreeumpiretypes;

    public List<Pcfreeumpiretype> getNewPcfreeumpiretypes() {
        return this.pcfreeumpiretypes;
    }

    public void setNewPcfreeumpiretypes(List<Pcfreeumpiretype> pcfreeumpiretypes) {
        this.pcfreeumpiretypes = pcfreeumpiretypes;
    }
    
    
     public String getPunishclassname() {
        return punishclassname;
    }
    public void setPunishclassname(String punishclassname) {
        this.punishclassname = punishclassname;
    }

    
    @Override   
    public String save(){
       if("false".equals(punishTypeStr)){
            object.setPunishtype(Long.parseLong("1"));
        }
       else{
            object.setPunishtype(Long.parseLong("2"));
        }
         PcpunishStandard pcpunishStandard = pcpunishStandardMag.getObjectByItemsId(object.getItemId(),Long.parseLong(object.getVersion()),object.getPunishtypeid()); 
        if(pcpunishStandard!=null){
            pcpunishStandardMag.copyObjectNotNullProperty(pcpunishStandard,
                    object);
            object = pcpunishStandard;    
        }
       object.setIsinuse(Long.parseLong("1"));
        pcpunishStandardMag.saveObject(object);
        savedMessage();
        
        return "edit";
    }
    public String listPcType() {
         pcpunishStandardsList=pcpunishStandardMag.listPcType(object.getItemId(),Long.parseLong(object.getVersion())); 
         
    return "viewpcpunishStandard";
    }
    
    @Override
    public String view() {
         String itemId=object.getItemId();
         String version=object.getVersion();
             object = new PcpunishStandard();
             object.setItemId(itemId);
             object.setVersion(version);
         return VIEW;
   }
    
     @Override
    public String edit() {
        // Punishtypeid为空，则为新增
        if (StringUtils.isNotBlank(object.getPunishtypeid())) {
            object = pcpunishStandardMag.getObjectByItemsId(object.getItemId(),Long.parseLong(object.getVersion()),
                    object.getPunishtypeid());

        } else {
            object.setItemId(object.getItemId());
            object.setVersion(object.getVersion());

        }
        return EDIT;
    }
     
    /**
     *  是否禁止操作
     * @return
     */
    
    public String editIsInUse(){
         object=pcpunishStandardMag.getObjectByItemsId(object.getItemId(),Long.parseLong(object.getVersion()),object.getPunishtypeid()); 
         if(object.getIsinuse()==1){
             object.setIsinuse(Long.parseLong("0"));
         }
         else{
             object.setIsinuse(Long.parseLong("1"));
         }
         pcpunishStandardMag.saveObject(object);
        
         return this.listPcType();
     }
    /**
     * 是否在禁止处罚种类时，禁止相应的自由裁量处罚种类       
     * @return
     */
    public String editFreeumpireTypeIsInUse() {
        object = pcpunishStandardMag.getObjectByItemsId(object.getItemId(),Long.parseLong(object.getVersion()),
                object.getPunishtypeid());
        if (object.getIsinuse() == 1) {
            object.setIsinuse(Long.parseLong("0"));
        } else {
           object.setIsinuse(Long.parseLong("1"));
        }
        pcpunishStandardMag.saveObject(object);

    
       pcfreeumpiretypeList = pcfreeumpiretypeManager
                .getPCFreeUmpireTypeListByClassID(
                        object.getItemId(),Long.parseLong(object.getVersion()), object.getPunishtypeid());
        if (pcfreeumpiretypeList.size() > 0) {
            for(int i=0;i<pcfreeumpiretypeList.size(); i++){
          Long degreeno=  pcfreeumpiretypeList.get(i).getDegreeno();
            pcfreeumpiretypeManager.updatepcfreeumpiretype( degreeno,
                   object.getPunishtypeid(),
                   object.getIsinuse());
            }
        }

        return this.listPcType();

    }
		
}
