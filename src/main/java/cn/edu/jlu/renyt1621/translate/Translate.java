package cn.edu.jlu.renyt1621.translate;

import org.apache.commons.io.IOUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Translate {

    private static Map<String, String> translateMap = new HashMap<>();
    public static final Collection<String> SUPPORT_LANGUAGE = List.of("zh_cn", "en_us");

    public static Map<String, String> getTranslationFromResourcePath(String lang) {
        InputStream langFile = Translate.class.getClassLoader().getResourceAsStream("assets/pc_easy/lang/%s.yaml".formatted(lang));
        if (langFile == null) {
            return Collections.emptyMap();
        }
        String yamlData;
        try {
            yamlData = IOUtils.toString(langFile, StandardCharsets.UTF_8);
        } catch (IOException e) {
            return Collections.emptyMap();
        }
        Yaml yaml = new Yaml();
        Map<String, Object> obj = yaml.load(yamlData);
        return addMapToResult("", obj);
    }

    public static void handleResourceReload(String lang) {
        translateMap = getTranslationFromResourcePath(lang);
    }

    public static String translate(String key, Object... args) {
        String fmt = translateMap.getOrDefault(key, key);
        if (!translateMap.containsKey(key)) return key;
        return String.format(fmt, args);
    }

    public static String tr(String k, Object... o) {
        return translate(k, o);
    }

    @SuppressWarnings("unchecked")
    private static Map<String, String> addMapToResult(String prefix, Map<String, Object> map) {
        Map<String, String> resultMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            String newPrefix = prefix.isEmpty() ? key : prefix + "." + key;
            if (value instanceof Map) {
                resultMap.putAll(addMapToResult(newPrefix, (Map<String, Object>) value));
            } else {
                resultMap.put(newPrefix, value.toString());
            }
        }
        return resultMap;
    }

}
