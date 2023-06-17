package dao;

public class GradingVO {
	
	private String alphabet;
	private int low;
	private int high;
	
	
	public void setAlphabet(String alphabet) {
		this.alphabet = alphabet;
	}
	public void setLow(int low) {
		this.low = low;
	}
	public void setHigh(int high) {
		this.high = high;
	}
	public String getAlphabet() {
		return alphabet;
	}
	public int getLow() {
		return low;
	}
	public int getHigh() {
		return high;
	}

}
