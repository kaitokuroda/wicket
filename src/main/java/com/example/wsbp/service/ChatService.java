package com.example.wsbp.service;

import com.example.wsbp.data.AuthUser;
import com.example.wsbp.data.Chat;
import com.example.wsbp.repository.IAuthUserRepository;
import com.example.wsbp.repository.IChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService implements IChatService {


    private IChatRepository chatRepos;


    @Autowired
    public ChatService(IChatRepository chatRepos) {
        this.chatRepos = chatRepos;
    }


    @Override
    public void registerChat(String userName, String chat) {
        int n = chatRepos.insertChat(userName, chat);
        System.out.println("記録行数：" + n);
    }

    @Override
    public List<Chat> findChatUsers() {
        var chats = chatRepos.findChat();
        System.out.println("データ件数：" + chats.size());
        return chats;
    }

}
