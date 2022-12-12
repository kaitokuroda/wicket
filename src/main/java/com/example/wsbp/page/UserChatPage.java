package com.example.wsbp.page;

import com.example.wsbp.MySession;
import com.example.wsbp.data.AuthUser;
import com.example.wsbp.data.Chat;
import com.example.wsbp.page.signed.SignedPage;
import com.example.wsbp.service.IChatService;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.validation.validator.StringValidator;
import org.wicketstuff.annotation.mount.MountPath;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import com.example.wsbp.service.IUserService;

@MountPath("UserChat")
public class UserChatPage extends WebPage {
    //IUserService を IoC/DI する
    @SpringBean
    private IChatService chatService;

    public UserChatPage() {

        var userChatModel = Model.of("");

        var toUserHomePageLink = new BookmarkablePageLink<>("toHome", HomePage.class);
        add(toUserHomePageLink);

        var userInfoForm = new Form<>("usersChat") {
            @Override
            protected void onSubmit() {
                var userName = MySession.get().getUserName();
                var chat = userChatModel.getObject();
                var msg = "送信データ："
                        + userName
                        + ","
                        + chat;
                System.out.println(msg);
                // IoC/DI した userService のメソッドを呼び出す
                chatService.registerChat(userName, chat);
                //移動したいページをnewの後に入れ、引数に渡したいものを入れる
                setResponsePage(new UserChatCompPage());
            }
        };
        add(userInfoForm);

        var userPassField = new TextField<>("chattext", userChatModel);
        userInfoForm.add(userPassField);

        var chatUsersModel = Model.ofList(chatService.findChatUsers());

        // List型のモデルを表示する ListView
        var usersChatLV = new ListView<>("chatList", chatUsersModel) {
            @Override
            protected void populateItem(ListItem<Chat> listItem) {
                // List型のモデルから、 <li>...</li> ひとつ分に分けられたモデルを取り出す
                var itemModel = listItem.getModel();
                var authUser = itemModel.getObject(); // 元々のListの n 番目の要素

                // インスタンスに入れ込まれたデータベースの検索結果を、列（＝フィールド変数）ごとにとりだして表示する
                // add する先が listItem になることに注意。
                var userNameModel = Model.of(authUser.getUserName());
                var userNameLabel = new Label("userName", userNameModel);
                listItem.add(userNameLabel);

                var userChatModel = Model.of(authUser.getUserPass());
                var userChatLabel = new Label("chat", userChatModel);
                listItem.add(userChatLabel);
            }
        };
        add(usersChatLV);


        var userNameModel = Model.of("");
        Form<Void> usersChatForm = new Form<Void>("chatSearch") {

            protected void onChatSubmit() {
                var userName = userNameModel.getObject();
                if (chatService.existsChat(userName)) {
                    System.out.println("a");
                }
                setResponsePage(new UserChatPage());
            }
        };
        var userNameField = new TextField<>("nameSearch", userNameModel) {
            @Override
            protected void onInitialize() {
                super.onInitialize();
                // 文字列の長さを8〜32文字に制限するバリデータ
                add(StringValidator.lengthBetween(8, 32));
            }
        };

    }
}
