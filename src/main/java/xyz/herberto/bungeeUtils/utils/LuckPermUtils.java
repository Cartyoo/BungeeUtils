package xyz.herberto.bungeeUtils.utils;

import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.group.GroupManager;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import xyz.herberto.bungeeUtils.utils.comparator.RankWeightComparator;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class LuckPermUtils {

    public static boolean isStaff(ProxiedPlayer player) {
        return player.hasPermission("hbungee.staff");
    }

    public static boolean isAdmin(ProxiedPlayer player) {
        return player.hasPermission("hbungee.admin");
    }

    public static String getDisplayName(ProxiedPlayer player){
        return CC.translate(LuckPermsProvider.get().getUserManager().getUser(player.getUniqueId()).getCachedData().getMetaData().getSuffix() + player.getName());
    }

    public static String getSuffix(ProxiedPlayer player){
        return LuckPermsProvider.get().getUserManager().getUser(player.getUniqueId()).getCachedData().getMetaData().getSuffix();
    }

    public static String getRankColorWithoutName(ProxiedPlayer player){
        return LuckPermsProvider.get().getUserManager().getUser(player.getUniqueId()).getCachedData().getMetaData().getSuffix();
    }

    public static String getSuffix(UUID uuid){
        return LuckPermsProvider.get().getUserManager().getUser(uuid).getCachedData().getMetaData().getSuffix();
    }

    public static String getRankName(ProxiedPlayer player){
        return LuckPermsProvider.get().getGroupManager().getGroup(getRank(player).getName()).getCachedData().getMetaData().getSuffix() + LuckPermsProvider.get().getUserManager().getUser(player.getUniqueId()).getPrimaryGroup();
    }

    public static Group getRank(ProxiedPlayer player){
        return LuckPermsProvider.get().getGroupManager().getGroup(LuckPermsProvider.get().getUserManager().getUser(player.getUniqueId()).getPrimaryGroup());
    }

    public static String getFancyName(Group group) {
        GroupManager groupManager = LuckPermsProvider.get().getGroupManager();
        String suffix = groupManager.getGroup(group.getName()).getCachedData().getMetaData().getSuffix();
        String displayName = groupManager.getGroup(group.getName()).getDisplayName();

        return CC.translate((suffix == null ? "&7" : suffix) + (displayName == null ? "DisplayName" : displayName));
    }

    public static String getFancyRankColor(Group group){
        GroupManager groupManager = LuckPermsProvider.get().getGroupManager();
        String suffix = groupManager.getGroup(group.getName()).getCachedData().getMetaData().getSuffix();

        return CC.translate(suffix);
    }

    public static List<Group> getSortedValueCache() {
        return Collections.unmodifiableList(LuckPermsProvider.get().getGroupManager().getLoadedGroups().stream().sorted(new RankWeightComparator().reversed().thenComparing(new RankWeightComparator())).collect(Collectors.toList()));
    }

}
