package tac.language.util;

import java.io.File;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.icu.text.Transliterator;

public class UnicodeToKanji {
    public static void main(String[] args) throws Exception {
        URL resource = UnicodeToKanji.class.getResource("/Unihan_Readings.txt");
        Path path = Paths.get(resource.toURI());
        Charset utf8 = Charset.forName("utf8");

        Function<String, String> keyMapper = (String line) -> {
            return line.split("\t", 2)[0];
        };

        Function<String, Map<String, String>> valueMapper = (String line) -> {
            String val = line.split("\t", 2)[1];
            String[] ss = val.split("\t");
            Map<String, String> map = new HashMap<>();
            map.put(ss[0], ss[1]);
            return map;
        };

        BinaryOperator<Map<String, String>> merger = (Map<String, String> oldVal, Map<String, String> newVal) -> {
            Map<String, String> map = new HashMap<>();
            map.putAll(oldVal);
            map.putAll(newVal);
            return map;
        };

        Map<String, Map<String, Object>> kanjiMap = Files.lines(path, utf8)
                .filter(s -> !"".equals(s.trim())
                        && !s.trim().startsWith("#")
                        && (s.contains("kJapaneseKun") || s.contains("kJapaneseOn")))
                .collect(Collectors.toMap(keyMapper, valueMapper, merger))
                .entrySet()
                .stream()
                .filter(e -> toString(e.getKey().replace("U+", "")) != null)
                .map(e -> {
                    String key = e.getKey();
                    String kanji = toString(key.replace("U+", ""));
                    Map<String, String> value = e.getValue();
                    String kun = value.get("kJapaneseKun");
                    String on = value.get("kJapaneseOn");
                    Map<String, Object> map = new HashMap<>();
                    map.put("kanji", kanji);
                    map.put("kunyomi", toHiraganaArray(kun));
                    map.put("onyomi", toKatakanaArray(on));

                    return new AbstractMap.SimpleImmutableEntry<String, Map<String, Object>>(key, map);
                })
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue));
        ObjectMapper om = new ObjectMapper();
        om.writeValue(new File("kanjimap.json"), kanjiMap);
    }

    static Transliterator katakanaTransliterator = Transliterator.getInstance("Latin-Katakana");

    private static List<String> toKatakanaArray(String on) {
        if (on == null) {
            return null;
        }
        return Stream.of(on.split("\\s")).map(s -> {
            return katakanaTransliterator.transliterate(s);
        }).collect(Collectors.toList());
    }

    static Transliterator hiraganaTransliterator = Transliterator.getInstance("Latin-Hiragana");

    private static List<String> toHiraganaArray(String kun) {
        if (kun == null) {
            return null;
        }
        return Stream.of(kun.split("\\s")).map(s -> {
            return hiraganaTransliterator.transliterate(s);
        }).collect(Collectors.toList());
    }

    private static String toString(String str) {
        int i = Integer.parseInt(str, 16);
        // only Basic Multilingual Plane
        if (65535 < i) {
            return null;
        }
        return (char) i + "";
    }
}
