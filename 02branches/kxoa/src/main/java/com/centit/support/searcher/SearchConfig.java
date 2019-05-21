package com.centit.support.searcher;

import org.apache.lucene.util.Version;

public  class SearchConfig {
    
    public static String FIELD_ID="id";
    public static String FIELD_TITLE="title";
    public static String FIELD_CONTENT="content";
	public static String FIELD_SUMMARY="summary";
	public static String FIELD_TYPE="type";
	public static String FIELD_CREATE_TIME="modified";
	public static String FIELD_URI="uri";
	public static String FIELD_FILE_ID=FIELD_URI;
	public static String FIELD_DB_ID=FIELD_URI;
	public static String FIELD_URL_ID=FIELD_URI;
	public static String FIELD_FILE_TYPE="ext";//文件扩展名标识文件格式
	public static String FIELD_OPT_TYPE="opt";//业务类别代码
	public static String FIELD_DOC_OWNER="owner";//所属用户
	
	public static String FIELD_FILECODE="filecode";
	
	public static String FIELD_METHOD="method";
	public static String FIELD_PARAMETER="parameter";

	public static int  MAX_SEARCH_RET_COUNT=50;//所属用户

	private static SearchConfig inst=null;
	
	private Version luceneversion;// Version.LUCENE_30
	private String indexDir;
	
	private SearchConfig(){
		setIndexDir("./");
		setLuceneVersion(Version.LUCENE_36);
	}
	
	private SearchConfig(String indDir){
		setIndexDir(indDir);
		setLuceneVersion(Version.LUCENE_36);
	}
	
	public static SearchConfig getConfig(){
		if(inst==null)
			inst = new SearchConfig();
		return inst;
	}
	
	public void setIndexDir(String indDir) {
		this.indexDir = indDir;
	}
	
	public String getIndexDir() {
		return indexDir;
	}

	public  void setLuceneVersion(Version lv) {
		this.luceneversion = lv;
	}

	public  Version getLuceneVersion() {
		return luceneversion;
	}
	
}
