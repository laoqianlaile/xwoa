package com.centit.oa.dao;

import java.util.List;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.oa.po.OaInformationAttachment;

public class OaInformationAttachmentDao extends BaseDaoImpl<OaInformationAttachment>{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    @SuppressWarnings("unchecked")
    public List<OaInformationAttachment> findDocAttachments(String refId){
       return (List<OaInformationAttachment>) super.findObjectsByHql("from OaInformationAttachment where refId=? and type=?", new Object[]{refId,OaInformationAttachment.TYPE_DOCUMENT});
    }
    
    @SuppressWarnings("unchecked")
    public List<OaInformationAttachment> findVideoAttachments(String refId){
        return (List<OaInformationAttachment>) super.findObjectsByHql("from OaInformationAttachment where refId=? and type=?", new Object[]{refId,OaInformationAttachment.TYPE_VEDIO});
    }
    
    @SuppressWarnings("unchecked")
    public  List<OaInformationAttachment> findAttachments(String refId){
        return (List<OaInformationAttachment>) super.findObjectsByHql("from OaInformationAttachment where refId=? order by id", new Object[]{refId});
    }
}
