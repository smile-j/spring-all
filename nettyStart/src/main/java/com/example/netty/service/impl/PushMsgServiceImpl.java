package com.example.netty.service.impl;

import com.example.netty.config.NettyConfig;
import com.example.netty.service.PushMsgService;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <p>
 *
 * </p>
 *
 * @author duguotao
 * @version 1.0.0
 * @since Created in 2022/1/24
 */
@Service
public class PushMsgServiceImpl implements PushMsgService {

    @Override
    public void pushMsgToOne(String userId, String msg) {
        Channel channel = NettyConfig.getChannel(userId);
        if (Objects.isNull(channel)) {
            throw new RuntimeException("未连接socket服务器");
        }

        channel.writeAndFlush(new TextWebSocketFrame(msg));
    }

    @Override
    public void pushMsgToAll(String msg) {
        NettyConfig.getChannelGroup().writeAndFlush(new TextWebSocketFrame(msg));
    }
}
