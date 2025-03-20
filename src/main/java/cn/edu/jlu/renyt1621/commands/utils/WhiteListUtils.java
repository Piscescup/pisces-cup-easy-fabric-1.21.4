package cn.edu.jlu.renyt1621.commands.utils;

import cn.edu.jlu.renyt1621.utils.PlayerUtils;
import com.mojang.authlib.GameProfile;
import net.minecraft.server.Whitelist;
import net.minecraft.server.WhitelistEntry;
import net.minecraft.server.command.ServerCommandSource;

import java.util.Collection;

import static cn.edu.jlu.renyt1621.References.*;
import static cn.edu.jlu.renyt1621.utils.FeedBackUtils.sendFeedBack;

/**
 * @author REN YuanTong
 * @Description
 * @Date 2025-03-14
 * @Time 16:34
 */
public final class WhiteListUtils {
    private WhiteListUtils() {}


    public static int whiteListAdd(ServerCommandSource source, String players) {
        MOD_LOGGER.info("Starting to ADD {} to White List", players);

        Whitelist whitelist = source.getServer().getPlayerManager().getWhitelist();
        Collection<GameProfile> profiles = PlayerUtils.getProfileFromNickName(players);
        int i = 0;
        for (GameProfile gameProfile : profiles) {
            if (whitelist.isAllowed(gameProfile)) {
                sendFeedBack(source, "pc_easy.commands.whitelist.add.exist", gameProfile.getName());
                continue;
            }
            WhitelistEntry whitelistEntry = new WhitelistEntry(gameProfile);
            whitelist.add(whitelistEntry);
            sendFeedBack(source, "pc_easy.commands.whitelist.add.success", gameProfile.getName());
            i++;
        }

        MOD_LOGGER.info("ADDED {} to white list", players);
        return i;
    }

    public static int whiteListRemove(ServerCommandSource source, String players) {
        MOD_LOGGER.info("Starting to REMOVE {} to white list", players);

        Whitelist whitelist = source.getServer().getPlayerManager().getWhitelist();
        Collection<GameProfile> profiles = PlayerUtils.getProfileFromNickName(players);

        int i = 0;
        for(GameProfile gameProfile : profiles) {
            if (whitelist.isAllowed(gameProfile)) {
                WhitelistEntry whitelistEntry = new WhitelistEntry(gameProfile);
                whitelist.remove(whitelistEntry);
                sendFeedBack(source, "pc_easy.commands.whitelist.remove.success", gameProfile.getName());
                ++i;
            }
            else {
                sendFeedBack(source, "pc_easy.commands.whitelist.remove.not_exist", gameProfile.getName());
            }
        }

        MOD_LOGGER.info("REMOVED {} to white list", players);
        return i;
    }


}
