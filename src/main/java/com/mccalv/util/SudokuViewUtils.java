/**
 * 
 */
package com.mccalv.util;

import com.mccalv.Sudoku;

/**
 * @author mccalv
 * 
 */
public class SudokuViewUtils {

	public static String printToASCII(Sudoku s) {
		return printMatrix(s.getMatrix());
	}

	public static String printToASCII(Integer[][] matrix) {
		return printMatrix(matrix);
	}

	private static String printMatrix(Integer[][] matrix) {
		StringBuilder sb = new StringBuilder();
		sb.append(printLine());
		for (int i = 0; i < Sudoku.MAX_VALID_SUDOKU_NUMBER; i++) {
			for (int j = 0; j < Sudoku.MAX_VALID_SUDOKU_NUMBER; j++) {
				sb.append(" ");

				Integer obj = matrix[i][j];
				if (obj > 0) {

					sb.append(obj);
				} else {
					sb.append(" ");

				}
				sb.append(" ");
				if (j != Sudoku.MAX_VALID_SUDOKU_NUMBER - 1
						&& (j != 2 && j != 5)) {
					sb.append("|");
				}
				if ((j + 1) % 3 == 0) {
					sb.append(" ");
				}
			}

			sb.append("\n");
			if (i == 2 || i == 5) {
				sb.append("\n");
				
			}
			
		}
		sb.append(printLine());
		return sb.toString();
	}
	
	private static String printLine(){
		StringBuffer sb = new StringBuffer();
		
		for (int i = 0;i<Sudoku.MAX_VALID_SUDOKU_NUMBER*(1 +Sudoku.SIZE_OF_EACH_SQUARE)-2 ;i++){
			 sb.append("-");
		}
		sb.append("\n");
		return sb.toString();
	}

}
