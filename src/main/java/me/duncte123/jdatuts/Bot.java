package me.duncte123.jdatuts;

import me.duncte123.botcommons.messaging.EmbedUtils;
import me.duncte123.botcommons.web.WebUtils;
import me.duncte123.jdatuts.database.DatabaseManager;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;
import java.util.EnumSet;

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

        JDABuilder.createDefault(
                Config.get("token"),
                GatewayIntent.GUILD_MEMBERS,
                GatewayIntent.GUILD_MESSAGES
        )
                .setDisabledCacheFlags(EnumSet.of(
                        CacheFlag.CLIENT_STATUS,
                        CacheFlag.ACTIVITY,
                        CacheFlag.EMOTE,
                        CacheFlag.VOICE_STATE
                ))
                .addEventListeners(new Listener())
                .setActivity(Activity.watching("MenuDocs"))
                .build();
    }

    public static void main(String[] args) throws LoginException {
        new Bot();
    }

}
