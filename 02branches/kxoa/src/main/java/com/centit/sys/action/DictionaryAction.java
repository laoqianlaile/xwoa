package com.centit.sys.action;


import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.extremecomponents.table.limit.Limit;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.core.dao.CodeBook;
import com.centit.core.utils.ExtremeTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.sys.po.FDatacatalog;
import com.centit.sys.po.FDatadictionary;
import com.centit.sys.po.FDatadictionaryId;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.po.FUserunit;
import com.centit.sys.service.CodeRepositoryManager;
import com.centit.sys.service.DictionaryManager;
import com.centit.sys.service.SysUnitManager;
import com.centit.sys.service.UserUnitManager;
import com.centit.sys.util.ISysOptLog;
import com.centit.sys.util.SysOptLogFactoryImpl;
import com.opensymphony.xwork2.ActionContext;

public class DictionaryAction extends BaseEntityExtremeAction<FDatacatalog> {

	private static final long serialVersionUID = 1L;
	private DictionaryManager dictManger;
	
	
	private CodeRepositoryManager codeRepo;
	private FDatacatalog catalog;
	private String[] fdesc;
	private List<FDatadictionary>  dictDetails;
	private static final ISysOptLog SYS_OPT_LOG = SysOptLogFactoryImpl.getSysOptLog("DICTSET");
	
	private Integer dc_totalRows;
	private FDatadictionaryId id;
	private FDatadictionary datadictionary;
	private  List<FDatadictionary>  rankList;
	private List<FDatadictionary>  stationList;
	private List<FUserunit> userUnits_rank;
	private List<FUserunit> userUnits_station;
	private UserUnitManager userunitManager;
	private SysUnitManager sysUnitManager;
	private String unitsJson;
	
	//岗位角色和行政角色业务的页面和数据字典共享，这里添加两个标志加以区分
	private String cdtbnm;
	private String datacode;
	
	//用于页面区分系统数据字典还是普通数据字典
	private String s_catalogstyle;
	private String s_catalogstylenot;
	private String syscatalogstyle;//"normal"普通 "sys"系统
	 
	 
	public String getDatacode() {
	        return datacode;
	    }

	    public void setDatacode(String datacode) {
	        this.datacode = datacode;
	    }
	
	public FDatacatalog getCatalog() {
		return catalog;
	}

	public void setCatalog(FDatacatalog catalog) {
		this.catalog = catalog;
	}

	public String[] getFdesc() {
		return fdesc;
	}

	public void setFdesc(String[] fdesc) {
		this.fdesc = fdesc;
	}

	public List<FDatadictionary> getDictDetails() {
		return dictDetails;
	}

	public void setDictDetails(List<FDatadictionary> dictDetails) {
		this.dictDetails = dictDetails;
	}


	public Integer getDc_totalRows() {
		return dc_totalRows;
	}

	public void setDc_totalRows(Integer dc_totalRows) {
		this.dc_totalRows = dc_totalRows;
	}

	public FDatadictionaryId getId() {
		return id;
	}

	public void setId(FDatadictionaryId id) {
		this.id = id;
	}

	public FDatadictionary getDatadictionary() {
		return datadictionary;
	}

	public void setDatadictionary(FDatadictionary datadictionary) {
		this.datadictionary = datadictionary;
	}
	
	public void setCodeRepoManager(CodeRepositoryManager crm) {
		this.codeRepo = crm;
	}
	
	public void setDictManger(DictionaryManager dm) {
		this.dictManger = dm;
		setBaseEntityManager(dm);
	}
	
	/*@Override
	protected Map<String,String> convertSearchColumn(Map<Object,Object> paramMap) {
		// log.debug("规则化前参数表：" + paramMap.toString());
		Map<String,String> map = super.convertSearchColumn(paramMap);
		//log.warn("request.getAttribute style");
		Object obj = map.get("style");

		String sAttr = obj==null?"U":obj.toString();
		//log.warn(sAttr);
		if(sAttr !=null && !sAttr.equals(""))
			map.put("CATALOGSTYLE", sAttr);

		return map;
	}*/
	
    public String refresh() {
    	
    	codeRepo.refreshAll();	  	
		return SUCCESS;
	}

