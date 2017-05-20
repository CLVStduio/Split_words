package cn.clvstudio.webcrawling;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 获取评论并分析
 * @author Darnell
 *
 */
public class GetComment{

	
	/**
	 * post方式获取通用链接的响应信息
	 * @param requestUrl
	 * @param apkName
	 * @param apkCode
	 * @param p
	 * @param fresh
	 * @param contextData
	 * @return
	 * @throws IOException 
	 * @throws HttpException 
	 */
	private static String postWebRequest(String requestUrl,Parameter... parameters) throws HttpException, IOException{
		String content = null;
	    /** post方式 */
	    HttpClient client = new HttpClient();
	    PostMethod postMethod = new PostMethod(requestUrl);
	    // 参数设置
	    for(Parameter parameter : parameters){
	    	postMethod.setParameter(parameter.getKey(),parameter.getValue());
	    }
	    // 设置HTTP协议内容的字符集
	    client.getParams().setParameter( HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
	    // 执行POST请求方法并返回状态码
	    int status = client.executeMethod(postMethod);
	    if(status == 200){
	    	content = postMethod.getResponseBodyAsString();
//		    	System.out.println(content);
	    }else{
	    	content = Integer.toString(status);
	    }
	    return content;
		
	}

	
	/**
	 * post方式获取应用宝的响应信息
	 * @param requestUrl
	 * @param apkName
	 * @param apkCode
	 * @param p
	 * @param fresh
	 * @param contextData
	 * @return
	 * @throws IOException 
	 * @throws HttpException 
	 */
	private static String postResponse(String requestUrl,String apkName,String apkCode,int p,String fresh,String contextData) throws HttpException, IOException{
		String content = null;
	    /** post方式 */
	    HttpClient client = new HttpClient();
	    PostMethod postMethod = new PostMethod(requestUrl);
	    // 参数设置
	    postMethod.setParameter("apkName",apkName);
	    postMethod.setParameter("apkCode",apkCode);
	    postMethod.setParameter("p",Integer.toString(p));
	    postMethod.setParameter("fresh",fresh);
	    postMethod.setParameter("contextData",contextData);
	    // 设置HTTP协议内容的字符集
	    client.getParams().setParameter( HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
	    // 执行POST请求方法并返回状态码
	    int status = client.executeMethod(postMethod);
	    if(status == 200){
	    	content = postMethod.getResponseBodyAsString();
//		    	System.out.println(content);
	    }else{
	    	content = Integer.toString(status);
	    }
	    return content;
		
	}

	public static Map<String,Object> getComment(String response) throws JSONException{
		Map<String,Object> map = new HashMap<String,Object>();
		List<CommentMyapp> commentList = new ArrayList<CommentMyapp>();
		JSONObject jsonObj = new JSONObject(response);
		boolean success = jsonObj.getBoolean("success");
		if(success){
			JSONObject obj = jsonObj.getJSONObject("obj");
			int total = obj.getInt("total");
			String contextData = obj.getString("contextData");
			int hasNext = obj.getInt("hasNext");
			JSONArray commentDetails = obj.getJSONArray("commentDetails");
			for(int i=0;i<commentDetails.length();i++){
				JSONObject commentObj = commentDetails.getJSONObject(i);
				CommentMyapp comment = new CommentMyapp();
				comment.setContent(commentObj.getString("content"));
				comment.setPkgName(commentObj.getString("pkgName"));
				comment.setVersionCode(commentObj.getString("versionCode"));
				comment.setCreatedTime(commentObj.getString("createdTime"));
				comment.setScore(commentObj.getString("score"));
				comment.setUin(commentObj.getString("uin"));
				comment.setNickName(commentObj.getString("nickName"));
				comment.setPhoneMode(commentObj.getString("phoneMode"));
				comment.setPhoneBrand(commentObj.getString("phoneBrand"));
				commentList.add(comment);
			}
			map.put("total", total);
			map.put("contextData", contextData);
			map.put("hasNext", hasNext);
			map.put("commentList", commentList);
			map.put("success", success);
		}else{
			map.put("success", success);
		}
		return map;
	}
   
    
    
     /** 
     *  对以上两个方法进行封装。
      * @return 
     * @throws IOException 
     * @throws HttpException 
     * @throws JSONException 
      */  
     public static Map<String, Object> getCommentMap(String apkName,String apkCode,int p,String fresh,String contextData) throws HttpException, IOException, JSONException {  
    	 String rootUrl = "http://android.myapp.com/myapp/app/comment.htm";
//         String response = postResponse(rootUrl,apkName,apkCode,p,fresh,contextData);  
    	 String response1 = postWebRequest(rootUrl, new Parameter("apkName",apkName),new Parameter("apkCode",apkCode),new Parameter("p",Integer.toString(p)),new Parameter("fresh",fresh),new Parameter("contextData",contextData));
         Map<String, Object> commentMap= getComment(response1);  
         
         return commentMap;  
     }  
   
}