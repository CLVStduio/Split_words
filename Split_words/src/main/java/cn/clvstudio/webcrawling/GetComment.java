package cn.clvstudio.webcrawling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
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
	 * post方式获取应用宝的响应信息
	 * @param requestUrl
	 * @param apkName
	 * @param apkCode
	 * @param p
	 * @param fresh
	 * @param contextData
	 * @return
	 */
	private static String postResponse(String requestUrl,String apkName,String apkCode,int p,String fresh,String contextData){
		String content = null;
		try {
		    /** post方式 */
		    HttpClient client = new HttpClient();
		    PostMethod postMethod = new PostMethod(requestUrl);
		    // 参数设置
		    postMethod.setParameter("apkName",apkName);
		    postMethod.setParameter("apkCode",apkCode);
		    postMethod.setParameter("p",Integer.toString(p));
		    postMethod.setParameter("fresh",fresh);
		    postMethod.setParameter("contextData",contextData);
		    // 执行postMethod
		    client.getParams().setParameter( HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
		    // 执行并返回状态
		    int status = client.executeMethod(postMethod);
		    if(status == 200){
		    	content = postMethod.getResponseBodyAsString();
//		    	System.out.println(content);
		    }else{
		    	content = Integer.toString(status);
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return content;
	}
	public static Map<String,Object> getComment(String response){
		Map<String,Object> map = new HashMap<String,Object>();
		List<CommentMyapp> commentList = new ArrayList<CommentMyapp>();
		try {
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
		} catch (JSONException e) {
			e.printStackTrace();
			map.put("success", false);
		}
		return map;
	}
   
    
    
     /** 
     *  对以上两个方法进行封装。
      * @return 
      */  
     public static Map<String, Object> getCommentMap(String apkName,String apkCode,int p,String fresh,String contextData) {  
    	 
         String response = postResponse("http://android.myapp.com/myapp/app/comment.htm",apkName,apkCode,p,fresh,contextData);  
         Map<String, Object> commentMap= getComment(response);  
         
         return commentMap;  
     }  
   
}