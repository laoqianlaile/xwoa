package com.centit.support.searcher;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.paoding.analysis.analyzer.PaodingAnalyzer;

import org.apache.commons.lang.StringUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriter.MaxFieldLength;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;

import com.centit.app.po.FileinfoFs;
import com.centit.app.po.Publicinfo;
import com.centit.support.utils.DatetimeOpt;
import com.centit.sys.service.CodeRepositoryUtil;
import com.centit.sys.util.SysParametersUtils;

public class Indexer {
	/*
	 * public Analyzer createIndexWriter(String fileType){
	 * if("txt".equalsIgnoreCase(fileType)) return new
	 * StandardAnalyzer(SearchConfig.getConfig().getLuceneVersion() ); return
	 * new StandardAnalyzer(SearchConfig.getConfig().getLuceneVersion() ); }
	 */
	private Indexer(){}
	private static String[] CanIndexFileType = { "txt", "doc", "docx", "xls",
			"xlsx", "ppt", "pptx","vsd", "htm", "html", "rtf", "pdf" };

	public static boolean canIndex(String fileExtName) {
		for (int i = 0; i < CanIndexFileType.length; i++)
			if (CanIndexFileType[i].equalsIgnoreCase(fileExtName))
				return true;
		return false;
	}

    public static Map<String, String> fileTypes;

    static {
        fileTypes = new HashMap<String, String>();
        fileTypes.put("pdf", "pdf");
        fileTypes.put("doc", "word");
        fileTypes.put("docx", "word");
        fileTypes.put("xls", "excel");
        fileTypes.put("xlsx", "excel");
        fileTypes.put("txt", "txt");

    }

	public static void indexFile(String filePath, String fileExtName)
	{
		indexFile(null,null, filePath, filePath ,fileExtName,DocDesc.ResType.FILE,null,null,null,null);
	}

	public static void indexFile(String filePath)
	{
		String fileExtName = filePath.substring(filePath.lastIndexOf(".")+1);
		indexFile(null,null, filePath, filePath ,fileExtName,DocDesc.ResType.FILE,null,null,null,null);
	}
	
	public static void indexFile(String filePath,String opt,String owner) 
	{
		String fileExtName = filePath.substring(filePath.lastIndexOf(".")+1);
		indexFile(null,null, filePath, filePath ,fileExtName,DocDesc.ResType.FILE,opt,owner,null,null);
	}
	
	public static void indexFile(String filePath, String url,
			 String fileExtName, String opt,String owner) 
	{
		indexFile(null,null, filePath, url ,fileExtName,DocDesc.ResType.FILE,opt,owner,null,null);
	}
	
	public static void indexFile(String filePath, String url,
				String opt,String owner) 
	{
		String fileExtName = filePath.substring(filePath.lastIndexOf(".")+1);
		indexFile(null,null, filePath, url ,fileExtName,DocDesc.ResType.FILE,opt,owner,null,null);
	}
	
	public static void indexFile(String dataID,String fileName,String filePath, String url,String fileExtName,
			DocDesc.ResType type,  String opt,String owner,String method,String parameter) {
		String sContent=null;
		if ("txt".equalsIgnoreCase(fileExtName))
			sContent = TextExtractor.getTextFileContent(filePath);
		else if ("doc".equalsIgnoreCase(fileExtName))
			sContent = TextExtractor.getDocFileContent(filePath);
		else if ("docx".equalsIgnoreCase(fileExtName))
			sContent = TextExtractor.getDocxFileContent(filePath);
		else if ("pdf".equalsIgnoreCase(fileExtName))
			sContent = TextExtractor.getPdfFileContent(filePath);
		else if ("rtf".equalsIgnoreCase(fileExtName))
			sContent = TextExtractor.getRtfFileContent(filePath);
		else if ("html".equalsIgnoreCase(fileExtName)
				|| "htm".equalsIgnoreCase(fileExtName))
			sContent = TextExtractor.getHtmFileContent(filePath);
		else if ("xls".equalsIgnoreCase(fileExtName))
			sContent = TextExtractor.getXlsFileContent(filePath);
		else if ("xlsx".equalsIgnoreCase(fileExtName))
			sContent = TextExtractor.getXlsxFileContent(filePath);
		else if ("ppt".equalsIgnoreCase(fileExtName))
			sContent = TextExtractor.getPptFileContent(filePath);
		else if ("pptx".equalsIgnoreCase(fileExtName))
			sContent = TextExtractor.getPptxFileContent(filePath);
		else if ("vsd".equalsIgnoreCase(fileExtName))
			sContent = TextExtractor.getVsdFileContent(filePath);
		if(StringUtils.isNotEmpty(sContent)){
			SolrInputDocument doc = createSolrDocument(dataID,fileName,sContent,type, url ,fileExtName.toLowerCase(), opt, owner,method, parameter);
			writeSolrIndex(doc);
		}
	}
	
