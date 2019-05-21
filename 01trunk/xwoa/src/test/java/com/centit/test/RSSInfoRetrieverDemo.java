package com.centit.test;

import java.net.URL;

import org.gnu.stealthp.rsslib.RSSHandler;
import org.gnu.stealthp.rsslib.RSSParser;

public class RSSInfoRetrieverDemo {

 // 这里定义一个本地的rss摘要，（对应我的博客空间） 
    public static final String localRSS = "charles.rss"; 
     
    //这里定义一个远程的rss摘要的地址(对应人民网） 
    public static final String remoteRSS="http://www.people.com.cn/rss/politics.xml"; 
 
    public static void main(String[] args) throws Exception { 
 
         
        // DEMO 1: 让RSSParser去解析本地某个rss文件 
        // 取得本地的rss 
      /*  RSSHandler localRSSHandler = new RSSHandler(); 
        RSSParser.parseXmlFile(localRSS, localRSSHandler, false); 
        // 取得rss元素的信息并且打印在控制台上 
        String localRSSInfo=RSSInfoRetriever.getRSSInfo(localRSSHandler); 
        System.out.println("*************Charles博客的 rss信息如下****************"); 
        System.out.println(localRSSInfo); 
        System.out.println("****************************************************"); 
          */   
 
        // DEMO 2: 让RSSParser去解析远程rss的url 
        // 取得远程的rss 
        RSSHandler remoteRSSHandler = new RSSHandler(); 
        RSSParser.parseXmlFile(new URL(remoteRSS), remoteRSSHandler, false); 
        // 取得rss元素的信息并且打印在控制台上 
        String remoteRSSInfo=RSSInfoRetriever.getRSSInfo(remoteRSSHandler); 
        System.out.println("****************人民网的 rss信息如下******************"); 
        System.out.println(remoteRSSInfo); 
        System.out.println("****************************************************"); 
        
 
    } 
}
