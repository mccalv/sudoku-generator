/**
 * 
 */
package com.mccalv;

import static com.mccalv.SudokuGenerator.generateRandomSudoku;
import static com.mccalv.util.SudokuFileUtils.readFromFile;
import static com.mccalv.util.SudokuFileUtils.writeToFile;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import com.mccalv.util.SudokuFileUtils;

/**
 * Test class for {@link SudokuFileUtils}
 * 
 * @author mccalv
 * 
 */
//@RunWith(Parameterized.class)
public class SudokuFileUtilsTest {
	//@Parameterized.Parameters
	public static List<Object[]> data() {
		return Arrays.asList(new Object[10][0]);
	}

	@Test
	public void testGeneratorOfSudoku() {
		String path = "genSudoku_" + new Random().nextInt() + ".csv";
		writeToFile(generateRandomSudoku(), path);
		Sudoku w = readFromFile(path);
		assertTrue(w.isValid());
		new File(path).delete();

	}
	@Test
	public void testGeneratorOfPlaybleSudoku() {
		String path = "genPlayBleSudoku_" + new Random().nextInt() + ".csv";
		writeToFile(SudokuGenerator.generatePlayableRandomSudoku(Sudoku.SudokuLevel.DIFFICULT), path);
		Sudoku w = readFromFile(path);
		assertTrue(w.isValid());
		new File(path).delete();

	}

	
}
