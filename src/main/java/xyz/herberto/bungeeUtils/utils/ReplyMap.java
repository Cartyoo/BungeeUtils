package xyz.herberto.bungeeUtils.utils;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ReplyMap {
    @Getter public static Map<UUID, UUID> lastMessagedMap;

    public ReplyMap() {
        lastMessagedMap = new HashMap<>();
    }

    public void setLastMessaged(UUID playerUUID, UUID lastMessagedUUID) {
        lastMessagedMap.put(playerUUID, lastMessagedUUID);
    }

    public UUID getLastMessaged(UUID playerUUID) {
        return lastMessagedMap.get(playerUUID);
    }
}
