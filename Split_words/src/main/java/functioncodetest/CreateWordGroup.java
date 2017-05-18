package functioncodetest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CreateWordGroup {
	//d+v+vn,n+a,vn+vn,v+n
	/**
	 * @param str
	 * @return
	 */
	public static String createWordGroup(String str){
		String[] strArr = str.split("[,]");
		/*
		 * 单词列表
		 */
		List<String> wordList = new ArrayList<String>();
		/*
		 * 词性列表
		 */
		List<String> partSpeech = new ArrayList<String>();
//		List<String> wordGroup = new ArrayList<String>();
		/*
		 * 词组
		 */
		Set<String> wordGroup = new HashSet<String>();
		for(String s : strArr){
			if(s.length()>1){
				String[] word = s.split("[/]");
				wordList.add(word[0]);
				partSpeech.add(word[1]);
				System.out.print(word[0]+"/"+word[1] + " ");
			}
		}
		for(int i=0;i<partSpeech.size()-1;i++){
			if(specification(partSpeech.get(i),partSpeech.get(i+1))){
				wordGroup.add(wordList.get(i)+wordList.get(i+1));
			}
		}
		System.out.println();
		for(String s : wordGroup){
			System.out.println(s);
		}
		List<String> shortsentences = shortsentences(wordList,partSpeech);
		for(String s : shortsentences){
			System.out.println(s);
		}
		return null;
		
	}
	
	/**
	 * 词组规范
	 * @param argStr1
	 * @param argStr2
	 * @return
	 */
	public static boolean specification(String argStr1,String argStr2){
		String str = argStr1+"+"+argStr2;
		if("v+n".equals(str)){
			return true;
		}
		if("n+v".equals(str)){
			return true;
		}
		if("vn+vn".equals(str)){
			return true;
		}
		if("a+n".equals(str)){
			return true;
		}
		if("d+a".equals(str)){
			return true;
		}
		return false;
	}
	
	
	/**
	 * 短句生成
	 * @param wordList
	 * @param partSpeech
	 * @return
	 */
	public static List<String> shortsentences(List<String> wordList,List<String> partSpeech){
		System.out.println();
		int flag = 0;
		List<String> shortsentencesList = new ArrayList<String>();
		for(int i=0;i < partSpeech.size();i++){
			String p = partSpeech.get(i);
			
			if("c".equals(p)){
				System.out.println("c : i : " + i + " text : " + wordList.get(i));
				partSpeech.remove(i);
				wordList.remove(i);
			}else if("m".equals(p)){
				System.out.println("m : i : " + i + " text : " + wordList.get(i));
				partSpeech.remove(i);
				wordList.remove(i);				
			}else if("r".equals(p)){
				System.out.println("m : i : " + i + " text : " + wordList.get(i));
				partSpeech.remove(i);
				wordList.remove(i);				
			}else if("w".equals(p)){
//				System.out.println("w : i : " + i + " text : " + wordList.get(i));
				StringBuilder str = new StringBuilder() ;
				for(int j = flag!=0?flag+1:0;j<i;j++){
					str.append(wordList.get(j));
				}
				shortsentencesList.add(str.toString());
				flag = i;
			}
		}
		return shortsentencesList;
		
	}
}
