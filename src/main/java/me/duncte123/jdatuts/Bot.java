package me.duncte123.jdatuts;

import me.duncte123.botcommons.messaging.EmbedUtils;
import me.duncte123.botcommons.web.WebUtils;
import me.duncte123.jdatuts.database.DatabaseManager;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class Bot {

    private Bot() throws LoginException {
        // Sneaky init of the class for faster boot
        DatabaseManager.INSTANCE.getPrefix(-1);
        WebUtils.setUserAgent();
        EmbedUtils.setEmbedBuilder(
                () -> new EmbedBuilder()
                .setColor(0x3883d9)
                .setFooter("MeuDocs Tutorials")
        );

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
