package com.guang.test;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.guang.web.mode.GAd;
import com.guang.web.mode.GUser;
import com.guang.web.tools.ApkTools;

public class GTest {

	public static void main(String[] args) {
		
		try {
			System.out.println(ApkTools.unZip("/Users/guang/Downloads/360Video_ys5600.apk", "")[1]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Long> listad = new ArrayList<Long>();
		
		for (long i=0;i<5;i++) {
				listad.add(i);				
		}
		
		System.out.println(JSONArray.fromObject(listad).toString());
	}
	
	
}
