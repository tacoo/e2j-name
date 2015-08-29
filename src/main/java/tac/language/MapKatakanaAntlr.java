package tac.language;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

    public static void main(String[] args) {
        prepareMap();
        List<String> altrString = new ArrayList<>();
        altrString.addAll(Stream.of(prefix)
                .filter(p -> "P".equals(p)
                        || "T".equals(p)
                        || "TH".equals(p))
                .flatMap(p -> Stream.concat(
                        Stream.of(base).map(b -> new String[] { p, b })
                        , Stream.of(new String[][] { new String[] { p, null } })))
                .map(ss -> {
                    if (ss[1] == null) {
                        return String.format("  | p=jword %-5s { $katakana = $p.katakana + \"ッ%s\"; }", ss[0], map(ss[0], ss[1]));
                    }
                    return String.format("  | p=jword %-2s %-2s { $katakana = $p.katakana + \"ッ%s\"; }", ss[0], ss[1], map(ss[0], ss[1]));
                })
                .collect(Collectors.toList()));

        altrString.addAll(Stream.of(prefix)
                .flatMap(p -> Stream.concat(
                        Stream.of(base).map(b -> new String[] { p, b })
                        , Stream.of(new String[][] { new String[] { p, null } })))
                .map(ss -> {
                    if (ss[1] == null) {
                        return String.format("  | %-5s { $katakana = \"%s\"; }", ss[0], map(ss[0], ss[1]));
                    }
                    return String.format("  | %-2s %-2s { $katakana = \"%s\"; }", ss[0], ss[1], map(ss[0], ss[1]));
                })
                .collect(Collectors.toList()));
        altrString.addAll(Stream.of(base)
                .map(b -> String.format("  | %-5s { $katakana = \"%s\"; }", b, (map(b, null))))
                .collect(Collectors.toList()));
        altrString.forEach(s -> System.out.println(s));
    }

    private static String map(String p, String b) {
        if (baseKanaMap.containsKey(p)) {
            return Stream.of(baseKanaMap.get(p)).map(s -> kanaMap.get(s)).collect(Collectors.joining(""));
        }
        String[] baseAry = baseKanaMap.get(b);
        if ("B".equals(p)) {
            if (b == null) {
                baseAry = new String[] { "U" };
            }
            return concatKana(p, baseAry);
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
            return concatKana(p, baseAry);
        }
        if ("G".equals(p)) {
            if (b == null) {
                baseAry = new String[] { "U" };
            }
            return concatKana(p, baseAry);
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
            return concatKana(p, baseAry);
        }
        if ("L".equals(p)) {
            if (b == null) {
                baseAry = new String[] { "U" };
            }
            return concatKana("R", baseAry);
        }
        if ("M".equals(p)) {
            if (b == null) {
                baseAry = new String[] { "U" };
            }
            return concatKana(p, baseAry);
        }
        if ("N".equals(p)) {
            if (b == null) {
                return "ン";
            }
            return concatKana(p, baseAry);
        }
        if ("NG".equals(p)) {
            if (b == null) {
                return "ン";
            }
            return concatKana("N", baseAry);
        }
        if ("P".equals(p)) {
            if (b == null) {
                baseAry = new String[] { "U" };
            }
            return concatKana(p, baseAry);
        }
        if ("R".equals(p)) {
            if (b == null) {
                baseAry = new String[] { "U" };
            }
            return concatKana(p, baseAry);
        }
        if ("S".equals(p)) {
            if (b == null) {
                baseAry = new String[] { "U" };
            }
            return concatKana(p, baseAry);
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
            return concatKana(p, baseAry);
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
            return concatKana("B", baseAry);
        }
        if ("W".equals(p)) {
            if (b == null) {
                baseAry = new String[] { "U" };
            }
            return concatKana(p, baseAry);
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
            return concatKana(p, baseAry);
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
        baseKanaMap.put("AE", new String[] { "E" });
        baseKanaMap.put("AH", new String[] { "A", "-" });
        baseKanaMap.put("AO", new String[] { "A", "O" });
        baseKanaMap.put("AW", new String[] { "A", "U" });
        baseKanaMap.put("AY", new String[] { "A", "I" });
        baseKanaMap.put("IH", new String[] { "I", "-" });
        baseKanaMap.put("IY", new String[] { "I" });
        baseKanaMap.put("UH", new String[] { "U" });
        baseKanaMap.put("UW", new String[] { "U" });
        baseKanaMap.put("EH", new String[] { "E" });
        baseKanaMap.put("ER", new String[] { "A", "-" });
        baseKanaMap.put("EY", new String[] { "E", "I" });
        baseKanaMap.put("OW", new String[] { "O", "U" });
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
        kanaMap.put("KYU", "キュ");
        kanaMap.put("KYO", "キョ");

        kanaMap.put("SHA", "シャ");
        kanaMap.put("SHU", "シュ");
        kanaMap.put("SHE", "シェ");
        kanaMap.put("SHO", "ショ");
        kanaMap.put("SYA", "シャ");
        kanaMap.put("SYU", "シュ");
        kanaMap.put("SYE", "シェ");
        kanaMap.put("SYO", "ショ");

        kanaMap.put("CHA", "チャ");
        kanaMap.put("TI", "ティ");
        kanaMap.put("CHU", "チュ");
        kanaMap.put("CHE", "チェ");
        kanaMap.put("CHO", "チョ");

        kanaMap.put("NYA", "ニャ");
        kanaMap.put("NYU", "ニュ");
        kanaMap.put("NYO", "ニョ");

        kanaMap.put("HYA", "ヒャ");
        kanaMap.put("HYU", "ヒュ");
        kanaMap.put("HYO", "ヒョ");

        kanaMap.put("FA", "ファ");
        kanaMap.put("FI", "フィ");
        kanaMap.put("FE", "フェ");
        kanaMap.put("FO", "フォ");

        kanaMap.put("MYA", "ミャ");
        kanaMap.put("MYU", "ミュ");
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
        kanaMap.put("DA", "ジャ");
        kanaMap.put("DI", "ディ");
        kanaMap.put("DU", "ジュ");
        kanaMap.put("DE", "デェ");
        kanaMap.put("DO", "ジョ");

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
