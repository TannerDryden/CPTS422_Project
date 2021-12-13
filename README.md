# CPTS422_Project

Assumptions:

	Halstead Checks:
	- Operators/Operands are only found after an expression.
	- halstead length (all operators/operands) includes +, -, /, *, %, <, >, &, |, ), (, ==, =, double, int, float.
	- halstead vocabulary (unique operators) includes %, <, > &, |, ), (, ==, =.

	Comment Checks:
	- Comment blocks (/**/) count as 1 comment. Single line comments count each (//).
	- Operators are only in the EXPR tokens and include +, -, /, *, %, <, >, &, |, ), (, ==, =.
	- Operands are only in the EXPR tokens and include int, double, float.
	- Loop Statements include for, while, do while.

Code and Tests Explanation:

Fault Models:
	
	1. CategoryA:
	- 1.1 HalsteadDifficulty
	- 1.2 HalsteadEffort
	- 1.3 HalsteadLength
	- 1.4 HalsteadVocabulary
	- 1.5 HalsteadVolume

	2. CategoryB:
	- 2.1 NumExpressions
	- 2.2 NumLoopStatements
	- 2.3 NumOperands
	- 2.4 NumOperators
	- 2.5 NumSingleComments
	- 2.6 NumTotalComments	
	
