package model;


public class MazeReadingException extends Exception {
	private String fileName;
	private String message;
	private int lineNumber;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
		
	}
	public MazeReadingException(String fileName,String message,int lineNumber) {
		this.fileName = fileName;
		this.message = message;
		this.lineNumber = lineNumber;
		
	}

}