    public String list() {
        sysOrNormal();
        return super.list();
    }
    
    public String getSyscatalogstyle() {
        return syscatalogstyle;
    }

    public void setSyscatalogstyle(String syscatalogstyle) {
        this.syscatalogstyle = syscatalogstyle;
    }

    @Override
    public String edit() {
        sysOrNormal();
        return super.edit();
    }

    @Override
    public String built() {
        sysOrNormal();
        return super.built();
    }
    
    @Override
    public String view() {

		try {
			
			if(null==cdtbnm||"".equals(cdtbnm))
			{FDatacatalog dbobject = dictManger.getObjectById(object.getCatalogcode());

			if (dbobject == null) {
			
				return LIST;
			}
			catalog= dbobject;
			fdesc = dictManger.getFieldsDesc(dbobject.getFielddesc(),dbobject.getCatalogtype());
			//request.setAttribute("fdesc", fdesc);
			dictDetails = dictManger.findByCdtbnm(object.getCatalogcode());
            //request.setAttribute("dictDetails", dictDetailList);
            
			}
			else{
			    
			    catalog=dictManger.getObjectById(cdtbnm);
			    fdesc = dictManger.getFieldsDesc(catalog.getFielddesc(),catalog.getCatalogtype());
			    dictDetails=dictManger.findByCdtbnm(cdtbnm);
			    
			}
			if(dictDetails!=null){
                totalRows= dictDetails.size();
                dc_totalRows= dictDetails.size();
            }
            //onInitForm(form, request, null);
            return VIEW;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
   
    @Override
    public String save() {
        sysOrNormal();
		try {
			FDatacatalog dbobject = dictManger.getObject(object);
			if (dbobject != null)
			{
				dbobject.copyNotNullProperty(object);
				object =dbobject;
			}
			try {
				dictManger.saveObject(object);
				savedMessage();
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				
				return EDIT;
			}
			
			return SUCCESS;
		} catch (Exception ee) {
			ee.printStackTrace();
			return ERROR;
		}
	}
    
    
    public void sysOrNormal(){
        if(StringUtils.equals(s_catalogstyle, "S"))
        {
            syscatalogstyle="sys";
        } 
        if(StringUtils.equals(s_catalogstylenot, "S"))
        {
           syscatalogstyle="normal";
        }
    }

    

    public String deleteDetail() {
   
    	try {  
    		 String catalogCode = request.getParameter("catalogcode");
             String dataCode = request.getParameter("datacode");
             id= new FDatadictionaryId();
             if(StringUtils.isNotBlank(catalogCode)) {
                 id.setCatalogcode(catalogCode); 
              }
             if(StringUtils.isNotBlank(dataCode) ){
                   id.setDatacode(dataCode);
             }
    		dictManger.deleteCditms(id);
    		codeRepo.refresh(id.getCatalogcode());
    		
			FDatacatalog dbobject = dictManger.getObjectById(id.getCatalogcode());

			if (dbobject == null) {
				saveError( "entity.missing");
				return LIST;
			}
			SYS_OPT_LOG.log(((FUserinfo)this.getLoginUser()).getUsercode(), id.toString(), "删除字典代码 [" + dbobject.getCatalogcode() + "]");
				
//			fdesc = dictManger.getFieldsDesc(dbobject.getFielddesc(),dbobject.getCatalogtype());
//			catalog=dbobject;
//			dictDetails =  CodeRepositoryUtil.getDataDetail(id.getCatalogcode());
//				
//			totalRows= dictDetails.size();

			
			return "deleteDetail";
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
    

    public String editDetail() {

    	try {
            String catalogCode = request.getParameter("catalogcode");
            String dataCode = request.getParameter("datacode");
            id= new FDatadictionaryId();
            if(StringUtils.isNotBlank(catalogCode)) {
                id.setCatalogcode(catalogCode);
             }
            if(StringUtils.isNotBlank(dataCode) ){
                  id.setDatacode(dataCode);
            }
            datadictionary= dictManger.findById(id);
			if (datadictionary == null) {
				datadictionary = new FDatadictionary();
				datadictionary.setId(id);
			}
			
 			catalog = dictManger.getObjectById(id.getCatalogcode());

			fdesc = dictManger.getFieldsDesc(catalog.getFielddesc(),catalog.getCatalogtype());

			return "editDetail";
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}   
    
	public String saveDetail() {
		try {
			
			FDatadictionary desobj = dictManger.findById(datadictionary
					.getId());

			if (desobj != null) {
				desobj.copyNotNullProperty(datadictionary);
				datadictionary = desobj;
			}
			dictManger.saveCditms(datadictionary);
			savedMessage();
			// 刷新缓存中的字典
			codeRepo.refresh(datadictionary.getCatalogcode());
			SYS_OPT_LOG.log(((FUserinfo)this.getLoginUser()).getUsercode(), 
			        datadictionary.getId().toString(), datadictionary.display(), 
			        desobj != null?desobj.display():"");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return "editDetail";
		}
		return "saveDetail";
	}
	

    public String listUserUnits(){
	    datacode=request.getParameter("datacode");
	    Map<String,Object> filterMap = convertSearchColumn((Map<Object,Object>) request.getParameterMap());
	    Limit limit=ExtremeTableUtils.getLimit(request);
        PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
        if(filterMap.get("username")!=null && !"".equals(filterMap.get("username")))
            filterMap.put("username", "%"+filterMap.get("username")+"%");
	    if("RankType".equals(cdtbnm))
	    { //userUnits_rank=dictManger.getUserUnitByCdtbnm("xz", datacode);
	        filterMap.put("userrank",datacode);
	        userUnits_rank=userunitManager.listObjects(filterMap, pageDesc);
	        totalRows = pageDesc.getTotalRows();
	        unitsJson =sysUnitManager.getAllUnitsJSON();
	        return "userUnitList_rank";
	    }
	    else if("StationType".equals(cdtbnm)){
	        filterMap.put("userstation",datacode);
	        //userUnits_station=dictManger.getUserUnitByCdtbnm("gw", datacode);
	        userUnits_station=userunitManager.listObjects(filterMap, pageDesc);
	        totalRows = pageDesc.getTotalRows();
	        unitsJson =sysUnitManager.getAllUnitsJSON();
	        return "userUnitList_station";
	    }
	    return null;
	}
    
	
	public String getS_catalogstyle() {
        return s_catalogstyle;
    }

    public void setS_catalogstyle(String s_catalogstyle) {
        this.s_catalogstyle = s_catalogstyle;
    }

    public String getS_catalogstylenot() {
        return s_catalogstylenot;
    }

    public void setS_catalogstylenot(String s_catalogstylenot) {
        this.s_catalogstylenot = s_catalogstylenot;
    }

    public List<FDatadictionary> getRankList() {
        return rankList;
    }

    public void setRankList(List<FDatadictionary> rankList) {
        this.rankList = rankList;
    }

    public List<FDatadictionary> getStationList() {
        return stationList;
    }

    public void setStationList(List<FDatadictionary> stationList) {
        this.stationList = stationList;
    }

    public DictionaryManager getDictManger() {
        return dictManger;
    }

    public List<FUserunit> getUserUnits_rank() {
        return userUnits_rank;
    }

    public void setUserUnits_rank(List<FUserunit> userUnits_rank) {
        this.userUnits_rank = userUnits_rank;
    }

    public List<FUserunit> getUserUnits_station() {
        return userUnits_station;
    }

    public void setUserUnits_station(List<FUserunit> userUnits_station) {
        this.userUnits_station = userUnits_station;
    }


    public String getCdtbnm() {
        return cdtbnm;
    }

    public void setCdtbnm(String cdtbnm) {
        this.cdtbnm = cdtbnm;
    }

    public UserUnitManager getUserunitManager() {
        return userunitManager;
    }

    public void setUserunitManager(UserUnitManager userunitManager) {
        this.userunitManager = userunitManager;
    }

    public SysUnitManager getSysUnitManager() {
        return sysUnitManager;
    }

    public void setSysUnitManager(SysUnitManager sysUnitManager) {
        this.sysUnitManager = sysUnitManager;
    }

    public String getUnitsJson() {
        return unitsJson;
    }

    public void setUnitsJson(String unitsJson) {
        this.unitsJson = unitsJson;
    }
}
