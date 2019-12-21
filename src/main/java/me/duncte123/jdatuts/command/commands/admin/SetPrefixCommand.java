package me.duncte123.jdatuts.command.commands.admin;

import me.duncte123.jdatuts.VeryBadDesign;
import me.duncte123.jdatuts.command.CommandContext;
import me.duncte123.jdatuts.command.ICommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.List;

public class SetPrefixCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        final TextChannel channel = ctx.getChannel();
        final List<String> args = ctx.getArgs();
        final Member member = ctx.getMember();

        if (!member.hasPermission(Permission.MANAGE_SERVER)) {
            channel.sendMessage("You must have the Manage Server permission to use his command").queue();
            return;
        }

        if (args.isEmpty()) {
            channel.sendMessage("Missing args").queue();
            return;
        }

        final String newPrefix = String.join("", args);
        VeryBadDesign.PREFIXES.put(ctx.getGuild().getIdLong(), newPrefix);

        channel.sendMessageFormat("New prefix has been set to `%s`", newPrefix).queue();
    }

    @Override
    public String getName() {
        return "setprefix";
    }

    @Override
    public String getHelp() {
        return "Sets the prefix for this server\n" +
                "Usage: `!!setprefix <prefix>`";
    }
}
