package CategoryB;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class NumTotalCommentsTest {
	
	@Test
	public void isCommentNodesRequiredTest() {
		NumTotalComments check = new NumTotalComments();
		assertTrue(check.isCommentNodesRequired());
	}
	
	@Test
	public void getDefaultTokensTest() {
		NumTotalComments check = new NumTotalComments();
		int[] arr = new int[] { TokenTypes.SINGLE_LINE_COMMENT, TokenTypes.BLOCK_COMMENT_BEGIN };
		assertArrayEquals(check.getDefaultTokens(), arr);
	}
	
	@Test
	public void getAcceptableTokensTest() {
		NumTotalComments check = new NumTotalComments();
		int[] arr = new int[] { TokenTypes.SINGLE_LINE_COMMENT, TokenTypes.BLOCK_COMMENT_BEGIN };
		assertArrayEquals(check.getAcceptableTokens(), arr);
	}
	
	@Test
	public void getRequiredTokensTest() {
		NumTotalComments check = new NumTotalComments();
		int arr[] = new int [] {};
		assertArrayEquals(check.getRequiredTokens(), arr);

	}
	
	@Test
	public void beginTreeTest() {
		NumTotalComments check = new NumTotalComments();
		NumTotalComments spyCheck = Mockito.spy(check);
		DetailAST mockAST = mock(DetailAST.class);
		spyCheck.beginTree(mockAST);
		spyCheck.beginTree(mockAST);
		spyCheck.beginTree(mockAST);
		verify(spyCheck, times(3)).beginTree(mockAST);
		assertEquals(0, spyCheck.totalComment);
	}
	
	@Test
	public void finishTreeTest() {

	}
	
	@Test
	public void visitTokenTest() {
		NumTotalComments check = new NumTotalComments();
		NumTotalComments spyCheck = Mockito.spy(check);
		DetailAST mockAST = mock(DetailAST.class);
		
		// if statement
		when(mockAST.getType()).thenReturn(TokenTypes.SINGLE_LINE_COMMENT);
		spyCheck.visitToken(mockAST);
		verify(spyCheck, times(1)).visitToken(mockAST);
		assertEquals(1, spyCheck.totalComment);
		spyCheck.totalComment -= 1;
		
		// else if statement
		when(mockAST.getType()).thenReturn(TokenTypes.BLOCK_COMMENT_BEGIN);
		when(mockAST.getChildCount(TokenTypes.COMMENT_CONTENT)).thenReturn(10);
		spyCheck.visitToken(mockAST);
		spyCheck.visitToken(mockAST);
		verify(spyCheck, times(3)).visitToken(mockAST);
		assertEquals(20, spyCheck.totalComment);
		
		// else
		when(mockAST.getType()).thenReturn(TokenTypes.ABSTRACT);
		spyCheck.visitToken(mockAST);
		spyCheck.visitToken(mockAST);
		spyCheck.visitToken(mockAST);
		verify(spyCheck, times(6)).visitToken(mockAST);
		assertEquals(20, spyCheck.totalComment);
	}
}