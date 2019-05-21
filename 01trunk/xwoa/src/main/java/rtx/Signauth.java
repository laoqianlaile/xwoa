package rtx;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.centit.sys.service.CodeRepositoryUtil;
   
public class Signauth 
 {
    public static boolean auth(String strUser, String strSign) throws UnsupportedEncodingException 
    {
        	boolean bRet = false;
        	
        String rtxSer=CodeRepositoryUtil.getDataPiece("SYSPARAM", "RTXSER")!=null?CodeRepositoryUtil.getDataPiece("SYSPARAM", "RTXSER").getDatavalue():RTXSvrApi.RTXSER;
        strSign = java.net.URLEncoder.encode(strSign,"UTF-8");
       	String strURL = "http://"
       	        + rtxSer
       	        + ":8012/SignAuth.cgi?user=" + strUser + "&sign=" + strSign;
//      	String szEncodeResult;
//      	String szResult;

        try
        {   
        	java.net.URL url = new URL(strURL);
        	HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection();
        
        	BufferedReader reader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));   
        	String strResult=reader.readLine();  
        
        	if(strResult.compareToIgnoreCase("success!") == 0)
        	{
        		bRet = true;
        	}
        
        }
        catch(Exception e)
        {
            System.out.println("系统出错"+e);
        }
        
		return bRet;
    }

}


