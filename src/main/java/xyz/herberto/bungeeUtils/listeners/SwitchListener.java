package xyz.herberto.bungeeUtils.listeners;

import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.event.EventHandler;
import xyz.herberto.bungeeUtils.BungeeUtils;
import xyz.herberto.bungeeUtils.utils.CC;
import xyz.herberto.bungeeUtils.utils.LuckPermUtils;


public class SwitchListener implements Listener {

    @EventHandler
    public void onSwitch(ServerSwitchEvent event) {
        String server = event.getPlayer().getServer().getInfo().getName();
        ProxiedPlayer player = event.getPlayer();

        ProxyServer.getInstance().getPlayers().stream().filter(proxiedPlayer -> proxiedPlayer.hasPermission("bungeeutils.staff")).forEach(proxiedPlayer ->
                proxiedPlayer.sendMessage(CC.translate(BungeeUtils.getConfig().getString("messages.switch.connected").replaceAll("<player>", LuckPermUtils.getDisplayName(player)).replaceAll("<server>", server)))
        );
    }

    @EventHandler
    public void onDisconnect(PlayerDisconnectEvent event) {
        String server = event.getPlayer().getServer().getInfo().getName();
        ProxiedPlayer player = event.getPlayer();

        ProxyServer.getInstance().getPlayers().stream().filter(proxiedPlayer -> proxiedPlayer.hasPermission("bungeeutils.staff")).forEach(proxiedPlayer ->
                proxiedPlayer.sendMessage(CC.translate(BungeeUtils.getConfig().getString("messages.switch.disconnected").replaceAll("<player>", LuckPermUtils.getDisplayName(player)).replaceAll("<server>", server)))
        );
    }

}
