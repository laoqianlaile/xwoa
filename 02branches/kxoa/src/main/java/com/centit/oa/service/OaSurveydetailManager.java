package com.centit.oa.service;

import java.util.List;

import com.centit.core.service.BaseEntityManager;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.OaSurveydetail;
import com.centit.oa.po.VOaSurveyDetail;
import com.centit.sys.po.FUserinfo;

public interface OaSurveydetailManager extends BaseEntityManager<OaSurveydetail> 
{
    /**
     *  保存调查明细信息
     * @param OaSurveydetails
     */
    void saveSurveydetail( List<OaSurveydetail> OaSurveydetails ,String usercode);
    /**
     * 按調查id獲取人員列表
     * @return
     */
    public List<FUserinfo> detailList( String djid,PageDesc pageDesc);
    /**
     * 根据调查id，投票者获取详细调查结果
     * @param djid
     * @param creater
     * @return
     */
    List<VOaSurveyDetail> detailViewList(String djid, String creater);
}
