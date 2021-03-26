package com.vhpizzaia.manbusBot.command.commands;

import com.vhpizzaia.manbusBot.command.CommandContext;
import com.vhpizzaia.manbusBot.command.ICommand;
import net.dv8tion.jda.api.JDA;

public class Ping implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        JDA jda = ctx.getJDA();
        jda.getRestPing().queue(
                (ping) -> ctx.getChannel()
                .sendMessageFormat("Resent ping: %sms\nWS ping: %sms", ping, jda.getGatewayPing()).queue()
        );
    }

    public String getHelp() {
        return "Mostra o ping entre o bot e o servidor do discord";
    }
    @Override
    public String getName() {
        return "ping";
    }
}
