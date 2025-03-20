package cn.edu.jlu.renyt1621.commands;

import cn.edu.jlu.renyt1621.utils.PermissionUtil;
import cn.edu.jlu.renyt1621.commands.utils.OpUtils;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

import static cn.edu.jlu.renyt1621.References.*;
import static net.minecraft.server.command.CommandManager.literal;


/**
 * @author REN YuanTong
 * @Description
 * @Date 2025-03-14
 * @Time 16:07
 */
public class PCEasyOpCommands {

    protected static final LiteralArgumentBuilder<ServerCommandSource> OP_ADD_COMMAND = literal("add")
        .requires(PermissionUtil::checkAdminPermission)
        .then(
            CommandManager
                .argument("targets", StringArgumentType.string())
                .executes(ctx ->
                    OpUtils.opListAddByName(ctx.getSource(), StringArgumentType.getString(ctx, "targets"))
                )
        );

    protected static final LiteralArgumentBuilder<ServerCommandSource> OP_REMOVE_COMMAND = literal("remove")
        .requires(PermissionUtil::checkAdminPermission)
        .then(
            CommandManager
                .argument("targets", StringArgumentType.string())
                .executes(ctx ->
                    OpUtils.opListRemoveByName(ctx.getSource(), StringArgumentType.getString(ctx, "targets"))
                )
        );

    public static final LiteralArgumentBuilder<ServerCommandSource> OP_COMMANDS = literal("op")
        .requires(PermissionUtil::checkAdminPermission)
        .then(OP_ADD_COMMAND)
        .then(OP_REMOVE_COMMAND);


}
