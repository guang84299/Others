package com.guang.server.net;

import org.apache.mina.core.session.IoSession;

public class GConnection {

	private IoSession session;
	
	
	public GConnection(IoSession session)
	{
		this.session = session;
	}
}
