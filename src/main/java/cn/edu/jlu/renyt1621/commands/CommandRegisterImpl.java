package cn.edu.jlu.renyt1621.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

import static net.minecraft.server.command.CommandManager.literal;

/**
 * @author REN YuanTong
 * @Description
 * @Date 2025-03-18
 * @Time 17:13
 */
public class CommandRegisterImpl
    implements CommandRegister
{

    private CommandRegisterImpl() {}

    public static final CommandRegisterImpl INSTANCE = new CommandRegisterImpl();


    @Override
    public void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess access, CommandManager.RegistrationEnvironment environment) {
        dispatcher.register(
            literal("pc_easy")
                .then(PCEasyWhiteListCommands.WHITELIST_COMMANDS)
                .then(PCEasyOpCommands.OP_COMMANDS)
                .then(PCEasyBanCommands.BAN_COMMANDS)
                .then(PCEasySettingCommands.SETTING_COMMAND)
        );
    }
}
