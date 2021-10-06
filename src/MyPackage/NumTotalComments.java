package MyPackage;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class NumTotalComments extends AbstractCheck {

  private int totalComment = 0;

  @Override
  public boolean isCommentNodesRequired()
  {
    return true;
  }
  
  @Override
  public int[] getAcceptableTokens() {
	    return new int[] { TokenTypes.SINGLE_LINE_COMMENT, TokenTypes.BLOCK_COMMENT_BEGIN };
  }

  @Override
  public int[] getRequiredTokens() {
    return new int[0];
  }

  @Override
  public int[] getDefaultTokens() {
	    return new int[] { TokenTypes.SINGLE_LINE_COMMENT, TokenTypes.BLOCK_COMMENT_BEGIN };
  }


  @Override
  public void beginTree(DetailAST rootAST)
  {
    totalComment = 0;
  }

  @Override
  public void finishTree(DetailAST rootAST)
  {
	  // log results
	  log(rootAST.getLineNo(),"number of total comments: " + totalComment);

	  // re-initialize values
	  totalComment = 0;
  }
  
  @Override
  public void visitToken(DetailAST ast) {
	  if(ast.getType() == TokenTypes.SINGLE_LINE_COMMENT)
	  {
		  totalComment++;
	  }
	  else if(ast.getType() == TokenTypes.BLOCK_COMMENT_BEGIN)
	  {
		totalComment += ast.getChildCount(TokenTypes.COMMENT_CONTENT);
	  }
  }
}