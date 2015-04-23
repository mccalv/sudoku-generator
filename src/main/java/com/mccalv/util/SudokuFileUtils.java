/**
 * 
 */
package com.mccalv.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

import com.mccalv.Sudoku;
import com.mccalv.exception.SudokuException;
import com.mccalv.exception.SudokuException.ExceptionCode;

/**
 * Utility file to read a file and convert it to Sudoku matrix and vice versa
 * 
 * @author mccalv
 * 
 */
public class SudokuFileUtils {

	public static void writeToFile(Sudoku s, String path) {
		FileWriter writer = null;
		try {

			writer = new FileWriter(path);
			Integer matrix[][] = s.getMatrix();
			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix.length; j++) {
					writer.append(Integer.toString(matrix[i][j]));
					char commaOrEndLine = (j == matrix.length - 1) ? '\n' : ',';
					writer.append(commaOrEndLine);
				}
			}

		} catch (Exception e) {
			throw new SudokuException(
					ExceptionCode.PROBLEM_WRITING_SUDOKU_TO_FILE,
					e.getMessage());
		} finally {

			if (writer != null) {
				try {
					writer.flush();
					writer.close();
				} catch (IOException e) {
					throw new SudokuException(
							ExceptionCode.PROBLEM_WRITING_SUDOKU_TO_FILE,
							e.getMessage());
				}
			}
		}

	}

	/**
	 * Reads a file formatted as CSV.
	 * <p>
	 * While reading the file it performs a validation of the file in order to
	 * convert into into a valid {@link Sudoku}
	 * 
	 * @throws SudokuException
	 *             for the possible
	 * 
	 * @return
	 */
	public static Sudoku readFromFile(String csvFile) {
		BufferedReader br = null;
		Sudoku sudoku = new Sudoku();

		try {
			String line;
			br = new BufferedReader(new FileReader(csvFile));
			int r = 0;// Counter for the row
			int c = 0;// Counter for the column
			while ((line = br.readLine()) != null) {
				c = 0;

				// use comma as separator
				StringTokenizer stringTokenizer = new StringTokenizer(line,
						Sudoku.SEPARATOR);
				Integer n = null;
				while (stringTokenizer.hasMoreTokens()) {
					try {
						String nextToken = stringTokenizer.nextToken();
						if(nextToken.equals(" ")) {
							n = 0;
						}else{
							n = Integer.parseInt(nextToken);
						}
					} catch (Exception e) {
						throwInvalidNumber(line);
					}
					if (!Sudoku.isValidSudokuNumber(n)) {
						throwLineParsingException(line);
					}
					if (!sudoku.addNumberToSudokuMatrix(r, c, n)) {
						throwDuplicatedNumberPerRowException(line, n);
					}

					c++;

				}
				if (c != Sudoku.MAX_VALID_SUDOKU_NUMBER) {

					throwLineParsingException(line);
				}
				r++; // increment the counter for the row
			}

		} catch (FileNotFoundException e) {
			throw new SudokuException(ExceptionCode.FILE_NOT_FOUND,
					e.getMessage());
		} catch (IOException e) {
			throw new SudokuException(ExceptionCode.IO_PARSING_PROBLEM,
					e.getMessage());
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					throw new SudokuException(ExceptionCode.IO_PARSING_PROBLEM,
							e.getMessage());
				}
			}
		}

		return sudoku;

	}

	private static void throwLineParsingException(String line) {
		throw new SudokuException(
				ExceptionCode.IRREGULAR_FILE_FORMAT,
				"Problem parsing the line:'"
						+ line
						+ "': Each line should contain 8 number betwen 1,9 separated by '"
						+ Sudoku.SEPARATOR + "'");
	}

	private static void throwDuplicatedNumberPerRowException(String line, int n) {
		throw new SudokuException(ExceptionCode.INVALID_ROW_DUPLICATE_NUMBER,
				"Problem parsing the line:'" + line + "': The number  '" + n
						+ "' is duplicated");
	}
	private static void throwInvalidNumber(String line) {
		throw new SudokuException(ExceptionCode.INVALID_NUMBER,
				"Problem parsing the line:'" + line + "': It contains invalid numbers");
	}
}
