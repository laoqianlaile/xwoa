package com.centit.powerbase.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManager;
import com.centit.core.utils.PageDesc;
import com.centit.powerbase.po.Suppower;
import com.centit.powerbase.po.Suppowerstatechglog;
import com.centit.powerbase.po.Vsuppowerwithoutlob;
import com.centit.powerruntime.po.VOrgSupPower;
import com.centit.powerruntime.po.VSupPowerWithoutLob;

public interface SuppowerManager extends BaseEntityManager<Suppower> {
    // 查询权力事项
    public List<VSupPowerWithoutLob> listSupPowerWithoutLob(
            Map<String, Object> filterMap, PageDesc pageDesc);

    // 根据业务需要，查询所属部门的关联的权力事项:风险点、流程、分组材料、行使部门、申请事项等
    public List<VOrgSupPower> listOrgSuppower(Map<String, Object> filterMap,
            PageDesc pageDesc);

    // 根据权力编码和部门获取流程接口
    public String getFlowCodeByOrgItem(String itemId, String Org_id);

    // 权力状态修改以及修改日志记录接口
    public void saveSupPownerAndState(Suppower bean, Suppowerstatechglog logbean);

    // 根据权力编号和名称查询列表,当有多个版本时，显示最新版本
    // public List<Suppower> getPicList(Map<String, String> filterMap, PageDesc
    // pageDesc, String ItemType);
    // 根据权力编号和名称，权利状态查询列表,当有多个版本时，显示最新版本
    public List<Suppower> getPicListByState(Map<String, Object> filterMap,
            PageDesc pageDesc, String qlState, String ItemType);

    // 查询权力事项列表，当有多个版本时，显示最新版本
    public List<VSupPowerWithoutLob> listSupPowerOnlyList(
            Map<String, Object> filterMap, PageDesc pageDesc);

    public Suppower getObjectById(String itemId, Long version);
    
    // 获取具体权力事项详细信息，显示最新版本
    public Suppower getSuppowerLastVersion(String itemId, Date date);

    public Suppower getSuppowerQlfb(String itemId);

    // 根据编号查询版本号列表
    public List getVersionList(String itemId);

    // 根据权力编号，查询所有的版本号和状态变更情况
    public List<Suppower> getlistSuppowerOld(String item_id, Long version);

    public void saveSuppower(Suppower suppower1, Suppower suppower2);

    // 查看权力变更list信息
    public List<Vsuppowerwithoutlob> listSuppowerWithoutLob(String hsql,
            Map<String, Object> filterMap, PageDesc pageDesc);

    // 保存权力事项（权力管理）
    public void saveSuppower(Suppower suppower);

    public void updateSuppower(Suppower suppower);

    public List xmlDISCRETIONList(String dis_detail, String update_type,
            String item_id);

    public List xmlStandardList(String dis_standard, String update_type,
            String item_id);

    public List getzycvie(String Linagesize);

    public List getseqtype(String Linagesize);

    public List getseqlowlimitunit(String Linagesize);

    public List genxmlStandardList(String dis_standard, String update_type,
            String item_id);

    public List<Suppower> getlistSuppower(String item_type);

    public List<Vsuppowerwithoutlob> listPowerWithoutLob(
            Map<String, Object> filterMap, PageDesc pageDesc);
    /**
     * 根据权力编码和部门获取权力信息,其中带着版本
     * @param item_id
     * @param orgid
     * @return
     */
    public VOrgSupPower getSupPowerInfo(String item_id,String orgid);
}
