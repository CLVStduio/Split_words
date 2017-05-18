package functioncodetest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class webcrawling {
	/**
	 * Get方式获取页面
	 * @param requestUrl
	 * @return
	 */
	private static String getWeb(String requestUrl) {  
         
         StringBuffer buffer = null;  
         BufferedReader bufferedReader = null;
         InputStreamReader inputStreamReader = null;
         InputStream inputStream = null;
         HttpURLConnection httpUrlConn = null;
   
         try {  
             // 建立get请求
             URL url = new URL(requestUrl);  
             httpUrlConn = (HttpURLConnection) url.openConnection();  
             httpUrlConn.setDoInput(true);  
             httpUrlConn.setRequestMethod("GET");  
   
//             int responseCode = httpUrlConn.getResponseCode();
             // 获取输入流  
             inputStream = httpUrlConn.getInputStream();  
             inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
             bufferedReader = new BufferedReader(inputStreamReader);  
   
             // 从输入流读取结果
             buffer = new StringBuffer();  
             String str = null;  
             while ((str = bufferedReader.readLine()) != null) {  
                 buffer.append(str);  
             }  
         } catch (Exception e) {  
             e.printStackTrace();  
         }  finally {
             // 释放资源
             if(bufferedReader != null) {
                 try {
                     bufferedReader.close();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             }
             if(inputStreamReader != null){
                 try {
                     inputStreamReader.close();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             }
             if(inputStream != null){
                 try {
                     inputStream.close();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             }
             if(httpUrlConn != null){
                 httpUrlConn.disconnect();  
             }
         }
        return buffer.toString();  
     }  
}
