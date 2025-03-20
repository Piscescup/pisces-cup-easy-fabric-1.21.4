package cn.edu.jlu.renyt1621.config;


import static cn.edu.jlu.renyt1621.translate.Translate.SUPPORT_LANGUAGE;

/**
 * @author REN YuanTong
 * @Description
 * @Date 2025-03-14
 * @Time 20:47
 */
public class ConfigInfo {
    public static final ConfigInfo DEFAULT = new ConfigInfo(
        "en_us"
    );

    private String lang;

    public ConfigInfo() {
        this.lang = "en_us";
    }

    public ConfigInfo(String lang) {
        this.lang = lang;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        if (!SUPPORT_LANGUAGE.contains(lang))
            lang = "en_us";
        this.lang = lang;
    }
}

