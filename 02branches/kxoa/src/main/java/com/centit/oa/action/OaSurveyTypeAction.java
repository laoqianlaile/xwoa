package com.centit.oa.action;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.core.dao.CodeBook;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.OaSurveyType;
import com.centit.oa.service.OaSurveyTypeManager;
import com.centit.sys.po.FUserinfo;
	

public class OaSurveyTypeAction  extends  BaseEntityExtremeAction<OaSurveyType>  {
	@SuppressWarnings("unused")
    private static final Log log = LogFactory.getLog(OaSurveyTypeAction.class);
	private static final long serialVersionUID = 1L;
	private OaSurveyTypeManager oaSurveyTypeMag;
	public void setOaSurveyTypeManager(OaSurveyTypeManager basemgr)
	{
		oaSurveyTypeMag = basemgr;
		this.setBaseEntityManager(oaSurveyTypeMag);
	}
	/**
	 *查询调查类型列表 
	 */
	@SuppressWarnings("unchecked")
	@Override
    public String list(){
	    try {
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);

            String orderField = request.getParameter("orderField");
            String orderDirection = request.getParameter("orderDirection");

            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            if (!StringUtils.isBlank(orderField)
                    && !StringUtils.isBlank(orderDirection)) {

                filterMap.put(CodeBook.SELF_ORDER_BY, orderField + " "
                        + orderDirection);

            }
            PageDesc pageDesc = makePageDesc();
            filterMap.put("state", "T");
            objList = oaSurveyTypeMag.listObjects(filterMap, pageDesc);
            request.setAttribute("loginer", this.getLoginUserCode());//保存登录用户的用户代码，方便判断调查类型是否可修改，删除
            totalRows = pageDesc.getTotalRows();
            setbackSearchColumn(filterMap);
            this.pageDesc = pageDesc;
            return LIST;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
	}
	/**
	 *保存新增或修改后的调查类型页面 
	 */
	public String save(){
	    if(StringUtils.isBlank(object.getNo())){
            object.setCreater(getLoginUserCode());//调查类型创建人
            object.setCreatertime(new Date());
        }
	    oaSurveyTypeMag.saveSuryType(object);
	    return SUCCESS;
	}
    /**
     * 当前登录人员usercode
     */
    private String getLoginUserCode() {
        return ((FUserinfo) this.getLoginUser()).getUsercode();
    }
	/**
	 * 删除调查类型
	 */	
    public String delete(){
        if(StringUtils.isNotBlank(object.getNo())){
        OaSurveyType oatype=oaSurveyTypeMag.getObjectById(object.getNo());
        if(null!=oatype)
        oaSurveyTypeMag.deleteSuryType(oatype);
        }
        return SUCCESS;
    }
}
