package CategoryB;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class NumLoopStatements extends AbstractCheck {

  private int loopStatement = 0;

  @Override
  public boolean isCommentNodesRequired()
  {
    return true;
  }
  
  @Override
  public int[] getAcceptableTokens() {
    return new int[] { TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_WHILE, TokenTypes.DO_WHILE };
  }

  @Override
  public int[] getRequiredTokens() {
    return new int[0];
  }

  @Override
  public int[] getDefaultTokens() {
	    return new int[] { TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_WHILE, TokenTypes.DO_WHILE };
  }


  @Override
  public void beginTree(DetailAST rootAST)
  {
    loopStatement = 0;
  }

  @Override
  public void finishTree(DetailAST rootAST)
  {
	  // log results
	  log(rootAST.getLineNo(),"number of loop statements: " + loopStatement);
    
	  // re-initialize values
	  loopStatement = 0;
  }
  
  @Override
  public void visitToken(DetailAST ast) {
	  loopStatement++;
  }
}