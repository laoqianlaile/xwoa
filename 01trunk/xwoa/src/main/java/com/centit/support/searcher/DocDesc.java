package com.centit.support.searcher;

import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.solr.common.SolrDocument;

public class DocDesc {
	public static String TYPE_FILE="file";//存放在文件中
	public static String TYPE_DB="db";//存放在数据库中
	public static String TYPE_URL="url";//外部资源
    public static String OPT="opt";//外部资源

	public static enum ResType {UNKNOW, FILE,URL,DB,OPT};
	private ResType type;
	private String value;
	private String createTime;
	private String optID;
	/*private String opt;*/
    private String owner;
    
    private String dataID;
    private String title;
    private String content;
    private String extension;
    private String method;
    private String opttag;

    private String summary;
    
    //索引Content内容时，通过此属性查询数据库
    private String queryContentJson;

    public String getQueryContentJson() {
        return queryContentJson;
    }

    public void setQueryContentJson(String queryContentJson) {
        this.queryContentJson = queryContentJson;
    }
/*
    public String getValue() {
        return value;
    }*/

    public void setValue(String value) {
        this.value = value;
    }
/*
    public String getsCreateTime() {
        return createTime;
    }

    public void setsCreateTime(String sCreateTime) {
        this.createTime = sCreateTime;
    }
*/
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setType(ResType type) {
        this.type = type;
    }

    public String getDataID() {
        return dataID;
    }

    public void setDataID(String dataID) {
        this.dataID = dataID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getOpttag() {
        return opttag;
    }

    public void setOpttag(String opttag) {
        this.opttag = opttag;
    }

    public String getOptID() {
        return optID;
    }

    public void setOptID(String optID) {
        this.optID = optID;
    }

    public DocDesc(String dataID) {
        this.dataID = dataID;
    }
    
    public DocDesc(ResType resType, String sOptID, String sOwner, String dataID, String title,
            String extension, String method, String opttag) {
        super();
        this.type = resType;
        this.optID = sOptID;
        this.owner = sOwner;
        this.dataID = dataID;
        this.title = title;
        this.extension = extension;
        this.method = method;
        this.opttag = opttag;
    }

    public DocDesc(String sOptID, String sOwner, String dataID, String title,
            String extension, String method, String opttag) {
        super();
        this.optID = sOptID;
        this.owner = sOwner;
        this.dataID = dataID;
        this.title = title;
        this.extension = extension;
        this.method = method;
        this.opttag = opttag;
    }

    public ResType getType(){
		return  type;
	}
	public void setId(String sId) {
		this.value = sId;
	}
	public String getUrl(){
		return  value;
	}
	public String getFilePath(){
		return  value;
	}
	public String getFetchSql(){
		return  value;
	}
	public String getValue(){
		return  value;
	}
	public String getId(){
		return  value;
	}		
	public DocDesc(){
		type = ResType.UNKNOW;
	}
	
	public DocDesc(Document document ){
		bindDocument( document );
	}

    public DocDesc(SolrDocument doc){
        convertToDocDesc(doc);
    }

	public DocDesc(String sValue, ResType type){
		this.value = sValue;
		this.type = type;
	}
	
	public void setFilepath(String sfilepath){
		this.value = sfilepath;
		this.type = ResType.FILE;
	}
	public void setUrl(String sUrl){
		this.value = sUrl;
		this.type = ResType.URL;
	}
	public void setSql(String sSql){
		this.value = sSql;
		this.type = ResType.DB;
	}
	
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String sCreateTime) {
		this.createTime = sCreateTime;
	}

