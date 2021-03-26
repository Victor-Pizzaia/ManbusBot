package com.vhpizzaia.manbusBot.command.commands;

import com.vhpizzaia.manbusBot.CommandManager;
import com.vhpizzaia.manbusBot.Config;
import com.vhpizzaia.manbusBot.command.CommandContext;
import com.vhpizzaia.manbusBot.command.ICommand;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.List;

public class Help implements ICommand {

    private final CommandManager manager;

    public Help(CommandManager manager) {
        this.manager = manager;
    }

    @Override
    public void handle(CommandContext ctx) {
        List<String> args = ctx.getArgs();
        TextChannel channel = ctx.getChannel();

        if (args.isEmpty()) {
            StringBuilder builder = new StringBuilder();

            builder.append("Lista de comandos\n");

            manager.getCommands().stream().map(ICommand::getName).forEach(
                    (it) -> builder.append('`').append(Config.get("PREFIX")).append(it).append("`\n")
            );

            channel.sendMessage(builder.toString()).queue();
            return;
        }

        String search = args.get(0);
        ICommand command = manager.getCommand(search);

        if (command == null) {
            channel.sendMessage("Não encontrei este comando: " + search).queue();
            return;
        }

        channel.sendMessage(command.getHelp()).queue();
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getHelp() {
        return "Mostra a lista de comandos do bot\n" +
                "Uso: ```!help [comando]```";
    }

    @Override
    public List<String> getAliases() {
        return List.of("commands", "cmds", "commandlist", "comandos", "cmd");
    }
}
