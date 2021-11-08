package CategoryA;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class HalsteadVolume extends AbstractCheck {

  private int halsteadLength = 0;
  private double halsteadVocab = 0;
  private double halsteadVol = 0;
  private int operator = 0;
  private int operand = 0;

  @Override
  public int[] getAcceptableTokens() {
	    return new int[] { TokenTypes.EXPR, TokenTypes.NUM_INT, TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT,
	    		TokenTypes.PLUS, TokenTypes.MINUS, TokenTypes.DIV, TokenTypes.STAR, TokenTypes.MOD, 
	    		TokenTypes.LT, TokenTypes.GT, TokenTypes.BAND, TokenTypes.BOR, TokenTypes.RPAREN, 
	    		TokenTypes.LPAREN, TokenTypes.EQUAL, TokenTypes.ASSIGN };
  }

  @Override
  public int[] getRequiredTokens() {
	  return new int[0];
  }

  @Override
  public int[] getDefaultTokens() {
	    return new int[] { TokenTypes.EXPR, TokenTypes.NUM_INT, TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT,
	    		TokenTypes.PLUS, TokenTypes.MINUS, TokenTypes.DIV, TokenTypes.STAR, TokenTypes.MOD, 
	    		TokenTypes.LT, TokenTypes.GT, TokenTypes.BAND, TokenTypes.BOR, TokenTypes.RPAREN, 
	    		TokenTypes.LPAREN, TokenTypes.EQUAL, TokenTypes.ASSIGN };
  }

  @Override
  public void beginTree(DetailAST rootAST)
  {
	  halsteadLength = 0;
	  halsteadVocab = 0;
	  halsteadVol = 0;

  }

  @Override
  public void finishTree(DetailAST rootAST)
  {
	  // calculate halstead length, volume, vocabulary, and effort
	  halsteadLength = operator + operand;
	  halsteadVol = halsteadLength * log2(halsteadVocab);
	  
	  // log results
	  log(rootAST.getLineNo(),"Halstead Volume: " + halsteadVol);
    
	  // re-initialize values
	  halsteadLength = 0;
	  halsteadVocab = 0;
	  halsteadVol = 0;

  }
  
  @Override
  public void visitToken(DetailAST ast) {
	  if(ast.getType() == TokenTypes.EXPR)
	  {	  
		  countOperatorsOperands(ast);
	  }
  }
  
  private double log2(double n)
  {
	  double result = (int)(Math.log(n) / Math.log(2));
	  return result;
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
	  
	  // if token is and operator increment number of operators and halsteadLength
	  if(check == TokenTypes.PLUS || check == TokenTypes.MINUS || check == TokenTypes.DIV ||
			  check == TokenTypes.STAR || check == TokenTypes.MOD || check == TokenTypes.LT ||
			  check == TokenTypes.GT || check == TokenTypes.BAND || check == TokenTypes.BOR || 
			  check == TokenTypes.RPAREN || check == TokenTypes.LPAREN || check == TokenTypes.EQUAL ||
			  check == TokenTypes.ASSIGN)
	  {
		  operator++;
	  } 	  
	  // if token is and operand increment number of operands
	  else if (check == TokenTypes.NUM_DOUBLE || check == TokenTypes.NUM_INT || check == TokenTypes.NUM_FLOAT)
	  {
		  operand++;
	  }
	  
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