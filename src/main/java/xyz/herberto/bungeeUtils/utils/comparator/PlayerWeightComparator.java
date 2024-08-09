package xyz.herberto.bungeeUtils.utils.comparator;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import xyz.herberto.bungeeUtils.utils.LuckPermUtils;

import java.util.Comparator;

public class PlayerWeightComparator implements Comparator<ProxiedPlayer> {
    @Override
    public int compare(ProxiedPlayer o1, ProxiedPlayer o2) {
        return Integer.compare(LuckPermUtils.getRank(o1).getWeight().getAsInt(), LuckPermUtils.getRank(o2).getWeight().getAsInt());
    }
}
