package cn.edu.jlu.renyt1621.utils;

import cn.edu.jlu.renyt1621.translate.Translate;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import static cn.edu.jlu.renyt1621.References.MOD_LOGGER;

/**
 * @author REN YuanTong
 * @Description
 * @Date 2025-03-15
 * @Time 14:20
 */
public final class FeedBackUtils {
    private FeedBackUtils() {}

    public static void sendFeedBack(ServerCommandSource source, String translateKey, Object... args) {
        source.sendMessage(
            Text.of(Translate.tr(translateKey, args))
        );
        MOD_LOGGER.info(Translate.tr(translateKey, args));
    }

    public static void sendFeedBack(ServerCommandSource source, Text message) {
        source.sendMessage(message);
        MOD_LOGGER.info(message.getString());
    }
}
