package xyz.herberto.bungeeUtils.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import net.md_5.bungee.api.CommandSender;
import xyz.herberto.bungeeUtils.BungeeUtils;
import xyz.herberto.bungeeUtils.utils.CC;
import xyz.herberto.bungeeUtils.utils.Stopwatch;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@CommandAlias("bungeeutils")
public class BungeeUtilsCommand extends BaseCommand {

    @Default
    public void bungeeUtils(CommandSender sender) {
        sender.sendMessage(CC.translate("&7&m                                                           "));

        sender.sendMessage(CC.translate("&a/bungeeutils reload"));
        sender.sendMessage(CC.translate("&a/bungeeutils info"));

        sender.sendMessage(CC.translate("&7&m                                                           "));
    }

    @Subcommand("reload")
    @CommandPermission("bungeeutils.reload")

    public void reload(CommandSender sender) {
        Stopwatch stopwatch = new Stopwatch();
        stopwatch.start();
        try {
            BungeeUtils.loadConfig();
        } catch(IOException e) {
            e.printStackTrace();
            sender.sendMessage(CC.translate(BungeeUtils.getConfig().getString("messages.bungeeutils.config-error")));
        }
        stopwatch.stop();
        sender.sendMessage(CC.translate(BungeeUtils.getConfig().getString("messages.bungeeutils.reloaded").replaceAll("<time>", "" + stopwatch.elapsedTime(TimeUnit.MILLISECONDS))));
    }

    @Subcommand("info")
    public void info(CommandSender sender) {
        sender.sendMessage(CC.translate("&7&m                                                                                "));
        sender.sendMessage(CC.translate(" &8| &aRunning BungeeUtils Version &f" + BungeeUtils.getInstance().getDescription().getVersion() + "&a made by &f" + BungeeUtils.getInstance().getDescription().getAuthor()));
        sender.sendMessage(CC.translate("&7&m                                                                                "));
    }

}
