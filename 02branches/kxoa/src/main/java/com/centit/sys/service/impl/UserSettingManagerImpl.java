package com.centit.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.centit.core.service.BaseEntityManagerImpl;
import com.centit.sys.dao.OptInfoDao;
import com.centit.sys.dao.UserSettingDao;
import com.centit.sys.po.FOptinfo;
import com.centit.sys.po.MyHome;
import com.centit.sys.po.Usersetting;
import com.centit.sys.service.UserSettingManager;


public class UserSettingManagerImpl extends BaseEntityManagerImpl<Usersetting>
    implements UserSettingManager{

    private static final long serialVersionUID = 1L;
    private UserSettingDao usersettingDao ;
    private OptInfoDao functionDao;
    public void setUsersettingDao(UserSettingDao baseDao)
    {
        this.usersettingDao = baseDao;
        setBaseDao(this.usersettingDao);
    }
    

    public void setFunctionDao(OptInfoDao functionDao) {
        this.functionDao = functionDao;
    }


    /* (non-Javadoc)
     * @see com.centit.sys.service.UserSettingManager#queryMyHomeByUsercode(java.lang.String)
     */
    public List<MyHome> queryMyHomeByUsercode(String code) {
        List<MyHome> home = new ArrayList<MyHome>();
        Usersetting user = usersettingDao.getObjectById(code);
        if(user==null)
            user = usersettingDao.getObjectById("default");
        add2List(home, parseUsersetting2MyHome(user.getFavorurl1()));
        add2List(home, parseUsersetting2MyHome(user.getFavorurl2()));
        add2List(home, parseUsersetting2MyHome(user.getFavorurl3()));
        add2List(home, parseUsersetting2MyHome(user.getFavorurl4()));
        add2List(home, parseUsersetting2MyHome(user.getFavorurl5()));
        add2List(home, parseUsersetting2MyHome(user.getFavorurl6()));
        add2List(home, parseUsersetting2MyHome(user.getFavorurl7()));
        add2List(home, parseUsersetting2MyHome(user.getFavorurl8()));
        add2List(home, parseUsersetting2MyHome(user.getFavorurl9()));
        
        return home;
    }
    
    /**
     * ���û�����ת�����ҵ���ҳ����Ҫ����ݸ�ʽ
     * 
     * @param optid
     * @return
     */
    private MyHome parseUsersetting2MyHome(String optid) {
        if (null == optid || "".equals(optid)) {
            return null;
        }
        
        FOptinfo optInfo = functionDao.getObjectById(optid);
        if (null != optInfo) {
            MyHome home = new MyHome();
            
            home.setId(optid);
            home.setTitle(optInfo.getOptname());
            home.setUrl(optInfo.getOpturl());
            
            return home;
        }
        
        return null;
    }
    
    /**
     * ���б���������
     * @param list
     * @param home
     */
    private void add2List(List<MyHome> list, MyHome home) {
        if (null != home) {
            list.add(home);
        }
    }
    
}

