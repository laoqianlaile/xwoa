package com.centit.support.searcher;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.rtf.RTFEditorKit;

/*import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.util.PDFTextStripper;*/
import org.apache.poi.hdgf.extractor.VisioTextExtractor;
import org.apache.poi.hslf.HSLFSlideShow;
import org.apache.poi.hslf.extractor.PowerPointExtractor;
import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xslf.XSLFSlideShow;
import org.apache.poi.xslf.extractor.XSLFPowerPointExtractor;
import org.apache.poi.xssf.extractor.XSSFExcelExtractor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
/*import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.util.NodeList;
import org.htmlparser.visitors.HtmlPage;*/

public class TextExtractor {
	public static String getTextFileContent(String filePath) {
		try {
			InputStream inputStream = new FileInputStream(filePath);
			byte[] head = new byte[3];
			inputStream.read(head);
			String code = "gb2312";
			if (head[0] == -1 && head[1] == -2) {
				code = "UTF-16";
			}
			if (head[0] == -2 && head[1] == -1) {
				code = "Unicode";
			}
			if (head[0] == -17 && head[1] == -69 && head[2] == -65) {
				code = "UTF-8";
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath),code));
			StringBuffer content = new StringBuffer("");
			String str = null;
			while (null != (str = br.readLine())) {
				content.append(str + " ");
			}
			br.close();
			String sContent = content.toString();
			return sContent;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getTextByteContent(byte[] buff) {
		return String.valueOf(buff);
	}

