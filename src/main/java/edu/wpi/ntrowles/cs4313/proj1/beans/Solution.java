package edu.wpi.ntrowles.cs4313.proj1.beans;

import java.util.List;

public class Solution {
	protected List<String> path;   //path of solution
	protected double endNum;       //calculated number after path
	
	public Solution(List<String> path, double endNum){
		this.path = path;
		this.endNum = endNum;
	}

	public List<String> getPath() {
		return path;
	}

	public void setPath(List<String> path) {
		this.path = path;
	}

	public double getEndNum() {
		return endNum;
	}

	public void setEndNum(double endNum) {
		this.endNum = endNum;
	}
	
}
