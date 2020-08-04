package uril.patternmatchingstring;

public class WordMetric {

	private String pattern;
	private int lineNumber;
	private int startPosition;
	private int endPosition;
	
	
	
	public WordMetric(String pattern, int lineNumber, int startPosition, int endPosition) {
		super();
		this.pattern = pattern;
		this.lineNumber = lineNumber;
		this.startPosition = startPosition;
		this.endPosition = endPosition;
	}
	
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	
	public String getPattern() {
		return pattern;
	}
	
	public int getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}
	public int getStartPosition() {
		return startPosition;
	}
	public void setStartPosition(int startPosition) {
		this.startPosition = startPosition;
	}
	public int getEndPosition() {
		return endPosition;
	}
	public void setEndPosition(int endPosition) {
		this.endPosition = endPosition;
	}
	
	
}
