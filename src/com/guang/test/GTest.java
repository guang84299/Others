package com.guang.test;

import net.sf.json.JSONObject;

import com.guang.web.mode.GUser;

public class GTest {

	public static void main(String[] args) {
		String data = "{\"city\":\"上海市\",\"deviceId\":\"861843034407990\",\"district\":\"\",\"location\":\"[6150,28773636,-1]\",\"model\":\"vivo Y51A\",\"name\":\"460028109257296\",\"networkCountryIso\":\"cn\",\"networkOperator\":\"46000\",\"networkOperatorName\":\"CMCC\",\"networkType\":\"WIFI\",\"password\":\"861843034407990\",\"phoneNumber\":\"15810947248\",\"province\":\"上海市\",\"release\":\"5.0.2\",\"simSerialNumber\":\"89860011011550081378\",\"street\":\"\",\"id\":0,\"phoneType\":1}";
		GUser user = (GUser) JSONObject.toBean(JSONObject.fromObject(data),GUser.class);
		System.out.println(user.toString());
	}
}
