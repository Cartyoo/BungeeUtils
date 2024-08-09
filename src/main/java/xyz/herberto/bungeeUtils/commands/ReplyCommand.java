package xyz.herberto.bungeeUtils.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import xyz.herberto.bungeeUtils.BungeeUtils;
import xyz.herberto.bungeeUtils.utils.CC;
import xyz.herberto.bungeeUtils.utils.ReplyMap;


@CommandAlias("reply|r")
public class ReplyCommand extends BaseCommand {
    private final BungeeUtils plugin;
    private final ReplyMap replyMap;

    public ReplyCommand(BungeeUtils plugin, ReplyMap replyMap) {
        this.plugin = plugin;
        this.replyMap = replyMap;
    }

    @Default
    public void onReply(CommandSender sender, String[] message) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage("This command can only be executed by a player.");
            return;
        }

        ProxiedPlayer player = (ProxiedPlayer) sender;

        if (!replyMap.lastMessagedMap.containsKey(player.getUniqueId())) {
            player.sendMessage(CC.translate("&cNo players to reply to."));
            return;
        }

        ProxiedPlayer target = plugin.getProxy().getPlayer(replyMap.getLastMessaged(player.getUniqueId()));
        if (target == null || !target.isConnected()) {
            player.sendMessage(CC.translate("&cThe player who last messaged you is now offline."));
            return;
        }

        StringBuilder messageBuilder = new StringBuilder();
        for (String word : message) {
            messageBuilder.append(word).append(" ");
        }

        if(messageBuilder.isEmpty()) {
            player.sendMessage(CC.translate("&cYou can not send an empty reply."));
            return;
        }

        target.sendMessage(CC.translate("&8[&c&lMSG&8] &f" + player.getDisplayName() + " &f-> You: ") + messageBuilder);
        sender.sendMessage(CC.translate("&8[&c&lMSG&8] &fYou -> " + target.getName() + ": " + messageBuilder));

        replyMap.setLastMessaged(player.getUniqueId(), target.getUniqueId());
        replyMap.setLastMessaged(target.getUniqueId(), player.getUniqueId());
    }

}

