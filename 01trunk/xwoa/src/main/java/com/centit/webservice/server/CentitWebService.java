package com.centit.webservice.server;

public interface CentitWebService {

    /**
     * 接口描述: OA系统提供各类待办事宜获取接口，通过该接口获取设定的需要提醒的待办事项： 新收发文 新通知公告 新领导日程 等
     * 
     * @param userid
     *            用户唯一ID
     * @param type
     *            流程类型，例如：0收文 1发文 2待参加会议 3通知会议。。。等(根据具体业务设置类型)
     * @return JSON
     */

    public String getOfficialCount(String json);

    /**
     *  接口描述 接口返回当前登录人员的个人日程列表信息  支持条件查询  支持分页
     * 
     * @param userid
     *            用户唯一ID
     * @param cycletime
     *            时间周期（1,2,3）
     * @param cycletimetype
     *            时间周期类型（0全部、1周、2月、3季度、4年）
     * @param starttime
     *            起始时间
     * @param currentdatetime
     *            当前记录时间
     * @param pagesize
     *            请求记录条数
     * @param keyword
     *            搜索关键字
     * @return JSON
     */

    public String getPersonalScheduleList(String json);

    /**
     * 接口返回具体某一条日程的明细内容
     * 
     * @param userid
     *            用户唯一ID
     * @param scheduleid
     *            日程id
     * @return JSON
     */

    public String getPersonalScheduleDetail(String json);

    /**
     * 通过该接口，增加一条个人日程
     * 
     * @param userid
     *            用户唯一ID
     * @param title
     *            日程安排标题
     * @param isimportant
     *            是否标注为重要
     * @param scheduleremark
     *            是否标注为重要（0：重要，1：不重要）
     * @param remindtime
     *            提醒时间
     * @param contenturl
     *            正文pdf附件url
     * @return JSON
     */

    public String createPersonalSchedule(String json);

    /**
     * 通过该接口，修改一条已经存在的个人日程
     * 
     * @param userid
     *            用户唯一ID
     * @param scheduleid
     *            日程id
     * @param title
     *            日程安排标题
     * @param isimportant
     *            是否标注为重要
     * @param scheduleremark
     *            是否标注为重要（0：重要，1：不重要）
     * @param remindtime
     *            提醒时间
     * @param contenturl
     *            正文pdf附件url
     * @return JSON
     */

    public String updatePersonalSchedule(String json);

    /**
     * 通过该接口，删除一条已经存在的个人日程
     * 
     * @param userid
     *            用户唯一ID
     * @param scheduleid
     *            日程id
     * @return JSON
     */

    public String deletePersonalSchedule(String json);

    /**
     * 通过该接口获取登录用户权限内可访问的领导日程列表，支持条件查询
     * 
     * @param userid
     *            用户唯一ID
     * @param cycletime
     *            时间周期（1,2,3）
     * @param cycletimetype
     *            时间周期类型（0全部、1周、2月、3季度、4年）
     * @param starttime
     *            起始时间
     * @param currentdatetime
     *            当前记录时间
     * @param pagesize
     *            请求记录条数
     * @param keyword
     *            搜索关键字
     * @return JSON
     */

    public String getLeaderScheduleList(String json);

    /**
     * 通过该接口获取具体某一条领导日程详细信息
     * 
     * @param userid
     *            用户唯一ID
     * @param scheduleid
     *            日程id
     * @return JSON
     */

    public String getLeaderScheduleDetail(String json);

    /**
     * 通过该接口获取个人权限范围内的文档列表，支持条件查询，分页显示。
     * 
     * @param userid
     *            用户唯一ID
     * @param foldertype
     *            文件夹类型
     * @param parentnode
     *            上级节点id（空字符串为获取根节点）
     * @param currentdatetime
     *            分页用检索时间
     * @param pagesize
     *            文档标题
     * @param keyword
     *            搜索关键字
     * @return JSON
     */

