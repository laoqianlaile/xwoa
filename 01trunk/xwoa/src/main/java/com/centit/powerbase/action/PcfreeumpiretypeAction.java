package com.centit.powerbase.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.core.utils.LabelValueBean;
import com.centit.powerbase.po.Pcfreeumpiredegree;
import com.centit.powerbase.po.Pcfreeumpiretype;
import com.centit.powerbase.po.PcpunishStandard;
import com.centit.powerbase.po.Suppower;
import com.centit.powerbase.service.PcfreeumpiredegreeManager;
import com.centit.powerbase.service.PcfreeumpiretypeManager;
import com.centit.powerbase.service.PcpunishStandardManager;
import com.centit.powerbase.service.SuppowerManager;
import com.centit.workflow.FlowDescribe;
	
/**
 * 
 * 处罚种类
 * 
 * @author hx
 * @create 2012-12-12
 * @version
 */ 
public class PcfreeumpiretypeAction  extends BaseEntityExtremeAction<Pcfreeumpiretype>  implements
ServletResponseAware{
	private static final Log log = LogFactory.getLog(PcfreeumpiretypeAction.class);
	private static final long serialVersionUID = 1L;
	private PcfreeumpiretypeManager pcfreeumpiretypeMag;
	private PcpunishStandardManager pcpunishStandardManager;
	private SuppowerManager suppowerManager;
    private Suppower suppower;
	private PcpunishStandard pcpunishStandard;
	private Pcfreeumpiredegree pcfreeumpiredegree;
    private PcfreeumpiredegreeManager pcfreeumpiredegreeManager;
    private String isPcpunishStandard;//用于判断是否是处罚种类列表
    private String isEdit;//用于判断是编辑 还是新增
    private String punishTypeStr;
    private String itemId;
    private Long version;
    public void setSuppowerManager(SuppowerManager suppowerManager) {
        this.suppowerManager = suppowerManager;
    }
    public String getItemId() {
        return itemId;
    }
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Long getVersion() {
        return version;
    }
    public void setVersion(Long version) {
        this.version = version;
    }
    public List<Pcfreeumpiretype> getPcfreeumpiretypeList() {
        return PcfreeumpiretypeList;
    }
    public void setPcfreeumpiretypeList(List<Pcfreeumpiretype> pcfreeumpiretypeList) {
        PcfreeumpiretypeList = pcfreeumpiretypeList;
    }
    public List<FlowDescribe> getFlowDescribesList() {
        return flowDescribesList;
    }
    public void setFlowDescribesList(List<FlowDescribe> flowDescribesList) {
        this.flowDescribesList = flowDescribesList;
    }
    public void setPcfreeumpiretypeMag(PcfreeumpiretypeManager pcfreeumpiretypeMag) {
        this.pcfreeumpiretypeMag = pcfreeumpiretypeMag;
    }
    public PcpunishStandard getPcpunishStandard() {
        return pcpunishStandard;
    }
    public void setPcpunishStandard(PcpunishStandard pcpunishStandard) {
        this.pcpunishStandard = pcpunishStandard;
    }
    public Pcfreeumpiredegree getPcfreeumpiredegree() {
        return pcfreeumpiredegree;
    }
    public void setPcfreeumpiredegree(Pcfreeumpiredegree pcfreeumpiredegree) {
        this.pcfreeumpiredegree = pcfreeumpiredegree;
    }

    public String getIsPcpunishStandard() {
        return isPcpunishStandard;
    }
    public void setIsPcpunishStandard(String isPcpunishStandard) {
        this.isPcpunishStandard = isPcpunishStandard;
    }

    public String getIsEdit() {
        return isEdit;
    }
    public void setIsEdit(String isEdit) {
        this.isEdit = isEdit;
    }

    public String getPunishTypeStr() {
        return punishTypeStr;
    }
    public void setPunishTypeStr(String punishTypeStr) {
        this.punishTypeStr = punishTypeStr;
    }

    private List<Pcfreeumpiretype> PcfreeumpiretypeList=new ArrayList<Pcfreeumpiretype>();
    private List<FlowDescribe> flowDescribesList=new ArrayList<FlowDescribe>();

	
    public void setPcfreeumpiredegreeManager(
            PcfreeumpiredegreeManager pcfreeumpiredegreeManager) {
        this.pcfreeumpiredegreeManager = pcfreeumpiredegreeManager;
    }
    public void setPcfreeumpiretypeManager(PcfreeumpiretypeManager basemgr)
	{
		pcfreeumpiretypeMag = basemgr;
		this.setBaseEntityManager(pcfreeumpiretypeMag);
	}
    public void setPcpunishStandardManager(PcpunishStandardManager pcpunishStandardManager) {
        this.pcpunishStandardManager = pcpunishStandardManager;
    }

  
    
    
    public String save() {

        Pcfreeumpiretype pcfType = pcfreeumpiretypeMag.getObject(object);
        if("0".equals(punishTypeStr)){
            object.setPunishtype(Long.parseLong("1"));
        }
        else if ("1".equals(punishTypeStr)){
            object.setPunishtype(Long.parseLong("2"));
        }
        // isedit=1则表示编辑状态 可以直接更新 否则为新增状态 要判断库里是否存在
        if ("1".equals(isEdit)) {
            pcfreeumpiretypeMag.copyObjectNotNullProperty(pcfType, object);
            object = pcfType;
            pcfreeumpiretypeMag.saveObject(object);
            savedMessage();
        }
        if (StringUtils.isBlank(isEdit)) {
         try{
                if (pcfType != null) {
                    this.postAlertMessage("此处罚已存在！", response);
                   return null;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
                } else {
                    object.setIsinuse(Long.parseLong("1"));
                    pcfreeumpiretypeMag.saveObject(object);
                    savedMessage();

                }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            saveError(e.getMessage());
        }

        }
        return EDIT;
    }
    public String delete() 
    {
        try {
            baseEntityManager.deleteObject(object);
            deletedMessage();
            if("1".equals(isPcpunishStandard)){
            return  this.viewpcfreeumpiretype();  }
            else if("0".equals(isPcpunishStandard)){
                return  this.viewpcfreeumpiretype() ;
            }else{
                return this.edit();
            }
            } 
            catch (Exception e) {
            log.error(e.getMessage(), e);
            saveError( e.getMessage());
            return this.edit();
        }

    }

    /**
     * 获取自由裁量-处罚项目种类列表
     */
	public String list(){
	    suppower  = suppowerManager.getObjectById(itemId,version);
        pcfreeumpiredegree = pcfreeumpiredegreeManager.getObjectByItemsId(
                object.getDegreeno());  // 获取违法事实程度
        return LIST;
    }
	
	/**
	 *  1表示显示相应的处罚项目种类              0表示显示相应的违法事实列表   
	 * @return
	 */
    public String viewpcfreeumpiretype() {
        
     
        if ("1".equals(isPcpunishStandard)) {
            suppower  = suppowerManager.getObjectById(itemId,version);
            PcfreeumpiretypeList = pcfreeumpiretypeMag.listFreeumpiretype(
                    object.getDegreeno());
        }
        return VIEW;

    }
	
	/**
     * 编辑
     * @return
     */
    public String edit() {

        if (isPcpunishStandard.equals("1")) {
            // 获取处罚种类下拉列表
            @SuppressWarnings("rawtypes")
            List<Map> fdslist = pcpunishStandardManager.listPunishType(
                    itemId,version,object.getDegreeno().toString());
            this.getSelectList(flowDescribesList, fdslist, 22);
            pcfreeumpiredegree = pcfreeumpiredegreeManager.getObjectByItemsId(object.getDegreeno());  // 获取违法事实程度相关信息放到页面隐藏 用于保存
        }
      
        Pcfreeumpiretype pcfreeumpiretype = pcfreeumpiretypeMag
                .getObject(object);
        if (pcfreeumpiretype != null) {
            pcfreeumpiretypeMag.copyObjectNotNullProperty(pcfreeumpiretype,
                    object);
            object = pcfreeumpiretype;
        }

        return EDIT;
    }

	/**
	 * 是否禁止操作
	 * @return
	 */
    public String editIsInUse() {
        object = pcfreeumpiretypeMag.getObject(object);
        if (object.getIsinuse() == 1) {
            object.setIsinuse(Long.parseLong("0"));
        } else {
            object.setIsinuse(Long.parseLong("1"));
        }
        pcfreeumpiretypeMag.saveObject(object);

        return this.viewpcfreeumpiretype();
    }
	
	
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void getSelectList(List flowDescribesList,
            List<Map> fdslist, int len) {
        if (flowDescribesList == null) {
            return;
        }
        flowDescribesList.clear();

        if (fdslist != null && fdslist.size() > 0) {
            for (int i = 0; i < fdslist.size(); i++) {
                Map po = (Map) fdslist.get(i);
                String id = (String) po.get("id");
                String value = (String) po.get("value");
                if (value.length() > len) {
                    value = value.substring(0, len) + "...";
                }
                flowDescribesList.add(new LabelValueBean(value, id));
            }
        }

    }

    @SuppressWarnings({ "unchecked", "rawtypes", "unused" })
    private void getFreeumpiretypeSelectList(List flowDescribesList,
            List<Map> fdslist, int len) {
        if (flowDescribesList == null) {
            return;
        }
        flowDescribesList.clear();

        if (fdslist != null && fdslist.size() > 0) {
            for (int i = 0; i < fdslist.size(); i++) {
                Map po = (Map) fdslist.get(i);
                String id = String.valueOf(po.get("id"));
                String value = (String) po.get("value");
                if (value.length() > len) {
                    value = value.substring(0, len) + "...";
                }
                flowDescribesList.add(new LabelValueBean(value, id));
            }
        }

    }

    /**
     * 弹出提示信息
     * 
     * @param msg
     * @param response
     */
    HttpServletResponse response; 
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }
    protected void postAlertMessage(String msg, HttpServletResponse response) {

        String alertCoding = "GBK";

        ServletOutputStream sos;
        String str = "<script language=\"JavaScript\""
                + " type=\"text/JavaScript\" charset=\"" + alertCoding + "\">"
                + "javascript:alert('" + msg + "');history.back(-1);"
                + " </script>";

        response.setContentType("text/html; charset=" + alertCoding);
        try {
            sos = response.getOutputStream();
//            int strSize = (int) str.length();
            byte[] b = str.getBytes();
            sos.write(b);
            sos.flush();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.response = response;

    }
    public Suppower getSuppower() {
        return suppower;
    }
    public void setSuppower(Suppower suppower) {
        this.suppower = suppower;
    }

}
