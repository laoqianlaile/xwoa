package com.centit.oa.service;

import java.util.List;

import com.centit.core.service.BaseEntityManager;
import com.centit.oa.po.OaOptPowerDef;

public interface OaOptPowerDefManager  extends BaseEntityManager<OaOptPowerDef> {
    /**
     * 根据权限类别获取业务权限
     * @param optlevel
     * @return
     */
	public List<OaOptPowerDef> getOptDefByOptId(String optid);

    public String getNextOptCode();
    /**
     * 保存公文查看权限
     * @param initDocViewOptPowerDef
     * @param optcodelist
     */
    public void saveDocViewPower(OaOptPowerDef initDocViewOptPowerDef,
            String[] optcodelist);
    /**
     * 清空公文查看权限
     * @param initDocViewOptPowerDef
     * @param optcodelist
     */
    public void deleteDocViewPower(String optid);
}
