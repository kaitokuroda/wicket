package com.example.wsbp.repository;

import com.example.wsbp.data.AuthUser;
import com.example.wsbp.data.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChatRepository implements IChatRepository {

    // SpringJDBCのデータベース制御用インスタンス
    private final JdbcTemplate jdbc;

    // jdbc の di/ioc 設定（Wicketとやり方が異なるので注意）
    @Autowired
    public ChatRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }


    @Override
    public int insertChat(String userName, String chats) {
        var sql = "insert into chat values (?, ?)";
        var n = jdbc.update(sql, userName, chats);
        return n;
    }


    @Override
    public List<Chat> findChat() {
        // auth_user テーブルの user_name, user_pass を検索する
        String sql = "select * from chat";

        // 検索用のSQLを実行する方法。
        // 取り出したいデータの型によって、第2引数の RowMapper を切り替える。
        // ? を使うSQLであれば、第3引数の Object型配列 の要素に順番に設定する。
        List<Chat> users = jdbc.query(sql,
                DataClassRowMapper.newInstance(Chat.class));

        // 取り出したデータ（Listの要素）をそのまま返値とする。
        return users;
    }

    @Override
    public boolean exists(String userName) {
        // ユーザ名とパスワードが一致する情報が auth_user テーブルにあれば、true を返す
        // テーブルになければ、何も返さない
        var sql = "select true from chat "
                + "where user_name = ? ";

        // 検索用のSQLを実行する方法。検索結果をList（可変長配列）で返す。
        // データの追加時と若干異なるので注意。
        var booles = jdbc.query(sql,
                SingleColumnRowMapper.newInstance(Boolean.class),
                userName);

// Listにデータがある(＝trueの要素ものがある)：照合成功
// Listにデータがない(要素が何もない)：照合失敗
        return !booles.isEmpty();
    }
}
