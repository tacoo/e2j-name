package tac.language.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

public class MapKatakanaAntlr {
    static String[] base = new String[] {
            "AA", "AE", "AH", "AO", "AW", "AY",
            "IH", "IY",
            "UH", "UW",
            "EH", "ER", "EY",
            "OW", "OY",
    };

    static String[] prefix = new String[] {
            "B", "CH", "D", "DH", "F",
            "G", "HH", "JH", "K", "L",
            "M", "N", "NG", "P", "R",
            "S", "SH", "T", "TH", "V",
            "W", "Y", "Z", "ZH",
    };

    public static void main(String[] args) throws Exception {
        prepareMap();
        List<String> altrString = new ArrayList<>();
        // S HH AE -> シャ
        altrString.addAll(Stream.of(prefix)
                .filter(p -> !"NG".equals(p))
                .flatMap(p -> Stream.of(base).map(b -> new String[] { p, "HH", b }))
                .map(s -> {
                    String[] ss = (String[]) s;
                    return String.format("  | %-2s %-2s %-2s { $katakana = \"%s\"; }", ss[0], ss[1], ss[2], map(ss[0], ss[1], ss[2]));
                }).collect(Collectors.toList()));

        // B AA -> バ
        altrString.addAll(Stream.of(prefix)
                .flatMap(p -> Stream.concat(
                        Stream.of(base).map(b -> new String[] { p, b })
                        , Stream.of(new String[][] { new String[] { p, null } })))
                .map(ss -> {
                    if (ss[1] == null) {
                        return String.format("  | %-5s { $katakana = \"%s\"; }", ss[0], map(ss[0], null, ss[1]));
                    }
                    return String.format("  | %-2s %-2s { $katakana = \"%s\"; }", ss[0], ss[1], map(ss[0], null, ss[1]));
                })
                .collect(Collectors.toList()));
        // A -> ア
        altrString.addAll(Stream.of(base)
                .map(b -> String.format("  | %-5s { $katakana = \"%s\"; }", b, (map(b, null, null))))
                .collect(Collectors.toList()));

        AtomicBoolean first = new AtomicBoolean(true);
        String strPattern = altrString.stream().map(s -> {
            if (first.getAndSet(false)) {
                return s.replaceFirst("\\|", " ");
            } else {
                return s;
            }
        }).collect(Collectors.joining("\n"));

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
        cfg.setDirectoryForTemplateLoading(new File("src/test/resources/"));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        Map<String, Object> root = new HashMap<>();
        root.put("katakana_pattern", strPattern);
        Template temp = cfg.getTemplate("CMU2Katakana.g4.ftl");

        Writer out = new OutputStreamWriter(new FileOutputStream("src/main/antlr/CMU2Katakana.g4"));
        temp.process(root, out);
    }

