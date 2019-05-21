package com.centit.powerbase.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import com.centit.powerbase.po.Pcfreeumpiredegree;
import com.centit.powerbase.po.Pcfreeumpiretype;
import com.centit.powerbase.po.PcpunishStandard;
import com.centit.support.utils.StringBaseOpt;
import com.centit.sys.po.FDatadictionary;
import com.centit.sys.service.CodeRepositoryUtil;

/**
 * 
 * TODO Class description should be added
 * 
 * @author cjw
 * @create 2013-8-7
 * @version
 */
public class ZycXmlElement {

    /**
     * 
     * @param listXml
     * @param listSeqList
     * @return
     * @throws IOException
     */
	public String BuildXMLDoc(List listXml,List listSeqList) throws IOException {
		//SupPowerManager manager = new SupPowerManager();
		//List listXml = manager.getsuppowerDissxml(info.getItem_id());
	    Pcfreeumpiredegree  infoxml =null;
	    Pcfreeumpiretype  infoxmlvalue=null ;
		String xml="";
		String info = "";
		if(listXml.size()>0){
		    
		    info = "自由裁量：";
		    DocumentFactory df = DocumentFactory.getInstance();
            
            Document Doc = df.createDocument("UTF-8");
            // 创建根节点
            Element root = Doc.addElement("STANDARD");
		    for(int x = 0;x < listXml.size();x++){
		      infoxml=(Pcfreeumpiredegree)listXml.get(x);
		      Element elements = root.addElement("CONTENT");   
		      Element elementsStandId = elements.addElement("STAND_ID");
		      elementsStandId.setText(""+infoxml.getStandardNo()+"");
		      Element elementsStandCode = elements.addElement("STAND_CODE");
		      elementsStandCode.setText(""+infoxml.getPunishfactgrade()+"");
		      info += "裁量名称："+infoxml.getPunishfactgrade()+"\n";
		  
		  	  //List listXmlValue = manager.generationXml(infoxml.getItem_id());
		      for (int j = 0; j < listSeqList.size(); j++){
		    	  infoxmlvalue = (Pcfreeumpiretype)listSeqList.get(j);
		    	  if(infoxmlvalue.getSupPart().equals(infoxml.getThisPart())){
		    		  Element elementsStandSeq = elements.addElement("STAND_SEQ");
		    		  FDatadictionary fDate = CodeRepositoryUtil.getDataPiece("punishType", infoxmlvalue.getPunishtypeid());
//		    		  if("1".equals(infoxmlvalue.getPunishtypeid())){
//	                      info += "\n警告";
//	                  } else if("2".equals(infoxmlvalue.getPunishtypeid())){
//	                      info += "\n通报批评";
//	                  }else if("3".equals(infoxmlvalue.getPunishtypeid())){
//	                      info += "\n罚款";
//	                     
//	                  }else if("4".equals(infoxmlvalue.getPunishtypeid())){
//	                      info += "\n没收财物";
//	                  }else if("5".equals(infoxmlvalue.getPunishtypeid())){
//	                      info += "\n暂扣或者吊销许可证和营业执照";
//	                  }else if("6".equals(infoxmlvalue.getPunishtypeid())){
//	                      info += "\n责令停产、停业";
//	                  }else if("7".equals(infoxmlvalue.getPunishtypeid())){
//	                      info += "\n行政拘留";
//	                  }else if("8".equals(infoxmlvalue.getPunishtypeid())){
//	                      info += "\n劳动教养";
//	                  }else if("9".equals(infoxmlvalue.getPunishtypeid())){
//	                      info += "\n其他";
//	                  }
		    		  info += "\n"+ fDate.getDatavalue();
	                  if("1".equals(String.valueOf(infoxmlvalue.getPunishtype()))){
	                      info += ":上下限";
	                  }else if("2".equals(String.valueOf(infoxmlvalue.getPunishtype()))){
	                      info += ":基数";
	                  }
	                
	                  if(!StringBaseOpt.isNvl(infoxmlvalue.getToplimit().trim()) && !"null".equals(infoxmlvalue.getToplimit())){
	                      info += ":"+infoxmlvalue.getLowlimit() + infoxmlvalue.getLowlimitUnit() + "到"+infoxmlvalue.getToplimit()+infoxmlvalue.getToplimitUnit();
	                  }
	                  if(!StringBaseOpt.isNvl(infoxmlvalue.getBaseName().trim()) && !"null".equals(infoxmlvalue.getBaseName())){
	                      info += ":"+infoxmlvalue.getBaseName()+":"+infoxmlvalue.getBaseLowlimit() + infoxmlvalue.getBaseUnit() + "到"+infoxmlvalue.getBaseToplimit()+infoxmlvalue.getBaseUnit();
	                  }
		    		  Element seqId = elementsStandSeq.addElement("SEQ_ID");
		              seqId.setText(""+infoxmlvalue.getPunishtypeid()+"");
		              Element seqType = elementsStandSeq.addElement("SEQ_TYPE"); 
		              seqType.setText(""+infoxmlvalue.getPunishtype()+"");
		              Element seqToplimit = elementsStandSeq.addElement("SEQ_TOPLIMIT"); 
		              seqToplimit.setText(""+infoxmlvalue.getToplimit()+"");
		              Element seqToplimitUnit = elementsStandSeq.addElement("SEQ_TOPLIMIT_UNIT"); 
		              seqToplimitUnit.setText(""+infoxmlvalue.getToplimitUnit()+"");
		              Element seqLowlimit = elementsStandSeq.addElement("SEQ_LOWLIMIT"); 
		              seqLowlimit.setText(""+infoxmlvalue.getLowlimit()+"");
		              Element seqLowlimitUnit = elementsStandSeq.addElement("SEQ_LOWLIMIT_UNIT"); 
		              seqLowlimitUnit.setText(""+infoxmlvalue.getLowlimitUnit()+"");
		              Element seqBaseName = elementsStandSeq.addElement("SEQ_BASE_NAME"); 
		              seqBaseName.setText(""+infoxmlvalue.getBaseName()+"");
		              Element seqBaseTopmul = elementsStandSeq.addElement("SEQ_BASE_TOPMUL"); 
		              seqBaseTopmul.setText(""+infoxmlvalue.getBaseToplimit()+"");
		              Element seqBaseLowmul = elementsStandSeq.addElement("SEQ_BASE_LOWMUL"); 
		              seqBaseLowmul.setText(""+infoxmlvalue.getBaseLowlimit()+"");
		              Element seqBaseUnit = elementsStandSeq.addElement("SEQ_BASE_UNIT"); 
		              seqBaseUnit.setText(""+infoxmlvalue.getBaseUnit()+"");
		             
		    	  }
		      }   
		     
		     
		  }   
		    OutputFormat opf = OutputFormat.createPrettyPrint();
            opf.setEncoding("UTF-8");
            opf.setTrimText(true);
            
            // 生成XML文件
            XMLWriter xmlOut = null;
            try {
                xmlOut = new XMLWriter(new FileOutputStream
                        ("city-dom4j.xml"), opf);
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            xmlOut.write(Doc);
            xmlOut.flush();
            xmlOut.close();
            
            // 获取XML字符串形式
            StringWriter writerStr = new StringWriter();
            XMLWriter xmlw = new XMLWriter(writerStr, opf);
            xmlw.write(Doc);
            xml = writerStr.getBuffer().toString();
		    xml += "wzsm??"+info;
		    System.out.println(xml);
		}
		
	    return xml;
	} 
	
	/**
	 * 
	 * @param listXmlValue
	 * @return
	 * @throws IOException
	 */
	public String buildStandXMLDoc(List listXmlValue) throws IOException {
		String xml="";
		String info = "";
		//SupPowerManager manager = new SupPowerManager();
		PcpunishStandard supinfo =null;

		if(listXmlValue.size()>0){
		    info = "处罚标准：";
			// 创建根节点 STANDARD;   
		    DocumentFactory df = DocumentFactory.getInstance();
            
            Document Doc = df.createDocument("UTF-8");
            // 创建根节点
            Element root = Doc.addElement("STANDARD");
            // 创建根节点 STANDARD;    

		    
			for (int j = 0; j < listXmlValue.size(); j++){
				supinfo = (PcpunishStandard)listXmlValue.get(j);
				FDatadictionary fDate = CodeRepositoryUtil.getDataPiece("punishType", supinfo.getPunishtypeid());
//				  if("1".equals(supinfo.getPunishtypeid())){
//                      info += "\n警告";
//                  } else if("2".equals(supinfo.getPunishtypeid())){
//                      info += "\n通报批评";
//                  }else if("3".equals(supinfo.getPunishtypeid())){
//                      info += "\n罚款";
//                     
//                  }else if("4".equals(supinfo.getPunishtypeid())){
//                      info += "\n没收财物";
//                  }else if("5".equals(supinfo.getPunishtypeid())){
//                      info += "\n暂扣或者吊销许可证和营业执照";
//                  }else if("6".equals(supinfo.getPunishtypeid())){
//                      info += "\n责令停产、停业";
//                  }else if("7".equals(supinfo.getPunishtypeid())){
//                      info += "\n行政拘留";
//                  }else if("8".equals(supinfo.getPunishtypeid())){
//                      info += "\n劳动教养";
//                  }else if("9".equals(supinfo.getPunishtypeid())){
//                      info += "\n其他";
//                  }
				 info += "\n"+ fDate.getDatavalue();
				  if("1".equals(String.valueOf(supinfo.getPunishtype()))){
                      info += ":上下限";
                  }else if("2".equals(String.valueOf(supinfo.getPunishtype()))){
                      info += ":基数";
                  }
				
                  if(!StringBaseOpt.isNvl(supinfo.getToplimit().trim()) && !"null".equals(supinfo.getToplimit())){
                      info += ":"+supinfo.getLowlimit() + supinfo.getLowlimitUnit() + "到"+supinfo.getToplimit()+supinfo.getToplimitUnit();
                  }
                  if(!StringBaseOpt.isNvl(supinfo.getBaseName().trim()) && !"null".equals(supinfo.getBaseName())){
                      info += ":"+supinfo.getBaseName()+":"+supinfo.getBaseLowlimit() + supinfo.getBaseUnit() + "到"+supinfo.getBaseToplimit()+supinfo.getBaseUnit();
                  }
				Element elementsStandSeq = root.addElement("STAND_SEQ");
		        Element seqId = elementsStandSeq.addElement("SEQ_ID");
		        seqId.setText(""+supinfo.getPunishtypeid()+"");
		        Element seqType = elementsStandSeq.addElement("SEQ_TYPE"); 
		        seqType.setText(""+supinfo.getPunishtype()+"");
		        Element seqToplimit = elementsStandSeq.addElement("SEQ_TOPLIMIT"); 
		        seqToplimit.setText(""+supinfo.getToplimit()+"");
		        Element seqToplimitUnit = elementsStandSeq.addElement("SEQ_TOPLIMIT_UNIT"); 
		        seqToplimitUnit.setText(""+supinfo.getToplimitUnit()+"");
		        Element seqLowlimit = elementsStandSeq.addElement("SEQ_LOWLIMIT"); 
		        seqLowlimit.setText(""+supinfo.getLowlimit()+"");
	            Element seqLowlimitUnit = elementsStandSeq.addElement("SEQ_LOWLIMIT_UNIT"); 
	            seqLowlimitUnit.setText(""+supinfo.getLowlimitUnit()+"");
	            Element seqBaseName = elementsStandSeq.addElement("SEQ_BASE_NAME"); 
	            seqBaseName.setText(""+supinfo.getBaseName()+"");
	            Element seqBaseTopmul = elementsStandSeq.addElement("SEQ_BASE_TOPMUL"); 
		        seqBaseTopmul.setText(""+supinfo.getBaseToplimit()+"");
		        Element seqBaseLowmul = elementsStandSeq.addElement("SEQ_BASE_LOWMUL"); 
		        seqBaseLowmul.setText(""+supinfo.getBaseLowlimit()+"");
		        Element seqBaseUnit = elementsStandSeq.addElement("SEQ_BASE_UNIT"); 
		        seqBaseUnit.setText(""+supinfo.getBaseUnit()+"");
		        Element seqRemark = elementsStandSeq.addElement("seq_remark"); 
		        seqRemark.setText(""+supinfo.getRemark()+"");
		     
			
		    }  
			 OutputFormat opf = OutputFormat.createPrettyPrint();
             opf.setEncoding("UTF-8");
             opf.setTrimText(true);
             
             // 生成XML文件
             XMLWriter xmlOut = null;
             try {
                 xmlOut = new XMLWriter(new FileOutputStream
                         ("city-dom4j.xml"), opf);
             } catch (UnsupportedEncodingException e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
             } catch (FileNotFoundException e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
             }
             xmlOut.write(Doc);
             xmlOut.flush();
             xmlOut.close();
             
             // 获取XML字符串形式
             StringWriter writerStr = new StringWriter();
             XMLWriter xmlw = new XMLWriter(writerStr, opf);
             xmlw.write(Doc);
             xml = writerStr.getBuffer().toString();
		    
		    xml += "wzsm??"+info;
		    System.out.println(xml);
		}
		return xml;
	} 
}
