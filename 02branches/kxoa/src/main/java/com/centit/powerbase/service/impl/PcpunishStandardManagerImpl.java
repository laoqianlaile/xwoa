package com.centit.powerbase.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.powerbase.dao.PcpunishStandardDao;
import com.centit.powerbase.po.PcpunishStandard;
import com.centit.powerbase.service.PcpunishStandardManager;

public class PcpunishStandardManagerImpl extends BaseEntityManagerImpl<PcpunishStandard>
	implements PcpunishStandardManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(PcpunishStandardManager.class);

	private PcpunishStandardDao pcpunishStandardDao ;
	
    public PcpunishStandard getObjectByClassId(String itemId,Long version){
         
	        return pcpunishStandardDao.getObjectByClassId(itemId,version);
	     }
	      public PcpunishStandard getObjectByItemsId(String itemId,Long version,String punishtypeid){
	          return pcpunishStandardDao.getObjectByItemsId(itemId,version,punishtypeid);
	      }
	      public List<PcpunishStandard> listPcType(String itemId,Long version){
	            return pcpunishStandardDao.listPcType(itemId,version);
	        }
	     @SuppressWarnings("rawtypes")
	    public List<Map> listPunishType(String itemId,Long version,String degreeno){
	          return pcpunishStandardDao.listPunishType(itemId,version,degreeno);
	      }
	     public List<PcpunishStandard> listPcTypeInUse(String itemId,Long version){
	         return pcpunishStandardDao.listPcTypeInUse(itemId,version);
	     }
	     /**
	      * 获取处罚标准处罚方式
	      */
        @Override
        public String getpunishItemType(String punishobjectid, String item_id,
                Long version) {
            String punlishType = "";
            //获取处罚标准
            List<PcpunishStandard> typeList = pcpunishStandardDao.listPcTypeInUse(item_id,version);
            
            
            for (int i = 0; i < typeList.size(); i++) {
                PcpunishStandard pcType = (PcpunishStandard) typeList.get(i);
                String PunishTypeID = pcType.getPunishtypeid();
                //上下限
                String PunishMax = pcType.getToplimit();
                String PunishMin = pcType.getLowlimit();              
                String PunishContent = pcType.getRemark();               
                String tmp =""; //this.poradixbasicDao.getDatavalueFromFdic("punishType", PunishTypeID);
                String dicPunishtypeid1 = ""; //this.poradixbasicDao.getDataCodeFromFdic("punishType", "3");// 从字典项中取amerce处罚类型的punishtypeid;
                String dicPunishtypeid2 = ""; //this.poradixbasicDao.getDataCodeFromFdic("punishType", "7");// 从字典项中取amerce处罚类型的punishtypeid;
                //基数
                 String baseToplimit=pcType.getBaseToplimit();
                 String baseLowlimit=pcType.getBaseLowlimit();
                 String baseUnit=pcType.getBaseUnit();
                /**
                 * punishType 处罚基准
                 * 如果punishType没有选择，则默认认为是不做具体数值处罚；
                 * 如果punishType有值，1：表示上下限，2表示基数倍数
                 */
                if(null!=pcType.getPunishtype()){
                    
                    //如果是上下限情况
                    if(1==pcType.getPunishtype()){
                if (!"-".equals(PunishMax) && !"-".equals(PunishMin)&& !"0".equals(PunishMax)) {
                    tmp = tmp + ":";
                    if ("∞".equals(PunishMax)) {
                        if (dicPunishtypeid1.equals(PunishTypeID)) {
                            tmp = tmp + PunishMin + "元以上";
                        } else if (dicPunishtypeid2.equals(PunishTypeID)) {
                            tmp = tmp + PunishMin + "天以上";
                        }else{
                            tmp = tmp + PunishMin + (StringUtils.isBlank(pcType.getLowlimitUnit())?pcType.getToplimitUnit():pcType.getLowlimitUnit())+"以上"; 
                        }
                    } else {
                        if (null!=PunishMax&&null!=PunishMin&&Double.parseDouble(PunishMax) == Double
                                .parseDouble(PunishMin)) {
                            if (dicPunishtypeid1.equals(PunishTypeID)) {                    
                                    tmp = tmp + PunishMin + "元";                             
                            } else if (dicPunishtypeid2.equals(PunishTypeID)) {
                                tmp = tmp + PunishMax + "天";
                            }else{
                                tmp = tmp + PunishMin + (StringUtils.isBlank(pcType.getLowlimitUnit())?pcType.getToplimitUnit():pcType.getLowlimitUnit()); 
                            }
                        } else {
                            if (dicPunishtypeid1.equals(PunishTypeID)) {                             
                                    tmp = tmp + PunishMin + "元-" + PunishMax + "元";                           
                            } else if (dicPunishtypeid2.equals(PunishTypeID)) {
                                tmp = tmp + PunishMin + "天-" + PunishMax + "天";
                            }else{
                                tmp = tmp + PunishMin + (StringUtils.isBlank(pcType.getLowlimitUnit())?pcType.getToplimitUnit():pcType.getLowlimitUnit())+"-" + baseToplimit + (StringUtils.isBlank(pcType.getToplimitUnit())?pcType.getToplimitUnit():pcType.getToplimitUnit()); 
                            }
                        }
                    }
                }
                    }else{//基数情况
                        // 相等的情况
                        if (StringUtils.isNotBlank(baseToplimit)&&StringUtils.isNotBlank(baseLowlimit)&&Double.parseDouble(baseToplimit) == Double
                                .parseDouble(baseLowlimit)) {                                           
                                    tmp = tmp +":"+ baseToplimit + "倍";                       
                        } else {
                            //
                            tmp = tmp +":"+ baseLowlimit + "倍-" + baseToplimit + "倍";
                        }
                        
                    }
                
                }else if (!StringUtils.isBlank(PunishContent)) {
                    tmp = tmp + PunishContent;
                }
                if (StringUtils.isBlank(punlishType)) {
                    punlishType = tmp;
                } else {
                    punlishType = punlishType + "；" + tmp;
                }
            }
            return punlishType;
        }
        
        
        public void setPcpunishStandardDao(PcpunishStandardDao baseDao)
        {
            this.pcpunishStandardDao = baseDao;
            setBaseDao(this.pcpunishStandardDao);
        }
         
}

