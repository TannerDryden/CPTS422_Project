package MyPackage;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class NumSingleComments extends AbstractCheck {

  private int singleComment = 0;

  @Override
  public boolean isCommentNodesRequired()
  {
    return true;
  }
  
  @Override
  public int[] getAcceptableTokens() {
    return new int[] { TokenTypes.SINGLE_LINE_COMMENT };
  }

  @Override
  public int[] getRequiredTokens() {
    return new int[0];
  }

  @Override
  public int[] getDefaultTokens() {
	    return new int[] { TokenTypes.SINGLE_LINE_COMMENT };
  }


  @Override
  public void beginTree(DetailAST rootAST)
  {
    singleComment = 0;
  }

  @Override
  public void finishTree(DetailAST rootAST)
  {
	  // log results
	  log(rootAST.getLineNo(),"number of single line comments: " + singleComment);
    
	  // re-initialize values
	  singleComment = 0;
  }
  
  @Override
  public void visitToken(DetailAST ast) {
	  singleComment++;
  }
}