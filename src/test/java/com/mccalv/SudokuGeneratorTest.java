package com.mccalv;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.mccalv.Sudoku.SudokuLevel;

@RunWith(Parameterized.class)
public class SudokuGeneratorTest {
	@Parameterized.Parameters
	public static List<Object[]> data() {
		return Arrays.asList(new Object[1000][0]);
	}

	@Test
	public void testGenerateAndValidateValidSudoku() {
		// Generate a large number of sudoku and checks if each one is valid
		Sudoku s = SudokuGenerator.generateRandomSudoku();
		assertTrue(s.isValid());
		
	}
	@Test
	public void testGenerateaPlaybleEasySudoku() {
		// Generate a large number of sudoku and checks if each one is valid
		Sudoku s = SudokuGenerator.generatePlayableRandomSudoku(SudokuLevel.EASY);
		System.out.println(s);
		assertTrue(s.isValid());
		
	}
	
	@Test
	public void testGenerateaPlaybleMediumSudoku() {
		// Generate a large number of sudoku and checks if each one is valid
		Sudoku s = SudokuGenerator.generatePlayableRandomSudoku(SudokuLevel.MEDIUM);
		System.out.println(s);
		assertTrue(s.isValid());
		
	}
	@Test
	public void testGenerateaPlaybleHardSudoku() {
		// Generate a large number of sudoku and checks if each one is valid
		Sudoku s = SudokuGenerator.generatePlayableRandomSudoku(SudokuLevel.DIFFICULT);
		System.out.println(s);
		assertTrue(s.isValid());
		
	}

	@Test
	public void testGenerateAndInValidateValidSudoku() {

		// Generate a large number of sudoku and checks if they are still valid
		Sudoku s = SudokuGenerator.generateRandomSudoku();
		// System.out.println(SudokuViewUtils.printToASCII(s));
		int randomRow = new Random().nextInt(9);
		int randomCol = new Random().nextInt(9);

		int value = s.getMatrix()[randomRow][randomCol];
		int newValue = 1 + (value) % 8;
		if (!Sudoku.isValidSudokuNumber(newValue)) {
			throw new IllegalArgumentException("Value " + newValue
					+ " is not valid");
		}
		s.getMatrix()[randomRow][randomCol] = newValue;
		assertFalse(s.isValid());

	}
}
