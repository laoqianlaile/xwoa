package com.centit.support.searcher;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeFilter;
import org.apache.solr.client.solrj.SolrQuery;

import com.centit.core.utils.PageDesc;

public class SearchCondition {
	
    private String sTitle;//文章标题或文件名
    
    private	String sKeyWord;//关键字，多个关键字用空格隔开
	private List<String> sTypeList;//相关的文件类别  如果 空 则为不限
	private List<String> sOptList;//相关的业务类别  如果 空 则为不限

	private String sOwner;// 文档上传用户
	private String sBeginTime;//起始时间
	private String sEndTime;//终止时间	
		
	private String[] sTypes;
	private String[] sOpts;
	private PageDesc pageDesc;
	
    public PageDesc getPageDesc() {
        return pageDesc;
    }
    public void setPageDesc(PageDesc pageDesc) {
        this.pageDesc = pageDesc;
    }
    public String getsOwner() {
        return sOwner;
    }
    public void setsOwner(String sOwner) {
        this.sOwner = sOwner;
    }
    public String[] getsTypes() {
        return sTypes;
    }
    public void setsTypes(String[] sTypes) {
        this.sTypes = sTypes;
    }
    public String[] getsOpts() {
        return sOpts;
    }
    public void setsOpts(String[] sOpts) {
        this.sOpts = sOpts;
    }
    public String getsTitle() {
        return sTitle;
    }
    public void setsTitle(String sTitle) {
        this.sTitle = sTitle;
    }
    public String getBeginTime() {
		return sBeginTime;
	}
	public void setBeginTime(String sBeginTime) {
		this.sBeginTime = sBeginTime;
	}
	public String getEndTime() {
		return sEndTime;
	}
	public void setEndTime(String sEndTime) {
		this.sEndTime = sEndTime;
	}	
	public SearchCondition(){
		sTypeList = null;
		sOptList = null;
		sOwner = null;
		sBeginTime = null;
		sEndTime = null;
		pageDesc = new PageDesc(1,10);
	}
	public SearchCondition(String sKW){
		sTypeList = null;
		sOptList = null;
		sOwner = null;
		sBeginTime = null;
		sEndTime = null;
		sKeyWord = sKW;
		pageDesc = new PageDesc(1,10);
	}
	
	public String getKeyWord() {
		return sKeyWord;
	}
	public void setKeyWord(String sKeyWord) {
		this.sKeyWord = sKeyWord;
	}
	public List<String> getTypeList() {
		return sTypeList;
	}
	public void setTypeList(List<String> sTypeList) {
		this.sTypeList = sTypeList;
	}
	public void addType(String sType) {
		if( sTypeList == null)
			sTypeList = new ArrayList<String>();
		sTypeList.add(sType);
	}
	
	public List<String> getOptList() {
		return sOptList;
	}
	public void setOptList(List<String> sOptList) {
		this.sOptList = sOptList;
	}
	public void addOpt(String sOpt) {
		if( sOptList == null)
			sOptList = new ArrayList<String>();
		sOptList.add(sOpt);
	}
	public String getOwner() {
		return sOwner;
	}
	public void setOwner(String sOwner) {
		this.sOwner = sOwner;
	}
	//对时间进行过滤
	public Filter getTimeFilter(){
		if ( StringUtils.isNotEmpty(sBeginTime)||
				StringUtils.isNotEmpty(sEndTime) ){
	        return new TermRangeFilter(SearchConfig.FIELD_CREATE_TIME, 
	        			sBeginTime,sEndTime,true,true);
        }
		return null;
	}
	
	public Query makeQuery()
	{
        try {
        	Query query = null;  
    	    Analyzer analyzer = new StandardAnalyzer(SearchConfig.getConfig().getLuceneVersion());   
            QueryParser qp = new QueryParser(SearchConfig.getConfig().getLuceneVersion(),SearchConfig.FIELD_CONTENT, analyzer);   
			query = qp.parse(SearchConfig.FIELD_CONTENT + ":" + sKeyWord );
	        BooleanQuery typeNegativeSearch = new BooleanQuery();
	        typeNegativeSearch.add(query,Occur.MUST);
	        
	        //搜索标题或文件
	        if(StringUtils.isNotEmpty(sTitle)){
	            typeNegativeSearch.add(new TermQuery(new Term(SearchConfig.FIELD_TITLE,sTitle)),Occur.MUST);
	        }
	        //只搜索某个人自己上传的文档
	        if(StringUtils.isNotEmpty(sOwner)){
	        	typeNegativeSearch.add(new TermQuery(new Term(SearchConfig.FIELD_DOC_OWNER,sOwner)),Occur.MUST);
	        }
	        //只搜索某一些指定类型的文档
	    	if(sTypeList !=null && !sTypeList.isEmpty()){
	    		PhraseQuery typeQuery = new PhraseQuery();
	    		for(String sType:sTypeList){
	    			typeQuery.add(new Term(SearchConfig.FIELD_FILE_TYPE,sType));
	    		}
    			typeNegativeSearch.add(typeQuery,Occur.MUST);
	    	}
	    	//只搜索某一些指定的业务相关的文档
	    	if(sOptList !=null && !sOptList.isEmpty()){
	    		PhraseQuery optQuery = new PhraseQuery();
	    		for(String sOpt:sOptList){
	    			optQuery.add(new Term(SearchConfig.FIELD_OPT_TYPE,sOpt));
	    		}
    			typeNegativeSearch.add(optQuery,Occur.MUST);
	    	}
	        return typeNegativeSearch;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}   

	}
	
