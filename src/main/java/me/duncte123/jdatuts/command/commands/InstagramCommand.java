package me.duncte123.jdatuts.command.commands;

import com.fasterxml.jackson.databind.JsonNode;
import me.duncte123.botcommons.messaging.EmbedUtils;
import me.duncte123.botcommons.web.WebUtils;
import me.duncte123.jdatuts.command.CommandContext;
import me.duncte123.jdatuts.command.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.Collections;
import java.util.List;

public class InstagramCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        final List<String> args = ctx.getArgs();
        final TextChannel channel = ctx.getChannel();

        if (args.isEmpty()) {
            channel.sendMessage("You must provide a username to look up").queue();
            return;
        }

        final String usn = args.get(0);

        WebUtils.ins.getJSONObject("https://apis.duncte123.me/insta/" + usn).async((json) -> {
            if (!json.get("success").asBoolean()) {
                channel.sendMessage(json.get("error").get("message").asText()).queue();
                return;
            }

            final JsonNode user = json.get("user");
            final String username = user.get("username").asText();
            final String pfp = user.get("profile_pic_url").asText();
            final String biography = user.get("biography").asText();
            final boolean isPrivate = user.get("is_private").asBoolean();
            final int following = user.get("following").get("count").asInt();
            final int followers = user.get("followers").get("count").asInt();
            final int uploads = user.get("uploads").get("count").asInt();

            final EmbedBuilder embed = EmbedUtils.defaultEmbed()
                    .setTitle("Instagram info of " + username, "https://www.instagram.com/" + username)
                    .setThumbnail(pfp)
                    .setDescription(String.format(
                            "**Private account:** %s\n**Bio:** %s\n**Following:** %s\n**Followers:** %s\n**Uploads:** %s",
                            toEmote(isPrivate),
                            biography,
                            following,
                            followers,
                            uploads
                    ))
                    .setImage(getLatestImage(json.get("images")));

            channel.sendMessage(embed.build()).queue();
        });
    }

    @Override
    public String getName() {
        return "instagram";
    }

    @Override
    public List<String> getAliases() {
        return Collections.singletonList("insta");
    }

    @Override
    public String getHelp() {
        return "Shows instagram statistics of a user with the latest image\n" +
                "Usage: `!!instagram <username>`";
    }

    private String getLatestImage(JsonNode json) {
        if (!json.isArray()) {
            return null;
        }

        if (json.size() == 0) {
            return null;
        }

        return json.get(0).get("url").asText();
    }

    private String toEmote(boolean bool) {
        return bool ? "<:sliderRight:582718257598038017>" : "<:sliderLeft:582718257866473472>";
    }
}
