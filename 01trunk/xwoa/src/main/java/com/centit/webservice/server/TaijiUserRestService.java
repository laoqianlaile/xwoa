package com.centit.webservice.server;

import java.util.List;

import com.centit.webservice.pojo.UnitDTO;
import com.centit.webservice.pojo.UserDTO;
/**
 * 
 * 太极用户接口
 * 
 * @author Ghost
 * @create 2017年2月24日
 * @version
 */
public interface TaijiUserRestService {
    /**
     * 获取所以用户
     * @return
     */
    List<UserDTO> getAllUser();
    
    /**
     * 同步用户
     */
    void syncUser();
    /**
     * 获取所有部门
     * @return
     */
    List<UnitDTO> getAllUnit();
    /**
     * 同步部门
     */
    void syncUnit();
}
