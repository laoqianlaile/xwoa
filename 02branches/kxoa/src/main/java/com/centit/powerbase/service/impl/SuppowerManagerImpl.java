package com.centit.powerbase.service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import com.centit.support.utils.StringBaseOpt;
import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.core.utils.PageDesc;
import com.centit.powerbase.dao.PcfreeumpiredegreeDao;
import com.centit.powerbase.dao.PcfreeumpiretypeDao;
import com.centit.powerbase.dao.PcpunishStandardDao;
import com.centit.powerbase.dao.SuppowerDao;
import com.centit.powerbase.dao.SuppowerchglogDao;
import com.centit.powerbase.dao.SuppowerqlbgsqDao;
import com.centit.powerbase.dao.SuppowerstatechglogDao;
import com.centit.powerbase.dao.VsuppowerwithoutlobDao;
import com.centit.powerruntime.dao.VOrgSupPowerDao;
import com.centit.powerruntime.dao.VSupPowerWithoutLobDao;
import com.centit.powerbase.po.Pcfreeumpiredegree;
import com.centit.powerbase.po.Pcfreeumpiretype;
import com.centit.powerbase.po.PcpunishStandard;
import com.centit.powerbase.po.Suppower;
import com.centit.powerbase.po.Suppowerstatechglog;
import com.centit.powerbase.po.Vsuppowerwithoutlob;
import com.centit.powerruntime.po.VOrgSupPower;
import com.centit.powerruntime.po.VSupPowerWithoutLob;
import com.centit.powerbase.service.SuppowerManager;
import com.centit.support.utils.StringBaseOpt;


