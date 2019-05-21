package com.centit.oa.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.core.dao.CodeBook;
import com.centit.core.utils.PageDesc;
import com.centit.oa.po.OaHelpinfo;
import com.centit.oa.po.VOaInformation;
import com.centit.oa.service.OaHelpinfoManager;
import com.centit.oa.service.OaLeaveMessageManager;
import com.centit.oa.service.OaLeaveReplyManager;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.po.FOptinfo;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.security.FUserDetail;
import com.centit.sys.service.FunctionManager;

public class OaHelpinfoAction  extends BaseEntityExtremeAction<OaHelpinfo>  {
	private static final Log log = LogFactory.getLog(OaHelpinfoAction.class);
	private static final long serialVersionUID = 1L;
	private OaHelpinfoManager oaHelpinfoMag;
	private OaLeaveMessageManager oaLeaveMessageManager;
	private OaLeaveReplyManager oaLeaveReplyManager;
	private FunctionManager functionManager;
	private List<OaHelpinfo> vobjList;
    private File fileDoc_;
	private String fileDoc_FileName;//生成帖子时，上传文件的文件名称
	
    private String mark;

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
	
	public List<OaHelpinfo> getVobjList() {
        return vobjList;
    }
    public void setVobjList(List<OaHelpinfo> vobjList) {
        this.vobjList = vobjList;
    }
    public File getFileDoc_() {
        return fileDoc_;
    }
    public void setFileDoc_(File fileDoc_) {
        this.fileDoc_ = fileDoc_;
    }
    public String getFileDoc_FileName() {
        return fileDoc_FileName;
    }
    public void setFileDoc_FileName(String fileDoc_FileName) {
        this.fileDoc_FileName = fileDoc_FileName;
    }
    public OaLeaveMessageManager getOaLeaveMessageManager() {
        return oaLeaveMessageManager;
    }
    public void setOaLeaveMessageManager(OaLeaveMessageManager oaLeaveMessageManager) {
        this.oaLeaveMessageManager = oaLeaveMessageManager;
    }
    public OaLeaveReplyManager getOaLeaveReplyManager() {
        return oaLeaveReplyManager;
    }
    public void setOaLeaveReplyManager(OaLeaveReplyManager oaLeaveReplyManager) {
        this.oaLeaveReplyManager = oaLeaveReplyManager;
    }
    
    public FunctionManager getFunctionManager() {
        return functionManager;
    }

    public void setFunctionManager(FunctionManager functionManager) {
        this.functionManager = functionManager;
    }

