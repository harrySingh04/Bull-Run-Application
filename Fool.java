package application;

import java.util.ArrayList;

public class Fool {
private int rowPos;
private int colPos;
private int position;
	
	public Fool(int rowPos ,int colPos,int position)
{
	this.rowPos = rowPos;
	this.colPos = colPos;
	this.position = position;
		
}
	

public int getPosition() {
		return position;
	}


	public void setPostion(int position) {
		this.position = position;
	}


public int getRowPos() {
		return rowPos;
	}


	public void setRowPos(int rowPos) {
		this.rowPos = rowPos;
	}


	public int getColPos() {
		return colPos;
	}


	public void setColPos(int colPos) {
		this.colPos = colPos;
	}


public void moveFool(ArrayList<Coordinate> co)
{
	
	for(Coordinate c: co)
	{
		
		
	}
}

}
