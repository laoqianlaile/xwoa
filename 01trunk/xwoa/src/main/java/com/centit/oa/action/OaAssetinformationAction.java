package com.centit.oa.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.extremecomponents.table.limit.Limit;

import com.centit.core.action.BaseEntityDwzAction;
import com.centit.core.utils.DwzTableUtils;
import com.centit.core.utils.ExtremeTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.OaAssetinformation;
import com.centit.oa.po.OaAssetinformationInlog;
import com.centit.oa.po.OaAssetinformationOutlog;
import com.centit.oa.service.OaAssetinformationInlogManager;
import com.centit.oa.service.OaAssetinformationManager;
import com.centit.oa.service.OaAssetinformationOutlogManager;
import com.centit.powerruntime.util.BizCommUtil;
import com.centit.support.utils.Algorithm;
import com.centit.support.utils.Algorithm.ParentChild;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.po.FDatadictionary;
import com.centit.sys.po.FUnitinfo;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.po.FUserunit;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.service.CodeRepositoryUtilExtend;
import com.centit.sys.service.SysUnitManager;
import com.centit.sys.service.SysUserManager;

public class OaAssetinformationAction  extends BaseEntityDwzAction<OaAssetinformation>  {
	private static final long serialVersionUID = 1L;
	private List<FDatadictionary> dictDetails;
	private String s_supEquipmentType;//接收菜单传值
	private String datacodeType;//资产类别
	private String no;//资产信息编码
	private String isDept;//标记机构属性T:表示厅机关
	private List<FUnitinfo> unitlist;//所属部门
	private String callback;
	private OaAssetinformationManager oaAssetinformationMag;
	private SysUserManager sysUserManager;
    private SysUnitManager sysUnitManager;
    private String  begcreatertime;//供导出使用
    private String  endcreatertime;
    private String hasChildNode;//标记是否含有子节点
    private OaAssetinformationOutlogManager oaAssetinformationOutlogManager;
    private OaAssetinformationInlogManager oaAssetinformationInlogManager;
	public void setOaAssetinformationManager(OaAssetinformationManager basemgr)
	{
		oaAssetinformationMag = basemgr;
		this.setBaseEntityManager(oaAssetinformationMag);
	}
	public void setOaAssetinformationOutlogManager(
            OaAssetinformationOutlogManager oaAssetinformationOutlogManager) {
        this.oaAssetinformationOutlogManager = oaAssetinformationOutlogManager;
    }
	
    public void setOaAssetinformationInlogManager(
            OaAssetinformationInlogManager oaAssetinformationInlogManager) {
        this.oaAssetinformationInlogManager = oaAssetinformationInlogManager;
    }
    public void setOaAssetinformationInlogs(
            List<OaAssetinformationInlog> oaAssetinformationInlogs) {
        this.oaAssetinformationInlogs = oaAssetinformationInlogs;
    }
    public void setSysUserManager(SysUserManager sysUserManager) {
        this.sysUserManager = sysUserManager;
    }
    public void setSysUnitManager(SysUnitManager sysUnitManager) {
        this.sysUnitManager = sysUnitManager;
    }

    public String getBegcreatertime() {
        return begcreatertime;
    }

    public void setBegcreatertime(String begcreatertime) {
        this.begcreatertime = begcreatertime;
    }

    public String getEndcreatertime() {
        return endcreatertime;
    }

    public void setEndcreatertime(String endcreatertime) {
        this.endcreatertime = endcreatertime;
    }


