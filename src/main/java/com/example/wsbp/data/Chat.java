package com.example.wsbp.data;

import java.io.Serializable;

import java.time.LocalDateTime;

// AUTH_USER テーブルのデータを入れるクラス
// Wicketの Model に使うかもしれないクラスは、 implements Serializable をつける
public class Chat implements Serializable {

    private final String userName;  // auth_userテーブルのuser_name列のデータ
    private final String msgBody;  // chatテーブルのuser_chat列のデータ

    private final LocalDateTime time;
    public Chat(String userName, String msgBody,LocalDateTime time) {
        this.userName = userName;
        this.msgBody = msgBody;
        this.time=time;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPass() {
        return msgBody;
    }

    public LocalDateTime getUserTime() {
        return time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Chat chat = (Chat) o;

        if (!userName.equals(chat.userName)) return false;
        return msgBody.equals(chat.msgBody);
    }

//    @Override
//    public int hashCode() {
//        int result = userName.hashCode();
//        result = 31 * result + msgBody.hashCode();
//        return result;
//    }
}
