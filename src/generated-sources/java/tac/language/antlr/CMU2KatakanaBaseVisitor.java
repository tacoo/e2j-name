// Generated from /home/tac/Documents/workspace-sts-3.6.3.RELEASE/e2j-name/src/main/antlr/CMU2Katakana.g4 by ANTLR 4.5.1
package tac.language.antlr;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

/**
 * This class provides an empty implementation of {@link CMU2KatakanaVisitor},
 * which can be extended to create a visitor which only needs to handle a subset
 * of the available methods.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public class CMU2KatakanaBaseVisitor<T> extends AbstractParseTreeVisitor<T> implements CMU2KatakanaVisitor<T> {
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitConvertKatakana(CMU2KatakanaParser.ConvertKatakanaContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitJwords(CMU2KatakanaParser.JwordsContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitJword(CMU2KatakanaParser.JwordContext ctx) { return visitChildren(ctx); }
}