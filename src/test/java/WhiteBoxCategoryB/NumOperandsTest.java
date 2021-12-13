package WhiteBoxCategoryB;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import CategoryB.NumOperands;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class NumOperandsTest {
	@Test
	public void isCommentNodesRequiredTest() {
		NumOperands check = new NumOperands();
		assertTrue(check.isCommentNodesRequired());
	}
	
	@Test
	public void getDefaultTokensTest() {
		NumOperands check = new NumOperands();
		int[] arr = new int[] { TokenTypes.NUM_DOUBLE, TokenTypes.NUM_INT, TokenTypes.NUM_FLOAT };
		assertArrayEquals(check.getDefaultTokens(), arr);
	}
	
	@Test
	public void getAcceptableTokensTest() {
		NumOperands check = new NumOperands();
		int[] arr = new int[] { TokenTypes.NUM_DOUBLE, TokenTypes.NUM_INT, TokenTypes.NUM_FLOAT };
		assertArrayEquals(check.getAcceptableTokens(), arr);
	}
	
	@Test
	public void getRequiredTokensTest() {
		NumOperands check = new NumOperands();
		int arr[] = new int [] {};
		assertArrayEquals(check.getRequiredTokens(), arr);

	}
	
	@Test
	public void beginTreeTest() {
		NumOperands check = new NumOperands();
		NumOperands spyCheck = Mockito.spy(check);
		DetailAST mockAST = mock(DetailAST.class);
		spyCheck.beginTree(mockAST);
		spyCheck.beginTree(mockAST);
		spyCheck.beginTree(mockAST);
		verify(spyCheck, times(3)).beginTree(mockAST);
		assertEquals(0, spyCheck.operand);
	}
	
	@Test
	public void finishTreeTest() {

	}
	
	@Test
	public void visitTokenTest() {
		NumOperands check = new NumOperands();
		NumOperands spyCheck = Mockito.spy(check);
		DetailAST mockAST = mock(DetailAST.class);
		
		// verify countoperandsOperands is not called
		when(mockAST.getType()).thenReturn(TokenTypes.ABSTRACT);
		spyCheck.visitToken(mockAST);
		verify(spyCheck, times(0)).countOperatorsOperands(mockAST);
		
		// verify countoperandsOperands is called
		when(mockAST.getType()).thenReturn(TokenTypes.EXPR);
		spyCheck.visitToken(mockAST);
		verify(spyCheck,times(1)).countOperatorsOperands(mockAST);
	}
	
	@Test
	public void countoperandOperandsTest() {

	}
	
	@Test
	public void checkIfoperandOperand() {
		NumOperands check = new NumOperands();
		DetailAST mockAST = mock(DetailAST.class);
		
		// ensure true for all usable tokens
		when(mockAST.getType()).thenReturn(TokenTypes.NUM_DOUBLE);
		check.checkIfOperatorOperand(mockAST);
		assertEquals(1, check.operand);
		check.operand -= 1;
		
		when(mockAST.getType()).thenReturn(TokenTypes.NUM_FLOAT);
		check.checkIfOperatorOperand(mockAST);
		assertEquals(1, check.operand);
		check.operand -= 1;
		
		when(mockAST.getType()).thenReturn(TokenTypes.NUM_INT);
		check.checkIfOperatorOperand(mockAST);
		assertEquals(1, check.operand);
		check.operand -= 1;
		
		// ensure false for unusable tokens
		when(mockAST.getType()).thenReturn(TokenTypes.ABSTRACT);
		check.checkIfOperatorOperand(mockAST);
		assertEquals(0, check.operand);

	}
}