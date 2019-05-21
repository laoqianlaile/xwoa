package com.centit.sys.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import com.centit.core.action.BaseEntityExtremeAction;
import com.centit.sys.po.FOptdef;
import com.centit.sys.po.FOptinfo;
import com.centit.sys.service.CodeRepositoryManager;
import com.centit.sys.service.FunctionManager;
import com.centit.sys.service.OptdefManager;

public class OptDefAction  extends BaseEntityExtremeAction<FOptdef> {


	private static final long serialVersionUID = 1L;
	private OptdefManager optdefManager;
	
	private FunctionManager functionManager;
	private CodeRepositoryManager codeRepositoryManager;
	private FOptinfo optinfo;
	private List<FOptdef> optdefs;
	private Integer totalRows; 
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

	public List<FOptdef> getOptdefs() {
		return optdefs;
	}

	public void setOptdefs(List<FOptdef> optdefs) {
		this.optdefs = optdefs;
	}

	public FOptinfo getOptinfo() {
		return optinfo;
	}

	public void setOptinfo(FOptinfo optinfo) {
		this.optinfo = optinfo;
	}
	
	public void setCodeRepositoryManager(CodeRepositoryManager codeRepositoryManager) {
		this.codeRepositoryManager = codeRepositoryManager;
	}
	
	
	public void setFunctionManager(FunctionManager functionManager) {
		this.functionManager = functionManager;
	}

	public void setOptdefManager(OptdefManager optdefManager) {
		this.optdefManager =optdefManager;
		setBaseEntityManager(optdefManager);
	}
	
	public String list()
	{

		try {
			
			if (object == null) {
				saveError("entity.missing");
				return LIST;
			}			
			optinfo = functionManager.getObjectById(object.getOptid());
			optdefs = optdefManager.getOptDefByOptID(object.getOptid());
			totalRows=optdefs.size();
			return LIST;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	public String built() {
		try {
			FOptdef fOptdef = getEntityClass().newInstance();
			fOptdef.setOptid(object.getOptid());
			object=fOptdef;
			object=optdefManager.getObject(object);
			return BUILT;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}		
	}
	
	public String delete() 
	{

		try {
				object = optdefManager.getObjectById(object.getOptcode());			
				optdefManager.deleteObject(object);
				codeRepositoryManager.refresh("optcode");
				deletedMessage();
			} catch (Exception e) {
				log.error(e.getMessage(), e);	
				return EDIT;
			}		
			return SUCCESS ;
	
	}	
	
	public String save() {
        boolean operFlag = true;
        try {
            
            FOptdef dbobject = optdefManager.getObjectById(object.getOptcode());
            if (dbobject != null)
            {
                dbobject.copyNotNullProperty(object);
                object=dbobject;
            }
            try {           
                optdefManager.saveObject(object);
                codeRepositoryManager.refresh("optcode");   
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
	
	
}
