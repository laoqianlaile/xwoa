package com.centit.sys.action;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import freemarker.log.Logger;

public class CountLineListener implements HttpSessionListener {

    public static int ONLING_NUM = 0;
    private static final Logger logger = Logger.getLogger(CountLineListener.class.getName()); 
    /***********
     * 创建session时调用
     */
    @Override
    public void sessionCreated(HttpSessionEvent event) {
        // TODO Auto-generated method stub
      /*  System.out.println("创建session......");
        ServletContext context = event.getSession().getServletContext();
        
        HashSet  sessions = (HashSet) context.getAttribute("sessions");
        Integer count = (Integer) context.getAttribute("count");
        if (sessions == null) {
            sessions = new HashSet();
            context.setAttribute("sessions", sessions);
        }
        
        sessions.add(event);
        
        
        if (count == null) {
            count = new Integer(1);
        } else {
            int co = count.intValue();
            count = new Integer(co + 1);
        }
        // System.out.println("当前用户人数：" + count);
        context.setAttribute("count", count);// 保存人数
        ONLING_NUM = count;
        System.out.println("当前用户人数：" + ONLING_NUM);*/
    }

    /************
     * 销毁session时调用
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        // TODO Auto-generated method stub
      /*  System.out.println("销毁session......");
        ServletContext context = event.getSession().getServletContext();
        Integer count = (Integer) context.getAttribute("count");
        int co = count.intValue();
        count = new Integer(co - 1);
        context.setAttribute("count", count);
        // System.out.println("当前用户人数：" + count);
        ONLING_NUM = count;
        System.out.println("当前用户人数：" + ONLING_NUM);*/
        logger.error("HelpDesk system sessionCreated, sessionid=" + event.getSession().getId());
    }

}
