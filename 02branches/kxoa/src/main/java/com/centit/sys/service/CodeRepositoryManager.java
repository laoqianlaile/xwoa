package com.centit.sys.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.centit.core.utils.LabelValueBean;
//import com.centit.dispatchdoc.po.ApplyUnitInfo;
//import com.centit.powerbase.po.Suppower;
import com.centit.sys.po.FDatadictionary;
import com.centit.sys.po.FOptdef;
import com.centit.sys.po.FOptinfo;
import com.centit.sys.po.FRoleinfo;
import com.centit.sys.po.FUnitinfo;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.po.FUserunit;

public interface CodeRepositoryManager  {
 // usercode 与 scriptSessionId 关联
    public static final Map<String, String> USERCODE_SCRIPTSESSION = new HashMap<String, String>();
    // httpsession id 与usercode关联
    public static final Map<String, String> SESSIONID_USERCODE = new HashMap<String, String>();

    public static final Map<String, FUserinfo > USERREPO = new HashMap<String, FUserinfo>();
    public static final Map<String, FUserinfo > LOGINEPO = new HashMap<String, FUserinfo>();
    public static final Map<String, FUserinfo> EMAILPO = new HashMap<String, FUserinfo>();
    public static final Map<String, FUnitinfo > UNITREPO = new HashMap<String, FUnitinfo>();
    public static final List<FUnitinfo > UNITASTREE = new ArrayList<FUnitinfo>();
    public static final Map<String, FUnitinfo > DEPNO = new HashMap<String, FUnitinfo>();
    public static final List<FUserunit> USERUNIT = new ArrayList<FUserunit>();
//    public static final Map<String, ApplyUnitInfo> MAINNOTYEPO = new HashMap<String, ApplyUnitInfo>();
    
    public static final Map<String, FRoleinfo > ROLEREPO = new HashMap<String, FRoleinfo>();
    public static final Map<String, FOptinfo > OPTREPO = new HashMap<String, FOptinfo>();
    public static final Map<String, FOptdef > POWERREPO = new HashMap<String, FOptdef>();
//  public static final Map<String, Suppower > SUPPOWERREPO = new HashMap<String, Suppower>();
    public static final List<LabelValueBean> DATACATALOG = new ArrayList<LabelValueBean>();
    public static final Map<String, List<FDatadictionary>> REPOSITORIES = new HashMap<String, List<FDatadictionary>>();

    public static final Map<String, List<FOptinfo>> USEROPTLIST = new HashMap<String, List<FOptinfo>>();
    
    public void refreshAll();
    public void refresh(String sCatalog);
    public void setUserOptList(List<FOptinfo> userOptList);
    
}