	public void bindDocument( Document document ){
		String sType = document.get(SearchConfig.FIELD_TYPE);
		if(TYPE_FILE.equals(sType))
			type = ResType.FILE;
		else if(TYPE_URL.equals(sType))
			type = ResType.URL;
		else if(TYPE_DB.equals(sType))
			type = ResType.DB;
		else 
			type = ResType.UNKNOW;
		/*
		switch(type){
		case FILE :
			sValue = document.get(SearchConfig.FIELD_FILE_ID);
			break;
		case URL :
			sValue = document.get(SearchConfig.FIELD_URL_ID);
			break;
		case DB :
			sValue = document.get(SearchConfig.FIELD_DB_ID);
			break;
		}
		*/
		value = document.get(SearchConfig.FIELD_URI);
		optID = document.get(SearchConfig.FIELD_OPT_TYPE);
		owner = document.get(SearchConfig.FIELD_DOC_OWNER);
		summary = document.get(SearchConfig.FIELD_SUMMARY);
		createTime = document.get(SearchConfig.FIELD_CREATE_TIME);
	}
	
	@SuppressWarnings("unchecked")
	public void convertToDocDesc(SolrDocument doc){
	    String sType = (String)doc.get(SearchConfig.FIELD_TYPE);
        if(TYPE_FILE.equals(sType))
            type = ResType.FILE;
        else if(TYPE_URL.equals(sType))
            type = ResType.URL;
        else if(TYPE_DB.equals(sType))
            type = ResType.DB;
        else 
            type = ResType.UNKNOW;
        
        if(null != doc.get(SearchConfig.FIELD_URI))
            value = (String)doc.get(SearchConfig.FIELD_URI);
        if(null != doc.get(SearchConfig.FIELD_CONTENT)) {
			Object object = doc.get(SearchConfig.FIELD_CONTENT);
			if(object instanceof String) {
				content = (String)doc.get(SearchConfig.FIELD_CONTENT);
			}else if(object instanceof List) {
				List<Object> objs = (List<Object>)object;
				StringBuilder sb = new StringBuilder();
				for (Object o : objs) {
					sb.append(o.toString());
				}
				
				content = sb.toString();
			}
		}
        	
        	
        if(null != doc.get(SearchConfig.FIELD_ID))
            dataID = (String)doc.get(SearchConfig.FIELD_ID);
        if(null != doc.get(SearchConfig.FIELD_CREATE_TIME))
            createTime = (String)doc.get(SearchConfig.FIELD_CREATE_TIME);
        if(null != doc.get(SearchConfig.FIELD_FILE_TYPE))
            extension = (String)doc.get(SearchConfig.FIELD_FILE_TYPE);
        if(null != doc.get(SearchConfig.FIELD_METHOD))
            method = (String)doc.get(SearchConfig.FIELD_METHOD);
        if(null != doc.get(SearchConfig.FIELD_PARAMETER))
            opttag = (String)doc.get(SearchConfig.FIELD_PARAMETER);
        if(null != doc.get(SearchConfig.FIELD_OPT_TYPE))
            optID = (String)doc.get(SearchConfig.FIELD_OPT_TYPE);
        if(null != doc.get(SearchConfig.FIELD_DOC_OWNER))
            owner = (String)doc.get(SearchConfig.FIELD_DOC_OWNER);
        if(null != doc.get(SearchConfig.FIELD_TITLE))
            title = (String)doc.get(SearchConfig.FIELD_TITLE);
        if(null != doc.get(SearchConfig.FIELD_SUMMARY))
            summary = (String)doc.get(SearchConfig.FIELD_SUMMARY);
	    
	}
	
	public Term getTerm(){
		/*
		switch(type){
		case FILE :
			return new Term(SearchConfig.FIELD_FILE_ID,sValue);
		case URL :
			return new Term(SearchConfig.FIELD_URL_ID,sValue);
		case DB :
			return new Term(SearchConfig.FIELD_DB_ID,sValue);
		}	
		*/
		return  new Term(SearchConfig.FIELD_URI,value);
	}

	public static String makeSummary(String sContent) {
		int nsl = sContent.length();
		if(nsl<=1024)
			return sContent;
		return sContent.substring(0, 256);//取文本的前256个字符作为摘要
	}
}