    public String getPersonalDocList(String json);

    /**
     * 通过该接口上传文档
     * 
     * @param userid
     *            用户唯一ID
     * @param type
     *            文档业务类型
     * @param title
     *            文档标题
     * @param doctype
     *            文档类型
     * @param docurl
     *            文档下载地址
     * @param parentnode
     *            上级节点(空字符串默认上传到根节点)
     * @return
     */

    public String uploadPersonalDoc(String json);

    /**
     * 通过该接口可新增文档目录
     * 
     * @param userid
     *            用户唯一ID
     * @param type
     *            文档业务类型
     * @param parentnode
     *            上级节点id（空字符串为获取根节点）
     * @param foldername
     *            文件夹名称
     * @return
     */

    public String createPersonalDocFolder(String json);

    /**
     * 通过该接口可修改或删除文档目录
     * 
     * @param userid
     *            用户唯一ID
     * @param type
     *            文档业务类型
     * @param operationtype
     *            操作类型（0：修改，1：删除）
     * @param id
     *            文件夹id
     * @param foldername
     *            文件夹名称
     * 
     * @return
     */

    public String saveOrUpdatePersonalDocFolder(String json);

    /**
     * 会议室列表:通过该接口获取会议室列表，支持条件查询，分页显示
     * 
     * @param userid
     *            用户唯一ID
     * @param currentdatetime
     *            检索时间类型
     * @param pagesize
     *            检索分页大小
     * @param keyword
     *            检索关键字
     * @return
     */

    public String getBoardroomList(String json);

    /**
     * 会议室明细:通过该接口获取会议室明细信息，包括会议室名称、位置、预订情况，是否空闲等
     * 
     * @param userid
     *            用户唯一ID
     * @param boardroomid
     *            会议室id
     * @param starttime
     *            检索开始时间
     * @param endtime
     *            检索结束时间
     * 
     * @return
     */

    public String getBoardroomDetail(String json);

    /**
     * 会议室预订:通过该接口可预订空闲会议室
     * 
     * @param userid
     *            用户唯一ID
     * @param boardroomid
     *            会议室id
     * @param title
     *            会议室主题
     * @param starttime
     *            检索开始时间
     * @param endtime
     *            检索结束时间
     * @param isremind
     *            是否提醒：0 不提醒 1 提醒
     * 
     * @return
     */

    public String bookBoardroom(String json);

    /**
     * 会议室取消预订:通过该接口，取消登录用户已经预订的会议室
     * 
     * @param userid
     *            用户唯一ID
     * @param meetingid
     *            会议实例id
     * @return
     */

    public String cancelBookingBoardroom(String json);

    /**
     * 通过该接口获取资讯列表信息，支持条件查询，支持分页显示
     * 
     * @param userid
     *            用户唯一ID
     * @param keyword
     *            关键字，搜索条件使用
     * @param currentdatetime
     *            当前检索时间，精确到秒或毫秒
     * @param pagesize
     *            需要检索的记录条数
     * @param type
     *            资讯类别：0 通知公告 1 规章制度 2 新闻发布 3 文件中心 4 公共资源。。。
     * 
     * @return
     */

    public String getInfomationList(String json);

    /**
     * 通过该接口获取某一资讯详细信息，包括标题、正文、附件等
     * 
     * @param userid
     *            用户唯一ID
     * @param informationid
     *            资讯ID
     * @return
     */

    public String getInformationDetail(String json);

    /**
     * 通过该接口下载某一具体的附件，调用相关已安装软件自动打开查阅，对于不能自动打开的附件类型，提示该附件类型无已安装软件可打开
     * 
     * @param userid
     *            用户唯一ID
     * @param informationid
     *            资讯id
     * @param currentdatetime
     *            当前检索时间，精确到秒或毫秒
     * @param pagesize
     *            需要检索的记录条数
     * 
     * @return
     */

