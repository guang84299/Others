package com.guang.test;

import net.sf.json.JSONObject;

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
	}
	
	
}
