package com.centit.powerbase.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.core.utils.PageDesc;
import com.centit.powerbase.dao.PcpunishItemDao;
import com.centit.powerbase.dao.PcpunishStandardDao;
import com.centit.powerbase.po.PcpunishItem;
import com.centit.powerbase.service.PcpunishItemManager;

public class PcpunishItemManagerImpl extends BaseEntityManagerImpl<PcpunishItem>
	implements PcpunishItemManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(PcpunishItemManager.class);

	private PcpunishItemDao pcpunishItemDao ;
	  private PcpunishStandardDao pcpunishStandardDao;

	public void setPcpunishItemDao(PcpunishItemDao baseDao)
	{
		this.pcpunishItemDao = baseDao;
		setBaseDao(this.pcpunishItemDao);
	}
	 public void setPcpunishStandardDao(PcpunishStandardDao baseDao)
	    {
	        this.pcpunishStandardDao = pcpunishStandardDao;
	    }
	 public String generateNextPunishClassID() {
	        return pcpunishItemDao.genNextPunishClassID();
	    }

	    @Override
	    public PcpunishItem getObjectByItemId(String punishclasscode) {
	        return pcpunishItemDao.getObjectByItemId(punishclasscode);
	    }

	    @Override
	    public List<PcpunishItem> listPcpunishItem(Map<String, Object> filterMap,
	            PageDesc pageDesc) {
	        return pcpunishItemDao.listObjects(filterMap, pageDesc);
	    }

	    @Override
	    public String getPunishclassname(String punishClassIDs) {
	        String punishclassname = "";
	        if (StringUtils.isBlank(punishClassIDs)) {
	            return "";
	        }
	        if (punishClassIDs.indexOf(",") > -1) {
	            String[] punishClassID = punishClassIDs.split(",");
	            for (String punishClassid : punishClassID) {
	                PcpunishItem pcpunishItem = pcpunishItemDao.getObjectById(punishClassid);
	                if (StringUtils.isBlank(punishclassname)) {
	                    punishclassname = pcpunishItem.getPunishclassname();
	                } else {
	                    punishclassname = punishclassname + "；"
	                            + pcpunishItem.getPunishclassname();
	                }
	            }
	        } else {
	            PcpunishItem pcpunishItem = pcpunishItemDao.getObjectById(punishClassIDs);
	            punishclassname = pcpunishItem.getPunishclassname();
	        }

	        return punishclassname;
	    }

	    /*public String getpunishItemType(String punishObjectID, String punishClassID) {// 对于某个处罚方式的综合显示.
	        String punlishType = "";
	        List<PcpunishStandard> typeList = this.pcpunishStandardDao.listPcTypeInUse(punishClassID);
	        for (int i = 0; i < typeList.size(); i++) {
	            PcpunishStandard pcpunishStandard = (PcpunishStandard) typeList.get(i);
	            String PunishTypeID = pcpunishStandard.getPunishtypeid();
	            String toplimit = pcpunishStandard.getToplimit();
	            String lowlimit = pcpunishStandard.getLowlimit();
	            String toplimitUnit = pcpunishStandard.getToplimitUnit();
	            String lowlimitUnit = pcpunishStandard.getLowlimitUnit();
	            String remark = pcpunishStandard.getRemark();
	            String pubishtype = pcpunishStandard.getPunishtype().toString();
	            String baseName = pcpunishStandard.getBaseName();
	            String baseToplimit= pcpunishStandard.getBaseToplimit();
	            String baseLowlimit= pcpunishStandard.getBaseLowlimit();
	            String baseUnit= pcpunishStandard.getBaseUnit();
	            String tmp = this.poradixbasicDao.getDatavalueFromFdic(
	                    "punishType", PunishTypeID);
	            String dicPunishtypeid1 = this.poradixbasicDao.getDataCodeFromFdic(
	                    "punishType", "amerce");// 从字典项中取amerce处罚类型的punishtypeid;
	            String dicPunishtypeid2 = this.poradixbasicDao.getDataCodeFromFdic(
	                    "punishType", "detention");// 从字典项中取amerce处罚类型的punishtypeid;
	            if (!"-".equals(toplimit) && !"-".equals(lowlimit)
	                    && !"0".equals(toplimit)) {
	                tmp = tmp + ":";
	                if ("∞".equals(toplimit)) {

	                        tmp = tmp + lowlimit + lowlimitUnit;
	                } else {
	                    if (null!=toplimit&&null!=lowlimit&&Double.parseDouble(toplimit) == Double
	                            .parseDouble(lowlimit)) {
	                        if (dicPunishtypeid1.equals(PunishTypeID)) {
	                            if ("1".equals(pubishtype)) {
	                                tmp = tmp + lowlimit + "倍";
	                                Poradixbasic radix = this.poradixbasicDao
	                                        .getRadixBasic(punishObjectID,
	                                                PunishTypeID, punishClassID);
	                                String value = null;
	                            } else {
	                                tmp = tmp + lowlimit+ lowlimitUnit;
	                            }
	                        } else if (dicPunishtypeid2.equals(PunishTypeID)) {
	                            tmp = tmp + toplimit + toplimitUnit;
	                        }
	                    } else {
	                        if (dicPunishtypeid1.equals(PunishTypeID)) {
	                            if ("1".equals(pubishtype)) {
	                                tmp = tmp + lowlimit + "倍-" + toplimit + "倍";
	                                Poradixbasic radix = this.poradixbasicDao
	                                        .getRadixBasic(punishObjectID,
	                                                PunishTypeID, punishClassID);
	                                String value = null;
	                                if (null != radix.getRadix()) {
	                                    value = radix.getRadix().toString();
	                                }
	                            } else {
	                                tmp = tmp + lowlimit + "元-" + toplimit + "元";
	                            }
	                        } else if (dicPunishtypeid2.equals(PunishTypeID)) {
	                            tmp = tmp + lowlimit + "天-" + toplimit + "天";
	                        }
	                    }
	                }
	            } else if (!StringUtils.isBlank(remark)) {
	                tmp = tmp + remark;
	            }
	            if (StringUtils.isBlank(punlishType)) {
	                punlishType = tmp;
	            } else {
	                punlishType = punlishType + "；" + tmp;
	            }
	        }
	        return punlishType;
	    }*/
	    
	    
}

