package xyz.herberto.bungeeUtils.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import xyz.herberto.bungeeUtils.BungeeUtils;
import xyz.herberto.bungeeUtils.utils.CC;
import xyz.herberto.bungeeUtils.utils.ReplyMap;

@CommandAlias("message|msg")
public class MessageCommand extends BaseCommand {

    private final BungeeUtils plugin;
    private final ReplyMap replyMap;

    public MessageCommand(BungeeUtils plugin, ReplyMap replyMap) {
        this.plugin = plugin;
        this.replyMap = replyMap;
    }

    @CommandCompletion("@players")
    @Default
    public void onMessage(CommandSender sender, String targetName, String[] message) {
        ProxiedPlayer senderPlayer = (ProxiedPlayer) sender;

        if (BungeeUtils.getInstance().getProxy().getPlayer(targetName) == null || !BungeeUtils.getInstance().getProxy().getPlayer(targetName).isConnected()) {
            sender.sendMessage(CC.translate("&cPlayer " + BungeeUtils.getInstance().getProxy().getPlayer(targetName).getName() + " not found."));
            return;
        }

        ProxiedPlayer target = BungeeUtils.getInstance().getProxy().getPlayer(targetName);

        StringBuilder messageBuilder = new StringBuilder();
        for (String word : message) {
            messageBuilder.append(word).append(" ");
        }

        if(messageBuilder.isEmpty()) {
            senderPlayer.sendMessage(CC.translate("&cYou can not send an empty reply."));
            return;
        }

        target.sendMessage(CC.translate("&8[&c&lMSG&8] &f" + senderPlayer.getName() + " -> You: ") + messageBuilder);
        sender.sendMessage(CC.translate("&8[&c&lMSG&8] &fYou -> " + target.getName() + ": " + messageBuilder));

        replyMap.setLastMessaged(senderPlayer.getUniqueId(), target.getUniqueId());
        replyMap.setLastMessaged(target.getUniqueId(), senderPlayer.getUniqueId());

    }

}

