package JunitBlackBoxCategoryA;

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

import CategoryA.HalsteadDifficulty;

class JunitHalsteadDifficultyTest {
	
	double results = 0;
	int vocab = 0;
	int operand = 0;
			
	@Test
	void test1() throws IOException, CheckstyleException {
		// Build File
		String filePath = "src/test/java/CategoryATestFiles/";
		File file = new File(filePath + "HalsteadDifficultyTest1.java");
		FileText ft = new FileText(file,"UTF-8");
		FileContents fc = new FileContents(ft);

		// Fill AST with FileContents
		DetailAST root = JavaParser.parse(fc);
		
		// Initialize Intended Check
		HalsteadDifficulty check = new HalsteadDifficulty();
		
		// Configure Check
		check.configure(new DefaultConfiguration("Local"));
		check.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		check.beginTree(root);
		
		// Visit Each Token in Tree
		helper(check,root);
		
		// Complete tree and display intended logs to user.
		check.finishTree(root);
		
		if (vocab == 0) {
			results = 0;
		} else {
			results = (vocab / 2) * (operand / vocab);	
		}
		
		// Verify Results
		assertTrue(results == 0);
		System.out.println("NumExpression Check Done!");
	}
	
	@Test
	void test2() throws IOException, CheckstyleException {
		results = 0;
		vocab = 0;
		operand = 0;
		// Build File
		String filePath = "src/test/java/CategoryATestFiles/";
		File file = new File(filePath + "HalsteadDifficultyTest2.java");
		FileText ft = new FileText(file,"UTF-8");
		FileContents fc = new FileContents(ft);

		// Fill AST with FileContents
		DetailAST root = JavaParser.parse(fc);
		
		// Initialize Intended Check
		HalsteadDifficulty check = new HalsteadDifficulty();
		
		// Configure Check
		check.configure(new DefaultConfiguration("Local"));
		check.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		check.beginTree(root);
		
		// Visit Each Token in Tree
		helper(check,root);
		
		// Complete tree and display intended logs to user.
		check.finishTree(root);
		
		if (vocab == 0) {
			results = 0;
		} else {
			results = (vocab / 2) * (operand / vocab);
		}
		
		// Verify Results
		assertTrue(results == 3);
		System.out.println("NumExpression Check Done!");
		
	}
	
	public void helper(AbstractCheck b, DetailAST a) {
		while(a != null) {
			if(a.getType() == TokenTypes.EXPR) {
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
		    
		  // if token is and operand increment number of operands
		  if (check == TokenTypes.NUM_DOUBLE || check == TokenTypes.NUM_INT || check == TokenTypes.NUM_FLOAT)
		  {
			  operand++;
		  }
		  
		  // count unique operators
		  if(check == TokenTypes.MOD || check == TokenTypes.LT || check == TokenTypes.GT ||
				  check == TokenTypes.BAND || check == TokenTypes.BOR)
		  {
			  vocab++;
		  }
	  }
}


