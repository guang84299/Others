package com.guang.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URL;
import java.nio.charset.Charset;

import org.apache.log4j.PropertyConfigurator;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.guang.server.filter.GFilter;
import com.guang.server.handler.GCoreHandler;

public class GuangServer {
		
	private static final Logger logger = LoggerFactory.getLogger(GuangServer.class); 
	private final int PORT = 9123;  
	private final String FILTER_LOGGER = "logger";
	private final String FILTER_CODEC = "codec";
	
	private static NioSocketAcceptor acceptor;
	
	public GuangServer()
	{
		//初始化日志系统
		URL url = GuangServer.class.getClassLoader().getResource("log4j.properties");		
		PropertyConfigurator.configure( url.getPath() );
		
		if(acceptor == null)
			acceptor = new NioSocketAcceptor();               
	}
	
	public static IoAcceptor getInstance()
	{
		if(acceptor == null)
		{
			logger.error("服务端还未启动...");
		}
		return acceptor;
	}
	
	public void start()
	{
		acceptor.getFilterChain().addLast( FILTER_LOGGER, new LoggingFilter() );  
        acceptor.getFilterChain().addLast( FILTER_CODEC, new ProtocolCodecFilter( new TextLineCodecFactory( Charset.forName( "UTF-8" ))));  
       
        acceptor.setHandler(  new GCoreHandler() );  
        acceptor.getSessionConfig().setReadBufferSize( 2048 );  
        acceptor.getSessionConfig().setIdleTime( IdleStatus.BOTH_IDLE, 80 );
        acceptor.setReuseAddress(true);//设置的是主服务监听的端口可以重用        
        acceptor.getSessionConfig().setReuseAddress(true);//设置每一个非主监听连接的端口可以重用  
		try {
			acceptor.bind(new InetSocketAddress(PORT));
			logger.info("服务端启动成功...     端口号为：" + PORT);
		} catch (IOException e) {
			logger.error("服务端启动异常....", e);
			e.printStackTrace();
		}
	}
	
	public void stop()
	{
		acceptor.unbind();
	}
	
	public void addFilter(GFilter filter)
	{
		acceptor.getFilterChain().addLast("",filter);
	}
}
