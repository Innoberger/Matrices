package de.innoberger.matrix;

import java.util.ArrayList;

public class SquareMatrix extends Matrix {

	/**
	 * Creates a new SquareMatrix object
	 * 
	 * @param size
	 *            Columns and rows of the matrix
	 * @throws Exception
	 */
	public SquareMatrix(int size) throws Exception {
		super(size, size);
	}

	/**
	 * Calculates the matrix determinant.
	 * 
	 * @return
	 */
	public double getDeterminat() throws Exception {
		if (!this.isSquareMatrix()) {
			throw new Exception("Matrix must be a square matrix to calculate the determinant.");
		} else {
			SquareMatrix temporary;
			double result = 0;

			if (this.getSize() == 1) {
				result = this.matrix[0][0];
				return (result);
			}

			if (this.getSize() == 2) {
				result = ((this.matrix[0][0] * this.matrix[1][1]) - (this.matrix[0][1] * this.matrix[1][0]));
				return (result);
			}

			for (int i = 0; i < this.getSize(); i++) {
				temporary = new SquareMatrix(this.getSize() - 1);

				for (int j = 1; j < this.getSize(); j++) {
					for (int k = 0; k < this.getSize(); k++) {
						if (k < i) {
							temporary.setElement(j, k + 1, this.matrix[j][k]);
						} else if (k > i) {
							temporary.setElement(j, k, this.matrix[j][k]);
						}
					}
				}

				result += this.matrix[0][i] * Math.pow(-1, (double) i) * temporary.getDeterminat();
			}
			return (result);
		}
	}

	/**
	 * Calculate a cofactor of the matrix.
	 * 
	 * @param i
	 *            Specific row number (STARTS WITH 1!!!)
	 * @param j
	 *            Specific column number (STARTS WITH 1!!!)
	 * @return
	 */
	public double getCofactor(int i, int j) throws Exception {
		if (i < 1 || i > this.getSize()) {
			throw new Exception("Row number (" + i + ") must be between 1 and the size of the square matrix ("
					+ this.getSize() + ")");
		} else if (j < 1 || j > this.getSize()) {
			throw new Exception("Column number (" + j + ") must be between 1 and the size of the square matrix ("
					+ this.getSize() + ")");
		} else {
			double result = 0;
			SquareMatrix temp = new SquareMatrix(this.getSize() - 1);
			ArrayList<double[]> rows = new ArrayList<double[]>();
			double[][] elements;

			for (int row = 1; row <= this.getSize(); row++) {
				if (row != i) {
					ArrayList<Double> cols = new ArrayList<Double>();
					double[] colsArray;
					
					for (int col = 1; col <= this.getSize(); col++) {

						if (col != j) {
							cols.add(this.matrix[row - 1][col - 1]);
						}
					}
					
					colsArray = new double[temp.getSize()];
					
					for (int z = 0; z < cols.size(); z++) {
						colsArray[z] = cols.get(z);
					}
					
					rows.add(colsArray);
				}
			}
			
			elements = new double[temp.getSize()][temp.getSize()];
			
			for (int zz = 0; zz < rows.size(); zz++) {
				elements[zz] = rows.get(zz);
			}

			temp.setElements(elements);

			result = Math.pow(-1, (i + j)) * temp.getDeterminat();

			return result;
		}
	}

	/**
	 * Creates a new Matrix object containing all cofactors of the current matrix.
	 * @return
	 * @throws Exception
	 */
	public SquareMatrix getCofactorMatrix() throws Exception {
		SquareMatrix cfm = new SquareMatrix(this.getSize());

		for (int row = 1; row <= cfm.getSize(); row++) {
			for (int col = 1; col <= cfm.getSize(); col++) {
				cfm.setElement(row, col, this.getCofactor(row, col));
			}
		}

		return cfm;
	}
	
	/**
	 * Creates a new Matrix object. This new matrix is the transposed of the current matrix.
	 * @return
	 * @throws Exception
	 */
	public SquareMatrix getTransposed() throws Exception {
		SquareMatrix transposed = new SquareMatrix(this.getSize());
		double[][] elements = new double[transposed.getSize()][transposed.getSize()];
		
		for (int oldCol = 1; oldCol <= this.getSize(); oldCol++) {
			elements[oldCol - 1] = this.getColumnAsArray(oldCol);
		}
		
		transposed.setElements(elements);
		
		return transposed;
	}
	
	/**
	 * Creates a new Matrix object. This new matrix is the adjunct of the current matrix.
	 * @return
	 * @throws Exception
	 */
	public SquareMatrix getAdjunct() throws Exception {
		return this.getCofactorMatrix().getTransposed();
	}
	
	/**
	 * Creates a new Matrix object. This new matrix is the inversion of the current matrix.
	 * @return
	 * @throws Exception
	 */
	public SquareMatrix getInversion() throws Exception {
		double determinant = this.getDeterminat();
		SquareMatrix adjunct = this.getAdjunct();
		
		adjunct.multiply(1.0 / determinant);
		
		return adjunct;
	}

	////////////////////////////////////////////////

	public int getSize() {
		return this.getWidth();
	}
	
	public boolean isRegular() throws Exception {
		return this.getDeterminat() != 0.0;
	}
	
	public boolean isSingluar() throws Exception {
		return !this.isRegular();
	}

}
