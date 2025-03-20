package cn.edu.jlu.renyt1621;

import cn.edu.jlu.renyt1621.utils.PathUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;

/**
 * @author REN YuanTong
 * @Description
 * @Date 2025-03-14
 * @Time 15:51
 */
public final class References {
    public static final String MOD_ID = "pc_easy";
    public static final String MOD_NAME = "PiscesCup Easy Whitelist";
    public static final String MOD_VERSION = "1.0.0";
    public static final String MC_VERSION = "1.21.4";


    public static final Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

    public static final Logger MOD_LOGGER = LoggerFactory.getLogger(MOD_ID);
}
