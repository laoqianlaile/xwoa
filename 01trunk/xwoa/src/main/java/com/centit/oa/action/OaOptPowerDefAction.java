package com.centit.oa.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.directwebremoting.export.Data;
import org.springframework.util.StringUtils;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.oa.po.OaOptPowerDef;
import com.centit.oa.service.OaOptPowerDefManager;
import com.centit.support.utils.Algorithm;
import com.centit.support.utils.Algorithm.ParentChild;
import com.centit.sys.po.FDatadictionary;
import com.centit.sys.po.FOptinfo;
import com.centit.sys.po.FRolepower;
import com.centit.sys.service.CodeRepositoryManager;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.service.CodeRepositoryUtilExtend;


/***
 * 
 * TODO Class description should be added
 * 和业务信息相关的权限设置    与角色权限有差异
 * eg：收发文 查看权限  和行政角色管理
 * @author lq
 * @create 2016年3月31日
 * @version
 */
public class OaOptPowerDefAction  extends BaseEntityExtremeAction<OaOptPowerDef> {


	private static final long serialVersionUID = 1L;
	private OaOptPowerDefManager oaOptPowerDefManager;
	private CodeRepositoryManager codeRepositoryManager;
	List<FDatadictionary> optinfos;//x轴
	List<FDatadictionary> optdefs ;//y轴
	private static String DocViewPowerID="DOCXZ";//收发文查看行政权限类别OaOptPowerDef
	private static String DocViewPowerRank="RankType";//收发文查看行政权限类别RankType
	
	
    private Map<String, String> powerlist;//权限操作代码列表
	public void setCodeRepositoryManager(CodeRepositoryManager codeRepositoryManager) {
		this.codeRepositoryManager = codeRepositoryManager;
	}


	public void setOaOptPowerDefManager(OaOptPowerDefManager oaOptPowerDefManager) {
		this.oaOptPowerDefManager =oaOptPowerDefManager;
		setBaseEntityManager(oaOptPowerDefManager);
	}
	
	/**
	 * 收发文查看权限设置
	 * 和行政角色关联 RankType 
	 * 1.获取行政级别
	 * 2.获取操作  OaOptPowerDef 父代码=DOCXZ（行政） 
	 */
    public String  getDocViewPower(){
        
        optinfos = CodeRepositoryUtil.getDictionary(DocViewPowerRank);//操作定义
        optdefs = CodeRepositoryUtilExtend
                .getTreeDictionaryStartBy("OaOptPowerDef", DocViewPowerID, false);//操作定义
        
        //权限数据
        powerlist = new HashMap<String, String>();
        // System.out.println(object.getRolecode());
        List<OaOptPowerDef> pls =  oaOptPowerDefManager.getOptDefByOptId(DocViewPowerID);
        for (OaOptPowerDef p : pls) {
            powerlist.put(p.getOptlevel()+p.getOptmethod(), "1");//Optlevel()=RankType（CZ）+OaOptPowerDef（DocViewAll）
        }
        
        //页面展示处理
        ParentChild<FDatadictionary> c = new  Algorithm.ParentChild<FDatadictionary>() {
            public boolean parentAndChild(FDatadictionary p,FDatadictionary c) {
                return p.getDatacode().equals(c.getExtracode());
             }
         };
        Algorithm.sortAsTree( optinfos ,c);    
        List<Integer> optIndex = Algorithm.makeJqueryTreeIndex(optinfos, c);
        ServletActionContext.getContext().put("INDEX", getOptIndex(optIndex));
        return "DocViewPower";
    }

    private String getOptIndex(List<Integer> optIndex) {
        JSONObject jo = new JSONObject();
        jo.put("indexes", optIndex);
        return jo.toString();
    } 
    
    
    /* 
     * 保存操作权限
     * 收发文查看权限设置
     * 和行政角色关联 RankType 
     * @return
     */
    public String saveDocViewPower(){
        if (!StringUtils.hasText(this.object.getOptcodelist())) {
            oaOptPowerDefManager.deleteDocViewPower(DocViewPowerID);
        }
        else{
            String[] optcodelist = this.object.getOptcodelist().split(",");
            if (0 == optcodelist.length) {
                oaOptPowerDefManager.deleteDocViewPower(DocViewPowerID);
            }
            else{
              //保存
                oaOptPowerDefManager.saveDocViewPower(initDocViewOptPowerDef(),optcodelist);
            }
        }
        codeRepositoryManager.refreshAll();//刷新oa业务数据权限
        return  getDocViewPower();
    }

    private OaOptPowerDef initDocViewOptPowerDef(){
        OaOptPowerDef  opt=new OaOptPowerDef();
        opt.setOptid(DocViewPowerID);
        opt.setOptdesc("分配收发文查看权限："+new Date());
        return opt;
    }
    public List<FDatadictionary> getOptinfos() {
        return optinfos;
    }


    public void setOptinfos(List<FDatadictionary> optinfos) {
        this.optinfos = optinfos;
    }


    public List<FDatadictionary> getOptdefs() {
        return optdefs;
    }


    public void setOptdefs(List<FDatadictionary> optdefs) {
        this.optdefs = optdefs;
    }


    public Map<String, String> getPowerlist() {
        return powerlist;
    }


    public void setPowerlist(Map<String, String> powerlist) {
        this.powerlist = powerlist;
    }
	
	
}
