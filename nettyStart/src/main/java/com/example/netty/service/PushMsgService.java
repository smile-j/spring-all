package com.example.netty.service;

/**
 * @author dongjunpeng
 * @Description
 * @date 2022/3/4
 */
public interface PushMsgService {

    /**
     * 推送给指定用户
     */
    void pushMsgToOne(String userId, String msg);

    /**
     * 推送给所有用户
     */
    void pushMsgToAll(String msg);

}
