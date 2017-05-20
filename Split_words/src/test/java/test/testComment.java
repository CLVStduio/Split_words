package test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpException;
import org.json.JSONException;
import org.junit.Test;

import cn.clvstudio.webcrawling.CommentMyapp;
import cn.clvstudio.webcrawling.GetComment;
import junit.framework.TestCase;

public class testComment extends TestCase {
	/**
	 * 单次爬取应用宝评论数据
	 * @throws JSONException 
	 * @throws IOException 
	 * @throws HttpException 
	 */
	@Test
	public void test1() throws HttpException, IOException, JSONException{
		String apkName = "com.tencent.mobileqq";
		String apkCode = "676";
		String fresh = "0.8325424489262343";
		Map<String, Object> commentMap = GetComment.getCommentMap(apkName,apkCode,1,fresh,"");
		boolean success = (Boolean) commentMap.get("success");
		if(success){
			String contextData = (String) commentMap.get("contextData");
			int hasNext = (Integer) commentMap.get("hasNext");
			List<CommentMyapp> commentList = (List<CommentMyapp>) commentMap.get("commentList");
			System.out.println("contextData : "+contextData+" ; hasNext : " + hasNext);
			for(int i=0;i<commentList.size();i++){
				CommentMyapp comment = commentList.get(i);
				System.out.println(comment);
			}
		}else{
			System.out.println(false );
		}
	}
	/**
	 * 多次自动爬取应用宝评论数据测试
	 * @throws JSONException 
	 * @throws IOException 
	 * @throws HttpException 
	 */
	@Test
	public void test2() throws HttpException, IOException, JSONException{
		String apkName = "com.tencent.mobileqq";
		String apkCode = "676";
		String fresh = "0.8325424489262343";
		int num = 3;
		int p = 1;
		String contextData = "";
		for(int i = 0 ;i<num;i++){
			Map<String, Object> commentMap = GetComment.getCommentMap(apkName,apkCode,p,fresh,contextData);
			boolean success = (Boolean) commentMap.get("success");
			if(success){
				contextData = (String) commentMap.get("contextData");
				int hasNext = (Integer) commentMap.get("hasNext");
				List<CommentMyapp> commentList = (List<CommentMyapp>) commentMap.get("commentList");
				System.out.println("第"+i+"次 ；contextData : "+contextData+" ; hasNext : " + hasNext);
				for(int j=0;j<commentList.size();j++){
					CommentMyapp comment = commentList.get(j);
					System.out.println(comment);
				}
				
				p++;
				
			}else{
				System.out.println(false +" : "+ i);
				break;
			}
		}
	}
}
