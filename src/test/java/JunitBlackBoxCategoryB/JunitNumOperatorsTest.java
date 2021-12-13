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

import CategoryB.NumOperators;



class JunitNumOperatorsTest {

	int results = 0;
	
	@Test
	void test() throws IOException, CheckstyleException {
		// Build File
		String filePath = "src/test/java/CategoryBTestFiles/";
		File file = new File(filePath + "NumOperatorsTest1.java");
		FileText ft = new FileText(file,"UTF-8");
		FileContents fc = new FileContents(ft);	
		
		// Fill AST with FileContents
		DetailAST root = JavaParser.parse(fc);
		
		// Initialize Intended Check
		NumOperators check = new NumOperators();
		
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
		File file = new File(filePath + "NumOperatorsTest2.java");
		FileText ft = new FileText(file,"UTF-8");
		FileContents fc = new FileContents(ft);	
		
		// Fill AST with FileContents
		DetailAST root = JavaParser.parse(fc);
		
		// Initialize Intended Check
		NumOperators check = new NumOperators();
		
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
		assertTrue(results == 3);
		System.out.println("NumExpression Check Done!");
		
	}
	
	
	public void helper(AbstractCheck b, DetailAST a) {
		while(a != null) {
			int check = a.getType();
			if (check == TokenTypes.PLUS || check == TokenTypes.MINUS || check == TokenTypes.DIV ||
					  check == TokenTypes.STAR || check == TokenTypes.MOD || check == TokenTypes.LT ||
					  check == TokenTypes.GT || check == TokenTypes.BAND || check == TokenTypes.BOR) {
				results++;
			}
			
			b.visitToken(a);
			helper(b,a.getFirstChild());
			a = a.getNextSibling();
		}
	}
}
