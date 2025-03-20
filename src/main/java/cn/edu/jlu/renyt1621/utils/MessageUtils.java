package cn.edu.jlu.renyt1621.utils;

import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

/**
 * A util is used to manage the feedback message.
 *
 * @author REN YuanTong
 * @Date 2025-03-15
 * @deprecated The class is deprecated, use the static method {@link cn.edu.jlu.renyt1621.utils.FeedBackUtils#sendFeedBack}
 *  in {@link cn.edu.jlu.renyt1621.utils.FeedBackUtils} instead.
 */
@Deprecated
public final class MessageUtils {
    public static void sendMessage(ServerCommandSource source, Text text)
    {
        source.sendMessage(text);
    }
}
