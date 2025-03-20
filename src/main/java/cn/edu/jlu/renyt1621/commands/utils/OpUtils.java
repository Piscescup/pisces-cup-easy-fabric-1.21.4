package cn.edu.jlu.renyt1621.commands.utils;

import cn.edu.jlu.renyt1621.utils.PlayerUtils;
import com.mojang.authlib.GameProfile;
import net.minecraft.server.OperatorEntry;
import net.minecraft.server.OperatorList;
import net.minecraft.server.command.ServerCommandSource;

import java.util.Arrays;
import java.util.Collection;

import static cn.edu.jlu.renyt1621.References.*;
import static cn.edu.jlu.renyt1621.utils.FeedBackUtils.sendFeedBack;

/**
 * @author REN YuanTong
 * @Description
 * @Date 2025-03-16
 * @Time 11:05
 */
public final class OpUtils {
    private OpUtils() {}

    public static int opListAddByName(ServerCommandSource source, String targets) {
        MOD_LOGGER.info("Starting to ADD {} to OP List", targets);

        int count = 0;
        OperatorList opList = source.getServer().getPlayerManager().getOpList();
        Collection<String> opNames = Arrays.asList(opList.getNames());

        Collection<GameProfile> profiles = PlayerUtils.getProfileFromNickName(targets);
        for (GameProfile profile : profiles) {
            if (opNames.contains(profile.getName())) {
                sendFeedBack(source, "pc_easy.commands.op.add.exist", profile.getName());
                continue;
            }
            OperatorEntry e = new OperatorEntry(profile, 4, false);
            opList.add(e);
            sendFeedBack(source, "pc_easy.commands.op.add.success", profile.getName());
            count++;
        }

        MOD_LOGGER.info("ADDED {} to OP List", targets);
        return count;
    }


    public static int opListRemoveByName(ServerCommandSource source, String targets) {
        MOD_LOGGER.info("Starting to REMOVE {} to OP List", targets);

        int count = 0;
        OperatorList opList = source.getServer().getPlayerManager().getOpList();
        Collection<String> opNames = Arrays.asList(opList.getNames());

        Collection<GameProfile> profiles = PlayerUtils.getProfileFromNickName(targets);
        for (GameProfile profile : profiles){
            if (!opNames.contains(profile.getName())) {
                sendFeedBack(source, "pc_easy.commands.op.remove.not_exist", profile.getName());
                continue;
            }
            opList.remove(profile);
            sendFeedBack(source, "pc_easy.commands.op.remove.success", profile.getName());
            count++;
        }

        MOD_LOGGER.info("REMOVED {} to OP List", targets);
        return count;
    }


}
