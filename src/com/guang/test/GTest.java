package com.guang.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.guang.web.mode.GAd;
import com.guang.web.mode.GUser;
import com.guang.web.tools.ApkTools;

public class GTest {

	public static void main(String[] args) {
		
		String s = "  825  0   0% S   115 1830792K 175400K  fg system   system_server";
		s = s.replaceFirst("[\\s]+", "");
		System.out.println(s);
		String[] arr = s.split("[\\s]+");
		System.out.println(arr.length);
	}
	
	public static String getColVals(LinkedHashMap<String, String> colvals)
	{
		StringBuffer colvalssq = new StringBuffer("");
		if(colvals!=null && colvals.size()>0){
			for(String key : colvals.keySet()){
				colvalssq.append("o.").append(key+" ").append(colvals.get(key)+" and ");
			}
			colvalssq.delete(colvalssq.length()-4, colvalssq.length()-1);
		}
		return colvalssq.toString();		
	}
}
