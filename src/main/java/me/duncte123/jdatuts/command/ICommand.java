package me.duncte123.jdatuts.command;

import java.util.List;

public interface ICommand {
    void handle(CommandContext ctx);

    String getName();

    default List<String> getAliases() {
        return List.of(); // use Arrays.asList if you are on java 8
    }
}
