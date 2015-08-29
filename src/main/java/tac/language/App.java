package tac.language;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.LogManager;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import tac.language.antlr.CMU2KatakanaLexer;
import tac.language.antlr.CMU2KatakanaParser;
import tac.language.antlr.CMU2KatakanaParser.ConvertKatakanaContext;
import edu.cmu.sphinx.linguist.acoustic.Unit;
import edu.cmu.sphinx.linguist.acoustic.UnitManager;
import edu.cmu.sphinx.linguist.dictionary.TextDictionary;
import edu.cmu.sphinx.linguist.dictionary.Word;

public class App {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Please set a argument");
            return;
        }
        try {
            LogManager.getLogManager().readConfiguration(App.class.getResourceAsStream("/logging.properties"));
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
        App app = new App();
        app.loadDictionary();

        String string = Stream.of(args[0].split("[ ]+"))
                .map(w -> {
                    List<String> word = app.getWord(w);
                    String[] array = word.toArray(new String[word.size()]);
                    System.out.println(Arrays.toString(array));
                    CMU2KatakanaLexer lexer = new CMU2KatakanaLexer(
                            new ANTLRInputStream(Stream.of(array).collect(Collectors.joining(" "))));
                    CommonTokenStream token = new CommonTokenStream(lexer);
                    CMU2KatakanaParser parser = new CMU2KatakanaParser(token);
                    ConvertKatakanaContext context = parser.convertKatakana();
                    return context.result;
                })
                .collect(Collectors.joining(" "));
        System.out.println(string);
        app.close();
    }

    private List<String> getWord(String text) {
        Word word = this.dictionary.getWord(text);
        if (word == null
                || word.getPronunciations() == null
                || word.getPronunciations().length == 0) {
            return null;
        }
        Unit[] units = word.getPronunciations()[0].getUnits();
        return Stream.of(units)
                .map(u -> u.toString())
                .collect(Collectors.toList());
    }

    TextDictionary dictionary;
    Map<String, String> cmu2Ipa;

    private void loadDictionary() {
        try {
            TextDictionary dictionary = new TextDictionary(
                    App.class.getResource("/cmudict-en-us.dict")
                    , App.class.getResource("/empty.txt")
                    , null
                    , false
                    , null
                    , new UnitManager()
                    , App.class.getResource("/model.fst.ser")
                    , 2);
            dictionary.allocate();
            this.dictionary = dictionary;
            this.cmu2Ipa = getCmu2IpaMap();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void close() {
        this.dictionary.deallocate();
    }

    List<String> toIPA(List<String> word) {
        return word.stream()
                .map(w -> cmu2Ipa.get(w))
                .collect(Collectors.toList());
    }

    private Map<String, String> getCmu2IpaMap() {
        Map<String, String> cmu2ipa = new HashMap<>();
        cmu2ipa.put("AA", "ɑ");
        cmu2ipa.put("AE", "æ");
        cmu2ipa.put("AH", "ʌ");
        cmu2ipa.put("AO", "ɔ");
        cmu2ipa.put("AW", "aʊ");
        cmu2ipa.put("AY", "aɪ");
        cmu2ipa.put("B", "b");
        cmu2ipa.put("CH", "t͡ʃ");
        cmu2ipa.put("D", "d");
        cmu2ipa.put("DH", "ð");
        cmu2ipa.put("EH", "ɛ");
        cmu2ipa.put("ER", "ɹ̩");
        cmu2ipa.put("EY", "eɪ");
        cmu2ipa.put("F", "f");
        cmu2ipa.put("G", "ɡ");
        cmu2ipa.put("HH", "h");
        cmu2ipa.put("IH", "ɪ");
        cmu2ipa.put("IY", "i");
        cmu2ipa.put("JH", "d͡ʒ");
        cmu2ipa.put("K", "k");
        cmu2ipa.put("L", "l");
        cmu2ipa.put("M", "m");
        cmu2ipa.put("N", "n");
        cmu2ipa.put("NG", "ŋ");
        cmu2ipa.put("OW", "oʊ");
        cmu2ipa.put("OY", "ɔɪ");
        cmu2ipa.put("P", "p");
        cmu2ipa.put("R", "ɹ");
        cmu2ipa.put("S", "s");
        cmu2ipa.put("SH", "ʃ");
        cmu2ipa.put("T", "t");
        cmu2ipa.put("TH", "θ");
        cmu2ipa.put("UH", "ʊ");
        cmu2ipa.put("UW", "u");
        cmu2ipa.put("V", "v");
        cmu2ipa.put("W", "w");
        cmu2ipa.put("Y", "j");
        cmu2ipa.put("Z", "z");
        cmu2ipa.put("ZH", "ʒ");
        return cmu2ipa;
    }
}
