package xyz.herberto.bungeeUtils.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import co.aikar.commands.bungee.contexts.OnlinePlayer;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import xyz.herberto.bungeeUtils.BungeeUtils;
import xyz.herberto.bungeeUtils.utils.CC;
import xyz.herberto.bungeeUtils.utils.ReplyMap;

public class MessageCommand extends BaseCommand {

    private final ReplyMap replyMap;

    public MessageCommand(BungeeUtils plugin, ReplyMap replyMap) {
        this.replyMap = replyMap;
    }

    @CommandAlias("message|msg")
    @CommandCompletion("@players")
    @Syntax("<player> <message>")
    public void onMessage(CommandSender sender, OnlinePlayer target, String[] message) {
        ProxiedPlayer senderPlayer = (ProxiedPlayer) sender;


        StringBuilder messageBuilder = new StringBuilder();
        for (String word : message) {
            messageBuilder.append(word).append(" ");
        }

        if(messageBuilder.isEmpty()) {
            senderPlayer.sendMessage(CC.translate("&cYou can not send an empty message."));
            return;
        }

        target.getPlayer().sendMessage(CC.translate("&8[&c&lMSG&8] &f" + senderPlayer.getName() + " -> You: ") + messageBuilder);
        sender.sendMessage(CC.translate("&8[&c&lMSG&8] &fYou -> " + target.getPlayer().getName() + ": " + messageBuilder));

        replyMap.setLastMessaged(senderPlayer.getUniqueId(), target.getPlayer().getUniqueId());
        replyMap.setLastMessaged(target.getPlayer().getUniqueId(), senderPlayer.getUniqueId());

    }

}

