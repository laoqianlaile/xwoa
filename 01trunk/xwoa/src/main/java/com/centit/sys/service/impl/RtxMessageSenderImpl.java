package com.centit.sys.service.impl;

import rtx.RTXSvrApi;

import com.centit.sys.dao.UserInfoDao;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.service.MessageSender;

/**
 * 
 * 连接RTX客户端，向指定用户发送消息
 * 
 * 配置方式，如项目服务器部署在Rtx Server相同的机器上则无须配置。
 * 
 * 
 * 1.在开发机器安装RTX2013 Server SDK包。
 * 2． 进入Server SDK安装目录(默认路径：x:\Program Files\Tencent\RTXSSDK\)，
 *   把该目录下的dll文件及rtxServerApi.ini拷贝到系统的System32目录下（RTX2008Beta2之后的RTX版本安装时已经自动拷贝到该目录下），
 *   如果在网页调用java接口，需要另拷贝一份到JDK的bin目录下。
 * 3． 进入RTX服务器安装目录下，用记事本打开SDKPorperty.xml文件，IPLimit > IP，保存后重启RTX服务。
 * 4.通过配置获取服务器IP地址
 *
 * 
 * 
 * @author 
 * @create 2015年5月31日
 * @version
 */
public class RtxMessageSenderImpl implements MessageSender {
    
    private static final String TYPE = "0";
    private static final String DELAYTIME = "0";
    
    private UserInfoDao sysUserDao;

    public void setSysuserDao(UserInfoDao userdao) {
        this.sysUserDao = userdao;
    }

    
    /**
     * @param receiver
     *            rtx系统用户，如有多个用户使用英文逗号分割且中间不要有空格
     */
    @Override
    public boolean sendMessage(String sender, String receiver,
            String msgSubject, String msgContent) {
        int iRet = -1;
        RTXSvrApi rtxsvrapiObj = new RTXSvrApi(); 
        boolean resultCode = true;
        rtxsvrapiObj.setServerIP(CodeRepositoryUtil.getDataPiece("SYSPARAM", "RTXSER")!=null?CodeRepositoryUtil.getDataPiece("SYSPARAM", "RTXSER").getDatavalue():RTXSvrApi.RTXSER);
        rtxsvrapiObj.setServerPort(6000);
        if (rtxsvrapiObj.Init()) {
            iRet = rtxsvrapiObj.sendNotify(receiver, msgSubject, msgContent, TYPE,
                    DELAYTIME);
            if (iRet != 0) {
                resultCode = false;
            }
        }
        rtxsvrapiObj.UnInit();

        return resultCode;
    }

   

    
}
