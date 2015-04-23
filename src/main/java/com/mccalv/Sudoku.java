/**
 * 
 */
package com.mccalv;

import java.util.HashSet;
import java.util.Set;

import com.mccalv.util.SudokuViewUtils;

/**
 * A class representing a 3X3 Sudoku board as the following:
 * 
 * <pre>
 *  6 | 1 | 2   3 | 5 | 8   7 | 4 | 9  
 *  3 | 5 | 8   7 | 4 | 9   6 | 1 | 2  
 *  7 | 4 | 9   6 | 1 | 2   3 | 5 | 8  
 * 
 *  1 | 2 | 3   5 | 8 | 7   4 | 9 | 6  
 *  5 | 8 | 7   4 | 9 | 6   1 | 2 | 3  
 *  4 | 9 | 6   1 | 2 | 3   5 | 8 | 7  
 * 
 *  2 | 3 | 5   8 | 7 | 4   9 | 6 | 1  
 *  8 | 7 | 4   9 | 6 | 1   2 | 3 | 5  
 *  9 | 6 | 1   2 | 3 | 5   8 | 7 | 4
 * </pre>
 * 
 * The number of the class are yield in the a bi dimensional matrix. To check
 * the validity of a Sudoku according to its basic rule of non repeated number
 * per row or column (and square), another two array are stored in this object:
 * 
 * <pre>
 * numbersSetPerRow = new boolean[MAX_VALID_SUDOKU_NUMBER][MAX_VALID_SUDOKU_NUMBER];
 * numbersSetPerColumns = new boolean[MAX_VALID_SUDOKU_NUMBER][MAX_VALID_SUDOKU_NUMBER];
 * 
 * </pre>
 * 
 * For each row the column of the array is set to true if the corresponding
 * number (column index + 1) is already present. This structure required a
 * minimum extra memory (162 bit) but allows to check the validity of the sudoku
 * in 0(1) time.
 * 
 * @author mccalv
 * 
 */
public class Sudoku {

	public static final String SEPARATOR = ",";

	public static final int MIN_VALID_SUDOKU_NUMBER = 1;
	public static final int MAX_VALID_SUDOKU_NUMBER = 9;

	public static final int SIZE_OF_EACH_SQUARE = 3;
	public static final int VALUE_FOR_EMPTY_CELL = 0;

	public static final Set<Integer> SUDOKU_NUMBERS = new HashSet<Integer>(
			MAX_VALID_SUDOKU_NUMBER);

	/**
	 * Indicate the level of the sudoku: i.e the number or random cells
	 * valorized
	 */
	public enum SudokuLevel {
		EASY(32), MEDIUM(24), DIFFICULT(18);

		private int valorizedCells;

		private SudokuLevel(int valorizedCells) {
			this.valorizedCells = valorizedCells;
		}

		/**
		 * @return the valorizedCells
		 */
		public int getValorizedCells() {
			return valorizedCells;
		}

	}

	/**
	 * Initializes populates the set of valid number and the standard sum for
	 * the row or cells
	 */
	static {
		for (int i = MIN_VALID_SUDOKU_NUMBER; i <= MAX_VALID_SUDOKU_NUMBER; i++) {
			SUDOKU_NUMBERS.add(i);

		}
	}

	private Integer[][] matrix = new Integer[MAX_VALID_SUDOKU_NUMBER][MAX_VALID_SUDOKU_NUMBER];

	/**
	 * A minimal control matrix to check for each row of the sudoku matrix if
	 * the number corresponding to the column or row are already be set As
	 * example:
	 * 
	 * <pre>
	 * [1 3 null 4 null 5 null 9 null]
	 * [1,0,1,1,0,0,0,0,1]
	 * </pre>
	 */
	private boolean[][] numbersSetPerRow = new boolean[MAX_VALID_SUDOKU_NUMBER][MAX_VALID_SUDOKU_NUMBER];
	private boolean[][] numbersSetPerColumns = new boolean[MAX_VALID_SUDOKU_NUMBER][MAX_VALID_SUDOKU_NUMBER];

	public static boolean isValidSudokuNumber(int number) {
		return (number >= MIN_VALID_SUDOKU_NUMBER - 1 && number <= MAX_VALID_SUDOKU_NUMBER);

	}

	/**
	 * Add number to Matrix. Checks if the number is present on the row and
	 * column return false. Otherwise set the numb
	 */
	public boolean addNumberToSudokuMatrix(int row, int col, int n) {

		

		if (n > 0
				&& (numbersSetPerRow[row][n - 1] || numbersSetPerColumns[col][n - 1])) {
			return false;
		} else {
			matrix[row][col] = n;
			if (n > 0) {
				numbersSetPerRow[row][n - 1] = true;
				numbersSetPerColumns[col][n - 1] = true;
			}
			return true;
		}
	}

	/**
	 * @return the matrix
	 */
	public Integer[][] getMatrix() {
		return matrix;
	}

	/**
	 * @param matrix
	 *            the matrix to set
	 */
	public void setMatrix(Integer[][] matrix) {
		this.matrix = matrix;
	}

	public boolean isValid() {
		// reinitialize the check matrix in case they has been already used to
		// stream the number in the sudoku
		numbersSetPerRow = new boolean[MAX_VALID_SUDOKU_NUMBER][MAX_VALID_SUDOKU_NUMBER];
		numbersSetPerColumns = new boolean[MAX_VALID_SUDOKU_NUMBER][MAX_VALID_SUDOKU_NUMBER];

		for (int row = 0; row < MAX_VALID_SUDOKU_NUMBER; row++) {
			for (int col = 0; col < MAX_VALID_SUDOKU_NUMBER; col++) {
				int n = matrix[row][col];
				if (n > VALUE_FOR_EMPTY_CELL
						&& (numbersSetPerRow[row][n - 1] || numbersSetPerColumns[col][n - 1])) {
					return false;
				} else {
					if (n > VALUE_FOR_EMPTY_CELL) {
						numbersSetPerRow[row][n - 1] = true;
						numbersSetPerColumns[col][n - 1] = true;
					}
				}
			}
		}

		return true;

	}

	@Override
	public String toString() {
		return SudokuViewUtils.printToASCII(this);
	}

}
