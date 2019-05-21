package com.centit.oa.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;

import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Store;

import net.sf.json.JSONObject;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.centit.app.po.FileinfoFs;
import com.centit.app.service.FileinfoFsManager;
import com.centit.app.util.InstantMsgFactory;
import com.centit.app.util.JavaMailUtils;
import com.centit.core.dao.HQLUtils;
import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.core.utils.PageDesc;
import com.centit.oa.dao.InnermsgDao;
import com.centit.oa.dao.InnermsgRecipientDao;
import com.centit.oa.dao.MsgannexDao;
import com.centit.oa.dao.UserMailConfigDao;
import com.centit.oa.po.Innermsg;
import com.centit.oa.po.InnermsgRecipient;
import com.centit.oa.po.Msgannex;
import com.centit.oa.po.UserMailConfig;
import com.centit.oa.service.InnermsgManager;
import com.centit.support.searcher.DocDesc;
import com.centit.support.searcher.SearchCondition;
import com.centit.support.searcher.Searcher;
import com.centit.support.utils.DatetimeOpt;
import com.centit.support.utils.StringBaseOpt;
import com.centit.sys.po.TaskLog;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.service.MessageSender;
import com.centit.sys.service.TaskLogManager;
import com.centit.sys.service.impl.IndexManagerImpl;
import com.centit.sys.util.SysParametersUtils;
import com.sun.mail.imap.IMAPMessage;

