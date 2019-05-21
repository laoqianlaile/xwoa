package com.centit.sys.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;

import org.extremecomponents.table.limit.Limit;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.core.utils.ExtremeTableUtils;
import com.centit.core.utils.PageDesc;
import com.centit.sys.po.OptVariable;
import com.centit.sys.service.OptVariableManager;


public class OptVariableAction  extends BaseEntityExtremeAction<OptVariable>  {
	private static final long serialVersionUID = 1L;
	private OptVariableManager optVariableMag;
	private InputStream inputStream;
    
	 public InputStream getInputStream() {
	        return inputStream;
	    }
	    public void setInputStream(InputStream inputStream) {
	        this.inputStream = inputStream;
	    }
	public void setOptVariableManager(OptVariableManager basemgr)
	{
		optVariableMag = basemgr;
		this.setBaseEntityManager(optVariableMag);
	}
	

	@SuppressWarnings("unchecked")
	public String list() {
		try {
			Map<Object,Object> paramMap = request.getParameterMap();
			resetPageParam(paramMap);			
			Map<String,Object> filterMap = convertSearchColumn( paramMap );
			Limit limit=ExtremeTableUtils.getLimit(request);
			PageDesc pageDesc = ExtremeTableUtils.makePageDesc(limit); 
	        objList=baseEntityManager.listObjects(filterMap, pageDesc);
	        totalRows = pageDesc.getTotalRows();
			return LIST;
		} catch (Exception e) {
			e.printStackTrace();
			return  ERROR;
		}
	}


	public String save() {
        boolean operFlag = true;
        try {
            OptVariable dbObject =  baseEntityManager.getObject(object);
            if (dbObject != null){
                baseEntityManager.copyObjectNotNullProperty(dbObject, object);
                object = dbObject;
            }
            object.setIsvalid("T");
            baseEntityManager.saveObject(object);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            saveError(e.getMessage());
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
	public String built() {
		try {
			String optid=request.getParameter("s_OPTID");
			//object = new OptVariable();
			object.setOptid(optid);
			return BUILT;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}		
	}
		
}