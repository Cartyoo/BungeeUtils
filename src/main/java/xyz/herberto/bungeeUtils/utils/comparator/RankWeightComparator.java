package xyz.herberto.bungeeUtils.utils.comparator;

import net.luckperms.api.model.group.Group;

import java.util.Comparator;

public class RankWeightComparator implements Comparator<Group> {
    @Override
    public int compare(Group o1, Group o2) {
        int weight1 = o1.getWeight().orElse(0);
        int weight2 = o2.getWeight().orElse(0);
        return Integer.compare(weight1, weight2);
    }

}