	// 直接索引数据
	public static void indexData(String dataID,String fileName,String fileExtName,String content,
            DocDesc.ResType type,  String opt,String owner,String method,String parameter){
	    if(StringUtils.isNotEmpty(content)){
            SolrInputDocument doc = createSolrDocument(dataID,fileName,content,type, null ,fileExtName.toLowerCase(), opt, owner,method, parameter);
            writeSolrIndex(doc);
        }
	}
	public static void indexFile(Publicinfo info, DocDesc docDesc){
	    indexFile(docDesc.getDataID(),info.getFilename(),SysParametersUtils.getUploadHome() + info.getFileinfoFs().getPath(),
	            SysParametersUtils.getUploadHome() + info.getFileinfoFs().getPath(),info.getFileextension(),
	            docDesc.getType(),docDesc.getOptID(),docDesc.getOwner(),docDesc.getMethod(),docDesc.getOpttag());
	}
	
	public static void indexFile(FileinfoFs info, DocDesc docDesc){
	    indexFile(docDesc.getDataID(),info.getFilename(),SysParametersUtils.getUploadHome() + info.getPath(),
	            SysParametersUtils.getUploadHome() + info.getPath(),info.getFileext(),
	            docDesc.getType(),docDesc.getOptID(),docDesc.getOwner(),docDesc.getMethod(),docDesc.getOpttag());
	}
	
    public static void indexFile(DocDesc docDesc) {
        indexData(docDesc.getDataID(), docDesc.getTitle(),
                docDesc.getExtension(), docDesc.getContent(),
                docDesc.getType(), docDesc.getOptID(), docDesc.getOwner(),
                docDesc.getMethod(), docDesc.getOpttag());
    }
	
	public static void indexByte(byte[] bbuf, String url)
	{
		indexByte(null, bbuf, url ,DocDesc.ResType.DB,"txt",null,null);
	}

	public static void indexByte(byte[] bbuf,String url,String opt,String owner) 
	{
		indexByte(null, bbuf, url ,DocDesc.ResType.DB,"txt",opt,owner);
	}
	
	public static void indexByte(byte[] bbuf, String url,
			 String fileExtName) 
	{
		indexByte(null, bbuf, url ,DocDesc.ResType.DB,fileExtName,null,null);
	}
	
	public static void indexByte(byte[] bbuf, String url,
			 String fileExtName, String opt,String owner) 
	{
		indexByte(null, bbuf, url ,DocDesc.ResType.DB,fileExtName,opt,owner);
	}
	
	public static void indexByte(byte[] bbuf, String url,
			DocDesc.ResType type, String fileExtName) 
	{
		indexByte(null, bbuf, url ,type,fileExtName,null,null);
	}	
	
	public static void indexByte(String fileName,byte[] bbuf  , String url,
			DocDesc.ResType type, String fileExtName, String opt,String owner) {
		String sContent=null;
		if ("txt".equalsIgnoreCase(fileExtName))
			sContent = TextExtractor.getTextByteContent(bbuf);
		else if ("doc".equalsIgnoreCase(fileExtName))
			sContent = TextExtractor.getDocByteContent(bbuf);
		else if ("docx".equalsIgnoreCase(fileExtName))
			sContent = TextExtractor.getDocxByteContent(bbuf);
		else if ("pdf".equalsIgnoreCase(fileExtName))
			sContent = TextExtractor.getPdfByteContent(bbuf);
		else if ("rtf".equalsIgnoreCase(fileExtName))
			sContent = TextExtractor.getRtfByteContent(bbuf);
		//else if ("html".equalsIgnoreCase(fileExtName)
			//	|| "htm".equalsIgnoreCase(fileExtName))
			//sContent = TextExtractor.getHtmByteContent(bbuf);
		else if ("xls".equalsIgnoreCase(fileExtName))
			sContent = TextExtractor.getXlsByteContent(bbuf);
		else if ("xlsx".equalsIgnoreCase(fileExtName))
			sContent = TextExtractor.getXlsxByteContent(bbuf);
		else if ("ppt".equalsIgnoreCase(fileExtName))
			sContent = TextExtractor.getPptByteContent(bbuf);
		//else if ("pptx".equalsIgnoreCase(fileType))
			//sContent = TextExtractor.getPptxByte(bbuf, storeFilePath);
		else if ("vsd".equalsIgnoreCase(fileExtName))
			sContent = TextExtractor.getVsdByteContent(bbuf);
		if(StringUtils.isNotEmpty(sContent)){
			Document doc = createDocument(null,fileName,sContent,type, url ,fileExtName.toLowerCase(),  opt, owner );
			writeIndexDocument(doc);
		}
	}	
	