    public String getInformationAttachs(String json);

    /**
     * 通过该接口获取登录用户权限内的待办已办公文列表 1. 公文类型区分收文、发文 2. 支持条件查询 3. 支持分页显示
     * 
     * @param userid
     *            用户唯一ID
     * @param keyword
     *            关键字，搜索条件使用
     * @param currentdatetime
     *            当前检索时间，精确到秒或毫秒
     * @param pagesize
     *            需要检索的记录条数
     * @param type
     *            公文类型，例如：0收文 1发文
     * @param handletype
     *            检索待办还是已办信息，0为待办 1为已办
     * @param readstatus
     *            阅读状态 2 全部0已读 1 未读 默认为2
     * 
     * @return
     */

    public String getOfficialList(String json);

    /**
     * 公文明细:通过该接口，获取某一公文明细信息以及公文锁定状态等
     * 
     * @param userid
     *            用户唯一ID
     * @param messageitemguid
     *            工作项的唯一消息ID
     * @param type
     *            公文类型，例如：0收文 1发文
     * 
     * @return
     */

    public String getOfficialDetail(String json);

    /**
     * 公文历史审批记录:通过该接口，获取当前公文历史审批记录
     * 
     * @param messageitemguid
     *            工作项的唯一消息ID
     * @param type
     *            公文类型，例如：0收文 1发文(若收发文id不可能重复，改字段可废弃)
     * 
     * @return
     */

    public String getOfficialApprovalList(String json);

    /**
     * 公文流程下一步信息:通过该接口，根据OA系统流程配置。获取当前公文流程下一步信息，包括下步骤接受人员
     * 
     * @param userid
     *            用户唯一ID
     * @param messageitemguid
     *            工作项的唯一消息ID
     * @param type
     *            公文类型，例如：0收文 1发文
     * 
     * @return
     */

    public String getNextOfficialProcessing(String json);

    /**
     * 通过该接口，发送已经签批或者签署意见的公文到OA系统。 手写签批公文返回签批的PDF文件 签署意见的公文返回录入的意见信息、录入人员、时间等
     * 
     * @param userid
     *            用户唯一ID
     * @param messageitemguid
     *            工作项的唯一消息ID
     * @param nextstepids
     *            下一步处理步骤id
     * @param nextpersonid
     *            下一步的处理人ID,指对应大平台中的人员id，多个用户之间采用;分开。
     * @param opinion
     *            处理意见
     * @param type
     *            公文类型，例如：0收文 1发文
     * @param docfileurl
     *            Pdf下载地址
     * @param docfileid
     *            原加密文件id
     * 
     * @return
     */

    public String sendOfficialProcess(String json);

    /**
     * 锁定状态更改:通过该接口发送公文锁定、解除锁定的状态。确保同一时间只允许一个人签批公文
     * 
     * @param userid
     *            用户唯一ID
     * @param messageitemguid
     *            工作项的唯一消息ID
     * @param lockstatus
     *            锁定状态 0解锁 1锁定
     * @param type
     *            公文类型，例如：0收文 1发文
     * 
     * @return
     */

    public String lockOfficial(String json);

    /**
     * 获取公文附件列表:通过该接口，获取当前公文附件列表信息
     * 
     * @param userid
     *            用户唯一ID
     * @param docid
     *            公文id
     * @param type
     *            公文类型，例如：0收文 1发文
     * @param currentdatetime
     *            当前检索时间，精确到秒或毫秒
     * @param pagesize
     *            需要检索的记录条数
     * 
     * @return
     */

    public String getOfficialAttachments(String json);

    /**
     * 通过该接口，收藏或者取消收藏具体某一公文，方便下次快速查找、审阅
     * 
     * @param userid
     *            用户唯一ID
     * @param messageitemguid
     *            工作项的唯一消息ID
     * @param collectstatus
     *            收藏状态 0取消收藏 1收藏
     * @param type
     *            公文类型，例如：0收文 1发文
     * 
     * @return
     */

