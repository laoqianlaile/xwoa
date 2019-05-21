package com.centit.app.action;

import java.util.List;
import java.util.Map;

import org.extremecomponents.table.limit.Limit;

import com.centit.app.po.FileInfo;
import com.centit.app.po.Innermsg;
import com.centit.app.service.InnermsgManager;
import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.core.utils.ExtremeTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.security.FUserDetail;

public class InnermsgAction extends BaseEntityExtremeAction<Innermsg> {
    private static final long serialVersionUID = 1L;
    private InnermsgManager innermsgMag;
    private List<Innermsg> replyList;
    private List<FileInfo> fileList;

    public void setInnermsgManager(InnermsgManager basemgr) {
        innermsgMag = basemgr;
        this.setBaseEntityManager(innermsgMag);
    }

    @SuppressWarnings("unchecked")
    public String list() {
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);

        String isReveive = request.getParameter("isReveive");

        if (isReveive != null && isReveive.equals("S")) {
            filterMap.put("sender", ((FUserDetail)this.getLoginUser()).getUsercode());
        } else {
            filterMap.put("receive", ((FUserDetail)this.getLoginUser()).getUsercode());
        }

        filterMap.put("nodelete", "D");

        Limit limit = ExtremeTableUtils.getLimit(request);
        PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit);
        objList = baseEntityManager.listObjects(filterMap, pageDesc);
        totalRows = pageDesc.getTotalRows();

        return LIST;
    }

    public String built() {
        object.clearProperties();
        return EDIT;
    }

    public String view() {

        try {

           

            if (object == null) {

                return LIST;
            }
            Innermsg msgObj = innermsgMag.getObjectById(object.getMsgcode());
            if (msgObj != null) {
                innermsgMag.copyObject(object, msgObj);
                fileList = innermsgMag.listFilesByMsg(object.getMsgcode());
            }

            replyList = innermsgMag.listReplyMsgs(object.getMsgcode());

            FUserDetail fuser = ((FUserDetail)this.getLoginUser());

            if (fuser.getUsercode() != null
                    && fuser.getUsercode().equals(msgObj.getReceive())) {
                if (msgObj.getMsgstate().equals("U")) {
                    msgObj.setMsgstate("R");// 设置邮件状态为已读
                    innermsgMag.saveObject(msgObj);
                }
            }

            return VIEW;
        } catch (Exception e) {
            return ERROR;
        }
    }

    public String save() {

        FUserDetail fuser = ((FUserDetail)this.getLoginUser());

        Innermsg msgObj = innermsgMag.getObject(object);

        if (msgObj != null) {
            innermsgMag.copyObjectNotNullProperty(msgObj, object);
            object = msgObj;
        }

        object.setSender(fuser.getUsercode());
        object.setSenddate(DatetimeOpt.currentSqlDate());
        innermsgMag.saveInnermsg(object);
        return EDIT;
    }

    public String reply() {
        Innermsg msgObj = innermsgMag.getObjectById(object.getMsgcode());
        if (msgObj != null) {
            Innermsg msg = new Innermsg();
            msg.setMsgtitle("答复:" + msgObj.getMsgtitle());
            msg.setReceive(msgObj.getSender());
            msg.setReceivetype("P");
            msg.setReplymsgcode(msgObj.getMsgcode());
            object.copy(msg);
        }
        return EDIT;
    }

    public String showMsgFrame() {
        return "msgFrame";
    }

    public List<Innermsg> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<Innermsg> replyList) {
        this.replyList = replyList;
    }

    public List<FileInfo> getFileList() {
        return fileList;
    }

    public void setFileList(List<FileInfo> fileList) {
        this.fileList = fileList;
    }

}
