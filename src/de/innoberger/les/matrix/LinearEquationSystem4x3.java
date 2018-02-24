package de.innoberger.les.matrix;

public class LinearEquationSystem4x3 {

	private double[][] les;

	/**
	 * Create a new LES (format: Ax = b)
	 * 
	 * @param newLES
	 *            1st dimension is the rows; 2nd dimension elements x_1, x_2, x_3
	 *            and b of a row
	 */
	public LinearEquationSystem4x3(double[][] newLES) {
		if (newLES.length != 3) {
			System.err.println("This matrix must have three rows.");
			return;
		}

		for (int i = 0; i < 3; i++) {
			if (newLES[i].length != 4) {
				System.err.println("A row must contain four values (x_1, x_2, x_3, b)");
				break;
			}
		}

		this.les = newLES;
	}

	public void print() {
		System.out.println("");

		for (int i = 0; i < 3; i++) {
			System.out.println(this.les[i][0] + "*x_1 + " + this.les[i][1] + "*x_2 + " + this.les[i][2] + "*x_3 = "
					+ this.les[i][3]);
		}

		System.out.println("");
	}

	public double[] solve() {
		double detA = this.les[0][0] * this.les[1][1] * this.les[2][2]
				+ this.les[0][1] * this.les[1][2] * this.les[2][0] + this.les[0][2] * this.les[1][0] * this.les[2][1]
				- this.les[2][0] * this.les[1][1] * this.les[0][2] - this.les[2][1] * this.les[1][2] * this.les[0][0]
				- this.les[2][2] * this.les[1][0] * this.les[0][1];

		double detA_1 = this.les[0][3] * this.les[1][1] * this.les[2][2]
				+ this.les[0][1] * this.les[1][2] * this.les[2][3] + this.les[0][2] * this.les[1][3] * this.les[2][1]
				- this.les[2][3] * this.les[1][1] * this.les[0][2] - this.les[2][1] * this.les[1][2] * this.les[0][3]
				- this.les[2][2] * this.les[1][3] * this.les[0][1];

		double detA_2 = this.les[0][0] * this.les[1][3] * this.les[2][2]
				+ this.les[0][3] * this.les[1][2] * this.les[2][0] + this.les[0][2] * this.les[1][0] * this.les[2][3]
				- this.les[2][0] * this.les[1][3] * this.les[0][2] - this.les[2][3] * this.les[1][2] * this.les[0][0]
				- this.les[2][2] * this.les[1][0] * this.les[0][3];

		double detA_3 = this.les[0][0] * this.les[1][1] * this.les[2][3]
				+ this.les[0][1] * this.les[1][3] * this.les[2][0] + this.les[0][3] * this.les[1][0] * this.les[2][1]
				- this.les[2][0] * this.les[1][1] * this.les[0][3] - this.les[2][1] * this.les[1][3] * this.les[0][0]
				- this.les[2][3] * this.les[1][0] * this.les[0][1];

		return new double[] { detA_1 / detA, detA_2 / detA, detA_3 / detA };
	}

	public void printSolve() {
		double[] solve = this.solve();

		System.out.println("Solved: x_1=" + solve[0] + " x_2=" + solve[1] + " x_3=" + solve[2]);
	}
}
