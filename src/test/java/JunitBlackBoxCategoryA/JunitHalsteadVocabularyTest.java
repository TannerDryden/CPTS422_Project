package JunitBlackBoxCategoryA;

import static org.junit.jupiter.api.Assertions.assertTrue;

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

import CategoryA.HalsteadVocabulary;

public class JunitHalsteadVocabularyTest {
	
	int results = 0;
	
	@Test
	void test1() throws IOException, CheckstyleException {
		// Build File
		String filePath = "src/test/java/CategoryATestFiles/";
		File file = new File(filePath + "HalsteadVocabularyTest1.java");
		FileText ft = new FileText(file,"UTF-8");
		FileContents fc = new FileContents(ft);

		// Fill AST with FileContents
		DetailAST root = JavaParser.parse(fc);
		
		// Initialize Intended Check
		HalsteadVocabulary check = new HalsteadVocabulary();
		
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
		String filePath = "src/test/java/CategoryATestFiles/";
		File file = new File(filePath + "HalsteadVocabularyTest2.java");
		FileText ft = new FileText(file,"UTF-8");
		FileContents fc = new FileContents(ft);

		// Fill AST with FileContents
		DetailAST root = JavaParser.parse(fc);
		
		// Initialize Intended Check
		HalsteadVocabulary check = new HalsteadVocabulary();
		
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
			  if(a.getType() == TokenTypes.EXPR)
			  {	  
				  countOperatorsOperands(a);
			  }
			b.visitToken(a);
			helper(b,a.getFirstChild());
			a = a.getNextSibling();
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
		  
		  // count unique operators
		  if(check == TokenTypes.MOD || check == TokenTypes.LT || check == TokenTypes.GT ||
				  check == TokenTypes.BAND || check == TokenTypes.BOR)
		  {
			  results++;
		  }
	  }
}
