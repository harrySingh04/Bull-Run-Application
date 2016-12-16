package application;

public class Coordinate {
private int rowNo;
private int colNo;
private char streetValue;
public Coordinate(int rowNo,int colNo,char streetValue)
{
	this.rowNo = rowNo;
	this.colNo = colNo;
	this.streetValue = streetValue;
	
}

public char getStreetValue() {
	return streetValue;
}
public void setStreetValue(char streetValue) {
	this.streetValue = streetValue;
}
public int getRowNo() {
	return rowNo;
}
public void setRowNo(int rowNo) {
	this.rowNo = rowNo;
}
public int getColNo() {
	return colNo;
}
public void setColNo(int colNo) {
	this.colNo = colNo;
};


}