    public String collectOfficial(String json);

    /**
     * 公文收藏列表:通过该接口，获取已经收藏公文列表
     * 
     * @param userid
     *            用户唯一ID
     * @param keyword
     *            关键字，搜索条件使用
     * @param currentdatetime
     *            当前检索时间，精确到秒或毫秒
     * @param pagesize
     *            需要检索的记录条数
     * @param type
     *            公文类型，例如：0收文 1发文
     * 
     * @return
     */

    public String getCollectOfficialList(String json);

    /**
     * 根据当前登录用户权限，获取已经归档公文列表，支持条件查询，支持分页显示
     * 
     * @param userid
     *            用户唯一ID
     * @param keyword
     *            关键字，搜索条件使用
     * @param currentdatetime
     *            当前检索时间，精确到秒或毫秒
     * @param pagesize
     *            需要检索的记录条数
     * @param type
     *            公文类型，例如：0收文 1发文
     * @return
     */

    public String getDocList(String json);

    /**
     * 归档公文明细:通过该接口，获取某一已归档公文明细信息
     * 
     * @param userid
     *            用户唯一ID
     * @param messageitemguid
     *            工作项的唯一消息ID
     * @param type
     *            公文类型，例如：0收文 1发文
     * 
     * @return
     */

    public String getDocDetail(String json);

    /**
     * 通讯录列表:通过该接口，获取内部通讯录列表，支持条件查询
     * 
     * @param userid
     *            用户唯一ID
     * @param deptid
     *            部门ID
     * @return
     */

    public String getContactList(String json);

    /**
     * 获取通讯录人员明细:通过该接口，获取具体某一内部人员已经记录的信息，包括姓名、性别、年龄、职位、所属部门、联系方式等
     * 
     * @param userid
     *            用户唯一ID
     * @param personid
     *            人员ID
     * @return
     */

    public String getUserinfo(String json);

    /**
     * 未读邮件列表:获取登录用户的未读邮件列表，支持条件查询，支持分页显示
     * 
     * @param userid
     *            用户唯一ID
     * @param keyword
     *            关键字，搜索条件使用
     * @param currentdatetime
     *            当前检索时间，精确到秒或毫秒
     * @param pagesize
     *            需要检索的记录条数
     * @param type
     *            流程类型，例如：0全部1收件箱2发件箱
     * @param isread
     *            0：已读 1：未读 2：全部
     * 
     * @return
     */

    public String getMsgList(String json);

    /**
     * 更新邮件为已读:通过该接口更新邮件为已读邮件
     * 
     * @param userid
     *            用户唯一ID
     * @param msgid
     *            邮件id，如果多个邮件则用,分隔
     * @return
     */

    public String updateMsgReadStatus(String json);

    /**
     * 通过该接口获取某一邮件详细信息，包括标题、发送人、接受人、抄送、正文、附件等
     * 
     * @param userid
     *            用户唯一ID
     * @param msgid
     *            邮件id
     * @return
     */

    public String getMsgDetail(String json);

    /**
     * 通过该接口实现邮件发送、回复、转发、上传附件等功能
     * 
     * @param userid
     *            用户唯一ID
     * @param content
     *            邮件内容
     * @param replaymsgid
     *            回复邮件id，回复操作时此项必填
     * @param title
     *            标题，回复操作时可为空
     * @param receiverids
     *            接受人ID,多个逗号分隔
     * @param attachlist
     *            附件列表
     * @param attachtitle
     *            附件标题
     * @param attachtype
     *            附件类型，附件后缀
     * @param attachurl
     *            附件下载地址
     * 
     * @return
     */

    public String sendMsg(String json);

    /**
     * 通过该接口可删除邮件，支持单个、批量删除
     * 
     * @param userid
     *            用户唯一ID
     * @param msgid
     *            邮件id,多个id“,”分割
     * @return
     */

