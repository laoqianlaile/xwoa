package com.centit.oa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.CollectionUtils;

import com.centit.oa.dao.InnermsgRecipientDao;
import com.centit.oa.po.Innermsg;
import com.centit.oa.po.InnermsgRecipient;
import com.centit.oa.service.InnermsgRecipientManager;
import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.core.utils.PageDesc;
import com.centit.support.searcher.DocDesc;
import com.centit.support.searcher.SearchCondition;
import com.centit.support.searcher.Searcher;

public class InnermsgRecipientManagerImpl extends
        BaseEntityManagerImpl<InnermsgRecipient> implements
        InnermsgRecipientManager {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory
            .getLog(InnermsgRecipientManager.class);

    private InnermsgRecipientDao innermsgRecipientDao;

    public void setInnermsgRecipientDao(InnermsgRecipientDao baseDao) {
        this.innermsgRecipientDao = baseDao;
        setBaseDao(this.innermsgRecipientDao);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<InnermsgRecipient> listUnReaderInnermsg(InnermsgRecipient ir) {

        return this.innermsgRecipientDao.getHibernateTemplate().findByExample(
                ir);
    }

    @Override
    public void delete(List<String> pk) {
        innermsgRecipientDao.getHibernateTemplate().deleteAll(
                innermsgRecipientDao.listAllByPk(pk));

    }

    @Override
    public List<InnermsgRecipient> listBySearch(String usercode, String key,
            PageDesc pageDesc) {
        SearchCondition cond = new SearchCondition(key);
        cond.setsOpts(new String[] { "MSG" });
        // cond.setOwner(usercode);
        // cond.set

        List<DocDesc> solrSearch = Searcher.solrSearch(cond);

        if (CollectionUtils.isEmpty(solrSearch)) {
            return null;
        }

        Map<String, DocDesc> docDescMap = new HashMap<String, DocDesc>();

        JSONObject jsonObject = null;
        for (DocDesc docDesc : solrSearch) {
            jsonObject = JSONObject.fromObject(docDesc.getDataID());

            docDescMap.put(jsonObject.getString("msgcode"), docDesc);

        }

        List<InnermsgRecipient> search = innermsgRecipientDao.listBySearch(
                docDescMap.keySet(), usercode, pageDesc);

        for (InnermsgRecipient innermsgRecipient : search) {
            innermsgRecipient.getInnermsg().setSummary(
                    docDescMap
                            .get(innermsgRecipient.getInnermsg().getMsgcode())
                            .getSummary());
        }

        return search;
    }

    /**
     * 按接收类别获取各类别人员列表
     * 
     * @param msgCode
     * @param recipientType
     * @return
     */
    public List<InnermsgRecipient> listByRtype(String msgCode,
            String recipientType) {
        return innermsgRecipientDao.listByRtype(msgCode, recipientType);
    }

    @Override
    public void deleteRec(List<String> pk, String loginUserCode) {

        innermsgRecipientDao.deleteRec(pk, loginUserCode);
    }

    @Override
    public void dropRec(List<String> pk, String loginUserCode) {

        innermsgRecipientDao.dropRec(pk, loginUserCode);
    }

    @Override
    public void cancleDropRec(List<String> pk, String loginUserCode) {

        innermsgRecipientDao.cancleDropRec(pk, loginUserCode);
    }

    /**
     * 查看时收件箱与本人有关的该条收件msgstat都更新为已读
     * 
     * @param innermsg
     * @param loginUserCode
     */
    @Override
    public void updateMsgStat(Innermsg innermsg, String loginUserCode) {
        innermsgRecipientDao.updateMsgStat(innermsg, loginUserCode);

    }

}
