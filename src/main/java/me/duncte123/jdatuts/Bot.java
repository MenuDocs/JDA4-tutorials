package me.duncte123.jdatuts;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class Bot {

    private Bot() throws LoginException {

        new JDABuilder()
                .setToken("NDg1MzQzNDI4NTYxODYyNjU2.XV5aqQ.kqcxTbBuk-ae_OJtYlj5iLrTxy4")
                .addEventListeners(new Listener())
                .setActivity(Activity.watching("MenuDocs"))
                .build();

    }

    public static void main(String[] args) throws LoginException {
        new Bot();
    }

}