	// solr查询条件设置
	public SolrQuery makeSolrQuery(){
	    SolrQuery query = new SolrQuery();

	    //Map<String,String> map = new HashMap<String,String>();	    
	    //Map<String,String[]> multiMap = new HashMap<String, String[]>();
	    
	    String queryString ;
    
	    // 关键字
	    String[] keywords = sKeyWord.split(" ");
	    String keys = "";
	    for(int i=0;i<keywords.length;i++){
	        if(i > 0)
	            keys += "+" + keywords[i];  
            else 
                keys = keywords[i];	        
	    }
	    queryString = SearchConfig.FIELD_CONTENT + ":" + keys ;
	    
	    // 业务ID
	    if(sOpts !=null && sOpts.length > 0){
            String s = "";
            if(sOpts.length == 1)
                queryString += " && " + SearchConfig.FIELD_OPT_TYPE + ":" + sOpts[0];           
            else {
                for (int i = 0; i < sOpts.length; i++) {                  
                    if(i == 0)
                        s = "(" + SearchConfig.FIELD_OPT_TYPE + ":" + sOpts[i];
                    else if(i == sOpts.length-1)
                        s += " + " + SearchConfig.FIELD_OPT_TYPE + ":" + sOpts[i] + ")";
                    else
                        s += " + " + SearchConfig.FIELD_OPT_TYPE + ":" + sOpts[i];
                }
                queryString += " && " + s;
            }
            
           // multiMap.put(SearchConfig.FIELD_OPT_TYPE, sOpts);             
        }
	    
	    // 文档类别
	    if(sTypes !=null && sTypes.length > 0){
            String s = "";
            if(sTypes.length == 1)
                queryString += " && " + SearchConfig.FIELD_FILE_TYPE + ":" + sTypes[0];
            else{
                for(int i=0;i<sTypes.length;i++){
                    if(i == 0)
                        s += "(" + SearchConfig.FIELD_FILE_TYPE + ":" + sTypes[i];
                    else if(i == sTypes.length - 1)
                        s += " + " + SearchConfig.FIELD_FILE_TYPE + ":" + sTypes[i] + ")";
                    else 
                        s += " + " + SearchConfig.FIELD_FILE_TYPE + ":" + sTypes[i];
                }           
                queryString = queryString + " && " + s;
            }         

        }

	    // 文档所属
	    if(StringUtils.isNotBlank(sOwner)){
	        queryString += " && " + SearchConfig.FIELD_DOC_OWNER + ":" + sOwner;
	    }
	    query.setQuery(queryString);

	    //SolrParams params1 = new MultiMapSolrParams(multiMap);

	    //query.add(params1);
	    
	    // 时间范围
	    if(StringUtils.isNotBlank(sBeginTime) && StringUtils.isBlank(sEndTime)){
	        query.addFilterQuery(SearchConfig.FIELD_CREATE_TIME + ":" + "[" + sBeginTime + " TO *]");
	    }else if(StringUtils.isBlank(sBeginTime) && StringUtils.isNotBlank(sEndTime)){
	        query.addFilterQuery(SearchConfig.FIELD_CREATE_TIME + ":" + "[*" + " TO " + sEndTime + "]");
	    }else if(StringUtils.isNotBlank(sBeginTime) && StringUtils.isNotBlank(sEndTime)){
	        query.addFilterQuery(SearchConfig.FIELD_CREATE_TIME + ":" + "[" + sBeginTime + " TO " + sEndTime+ "]"); 
	    }

	    // 高亮设置
	    query.setHighlight(true);
	    query.addHighlightField(SearchConfig.FIELD_TITLE);
	    query.addHighlightField(SearchConfig.FIELD_CONTENT);
	    query.setHighlightFragsize(200);
	    query.setHighlightSimplePre("<font color=\"red\">");
	    query.setHighlightSimplePost("</font>");
	   
	    // 分页    
        query.setStart(Integer.valueOf((pageDesc.getPageNo()-1)*pageDesc.getPageSize()));
        query.setRows(Integer.valueOf(pageDesc.getPageSize()));
	    	    
	    return query;
	}

}