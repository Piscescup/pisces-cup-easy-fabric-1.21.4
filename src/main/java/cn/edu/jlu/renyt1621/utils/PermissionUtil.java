package cn.edu.jlu.renyt1621.utils;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

/**
 * @author REN YuanTong
 * @Description
 * @Date 2025-03-14
 * @Time 16:16
 */
public final class PermissionUtil {
    private PermissionUtil() {}

    public static boolean checkPlayerPermission(ServerCommandSource source) {
        return source.hasPermissionLevel(0);
    }

    public static boolean checkOpPermission(ServerCommandSource source) {
        return source.hasPermissionLevel(4);
    }

    public static boolean checkAdminPermission(ServerCommandSource source) {
        return source.hasPermissionLevel(3);
    }
}
