package CategoryA;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class HalsteadDifficulty extends AbstractCheck {

  public double halsteadVocab = 0;
  public double halsteadDiff = 0;
  public int operand = 0;

  @Override
  public int[] getAcceptableTokens() {
	    return new int[] { TokenTypes.EXPR };
  }

  @Override
  public int[] getRequiredTokens() {
	  return new int[0];
  }

  @Override
  public int[] getDefaultTokens() {
	    return new int[] { TokenTypes.EXPR };
  }

  @Override
  public void beginTree(DetailAST rootAST)
  {
	  halsteadVocab = 0;
	  halsteadDiff = 0;
  }

  @Override
  public void finishTree(DetailAST rootAST)
  {
	  // calculate halstead length, volume, vocabulary, and effort
	  if (halsteadVocab == 0) {
		  halsteadDiff = 0;
	  } else {
		  halsteadDiff = (halsteadVocab / 2) * (operand / halsteadVocab);
	  }
	  
	  // log results
	  log(rootAST.getLineNo(),"Halstead Difficulty: " + halsteadDiff);
    
	  // re-initialize values
	  halsteadVocab = 0;
	  halsteadDiff = 0;
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
	    
	  // if token is and operand increment number of operands
	  if (check == TokenTypes.NUM_DOUBLE || check == TokenTypes.NUM_INT || check == TokenTypes.NUM_FLOAT)
	  {
		  operand++;
	  }
	  
	  // count unique operators
	  if(check == TokenTypes.MOD || check == TokenTypes.LT || check == TokenTypes.GT ||
			  check == TokenTypes.BAND || check == TokenTypes.BOR)
	  {
		  halsteadVocab++;
	  }
  }
}