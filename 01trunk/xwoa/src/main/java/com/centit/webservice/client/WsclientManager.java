/**
 * 
 */
package com.centit.webservice.client;


/**
 *WEBSERVICE客户端程序管理接口
 * 
 * @author hx
 * @create 2015-8-28
 * @version 
 */
public interface WsclientManager {    
    
    /**
     * PCOA有人员更新、新增、启用、禁用时，调用此接口同步人员列表信息。
     * @return
     */
    public String syncUserList(String userCodes);
    
    /**
     * PCOA有机构信息更新、新增、删除时，调用此接口同步机构列表信息
     * @param unitCodes
     * @return
     */
    public String syncDepList(String unitCodes);

}
