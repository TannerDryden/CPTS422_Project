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

import CategoryA.HalsteadEffort;

public class JunitHalsteadEffortTest {
	int operator = 0;
	int operand = 0;
	int vocab = 0;
	double vol = 0;
	double diff = 0;
	double results = 0;
	
	@Test
	void test1() throws IOException, CheckstyleException {
		// Build File
		String filePath = "src/test/java/CategoryATestFiles/";
		File file = new File(filePath + "HalsteadEffortTest1.java");
		FileText ft = new FileText(file,"UTF-8");
		FileContents fc = new FileContents(ft);

		// Fill AST with FileContents
		DetailAST root = JavaParser.parse(fc);
		
		// Initialize Intended Check
		HalsteadEffort check = new HalsteadEffort();
		
		// Configure Check
		check.configure(new DefaultConfiguration("Local"));
		check.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		check.beginTree(root);
		
		// Visit Each Token in Tree
		helper(check,root);
		
		// Complete tree and display intended logs to user.
		check.finishTree(root);
		
		  vol = (operator + operand) * check.log2(vol);
		  if (vocab == 0) {
			  diff = 0;
		  } else {
			  diff = (vocab / 2) * (operand / vocab);
		  }
		  results = diff * vol;
		
		// Verify Results
		assertTrue(results == 0.0);
		System.out.println("NumExpression Check Done!");
		
	}
	
	@Test
	void test2() throws IOException, CheckstyleException {
		operator = 0;
		operand = 0;
		vocab = 0;
		vol = 0;
		diff = 0;
		results = 0;
		
		// Build File
		String filePath = "src/test/java/CategoryATestFiles/";
		File file = new File(filePath + "HalsteadEffortTest2.java");
		FileText ft = new FileText(file,"UTF-8");
		FileContents fc = new FileContents(ft);

		// Fill AST with FileContents
		DetailAST root = JavaParser.parse(fc);
		
		// Initialize Intended Check
		HalsteadEffort check = new HalsteadEffort();
		
		// Configure Check
		check.configure(new DefaultConfiguration("Local"));
		check.contextualize(new DefaultContext());
		
		// Initialize Local Variables in Check
		check.beginTree(root);
		
		// Visit Each Token in Tree
		helper(check,root);
		
		// Complete tree and display intended logs to user.
		check.finishTree(root);
		
		  vol = (operator + operand) * check.log2(vocab);
		  if (vocab == 0) {
			  diff = 0;
		  } else {
			  diff = (vocab / 2) * (operand / vocab);
		  }
		  results = diff * vol;
		  
		// Verify Results
		assertTrue(results == 33);
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
		  
		  // if token is and operator increment number of operators and halsteadLength
		  if(check == TokenTypes.PLUS || check == TokenTypes.MINUS || check == TokenTypes.DIV ||
				  check == TokenTypes.STAR || check == TokenTypes.MOD || check == TokenTypes.LT ||
				  check == TokenTypes.GT || check == TokenTypes.BAND || check == TokenTypes.BOR)
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
				  check == TokenTypes.BAND || check == TokenTypes.BOR)
		  {
			  vocab++;
		  }
	  }
}
