package JunitBlackBoxCategoryB;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.DefaultContext;
import com.puppycrawl.tools.checkstyle.JavaParser;
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.FileText;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import CategoryB.NumTotalComments;

class JunitNumTotalCommentsTest {

	int results = 0;
	
	@Test
	void test1() throws IOException, CheckstyleException {
		// Build File
		String filePath = "src/test/java/CategoryBTestFiles/";
		File file = new File(filePath + "NumTotalCommentsTest1.java");
		FileText ft = new FileText(file,"UTF-8");
		FileContents fc = new FileContents(ft);	
		
		// Fill AST with FileContents
		DetailAST root = JavaParser.parseFile(file, JavaParser.Options.WITH_COMMENTS);
		
		// Initialize Intended Check
		NumTotalComments check = new NumTotalComments();
		
		// Configure Check
		check.configure(new DefaultConfiguration("Local"));
		check.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		check.beginTree(root);
		
		// Visit Each Token in Tree
		helper(check,root);
		
		// Complete tree and display intended logs to user.
		check.finishTree(root);
		
		
		// Verify Results
		assertTrue(results == 0);
		System.out.println("NumExpression Check Done!");
		
	}
	
	@Test
	void test2() throws IOException, CheckstyleException {
		// Build File
		String filePath = "src/test/java/CategoryBTestFiles/";
		File file = new File(filePath + "NumTotalCommentsTest2.java");
		FileText ft = new FileText(file,"UTF-8");
		FileContents fc = new FileContents(ft);	
		
		// Fill AST with FileContents
		DetailAST root = JavaParser.parseFile(file, JavaParser.Options.WITH_COMMENTS);
		
		// Initialize Intended Check
		NumTotalComments check = new NumTotalComments();
		
		// Configure Check
		check.configure(new DefaultConfiguration("Local"));
		check.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		check.beginTree(root);
		
		// Visit Each Token in Tree
		helper(check,root);
		
		// Complete tree and display intended logs to user.
		check.finishTree(root);
		
		
		// Verify Results
		assertTrue(results == 2);
		System.out.println("NumExpression Check Done!");
		
	}
	
	public void helper(AbstractCheck b, DetailAST a) {
		while(a != null) {
			  if(a.getType() == TokenTypes.SINGLE_LINE_COMMENT)
			  {
				  results++;
			  }
			  else if(a.getType() == TokenTypes.BLOCK_COMMENT_BEGIN)
			  {
				results += a.getChildCount(TokenTypes.COMMENT_CONTENT);
			  }
			
			b.visitToken(a);
			helper(b,a.getFirstChild());
			a = a.getNextSibling();
		}
	}
}
