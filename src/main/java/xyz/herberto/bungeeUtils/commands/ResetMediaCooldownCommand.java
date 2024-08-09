package xyz.herberto.bungeeUtils.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Syntax;
import co.aikar.commands.bungee.contexts.OnlinePlayer;
import net.md_5.bungee.api.CommandSender;
import xyz.herberto.bungeeUtils.BungeeUtils;
import xyz.herberto.bungeeUtils.utils.CC;

public class ResetMediaCooldownCommand extends BaseCommand {

    @CommandAlias("resetmediacooldown")
    @CommandPermission("bungeeutils.command.resetmediacooldown")
    @Syntax("<player>")
    @CommandCompletion("@players")

    public void resetCooldown(CommandSender sender, OnlinePlayer target) {

        MediaCommand.cooldowns.remove(target.getPlayer().getUniqueId());
        target.getPlayer().sendMessage(CC.translate(BungeeUtils.getConfig().getString("messages.resetmediacooldown.reset-target")));
        sender.sendMessage(CC.translate(BungeeUtils.getConfig().getString("messages.resetmediacooldown.reset").replaceAll("<player>", target.getPlayer().getName())));

    }

}
