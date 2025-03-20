package cn.edu.jlu.renyt1621.utils;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.util.Uuids;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

/**
 * @author REN YuanTong
 * @Description
 * @Date 2025-03-15
 * @Time 14:09
 */
public final class PlayerUtils {
    private PlayerUtils() {}

    public static Collection<GameProfile> getProfileFromNickName(String name) {
        UUID playerUuid = Uuids.getOfflinePlayerUuid(name);

        return Collections.singletonList(new GameProfile(playerUuid, name));
    }

    public static Collection<String> getIpFromNickName(String targets) {
        

        return Collections.singletonList("1");
    }
}
