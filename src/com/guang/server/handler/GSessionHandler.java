package com.guang.server.handler;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.mina.core.service.IoService;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.guang.server.GuangServer;
import com.guang.server.controller.GUserController;
import com.guang.server.protocol.GModeUser;
import com.guang.server.protocol.GProtocol;
import com.guang.server.session.GSession;

public class GSessionHandler {

	private final Logger logger = LoggerFactory
			.getLogger(GSessionHandler.class);

	private static GSessionHandler handler;
	private static HashMap<Long, GSession> sessions;

	private IoService service;

	private GSessionHandler() {
		service = GuangServer.getInstance();
		sessions = new HashMap<Long, GSession>();
	}

	public static GSessionHandler getInstance() {
		if (handler == null) {
			handler = new GSessionHandler();
		}
		return handler;
	}

	public static HashMap<Long, GSession> getSessions() {
		return sessions;
	}

	public void create(IoSession session) {
		logger.info(session.getId() + " create...");
		sessions.put(session.getId(), new GSession(session));
	}

	public void close(IoSession session) {
		logger.info(session.getId() + "  close...");
		GSession gs = sessions.get(session.getId());
		GModeUser.getInstance().loginOut(gs.getName());
		sessions.remove(session.getId());
		logger.info("map size=" + sessions.size());
	}

	public void idle(IoSession session) {
		session.close(true);
	}

	public void received(IoSession session, Object message) {
		GProtocol.parse(session, message);
	}

	public void send(IoSession session, Object message) {

	}
	//根据name关闭session
	public void closeSession(String name)
	{
		Iterator<Entry<Long, GSession>> iter = sessions.entrySet().iterator();
		GSession session = null;
		while (iter.hasNext()) {
			Entry<Long,GSession> entry = (Entry<Long,GSession>) iter.next();
			GSession val = entry.getValue();
			if(name.equals(val.getName()))
			{
				session = val;
				break;
			}
		}
		if(session != null)
		{
			logger.info(session.getSession().getId() + "  close...");
			GModeUser.getInstance().loginOut(name);
			sessions.remove(session.getSession().getId());
			logger.info("map size=" + sessions.size());
		}
		
	}

	// 判断是否在线
	public boolean judeOnline(String name) {
		Iterator<Entry<Long, GSession>> iter = sessions.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<Long,GSession> entry = (Entry<Long,GSession>) iter.next();
			GSession val = entry.getValue();
			if(name.equals(val.getName()))
			{
				return true;
			}
		}
		return false;
	}
	
	// 根据name 获得在线session
	public GSession getSessionByName(String name)
	{
		Iterator<Entry<Long, GSession>> iter = sessions.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<Long,GSession> entry = (Entry<Long,GSession>) iter.next();
			GSession val = entry.getValue();
			if(name.equals(val.getName()))
			{
				return val;
			}
		}
		return null;
	}
}
