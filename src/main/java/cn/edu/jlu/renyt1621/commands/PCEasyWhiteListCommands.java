package cn.edu.jlu.renyt1621.commands;

import cn.edu.jlu.renyt1621.utils.PermissionUtil;
import cn.edu.jlu.renyt1621.commands.utils.WhiteListUtils;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

import static net.minecraft.server.command.CommandManager.literal;

/**
 * @author REN YuanTong
 * @Description
 * @Date 2025-03-14
 * @Time 16:05
 */
public final class PCEasyWhiteListCommands {
    private static final LiteralArgumentBuilder<ServerCommandSource> WHITELIST_ADD_COMMAND = literal("add")
        .requires(PermissionUtil::checkPlayerPermission)
        .then(
            CommandManager
                .argument("targets", StringArgumentType.string())
                .executes(ctx ->
                    WhiteListUtils.whiteListAdd(ctx.getSource(), StringArgumentType.getString(ctx, "targets"))
                )
        );

    private static final LiteralArgumentBuilder<ServerCommandSource> WHITELIST_REMOVE_COMMAND = literal("remove")
        .requires(PermissionUtil::checkPlayerPermission)
        .then(
            CommandManager
                .argument("targets", StringArgumentType.string())
                .executes(ctx ->
                    WhiteListUtils.whiteListRemove(ctx.getSource(), StringArgumentType.getString(ctx, "targets"))
                )
        );

    public static final LiteralArgumentBuilder<ServerCommandSource> WHITELIST_COMMANDS = literal("whitelist")
        .requires(PermissionUtil::checkPlayerPermission)
        .then(WHITELIST_ADD_COMMAND)
        .then(WHITELIST_REMOVE_COMMAND);

    private PCEasyWhiteListCommands() {}

}
