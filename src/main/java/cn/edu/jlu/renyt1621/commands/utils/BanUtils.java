package cn.edu.jlu.renyt1621.commands.utils;

import cn.edu.jlu.renyt1621.utils.PlayerUtils;
import com.mojang.authlib.GameProfile;
import net.minecraft.server.BannedPlayerEntry;
import net.minecraft.server.BannedPlayerList;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import java.util.Collection;

import static cn.edu.jlu.renyt1621.References.MOD_LOGGER;
import static cn.edu.jlu.renyt1621.translate.Translate.tr;
import static cn.edu.jlu.renyt1621.utils.FeedBackUtils.sendFeedBack;
import static cn.edu.jlu.renyt1621.utils.MessageUtils.sendMessage;

/**
 * @author REN YuanTong
 * @Description
 * @Date 2025-03-15
 * @Time 14:09
 */
public final class BanUtils {
    private BanUtils() {}


    public static int banListAddByName(ServerCommandSource source, String players) {
        MOD_LOGGER.info("Starting to ADD {} to Ban List", players);

        BannedPlayerList userBanList = source.getServer().getPlayerManager().getUserBanList();
        Collection<GameProfile> profiles = PlayerUtils.getProfileFromNickName(players);

        int i = 0;
        for (GameProfile target : profiles) {
            if (userBanList.contains(target)) {
                sendFeedBack(source, "pc_easy.commands.ban.add.exist", target.getName());
                continue;
            }

            BannedPlayerEntry userBanEntry = new BannedPlayerEntry(target);
            userBanList.add(userBanEntry);
            sendFeedBack(source, "pc_easy.commands.ban.add.success", target.getName());
            i++;
        }

        MOD_LOGGER.info("ADDED {} to Ban List", players);
        return i;
    }

    public static int banListRemoveByName(ServerCommandSource source, String players) {
        MOD_LOGGER.info("Starting to REMOVE {} to Ban List", players);

        BannedPlayerList userBanList = source.getServer().getPlayerManager().getUserBanList();
        Collection<GameProfile> profiles = PlayerUtils.getProfileFromNickName(players);

        int i = 0;
        for (GameProfile target : profiles) {
            if ( !userBanList.contains(target) ) {
                sendFeedBack(source, "pc_easy.commands.ban.remove.not_exist", target.getName());
                continue;
            }

            userBanList.remove(target);
            sendFeedBack(source, "pc_easy.commands.ban.remove.success", target.getName());
            i++;
        }

        MOD_LOGGER.info("REMOVED {} to Ban List", players);
        return i;
    }

}
