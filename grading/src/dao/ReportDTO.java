package dao;

import java.util.ArrayList;

public class ReportDTO {
	
	private String id;
	private String name;
	private String dept;
	private ArrayList<ScoreDTO> scores;
	private int sum;
	private double avg;
	private String fin;
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public ArrayList<ScoreDTO> getScores() {
		return scores;
	}
	public void setScores(ArrayList<ScoreDTO> scores) {
		this.scores = scores;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}

	public double getAvg() {
		return avg;
	}
	public void setAvg(double avg) {
		this.avg = avg;
	}
	public String getFin() {
		return fin;
	}
	public void setFin(String fin) {
		this.fin = fin;
	}
	
	
}
