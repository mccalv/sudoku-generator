import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.mccalv.Sudoku;
import com.mccalv.SudokuGenerator;
import com.mccalv.util.SudokuFileUtils;

/**
 * Sudoku Application
 * 
 * @author mccalv
 * 
 */
public class SudokuApp {

	public static void main(String[] args) {
		if (args.length == 0) {

			System.out.println("Welcome to Sudoku App");
			System.out.print(SudokuGenerator.generateRandomSudoku());
			reinitializeInstructions();
		} else {
			validateACsvSudokuFile(args[0]);
		}

	}

	private static void reinitializeInstructions() {
		printInstructions();
		try {
			String readInput = readInput();
			if (readInput.equalsIgnoreCase("a")) {
				System.out.print(SudokuGenerator.generateRandomSudoku());
				reinitializeInstructions();
			} else if (readInput.equalsIgnoreCase("b")) {
				System.out
						.print(SudokuGenerator
								.generatePlayableRandomSudoku(Sudoku.SudokuLevel.MEDIUM));
				reinitializeInstructions();
			} else if (readInput.equalsIgnoreCase("c")) {

				System.out.println("Insert File Path:");
				validateACsvSudokuFile(readInput());
				reinitializeInstructions();

			}
		} catch (Exception e) {
			System.out.println("Invalid Input");
			reinitializeInstructions();
		}
	}

	/**
	 * Parse and validate a sudoku file
	 * 
	 * @param path
	 */
	private static void validateACsvSudokuFile(String path) {

		try {

			Sudoku s = SudokuFileUtils.readFromFile(path);
			System.out.println("VALID SUDOKU");
			System.out.println(s);
		} catch (Exception e) {
			System.out.println("INVALID SUDOKU");
			System.out.println(e.getMessage());
		}
	}

	private static void printInstructions() {
		System.out.println("Please press the following options:");
		System.out.println("[a] Generate a Sudoku matrix ");
		System.out.println("[b] Generate a Sudoku playble matrix");
		System.out.println("[c] Validate a Sudoku file");
	}

	private static String readInput() {
		try {

			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(System.in));
			return bufferedReader.readLine();
		} catch (Exception e) {
			return null;
		}

	}
}
