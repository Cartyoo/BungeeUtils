package xyz.herberto.bungeeUtils.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Syntax;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import xyz.herberto.bungeeUtils.BungeeUtils;
import xyz.herberto.bungeeUtils.utils.CC;

public class StaffChatCommand extends BaseCommand {

    @CommandAlias("staffchat|sc")
    @CommandPermission("bungeeutils.command.staffchat")
    @Syntax("<message>")

    public void staffChat(CommandSender sender, String message) {

        ProxyServer.getInstance().getPlayers().stream()
                .filter(staffMember -> staffMember.hasPermission("bungeeutils.staff"))
                .forEach(staffMember -> staffMember.sendMessage(CC.translate(BungeeUtils.getConfig().getString("messages.staffchat.format").replaceAll("<message>", String.join(" ", message).replaceAll("<player>", sender.getName())))));

        ProxyServer.getInstance().getLogger().info(CC.translate(BungeeUtils.getConfig().getString("messages.staffchat.format").replaceAll("<message>", String.join(" ", message).replaceAll("<player>", sender.getName()))));
    }

}
