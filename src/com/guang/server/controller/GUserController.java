package com.guang.server.controller;

import com.guang.server.handler.GSessionHandler;
import com.guang.server.session.GSession;

public class GUserController {
	private static final int HEART_TIME = 20000;
	//ÐÄÌø¼ì²â
	public static void updateHeartBeat(long id)
	{
		GSession session = GSessionHandler.getSessions().get(id);
		if(session != null)
		{
			long currTime = System.currentTimeMillis();
			if(currTime - session.getHeartBeatTime() > HEART_TIME)
			{
				session.getSession().close(true);
			}			
		}
	}
	
	
	
}
