package test;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import cn.clvstudio.webcrawling.CommentMyapp;
import cn.clvstudio.webcrawling.GetComment;

public class TestCase {
	@Test
	public void test1(){
		Map<String, Object> commentMap = GetComment.getCommentMap("com.tencent.mobileqq","676","1","0.8325424489262343","");
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
}
