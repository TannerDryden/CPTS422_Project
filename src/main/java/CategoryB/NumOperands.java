package CategoryB;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class NumOperands extends AbstractCheck {

  private int operand = 0;

  @Override
  public boolean isCommentNodesRequired()
  {
    return true;
  }
  
  @Override
  public int[] getAcceptableTokens() {
	    return new int[] { TokenTypes.NUM_DOUBLE, TokenTypes.NUM_INT, TokenTypes.NUM_FLOAT };
  }

  @Override
  public int[] getRequiredTokens() {
    return new int[0];
  }

  @Override
  public int[] getDefaultTokens() {
	    return new int[] { TokenTypes.NUM_DOUBLE, TokenTypes.NUM_INT, TokenTypes.NUM_FLOAT };
  }


  @Override
  public void beginTree(DetailAST rootAST)
  {
    operand = 0;
  }

  @Override
  public void finishTree(DetailAST rootAST)
  {
	  // log results
	  log(rootAST.getLineNo(),"number of operands: " + operand);

	  // re-initialize values
	  operand = 0;
  }
  
  @Override
  public void visitToken(DetailAST ast) {
	  if(ast.getType() == TokenTypes.EXPR)
	  {
		  // count operators/operands
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
	  
	  // if token is and operand increment number of operands
	  if (check == TokenTypes.NUM_DOUBLE || check == TokenTypes.NUM_INT || check == TokenTypes.NUM_FLOAT)
	  {
		  operand++;
	  }
  }
}