    public void setOaHelpinfoManager(OaHelpinfoManager basemgr)
	{
		oaHelpinfoMag = basemgr;
		this.setBaseEntityManager(oaHelpinfoMag);
	}
    //下载oa帮助信息的附件时使用
    private InputStream stuffStream;
    //下载oa帮助信息的附件时使用
    private String filename;
    public InputStream getStuffStream() {
        return stuffStream;
    }
    public void setStuffStream(InputStream stuffStream) {
        this.stuffStream = stuffStream;
    }
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
    /**
     * 
     */
    @SuppressWarnings("unchecked")
    public String list4iframe(){
        try {
            Map<Object, Object> paramMap = request.getParameterMap();
            resetPageParam(paramMap);
            Map<String, Object> filterMap = convertSearchColumn(paramMap);
            filterMap.put("state", "T");//只查看启用的帖子
            //默认搜索普通帖子
            String isgood=(String) filterMap.get("isgood");
            if(StringUtils.isBlank(isgood)){
                filterMap.put("isgood", "0");
            }
            //重新构造PageDesc
            PageDesc pageDesc = this.makeSearchPageDesc(request);
            objList = oaHelpinfoMag.listOahelpinfo(filterMap, pageDesc);
            //检查登陆者是否有修改的权限
            request.setAttribute("loginer", this.getLoginUserCode());
            totalRows = pageDesc.getTotalRows();
            String columnType=(String) filterMap.get("columnType");
            request.setAttribute("backcolumnType",columnType);//准备查看返回时使用
            setbackSearchColumn(filterMap);
            this.pageDesc = pageDesc;
            //1.设置主题数
            if(!objList.isEmpty())
            request.setAttribute("totalTitles",objList.size());
            //2.设置回帖总数和今天回帖数
          
            if(StringUtils.isNotBlank(columnType)){
                Map<String,Object> result=oaLeaveMessageManager.getMount(columnType);
                request.setAttribute("total", result.get("total"));
                request.setAttribute("today", result.get("today"));
            }
            return "leaderlist";
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }
    /**
     *按照栏目类型查询帖子 
     */
    public String list(){
/*	    //从某栏目类型进入查看后，返回时使用
	    Map<Object, Object> paramMap = request.getParameterMap();
	    resetPageParam(paramMap);
	    Map<String, Object> filterMap = convertSearchColumn(paramMap);
        pageDesc = makeSearchPageDesc(request);
	    objList = oaHelpinfoMag.listObjects(filterMap,pageDesc);
	   // setbackSearchColumn(filterMap);
*/
	   /* request.setAttribute("helpMenu",this.getFunctionsByUserCode(((FUserinfo) super.getLoginUser()).getUsercode()));*/
        
            String jsonStr = getMenusForTree();
            request.setAttribute("menusJsonStr", jsonStr);
	    
	        return LIST;
	}
    
    /**
     * 获取菜单树
     * @return
     */
    private String getMenusForTree(){
        FUserinfo user = new FUserinfo();
        user.setUsercode(((FUserinfo) this.getLoginUser()).getUsercode());
        List<FOptinfo> fOptinfo=functionManager.getMenuFuncByUser(user);
        List<Map<String,String>> maps = new ArrayList<Map<String,String>>();
        if(fOptinfo != null && fOptinfo.size() > 0){
            for(FOptinfo f : fOptinfo){
                Map<String,String> menusMap = new HashMap<String,String>();
                menusMap.put("id", f.getOptid());
                menusMap.put("pId", f.getPreoptid());
                menusMap.put("name", f.getOptname());
                maps.add(menusMap);
            }
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = "";
        try {
            jsonStr = objectMapper.writeValueAsString(maps);
        } catch(Exception e){
            log.error("系统业务展示菜单转换json发生异常:"+e.getMessage());
        }
        return jsonStr;
    }
    /**
     *按照栏目类型查询帖子(为管理员查询帖子)
     */
    @SuppressWarnings("unchecked")
    public String list4Manager(){
           try {
                String isadmin =((FUserinfo) this.getLoginUser()).getUsercode();//获取当前登录用户的id
                String admin="99999999";
                if(isadmin.equals(admin)){//判断是否是管理员
                    request.setAttribute("isadmin", isadmin);
                }
                Map<Object, Object> paramMap = request.getParameterMap();
                resetPageParam(paramMap);

                String orderField = request.getParameter("orderField");
                String orderDirection = request.getParameter("orderDirection");
                String optname = request.getParameter("optname");
                    
                Map<String, Object> filterMap = convertSearchColumn(paramMap);
                if (!StringUtils.isBlank(orderField)
                        && !StringUtils.isBlank(orderDirection)) {

                    filterMap.put(CodeBook.SELF_ORDER_BY, orderField + " "
                            + orderDirection);

                }
                PageDesc pageDesc = makeSearchPageDesc(request);
//                objList = oaHelpinfoMag.listObjects(filterMap, pageDesc);
                filterMap.put("state", "T");
                String isAll=(String) filterMap.get("isALL");
                if(StringUtils.isNotBlank(isAll)&&isAll.equals("true")){//是否包含禁用主题
                    filterMap.remove("state");
                }
                objList=oaHelpinfoMag.listOahelpinfo(filterMap, pageDesc);
                totalRows = pageDesc.getTotalRows();
                setbackSearchColumn(filterMap);
                this.pageDesc = pageDesc;
                return "leaderlist";
            } catch (Exception e) {
                e.printStackTrace();
                return ERROR;
            }
    }   
	/**
     * 当前登录人员usercode
     */
    private String getLoginUserCode() {
        return ((FUserinfo) this.getLoginUser()).getUsercode();
    }
  public  PageDesc makeSearchPageDesc(HttpServletRequest request) {
        
        String pagesize = request.getParameter("numPerPage");
        String pageno = request.getParameter("pageNum");
        
        String offset = request.getParameter("pager.offset");
        int pageSize = isNumber(pagesize) ? Integer.parseInt(pagesize) : 10;
        int pageNo = Integer.parseInt(isNumber(pageno) ? pageno : "1");
        
        if(StringUtils.isNotBlank(offset) && !StringUtils.isNotBlank(pageno) && isNumber(offset)) {
            int offsetNum = Integer.parseInt(offset);
            
            pageNo = (offsetNum / pageSize) + 1;
        }

        PageDesc pageDesc = new PageDesc(pageNo, pageSize);

        return pageDesc;

    }
      private static boolean isNumber(String input) {
          if (null == input) {
              return false;
          }
          return Pattern.matches("\\d+", input);
      }
	  /**
	   *保存帖子 
	   */
	public String save(){
	    OaHelpinfo oaHelpinfo=oaHelpinfoMag.getObjectById(object.getDjid());
        if (oaHelpinfo == null) {
            oaHelpinfo = new OaHelpinfo();
        }
        oaHelpinfoMag.copyObjectNotNullProperty(oaHelpinfo, object);
        object = oaHelpinfo;
        if (StringUtils.isBlank(object.getDjid())) {
            FUserDetail user = (FUserDetail) getLoginUser();
            object.setState("T");//默认启用
            object.setDjid(oaHelpinfoMag.getNextkey());
            object.setCreatertime(DatetimeOpt.currentUtilDate());
            object.setCreater(user.getUsercode());
            object.setIsgood("0");//默认非精华帖
            object.setViewnum(0L);//初始审阅数为零
            object.setReplynum(0L);//初始回复数为零
            object.setIsallowcomment("Y");//默认允许评论
        } 
        if (fileDoc_ != null) {
            try {
                FileInputStream fis = new FileInputStream(fileDoc_);
                if (fis != null) {
                    byte[] bbuf = null;
                    int len = fis.available();
                    bbuf = new byte[len];
                    fis.read(bbuf);
                    object.setFileDoc(bbuf);
                    object.setFileDocname(fileDoc_FileName);
                }
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        oaHelpinfoMag.saveObject(object);
        return this.list4Manager();
    }
	/**
	 * 删除帖子(管理员)
	 */
	public  String delete(){
	    OaHelpinfo oaHelpinfo=oaHelpinfoMag.getObjectById(object.getDjid());
	    oaHelpinfoMag.copyObjectNotNullProperty(oaHelpinfo, object);
        object = oaHelpinfo;
        if(object.getState().equals("T")){//启用状态的帮助帖子
            object.setState("F");
        }
        oaHelpinfoMag.saveObject(object);
	    return this.list4Manager();
	}
	/**
	 * 搜索帖子（浏览者可用）
	 */
    public String search(){
	    String search = null;
        try {
            search = URLDecoder.decode(request.getParameter("search"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	    PageDesc pageDesc = this.makeSearchPageDesc(request);
	    if(StringUtils.isNotBlank(search)){
	        objList=oaHelpinfoMag.search(search,pageDesc);
	    }
	    //检查登陆者是否有修改的权限
        request.setAttribute("loginer", this.getLoginUserCode());
        return  "iframe";
	}
	  public String edit() {
	        try {
	            if (object != null) {
	                OaHelpinfo oaHelpinfo=oaHelpinfoMag.getObjectById(object.getDjid());
	                if (oaHelpinfo != null)
	                    oaHelpinfoMag.copyObject(object, oaHelpinfo);
	            }
	            return EDIT;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return ERROR;
	        }
	    }
	  //附件下载
	    public String downStuffInfo() throws IOException {	        	        
	        OaHelpinfo info = oaHelpinfoMag.getObjectById(object.getDjid());
//	        OaHelpinfo info = oaHelpinfoMag.getOahelpInfoById(object.getDjid());
	        if (null == info) {
	            return "download";
	        }
	        byte[] bt = null;
	        String fn = info.getFileDocname();
	        bt = info.getFileDoc();
	        try {
	            if (bt != null) {
	                setStuffStream(new ByteArrayInputStream(bt));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            log.error(e.getMessage(), e);
	            saveError(e.getMessage());
	        }
	        this.setFilename(new String(fn.getBytes("GBK"), "ISO8859-1"));
	        return "download";

	    }
	    public String view(){
	        try {
	            OaHelpinfo oaHelpinfo= oaHelpinfoMag.getObject(object);
//	            OaHelpinfo oaHelpinfo= oaHelpinfoMag.getoaHelpinof(object.getDjid());
	            if (object == null) {
	                return this.list4iframe();
	            }
	            //保存浏览次数
	            if (oaHelpinfo != null){
	                oaHelpinfoMag.copyObject(object, oaHelpinfo);
	                oaHelpinfo.setViewnum(oaHelpinfo.getViewnum()+1L);
	                oaHelpinfoMag.saveObject(oaHelpinfo);
	            }
	            //保存进入查看前所在的栏目类型
	            String columnType=request.getParameter("backcolumnType");
	            if(StringUtils.isNotBlank(columnType)){
	                request.setAttribute("backcolumnType", columnType);
	            }
	            return VIEW;
	        } catch (Exception e) {
	            log.error(e.getMessage());
	            return ERROR;
	        }
	    }
	    /**
	     * 将普通帖置为精华帖
	     */
	    public String setGood(){
	        try {
                OaHelpinfo oaHelpinfo= oaHelpinfoMag.getObject(object);
                if (oaHelpinfo != null){
                    oaHelpinfoMag.copyObject(object, oaHelpinfo);
                    if("0".equals(oaHelpinfo.getIsgood())){
                        oaHelpinfo.setIsgood("1");
                        oaHelpinfoMag.saveObject(oaHelpinfo);
                    }
                }
                return this.list4Manager();
            } catch (Exception e) {
                log.error(e.getMessage());
                return ERROR;
            }
	    }
	    /**
         * 将精华帖置为普通帖
         */
	    public String setNormal(){
	        try {
                OaHelpinfo oaHelpinfo= oaHelpinfoMag.getObject(object);
                if (oaHelpinfo != null){
                    oaHelpinfoMag.copyObject(object, oaHelpinfo);
                    if("1".equals(oaHelpinfo.getIsgood())){
                        oaHelpinfo.setIsgood("0");
                        oaHelpinfoMag.saveObject(oaHelpinfo);
                    }
                }
                return this.list4Manager();
            } catch (Exception e) {
                log.error(e.getMessage());
                return ERROR;
            }
	    }
	    
	    /**
         * 管理后台修改帮助平台页面
         */
	    public String modify(){
            String jsonStr = getMenusForTree();
            request.setAttribute("menusJsonStr", jsonStr);
	        if (object != null) {
                OaHelpinfo oaHelpinfo=oaHelpinfoMag.getObjectById(object.getDjid());
                if (oaHelpinfo != null)
                    oaHelpinfoMag.copyObject(object, oaHelpinfo);
            }

	        return "modify";
	    }
	    
	    /**
         * 管理后台保存帮助平台修改结果
         */
	    public String saveModify(){
	        
	        OaHelpinfo dbObject = oaHelpinfoMag.getObject(object);
	        if(null != dbObject){
	            oaHelpinfoMag.copyObjectNotNullProperty(dbObject, object);
	            object = dbObject;
	            this.saveFileDoc();
	            oaHelpinfoMag.saveObject(object);
	        }
	        return "leaderlist";
	    }
	    
	    /**
         * 保存附件
         */
	    public void saveFileDoc(){
	        
            if (fileDoc_ != null) {
                try {
                    FileInputStream fis = new FileInputStream(fileDoc_);
                    if (fis != null) {
                        byte[] bbuf = null;
                        int len = fis.available();
                        bbuf = new byte[len];
                        fis.read(bbuf);
                        object.setFileDoc(bbuf);
                        object.setFileDocname(fileDoc_FileName);
                    }
                    fis.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                object.setFileDoc(null);
            }
	    }
}
