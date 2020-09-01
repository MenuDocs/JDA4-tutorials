package me.duncte123.jdatuts.command.commands.music;

import me.duncte123.jdatuts.command.CommandContext;
import me.duncte123.jdatuts.command.ICommand;

public class NowPlayingCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        //
    }

    @Override
    public String getName() {
        return "nowplaying";
    }

    @Override
    public String getHelp() {
        return "Shows the currently playing song";
    }
}
