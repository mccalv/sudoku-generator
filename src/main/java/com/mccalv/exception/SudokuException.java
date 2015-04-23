/**
 * 
 */
package com.mccalv.exception;

/**
 * A generic class representing an exception that can occur parsing the file or validating it
 * 
 * @author mccalv
 * 
 */
public class SudokuException extends RuntimeException {

	private static final long serialVersionUID = 1746585386329080235L;
	private ExceptionCode exceptionCode;
	private String message;

	/**
	 * The Generic Exception that can occur 
	 * 
	 * @param exceptionCode
	 * @param message
	 */
	public SudokuException(ExceptionCode exceptionCode, String message) {
		this.exceptionCode = exceptionCode;
		this.message = message;
	}

	public enum ExceptionCode {
		FILE_NOT_FOUND(1),
		IO_PARSING_PROBLEM(2),
		IRREGULAR_FILE_FORMAT(3),
		INVALID_NUMBER(4),
		INVALID_ROW_DUPLICATE_NUMBER(5),
		PROBLEM_WRITING_SUDOKU_TO_FILE(6);
	

		private int exceptionCode;

		private ExceptionCode(int exceptionCode) {
			this.exceptionCode = exceptionCode;
		}

		/**
		 * @return the exceptionCode
		 */
		public int getExceptionCode() {
			return exceptionCode;
		}
	}

	/**
	 * @return the exceptionCode
	 */
	public ExceptionCode getExceptionCode() {
		return exceptionCode;
	}

	/**
	 * @param exceptionCode
	 *            the exceptionCode to set
	 */
	public void setExceptionCode(ExceptionCode exceptionCode) {
		this.exceptionCode = exceptionCode;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