    public String deleteMsg(String json);

    /**
     * 已删邮件列表:通过该接口，获取已删邮件列表，支持条件查询，支持已删除邮件恢复功能
     * 
     * @param userid
     *            用户唯一ID
     * @param keyword
     *            关键字，搜索条件使用
     * @param currentdatetime
     *            当前检索时间，精确到秒或毫秒
     * @param pagesize
     *            需要检索的记录条数
     * @param type
     *            流程类型，例如：0全部1收件箱2发件箱
     * 
     * @return
     */

    public String getDeleteMsgList(String json);

    /**
     * 已删邮件恢复:通过该接口，恢复已删邮件状态，恢复的邮件自动进入收件箱或者发件箱
     * 
     * @param userid
     *            用户唯一ID
     * @param msgid
     *            邮件id,多个id“,”分割
     * @return
     */

    public String undeleteMsg(String json);

    /**
     * 短信发送:通过该接口，实现短信发送、转发
     * 
     * @param userid
     *            用户唯一ID
     * @param content
     *            邮件内容
     * @param receiverids
     *            接受人ID,多个逗号分隔
     * @param attachlist
     *            附件列表
     * @param attachtitle
     *            附件标题
     * @param attachtype
     *            附件类型，附件后缀
     * @param attachurl
     *            附件下载地址
     * 
     * @return
     */

    public String sendSMS(String json);

    /**
     * 获取当前用户已发送的短信列表，支持条件查询，支持分页显示
     * 
     * @param userid
     *            用户唯一ID
     * @param keyword
     *            关键字，搜索条件使用
     * @param currentdatetime
     *            当前检索时间，精确到秒或毫秒
     * @param pagesize
     *            需要检索的记录条数
     * @param type
     *            邮箱类型，例如：0全部1收件箱2发件箱
     * @param isread
     *            0：已读 1：未读 2：全部
     * 
     * @return
     */

    public String getSMSList(String json);

    /**
     * 通过该接口，获取某一短信详细信息，支持短信转发
     * 
     * @param userid
     *            用户唯一ID
     * @param msgid
     *            邮件id
     * @return
     */

    public String getSMSDetail(String json);

    public String updateSmsStatus(String json);

    /**
     * 通过该接口，获取当前公文活动节点
     * 
     * @param json
     * @return
     */
    public String getOfficialActiveNodeList(String json);

    /**
     * 获取办件最新锁定信息
     * 
     * @param json
     * @return
     */
    public String getOfficialLockState(String json);

    /**
     * 通过该接口获取车辆申请列表
     * 
     * @param json
     * @return
     */
    public String getCarApplyList(String json);

    /**
     * 通过该接口获取车辆申请详情
     * 
     * @param json
     * @return
     */
    public String getCarApplyDetail(String json);

    /**
     * 通过该接口发起用车申请
     * 
     * @param json
     * @return
     */
    public String bookCarApply(String json);

    /**
     * 通过该接口，取消登录用户已经预订的车辆申请。
     * 
     * @param json
     * @return
     */
    public String cancelBookingCarApply(String json);

    /***
     * 通过该接口获取会议申请列表
     * 
     * @param json
     * @return
     */
    public String getMeetApplyList(String json);

    /**
     * 通过该接口下载某一具体的附件，调用相关已安装软件自动打开查阅，对于不能自动打开的附件类型，提示该附件类型无已安装软件可打开
     * 
     * @param userid
     *            用户唯一ID
     * @param informationid
     *            id
     * @param currentdatetime
     *            当前检索时间，精确到秒或毫秒
     * @param pagesize
     *            需要检索的记录条数
     * 
     * @return
     */

    public String getFileManagerAttachs(String json);

    /**
     * 通过该接口获取某一文件详细信息，包括标题、正文、附件等
     * 
     * @param userid
     *            用户唯一ID
     * @param informationid
     *            资讯ID
     * @return
     */
    public String getFileManagerDetail(String json);

