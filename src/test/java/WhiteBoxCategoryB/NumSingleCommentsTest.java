package WhiteBoxCategoryB;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import CategoryA.HalsteadLength;
import CategoryB.NumSingleComments;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class NumSingleCommentsTest {

	@Test
	public void isCommentNodesRequiredTest() {
		NumSingleComments check = new NumSingleComments();
		assertTrue(check.isCommentNodesRequired());
	}
	
	@Test
	public void getDefaultTokensTest() {
		NumSingleComments check = new NumSingleComments();
		int[] arr = new int[] { TokenTypes.SINGLE_LINE_COMMENT };
		assertArrayEquals(check.getDefaultTokens(), arr);
	}
	
	@Test
	public void getAcceptableTokensTest() {
		NumSingleComments check = new NumSingleComments();
		int[] arr = new int[] { TokenTypes.SINGLE_LINE_COMMENT };
		assertArrayEquals(check.getAcceptableTokens(), arr);
	}
	
	@Test
	public void getRequiredTokensTest() {
		NumSingleComments check = new NumSingleComments();
		int arr[] = new int [] {};
		assertArrayEquals(check.getRequiredTokens(), arr);

	}
	
	@Test
	public void beginTreeTest() {
		NumSingleComments check = new NumSingleComments();
		NumSingleComments spyCheck = Mockito.spy(check);
		DetailAST mockAST = mock(DetailAST.class);
		spyCheck.beginTree(mockAST);
		spyCheck.beginTree(mockAST);
		spyCheck.beginTree(mockAST);
		verify(spyCheck, times(3)).beginTree(mockAST);
		assertEquals(0, check.singleComment);
	}
	
	@Test
	public void finishTreeTest() {

	}
	
	@Test
	public void visitTokenTest() {
		NumSingleComments check = new NumSingleComments();
		DetailAST mockAST = mock(DetailAST.class);
		check.visitToken(mockAST);
		assertEquals(1,check.singleComment);
		
	}
}