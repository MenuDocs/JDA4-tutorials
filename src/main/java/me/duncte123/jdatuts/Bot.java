package me.duncte123.jdatuts;

import me.duncte123.botcommons.web.WebUtils;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class Bot {

    private Bot() throws LoginException {
        WebUtils.setUserAgent();

        new JDABuilder()
                .setToken(Config.get("token"))
                .addEventListeners(new Listener())
                .setActivity(Activity.watching("MenuDocs"))
                .build();

    }

    public static void main(String[] args) throws LoginException {
        new Bot();
    }

}