    /**
     * 通过该接口获取市总文件列表信息，支持条件查询，支持分页显示
     * 
     * @param userid
     *            用户唯一ID
     * @param keyword
     *            关键字，搜索条件使用
     * @param currentdatetime
     *            当前检索时间，精确到秒或毫秒
     * @param pagesize
     *            需要检索的记录条数
     * @param type
     *            文件类别：SW 收文 FW 发文
     * 
     * @return
     */
    public String getFileManagerList(String json);

    /**
     *  接口描述 通过该接口推送资讯类交互信息，比如，活动参与结果、投票结果等。返回值包括：资讯ID、操作者、操作时间、操作结果等。
     * 
     * @param userid
     *            用户唯一ID
     * @param informationid
     *            资讯主键
     * @param attendTime
     *            交互时间
     * @param attendResult
     *            交互结果（这个结果由具体业务解释，可以为具体中文）
     * @param remark
     *            （此字段为扩展字段,暂时保留）
     * 
     * @return
     */
    public String sendAttendInfoResult(String json);

    /**
     * 通过该接口获取生活服务类别列表信息，支持条件查询，支持分页显示
     * 
     * @param userid
     *            用户唯一ID
     * @param keyword
     *            关键字，搜索条件使用
     * @param currentdatetime
     *            当前检索时间，精确到秒或毫秒
     * @param pagesize
     *            需要检索的记录条数
     * @param type
     *            服务类别：出行CX、住房ZF、商品SP、娱乐YL、休闲XX、其他OT
     * 
     * @return
     */
    public String getBbsInfoList(String json);

    /**
     * 通过该接口获取某一服务主题详细信息，包括标题、正文等。
     * 
     * @param userid
     *            用户唯一ID
     * @param themeNo
     *            主题ID
     * @return
     */
    public String getBbsInfoDetail(String json);

    /**
     * 通过该接口获取某一服务主题对应留言信息，包括留言人、留言时间、留言内容等
     * 
     * @param userid
     *            用户唯一ID
     * @param themeNo
     *            主题ID
     * @return
     */
    public String getBbsLeaveMessage(String json);

    /**
     * 通过该接口发布帖子信息。
     * 
     * @param userid
     *            用户唯一ID
     * @param LayoutNo
     *            所属版块
     * @param subLayoutTitle
     *            主题标题 IP
     * @param IP
     *            IP地址或者手机MAC地址
     * @param BodyContent
     *            正文内容
     */
    public String sendBbsTheme(String json);

    /**
     * 推送推送帖子留言信息。
     * 
     * @param userid
     *            用户唯一ID
     * @param id
     *            帖子主题主键
     * @param sendTime
     *            留言时间
     * @param content
     *            留言内容
     */
    public String sendBbsLeaveMessage(String json);


    /**
     * 推送推送帖子留言回复信息。
     * 
     * @param userid
     *            用户唯一ID
     * @param id
     *            回复主键
     * @param sendTime
     *            留言时间
     * @param content
     *            留言内容
     */
    public String sendBbsReplyLeaveMessage(String json);

