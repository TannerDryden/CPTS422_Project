package WhiteBoxCategoryB;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import CategoryB.NumExpressions;

public class NumExpressionsTest {
	@Test
	public void isCommentNodesRequiredTest() {
		NumExpressions check = new NumExpressions();
		assertTrue(check.isCommentNodesRequired());
	}
	
	@Test
	public void getDefaultTokensTest() {
		NumExpressions check = new NumExpressions();
		int[] arr = new int[] { TokenTypes.EXPR };
		assertArrayEquals(check.getDefaultTokens(), arr);
	}
	
	@Test
	public void getAcceptableTokensTest() {
		NumExpressions check = new NumExpressions();
		int[] arr = new int[] { TokenTypes.EXPR };
		assertArrayEquals(check.getAcceptableTokens(), arr);
	}
	
	@Test
	public void getRequiredTokensTest() {
		NumExpressions check = new NumExpressions();
		int arr[] = new int [] {};
		assertArrayEquals(check.getRequiredTokens(), arr);

	}
	
	@Test
	public void beginTreeTest() {
		NumExpressions check = new NumExpressions();
		NumExpressions spyCheck = Mockito.spy(check);
		DetailAST mockAST = mock(DetailAST.class);
		
		spyCheck.beginTree(mockAST);
		spyCheck.beginTree(mockAST);
		spyCheck.beginTree(mockAST);
		verify(spyCheck, times(3)).beginTree(mockAST);
		assertEquals(0, spyCheck.expression);
	}
	
	@Test
	public void finishTreeTest() {

	}
	
	@Test
	public void visitTokenTest() {
		NumExpressions check = new NumExpressions();
		NumExpressions spyCheck = Mockito.spy(check);
		DetailAST mockAST = mock(DetailAST.class);
		
		// if statement
		when(mockAST.getType()).thenReturn(TokenTypes.EXPR);
		spyCheck.visitToken(mockAST);
		spyCheck.visitToken(mockAST);
		verify(spyCheck, times(2)).visitToken(mockAST);
		assertEquals(2, spyCheck.expression);
		
		// else
		when(mockAST.getType()).thenReturn(TokenTypes.ABSTRACT);
		spyCheck.visitToken(mockAST);
		verify(spyCheck, times(3)).visitToken(mockAST);
		assertEquals(2, spyCheck.expression);
	}
}

