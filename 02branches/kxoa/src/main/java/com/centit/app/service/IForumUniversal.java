package com.centit.app.service;

import java.util.List;

import com.centit.app.po.Reply;
import com.centit.core.utils.PageDesc;


/**
 * 
 * 外部调用讨论版通用接口
 * 
 * @author sx
 * @create 2013-9-12
 * @version
 */
public interface IForumUniversal {
	/**
     * 对任意资源进行回复
     * @param boardcode 回复资源的模块，套用optid
     * @param resourceId 回复资源的主键，模块中唯一
     * @param usercode 用户代码
     * @param reply 回复内容
     * @param annexsId 附件id
     */
    void saveReply(String boardcode, String resourceId, String usercode, String reply, String... annexsId);
    
    /**
     * 对帖子资源进行回复，配置saveThread方法获取的threadId进行
     * @param threadId 帖子Id
     * @param usercode 用户代码
     * @param reply 回复内容
     * @param annexsId 附件Id
     */
    void saveReply(Long threadId, String usercode, String reply, String... annexsId);
    
    /**
     * 外部接口调用回复
     * @param boardcode 回复资源的模块，套用optid
     * @param resourceId 回复资源的主键，模块中唯一
     * @param pageDesc 分页
     * @return
     */
    List<Reply> listReplyByExternalUse(String boardcode, String resourceId, PageDesc pageDesc);
    
    /**
     * 外部调用时在指定的讨论版中发出一条帖子
     * @param boardcode 所属版块代码
     * @param forumName 讨论版名称
     * @param title 帖子标题
     * @param content 帖子内容
     * @param usercode 所有人
     * @param threadLock 帖子是否锁定
     * @param threadReply 帖子是否可回复
     * @param annexsId 附件Id
     * @return 帖子Id
     */
    Long saveThread(String boardcode, String forumName, String title, String content, String usercode, boolean threadLock, boolean threadReply, String... annexsId);
    
    /**
     * 外部调用时在指定的讨论版中发出一条帖子
     * @param boardcode 所属版块代码
     * @param forumName 讨论版名称
     * @param title 帖子标题
     * @param content 帖子内容
     * @param usercode 所有人
     * 默认 帖子未锁定 直接发布，任何人都可见
     * 默认 帖子任意人均可回复
     * @param annexsId 附件Id
     * @return 帖子Id
     */
    Long saveThread(String boardcode, String forumName, String title, String content, String usercode, String... annexsId);
    
    /**
     * 外部调用时在指定的讨论版中发出一条帖子
     * @param boardcode 所属版块代码,讨论版名称 与所属版块一致，适用于所属版块只有一个讨论区的情况
     * @param title 帖子标题
     * @param content 帖子内容
     * @param usercode 所有人
     * @param threadLock 帖子是否锁定
     * @param threadReply 帖子是否可回复
     * @param annexsId 附件Id
     * @return 帖子Id
     */
    Long saveThread(String boardcode, String title, String content, String usercode, boolean threadLock, boolean threadReply, String... annexsId);
    /**
     * 外部调用时在指定的讨论版中发出一条帖子
     * @param boardcode 所属版块代码,讨论版名称 与所属版块一致，适用于所属版块只有一个讨论区的情况
     * @param title 帖子标题
     * @param content 帖子内容
     * @param usercode 所有人
     * 默认 帖子未锁定 直接发布，任何人都可见
     * 默认 帖子任意人均可回复
     * @param annexsId 附件Id
     * @return 帖子Id
     */
    Long saveThread(String boardcode, String title, String content, String usercode, String... annexsId);
    
    /**
     * 更新蜚讨论版模块中帖子内容
     * @param threadId 帖子Id
     * @param threadLock 帖子是否锁定
     * @param threadReply 帖子是否可回复
     * @param annexsId 附件Id
     */
    void updateThread(Long threadId, boolean threadLock, boolean threadReply);
    
    /**
     * 删除[是否需要此接口，其实将帖子锁定就不可见]
     * @param threadId 主键
     */
    void deleteThread(Long threadId);
}
