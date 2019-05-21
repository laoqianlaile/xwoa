package com.centit.oa.service.impl;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.oa.po.OaBizBindInfo;
import com.centit.oa.dao.OaBizBindInfoDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.centit.oa.service.OaBizBindInfoManager;

public class OaBizBindInfoManagerImpl extends BaseEntityManagerImpl<OaBizBindInfo>
	implements OaBizBindInfoManager{
	private static final long serialVersionUID = 1L;
	public static final Log log = LogFactory.getLog(OaBizBindInfoManager.class);

	private OaBizBindInfoDao oaBizBindInfoDao ;
	public void setOaBizBindInfoDao(OaBizBindInfoDao baseDao)
	{
		this.oaBizBindInfoDao = baseDao;
		setBaseDao(this.oaBizBindInfoDao);
	}
    @Override
    public String getNextNO(String ev) {
        // TODO Auto-generated method stub
        return ev + oaBizBindInfoDao.getNextKeyBySequence("S_OA_BIZ_BIND_INFO", 14);
    }
    @Override
    public void deleteStartObjectById(String djId) {
        // TODO Auto-generated method stub
        oaBizBindInfoDao.deleteStartObjectById(djId);
    }
    /**
     *根据不同的业务要求,设置关联类型
     *@return
     *@param djid 被关联djId
     *@param startDjId 关联源djId
     */
    @Override
    public String initReturnBiztype(String djid, String startDjId) {
        // TODO Auto-generated method stub
        String returnfame = "";
        if (startDjId.indexOf("SQ") != -1 && djid.indexOf("FW")!= -1) {// 事权转发文
            returnfame = "QF";
        } else if (djid.indexOf("SQ") != -1 && startDjId.indexOf("FW")!= -1) {//发文转事权
            returnfame = "FQ";
        }
        else if (startDjId.indexOf("SW") != -1 && djid.indexOf("SQ")!= -1) {//收文转事权
            returnfame = "SQ";
        }else if (startDjId.indexOf("SW") != -1 && djid.indexOf("FW")!= -1) {//收文转发文
            returnfame = "SF";
        }else if (startDjId.indexOf("SQ") != -1 && djid.indexOf("SQ")!= -1) {//事权转事权
            returnfame = "QQ";
        }else if (startDjId.indexOf("FW") != -1 && djid.indexOf("FW")!= -1) {//发文转发文
            returnfame = "FF";
        }
        return returnfame;
    }
	
}

