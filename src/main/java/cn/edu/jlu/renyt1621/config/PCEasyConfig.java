package cn.edu.jlu.renyt1621.config;

import cn.edu.jlu.renyt1621.utils.PathUtils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Path;

import static cn.edu.jlu.renyt1621.References.MOD_LOGGER;
import static cn.edu.jlu.renyt1621.References.gson;

/**
 * @author REN YuanTong
 * @Description
 * @Date 2025-03-15
 * @Time 15:43
 */
public class PCEasyConfig {
    private static final Path CONFIG_PATH = PathUtils.getConfigDir();
    private static final File CONFIG_PATH_FILE = PathUtils.getConfigDir().toFile();
    private static final File CONFIG_FILE = CONFIG_PATH.resolve("pc_easy_config.json").toFile();

    private ConfigInfo configInfo;

    public void load() {
        FileReader reader = null;
        try  {
            MOD_LOGGER.info("Loading configs from {}", CONFIG_FILE.getAbsolutePath());
            if (!CONFIG_PATH_FILE.exists()) {
                CONFIG_PATH_FILE.mkdirs();
            }

            if (!CONFIG_FILE.exists()) {
                loadConfigInfo(ConfigInfo.DEFAULT);
            }
            reader = new FileReader(CONFIG_FILE);
            ConfigInfo result = gson.fromJson(reader, ConfigInfo.class);
            this.configInfo = fixFields(result, ConfigInfo.DEFAULT);
            loadConfigInfo(this.configInfo);
            MOD_LOGGER.info("Loaded configs from {}", CONFIG_FILE.getAbsolutePath());
        } catch (Exception e) {
            MOD_LOGGER.error("Error while loading config", e);
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
                MOD_LOGGER.error("Error while closing file", e);
            }
        }
    }

    public void loadConfigInfo(ConfigInfo configInfo) {
        FileWriter writer = null;
        try  {
            if (CONFIG_FILE.exists()) CONFIG_FILE.delete();
            if (!CONFIG_FILE.exists()) CONFIG_FILE.createNewFile();
            writer = new FileWriter(CONFIG_FILE);
            ConfigInfo fixConfig = fixFields(configInfo, ConfigInfo.DEFAULT);
            gson.toJson(fixConfig, writer);
        } catch (IOException e) {
            MOD_LOGGER.error("Error while loading config info", e);
        } finally {
            try {
                if (writer != null)
                    writer.close();
            } catch (IOException e) {
                MOD_LOGGER.error("Error while closing file", e);
            }
        }
    }

    private ConfigInfo fixFields(ConfigInfo t, ConfigInfo defaultVal) {
        if (t == null) {
            throw new NullPointerException();
        }
        if (t.equals(defaultVal)) {
            return t;
        }
        try {
            Class<?> clazz = t.getClass();
            for (Field declaredField : clazz.getDeclaredFields()) {
                declaredField.setAccessible(true);
                Object value = declaredField.get(t);
                Object dv = declaredField.get(defaultVal);
                if (value == null) {
                    declaredField.set(t, dv);
                }
            }
            return t;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ConfigInfo getConfigInfo() {
        return configInfo;
    }

    public String getLang() {
        return configInfo.getLang();
    }

    public void setLang(String lang) {
        configInfo.setLang(lang);
        loadConfigInfo(configInfo);
    }

}
