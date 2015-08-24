// Generated from /home/tac/Documents/workspace-sts-3.6.3.RELEASE/e2j-name/src/main/antlr/CMU2Katakana.g4 by ANTLR 4.5.1
package tac.language.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CMU2KatakanaParser}.
 */
public interface CMU2KatakanaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CMU2KatakanaParser#convertKatakana}.
	 * @param ctx the parse tree
	 */
	void enterConvertKatakana(CMU2KatakanaParser.ConvertKatakanaContext ctx);
	/**
	 * Exit a parse tree produced by {@link CMU2KatakanaParser#convertKatakana}.
	 * @param ctx the parse tree
	 */
	void exitConvertKatakana(CMU2KatakanaParser.ConvertKatakanaContext ctx);
	/**
	 * Enter a parse tree produced by {@link CMU2KatakanaParser#jwords}.
	 * @param ctx the parse tree
	 */
	void enterJwords(CMU2KatakanaParser.JwordsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CMU2KatakanaParser#jwords}.
	 * @param ctx the parse tree
	 */
	void exitJwords(CMU2KatakanaParser.JwordsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CMU2KatakanaParser#jword}.
	 * @param ctx the parse tree
	 */
	void enterJword(CMU2KatakanaParser.JwordContext ctx);
	/**
	 * Exit a parse tree produced by {@link CMU2KatakanaParser#jword}.
	 * @param ctx the parse tree
	 */
	void exitJword(CMU2KatakanaParser.JwordContext ctx);
}