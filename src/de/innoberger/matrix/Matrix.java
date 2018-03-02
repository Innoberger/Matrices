package de.innoberger.matrix;

import java.text.MessageFormat;

public class Matrix {

	/**
	 * 1st dimension: rows, 2nd dimension: columns
	 */
	protected double matrix[][];

	/**
	 * Creates a new Matrix object.
	 * 
	 * @param width
	 *            Columns of the matrix
	 * @param height
	 *            Rows of the matrix
	 */
	public Matrix(int width, int height) throws Exception {
		if (width < 1 || height < 1) {
			try {
				throw new Exception("Matrix must be at least 1x1");
			} catch (Exception e) {
				e.printStackTrace();
			}

			return;
		}

		this.matrix = new double[height][width];
		this.fillUpWithZeroes();
	}

	/**
	 * Sets the elements.
	 * 
	 * @param elements
	 *            Two-dimensional array of matrix elements. Length of first
	 *            dimension must match the given matrix height, length of second
	 *            dimension must match the given matrix width.
	 */
	public void setElements(double elements[][]) throws Exception {
		if (elements.length != this.getHeight()) {
			try {
				throw new Exception("Amount of rows (" + elements.length + ") must match given matrix height ("
						+ this.getHeight() + ")");
			} catch (Exception e) {
				e.printStackTrace();
			}

			return;
		}

		for (int row = 0; row < elements.length; row++) {
			if (elements[row].length != this.getWidth()) {
				try {
					throw new Exception("Amount of columns (" + elements[row].length
							+ ") does not match given matrix height (" + this.getWidth() + ") in row " + (row + 1));
				} catch (Exception e) {
					e.printStackTrace();
				}

				return;
			}
		}

		this.matrix = elements;
	}

	public void setElement(int row, int col, double value) throws Exception {
		if (row < 1 || row > this.getHeight()) {
			throw new Exception("Row number (" + row + ") must be between 1 and the height of the matrix ("
					+ this.getHeight() + ")");
		} else if (col < 1 || col > this.getWidth()) {
			throw new Exception("Column number (" + col + ") must be between 1 and the size of the matrix ("
					+ this.getWidth() + ")");
		} else {
			this.matrix[row - 1][col - 1] = value;
		}
	}

	public void multiply(double factor) {
		for (int row = 0; row < this.getHeight(); row++) {
			for (int col = 0; col < this.getWidth(); col++) {
				this.matrix[row][col] *= factor;
			}
		}
	}

	/**
	 * Replaces every empty element with zero.
	 */
	private void fillUpWithZeroes() {
		for (int row = 0; row < this.getHeight(); row++) {
			for (int col = 0; col < this.getWidth(); col++) {
				if (this.matrix[row][col] != 0.0) {
					this.matrix[row][col] = 0.0;
				}
			}
		}
	}

	/**
	 * Prints out the matrix to console in a readable format.
	 */
	public void print() {
		for (int row = 0; row < this.getHeight(); row++) {
			StringBuilder sb = new StringBuilder();
			sb.append("|   ");

			for (int col = 0; col < this.getWidth(); col++) {
				sb.append(this.matrix[row][col] + "   ");
			}

			sb.append("|");
			System.out.println(sb.toString());
		}
	}

	/**
	 * Extracts a specific row as array.
	 * 
	 * @param rowNumber
	 *            Number of row (STARTING WITH 1!!!)
	 * @return
	 */
	public double[] getRowAsArray(int rowNumber) {
		return this.matrix[rowNumber - 1];
	}

	/**
	 * Extracts a specific column as array.
	 * 
	 * @param colNumber
	 *            Number of column (STARTING WITH 1!!!)
	 * @return
	 */
	public double[] getColumnAsArray(int colNumber) {
		double[] result = new double[this.getHeight()];

		for (int row = 1; row <= this.getHeight(); row++) {
			for (int col = 1; col <= this.getWidth(); col++) {
				if (col == colNumber)
					result[row - 1] = this.matrix[row - 1][col - 1];
			}
		}

		return result;
	}

	/**
	 * Multiplies this matrix with a second matrix.
	 *
	 * @param second
	 *            second matrix
	 *
	 * @return result after multiplication
	 */
	public Matrix multiplyWithMatrix(Matrix second) throws Exception {
		Matrix resultMatrix;
		double[][] result;
		int xColumns, xRows, yColumns, yRows;

		xRows = this.getHeight();
		xColumns = this.getWidth();
		yRows = second.getHeight();
		yColumns = second.getWidth();
		result = new double[xRows][yColumns];
		resultMatrix = new Matrix(xRows, yColumns);

		if (xColumns != yRows) {
			throw new Exception(MessageFormat.format("Matrices don't match: {0} != {1}.", xColumns, yRows));
		}

		for (int i = 0; i < xRows; i++) {
			for (int j = 0; j < yColumns; j++) {
				for (int k = 0; k < xColumns; k++) {
					result[i][j] += (this.matrix[i][k] * second.matrix[k][j]);
				}
			}
		}
		
		resultMatrix.setElements(result);

		return resultMatrix;
	}

	////////////////////////////////////////////////

	public int getHeight() {
		return this.matrix.length;
	}

	public int getWidth() {
		return this.matrix[0].length;
	}

	/**
	 * 
	 * @return true if height is equal to width
	 */
	public boolean isSquareMatrix() {
		return this.getHeight() == this.getWidth();
	}

}
