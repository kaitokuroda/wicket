package com.example.wsbp.service;


import com.example.wsbp.data.Chat;

import java.util.List;

public interface IChatService {

    public void registerChat(String userName, String chat);

    /**
     * ユーザ名とチャットの一覧を、Chat型のリストで検索する
     *
     * @return Chat型のListインスタンス
     */
    public List<Chat> findChatUsers();
}
