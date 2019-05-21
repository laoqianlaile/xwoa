package com.centit.dispatchdoc.dao;

import java.util.List;
import java.util.Map;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.HQLUtils;
import com.centit.dispatchdoc.po.OptPdfInfo;

public class OptPdfInfoDao extends BaseDaoImpl<OptPdfInfo>{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public Map<String, String> getFilterField() {
        if (filterField == null) {
            
        }
        return filterField;
    }
    
    /**
     * 获取最新的pdf信息
     * @param djId
     * @param bizType
     * @return
     */
    public OptPdfInfo findNewestPdfInfo(String djId,int bizType){
       List<OptPdfInfo> res = listObjects("From OptPdfInfo where djId = ? and bizType = ? order by createTime desc",
                new Object[]{djId,String.valueOf(bizType)});
       if(res != null && !res.isEmpty()){
           return res.get(0);
       }
       return null;
    }
    
    /**
     * 
     * @param djId
     * @param bizType
     * @return
     */
    public List<OptPdfInfo> findPdfInfoList(String djId,int bizType){
        List<OptPdfInfo> res = listObjects("From OptPdfInfo where djId = ? and bizType = ? order by createTime",
                new Object[]{djId,String.valueOf(bizType)});
        return res;
    }
    /**
     * 根据djId和流程节点实例id获取pdf信息
     * @param djId
     * @param nodeInstId
     * @return
     */
    public OptPdfInfo findPdfInfoBy(String djId,Long nodeInstId,int bizType){
        Object pdfInfo = getSingleObjectByHql("From OptPdfInfo where djId = '"+djId+"' and nodeInstId="
                 +nodeInstId+" and bizType='"+bizType+"'");
        if(pdfInfo == null){
            return null;
        }
        return (OptPdfInfo)pdfInfo;
    }
    
    /**
     * 根据djId和流程节点实例id获取pdf信息
     * @param djId
     * @param nodeInstId
     * @return
     */
    public OptPdfInfo findPdfInfoBy(String djId,Long nodeInstId,int bizType,String  userCode){
        List<OptPdfInfo> res =listObjects("From OptPdfInfo where djId = "+HQLUtils.buildHqlStringForSQL(djId)+"  and nodeInstId="
                 +nodeInstId+" and bizType= "+HQLUtils.buildHqlStringForSQL( String.valueOf(bizType))+"  and userCode= "
                 +HQLUtils.buildHqlStringForSQL(userCode)  );
        
        if(res != null && !res.isEmpty()){
            return (OptPdfInfo)res.get(0);
        }
       
        return null;
    }
}
