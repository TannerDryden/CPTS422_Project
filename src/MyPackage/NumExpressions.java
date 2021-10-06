package MyPackage;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class NumExpressions extends AbstractCheck {

  private int expression = 0;

  @Override
  public boolean isCommentNodesRequired()
  {
    return true;
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
  public int[] getDefaultTokens() {
	    return new int[] { TokenTypes.EXPR };
  }


  @Override
  public void beginTree(DetailAST rootAST)
  {
    expression = 0;
  }

  @Override
  public void finishTree(DetailAST rootAST)
  {
	  // log results
	  log(rootAST.getLineNo(),"number of expressions: " + expression);
    
	  // re-initialize values
	  expression = 0;
  }
  
  @Override
  public void visitToken(DetailAST ast) {
	  if(ast.getType() == TokenTypes.EXPR)
	  {
		  // count expressions
		  expression++;	  
	  }
  }
  
}