	public static void indexWebUrl(String fileName, String url,String opt,String owner)  
	{
		String sContent = TextExtractor.getWebUrl(url);
		if(StringUtils.isNotEmpty(sContent)){
			Document doc = createDocument(null,fileName,sContent,DocDesc.ResType.URL, url ,"html",  opt, owner );
			writeIndexDocument(doc);
		}
	}	
	
	public static void indexWebUrl( String url) 
	{
		indexWebUrl( null, url,null,null);  
	}	

	public static void setIndexDir(String sDir) {
		SearchConfig.getConfig().setIndexDir(sDir);
	}

	public static void createIndexDir() {
		try {
			Directory indexDir = FSDirectory.open(new File(SearchConfig
					.getConfig().getIndexDir()));
			/*Analyzer analyzer = new StandardAnalyzer(SearchConfig.getConfig()
					.getLuceneVersion());
			IndexWriter indexWriter = new IndexWriter(indexDir, analyzer, true,
					MaxFieldLength.LIMITED);*/
			
			Analyzer analyzer = new PaodingAnalyzer();
            IndexWriter indexWriter = new IndexWriter(indexDir,new IndexWriterConfig(Version.LUCENE_36 ,analyzer));

			//indexWriter.optimize();
			indexWriter.close();

		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public static void createIndexDir(String sDir) {
		try {
			SearchConfig.getConfig().setIndexDir(sDir);
			Directory indexDir = FSDirectory.open(new File(sDir));
			
			Analyzer analyzer = new PaodingAnalyzer();
            IndexWriter indexWriter = new IndexWriter(indexDir,new IndexWriterConfig(Version.LUCENE_36 ,analyzer));

			//indexWriter.optimize();
			indexWriter.close();

		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	private static void writeIndexDocument(Document document) {
		try {
		    Directory indexDir = FSDirectory.open(new File(SysParametersUtils.getIndexHome()));
			
			Analyzer analyzer = new PaodingAnalyzer();
            IndexWriter indexWriter = new IndexWriter(indexDir,new IndexWriterConfig(Version.LUCENE_36 ,analyzer));

			indexWriter.addDocument(document);
			
			indexWriter.commit();

			indexWriter.close();

		} catch (Exception e) {
 			e.printStackTrace();
		}	
	}
	
	
	private static void writeSolrIndex(SolrInputDocument document) {
        try {
            SolrServer server = new SearchServer(CodeRepositoryUtil.getValue("SYSPARAM", "SOLR_HOME")).getHttpSolrServer();
            
            server.add(document);
            server.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }   
    }
	
	
	private static Document createDocument(String filecode,String fileName,String sContent, String sSummary,
			DocDesc.ResType type, String idDesc,String fileExtName, String opt,String owner) {
		Document resDoc=null;
		try {
			Document document = new Document();
			document.add(new Field(SearchConfig.FIELD_CONTENT, sContent, Field.Store.YES,
					Field.Index.ANALYZED));
			switch(type){
			case FILE :
				document.add(new Field(SearchConfig.FIELD_FILE_ID, idDesc, Field.Store.YES,
						Field.Index.NOT_ANALYZED));
				document.add(new Field(SearchConfig.FIELD_TYPE,DocDesc.TYPE_FILE, Field.Store.YES,
						Field.Index.NO));
				break;
			case URL :
				document.add(new Field(SearchConfig.FIELD_URL_ID, idDesc, Field.Store.YES,
						Field.Index.NOT_ANALYZED));
				document.add(new Field(SearchConfig.FIELD_TYPE,DocDesc.TYPE_URL, Field.Store.YES,
						Field.Index.NO));
				break;
			case DB :
				document.add(new Field(SearchConfig.FIELD_DB_ID, idDesc, Field.Store.YES,
						Field.Index.NOT_ANALYZED));
				document.add(new Field(SearchConfig.FIELD_TYPE,DocDesc.TYPE_DB, Field.Store.YES,
						Field.Index.NO));
				break;
			}
			document.add(new Field(SearchConfig.FIELD_FILECODE, filecode , Field.Store.YES,
                    Field.Index.NOT_ANALYZED));
			document.add(new Field(SearchConfig.FIELD_SUMMARY, sSummary , Field.Store.YES,
					Field.Index.NO));
			document.add(new Field(SearchConfig.FIELD_CREATE_TIME, new SimpleDateFormat(
					"yyyy:MM:dd HH:mm:ss").format(new Date(System.currentTimeMillis())), Field.Store.YES,
					Field.Index.NO));
			document.add(new Field(SearchConfig.FIELD_FILE_TYPE ,fileExtName, Field.Store.YES,
					Field.Index.NOT_ANALYZED));
			if(StringUtils.isNotEmpty(opt))
				document.add(new Field(SearchConfig.FIELD_OPT_TYPE ,opt, Field.Store.YES,
					Field.Index.NOT_ANALYZED));
			if(StringUtils.isNotEmpty(owner))
				document.add(new Field(SearchConfig.FIELD_DOC_OWNER ,owner, Field.Store.YES,
					Field.Index.NOT_ANALYZED));
			if(StringUtils.isNotEmpty(fileName))
			    document.add(new Field(SearchConfig.FIELD_TITLE, fileName , Field.Store.YES,
	                    Field.Index.ANALYZED));
			resDoc = document;

		} catch (Exception e) {
			e.printStackTrace();
		}		
		return resDoc;
	}
	
    private static SolrInputDocument createSolrDocument(String dataID,String fileName,String sContent,
            DocDesc.ResType type, String url,String fileExtName, String optid,String owner,String method,String parameter) {
	    SolrInputDocument resDoc=null;
        try {
            SolrInputDocument document = new SolrInputDocument();
            document.addField(SearchConfig.FIELD_CONTENT, sContent);
            switch(type){
            case FILE :
                document.addField(SearchConfig.FIELD_FILE_ID, url);
                document.addField(SearchConfig.FIELD_TYPE,DocDesc.TYPE_FILE);
                break;
            case URL :
                document.addField(SearchConfig.FIELD_URL_ID, url);
                document.addField(SearchConfig.FIELD_TYPE,DocDesc.TYPE_URL);
                break;
            case DB :
                document.addField(SearchConfig.FIELD_DB_ID, url);
                document.addField(SearchConfig.FIELD_TYPE,DocDesc.TYPE_DB);
                break;
            }
            // document.addField(SearchConfig.FIELD_FILECODE, infocode );
            
            document.addField(SearchConfig.FIELD_ID, dataID);
            document.addField(SearchConfig.FIELD_CREATE_TIME, DatetimeOpt.convertDateToString(DatetimeOpt.currentSqlDate()));
            
            document.addField(SearchConfig.FIELD_FILE_TYPE ,fileExtName);
            
            document.addField(SearchConfig.FIELD_METHOD, method);
            document.addField(SearchConfig.FIELD_PARAMETER, parameter);
            if(StringUtils.isNotEmpty(optid))
                document.addField(SearchConfig.FIELD_OPT_TYPE ,optid);
            if(StringUtils.isNotEmpty(owner))
                document.addField(SearchConfig.FIELD_DOC_OWNER ,owner);
            if(StringUtils.isNotEmpty(fileName))
                document.addField(SearchConfig.FIELD_TITLE, fileName );
            resDoc = document;

        } catch (Exception e) {
            e.printStackTrace();
        }       
        return resDoc;
    }
	

	private static Document createDocument(String filecode,String fileName,String sContent,
			DocDesc.ResType type, String idDesc,String fileExtName ,String opt,String owner) {
		return createDocument(filecode,fileName, sContent,DocDesc.makeSummary(sContent), type,idDesc,fileExtName,opt,owner);
	}
	
	/*private static SolrInputDocument createSolrDocument(String filecode,String fileName,String sContent,
            DocDesc.ResType type, String idDesc,String fileExtName ,String opt,String owner){
	    return createSolrDocument(filecode,fileName, sContent, type,idDesc,fileExtName,opt,owner);
	}*/

	public static int removeIndex(DocDesc did) {
		int deleted = 0;
		Term tm = did.getTerm();
		if(tm==null)
			return deleted;
		try {
			Directory indexDir = FSDirectory.open(new File(SearchConfig
					.getConfig().getIndexDir()));
		    IndexReader reader = IndexReader.open(indexDir, false); // we don't want read-only because we are about to delete
		    deleted = reader.deleteDocuments(tm);
		    reader.close();
		     //要彻底删除索引还需要执行下面的代码
			Analyzer analyzer = new StandardAnalyzer(SearchConfig.getConfig()
					.getLuceneVersion());
			IndexWriter indexWriter = new IndexWriter(indexDir, analyzer, true,
					MaxFieldLength.LIMITED);

			indexWriter.optimize();
			indexWriter.close();
			
			indexDir.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deleted;
	}
	
	//修改索引删除
	//用IndexWriter删除索引
	public static void deleteIndex(DocDesc did) {

        Term tm = did.getTerm();

        try {
            Directory indexDir = FSDirectory.open(new File(SearchConfig
                    .getConfig().getIndexDir()));
            
            Analyzer analyzer = new PaodingAnalyzer();
            IndexWriter writer = new IndexWriter(indexDir,new IndexWriterConfig(Version.LUCENE_36 ,analyzer)); // we don't want read-only because we are about to delete
            writer.deleteDocuments(tm);
            writer.close();
            
            indexDir.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	// 非必要情况下，不轻易使用
	public void commitIndexChange(IndexWriter writer){
	    try{
	        writer.commit(); 
	    }catch(Exception e){
	        e.printStackTrace();
	    }	    
	}


}
