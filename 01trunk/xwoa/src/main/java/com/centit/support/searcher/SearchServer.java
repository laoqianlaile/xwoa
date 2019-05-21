package com.centit.support.searcher;

import org.apache.solr.client.solrj.impl.HttpSolrServer;

public class SearchServer {

    private String url = "192.168.128.16:8080";
 
    public SearchServer(String url){
        this.url = url;
    }
    
    public HttpSolrServer getHttpSolrServer(){
        
        /*HttpClient client = new HttpClient();  
        SolrServer solrServer = new HttpSolrServer(url, client);  */  // solr服务器不在本机    
        
        HttpSolrServer server = new HttpSolrServer( "http://" + url + "/solr" );   // Solr服务器在本机上用该方法
        server.setSoTimeout(60000);  // socket read timeout   
        server.setConnectionTimeout(60000);   
        server.setDefaultMaxConnectionsPerHost(1000);   
        server.setMaxTotalConnections(100);   
        server.setFollowRedirects(false);  // defaults to false   
        server.setAllowCompression(true);   
        server.setMaxRetries(1); 
        return server;
    }
}
