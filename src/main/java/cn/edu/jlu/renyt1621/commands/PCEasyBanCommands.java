package cn.edu.jlu.renyt1621.commands;

import cn.edu.jlu.renyt1621.utils.PermissionUtil;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import cn.edu.jlu.renyt1621.commands.utils.BanUtils;


import static net.minecraft.server.command.CommandManager.literal;

/**
 * This class is used to register commands, which is used to ban the player.
 *
 * @author REN YuanTong
 * @Date 2025-03-14
 * @Time 16:07
 */
public class PCEasyBanCommands {
    protected static final LiteralArgumentBuilder<ServerCommandSource> BAN_ADD_COMMAND = literal("add")
        .requires(PermissionUtil::checkPlayerPermission)
        .then(
            CommandManager
                .argument("targets", StringArgumentType.string())
                .executes(ctx ->
                    BanUtils.banListAddByName(ctx.getSource(), StringArgumentType.getString(ctx, "targets"))
                )
        );

    protected static final LiteralArgumentBuilder<ServerCommandSource> BAN_REMOVE_COMMAND = literal("remove")
        .requires(PermissionUtil::checkPlayerPermission)
        .then(
            CommandManager
                .argument("targets", StringArgumentType.string())
                .executes(ctx ->
                    BanUtils.banListRemoveByName(ctx.getSource(), StringArgumentType.getString(ctx, "targets"))
                )
        );

    public static final LiteralArgumentBuilder<ServerCommandSource> BAN_COMMANDS = literal("ban_name")
        .requires(PermissionUtil::checkPlayerPermission)
        .then(BAN_ADD_COMMAND)
        .then(BAN_REMOVE_COMMAND);



}
