package com.centit.sys.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.sys.po.FOptinfo;
import com.centit.sys.po.FUserinfo;
import com.centit.sys.service.CodeRepositoryManager;
import com.centit.sys.service.FunctionManager;
import com.centit.sys.util.ISysOptLog;
import com.centit.sys.util.SysOptLogFactoryImpl;

public class OptInfoAction  extends BaseEntityExtremeAction< FOptinfo > {

	private static final long serialVersionUID = 1L;
	private FunctionManager functionManager;
	private CodeRepositoryManager codeRepositoryManager;
	private Integer totalRows;
	private static final ISysOptLog SYS_OPT_LOG = SysOptLogFactoryImpl.getSysOptLog("OPTINFO");
	private InputStream inputStream;
	
	public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
    
	public Integer getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(Integer totalRows) {
		this.totalRows = totalRows;
	}

	public void setCodeRepositoryManager(CodeRepositoryManager codeRepositoryManager) {
		this.codeRepositoryManager = codeRepositoryManager;
	}

	public void setFunctionManager(FunctionManager functionManager) {
		this.functionManager = functionManager;
		setBaseEntityManager(functionManager);
	}
	
	 public String list(){
	        String jsonStr = getMenusForTree();
	        request.setAttribute("menusJsonStr", jsonStr);
	        return LIST;
	    }
	 /**
	     * 获取菜单树
	     * @return
	     */
	    private String getMenusForTree(){
	        objList= functionManager.listObjects();
	        List<Map<String,String>> maps = new ArrayList<Map<String,String>>();
	        
	        if(objList != null && objList.size() > 0){
	            for(FOptinfo f : objList){
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
	public String built() {
		try {
		    //标识是新增
		    request.setAttribute("isNew", 1);
		    
			return EDIT;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}		
	}
	
	 /**
	    * 保存操作
	    */
	  public String save(){
	      boolean operFlag = true;
	      try {
	          FOptinfo dbobject = functionManager.getObjectById(object.getOptid());
	          if (dbobject != null){
	              functionManager.copyObjectNotNullProperty(dbobject, object);
	              object=dbobject;
	              
	          }
	          try {
	              functionManager.saveObject(object);
	              //刷新系统中的缓存
	              codeRepositoryManager.refresh("optid"); 
	              
	              SYS_OPT_LOG.log(((FUserinfo)this.getLoginUser()).getUsercode(),
	                      object.getOptid(), object.display(), 
	                      dbobject != null?dbobject.display():"");
	          } catch (Exception e) {
	              log.error(e.getMessage(), e);  
	              operFlag = false;
	          }
	      } catch (Exception ee) {
	          log.error(ee.getMessage(), ee);  
	          operFlag = false;
	      }
	      String jsonStr = "{\"operFlag\":" + operFlag + "}";
	      try{
	          this.setInputStream(new ByteArrayInputStream(jsonStr
	                  .getBytes("utf-8")));
	      }catch(Exception e){
	          log.error(e.getMessage(), e);  
	      }
	      return "operSuccess";
	  }

	  /**
	   * 删除操作
	   */
	  public String delete() 
	  {
	      boolean operFlag = true;
	      try {
	          
	          try {
	              functionManager.deleteObject(object);
	              //刷新系统中的缓存
	              codeRepositoryManager.refresh("optid");
	              deletedMessage();
	          } catch (Exception e) {
	              log.error(e.getMessage(), e);
	              operFlag = false;
	          }
	      } catch (Exception e) {
	          log.error(e.getMessage(), e);
	          operFlag = false;
	      }
	      String jsonStr = "{\"operFlag\":" + operFlag + "}";
	      try{
	          this.setInputStream(new ByteArrayInputStream(jsonStr
	                  .getBytes("utf-8")));
	      }catch(Exception e){
	          log.error(e.getMessage(), e);  
	      }
	      return "operSuccess";
	  }
	
}
