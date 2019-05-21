package com.centit.support.searcher;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import org.apache.solr.client.solrj.*;
import org.apache.solr.client.solrj.impl.HttpSolrServer;

import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import com.centit.core.utils.PageDesc;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.util.SysParametersUtils;


public class Searcher {
	private Searcher(){}
	
	public static SolrServer server = new SearchServer(CodeRepositoryUtil.getValue("SYSPARAM", "SOLR_HOME")).getHttpSolrServer();
	
	public static List<DocDesc> search(String sQuery){
	    Query query = null;  
	    IndexSearcher sh = null;
	    List<DocDesc>  res = null;
	    try { 
	    	Directory indexDir = FSDirectory.open(new File(SearchConfig
				.getConfig().getIndexDir()));
		    sh = new IndexSearcher(indexDir);
		    Analyzer analyzer = new StandardAnalyzer(SearchConfig.getConfig().getLuceneVersion());   
	        QueryParser qp = new QueryParser(SearchConfig.getConfig().getLuceneVersion(),SearchConfig.FIELD_CONTENT, analyzer);   
	        query = qp.parse(sQuery);   
	        res = new ArrayList<DocDesc>();
		    if (sh != null && query != null) {   
		        TopDocs tdocs = sh.search(query, SearchConfig.MAX_SEARCH_RET_COUNT); 
		        for(int i=0;i<tdocs.totalHits;i++){
		        	Document doc = sh.doc(tdocs.scoreDocs[i].doc);
		        	res.add( new DocDesc(doc));
		        }
		    }
		    return res;
		} catch (Exception e) {
		        e.printStackTrace();
		}
		
		return res;
	}
	
	public static List<DocDesc> search(SearchCondition cond){
	    List<DocDesc>  res = null;
	    try { 
		    IndexSearcher sh = null;
		   
		    //手动设置索引目录为solr的index目录
		   // SearchConfig.getConfig().setIndexDir("D:\\solr\\data\\index");
	    	
		    Directory indexDir = FSDirectory.open(new File(SysParametersUtils.getIndexHome()));
		    sh = new IndexSearcher(indexDir);

		    Query query = cond.makeQuery();
		    res = new ArrayList<DocDesc>();
		    if (sh != null && query != null) {   
		    	Filter filt = cond.getTimeFilter();
		        TopDocs tdocs ;
		        if(filt==null)
		        	tdocs = sh.search(query, SearchConfig.MAX_SEARCH_RET_COUNT); 
		        else
		        	tdocs = sh.search(query,filt, SearchConfig.MAX_SEARCH_RET_COUNT); 
		        for(int i=0;i<tdocs.totalHits;i++){
		        	Document doc = sh.doc(tdocs.scoreDocs[i].doc);
		        	res.add( new DocDesc(doc));
		        }
		    }
		    return res;
		} catch (Exception e) {
		        e.printStackTrace();
		}
		
		return res;
	}
	
	// 直接搜索内容
	public static List<DocDesc> solrSearch(String sQuery){
		SolrDocumentList docs = null;
		
		List<DocDesc> results = new ArrayList<DocDesc>();
		
		try{				

			SolrQuery query = new SolrQuery();
			query.setQuery(SearchConfig.FIELD_CONTENT + ":" + sQuery);			

			QueryResponse rsp = server.query(query);
			query.setStart(1);
            query.setRows(20);
			docs = rsp.getResults();
			
			for(int i=0;i<docs.getNumFound();i++){
			    SolrDocument doc = docs.get(i);
			    results.add(new DocDesc(doc));
			}
			
		}catch (Exception e){
			e.printStackTrace();			
		}		
		return results;
	}
	
	// 带分页的多条件搜索
	public static List<DocDesc> solrSearch(SearchCondition cond,PageDesc pageDesc){
        SolrDocumentList docs = null;
        
        List<DocDesc> results = new ArrayList<DocDesc>();
        try{               

            cond.setPageDesc(pageDesc);
            SolrQuery query = cond.makeSolrQuery();               
  
            QueryResponse rsp = server.query(query);
            
            docs = rsp.getResults();
            Map<String,Map<String,List<String>>> highlights = rsp.getHighlighting();
            
            for(SolrDocument doc : docs){
                if(highlights.get(doc.getFieldValue(SearchConfig.FIELD_ID)).get(SearchConfig.FIELD_CONTENT) != null){  
                    String hl = highlights.get(doc.getFieldValue(SearchConfig.FIELD_ID)).get(SearchConfig.FIELD_CONTENT).get(0);
                    doc.addField(SearchConfig.FIELD_SUMMARY, hl);
                } 
                results.add(new DocDesc(doc));
            }

            pageDesc.setTotalRows(Long.valueOf(docs.getNumFound()).intValue());
            int pageNum = (Long.valueOf(query.getStart()).intValue())/10 + 1;
            pageDesc.setPageNo(pageNum);
            pageDesc.setPageSize(query.getRows());
            
        }catch (Exception e){
            e.printStackTrace();            
        }       
        return results;
    }
	
	// 多条件搜索
	public static List<DocDesc> solrSearch(SearchCondition cond){
        SolrDocumentList docs = null;
        
        List<DocDesc> results = new ArrayList<DocDesc>();
        try{               

            SolrQuery query = new SolrQuery();

            query = cond.makeSolrQuery();               
  
            QueryResponse rsp = server.query(query);
            
            docs = rsp.getResults();
            Map<String,Map<String,List<String>>> highlights = rsp.getHighlighting();
            
            for(SolrDocument doc : docs){
                if(highlights.get(doc.getFieldValue(SearchConfig.FIELD_ID)).get(SearchConfig.FIELD_CONTENT) != null){  
                    String hl = highlights.get(doc.getFieldValue(SearchConfig.FIELD_ID)).get(SearchConfig.FIELD_CONTENT).get(0);
                    doc.addField(SearchConfig.FIELD_SUMMARY, hl);
                }
                
                results.add(new DocDesc(doc));
            }
            

        }catch (Exception e){
            e.printStackTrace();            
        }       
        return results;
    }

	public static void deleteIndexByQuery(String filecode,String optid){
        try {
            
            server.deleteByQuery(SearchConfig.FIELD_ID + ":" + optid + filecode);
            server.optimize();
            server.commit();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public static void deleteIndexByQuery(String dataID){
        try {
            
            server.deleteByQuery(SearchConfig.FIELD_ID + ":" + dataID);
            server.optimize();
            server.commit();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
}
