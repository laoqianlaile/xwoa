package com.goldgrid.weboffice;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.centit.powerruntime.dao.OptStuffInfoDao;
import com.centit.sys.service.OptFlowNoInfoManager;
import com.centit.workflow.sample.dao.VUserTasksDao;

/**
 * Servlet implementation class OfficeServer
 */
public class OfficeServer extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String CONTENT_TYPE = "text/html; charset=GBK";
    private ExecuteService es;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public OfficeServer() {
        super();
    }

    /**
     * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void service(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(CONTENT_TYPE);

        ServletContext application= getServletContext();   
        WebApplicationContext wac;   
        wac = WebApplicationContextUtils.getWebApplicationContext(application);//获取spring的context   
        OptStuffInfoDao optStuffInfoDao = (OptStuffInfoDao) wac.getBean("optStuffInfoDao");
        VUserTasksDao vUserTasksDao = (VUserTasksDao)wac.getBean("vUserTasksDao");
        OptFlowNoInfoManager optFlowNoInfoManager=(OptFlowNoInfoManager)wac.getBean("optFlowNoInfoManager");
        es = new ExecuteService();
        es.setOptStuffInfoDao(optStuffInfoDao);
        es.setUserTasksDao(vUserTasksDao);
        es.setOptFlowNoInfoManager(optFlowNoInfoManager);
        es.ExecuteRun(request, response);

    }

}
