package com.centit.bbs.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.bbs.po.OaBbsAttention;
import com.centit.bbs.service.OaBbsAttentionManager;
import com.centit.core.action.BaseEntityDwzAction;
import com.centit.sys.po.FUserinfo;

public class OaBbsAttentionAction extends BaseEntityDwzAction<OaBbsAttention> {
    private static final Log log = LogFactory
            .getLog(OaBbsAttentionAction.class);
    private static final long serialVersionUID = 1L;
    private OaBbsAttentionManager oaBbsAttentionMag;

    public void setOaBbsAttentionManager(OaBbsAttentionManager basemgr) {
        oaBbsAttentionMag = basemgr;
        this.setBaseEntityManager(oaBbsAttentionMag);
    }

    private String s_usercode;
    private String s_laytype;
    private JSONObject json;
  

    /**
     * 添加收藏，关注，支持，反对 G 关注 S 收藏 O 其他。。。
     * 
     * @return
     */
    public String addSign() {
        json = new JSONObject();
        String themeno = (String) request.getParameter("themeno");
        String laytype = (String) request.getParameter("laytype");

        String usercode = ((FUserinfo) this.getLoginUser()).getUsercode();
        object.getCid().setThemeno(themeno);
        object.getCid().setLaytype(laytype);
        object.getCid().setUsercode(usercode);

        OaBbsAttention dbObject = baseEntityManager.getObject(object);

        if (dbObject != null) {
            oaBbsAttentionMag.copyObjectNotNullProperty(dbObject, object);
            object = dbObject;
            json.put("status", "false");
        } else {
            object.setCreatetime(new Date());
            oaBbsAttentionMag.saveObject(object);
            json.put("status", "true");
        }
        return "options";
    }

    /**
     * 取消收藏，关注 G 关注 S 收藏 O 其他。。。
     * 
     * @return
     */
    public String cancleSign() {

        String usercode = ((FUserinfo) this.getLoginUser()).getUsercode();
        object.getCid().setUsercode(usercode);

        s_usercode = usercode;
        s_laytype = object.getCid().getLaytype();

        oaBbsAttentionMag.deleteObject(object);

        return "attentionThemeList";
    }

    public String getAttentionNum() {
        json = new JSONObject();
        String themeno = (String) request.getParameter("themeno");
        String laytype = (String) request.getParameter("laytype");
        Map<String, Object> filterMap = new HashMap<String, Object>();
//        filterMap.put("usercode", ((FUserinfo) getLoginUser()).getUsercode());
        filterMap.put("laytype", laytype);
        filterMap.put("themeno", themeno);
        objList = oaBbsAttentionMag.listObjects(filterMap);
        if (null == objList || objList.size() < 0) {
            json.put("status", "0");
        } else {
            json.put("status", objList.size());
        }
        return "options";
    }

    public JSONObject getJson() {
        return json;
    }

    public void setJson(JSONObject json) {
        this.json = json;
    }

    public String getS_usercode() {
        return s_usercode;
    }

    public void setS_usercode(String s_usercode) {
        this.s_usercode = s_usercode;
    }

    public String getS_laytype() {
        return s_laytype;
    }

    public void setS_laytype(String s_laytype) {
        this.s_laytype = s_laytype;
    }

}
