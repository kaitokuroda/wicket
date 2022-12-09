package com.example.wsbp.page;

import com.example.wsbp.MySession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("UserChatComp")
public class UserChatCompPage extends WebPage {

    public UserChatCompPage() {
        var userNameModel = Model.of(MySession.get().getUserName());
        var userNameLabel = new Label("userName", userNameModel);
        add(userNameLabel);


        var toChatLink = new BookmarkablePageLink<>("toChat", UserChatPage.class);
        add(toChatLink);

        var toHomeLink = new BookmarkablePageLink<>("toHome", HomePage.class);
        add(toHomeLink);
    }
}