public class InnermsgManagerImpl extends BaseEntityManagerImpl<Innermsg>
        implements InnermsgManager, MessageSender {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    /**
     *
     */

    private static final Log LOG = LogFactory.getLog(InnermsgManagerImpl.class);

    private InnermsgDao innermsgDao;
    private InnermsgRecipientDao innermsgRecipientDao;
    private UserMailConfigDao userMailConfigDao;
    private MsgannexDao msgannexDao;

    private FileinfoFsManager fileinfoFsManager;

    private TaskLogManager taskLogManager;

    public void setTaskLogManager(TaskLogManager taskLogManager) {
        this.taskLogManager = taskLogManager;
    }

    // 即时消息发送接口
    // private IInstantMsg instantMsg = InstantMsgFactory.getInstance();

    public void setFileinfoFsManager(FileinfoFsManager fileinfoFsManager) {
        this.fileinfoFsManager = fileinfoFsManager;
    }

    public void setInnermsgDao(InnermsgDao baseDao) {

        this.innermsgDao = baseDao;
        setBaseDao(this.innermsgDao);
    }

    public void setUserMailConfigDao(UserMailConfigDao userMailConfigDao) {
        this.userMailConfigDao = userMailConfigDao;
    }

    public void setInnermsgRecipientDao(
            InnermsgRecipientDao innermsgRecipientDao) {
        this.innermsgRecipientDao = innermsgRecipientDao;
    }

    @Override
    public void saveMsg(Innermsg innermsg) {
        String pattern = "\\s*,\\s*";
        String recMailType = "I";// 收件箱mail_type（编辑草稿还是收件（已发送））
        if (null != innermsg.getMailtype()
                && innermsg.getMailtype().equals(Innermsg.MAIL_TYPE_D))
            recMailType = "D";

        String[] to = StringUtils.hasText(innermsg.getTo()) ? innermsg.getTo()
                .trim().split(pattern) : null;
        String[] cc = StringUtils.hasText(innermsg.getCc()) ? innermsg.getCc()
                .trim().split(pattern) : null;
        String[] bcc = StringUtils.hasText(innermsg.getBcc()) ? innermsg
                .getBcc().trim().split(pattern) : null;

        if (null == innermsg.getMsgcode())
            innermsg.setMsgcode(this.innermsgDao.getNextMsgCode());
        else
            // 第二次编辑草稿箱中某发件时，该发件对应的原来收件编辑前要全部删除
            this.innermsgRecipientDao.deleteAllMsgByMsgCode(innermsg
                    .getMsgcode());
        // innermsg.setHoldusers(new Long(to.length+cc.length+bcc.length + 1));
        // 已读
        innermsg.setMsgstate(CodeRepositoryUtil.getValue(Innermsg.MSG_STAT,
                Innermsg.MSG_STAT_R));
        // Set<InnermsgRecipient> irs = new HashSet<InnermsgRecipient>();
        // 消息 msgtype
        if ("P".equals(innermsg.getMsgtype())) {
            // 除收件人外附件抄送密送字段
            innermsg.getInnermsgRecipients().addAll(
                    this.listSenderMsg(innermsg, to, CodeRepositoryUtil
                            .getValue(Innermsg.RECIPIENT_TYPE,
                                    Innermsg.RECIPIENT_TYPE_T), recMailType,
                            CodeRepositoryUtil.getValue(Innermsg.MSG_STAT,
                                    Innermsg.MSG_STAT_U)));
            innermsg.getInnermsgRecipients().addAll(
                    this.listSenderMsg(innermsg, cc, CodeRepositoryUtil
                            .getValue(Innermsg.RECIPIENT_TYPE,
                                    Innermsg.RECIPIENT_TYPE_C), recMailType,
                            CodeRepositoryUtil.getValue(Innermsg.MSG_STAT,
                                    Innermsg.MSG_STAT_U)));
            innermsg.getInnermsgRecipients().addAll(
                    this.listSenderMsg(innermsg, bcc, CodeRepositoryUtil
                            .getValue(Innermsg.RECIPIENT_TYPE,
                                    Innermsg.RECIPIENT_TYPE_B), recMailType,
                            CodeRepositoryUtil.getValue(Innermsg.MSG_STAT,
                                    Innermsg.MSG_STAT_U)));
        }

        // 文件群发 msgtype
        if ("F".equals(innermsg.getMsgtype())) {
            innermsg.getInnermsgRecipients().addAll(
                    this.listSenderMsg(innermsg, to, CodeRepositoryUtil
                            .getValue(Innermsg.RECIPIENT_TYPE,
                                    Innermsg.MSG_TYPE_F), recMailType,
                            CodeRepositoryUtil.getValue(Innermsg.MSG_STAT,
                                    Innermsg.MSG_STAT_U)));
        }
        // 消息附件
        innerMsgannex(innermsg);
        super.saveObject(innermsg);
    }

    @Override
    public void saveSimpleMsg(Innermsg innermsg) {
        Set<String> userCodes = new HashSet<String>();
        if (Pattern.matches(".*,.*", innermsg.getReceiveUserCode())) {
            userCodes.addAll(Arrays.asList(innermsg.getReceiveUserCode().split(
                    "\\s*,\\s*")));
        } else {
            userCodes.add(innermsg.getReceiveUserCode());
        }
        if (null == innermsg.getMsgcode())
            innermsg.setMsgcode(this.innermsgDao.getNextMsgCode());
        else
            // 第二次编辑草稿箱中某发件时，该发件对应的原来收件编辑前要全部删除
            this.innermsgRecipientDao.deleteAllMsgByMsgCode(innermsg
                    .getMsgcode());
        innermsg.setHoldusers(Long.valueOf(userCodes.size() + 1));
        // 已读
        innermsg.setMsgstate(CodeRepositoryUtil.getValue(Innermsg.MSG_STAT,
                Innermsg.MSG_STAT_R));

        Set<InnermsgRecipient> irs = new HashSet<InnermsgRecipient>();
        // 消息 msgtype
        if ("P".equals(innermsg.getMsgtype())) {
            for (String userCode : userCodes) {
                InnermsgRecipient ir = new InnermsgRecipient(
                        this.innermsgRecipientDao.getNextMsgRecipiCode(),
                        innermsg, userCode, CodeRepositoryUtil.getValue(
                                Innermsg.MSG_TYPE, Innermsg.MSG_TYPE_P),
                        CodeRepositoryUtil.getValue(Innermsg.MSG_STAT,
                                Innermsg.MSG_STAT_U));
                if (null != innermsg.getMailtype()
                        && innermsg.getMailtype().equals(Innermsg.MAIL_TYPE_D))
                    ir.setMailtype("D");
                else
                    ir.setMailtype("I");
                irs.add(ir);
            }
        }

        // 文件群发 msgtype
        if ("F".equals(innermsg.getMsgtype())) {
            for (String userCode : userCodes) {
                InnermsgRecipient ir = new InnermsgRecipient(
                        this.innermsgRecipientDao.getNextMsgRecipiCode(),
                        innermsg, userCode, CodeRepositoryUtil.getValue(
                                Innermsg.MSG_TYPE, Innermsg.MSG_TYPE_F),
                        CodeRepositoryUtil.getValue(Innermsg.MSG_STAT,
                                Innermsg.MSG_STAT_U));
                ir.setMailtype("I");
                irs.add(ir);
            }
        }

        // 消息 公告 msgtype
        if ("A".equals(innermsg.getMsgtype())) {
            InnermsgRecipient ir = new InnermsgRecipient(
                    this.innermsgRecipientDao.getNextMsgRecipiCode(), innermsg,
                    "ALL", CodeRepositoryUtil.getValue(Innermsg.MSG_TYPE,
                            Innermsg.MSG_TYPE_A), CodeRepositoryUtil.getValue(
                            Innermsg.MSG_STAT, Innermsg.MSG_STAT_U));
            ir.setMailtype("I");
            irs.add(ir);
        }

        innermsg.setInnermsgRecipients(irs);

        // 消息附件
        innerMsgannex(innermsg);
        super.saveObject(innermsg);
        // this.msgannexDao.batchSaveObject(new
        // ArrayList<Msgannex>(innermsg.getMsgannexs()));

        // InstantMsgFactory.getInstance().push(new
        // ArrayList<String>(userCodes), innermsg.getMsgtitle());

        // 添加全文索引
        JSONObject jsonObjectOptId = new JSONObject();
        jsonObjectOptId.put("msgcode", innermsg.getMsgcode());

        DocDesc threadDocDesc = new DocDesc(DocDesc.ResType.OPT, "MSG",
                innermsg.getSender(), jsonObjectOptId.toString(),
                innermsg.getMsgtitle(), null, "view", "msgcode="
                        + innermsg.getMsgcode());

        JSONObject jsonObject = JSONObject.fromObject(threadDocDesc);
        jsonObject.put(IndexManagerImpl.QUERY_CONTENT_JSON,
                "select t.msgcontent from M_INNERMSG t where t.msgcode = '"
                        + innermsg.getMsgcode() + "'");

        jsonObject.put(IndexManagerImpl.INDEX_TYPE,
                IndexManagerImpl.INDEX_TYPE_ADD);

        TaskLog taskLog = new TaskLog("MsgToIndex", jsonObject.toString());

        taskLogManager.saveObject(taskLog);
    }

    @Override
    public void saveAnnouncement(Innermsg innermsg) {
        this.innerMsgannex(innermsg);
        super.saveObject(innermsg);
        InstantMsgFactory.getInstance().pushAll(innermsg.getMsgtitle());
    }

    @Override
    public void deleteObject(Innermsg o) {
        List<String> paths = this.getInnermsgAnnexPaths(o);
        try {
            super.deleteObject(o);
            if (!(CollectionUtils.isEmpty(paths))) {
                this.deleteInnermsgAnnex(paths);
            }
        } catch (RuntimeException e) {
            throw e;
        }
    }

    @Override
    public void deleteMsg(Innermsg innermsg, String mailType) {
        long holdUsers = this.innermsgRecipientDao
                .countHoldUsers(new InnermsgRecipient(innermsg));
        Innermsg imsg = this.getObject(innermsg);
        // 发件
        if (CodeRepositoryUtil.getValue(Innermsg.MAIL_TYPE,
                Innermsg.MAIL_TYPE_O).equals(mailType)
                || CodeRepositoryUtil.getValue(Innermsg.MAIL_TYPE,
                        Innermsg.MAIL_TYPE_D).equals(mailType)) {

            // 收件人已无人持有此信息，删除
            if (0 == holdUsers) {
                List<String> paths = this.getInnermsgAnnexPaths(innermsg);
                try {
                    this.deleteObject(imsg);
                    if (!(CollectionUtils.isEmpty(paths))) {
                        this.deleteInnermsgAnnex(paths);
                    }
                } catch (RuntimeException e) {
                    throw e;
                }

            } else {
                // 持有数-1，将此消息在发件人位置不可见
                imsg.setHoldusers(holdUsers - 1);
                imsg.setMsgstate(CodeRepositoryUtil.getValue(Innermsg.MSG_STAT,
                        Innermsg.MSG_STAT_D));

                this.saveObject(imsg);
            }

            // 收件人删除消息
        } else if (CodeRepositoryUtil.getValue(Innermsg.MAIL_TYPE,
                Innermsg.MAIL_TYPE_I).equals(mailType)) {
            // 当前人为最后一个持有人
            InnermsgRecipient ir = new InnermsgRecipient(imsg,
                    innermsg.getReceiveUserCode());
            this.innermsgRecipientDao.delete(ir);
            if (1 == holdUsers) {
                // 发件人已删除此条消息

                // 或邮件删除
                if (CodeRepositoryUtil.getValue(Innermsg.MSG_STAT,
                        Innermsg.MSG_STAT_D).equals(imsg.getMsgstate())) {
                    List<String> paths = this.getInnermsgAnnexPaths(innermsg);

                    try {
                        this.deleteObject(imsg);

                        // 删除索引
                        if (!CollectionUtils.isEmpty(imsg.getMsgannexs())) {
                            for (Msgannex msgannex : imsg.getMsgannexs()) {
                                delToIndex(imsg, msgannex);
                            }
                        } else {
                            delToIndex(imsg, null);
                        }

                        if (!(CollectionUtils.isEmpty(paths))) {
                            this.deleteInnermsgAnnex(paths);
                        }
                    } catch (RuntimeException e) {
                        throw e;
                    }
                }
            }
        }

    }

    private void delToIndex(Innermsg imsg, Msgannex msgannex) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msgcode", imsg.getMsgcode());
        if (null != msgannex) {
            jsonObject.put("filecode", msgannex.getFileinfo().getFilecode());
        }
        DocDesc docDesc = new DocDesc();
        docDesc.setDataID(jsonObject.toString());

        jsonObject = JSONObject.fromObject(docDesc);

        jsonObject.put(IndexManagerImpl.INDEX_TYPE,
                IndexManagerImpl.INDEX_TYPE_DEL);

        TaskLog taskLog = new TaskLog("MsgDelToIndex", jsonObject.toString());
        taskLogManager.saveObject(taskLog);
    }

    @Override
    public void deleteMsgs(List<Innermsg> innermsgs, String mailType) {
        for (Innermsg innermsg : innermsgs) {
            deleteMsg(innermsg, mailType);
        }

    }

    @Override
    public void dropMsgs(List<String> pk, String loginUserCode) {
        innermsgDao.dropMsgs(pk, loginUserCode);
    }

    @Override
    public void cancleDropMsgs(List<String> pk, String loginUserCode) {
        innermsgDao.cancleDropMsgs(pk, loginUserCode);
    }

    @Override
    public void updateMailBox(List<Innermsg> innermsgs) {
        for (Innermsg innermsg : innermsgs) {
            if (innermsg.getMailtype().equalsIgnoreCase(
                    innermsg.getMailUnDelType())) {

                deleteObject(innermsg);

            } else if (CodeRepositoryUtil.getValue(Innermsg.MAIL_TYPE, "I")
                    .equalsIgnoreCase(innermsg.getMailtype())
                    || CodeRepositoryUtil.getValue(Innermsg.MAIL_TYPE, "O")
                            .equalsIgnoreCase(innermsg.getMailtype())) {
                // To 废件箱

                innermsg.setMailtype(CodeRepositoryUtil.getValue(
                        Innermsg.MAIL_TYPE, "T"));
                innermsg.setMailUnDelType(innermsg.getMailtype());

                saveObject(innermsg);

            } else {
                // 恢复
                innermsg.setMailtype(innermsg.getMailUnDelType());
                innermsg.setMailUnDelType(null);

                saveObject(innermsg);

            }
        }

    }

    @Override
    public void updateMailBox(List<Innermsg> innermsgs, String to) {
        for (Innermsg innermsg : innermsgs) {
            innermsg.setMailtype(to);
            // innermsg.setMailUnDelType(null);

            saveObject(innermsg);
        }

    }

    @Override
    public void deleteMail(Innermsg innermsg) {
        List<String> paths = this.getInnermsgAnnexPaths(innermsg);

        try {
            this.deleteObject(innermsg);
            if (!(CollectionUtils.isEmpty(paths))) {
                this.deleteInnermsgAnnex(paths);
            }
        } catch (RuntimeException e) {
            throw e;
        }
    }

    /**
     * 获取附件路径
     * 
     * @param o
     * @return
     */
    @SuppressWarnings("static-method")
    private List<String> getInnermsgAnnexPaths(Innermsg o) {
        if (CollectionUtils.isEmpty(o.getMsgannexs())) {
            return new ArrayList<String>();
        }

        List<String> paths = new ArrayList<String>();
        for (Msgannex annex : o.getMsgannexs()) {
            paths.add(annex.getFileinfo().getPath());

        }

        return paths;
    }

    /**
     * 删除消息附件
     * 
     * @param imsg
     */
    private void deleteInnermsgAnnex(List<String> paths) {
        for (String path : paths) {
            this.fileinfoFsManager.deleteFileOnDisk(path);
        }
    }

    @Override
    public void saveSendMail(Innermsg innermsg) {
        String pattern = "\\s*;\\s*";
        UserMailConfig c = this.userMailConfigDao.getObjectById(innermsg
                .getEmailid());
        if (null == c) {
            LOG.error("发送邮箱账号为空");
            return;
        }
        String[] to = StringUtils.hasText(innermsg.getTo()) ? verificationMailAccount(innermsg
                .getTo().trim().split(pattern))
                : null;
        String[] cc = StringUtils.hasText(innermsg.getCc()) ? verificationMailAccount(innermsg
                .getCc().trim().split(pattern))
                : null;
        String[] bcc = StringUtils.hasText(innermsg.getBcc()) ? verificationMailAccount(innermsg
                .getBcc().trim().split(pattern))
                : null;

        if (ArrayUtils.isEmpty(to) && ArrayUtils.isEmpty(cc)
                && ArrayUtils.isEmpty(bcc)) {
            LOG.error("收件人账户为空");

            return;
        }

        // 消息附件
        innermsg = this.innerMsgannex(innermsg);
        String uid = null;

        // 索引
        List<FileinfoFs> fileinfos = new ArrayList<FileinfoFs>();
        List<DocDesc> docDescs = new ArrayList<DocDesc>();

        // 发送邮件
        if (CodeRepositoryUtil.getValue(Innermsg.MAIL_TYPE,
                Innermsg.MAIL_TYPE_O).equalsIgnoreCase(innermsg.getMailtype())) {
            JavaMailSenderImpl sender = (JavaMailSenderImpl) JavaMailUtils
                    .getJavaMailSender(c.getSmtpurl(), c.getMailaccount(),
                            StringBaseOpt.decryptBase64Des(c.getMailpassword()));
            sender.setPort(c.getSmtpport().intValue());
            Properties p = new Properties();
            p.put("mail.smtp.auth", "true");

            sender.setJavaMailProperties(p);

            try {
                Map<String, File> attachment = new HashMap<String, File>();
                if (!CollectionUtils.isEmpty(innermsg.getMsgannexs())) {
                    for (Msgannex msgannex : innermsg.getMsgannexs()) {
                        attachment.put(msgannex.getFileinfo().getFilename(),
                                new File(SysParametersUtils.getUploadHome()
                                        + msgannex.getFileinfo().getPath()));

                        // 索引
                        fileinfos.add(msgannex.getFileinfo());
                        DocDesc annexDocDesc = new DocDesc(
                                DocDesc.ResType.FILE, "MSG",
                                innermsg.getSender(), "MAIL_FILE"
                                        + msgannex.getFileinfo().getFilecode(),
                                innermsg.getMsgtitle(), null, "view",
                                "msgcode=" + innermsg.getMsgcode());

                        docDescs.add(annexDocDesc);
                    }
                }

                uid = JavaMailUtils
                        .sendMimeMessage(sender, innermsg.getMsgtitle(),
                                innermsg.getMsgcontent(), c.getMailaccount(),
                                to, cc, bcc, CollectionUtils
                                        .isEmpty(attachment) ? null
                                        : attachment, false);
            } catch (UnsupportedEncodingException e) {
                LOG.error("用户 " + c.getUsercode() + " 账户 " + c.getMailaccount()
                        + " 发送邮件失败", e);

                throw new RuntimeException("发送邮件失败");

            } catch (MessagingException e) {
                LOG.error("用户 " + c.getUsercode() + " 账户 " + c.getMailaccount()
                        + " 发送邮件失败", e);

                throw new RuntimeException("发送邮件失败");
            }

            if (null == uid) {
                throw new RuntimeException("发送邮件失败");
            }

            if (!StringUtils.hasText(innermsg.getMsgcode())) {
                innermsg.setMsgcode(uid);
            }
        }

        // 保存草稿箱

        innermsg.setMsgtype(CodeRepositoryUtil.getValue(Innermsg.MSG_TYPE,
                Innermsg.MSG_TYPE_M));
        innermsg.setMsgstate(CodeRepositoryUtil.getValue(Innermsg.MSG_STAT,
                null == uid ? Innermsg.MSG_STAT_R : Innermsg.MSG_STAT_S));
        // innermsg.setMailtype(CodeRepositoryUtil.getValue(Innermsg.MAIL_TYPE,
        // Innermsg.MAIL_TYPE_O));

        innermsg.setSenddate(new Date());

        innermsg.getInnermsgRecipients().clear();
        innermsg.getInnermsgRecipients().addAll(
                listSenderMail(innermsg, to, CodeRepositoryUtil.getValue(
                        Innermsg.RECIPIENT_TYPE, Innermsg.RECIPIENT_TYPE_T)));
        innermsg.getInnermsgRecipients().addAll(
                listSenderMail(innermsg, cc, CodeRepositoryUtil.getValue(
                        Innermsg.RECIPIENT_TYPE, Innermsg.RECIPIENT_TYPE_C)));
        innermsg.getInnermsgRecipients().addAll(
                listSenderMail(innermsg, bcc, CodeRepositoryUtil.getValue(
                        Innermsg.RECIPIENT_TYPE, Innermsg.RECIPIENT_TYPE_B)));

        innermsg.setC(c);

        super.saveObject(innermsg);

        // 索引
        fileinfoFsManager.update4ConfirmFileinfos(fileinfos, docDescs);

        innermsgDao.getHibernateTemplate().flush();
        innermsgDao.getHibernateTemplate().clear();
    }

    /**
     * 消息附件
     * 
     * @param innermsg
     * @return
     */
    private Innermsg innerMsgannex(Innermsg innermsg) {
        List<FileinfoFs> fileinfos = new ArrayList<FileinfoFs>();
        List<DocDesc> docDescs = new ArrayList<DocDesc>();
        if (!CollectionUtils.isEmpty(innermsg.getMsgannexs())) {
            for (Msgannex o : innermsg.getMsgannexs()) {
                o.setInnermsg(innermsg);
                o.setMsgannexId(this.innermsgDao.getNextMsgCode());

                fileinfos.add(o.getFileinfo());

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("msgcode", innermsg.getMsgcode());
                jsonObject.put("filecode", o.getFileinfo().getFilecode());

                DocDesc annexDocDesc = new DocDesc(DocDesc.ResType.FILE, "MSG",
                        innermsg.getSender(), jsonObject.toString(),
                        innermsg.getMsgtitle(), null, "view", "id="
                                + innermsg.getMsgcode());

                docDescs.add(annexDocDesc);

            }

            // 调用文件接口，确认文件上传成功
            if (!CollectionUtils.isEmpty(fileinfos)) {
                this.fileinfoFsManager.update4ConfirmFileinfos(fileinfos,
                        docDescs);
            }
        }

        return innermsg;
    }

    private void receiveMail(Innermsg innermsg, Message msg, String mailUID,
            UserMailConfig c) throws MessagingException, IOException {
        String pattern = "\\s*,\\s*";
        Innermsg imsg = new Innermsg(mailUID);

        imsg.setReceiveUserCode(innermsg.getReceiveUserCode());

        // 收件箱
        imsg.setMailtype(CodeRepositoryUtil.getValue(Innermsg.MAIL_TYPE,
                Innermsg.MAIL_TYPE_I));
        // 类别为邮件
        imsg.setMsgtype(CodeRepositoryUtil.getValue(Innermsg.MSG_TYPE,
                Innermsg.MSG_TYPE_M));
        // 邮件状态 未读
        imsg.setMsgstate(CodeRepositoryUtil.getValue(Innermsg.MSG_STAT,
                Innermsg.MSG_STAT_U));

        // String sender = Javam

        // 发件人
        imsg.setSender(JavaMailUtils.getFrom(msg));

        imsg.setMsgtitle(JavaMailUtils.getSubject(msg));

        imsg.setMsgcontent(JavaMailUtils.getText(msg, new StringBuffer()));
        imsg.setTo(JavaMailUtils.getTo(msg));
        imsg.setCc(JavaMailUtils.getCC(msg));
        imsg.setBcc(JavaMailUtils.getBCC(msg));

        imsg.setSenddate(DatetimeOpt.convertStringToDate(
                JavaMailUtils.getSendDate(msg), JavaMailUtils.DATE_PATTERN));

        String[] to = StringUtils.hasText(imsg.getTo()) ? imsg.getTo().trim()
                .split(pattern) : null;
        String[] cc = StringUtils.hasText(imsg.getCc()) ? imsg.getCc().trim()
                .split(pattern) : null;
        String[] bcc = StringUtils.hasText(imsg.getBcc()) ? imsg.getBcc()
                .trim().split(pattern) : null;
        imsg.getInnermsgRecipients().addAll(
                this.listSenderMail(imsg, to, CodeRepositoryUtil.getValue(
                        Innermsg.RECIPIENT_TYPE, Innermsg.RECIPIENT_TYPE_T)));
        imsg.getInnermsgRecipients().addAll(
                this.listSenderMail(imsg, cc, CodeRepositoryUtil.getValue(
                        Innermsg.RECIPIENT_TYPE, Innermsg.RECIPIENT_TYPE_C)));
        imsg.getInnermsgRecipients().addAll(
                this.listSenderMail(imsg, bcc, CodeRepositoryUtil.getValue(
                        Innermsg.RECIPIENT_TYPE, Innermsg.RECIPIENT_TYPE_B)));

        // 附件
        Map<String, InputStream> attachMent = JavaMailUtils.getAttachMent(msg,
                new HashMap<String, InputStream>());
        for (Map.Entry<String, InputStream> entry : attachMent.entrySet()) {
            FileinfoFs fileinfo = new FileinfoFs();

            fileinfo.setFilename(entry.getKey());
            fileinfo.setFileext(FilenameUtils.getExtension(fileinfo
                    .getFilename()));
            fileinfo.setRecorder(c.getUsercode());
            fileinfo.setRecorderDate(new Date());

            fileinfo = this.fileinfoFsManager.saveFileinfo(fileinfo,
                    entry.getValue(), "EMAIL");
            imsg.getMsgannexs().add(
                    new Msgannex(this.innermsgDao.getNextMsgCode(), imsg,
                            fileinfo));
        }

        imsg.setC(c);

        super.saveObject(imsg);
    }

    /**
     * Pop3 InBox Message
     * 
     * @param c
     * @return
     * @throws MessagingException
     */
    @SuppressWarnings({ "static-method", "unused" })
    private Message[] listPop3Message(UserMailConfig c, Folder folder)
            throws MessagingException {
        Store store = JavaMailUtils.getPop3Store(c.getReceiveurl(), c
                .getReceiveport().intValue(), c.getMailaccount(), StringBaseOpt
                .decryptBase64Des(c.getMailpassword()));
        folder = JavaMailUtils.getFolder(store);

        return JavaMailUtils.getMessage(folder);
    }

    @SuppressWarnings("static-method")
    private Message[] listImapMessage(Folder folder) throws MessagingException {
        Message[] message = JavaMailUtils.getMessage(folder);
        List<Message> msgs = new ArrayList<Message>();

        IMAPMessage imapMsg = null;
        for (Message msg : message) {
            imapMsg = (IMAPMessage) msg;
            // 标示未读邮件
            if (!imapMsg.isSet(Flag.SEEN)) {

                msgs.add(msg);
            }
        }

        return msgs.toArray(new Message[msgs.size()]);
    }

    @SuppressWarnings("static-method")
    private Folder getImapFolder(UserMailConfig c) throws MessagingException {
        Store store = JavaMailUtils.getImapStore(c.getReceiveurl(),
                c.getMailaccount(),
                StringBaseOpt.decryptBase64Des(c.getMailpassword()));
        Folder folder = JavaMailUtils.getFolder(store);
        return folder;
    }

    @Override
    public int saveReceiveMail(Innermsg innermsg) {

        UserMailConfig c = this.userMailConfigDao.getObjectById(innermsg
                .getEmailid());
        String lastUID = null;

        int length = 0;
        try {
            Folder folder = null;
            Message[] message = null;

            if (CodeRepositoryUtil.getValue("MAIL_RECEIVE_TYP", "P")
                    .equalsIgnoreCase(c.getMailreceivetype())) {
                // message = this.listPop3Message(c, folder);
                Store store = JavaMailUtils.getPop3Store(c.getReceiveurl(), c
                        .getReceiveport().intValue(), c.getMailaccount(),
                        StringBaseOpt.decryptBase64Des(c.getMailpassword()));
                folder = JavaMailUtils.getFolder(store);

                message = JavaMailUtils.getMessage(folder);
            } else if (CodeRepositoryUtil.getValue("MAIL_RECEIVE_TYP", "I")
                    .equalsIgnoreCase(c.getMailreceivetype())) {
                folder = this.getImapFolder(c);
                if (0 == folder.getNewMessageCount()) {
                    return length;
                }
                message = this.listImapMessage(folder);
            } else {
                throw new RuntimeException("收取邮件协议配置错误");
            }

            if (ArrayUtils.isEmpty(message)) {
                throw new RuntimeException("无新邮件");
            }

            for (Message msg : message) {
                String mailUID = null;
                try {
                    mailUID = JavaMailUtils.getMessageUID(msg.getFolder(), msg);
                    if (CodeRepositoryUtil.getValue("MAIL_RECEIVE_TYP", "I")
                            .equalsIgnoreCase(c.getMailreceivetype())) {
                        // 防止 与 序列 重复ID
                        mailUID = "IMAP_" + mailUID;
                    }
                    length++;
                } catch (MessagingException e) {
                    continue;
                }

                // IMAP 不需要比对UID
                if ((!StringUtils.hasText(mailUID) || (StringUtils.hasText(c
                        .getLastUID()) && mailUID.compareTo(c.getLastUID()) < 1))
                        && CodeRepositoryUtil.getValue("MAIL_RECEIVE_TYP", "P")
                                .equalsIgnoreCase(c.getMailreceivetype())) {
                    continue;
                }

                try {
                    receiveMail(innermsg, msg, mailUID, c);
                } catch (Exception e) {
                    length--;
                    LOG.error(
                            "用户 " + c.getUsercode() + " 账户 "
                                    + c.getMailaccount() + " 接收邮件出现错误", e);
                }

                // 标志 SEE及DELETE
                msg.setFlag(Flag.SEEN, true);
                msg.setFlag(Flag.DELETED, true);

                lastUID = mailUID;

                folder = msg.getFolder();

            }

            JavaMailUtils.close(folder.getStore(), folder, true);

        } catch (MessagingException e) {
            LOG.error("用户 " + c.getUsercode() + " 账户 " + c.getMailaccount()
                    + " 接收邮件失败", e);

            throw new RuntimeException("接收邮件失败");
        }

        // 更新此邮箱最新拉取的邮件
        if (StringUtils.hasText(lastUID)
                && CodeRepositoryUtil.getValue("MAIL_RECEIVE_TYP", "P")
                        .equalsIgnoreCase(c.getMailreceivetype())) {
            c.setLastUID(lastUID);

            this.userMailConfigDao.saveObject(c);
        }

        return length;
    }

    private List<InnermsgRecipient> listSenderMail(Innermsg innermsg,
            String[] account, String recipientType) {
        if (ArrayUtils.isEmpty(account)) {
            return new ArrayList<InnermsgRecipient>();
        }
        List<InnermsgRecipient> irs = new ArrayList<InnermsgRecipient>();
        for (String acc : account) {
            InnermsgRecipient ir = new InnermsgRecipient(
                    innermsgRecipientDao.getNextMsgRecipiCode(), innermsg, acc,
                    CodeRepositoryUtil.getValue(Innermsg.MSG_TYPE,
                            Innermsg.MSG_TYPE_M), CodeRepositoryUtil.getValue(
                            Innermsg.MSG_STAT, Innermsg.MSG_STAT_U));
            ir.setMailtype(recipientType);
            irs.add(ir);
        }

        return irs;
    }

    /**
     * 
     * @param innermsg
     * @param account
     * @param recipientType
     *            接收人类别
     * @param mailType
     *            邮件类型
     * @param msgState
     *            状态
     * @return
     */
    private List<InnermsgRecipient> listSenderMsg(Innermsg innermsg,
            String[] account, String recipientType, String mailType,
            String msgState) {
        if (ArrayUtils.isEmpty(account)) {
            return new ArrayList<InnermsgRecipient>();
        }
        List<InnermsgRecipient> irs = new ArrayList<InnermsgRecipient>();
        for (String acc : account) {
            InnermsgRecipient ir = new InnermsgRecipient(
                    innermsgRecipientDao.getNextMsgRecipiCode(), innermsg, acc,
                    recipientType, CodeRepositoryUtil.getValue(
                            Innermsg.MAIL_TYPE, mailType),
                    CodeRepositoryUtil.getValue(Innermsg.MSG_STAT, msgState));
            irs.add(ir);
        }

        return irs;
    }

    /**
     * 验证邮箱账户
     * 
     * @param account
     * @return
     */
    private static String[] verificationMailAccount(String[] account) {
        // Pattern p =
        // Pattern.compile("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$");
        List<String> l = new ArrayList<String>();
        for (String acc : account) {
            // if (p.matcher(acc).find()) {
            l.add(acc);
            // }
        }

        return l.toArray(new String[l.size()]);
    }

    public void setMsgannexDao(MsgannexDao msgannexDao) {
        this.msgannexDao = msgannexDao;
    }

    // public List<Innermsg> listReplyMsgs(Long replymsgcode){
    //
    // return innermsgDao.listMsgsByReplycode(replymsgcode);
    // }
    //
    // public List<Innermsg> listMyMsgs(String receive){
    // return innermsgDao.listMsgsByReceive(receive);
    // }
    //
    // public boolean sendMessage(String sender, String receiver,
    // String msgSubject, String msgContent) {
    // Innermsg innermsg = new Innermsg();
    // innermsg.setSender(sender);
    // //innermsg.setReceive(receiver);
    // innermsg.setMsgtitle(msgSubject);
    // innermsg.setMsgcontent(msgContent);
    // innermsg.setSenddate(DatetimeOpt.currentSqlDate());
    // innermsg.setMsgstate("U");
    // //innermsg.setReceivetype("P");
    //
    // innermsgDao.saveObject(innermsg);
    // return true;
    // }
    //
    // public void saveInnermsg(Innermsg innermsg) {
    // Long msgCode = innermsg.getMsgcode();
    // if(msgCode == null || msgCode == 0){
    // msgCode = innermsgDao.getNextMsgCode();
    // innermsg.setMsgcode(msgCode);
    // }
    //
    // //获取文件编号，解析并封装Innermsg
    // String[] codeArr = innermsg.getFileCodes();
    // if (codeArr != null) {
    // for (String fileCode : codeArr) {
    // Msgannex msgannex = new Msgannex();
    // msgannex.setMsgcode(msgCode);
    // msgannex.setFilecode(fileCode);
    // innermsg.addMsgannex(msgannex);
    // }
    // }
    //
    // innermsgDao.saveObject(innermsg);
    // }
    //
    // public List<FileInfo> listFilesByMsg(Long msgcode) {
    // return fileinfoDao.listFilesByMsg(msgcode);
    // }
    //
    // public FileInfoDao getFileinfoDao() {
    // return fileinfoDao;
    // }
    //
    // public void setFileinfoDao(FileInfoDao fileInfoDao) {
    // this.fileinfoDao = fileInfoDao;
    // }

    @Override
    public void saveSendMail(String emailid, List<String> to, List<String> cc,
            String title, String content, String... filecodes) {
        Innermsg innermsg = new Innermsg();
        innermsg.setEmailid(emailid);

        if (!CollectionUtils.isEmpty(to)) {
            innermsg.setTo(StringUtils.collectionToDelimitedString(to, ";"));
        }
        if (!CollectionUtils.isEmpty(cc)) {
            innermsg.setCc(StringUtils.collectionToDelimitedString(cc, ";"));
        }
        innermsg.setMailtype(CodeRepositoryUtil.getValue(Innermsg.MAIL_TYPE,
                Innermsg.MAIL_TYPE_O));

        innermsg.setMsgtitle(title);
        innermsg.setMsgcontent(content);

        getMsgannexs(innermsg, filecodes);
        saveSendMail(innermsg);
    }

    private void getMsgannexs(Innermsg innermsg, String... filecodes) {
        if (ArrayUtils.isNotEmpty(filecodes)) {
            for (String id : filecodes) {
                innermsg.addMsgannex(new Msgannex(null, fileinfoFsManager
                        .getObjectById(id)));
            }
        }
    }

    @Override
    public void saveSendMessage(String senderUsercode,
            List<String> receiverUsercode, String title, String content,
            String... filecodes) {
        Innermsg innermsg = new Innermsg();
        innermsg.setSender(senderUsercode);
        innermsg.setSenddate(new Date());
        innermsg.setReceiveUserCode(StringUtils.collectionToDelimitedString(
                receiverUsercode, ","));
        innermsg.setMsgtitle(title);
        innermsg.setMsgcontent(content);

        getMsgannexs(innermsg, filecodes);
        saveMsg(innermsg);
    }

    @Override
    public List<Innermsg> listBySearch(String usercode, String key,
            PageDesc pageDesc) {
        SearchCondition cond = new SearchCondition(key);
        cond.setsOpts(new String[] { "MSG" });
        cond.setOwner(usercode);
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

        List<Innermsg> search = innermsgDao.listBySearch(docDescMap.keySet(),
                usercode, pageDesc);

        for (Innermsg innermsg : search) {
            innermsg.setSummary(docDescMap.get(innermsg.getMsgcode())
                    .getSummary());
        }

        return search;
    }

    @Override
    public boolean sendMessage(String sender, String receiver,
            String msgSubject, String msgContent) {

        return false;
    }

    /**
     * 生成内部消息编码
     * 
     * @return String
     */
    @Override
    public String getNextKey() {
        return String.valueOf(innermsgDao.getNextValueOfSequence("S_MSGCODE"));

    }

    @Override
    public void deleteMsgnnex(String msgcode, String filecode) {
        // TODO Auto-generated method stub
        if (StringUtils.hasText(msgcode) && StringUtils.hasText(filecode))
            this.msgannexDao.deleteMsgnnex(msgcode, filecode);
    }

    /**
     * 收件箱列表，排数收件箱邮件类型为删除的T废件箱,邮件类型为草稿的D草稿箱 这样定时发送时也只要修改Innermsg 的邮件状态就好
     */
    public List<Innermsg> listObjects(Map<String, Object> filterMap,
            PageDesc pageDesc, String usercode) {
        // 未读标记对收件箱有效
        String msgstate = "";
        // 邮箱类型（收件箱，废件箱）
        String mailtype = "";
        if (StringUtils.hasText((String) filterMap.get("msgstate"))) {
            msgstate = (String) filterMap.get("msgstate");
            filterMap.remove("msgstate");
        }
        if (StringUtils.hasText((String) filterMap.get("mailtype"))) {
            mailtype = (String) filterMap.get("mailtype");
            filterMap.remove("mailtype");
        }
        String sHql = " from Innermsg t where 1=1 ";
        String hql = "";
        if (StringUtils.hasText(usercode)) {
            hql = "select distinct a.innermsg.msgcode from InnermsgRecipient a  where a.receive="
                    + HQLUtils.buildHqlStringForSQL(usercode)
                    + "  and a.msgstate !='D' ";
        }

        if (StringUtils.hasText(mailtype)) {
            hql += " and a.mailtype ="
                    + HQLUtils.buildHqlStringForSQL(mailtype);
        }

        if (StringUtils.hasText(msgstate)) {
            hql += " and  a.msgstate ="
                    + HQLUtils.buildHqlStringForSQL(msgstate);
        }
        sHql += "and t.msgcode in (" + hql + ")";
        
        if (StringUtils.hasText((String) filterMap.get("msgtype"))) {
            String msgtype = (String) filterMap.get("msgtype");
            sHql += "and t.msgtype = "+ HQLUtils.buildHqlStringForSQL(msgtype); ;
        }
        
        
        //排查在首页已查看提醒 by dk 2016-2-18========================
        String F_userBizopt_log="";
        if(null!=filterMap.get("notInUserBizoptLog")){
            F_userBizopt_log=(String)filterMap.get("F_userBizopt_log");
        }
        if(StringUtils.hasText(F_userBizopt_log)){
            sHql+="and t.msgcode not in (" +
                  "select f.dj_id from F_userBizopt_log f where f.createUser= "+HQLUtils.buildHqlStringForSQL(F_userBizopt_log)+" ) ";
        }
        //=======================================================
        
        List<Innermsg> innermsgList = baseDao.listObjects(sHql, filterMap,
                pageDesc);
        if (StringUtils.hasText(mailtype)) {
            filterMap.put("mailtype", mailtype);
        }
        if (StringUtils.hasText(msgstate)) {
            filterMap.put("msgstate", msgstate);
        }

        return innermsgList;
    }

    @Override
    public void autoSend() {
        innermsgDao.autoSend();

    }

    @Override
    public int getReplayCount(Innermsg innermsg) {

        return innermsgDao.getReplayCount(innermsg);
    }

    @Override
    public int getUnReadRepalyCount(Innermsg innermsg) {
        // TODO Auto-generated method stub
        return innermsgDao.getUnReadRepalyCount(innermsg);
    }
}
