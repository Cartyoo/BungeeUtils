package xyz.herberto.bungeeUtils.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Syntax;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import xyz.herberto.bungeeUtils.BungeeUtils;
import xyz.herberto.bungeeUtils.utils.CC;
import xyz.herberto.bungeeUtils.utils.LuckPermUtils;

public class StaffChatCommand extends BaseCommand {

    @CommandAlias("staffchat|sc")
    @CommandPermission("bungeeutils.command.staffchat")
    @Syntax("<message>")

    public void staffChat(CommandSender sender, String[] message) {

        if(!String.join(" ", message).isEmpty()) {

            ProxiedPlayer player = (ProxiedPlayer)sender;

            ProxyServer.getInstance().getPlayers().stream()
                    .filter(staffMember -> staffMember.hasPermission("bungeeutils.staff"))
                    .forEach(staffMember -> staffMember.sendMessage(CC.translate(BungeeUtils.getConfig().getString("messages.staffchat.format").replaceAll("<message>", String.join(" ", message)).replaceAll("<player>", LuckPermUtils.getDisplayName(player)).replaceAll("<server>", player.getServer().getInfo().getName()))));

            ProxyServer.getInstance().getLogger().info(CC.translate(BungeeUtils.getConfig().getString("messages.staffchat.format").replaceAll("<message>", String.join(" ", message)).replaceAll("<player>", LuckPermUtils.getDisplayName(player)).replaceAll("<server>", player.getServer().getInfo().getName())));
        } else {
            sender.sendMessage(CC.translate("&c/staffchat <message>"));
        }

    }

}