    /**
     *通过该接口获取议程列表信息
     * 
     * @param userid
     *            用户唯一ID
     * @param keyword
     *            关键字，搜索条件使用
     * @param currentdatetime
     *            当前检索时间，精确到秒或毫秒
     * @param starttime
     *            检索开始时间
     * @param endtime
     *            检索结束时间
     * @param pagesize
     *            需要检索的记录条数
     * @return
     */
    public String getAgendaList(String json);
    /**
     * 通过该接口获取议程详细信息
     * 
     * @param userid
     *            用户唯一ID
     * @param djId
     *            会议材料ID
     * @return
     */
    public String getAgendaDetail(String json);
    /**
     * 通过该接口，提交阅后的会议材料附件，会议材料附件支持反复提交
     * 
     * @param userid
     *            用户唯一ID
     * @param djId
     *            会议材料ID
     * @param stuffId
     *            附件材料Id
     * @param stuff_path
     *           附件材料地址
     * @return
     */
    public String sendMeetingMaterial(String json);
    
    
    /**
     * 
     * 通过该接口，获取会议列表信息
     * 
     * @param userid
     *            用户唯一ID
     * @param keyword
     *            关键字，搜索条件使用
     * @param currentdatetime
     *            当前检索时间，精确到秒或毫秒
     * @param starttime
     *            检索开始时间
     * @param endtime
     *            检索结束时间
     * @param pagesize
     *            需要检索的记录条数
     * @return
     */
    public String getMeetingList(String json);
    
    
    /**
     * 通过该接口获取会议详细信息
     * 
     * @param mId
     *           会议ID
     * 
     * @return
     */
    public String getMeetingDetail(String json);

    /**
     * 通过该接口可预订请假条申请
     * 
     * @param userid
     *            用户唯一ID
     * @param transAffairName
     *            标题
     * @param applydate
     *            预计开始时间
     * @param endtime
     *            预计结束时间
     * @param leaveNum
     *            假期天数
     * @param remark_content
     *            请假事由
     * 
     * @return
     */
    public String bookLeaveApply(String json);
    
    /**
     * 通过该接口可预订自助餐申请
     * 
     * @param userid
     *            用户唯一ID
     * @param transAffairName
     *            标题
     * @param applydate
     *            预计开始时间
     * @param applyuser
     *            申请部门
     * @param visitors
     *            来访人员
     * @param visitorsnum
     *            来访人数
     * @param remark_content
     *            来访目的
     * 
     * @return
     */
    public String bookBuffetApply(String json);
    
    /**
     * 通过该接口可提交物品申领信息
     * 
     * @param userid
     *            用户唯一ID
     * @param transAffairName
     *            标题
     * @param applydate
     *            预计开始时间
     * @param applyuser
     *            申请部门
     * @param remark_content
     *            来访目的
     * 
     * @return
     */
    public String bookOfficeSuppApply(String json);

    /**
     * 通过该接口可提交物品申领信息
     * 
     * @param userid
     *            用户唯一ID
     * @param transAffairName
     *            标题
     * @param applydate
     *            公示公告时间
     * @param applyuser
     *            公示公告部门
     * @param remark_content
     *            公示公告内容
     * 
     * @return
     */
    public String bookNetApplication(String json);
    /**
     * 通过该接口可提交物品申领信息
     * 
     * @param userid
     *            用户唯一ID
     * @param transAffairName
     *            标题
     * @param applydate
     *            离开时间
     * @param endtime
     *            返回时间
     * @param applyuser
     *            所在部门
     * @param postleve
     *            职位
     * @param remark_content
     *            公示公告内容
     * @param leaveaddress
     *            外出地点
     * @param telephone
     *            联系方式 
     * @param remark
     *            备注
     * 
     * @return
     */
    public String bookLeaveReported(String json);
    /**
     * 通过该接口获取生活服务最新最热帖子列表信息，支持条件查询，支持分页显示
     * 
     * @param userid
     *            用户唯一ID
     * @param keyword
     *            关键字，搜索条件使用
     * @param currentdatetime
     *            当前检索时间，精确到秒或毫秒
     * @param pagesize
     *            需要检索的记录条数
     * 
     * @return
     */
    public String getBbsNewHotInfoList(String json);
    
    /**
     * 通过该接口终端收发文附件上传
     * 
     * @param userid
     *            用户唯一ID
     * @param type
     *            文档业务类型
     * @param title
     *            文档标题
     * @param doctype
     *            文档类型
     * @param docurl
     *            文档下载地址
     * @return
     */

    public boolean uploadFile(String messageitemguid, String qpfileurl);
    
    public String bookOatripPlan(String json);
}
