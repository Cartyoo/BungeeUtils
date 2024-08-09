package xyz.herberto.bungeeUtils.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Syntax;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import xyz.herberto.bungeeUtils.BungeeUtils;
import xyz.herberto.bungeeUtils.utils.CC;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class MediaCommand extends BaseCommand {

    public static final Map<UUID, Long> cooldowns = new HashMap<>();

    @CommandAlias("media")
    @CommandPermission("bungeeutils.command.media")
    @Syntax("<message>")
    public void media(ProxiedPlayer player, String[] args) {
        String rank = getRank(player);
        long cooldownTime = getCooldownTime(rank);
        long currentTime = System.currentTimeMillis();

        if (cooldowns.containsKey(player.getUniqueId())) {
            long timeLeft = (cooldowns.get(player.getUniqueId()) - currentTime) / 1000;
            if (timeLeft > 0) {
                player.sendMessage(CC.translate("&cYou must wait " + timeLeft + " seconds before using /media again."));
                return;
            }
        }

        String message = String.join(" ", args);
        if (!message.isEmpty()) {
            ProxyServer.getInstance().broadcast(CC.translate(BungeeUtils.getConfig().getString("messages.media.format")
                    .replaceAll("<player>", player.getName())
                    .replaceAll("<message>", message)));
            cooldowns.put(player.getUniqueId(), currentTime + cooldownTime);
        } else {
            player.sendMessage(CC.translate("&c/media <message>"));
        }
    }

    private String getRank(ProxiedPlayer player) {
        if (player.hasPermission("media.netherite")) {
            return "netherite";
        } else if (player.hasPermission("media.diamond")) {
            return "diamond";
        } else if (player.hasPermission("media.iron")) {
            return "iron";
        } else if (player.hasPermission("media.coal")) {
            return "coal";
        } else if(player.hasPermission("media.bypass")) {
            return "bypass";
        }
        return "default";
    }

    private long getCooldownTime(String rank) {
        switch (rank) {
            case "media", "netherite":
                return TimeUnit.SECONDS.toMillis(30);
            case "diamond":
                return TimeUnit.SECONDS.toMillis(45);
            case "iron":
                return TimeUnit.MINUTES.toMillis(2);
            case "bypass":
                return TimeUnit.SECONDS.toMillis(0);
            default:
                return TimeUnit.MINUTES.toMillis(3);
        }
    }

}
