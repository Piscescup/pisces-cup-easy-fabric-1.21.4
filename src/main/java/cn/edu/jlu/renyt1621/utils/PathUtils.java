package cn.edu.jlu.renyt1621.utils;

import cn.edu.jlu.renyt1621.References;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;

import java.nio.file.Path;

/**
 * @author REN YuanTong
 * @Description
 * @Date 2025-03-14
 * @Time 20:39
 */
public final class PathUtils {
    private PathUtils() {}


    public static Path getRunnableDir() {
        return FabricLoader.getInstance().getGameDir();
    }

    public static Path getConfigDir() {
        return FabricLoader.getInstance().getConfigDir();
    }


}
