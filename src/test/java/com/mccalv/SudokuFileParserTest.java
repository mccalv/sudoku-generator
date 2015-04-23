package com.mccalv;

import static com.mccalv.util.SudokuFileUtils.readFromFile;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.mccalv.exception.SudokuException;
import com.mccalv.exception.SudokuException.ExceptionCode;

public class SudokuFileParserTest {

	/** A Test for a file not formatted correctly */
	@Test
	public void testSudokuIncorrectNumber() {
		try {
			readFromFile(this.getClass().getClassLoader()
					.getResource("wrongFormat.csv").getPath());
		} catch (SudokuException e) {
			assertEquals(ExceptionCode.INVALID_NUMBER,
					e.getExceptionCode());
		}
	}

	
	@Test
	public void testSudokuIncorrect() {
		try {
			readFromFile(this.getClass().getClassLoader()
					.getResource("incorrectNumbers.csv").getPath());
		} catch (SudokuException e) {
			assertEquals(ExceptionCode.INVALID_ROW_DUPLICATE_NUMBER,
					e.getExceptionCode());
		}
	}
	@Test
	public void testNonExisting() {
		try {
			readFromFile("non existing path");
		} catch (SudokuException e) {
			assertEquals(ExceptionCode.FILE_NOT_FOUND, e.getExceptionCode());
		}
	}
	@Test
	public void testDuplicateNumberPerRow() {
		
		try {
			readFromFile(this.getClass().getClassLoader()
					.getResource("duplicatedValue.csv").getPath());
		} catch (SudokuException e) {
			assertEquals(ExceptionCode.INVALID_ROW_DUPLICATE_NUMBER,
					e.getExceptionCode());
		}
	}
}
