package WhiteBoxCategoryB;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import CategoryB.NumLoopStatements;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class NumLoopStatementsTest {
	@Test
	public void isCommentNodesRequiredTest() {
		NumLoopStatements check = new NumLoopStatements();
		assertTrue(check.isCommentNodesRequired());
	}
	
	@Test
	public void getDefaultTokensTest() {
		NumLoopStatements check = new NumLoopStatements();
		int[] arr = new int[] { TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_WHILE, TokenTypes.DO_WHILE };
		assertArrayEquals(check.getDefaultTokens(), arr);
	}
	
	@Test
	public void getAcceptableTokensTest() {
		NumLoopStatements check = new NumLoopStatements();
		int[] arr = new int[] { TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_WHILE, TokenTypes.DO_WHILE };
		assertArrayEquals(check.getAcceptableTokens(), arr);
	}
	
	@Test
	public void getRequiredTokensTest() {
		NumLoopStatements check = new NumLoopStatements();
		int arr[] = new int [] {};
		assertArrayEquals(check.getRequiredTokens(), arr);

	}
	
	@Test
	public void beginTreeTest() {
		NumLoopStatements check = new NumLoopStatements();
		NumLoopStatements spyCheck = Mockito.spy(check);
		DetailAST mockAST = mock(DetailAST.class);
		
		spyCheck.beginTree(mockAST);
		spyCheck.beginTree(mockAST);
		spyCheck.beginTree(mockAST);
		verify(spyCheck, times(3)).beginTree(mockAST);
		assertEquals(0, spyCheck.loopStatement);
	}
	
	@Test
	public void finishTreeTest() {

	}
	
	@Test
	public void visitTokenTest() {
		NumLoopStatements check = new NumLoopStatements();
		NumLoopStatements spyCheck = Mockito.spy(check);
		DetailAST mockAST = mock(DetailAST.class);
		
		spyCheck.visitToken(mockAST);
		spyCheck.visitToken(mockAST);
		verify(spyCheck, times(2)).visitToken(mockAST);
		assertEquals(2, spyCheck.loopStatement);
	}
}