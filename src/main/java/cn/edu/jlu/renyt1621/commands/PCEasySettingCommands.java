package cn.edu.jlu.renyt1621.commands;

import cn.edu.jlu.renyt1621.config.Config;
import cn.edu.jlu.renyt1621.translate.LangSuggestionProvider;
import cn.edu.jlu.renyt1621.translate.Translate;
import cn.edu.jlu.renyt1621.utils.MessageUtils;
import cn.edu.jlu.renyt1621.utils.PermissionUtil;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import static cn.edu.jlu.renyt1621.translate.Translate.tr;
import static net.minecraft.server.command.CommandManager.literal;

/**
 * @author REN YuanTong
 * @Description
 * @Date 2025-03-15
 * @Time 15:14
 */
public final class PCEasySettingCommands {

    public static LiteralArgumentBuilder<ServerCommandSource> SETTING_COMMAND = literal("setting")
        .requires(PermissionUtil::checkPlayerPermission)
        .then(
            literal("lang")
                .requires(PermissionUtil::checkPlayerPermission)
                .then(
                    literal("get")
                        .executes(ctx -> getLang(ctx.getSource()))
                )

                .then(
                    literal("set")
                        .requires(PermissionUtil::checkPlayerPermission)
                        .then(
                            CommandManager.argument("lang", StringArgumentType.string())
                                .suggests(new LangSuggestionProvider())
                                .executes(
                                    ctx -> setLang(
                                        ctx.getSource(),
                                        StringArgumentType.getString(ctx, "lang")
                                    )
                                )
                        )
                )

        );


    private static int setLang(ServerCommandSource source, String lang) {
        if (!Translate.SUPPORT_LANGUAGE.contains(lang)) {
            MessageUtils.sendMessage(
                source,
                Text.of(tr("pc_easy.commands.settings.lang.no_exist"))
            );
        }
        Config.INSTANCE.setLang(lang);
        MessageUtils.sendMessage(
            source,
            Text.of(tr("pc_easy.commands.settings.lang.set", lang))
        );
        return 0;
    }

    private static int getLang(ServerCommandSource source) {
        MessageUtils.sendMessage(
            source,
            Text.of(tr("pc_easy.commands.settings.lang.get", Config.INSTANCE.getLang()))
        );
        return 0;
    }
}
