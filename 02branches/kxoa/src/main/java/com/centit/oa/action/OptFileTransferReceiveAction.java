package com.centit.oa.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.OptFileTransferReceive;
import com.centit.oa.po.OptFilingCabinets;
import com.centit.oa.service.OptFileTransferReceiveManager;
import com.centit.oa.service.OptFilingCabinetsManager;
import com.centit.sys.po.UserbizoptLog;
import com.centit.sys.po.UserbizoptLogId;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.service.UserbizoptLogManager;

public class OptFileTransferReceiveAction extends BaseEntityExtremeAction<OptFileTransferReceive>{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private OptFileTransferReceiveManager optFileTransferReceiveMgr;
    private OptFilingCabinetsManager optFilingCabinetsMgr;
    
    private String  show_type;//页面打开方式
    
    private UserbizoptLogManager userbizoptLogManager;
    
    public void setUserbizoptLogManager(UserbizoptLogManager userbizoptLogManager) {
        this.userbizoptLogManager = userbizoptLogManager;
    }
    
    public OptFileTransferReceiveManager getOptFileTransferReceiveMgr() {
        return optFileTransferReceiveMgr;
    }
    
    public void setOptFileTransferReceiveMgr(
            OptFileTransferReceiveManager optFileTransferReceiveMgr) {
        this.optFileTransferReceiveMgr = optFileTransferReceiveMgr;
        super.setBaseEntityManager(optFileTransferReceiveMgr);
    }
    
    
    public OptFilingCabinetsManager getOptFilingCabinetsMgr() {
        return optFilingCabinetsMgr;
    }

    public void setOptFilingCabinetsMgr(
            OptFilingCabinetsManager optFilingCabinetsMgr) {
        this.optFilingCabinetsMgr = optFilingCabinetsMgr;
    }

    @SuppressWarnings("unchecked")
    @Override
    public String list() {
        FUserDetail userDetail = (FUserDetail) getLoginUser();
        Map<Object, Object> paramMap = request.getParameterMap();
        resetPageParam(paramMap);
        Map<String, Object> filterMap = convertSearchColumn(paramMap);
        if(!"sa".equals(userDetail.getLoginname()) & !"admin".equals(userDetail.getLoginname())){
            filterMap.put("receiverCode" , userDetail.getUsercode());
        }
        
        PageDesc pageDesc =makePageDesc();
       
        this.pageDesc = pageDesc;
        objList = optFileTransferReceiveMgr.listObjects(filterMap, pageDesc);
        for(OptFileTransferReceive o:objList){
            UserbizoptLog uboptlog=userbizoptLogManager.listObject(o.getId(),userDetail.getUsercode(),o.getReceiverCode());
            if(uboptlog!=null){
                o.setReadstate("T");//已读
            }else{
                o.setReadstate("F");//未读
            }
            o.setReadstate(CodeRepositoryUtil.getValue("readstate", o.getReadstate()));//已读
            Date today=new Date();
            int n=today.compareTo(new Date(o.getCreateTime().getTime()+ 3 * 24 * 60 * 60 * 1000));
            if(n==-1){//当前日期不大于发送日期加三天，则就最新
                o.setNewmsg("1");
            }else{
                o.setNewmsg("0");
            }
        }
        totalRows = pageDesc.getTotalRows();
        setbackSearchColumn(filterMap);
        return LIST;
    }
    
    @Override
    public String view() {
        String id = request.getParameter("id");
        object = optFileTransferReceiveMgr.getObjectById(id);
        object.setReceiverName(CodeRepositoryUtil.getValue("usercode", object.getReceiverCode()));
        Map<String,Object> filterMap = new HashMap<String,Object>();
        filterMap.put("refId", id);
        List<OptFilingCabinets> fileList = optFilingCabinetsMgr.listObjects(filterMap);
        //保存查看日志
        FUserDetail user = (FUserDetail) getLoginUser();
        //因为管理员可以查看所有人的文件，所以把收件人放入remark中以做区分
        UserbizoptLog u=new UserbizoptLog(new UserbizoptLogId(object.getSubject(),object.getId()),object.getReceiverCode(),null);
        userbizoptLogManager.saveUserbizoptLog(u, user);
        request.setAttribute("fileList", fileList);
        return VIEW;
    }
    
    public String list_dashboard() {
        list();
        if(null!=objList){//数据处理
            for(OptFileTransferReceive  file:  objList){
                String name=CodeRepositoryUtil.getValue("unitcode",CodeRepositoryUtil.getPrimaryUnit(file.getSenderCode()));
                
                file.setReceiverName(name);
            }
            
        }
       return "list_dashboard"; 
    }

    public String getShow_type() {
        return show_type;
    }

    public void setShow_type(String show_type) {
        this.show_type = show_type;
    }
    
    
}
