package xyz.herberto.bungeeUtils.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.Optional;
import co.aikar.commands.annotation.Syntax;
import co.aikar.commands.bungee.contexts.OnlinePlayer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import xyz.herberto.bungeeUtils.BungeeUtils;
import xyz.herberto.bungeeUtils.utils.CC;

public class HubCommand extends BaseCommand {

    @CommandAlias("hub|lobby")
    @CommandCompletion("@players")
    @Syntax("[player]")

    public void hub(ProxiedPlayer sender, @Optional OnlinePlayer target) {

        if(BungeeUtils.getInstance().getProxy().getServerInfo(BungeeUtils.getConfig().getString("hub-name")) != null) {
            ServerInfo hubInfo = BungeeUtils.getInstance().getProxy().getServerInfo(BungeeUtils.getConfig().getString("hub-name"));

            if (target == null) {
                if (sender.getServer().getInfo().equals(hubInfo)) {
                    sender.sendMessage(CC.translate(BungeeUtils.getConfig().getString("messages.hub.already-connected")));
                } else {
                    sender.connect(hubInfo);
                    sender.sendMessage(CC.translate(BungeeUtils.getConfig().getString("messages.hub.sending")));
                }

            } else {
                target.getPlayer().connect(hubInfo);
                sender.sendMessage(CC.translate(BungeeUtils.getConfig().getString("messages.hub.sending")));
                sender.sendMessage(CC.translate(BungeeUtils.getConfig().getString("messages.hub.sent").replaceAll("<player>", target.getPlayer().getName())));

            }

        } else {
            sender.sendMessage(CC.translate("&cThe Hub server has not been detected. Please notify a server administrator."));
            BungeeUtils.getInstance().getProxy().getLogger().severe("Hub server not found!!");
            BungeeUtils.getInstance().getProxy().getLogger().severe("Hub server not found!!");
            BungeeUtils.getInstance().getProxy().getLogger().severe("The command /hub will fail!");
            BungeeUtils.getInstance().getProxy().getLogger().severe("Hub config value: " + BungeeUtils.getConfig().getString("hub-name"));
        }
    }

}