public class SuppowerManagerImpl extends BaseEntityManagerImpl<Suppower>
        implements SuppowerManager {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(SuppowerManager.class);

    private SuppowerDao suppowerDao;
    private SuppowerstatechglogDao suppowerstatechglogDao;
    
    private PcpunishStandardDao pcpunishStandardDao ;
    private PcfreeumpiredegreeDao pcfreeumpiredegreeDao ;
    private PcfreeumpiretypeDao pcfreeumpiretypeDao ;
    public void setPcfreeumpiretypeDao(PcfreeumpiretypeDao baseDao)
    {
        this.pcfreeumpiretypeDao = baseDao;

    }
    
    public void setPcfreeumpiredegreeDao(PcfreeumpiredegreeDao baseDao)
    {
        this.pcfreeumpiredegreeDao = baseDao;
    }
    public void setPcpunishStandardDao(PcpunishStandardDao baseDao)
    {
        this.pcpunishStandardDao = baseDao;
    }
    public void setSuppowerstatechglogDao(
            SuppowerstatechglogDao suppowerstatechglogDao) {
        this.suppowerstatechglogDao = suppowerstatechglogDao;
    }


    private VSupPowerWithoutLobDao vSupPowerDao;
    private VsuppowerwithoutlobDao vsuppowerwithoutlobDao;
    private SuppowerqlbgsqDao suppowerqlbgsqDao;
    private SuppowerchglogDao suppowerchglogDao;
    private VOrgSupPowerDao vOrgSupPowerDao;
    
    public SuppowerchglogDao getSuppowerchglogDao() {
        return suppowerchglogDao;
    }
    public void setSuppowerchglogDao(SuppowerchglogDao suppowerchglogDao) {
        this.suppowerchglogDao = suppowerchglogDao;
    }
    public VOrgSupPowerDao getvOrgSupPowerDao() {
        return vOrgSupPowerDao;
    }
    public void setSuppowerqlbgsqDao(SuppowerqlbgsqDao suppowerqlbgsqDao) {
        this.suppowerqlbgsqDao = suppowerqlbgsqDao;
    }
    public void setSuppowerDao(SuppowerDao baseDao) {
        this.suppowerDao = baseDao;
        setBaseDao(this.suppowerDao);
    }
    public void setVsuppowerwithoutlobDao(
            VsuppowerwithoutlobDao vsuppowerwithoutlobDao) {
        this.vsuppowerwithoutlobDao = vsuppowerwithoutlobDao;
    }

    public List<VSupPowerWithoutLob> listSupPowerWithoutLob(Map<String, Object> filterMap,
            PageDesc pageDesc){
        return vSupPowerDao.listObjects(filterMap, pageDesc);
    }
   
    public void setvSupPowerDao(VSupPowerWithoutLobDao vSupPowerDao) {
        this.vSupPowerDao = vSupPowerDao;
    }


    public void setvOrgSupPowerDao(VOrgSupPowerDao vOrgSupPowerDao) {
        this.vOrgSupPowerDao = vOrgSupPowerDao;
    }
    @Override
    public List<Suppower> getlistSuppowerOld(String itemId,Long version) {
        return suppowerDao.getlistSuppowerOld(itemId, version);
    }

    @Override
    public List<VOrgSupPower> listOrgSuppower(Map<String, Object> filterMap,
            PageDesc pageDesc) {
        return vOrgSupPowerDao.listObjects(filterMap, pageDesc);
    }


    @Override
    public String getFlowCodeByOrgItem(String itemId,
            String Org_id) {
        return vOrgSupPowerDao.getFlowCodeListByItemIdOrgId(itemId,Org_id);
    }


    @Override
    public void saveSupPownerAndState(Suppower bean, Suppowerstatechglog logbean) {
        // TODO Auto-generated method stub
      suppowerDao.saveObject(bean);
      //suppowerstatechglogDao.saveObject(logbean);
      logbean.setEndTime(logbean.getBeginTime());
      suppowerstatechglogDao.updateSuppowerstatechglog(logbean);
      logbean.setEndTime(null);
      suppowerstatechglogDao.saveObject(logbean);
    }

//    public List<Suppower> getPicList(Map<String, String> filterMap,
//            PageDesc pageDesc, String ItemType) {
//        return suppowerDao.getPicList(filterMap, pageDesc,ItemType);
//    }


    @Override
    public List getVersionList(String itemId) {
       
        return  suppowerDao.getVersionList(itemId);
    }

    @Override
    public List<Suppower> getPicListByState(Map<String, Object> filterMap,
            PageDesc pageDesc, String qlState, String ItemType) {
        // TODO Auto-generated method stub
        return suppowerDao.getPicListByState(filterMap, pageDesc,qlState,ItemType);
    }

    @Override
    public List<VSupPowerWithoutLob> listSupPowerOnlyList(Map<String, Object> filterMap,
            PageDesc pageDesc) {

        return vSupPowerDao.listSupPowerOnlyList(filterMap, pageDesc);
    }
    @Override
    public Suppower getObjectById(String itemId, Long version) {
        // TODO Auto-generated method stub
        return suppowerDao.getSuppower(itemId, version);
    }
    
    public Suppower getSuppowerLastVersion(String itemId, Date date) {
        // TODO Auto-generated method stub
        return suppowerDao.getSuppowerLastVersion(itemId, date);
    }
    
    public void saveSuppower(Suppower suppower1, Suppower suppower2) {
        suppowerDao.getHibernateTemplate().merge(suppower1);
        suppowerDao.getHibernateTemplate().merge(suppower2);
        //suppowerDao.saveObject(suppower1);
        //suppowerDao.saveObject(suppower2);
        if("CF".equals(suppower1.getItemType())){
            this.saveDISCRETION(suppower1.getDis_detail(), suppower1.getDis_standard(), suppower1.getItemId(), suppower1.getVersion());
        }
        if("CF".equals(suppower2.getItemType())){
            this.saveDISCRETION(suppower2.getDis_detail(), suppower2.getDis_standard(), suppower2.getItemId(), suppower2.getVersion());
        }
    }
    public void saveSuppower(Suppower suppower){
        suppowerDao.saveObject(suppower);
        if("CF".equals(suppower.getItemType())){
            this.saveDISCRETION(suppower.getDis_detail(), suppower.getDis_standard(), suppower.getItemId(), suppower.getVersion());
        }
    }

    @Override
    public List xmlDISCRETIONList(String dis_detail, String update_type,
            String item_id) {
        boolean flag=false;
        List retList=new ArrayList();  
        if(dis_detail == null || dis_detail.equals("")) return retList;
        Document doc = null;   
        try  
        {   
            doc = DocumentHelper.parseText(dis_detail); 
            Element root = doc.getRootElement(); 
            Iterator iter = root.elementIterator("CONTENT");
             while(iter.hasNext()){
                Element ele=(Element) iter.next();
                String stand_id=ele.elementTextTrim("STAND_ID");
                String stand_code=ele.elementTextTrim("STAND_CODE");
                Pcfreeumpiredegree info=new Pcfreeumpiredegree();
                info.setStandardNo(stand_id);
                info.setPunishfactgrade(stand_code);
                retList.add(info);
            }
        }
         catch (DocumentException e)   
        {   
        e.printStackTrace();          
        }
        return retList;
    }


    @Override
    public List xmlStandardList(String dis_standard, String update_type,
            String item_id) {
        boolean flag=false;
        List retList=new ArrayList(); 
        if(dis_standard == null || dis_standard.equals("")) return retList;
        Document doc = null;     
        try  
        {   
           // doc.setXMLEncoding("GBK");
            doc = DocumentHelper.parseText(dis_standard); 
            Element root = doc.getRootElement(); 
            Iterator iter = root.elementIterator("CONTENT");
        while(iter.hasNext()){
                
                Element ele=(Element) iter.next();
                String stand_id=ele.elementTextTrim("STAND_ID");
                String stand_code=ele.elementTextTrim("STAND_CODE");
                Iterator iter2 = ele.elementIterator("STAND_SEQ");

                boolean next=iter2.hasNext();
             while (iter2.hasNext()) {
                Element ele2 = (Element) iter2.next();  
                Pcfreeumpiretype bean=new Pcfreeumpiretype();
                bean.setStandardNo(stand_id);
                bean.setPunishfactgrade(stand_code);
                bean.setPunishtypeid(ele2.elementText("SEQ_ID"));
                String seq_typei = ele2.elementText("SEQ_TYPE");
                if(!StringBaseOpt.isNvl(seq_typei)){
                    seq_typei = seq_typei.trim();
                }
                if("null".equals(seq_typei)){
                    seq_typei = null;
                }
                bean.setPunishtype(StringBaseOpt.isNvl(seq_typei)?null:Long.parseLong(seq_typei));
                bean.setToplimit(ele2.elementText("SEQ_TOPLIMIT"));
                bean.setToplimitUnit(ele2.elementText("SEQ_TOPLIMIT_UNIT"));
                bean.setLowlimit(ele2.elementText("SEQ_LOWLIMIT"));
                bean.setLowlimitUnit(ele2.elementText("SEQ_LOWLIMIT_UNIT"));
                bean.setBaseName(ele2.elementText("SEQ_BASE_NAME"));
                bean.setBaseToplimit(ele2.elementText("SEQ_BASE_TOPMUL"));
                bean.setBaseLowlimit(ele2.elementText("SEQ_BASE_LOWMUL"));
                bean.setBaseUnit(ele2.elementText("SEQ_BASE_UNIT"));
                bean.setRemark(ele2.elementText("SEQ_REMARK")); 
                retList.add(bean);
            }
            }
        }
         catch (DocumentException e)   
        {   
        e.printStackTrace();          
        }
        return retList;
    }


    @Override
    public List getzycvie(String Linagesize) {
        // TODO Auto-generated method stub
        return suppowerDao.getzycviewnew(Linagesize);
    }
    public List<Vsuppowerwithoutlob> listSuppowerWithoutLob(String hsql ,Map<String, Object> filterMap, PageDesc pageDesc){
        
        return   vsuppowerwithoutlobDao.listSuppowerWithoutLob(hsql,filterMap, pageDesc);
    }

    @Override
    public List getseqtype(String Linagesize) {
        // TODO Auto-generated method stub
        return new ArrayList();
    }


    @Override
    public List getseqlowlimitunit(String Linagesize) {
        // TODO Auto-generated method stub
        return new ArrayList();
    }


    @Override
    public List genxmlStandardList(String dis_standard, String update_type,
            String item_id) {
        System.out.println(dis_standard );
        System.out.println(item_id);
        boolean flag = false;
        List insertList = new ArrayList();
        Document doc = null;
        if(dis_standard == null || dis_standard.equals("")) return insertList;
        try {
            doc = DocumentHelper.parseText(dis_standard); // 为Docunment对象doc加载CLOB数据
            //doc.setXMLEncoding("GBK");
            Element root = doc.getRootElement(); // 获得XML根节点
            Iterator iter = root.elementIterator("STAND_SEQ");// 获取根节点的子节点STAND_SEQ放入迭代器中
            while (iter.hasNext()) { // 遍历STAND_SEQ
//              String no = genId("NO", "suppower_standard") == null ? "1"
//                      : genId("NO", "suppower_standard");// 生成NO号
                Element recordEle = (Element) iter.next(); // 在迭代器中获取当前STAND_SEQ
//                String ret[] = new String[11];// 装数据
                Map map=new HashMap();
                PcpunishStandard info=new PcpunishStandard();
                

                info.setPunishtypeid(recordEle.elementTextTrim("SEQ_ID"));
                String seq_typei = recordEle.elementTextTrim("SEQ_TYPE");
                if(!StringBaseOpt.isNvl(seq_typei)){
                    seq_typei = seq_typei.trim();
                }
                if("null".equals(seq_typei)){
                    seq_typei = null;
                }
                info.setPunishtype(StringBaseOpt.isNvl(seq_typei)?null:Long.parseLong(seq_typei));
                info.setToplimit(recordEle.elementTextTrim("SEQ_TOPLIMIT"));
                info.setToplimitUnit(recordEle.elementTextTrim("SEQ_TOPLIMIT_UNIT"));
                info.setLowlimit(recordEle.elementTextTrim("SEQ_LOWLIMIT"));
                info.setLowlimitUnit(recordEle.elementTextTrim("SEQ_LOWLIMIT_UNIT"));
                info.setBaseName(recordEle.elementTextTrim("SEQ_BASE_NAME"));
                info.setBaseToplimit(recordEle.elementTextTrim("SEQ_BASE_TOPMUL"));
                info.setBaseLowlimit(recordEle.elementTextTrim("SEQ_BASE_LOWMUL"));
                info.setBaseUnit(recordEle.elementTextTrim("SEQ_BASE_UNIT"));
                info.setRemark(recordEle.elementTextTrim("SEQ_REMARK"));

                insertList.add(info);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return insertList;
    }
    public Suppower getSuppowerQlfb(String itemId) {
        return suppowerDao.getSuppowerQlfb(itemId);
    }
    public boolean saveDISCRETION(String Dis_detail,String Dis_standard,String item_id, Long version){
        boolean flag=true;
       // SupPowerManager spMg=new SupPowerManager();

        List disstandardxmlList=this.genxmlStandardList(Dis_standard,"","");
        this.delStandardItemID(item_id, version);
        for(int i=0;i<disstandardxmlList.size();i++){
            PcpunishStandard info=(PcpunishStandard) disstandardxmlList.get(i);
            try{
               info.setItemId(item_id);
               info.setVersion(version+"");
               info.setIsinuse(1l);
               System.out.println(info.getPunishtypeid() + info.getItemId() + info.getVersion() + info.getIsinuse());
               pcpunishStandardDao.saveObject(info);
               
            }catch(Exception e){
                e.printStackTrace();
                break;
            }
        }
        if(flag){
            this.delDiscretionItemID(item_id, version);
        }
        if(flag){
        flag=this.xmlDISCRETION(Dis_detail, "", item_id, version);
        }
        return flag;
    }

    public void delStandardItemID(String item_id, Long version) {
         suppowerDao.delStandardItemID(item_id, String.valueOf(version));
    }
    public void updateSuppower(Suppower suppower) {
        suppowerDao.updateSuppower(suppower);
   }
    public void delDiscretionItemID(String item_id, Long version) {
        boolean flag=true;
        suppowerDao.delDiscretionValueByItemID(item_id, version);
        if(flag){
            suppowerDao.delDiscretionItemID(item_id, version);
        }
        
    }
//    public void delDiscretionItemID(String item_id, Long version) {
//         suppowerDao.delDiscretionItemID(item_id, version);
//    }
    
    public boolean xmlDISCRETION(String dis_detail,String update_type,String item_id,Long version){
        boolean flag=false;
        if(StringBaseOpt.isNvl(dis_detail)){
            return flag;
        }  
        Document doc = null;   
        try  
        {   
            doc = DocumentHelper.parseText(dis_detail); 
            Element root = doc.getRootElement(); 
            Iterator iter = root.elementIterator("CONTENT");
        while(iter.hasNext()){
            //String no=ConDateTime.currentDateYYYYMMDD()+StringUtil.genRandomNum(6);
                Element ele=(Element) iter.next();
                String stand_id=ele.elementTextTrim("STAND_ID");
                String stand_code=ele.elementTextTrim("STAND_CODE");
                Iterator iter2 = ele.elementIterator("STAND_SEQ");
                String degreeno = pcfreeumpiredegreeDao.getNextKeyMax("degreeno", "Pcfreeumpiredegree");
                Pcfreeumpiredegree reeInfo = new Pcfreeumpiredegree();
                reeInfo.setIsinuse(1L);
                reeInfo.setItemId(item_id);
                reeInfo.setPunishfactgrade(stand_code);
                reeInfo.setStandardNo(stand_id);
                reeInfo.setVersion(version);
                reeInfo.setDegreeno(Long.parseLong(degreeno));
                pcfreeumpiredegreeDao.saveObject(reeInfo);
                boolean next=iter2.hasNext();
             while (iter2.hasNext()) {
                Element ele2 = (Element) iter2.next();  
                Pcfreeumpiretype typeInfo = new Pcfreeumpiretype();
                typeInfo.setDegreeno(Long.parseLong(degreeno));
                typeInfo.setPunishtypeid(ele2.elementTextTrim("SEQ_ID"));
                String seq_typei = ele2.elementTextTrim("SEQ_TYPE");
                if(!StringBaseOpt.isNvl(seq_typei)){
                    seq_typei = seq_typei.trim();
                }
                if("null".equals(seq_typei)){
                    seq_typei = null;
                }
                typeInfo.setPunishtype(StringBaseOpt.isNvl(seq_typei)?null:Long.parseLong(seq_typei));
                typeInfo.setToplimit(ele2.elementText("SEQ_TOPLIMIT"));
                typeInfo.setToplimitUnit(ele2.elementText("SEQ_TOPLIMIT_UNIT"));
                typeInfo.setLowlimit(ele2.elementText("SEQ_LOWLIMIT"));
                typeInfo.setLowlimitUnit(ele2.elementText("SEQ_LOWLIMIT_UNIT"));
                typeInfo.setBaseName(ele2.elementText("SEQ_BASE_NAME"));
                typeInfo.setBaseToplimit(ele2.elementText("SEQ_BASE_TOPMUL"));
                typeInfo.setBaseLowlimit(ele2.elementText("SEQ_BASE_LOWMUL"));
                typeInfo.setBaseUnit(ele2.elementText("SEQ_BASE_UNIT"));
                typeInfo.setRemark(ele2.elementText("SEQ_REMARK")); 
                typeInfo.setStandardNo(stand_id);
                typeInfo.setIsinuse(1l);
                pcfreeumpiretypeDao.saveObject(typeInfo);
            }
            }
        }
         catch (DocumentException e)   
        {   
        e.printStackTrace();          
        }
        return flag;
    }
    
    public List<Suppower> getlistSuppower(String item_type){
        return suppowerDao.getlistSuppower(item_type);
    }

    @Override
    public List<Vsuppowerwithoutlob> listPowerWithoutLob(
            Map<String, Object> filterMap, PageDesc pageDesc) {
        // TODO Auto-generated method stub
        return vsuppowerwithoutlobDao.listObjects(filterMap, pageDesc);
    }
    /**
     * 根据权力编码和部门获取权力信息
     * @param item_id
     * @param orgid
     * @return
     */
    @Override
    public VOrgSupPower getSupPowerInfo(String item_id,String orgid) {
        // TODO Auto-generated method stub
        return vOrgSupPowerDao.getSupPowerInfo(item_id,orgid);
    }
}