	public static String getDocFileContent(String filePath) {
		try {

			WordExtractor wordExtractor = new WordExtractor(new FileInputStream(filePath));
			return wordExtractor.getText();
			/*
			 * StringBuffer content = new StringBuffer("");// 文档内容 HWPFDocument doc = new HWPFDocument(new
			 * FileInputStream(filePath)); Range range = doc.getRange(); int paragraphCount = range.numParagraphs();//
			 * 段落 for (int i = 0; i < paragraphCount; i++) {// 遍历段落读取数据 Paragraph pp = range.getParagraph(i);
			 * content.append(pp.text()); } indexFileContent(content.toString(),storeFilePath);
			 */
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getDocByteContent(byte[] buff) {
		try {
			WordExtractor wordExtractor = new WordExtractor(new ByteArrayInputStream(buff));
			return wordExtractor.getText();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getDocxFileContent(String filePath) {
		try {
			XWPFDocument docx = new XWPFDocument(new FileInputStream(filePath));
			XWPFWordExtractor wordExtractor = new XWPFWordExtractor(docx);
			return wordExtractor.getText();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static String getDocxByteContent(byte[] buff) {
		try {
			XWPFDocument docx = new XWPFDocument(new ByteArrayInputStream(buff));
			XWPFWordExtractor wordExtractor = new XWPFWordExtractor(docx);
			return wordExtractor.getText();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static String getXlsFileContent(String filePath) {
		try {

			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(filePath));
			ExcelExtractor excel = new ExcelExtractor(workbook);
			return excel.getText();

			/*
			 * StringBuffer content = new StringBuffer(""); int nSheets = workbook.getNumberOfSheets(); for (int i = 0;
			 * i < nSheets; i++) { HSSFSheet sheet = workbook.getSheetAt(i); int nRows = sheet.getLastRowNum(); for (int
			 * j = 0; j < nRows; j++) { HSSFRow row = sheet.getRow(j); int nCols = row.getLastCellNum(); for (int k = 0;
			 * k < nCols; k++) { HSSFCell cell = row.getCell(k); content.append(cell.toString()); content.append(" "); }
			 * } } indexFileContent(content.toString(), storeFilePath);
			 */
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getXlsByteContent(byte[] buff) {
		try {

			HSSFWorkbook workbook = new HSSFWorkbook(new ByteArrayInputStream(buff));
			ExcelExtractor excel = new ExcelExtractor(workbook);
			return excel.getText();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getXlsxFileContent(String filePath) {
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(filePath));
			XSSFExcelExtractor excel = new XSSFExcelExtractor(workbook);
			return excel.getText();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getXlsxByteContent(byte[] buff) {
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(new ByteArrayInputStream(buff));
			XSSFExcelExtractor excel = new XSSFExcelExtractor(workbook);
			return excel.getText();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getPptFileContent(String filePath) {
		try {
			HSLFSlideShow ppt = new HSLFSlideShow(new FileInputStream(filePath));
			PowerPointExtractor powerpoint = new PowerPointExtractor(ppt);
			return powerpoint.getText();
			/*
			 * StringBuffer content = new StringBuffer(""); SlideShow ss = new SlideShow(ppt); Slide[] slides =
			 * ss.getSlides(); for (int i = 0; i < slides.length; i++) { TextRun[] t = slides[i].getTextRuns(); for (int
			 * j = 0; j < t.length; j++) { content.append(t[j].getText()); } content.append(slides[i].getTitle()); }
			 * indexFileContent(content.toString(), storeFilePath);
			 */
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getPptByteContent(byte[] buff) {
		try {
			HSLFSlideShow ppt = new HSLFSlideShow(new ByteArrayInputStream(buff));
			PowerPointExtractor powerpoint = new PowerPointExtractor(ppt);
			return powerpoint.getText();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getPptxFileContent(String filePath) {
		try {
			XSLFSlideShow ppt = new XSLFSlideShow(filePath);
			XSLFPowerPointExtractor powerpoint = new XSLFPowerPointExtractor(ppt);
			return powerpoint.getText();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * public static String getPptxByteContent(byte[] buff) { try { XSLFSlideShow ppt = new XSLFSlideShow(new
	 * ByteArrayInputStream(buff)); XSLFPowerPointExtractor powerpoint = new XSLFPowerPointExtractor(ppt);
	 * indexFileContent(powerpoint.getText(), storeFilePath); } catch (Exception e) { e.printStackTrace(); } }
	 */
	public static String getVsdFileContent(String filePath) {
		try {
			VisioTextExtractor vsdExtractor = new VisioTextExtractor(new FileInputStream(filePath));
			return vsdExtractor.getText();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getVsdByteContent(byte[] buff) {
		try {
			VisioTextExtractor vsdExtractor = new VisioTextExtractor(new ByteArrayInputStream(buff));
			return vsdExtractor.getText();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getPdfFileContent(String filePath) {
		/*try {
			FileInputStream fis = new FileInputStream(filePath);
			PDFParser p = new PDFParser(fis);
			p.parse();
			PDFTextStripper ts = new PDFTextStripper();
			String bodyText = ts.getText(p.getPDDocument());
			fis.close();
			return bodyText;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}*/
	    return null;
	}

	public static String getPdfByteContent(byte[] buff) {
		/*try {
			InputStream fis = new ByteArrayInputStream(buff);
			PDFParser p = new PDFParser(fis);
			p.parse();
			PDFTextStripper ts = new PDFTextStripper();
			String bodyText = ts.getText(p.getPDDocument());
			fis.close();
			return bodyText;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}*/
	    return null;
	}

	public static String getRtfFileContent(String filePath) {
		try {
			InputStream is = new FileInputStream(filePath);
			DefaultStyledDocument styledDoc = new DefaultStyledDocument();
			new RTFEditorKit().read(is, styledDoc, 0);
			is.close();
			String bodyText = styledDoc.getText(0, styledDoc.getLength());
			return bodyText;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getRtfByteContent(byte[] buff) {
		try {
			InputStream is = new ByteArrayInputStream(buff);
			DefaultStyledDocument styledDoc = new DefaultStyledDocument();
			new RTFEditorKit().read(is, styledDoc, 0);
			is.close();
			String bodyText = styledDoc.getText(0, styledDoc.getLength());
			return bodyText;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * public static String getHtmByteContent(byte[] buff) { StringBuffer content = new StringBuffer(""); try { Parser
	 * parser = null; parser = new Parser(filePath); HtmlPage visitor = new HtmlPage(parser);
	 * parser.visitAllNodesWith(visitor); NodeList nodes = visitor.getBody(); int size = nodes.size(); for(int
	 * i=0;i<size;i++){ Node node = nodes.elementAt(i); content.append( node.toPlainTextString() ); }
	 * indexFileContent(content.toString(), storeFilePath ,"html");
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } }
	 */

	public static String getHtmFileContent(String filePath) {
		/*StringBuffer content = new StringBuffer("");
		try {
			Parser parser = null;
			parser = new Parser(filePath);
			HtmlPage visitor = new HtmlPage(parser);
			parser.visitAllNodesWith(visitor);
			NodeList nodes = visitor.getBody();
			int size = nodes.size();
			for (int i = 0; i < size; i++) {
				Node node = nodes.elementAt(i);
				content.append(node.toPlainTextString());
			}
			return content.toString();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}*/
	    return null;
	}

	public static String getWebUrl(String urlString) {
		/*StringBuffer content = new StringBuffer("");
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpGet httpget = new HttpGet(urlString);

			HttpResponse response = httpclient.execute(httpget);

			String inputHTML = EntityUtils.toString(response.getEntity());
			// System.out.println(inputHTML);
			Parser parser = new Parser();
			parser.setInputHTML(inputHTML);

			HtmlPage visitor = new HtmlPage(parser);
			parser.visitAllNodesWith(visitor);
			NodeList nodes = visitor.getBody();
			int size = nodes.size();
			for (int i = 0; i < size; i++) {
				Node node = nodes.elementAt(i);
				content.append(node.toPlainTextString());
			}
			return content.toString();*/

			/*
			 * URL url = new URL(urlString); URLConnection urlConn = url.openConnection(); if(urlConn != null){ Parser
			 * parser = new Parser(urlConn); HtmlPage visitor = new HtmlPage(parser); parser.visitAllNodesWith(visitor);
			 * NodeList nodes = visitor.getBody(); int size = nodes.size(); for(int i=0;i<size;i++){ Node node =
			 * nodes.elementAt(i); content.append( node.toPlainTextString() ); } indexContent(content.toString(),
			 * DocDesc.ResType.URL,urlString); }
			 */
			/*
			 * StringBuffer content = new StringBuffer(""); String str = null; reader = new BufferedReader(new
			 * InputStreamReader(inputStream)); while (null != (str = reader.readLine())) { content.append(str); }
			 * reader.close();
			 * 
			 * String sContent = content.toString();
			 * indexContent(sContent,DocDesc.makeSummary(sContent),DocDesc.ResType.URL,urlString);
			 */
		/*} catch (Exception e) {
			e.printStackTrace();
			return null;
		}*/
	    return null;
	}
}
