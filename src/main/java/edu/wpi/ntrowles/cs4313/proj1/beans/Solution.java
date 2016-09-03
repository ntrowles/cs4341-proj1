package edu.wpi.ntrowles.cs4313.proj1.beans;

import java.util.List;

/**
 * 
 * @author ntrowles
 *
 * Contains the path and end state for a given solution
 */
public class Solution {
	//member data
	protected List<String> path;   //path of solution
	protected double endNum;       //calculated number after path

	//getters and setters
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
	
	//constructor
	public Solution(List<String> path, double endNum){
		this.path = path;
		this.endNum = endNum;
	}
}
