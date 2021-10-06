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