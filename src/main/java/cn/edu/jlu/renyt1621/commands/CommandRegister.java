package cn.edu.jlu.renyt1621.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

/**
 * @author REN YuanTong
 * @Description
 * @Date 2025-03-14
 * @Time 17:02
 */
public interface CommandRegister {
    void registerCommands(
        CommandDispatcher<ServerCommandSource> dispatcher,
        CommandRegistryAccess access,
        CommandManager.RegistrationEnvironment environment
    );
}
