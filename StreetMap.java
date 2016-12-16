package application;


public class StreetMap {



public void setCordinate(Coordinate[][] cordArray){
	
	double random;
	
	for(int rowCounter=0;rowCounter<cordArray.length;rowCounter++)
	{
		
		
	for(int colCounter=0;colCounter<cordArray.length;colCounter++)
	{
		
		
		if(rowCounter==0 && colCounter==1)
		{
			cordArray[rowCounter][colCounter] = new Coordinate(0,1,'S');
			
		}
		
		else if(rowCounter==17 && colCounter==16)
		{
			cordArray[rowCounter][colCounter] = new Coordinate(17,16,'E');
			
		}
		else if(rowCounter==0||rowCounter==17)
		{
			cordArray[rowCounter][colCounter] = new Coordinate(rowCounter,colCounter,'W');

			
		}
		else if(colCounter==0||colCounter==17)
		{
			
			cordArray[rowCounter][colCounter] = new Coordinate(rowCounter,colCounter,'W');
		}
		
		else
		{
			
			random = Math.random();
			if(random<=0.88)
			{
				cordArray[rowCounter][colCounter] = new Coordinate(rowCounter,colCounter,'X');
				
			}
			else
				cordArray[rowCounter][colCounter] = new Coordinate(rowCounter,colCounter,'W');
		}
		
			}

	}	
}

public void move(Fool fool,Bull bull,Coordinate[][] cordArray,int lastRow,int lastCol)
{
	int rowPos,colPos,foolRowPos,foolColPos,random=1;
	boolean check=false,rowMove = false,colMove = false;
	
	boolean repeat;
	do
	{
	repeat = false;
	rowPos = bull.getRowPos();
	colPos =bull.getColPos();
	foolRowPos = fool.getRowPos();
	foolColPos = fool.getColPos();
	
	if(colPos==0)
		colPos=1;
	
	if (foolRowPos-rowPos>0 && foolColPos-colPos==0 && !check)
	{
		
		rowPos = rowPos + 2;
		rowMove = true;
		colMove = false;
		check = true;
		
		
		
		
	}
	else if(foolColPos-colPos>0 && foolRowPos-rowPos==0 && !check)
	{
	
		colPos = colPos + 2;
		check = true;
		rowMove = false;
		colMove = true;
	}
	else if (foolRowPos-rowPos<0 && foolColPos-colPos==0 && !check)
	{
		
		rowPos = rowPos + 2;
		rowMove = true;
		colMove = false;
		check = true;
		
	}
	else if(foolColPos-colPos>0 && foolRowPos-rowPos==0 && !check)
	{
		
		colPos = colPos + 2;
		check = true;
		rowMove = false;
		colMove = true;
	}
	else if(lastRow==rowPos && !check )
	{
	
		if(lastCol<=colPos)
			colPos = colPos-2;
		else
		colPos = colPos+2;
		check = true;
		rowMove = false;
		colMove = true;
		
	}
	else if(lastCol==colPos && !check )
	{
		
		if(lastRow<=rowPos)
		rowPos = rowPos-2;
		else
			rowPos = rowPos+2;
		check = true;
		rowMove = true;
		colMove = false;
		
	}
	
	
	else
	{
		do
		{
		random = (int)(Math.random()*10);
		}while(random <1 || random > 4);
		
		switch(random)
		{
		
		case 1:
		{
			if(colPos==0)
			{
				rowPos =rowPos + 1;
				colPos = 1;
				
				
			}
			else
				rowPos = rowPos + 2;
			
			rowMove = true;
			colMove = false;
			break;
			
		}
		case 2:
		{
			if(colPos==0)
			{
				rowPos =rowPos + 1;
				colPos = 1;
				
			}
			else
				rowPos = rowPos - 2;
			rowMove = true;
			colMove = false;
			break;
			
		}
		case 3:
		{
			if(rowPos==0)
			{
				colPos =colPos + 1;
				rowPos = 1;
				
			}
			else
				colPos = colPos + 2;
			rowMove = false;
			colMove = true;
			break;
			
		}
		case 4:
		{
			if(rowPos==0)
			{
				colPos =colPos + 1;
				rowPos = 1;
				
			}
			else
				colPos = colPos - 2;
			rowMove = false;
			colMove = true;
			break;
			
		}
		default:
			repeat = true;
			continue;
		}
		random =random+1;
		if(random>=5)
			random = 1;
		
		
		
		
	}
	
	if(rowPos>=17 || rowPos<=0 || colPos>=17 || colPos<=0)
	{
		repeat = true;
		continue;
		
	}
	
	if(cordArray[rowPos][colPos].getStreetValue()=='W')
	{
		repeat = true;
	}
	else if(rowPos-1>0 && rowPos-1<19)
			if(cordArray[rowPos-1][colPos].getStreetValue()=='W' && rowMove)
				repeat = true;
	else if(colPos-1>0 && colPos-1<19)
		if(cordArray[rowPos][colPos-1].getStreetValue()=='W' && colMove)
		repeat = true;
	
	
	
	}while(repeat);
	
	
	bull.setColPos(colPos);
	bull.setRowPos(rowPos);
	
	
}

}
