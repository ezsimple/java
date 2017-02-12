package net.ion.system;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by bao on 16-6-26.
 */
@Component
public class NettyServerListener implements ServletContextListener {

	public static final String host = "127.0.0.1";

	public static final int port = 9000;

	private static EventLoopGroup bossGroup;
	private static EventLoopGroup workerGroup;
	private static ServerBootstrap serverBootstrap;

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				bossGroup = new NioEventLoopGroup();
				workerGroup = new NioEventLoopGroup();
				serverBootstrap = new ServerBootstrap();

				serverBootstrap.group(bossGroup, workerGroup);
				serverBootstrap.channel(NioServerSocketChannel.class);
				serverBootstrap.option(ChannelOption.SO_BACKLOG, 128);
				serverBootstrap.option(ChannelOption.TCP_NODELAY, true);
				serverBootstrap.option(ChannelOption.RCVBUF_ALLOCATOR, new AdaptiveRecvByteBufAllocator());
				serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);

				serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {

					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						ch.pipeline().addLast(new ChannelInboundHandler() {

							@Override
							public void channelRegistered(ChannelHandlerContext channelHandlerContext)
									throws Exception {
									System.out.println("#channelRegistered#");
							}

							@Override
							public void channelUnregistered(ChannelHandlerContext channelHandlerContext)
									throws Exception {
									System.out.println("#channelUnregistered#");
							}

							@Override
							public void channelActive(ChannelHandlerContext channelHandlerContext) throws Exception {
									System.out.println("#channelActive#");
							}

							@Override
							public void channelInactive(ChannelHandlerContext channelHandlerContext) throws Exception {
									System.out.println("#channelInactive#");

							}

							@Override
							public void channelRead(ChannelHandlerContext channelHandlerContext, Object o)
									throws Exception {
								System.out.println("#channelRead#");
								channelHandlerContext.writeAndFlush(o);
							}

							@Override
							public void channelReadComplete(ChannelHandlerContext channelHandlerContext)
									throws Exception {
									System.out.println("#channelReadComplete#");

							}

							@Override
							public void userEventTriggered(ChannelHandlerContext channelHandlerContext, Object o)
									throws Exception {

							}

							@Override
							public void channelWritabilityChanged(ChannelHandlerContext channelHandlerContext)
									throws Exception {

							}

							@Override
							public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {

							}

							@Override
							public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {

							}

							@Override
							public void exceptionCaught(ChannelHandlerContext channelHandlerContext,
									Throwable throwable) throws Exception {

							}

						});
					}
				});

				ChannelFuture future = serverBootstrap.bind(host, port).syncUninterruptibly();
				try {
					future.sync();
					future.channel().closeFuture().sync();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					bossGroup.shutdownGracefully();
					workerGroup.shutdownGracefully();
				}

			}
		}).start();
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {

	}
}