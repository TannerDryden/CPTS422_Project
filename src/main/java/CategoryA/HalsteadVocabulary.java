package CategoryA;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class HalsteadVocabulary extends AbstractCheck {

	private int halsteadVocab = 0;
	
	@Override
	public int[] getDefaultTokens() {
		return new int[] { TokenTypes.EXPR, TokenTypes.MOD, TokenTypes.LT, TokenTypes.GT, 
				TokenTypes.BAND, TokenTypes.BOR, TokenTypes.RPAREN, TokenTypes.LPAREN, 
				TokenTypes.EQUAL, TokenTypes.ASSIGN };
	}

	@Override
	public int[] getAcceptableTokens() {
		return new int[] { TokenTypes.EXPR, TokenTypes.MOD, TokenTypes.LT, TokenTypes.GT, 
				TokenTypes.BAND, TokenTypes.BOR, TokenTypes.RPAREN, TokenTypes.LPAREN, 
				TokenTypes.EQUAL, TokenTypes.ASSIGN };
	}

	@Override
	public int[] getRequiredTokens() {
		return new int[0];
	}

	  @Override
	  public void beginTree(DetailAST rootAST)
	  {
		  halsteadVocab = 0;
	  }

	  @Override
	  public void finishTree(DetailAST rootAST)
	  {  
		  // log results
		  log(rootAST.getLineNo(),"Halstead Vocabulary: " + halsteadVocab);
	    
		  // re-initialize values
		  halsteadVocab = 0;
	  }
	  
	  @Override
	  public void visitToken(DetailAST ast) {
		  if(ast.getType() == TokenTypes.EXPR)
		  {	  
			  countOperatorsOperands(ast);
		  }
	  }
	  
	  // counts operators and operands recursively using the tree of the expression token
	  private void countOperatorsOperands(DetailAST astToken)
	  { 
		  if (astToken.getFirstChild() != null) 
		  {
			  // recurse down the expression tree
			  countOperatorsOperands(astToken.getFirstChild());
		  }

		  
		  if (astToken.getNextSibling() != null) 
		  {
			  // get next sibling token
			  countOperatorsOperands(astToken.getNextSibling());
		  }
		  
		  // count if token is an operator/operand
		  checkIfOperatorOperand(astToken);
	  }
	  
	  // increments operator/operand if token is operator/operand.
	  private void checkIfOperatorOperand(DetailAST token)
	  {
		  int check = token.getType();
		  
		  // count unique operators
		  if(check == TokenTypes.MOD || check == TokenTypes.LT || check == TokenTypes.GT ||
				  check == TokenTypes.BAND || check == TokenTypes.BOR || 
				  check == TokenTypes.RPAREN || check == TokenTypes.LPAREN ||
				  check == TokenTypes.EQUAL || check == TokenTypes.ASSIGN)
		  {
			  halsteadVocab++;
		  }
	  }
}