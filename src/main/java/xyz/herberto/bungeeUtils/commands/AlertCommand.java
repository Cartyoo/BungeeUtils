package xyz.herberto.bungeeUtils.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Syntax;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import xyz.herberto.bungeeUtils.BungeeUtils;
import xyz.herberto.bungeeUtils.utils.CC;

public class AlertCommand extends BaseCommand {

    @CommandAlias("globalalert|globalbroadcast")
    @CommandPermission("bungeeutils.command.alert")
    @Syntax("<message>")

    public void alert(CommandSender sender, String[] message) {
        String formattedMessage = String.join(" ", message);

        if(!formattedMessage.isEmpty()) {
            ProxyServer.getInstance().broadcast(CC.translate(BungeeUtils.getConfig().getString("messages.alert.format").replaceAll("<message>", formattedMessage)));
        }

    }

}
