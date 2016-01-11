package tac.language.util;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.LogManager;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import tac.language.App;
import tac.language.antlr.CMU2KatakanaLexer;
import tac.language.antlr.CMU2KatakanaParser;
import tac.language.antlr.CMU2KatakanaParser.ConvertKatakanaContext;

class FileTest {
    public static void main(String[] args) throws Exception {
        try {
            LogManager.getLogManager().readConfiguration(FileTest.class.getResourceAsStream("/logging.properties"));
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }

        App app = new App();
        app.loadDictionary();

        Charset utf8 = Charset.forName("utf8");
        URL resource = FileTest.class.getResource("/english_names.txt");
        Path path = Paths.get(resource.toURI());

        List<String> lines = new ArrayList<>();
        Files.lines(path, utf8)
                .filter(s -> !"".equals(s.trim()))
                .forEach(s -> {
                    List<String> word = app.getWord(s);
                    String[] array = word.toArray(new String[word.size()]);

                    CMU2KatakanaLexer lexer = new CMU2KatakanaLexer(
                            new ANTLRInputStream(Stream.of(array).collect(Collectors.joining(" "))));
                    CommonTokenStream token = new CommonTokenStream(lexer);
                    CMU2KatakanaParser parser = new CMU2KatakanaParser(token);
                    ConvertKatakanaContext context = parser.convertKatakana();

                    String result = String.format("%s\t%s\t%s"
                            , s
                            , Arrays.toString(array)
                            , context.result);
                    System.out.println(result);
                    lines.add(result);
                });
        Files.write(Paths.get("./sample/test.log"), lines, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
        app.close();

    }
}
