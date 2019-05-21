package com.centit.sys.service;

import java.util.List;

import com.centit.core.service.BaseEntityManager;
import com.centit.sys.po.FOptdef;
import com.centit.sys.po.FOptinfo;
import com.centit.sys.po.FUserinfo;



public interface FunctionManager extends BaseEntityManager<FOptinfo>  {
	/**
	 * @return
	 */

	public List<FOptinfo> getFunctionsByUser(FUserinfo user);
	public List<FOptinfo> getMenuFuncByUser(FUserinfo user);
	
    List<FOptinfo> getMenuFuncByUserIDAndSuperFunctionId(FUserinfo user, String superFunctionId);

	/**
	 * 根据业务类型返回菜单
	 * 'M':'系统管理','S':'系统业务','N':'普通业务','W':'流程业务','H':'我的首页'
	 * @param type 业务类型 
	 * @return List<FOptinfo>
	 */
	public List<FOptinfo> findMenuFuncByType(String type);
	
	/**
	 * @param user
	 * @param superFunctionId
	 * @return 根据权限过滤，返回用户对应superFunction功能列表
	 */
	public List<FOptinfo> getFunctionsByUserAndSuperFunctionId(FUserinfo user, String superFunctionId);
	
	public List<FOptdef> getMethodByUserAndOptID(FUserinfo user,String sOptid);
	public List<FOptdef> getMethodByUserAndOptID(String sUserCode,String sOptid);
}