    private List<OaAssetinformationOutlog> oaAssetinformationOutlogs;
	public List<OaAssetinformationOutlog> getNewOaAssetinformationOutlogs() {
		return this.oaAssetinformationOutlogs;
	}
	public void setNewOaAssetinformationOutlogs(List<OaAssetinformationOutlog> oaAssetinformationOutlogs) {
		this.oaAssetinformationOutlogs = oaAssetinformationOutlogs;
	}
	private List<OaAssetinformationInlog> oaAssetinformationInlogs;
	public List<OaAssetinformationInlog> getNewOaAssetinformationInlogs() {
		return this.oaAssetinformationInlogs;
	}
	public void setNewOaAssetinformationInlogs(List<OaAssetinformationInlog> oaAssetinformationInlogs) {
		this.oaAssetinformationInlogs = oaAssetinformationInlogs;
	}
	  /**
     * 是否启用，禁用入口
     * @return 
     */
    public String oaAssetinformationStart(){
        @SuppressWarnings("unchecked")
        Map<String, Object> filterMap = convertSearchColumn(request.getParameterMap());
        OaAssetinformation o = oaAssetinformationMag.getObjectById(object.getNo());
        o.setState((String)filterMap.get("state"));
        oaAssetinformationMag.saveObject(o);
        return this.list();
    }
    /**
	 * 资产信息维护list
	 * @return
	 */
	public String listLab(){
	    this.list();
	    putTree("equipment",datacodeType);
	    return "listLab";
	}
	/**
     * 获取资产树形结构
     * 
     */
    public void putTree(String catalogCode,String datacodeType) {
        dictDetails = CodeRepositoryUtilExtend.getTreeDictionaryStartBy(catalogCode,
                s_supEquipmentType, true);
        ParentChild<FDatadictionary> c = new Algorithm.ParentChild<FDatadictionary>() {
            public boolean parentAndChild(FDatadictionary p, FDatadictionary c) {
                return p.getDatacode().equals(c.getExtracode());
            }
        };
        int n=dictDetails.size();
        for(int i=0;i<n;i++){
            for(int j=0;j<i;j++){
                if(c.parentAndChild(dictDetails.get(j),dictDetails.get(i))){
                    dictDetails.get(j).setDatastyle("Y");//设置此节点含有子节点
                    break;
                }
            }
        }
        Algorithm.sortAsTree(dictDetails, c);
        List<Integer> optIndex = Algorithm.makeJqueryTreeIndex(dictDetails, c);
       
        ServletActionContext.getContext().put("INDEX", getOptIndex(optIndex));
    }
    private static String getOptIndex(List<Integer> optIndex) {
        JSONObject jo = new JSONObject();
        jo.put("indexes", optIndex);
        return jo.toString();
    }
    /**
     * 选择人员
     * @return
     */
    @SuppressWarnings("unchecked")
    public String userSelectList(){
        FUserDetail user = ((FUserDetail)getLoginUser());//.getUserinfo（）;
        FUserunit dept = sysUserManager.getUserPrimaryUnit(user.getUsercode());
        Map<Object,Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);             
        Map<String,Object> filterMap = convertSearchColumn( paramMap );
        if("T".equals(isDept)&&StringUtils.isBlank((String)filterMap.get("unitcode"))){
            filterMap.put("queryByIsDeptT", isDept);   //查找厅机关所有人员
        }if("S".equals(isDept)&&StringUtils.isBlank((String)filterMap.get("unitcode"))){
            filterMap.put("queryByIsDeptS", dept.getUnitcode()); //查找单位本部门人员
        }else{
            filterMap.put("queryByIsDeptS", (String)filterMap.get("unitcode"));
        }
        Limit limit=ExtremeTableUtils.getLimit(request);
        PageDesc pageDesc =  ExtremeTableUtils.makePageDesc(limit); 
        List<FUserinfo> userlist = sysUserManager.listUnderUnit(filterMap,pageDesc);
        if(userlist!=null&&userlist.size()>0){
            for(FUserinfo u : userlist){
                FUserunit unitcode = sysUserManager.getUserPrimaryUnit(u.getUsercode());
                u.setOrgCode(unitcode.getUnitcode());
                u.setUnitName(CodeRepositoryUtil.getValue("unitcode", dept.getUnitcode()));
            }
        }
        String sParentUnit = dept.getUnitcode();
        unitlist = sysUnitManager.getAllSubUnits(sParentUnit);
        totalRows = pageDesc.getTotalRows();
        request.setAttribute("userlist", userlist);
        return "oaAssetinformationUserSelect";
    }
    @Override
    public String delete(){
        super.delete();
        return this.list();
    }
	@Override
	public String list(){
	    FUserDetail fuser = (FUserDetail) getLoginUser();
        @SuppressWarnings("unchecked")
        Map<String, Object> filterMap = convertSearchColumn(request.getParameterMap());
        PageDesc pageDesc = DwzTableUtils.makePageDesc(this.request);
        datacodeType = (String) filterMap.get("datacode");
        
        StringBuilder types = new StringBuilder();
        String shql = "";
        
        //资产类别的叶子节点查询
        if ( (!StringUtils.isEmpty(datacodeType)) && (!"resourse".equals(datacodeType))) {
            List<FDatadictionary> l=CodeRepositoryUtilExtend.getTreeDictionaryStartBy("equipment", datacodeType, true);
            for (FDatadictionary f : l) {
                types.append(", '" + f.getDatacode() + "'");
            }
            if (!StringUtils.isBlank(types.toString())) {
                String str = types.substring(1, types.length());
                shql = "  From OaAssetinformation where datacode in( "+ str + ")  ";
            }
        }else{
            shql ="From OaAssetinformation where 1=1";
            hasChildNode="Y";//初始化设置为有子节点
            filterMap.remove("datacode");
        }
        FUserunit funit = sysUserManager.getUserPrimaryUnit(fuser
                .getUsercode());
        FUnitinfo unitinfo = sysUnitManager.getObjectById(funit
                .getUnitcode());
        request.setAttribute("unitcode", unitinfo.getUnitcode());
        isDept = unitinfo.getUnittype();
        
        
        //若为事业单位则查询出本部门的资产信息
        if(!"T".equals(isDept)){
            filterMap.put("createDepno", unitinfo.getUnitcode()); 
        }
        
        //若为厅机关则查询出所有厅机关的资产信息
        filterMap.put("senddeptype", isDept);
        objList = baseEntityManager.listObjects(shql, filterMap, pageDesc);
        
        for(OaAssetinformation o:objList){
            filterMap.put("no", o.getNo());
            List<OaAssetinformationOutlog> outs= oaAssetinformationOutlogManager.listObjects(filterMap);
            List<OaAssetinformationInlog> ins= oaAssetinformationInlogManager.listObjects(filterMap);
            if((outs!=null&&outs.size()>0)||(ins!=null&&ins.size()>0)){
                o.setHasOutIn("T");
            }
        }
        totalRows = pageDesc.getTotalRows();
        setbackSearchColumn(filterMap);
        
        //查询所有厅机关部门
        filterMap.put("unittype", isDept);
        unitlist= sysUnitManager.listObjects(filterMap);
       
	    //return  LIST;
	    return  "listV2";
	}
	//根据标题查看详细页面
	public String viewInfo(){
	    this.view();
	    return "oaAssetinformationViewInfo";
	}
	@Override
	public String view(){
	    super.view();
	    callback=(String)request.getParameter("callback");
	    return VIEW;
	}
	/**
	 * 修改资产信息预警红线
	 * 
	 */
	public void oaAssetinformationAdd(){
	    OaAssetinformation oaAssetinformation =oaAssetinformationMag.getObjectById(object.getNo());
        oaAssetinformation.setAssetleftalert(object.getAssetleftalert());
        //解决中文传值乱码问题
        try {
            //URLDecoder.decode(object.getCreateRemark(), "UTF-8");// 此处解码   
            oaAssetinformation.setCreateRemark(new String(object.getCreateRemark().getBytes("ISO-8859-1"),"UTF-8"));//此次编码
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        oaAssetinformationMag.saveObject(oaAssetinformation); 
	}
		
	public String save(){
	    FUserDetail fuser = (FUserDetail) getLoginUser();
        FUserunit funit = sysUserManager.getUserPrimaryUnit(fuser
                .getUsercode());
        FUnitinfo unitinfo = sysUnitManager.getObjectById(funit
                .getUnitcode());
	    object.setNo(oaAssetinformationMag.getNextKey());
	    object.setCreatetime(new Date(System.currentTimeMillis()));
        object.setCreater(((FUserDetail) getLoginUser()).getUsercode());
        object.setCreateDepno(funit.getUnitcode());
        object.setSenddeptype(unitinfo.getUnittype());
		//object.replaceOaAssetinformationOutlogs( oaAssetinformationOutlogs);
		//object.replaceOaAssetinformationInlogs( oaAssetinformationInlogs);
		oaAssetinformationMag.saveObject(object);
		return this.list(); 
	}
	/**
     * 导出Excel 通讯录
     * 
     * @throws IOException
     */
    public void exportExcelByPo() throws IOException {
        this.list();
        List<Object[]> chosedList = new ArrayList<Object[]>() ;
     // 获取需要生成Excel的数据
        if (objList != null && objList.size()>0) {
            for (OaAssetinformation o : objList) {
                Object[] temp= new Object[6];
                temp[0]=CodeRepositoryUtil.getValue("equipment", o.getDatacode());
                temp[1]=o.getAssetnum();
                temp[2]=CodeRepositoryUtil.getValue("unitcode", o.getCreateDepno());
                temp[3]=DatetimeOpt.convertSqlDate(o.getCreatetime());
                temp[4]=CodeRepositoryUtil.getValue("UnitType", o.getSenddeptype());
                temp[5]=o.getAssetleftalert();
                chosedList.add(temp);
               
            }
        }
        String[] header = { "资产名称", "资产数量","发起部门","记录时间", "部门属性","库存预警红线"};// 列头
        String titleName="";
        String title = (null==begcreatertime&&null==endcreatertime)?"":"在"+begcreatertime+"至"+endcreatertime;
        titleName += title +CodeRepositoryUtil.getValue("UnitType", isDept)+"资产使用情况";
        BizCommUtil.doPoi2Excel(titleName, header, chosedList);
    }
    
    public List<FDatadictionary> getDictDetails() {
        return dictDetails;
    }
    public void setDictDetails(List<FDatadictionary> dictDetails) {
        this.dictDetails = dictDetails;
    }
    public String getS_supEquipmentType() {
        return s_supEquipmentType;
    }
    public void setS_supEquipmentType(String s_supEquipmentType) {
        this.s_supEquipmentType = s_supEquipmentType;
    }
    public String getDatacodeType() {
        return datacodeType;
    }
    public void setDatacodeType(String datacodeType) {
        this.datacodeType = datacodeType;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getIsDept() {
        return isDept;
    }

    public void setIsDept(String isDept) {
        this.isDept = isDept;
    }

    public List<FUnitinfo> getUnitlist() {
        return unitlist;
    }

    public void setUnitlist(List<FUnitinfo> unitlist) {
        this.unitlist = unitlist;
    }

    public String getHasChildNode() {
        return hasChildNode;
    }

    public void setHasChildNode(String hasChildNode) {
        this.hasChildNode = hasChildNode;
    }
		
}