    private static String map(String p, String hh, String b) {
        if (baseKanaMap.containsKey(p)) {
            return Stream.of(baseKanaMap.get(p)).map(s -> kanaMap.get(s)).collect(Collectors.joining(""));
        }
        String[] baseAry = baseKanaMap.get(b);
        if ("B".equals(p)) {
            if (b == null) {
                baseAry = new String[] { "U" };
            }
            return concatKana("HH".equals(hh) ? p + "Y" : p, baseAry);
        }
        if ("CH".equals(p)) {
            if (b == null) {
                baseAry = new String[] { "I" };
            }
            return concatKana(p, baseAry);
        }
        if ("D".equals(p)) {
            if (b == null) {
                baseAry = new String[] { "O" };
            }
            return concatKana(p, baseAry);
        }
        if ("DH".equals(p)) {
            if (b == null) {
                baseAry = new String[] { "O" };
            }
            return concatKana("D", baseAry);
        }
        if ("F".equals(p)) {
            if (b == null) {
                baseAry = new String[] { "U" };
            }
            return concatKana("HH".equals(hh) ? "HY" : p, baseAry);
        }
        if ("G".equals(p)) {
            if (b == null) {
                baseAry = new String[] { "U" };
            }
            return concatKana("HH".equals(hh) ? p + "Y" : p, baseAry);
        }
        if ("HH".equals(p)) {
            if (b == null) {
                baseAry = new String[] { "U" };
            }
            return concatKana("H", baseAry);
        }
        if ("JH".equals(p)) {
            if (b == null) {
                baseAry = new String[] { "I" };
            }
            return concatKana("J", baseAry);
        }
        if ("K".equals(p)) {
            if (b == null) {
                baseAry = new String[] { "U" };
            }
            if ("HH".equals(hh)) {
                return concatKana("CH", baseAry);
            }
            return concatKana("HH".equals(hh) ? p + "Y" : p, baseAry);
        }
        if ("L".equals(p)) {
            if (b == null) {
                baseAry = new String[] { "U" };
            }
            return concatKana("HH".equals(hh) ? "RY" : "R", baseAry);
        }
        if ("M".equals(p)) {
            if (b == null) {
                baseAry = new String[] { "U" };
            }
            return concatKana("HH".equals(hh) ? p + "Y" : p, baseAry);
        }
        if ("N".equals(p)) {
            if (b == null) {
                return "ン";
            }
            return concatKana("HH".equals(hh) ? p + "Y" : p, baseAry);
        }
        if ("NG".equals(p)) {
            if (b == null) {
                return "ン";
            }
            return concatKana("HH".equals(hh) ? p + "NY" : "N", baseAry);
        }
        if ("P".equals(p)) {
            if (b == null) {
                baseAry = new String[] { "U" };
            }
            return concatKana("HH".equals(hh) ? "F" : p, baseAry);
        }
        if ("R".equals(p)) {
            if (b == null) {
                //baseAry = new String[] { "U" };
                return "";
            }
            return concatKana("HH".equals(hh) ? p + "Y" : p, baseAry);
        }
        if ("S".equals(p)) {
            if (b == null) {
                baseAry = new String[] { "U" };
            }
            return concatKana("HH".equals(hh) ? p + "Y" : p, baseAry);
        }
        if ("SH".equals(p)) {
            if (b == null) {
                baseAry = new String[] { "U" };
            }
            return concatKana(p, baseAry);
        }
        if ("T".equals(p)) {
            if (b == null) {
                baseAry = new String[] { "O" };
            }
            return concatKana("HH".equals(hh) ? "S" : p, baseAry);
        }
        if ("TH".equals(p)) {
            if (b == null) {
                baseAry = new String[] { "U" };
            }
            return concatKana("S", baseAry);
        }
        if ("V".equals(p)) {
            if (b == null) {
                baseAry = new String[] { "U" };
            }
            return concatKana("HH".equals(hh) ? p + "BY" : "B", baseAry);
        }
        if ("W".equals(p)) {
            if (b == null) {
                baseAry = new String[] { "U" };
            }
            return concatKana("HH".equals(hh) ? p + "Y" : p, baseAry);
        }
        if ("Y".equals(p)) {
            if (b == null) {
                return "イ";
            }
            return concatKana(p, baseAry);
        }
        if ("Z".equals(p)) {
            if (b == null) {
                baseAry = new String[] { "U" };
            }
            return concatKana("HH".equals(hh) ? p + "Y" : p, baseAry);
        }
        if ("ZH".equals(p)) {
            if (b == null) {
                baseAry = new String[] { "U" };
            }
            return concatKana("ZY", baseAry);
        }
        return "";
    }

    private static String concatKana(String p, String[] baseAry) {
        String first = kanaMap.get(p + baseAry[0]);
        String string = IntStream.range(1, baseAry.length).mapToObj(i -> kanaMap.get(baseAry[i])).collect(Collectors.joining(""));
        return first + string;
    }

    static Map<String, String[]> baseKanaMap = new HashMap<>();

    static Map<String, String> kanaMap = new HashMap<>();

