package de.innoberger.les;

import de.innoberger.les.matrix.LinearEquationSystem3x2;
import de.innoberger.les.matrix.LinearEquationSystem4x3;

public class Main {

	public static void main(String[] args) {
		double[][] matrix4x3 = { { 1, 1, 1, 6 }, { 1, -1, 2, 2 }, { 2, 1, -1, 3 } };
		double[][] matrix3x2 = { { 7, -2, 3 }, { 3, 1, 5 } };

		LinearEquationSystem4x3 les_1 = new LinearEquationSystem4x3(matrix4x3);
		LinearEquationSystem3x2 les_2 = new LinearEquationSystem3x2(matrix3x2);

		les_1.print();
		les_1.printSolve();

		les_2.print();
		les_2.printSolve();
	}

}
