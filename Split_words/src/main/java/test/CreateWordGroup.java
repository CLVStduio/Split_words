package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CreateWordGroup {
	//d+v+vn,n+a,vn+vn,v+n
	public static String createWordGroup(String str){
		String[] strArr = str.split("[,]");
		List<String> wordList = new ArrayList<String>();
		List<String> partSpeech = new ArrayList<String>();
//		List<String> wordGroup = new ArrayList<String>();
		Set<String> wordGroup = new HashSet<String>();
		for(String s : strArr){
			if(s.length()>1){
				String[] word = s.split("[/]");
				wordList.add(word[0]);
				partSpeech.add(word[1]);
				System.out.println(word[0]+" , "+word[1]);
			}
		}
		for(int i=0;i<partSpeech.size()-1;i++){
			if(specification(partSpeech.get(i),partSpeech.get(i+1))){
				wordGroup.add(wordList.get(i)+wordList.get(i+1));
			}
		}
		for(String s : wordGroup){
			System.out.println(s);
		}
		return null;
		
	}
	
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
}
