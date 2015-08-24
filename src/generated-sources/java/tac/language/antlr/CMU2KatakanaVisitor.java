// Generated from /home/tac/Documents/workspace-sts-3.6.3.RELEASE/e2j-name/src/main/antlr/CMU2Katakana.g4 by ANTLR 4.5.1
package tac.language.antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CMU2KatakanaParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CMU2KatakanaVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CMU2KatakanaParser#convertKatakana}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConvertKatakana(CMU2KatakanaParser.ConvertKatakanaContext ctx);
	/**
	 * Visit a parse tree produced by {@link CMU2KatakanaParser#jwords}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJwords(CMU2KatakanaParser.JwordsContext ctx);
	/**
	 * Visit a parse tree produced by {@link CMU2KatakanaParser#jword}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJword(CMU2KatakanaParser.JwordContext ctx);
}