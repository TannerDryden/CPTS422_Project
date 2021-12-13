package CategoryA;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class HalsteadLength extends AbstractCheck {

	public int halsteadLen = 0;
	
	@Override
	public int[] getDefaultTokens() {
		return new int[] { TokenTypes.EXPR, TokenTypes.NUM_INT, TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT,
	    		TokenTypes.PLUS, TokenTypes.MINUS, TokenTypes.DIV, TokenTypes.STAR, TokenTypes.MOD, 
	    		TokenTypes.LT, TokenTypes.GT, TokenTypes.BAND, TokenTypes.BOR, TokenTypes.RPAREN, 
	    		TokenTypes.LPAREN, TokenTypes.EQUAL, TokenTypes.ASSIGN };
	}
	
	@Override
	public int[] getAcceptableTokens() {
		return new int[] { TokenTypes.EXPR };
	}

	@Override
	public int[] getRequiredTokens() {
		return new int[0];
	}

	  @Override
	  public void beginTree(DetailAST rootAST)
	  {
		  halsteadLen = 0;
	  }

	  @Override
	  public void finishTree(DetailAST rootAST)
	  {  
		  // log results
		  log(rootAST.getLineNo(),"Halstead Length: " + halsteadLen);
	    
		  // re-initialize values
		  halsteadLen = 0;
	  }
	  
	  @Override
	  public void visitToken(DetailAST ast) {
		  if(ast.getType() == TokenTypes.EXPR)
		  {	  
			  countOperatorsOperands(ast);
		  }
	  }
	  
	  // counts operators and operands recursively using the tree of the expression token
	  public void countOperatorsOperands(DetailAST astToken)
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
	  public void checkIfOperatorOperand(DetailAST token)
	  {
		  int check = token.getType();
		  
		  // if token is and operator increment number of operators and halsteadLength
		  if(check == TokenTypes.PLUS || check == TokenTypes.MINUS || check == TokenTypes.DIV ||
				  check == TokenTypes.STAR || check == TokenTypes.MOD || check == TokenTypes.LT ||
				  check == TokenTypes.GT || check == TokenTypes.BAND || check == TokenTypes.BOR)
		  {
			  halsteadLen++;
		  } 	  
		  // if token is and operand increment number of operands
		  else if (check == TokenTypes.NUM_DOUBLE || check == TokenTypes.NUM_INT || check == TokenTypes.NUM_FLOAT)
		  {
			  halsteadLen++;
		  }
		  else
		  {
			  
		  }
	  }
}