package com.centit.powerbase.action;
 
import java.util.ArrayList;
import java.util.List;

import com.centit.powerbase.po.Pcfreeumpiredegree;
import com.centit.powerbase.service.PcfreeumpiredegreeManager;
import com.centit.powerbase.service.SuppowerManager;
import com.centit.workflow.sample.optmodel.BaseWFEntityAction;
/**
 * 
 * 自由裁量，处罚违法程度管理
 * 
 * @author hx
 * @create 2012-12-11
 * @version
 */ 
public class PcfreeumpiredegreeAction  extends BaseWFEntityAction<Pcfreeumpiredegree>  {
	private static final long serialVersionUID = 1L;
	private PcfreeumpiredegreeManager pcfreeumpiredegreeMag;
	@SuppressWarnings("unused")
    private SuppowerManager suppowerManager;
public void setSuppowerManager(SuppowerManager suppowerManager) {
        this.suppowerManager = suppowerManager;
    }
    public void setPcfreeumpiredegreeManager(PcfreeumpiredegreeManager basemgr)
	{
		pcfreeumpiredegreeMag = basemgr;
		this.setBaseEntityManager(pcfreeumpiredegreeMag);
	}
    private List<Pcfreeumpiredegree> pcfreeumpiretypesList=new ArrayList<Pcfreeumpiredegree>();
  	
	public List<Pcfreeumpiredegree> getPcfreeumpiretypesList() {
        return pcfreeumpiretypesList;
    }
    public void setPcfreeumpiretypesList(
            List<Pcfreeumpiredegree> pcfreeumpiretypesList) {
        this.pcfreeumpiretypesList = pcfreeumpiretypesList;
    }
    public String save() {

        Pcfreeumpiredegree pcfDegree = pcfreeumpiredegreeMag.getObject(object);
        // 对象不为空 则为更新
        if (pcfDegree != null) {
            pcfreeumpiredegreeMag.copyObjectNotNullProperty(pcfDegree, object);
            object = pcfDegree;
        } else {
            // 生成违法程度序号
            object.setDegreeno(Long.parseLong(pcfreeumpiredegreeMag
                    .generateNextDegreeno()));
            object.setIsinuse(Long.parseLong("1"));// 默认是使用状态
        }
        pcfreeumpiredegreeMag.saveObject(object);
        savedMessage();

        return "edit";
    }
    public String list() {
        pcfreeumpiretypesList = pcfreeumpiredegreeMag
                .listFreeumpire(object.getItemId(),object.getVersion());

        return LIST;
    }
	
	//添加
    public String edit() {

        // 获取处罚项目名称
        Pcfreeumpiredegree pcfreeumpiredegree = pcfreeumpiredegreeMag
                .getObject(object);

        if (pcfreeumpiredegree != null) {
            pcfreeumpiredegreeMag.copyObjectNotNullProperty(pcfreeumpiredegree,
                    object);
            object = pcfreeumpiredegree;

        }

        return EDIT;
    }

       
    // 是否禁止操作
    public String editIsInUse() {
        object = pcfreeumpiredegreeMag.getObject(object);
        if (object.getIsinuse() == 1) {
            object.setIsinuse(Long.parseLong("0"));
        } else {
            object.setIsinuse(Long.parseLong("1"));
        }
        pcfreeumpiredegreeMag.saveObject(object);

        return this.list();
    }

}