    private static void prepareMap() {
        baseKanaMap.put("AA", new String[] { "A" });
        baseKanaMap.put("AE", new String[] { "A" });
        baseKanaMap.put("AH", new String[] { "A" });
        // baseKanaMap.put("AH", new String[] { "A", "-" });
        baseKanaMap.put("AO", new String[] { "O" });
        // baseKanaMap.put("AO", new String[] { "A", "O" });
        baseKanaMap.put("AW", new String[] { "A", "U" });
        baseKanaMap.put("AY", new String[] { "A", "I" });
        baseKanaMap.put("IH", new String[] { "I" });
        // baseKanaMap.put("IH", new String[] { "I", "-" });
        baseKanaMap.put("IY", new String[] { "I" });
        baseKanaMap.put("UH", new String[] { "U" });
        baseKanaMap.put("UW", new String[] { "U" });
        baseKanaMap.put("EH", new String[] { "E" });
        baseKanaMap.put("ER", new String[] { "A" });
        // baseKanaMap.put("ER", new String[] { "A", "-" });
        baseKanaMap.put("EY", new String[] { "E", "I" });
        baseKanaMap.put("OW", new String[] { "O" });
        // baseKanaMap.put("OW", new String[] { "O", "U" });
        baseKanaMap.put("OY", new String[] { "O", "I" });

        kanaMap.put("-", "ー");
        kanaMap.put("A", "ア");
        kanaMap.put("I", "イ");
        kanaMap.put("U", "ウ");
        kanaMap.put("E", "エ");
        kanaMap.put("O", "オ");

        kanaMap.put("KA", "カ");
        kanaMap.put("KI", "キ");
        kanaMap.put("KU", "ク");
        kanaMap.put("KE", "ケ");
        kanaMap.put("KO", "コ");

        kanaMap.put("SA", "サ");
        kanaMap.put("SHI", "シ");
        kanaMap.put("SI", "シ");
        kanaMap.put("SU", "ス");
        kanaMap.put("SE", "セ");
        kanaMap.put("SO", "ソ");

        kanaMap.put("TA", "タ");
        kanaMap.put("CHI", "チ");
        kanaMap.put("TSU", "ツ");
        kanaMap.put("TU", "ツ");
        kanaMap.put("TE", "テ");
        kanaMap.put("TO", "ト");

        kanaMap.put("NA", "ナ");
        kanaMap.put("NI", "ニ");
        kanaMap.put("NU", "ヌ");
        kanaMap.put("NE", "ネ");
        kanaMap.put("NO", "ノ");

        kanaMap.put("HA", "ハ");
        kanaMap.put("HI", "ヒ");
        kanaMap.put("FU", "フ");
        kanaMap.put("HU", "フ");
        kanaMap.put("HE", "ヘ");
        kanaMap.put("HO", "ホ");

        kanaMap.put("MA", "マ");
        kanaMap.put("MI", "ミ");
        kanaMap.put("MU", "ム");
        kanaMap.put("ME", "メ");
        kanaMap.put("MO", "モ");

        kanaMap.put("YA", "ヤ");
        kanaMap.put("YI", "イ");
        kanaMap.put("YU", "ユ");
        kanaMap.put("YE", "イエ");
        kanaMap.put("YO", "ヨ");

        kanaMap.put("RA", "ラ");
        kanaMap.put("RI", "リ");
        kanaMap.put("RU", "ル");
        kanaMap.put("RE", "レ");
        kanaMap.put("RO", "ロ");

        kanaMap.put("WA", "ワ");
        kanaMap.put("WI", "ウィ");
        kanaMap.put("WU", "ウ");
        kanaMap.put("WE", "ウェ");
        kanaMap.put("WO", "ウォ");

        kanaMap.put("GA", "ガ");
        kanaMap.put("GI", "ギ");
        kanaMap.put("GU", "グ");
        kanaMap.put("GE", "ゲ");
        kanaMap.put("GO", "ゴ");

        kanaMap.put("ZA", "ザ");
        kanaMap.put("JI", "ジ");
        kanaMap.put("ZI", "ジィ");
        kanaMap.put("ZU", "ズ");
        kanaMap.put("ZE", "ゼ");
        kanaMap.put("ZO", "ゾ");

        kanaMap.put("DA", "ダ");
        kanaMap.put("JI", "ヂ");
        kanaMap.put("ZU", "ズ");
        kanaMap.put("DE", "デ");
        kanaMap.put("DO", "ド");

        kanaMap.put("BA", "バ");
        kanaMap.put("BI", "ビ");
        kanaMap.put("BU", "ブ");
        kanaMap.put("BE", "ベ");
        kanaMap.put("BO", "ボ");

        kanaMap.put("PA", "パ");
        kanaMap.put("PI", "ピ");
        kanaMap.put("PU", "プ");
        kanaMap.put("PE", "ペ");
        kanaMap.put("PO", "ポ");

        kanaMap.put("KYA", "キャ");
        kanaMap.put("KYI", "キ");
        kanaMap.put("KYU", "キュ");
        kanaMap.put("KYE", "ケ");
        kanaMap.put("KYO", "キョ");

        kanaMap.put("SHA", "シャ");
        kanaMap.put("SHU", "シュ");
        kanaMap.put("SHE", "シェ");
        kanaMap.put("SHO", "ショ");
        kanaMap.put("SYA", "シャ");
        kanaMap.put("SYI", "シ");
        kanaMap.put("SYU", "シュ");
        kanaMap.put("SYE", "シェ");
        kanaMap.put("SYO", "ショ");

        kanaMap.put("CHA", "チャ");
        kanaMap.put("TI", "ティ");
        kanaMap.put("CHU", "チュ");
        kanaMap.put("CHE", "チェ");
        kanaMap.put("CHO", "チョ");

        kanaMap.put("NYA", "ニャ");
        kanaMap.put("NYI", "ニ");
        kanaMap.put("NYU", "ニュ");
        kanaMap.put("NYE", "ニェ");
        kanaMap.put("NYO", "ニョ");

        kanaMap.put("HYA", "ヒャ");
        kanaMap.put("HYI", "ヒ");
        kanaMap.put("HYU", "ヒュ");
        kanaMap.put("HYE", "ヒェ");
        kanaMap.put("HYO", "ヒョ");

        kanaMap.put("FA", "ファ");
        kanaMap.put("FI", "フィ");
        kanaMap.put("FE", "フェ");
        kanaMap.put("FO", "フォ");

        kanaMap.put("MYA", "ミャ");
        kanaMap.put("MYI", "ミ");
        kanaMap.put("MYU", "ミュ");
        kanaMap.put("MYE", "ミェ");
        kanaMap.put("MYO", "ミョ");

        kanaMap.put("RYA", "リャ");
        kanaMap.put("RYU", "リュ");
        kanaMap.put("RYO", "リョ");

        kanaMap.put("GYA", "ギャ");
        kanaMap.put("GYU", "ギュ");
        kanaMap.put("GYO", "ギョ");

        kanaMap.put("JA", "ジャ");
        kanaMap.put("JU", "ジュ");
        kanaMap.put("JO", "ジョ");
        kanaMap.put("DI", "ディ");
        kanaMap.put("DU", "ジュ");
        kanaMap.put("DE", "デェ");

        kanaMap.put("ZYA", "ジャ");
        kanaMap.put("ZYI", "ゼィ");
        kanaMap.put("ZYU", "ジュ");
        kanaMap.put("ZYE", "ジェ");
        kanaMap.put("ZYO", "ジョ");

        kanaMap.put("ZHA", "ジャ");
        kanaMap.put("ZHI", "ゼィ");
        kanaMap.put("ZHU", "ジュ");
        kanaMap.put("ZHE", "ジェ");
        kanaMap.put("ZHO", "ジョ");

        kanaMap.put("JA", "ジャ");
        kanaMap.put("JU", "ジュ");
        kanaMap.put("JE", "ジェ");
        kanaMap.put("JO", "ジョ");

        kanaMap.put("BYA", "ビャ");
        kanaMap.put("BYU", "ビュ");
        kanaMap.put("BYO", "ビョ");

        kanaMap.put("PYA", "ピャ");
        kanaMap.put("PYU", "ピュ");
        kanaMap.put("PYO", "ピョ");

    }
}
