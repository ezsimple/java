package h2;

import java.nio.charset.Charset;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class EchoServerHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		// ctx.fireChannelRead(msg);
		String readMessage = ((ByteBuf) msg).toString(Charset.defaultCharset());
		System.out.println("������ �޼��� : "+readMessage);
		ctx.write(msg);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		// ctx.fireChannelReadComplete();
	}

}
