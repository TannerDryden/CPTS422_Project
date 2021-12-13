package WhiteBoxCategoryA;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import CategoryA.HalsteadVocabulary;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class HalsteadVocabularyTest {
	@Test
	public void getDefaultTokensTest() {
		HalsteadVocabulary check = new HalsteadVocabulary();
		int[] arr = new int[] { TokenTypes.EXPR, TokenTypes.MOD, TokenTypes.LT, TokenTypes.GT, 
				TokenTypes.BAND, TokenTypes.BOR, TokenTypes.RPAREN, TokenTypes.LPAREN, 
				TokenTypes.EQUAL, TokenTypes.ASSIGN };
		
		assertArrayEquals(check.getDefaultTokens(), arr);
	}
	
	@Test
	public void getAcceptableTokensTest() {
		HalsteadVocabulary check = new HalsteadVocabulary();
		int arr[] = new int [] { TokenTypes.EXPR };
		
		assertArrayEquals(check.getAcceptableTokens(), arr);
	}
	
	@Test
	public void getRequiredTokensTest() {
		HalsteadVocabulary check = new HalsteadVocabulary();
		int arr[] = new int [] {};
		assertArrayEquals(check.getRequiredTokens(), arr);
	}
	
	@Test
	public void beginTreeTest() {
		HalsteadVocabulary check = new HalsteadVocabulary();
		HalsteadVocabulary spyCheck = Mockito.spy(check);
		DetailAST mockAST = mock(DetailAST.class);
		spyCheck.beginTree(mockAST);
		spyCheck.beginTree(mockAST);
		spyCheck.beginTree(mockAST);
		verify(spyCheck, times(3)).beginTree(mockAST);
	}
	
	@Test
	public void visitTokenTest() {
		HalsteadVocabulary check = new HalsteadVocabulary();
		HalsteadVocabulary spyCheck = Mockito.spy(check);
		DetailAST mockAST = mock(DetailAST.class);
		
		// verify countOperatorsOperands is not called
		when(mockAST.getType()).thenReturn(TokenTypes.ABSTRACT);
		spyCheck.visitToken(mockAST);
		verify(spyCheck, times(0)).countOperatorsOperands(mockAST);
		
		// verify countOperatorsOperands is called
		when(mockAST.getType()).thenReturn(TokenTypes.EXPR);
		spyCheck.visitToken(mockAST);
		verify(spyCheck,times(1)).countOperatorsOperands(mockAST);
	}
	
	@Test
	public void finishTreeTest() {
		
	}
	
	@Test
	public void checkIfOperatorOperandTest() {
		HalsteadVocabulary check = new HalsteadVocabulary();
		HalsteadVocabulary spyCheck = Mockito.spy(check);
		DetailAST mockAST = mock(DetailAST.class);
		
		when(mockAST.getType()).thenReturn(TokenTypes.MOD);
		check.checkIfOperatorOperand(mockAST);
		assertEquals(1, check.halsteadVocab);
		check.halsteadVocab -= 1;
		
		when(mockAST.getType()).thenReturn(TokenTypes.LT);
		check.checkIfOperatorOperand(mockAST);
		assertEquals(1, check.halsteadVocab);
		check.halsteadVocab -= 1;
		
		when(mockAST.getType()).thenReturn(TokenTypes.GT);
		check.checkIfOperatorOperand(mockAST);
		assertEquals(1, check.halsteadVocab);
		check.halsteadVocab -= 1;
		
		when(mockAST.getType()).thenReturn(TokenTypes.BAND);
		check.checkIfOperatorOperand(mockAST);
		assertEquals(1, check.halsteadVocab);
		check.halsteadVocab -= 1;
		
		when(mockAST.getType()).thenReturn(TokenTypes.BOR);
		check.checkIfOperatorOperand(mockAST);
		assertEquals(1, check.halsteadVocab);
		check.halsteadVocab -= 1;
		
		when(mockAST.getType()).thenReturn(TokenTypes.RPAREN);
		check.checkIfOperatorOperand(mockAST);
		assertEquals(1, check.halsteadVocab);
		check.halsteadVocab -= 1;
		
		when(mockAST.getType()).thenReturn(TokenTypes.LPAREN);
		check.checkIfOperatorOperand(mockAST);
		assertEquals(1, check.halsteadVocab);
		check.halsteadVocab -= 1;
		
		when(mockAST.getType()).thenReturn(TokenTypes.EQUAL);
		check.checkIfOperatorOperand(mockAST);
		assertEquals(1, check.halsteadVocab);
		check.halsteadVocab -= 1;
		
		when(mockAST.getType()).thenReturn(TokenTypes.ASSIGN);
		check.checkIfOperatorOperand(mockAST);
		assertEquals(1, check.halsteadVocab);
		check.halsteadVocab -= 1;
		
		// ensure false for unusable tokens
		when(mockAST.getType()).thenReturn(TokenTypes.ABSTRACT);
		check.checkIfOperatorOperand(mockAST);
		assertEquals(0, check.halsteadVocab);
	}
}