package com.centit.powerbase.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.centit.core.dao.CodeBook;
import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.HQLUtils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.powerbase.po.PcpunishStandard;
import com.centit.powerbase.dao.PcpunishStandardDao;
public class PcpunishStandardDao extends BaseDaoImpl<PcpunishStandard>
	{
		private static final long serialVersionUID = 1L;
		public static final Log log = LogFactory.getLog(PcpunishStandardDao.class);
		
	public Map<String, String> getFilterField() {
		if( filterField == null){
			filterField = new HashMap<String, String>();

			filterField.put("punishclassid" , CodeBook.EQUAL_HQL_ID);

			filterField.put("punishtypeid" , CodeBook.EQUAL_HQL_ID);


			filterField.put("isinuse" , CodeBook.LIKE_HQL_ID);

			filterField.put("punishtype" , CodeBook.LIKE_HQL_ID);

			filterField.put("toplimit" , CodeBook.LIKE_HQL_ID);

			filterField.put("toplimitUnit" , CodeBook.LIKE_HQL_ID);

			filterField.put("lowlimit" , CodeBook.LIKE_HQL_ID);

			filterField.put("lowlimitUnit" , CodeBook.LIKE_HQL_ID);

			filterField.put("baseName" , CodeBook.LIKE_HQL_ID);

			filterField.put("baseToplimit" , CodeBook.LIKE_HQL_ID);

			filterField.put("baseLowlimit" , CodeBook.LIKE_HQL_ID);

			filterField.put("baseUnit" , CodeBook.LIKE_HQL_ID);

			filterField.put("remark" , CodeBook.LIKE_HQL_ID);

		}
		return filterField;
	} 
	 public PcpunishStandard getObjectByClassId(String itemId,Long version) {
         List<PcpunishStandard> procs = this.listObjects("From PcpunishStandard where itemId =  "+HQLUtils.buildHqlStringForSQL(itemId)+" and version="+HQLUtils.buildHqlStringForSQL(Long.toString(version)));
         if (procs == null || procs.size() < 1)
             return null;
         return procs.get(0);
     } 
  public PcpunishStandard getObjectByItemsId(String itemId,Long version,String punishtypeid) {
      List<PcpunishStandard> procs = this.listObjects("From PcpunishStandard where itemId =  "+HQLUtils.buildHqlStringForSQL(itemId)+" and version="+HQLUtils.buildHqlStringForSQL(Long.toString(version))+" and punishtypeid="+HQLUtils.buildHqlStringForSQL(punishtypeid));
      if (procs == null || procs.size() < 1)
          return null;
      return procs.get(0);
  } 
  @SuppressWarnings("unchecked")
     public List<PcpunishStandard> listPcType(String itemId,Long version){
         String baseHQL = "from PcpunishStandard where itemId = ?  and version =? order by punishtypeid desc";
         
         return (List<PcpunishStandard>)findObjectsByHql(baseHQL,new Object[]{itemId,version});
     }
  
  
  @SuppressWarnings("unchecked")
  public List<PcpunishStandard> listPcpunishStandardByItemIdAndMaxVesion(String itemId){
      String baseHQL = "from PcpunishStandard where itemId = ?  order by version desc";
      
      return (List<PcpunishStandard>)findObjectsByHql(baseHQL,itemId);
  }
  
  
  /**
   * 
   * @param itemId
   * @param version
   * @return
   */
  @SuppressWarnings("unchecked")
 public List<PcpunishStandard> listPcTypeInUse(String itemId,Long version){
      String baseHQL = "from PcpunishStandard where cid.itemId =?  and cid.version =?  and isinuse='1' order by punishtypeid desc";
      List<PcpunishStandard> retlist=(List<PcpunishStandard>)findObjectsByHql(baseHQL,new Object[]{itemId,String.valueOf(version)});
      for(PcpunishStandard o:retlist){
          //做上下限和基数的判断
          if(null!=o.getPunishtype()){//如果没有给出处罚基准
          if(1==o.getPunishtype()){         
          if(StringUtils.isBlank(o.getToplimit())){
              o.setToplimit("-");
          }
          if(StringUtils.isBlank(o.getLowlimit())){
              o.setLowlimit("-");
          }
          o.setIsrate("0");
          }else{
              if(StringUtils.isBlank(o.getBaseLowlimit())){
                  o.setBaseLowlimit("-");
              }
              if(StringUtils.isBlank(o.getBaseToplimit())){
                  o.setBaseToplimit("-");
              }
              o.setIsrate("1");
          }
          }else{             
                  o.setBaseLowlimit("-");            
                  o.setBaseToplimit("-");            
                  o.setToplimit("-");            
                  o.setLowlimit("-");             
          }
      }
      return retlist;
  }
  @SuppressWarnings({ "rawtypes", "unchecked" })
//  记得修改
 public List<Map> listPunishType(String itemId,Long version,String degreeno){
      List<Map> list = new ArrayList<Map>();
      String sSqlsen = "select f.datacode as id,f.datavalue as value from F_DATADICTIONARY f " +
      "where  f.datacode not in (select punishtypeid from B_free_umpire_type where  B_free_umpire_type.degreeno="+HQLUtils.buildHqlStringForSQL(degreeno)+")"+
      "and f.catalogcode='punishType' and f.datacode in (select punishtypeid from B_punish_Standard where item_Id="+HQLUtils.buildHqlStringForSQL(itemId)+" and  version= "+HQLUtils.buildHqlStringForSQL(Long.toString(version))+")";

              List<Object[]> l = (List<Object[]>) findObjectsBySql(sSqlsen);                                     
              if (l.size() > 0) {
                  for (int i = 0; i < l.size(); i++) {
                      Object[] O = (Object[]) l.get(i);
                      Map po = new HashMap();
                      po.put("id", O[0]);
                      po.put("value", O[1]);
                      list.add(po);
                  }
              }
              return list;
  }

}
