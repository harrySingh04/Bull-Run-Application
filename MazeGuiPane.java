package application;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class MazeGuiPane extends BorderPane {
	StreetMap sMap = new StreetMap();
	GridPane gp;
	boolean start = false, end = false;
	Coordinate[][] cordArray = new Coordinate[18][18];
	int rowPos = 1, colPos = 1;
	Bull bull = new Bull(0, 0);
	Bull bull2 = new Bull(0,0);
	Bull bull3 = new Bull(0,0);
	Fool fool = new Fool(0, 0, 0);
	Label[][] gridLabel = new Label[20][20];
	Label message = new Label();
	Label foolLabel = new Label();
	Label bullLabel = new Label();
	Label bullLabel2 = new Label();
	Label bullLabel3 = new Label();

	public MazeGuiPane(BorderPane bp)
	{
		StackPane sp = new StackPane();
		Label topLabel = new Label();
		topLabel.setText("Map of Pamplona");
		topLabel.getStyleClass().add("topLabel");

		sp.getChildren().add(topLabel);
		sp.getStyleClass().add("stackPane");
		sp.setPadding(new Insets(10, 20, 10, 30));
		bp.setTop(sp);
		// root.getChildren().add(topLabel);

		// co = new ArrayList<Coordinate>();
		sMap.setCordinate(cordArray);

		gp = new GridPane();
		gp.setHgap(6);
		gp.setVgap(6);
		for (int rowCounter = 0; rowCounter < cordArray.length; rowCounter++) {
			for (int colCounter = 0; colCounter < cordArray.length; colCounter++) {
				gp.getStyleClass().add("grid");
				final int X = rowCounter;
				final int Y = colCounter;

				gridLabel[rowCounter][colCounter] = new Label();

				if (cordArray[rowCounter][colCounter].getStreetValue() == 'S') {
					gridLabel[rowCounter][colCounter].setText("S");
					gridLabel[rowCounter][colCounter].getStyleClass().add("startEndLabel");

				} else if (cordArray[rowCounter][colCounter].getStreetValue() == 'E') {
					gridLabel[rowCounter][colCounter].setText("E");
					gridLabel[rowCounter][colCounter].getStyleClass().add("startEndLabel");

				} else if (cordArray[rowCounter][colCounter].getStreetValue() == 'W') {
					gridLabel[rowCounter][colCounter].getStyleClass().add("wallLabel");
				} else if (cordArray[rowCounter][colCounter].getStreetValue() == 'X') {

					gridLabel[rowCounter][colCounter].getStyleClass().add("raceLabel");

				}

				gridLabel[rowCounter][colCounter].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {

					public void handle(Event event) {
						if (!(X == 0 || X == 19 || Y == 0 || Y == 19))

						{

							if (cordArray[X][Y].getStreetValue() == 'W') {

								gridLabel[X][Y].getStyleClass().clear();
								cordArray[X][Y].setStreetValue('X');
								gridLabel[X][Y].getStyleClass().add("raceLabel2");
								
							} else if (cordArray[X][Y].getStreetValue() == 'X') {
								gridLabel[X][Y].getStyleClass().clear();
								cordArray[X][Y].setStreetValue('W');
								gridLabel[X][Y].getStyleClass().add("wallLabel2");
								

							}

						}
					}

				});

				gp.setPadding(new Insets(10, 0, 0, 0));
				gp.add(gridLabel[rowCounter][colCounter], colCounter, rowCounter);

			}

		}
		
		Button runButton = new Button();
		runButton.setText("RUN");
		runButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>(){
			
			public void handle(Event event)
			{
				
				start = true;
			}
			
		});
		
		bp.setCenter(gp);
		runButton.getStyleClass().add("runButton");
		
		HBox h1 = new HBox();
		message.setPadding(new Insets(0,0,0,50));
		h1.getChildren().addAll(runButton,message);
		h1.setPadding(new Insets(20,50,30,300));
		h1.getStyleClass().add("hbox");
		
		bp.setBottom(h1);
		bp.getStyleClass().add("borderPane");
	}

	public void moveDown() {
		if(start && !end)
		{
		gp.getChildren().remove(foolLabel);
		gp.getChildren().remove(bullLabel);
		gp.getChildren().remove(bullLabel2);
		gp.getChildren().remove(bullLabel3);
		int rowPos, colPos, pos, bullRowPos = 0, bullColPos = 0,bullRowPos2=0,bullColPos2 = 0,bullRowPos3=0,bullColPos3=0, lastRow, lastCol;
		rowPos = fool.getRowPos();
		colPos = fool.getColPos();
		pos = fool.getPosition();
		lastRow = rowPos;
		lastCol = colPos;
		rowPos = rowPos + 1;
		if (colPos == 0)
			colPos = colPos + 1;

		pos = pos + 1;

		if (cordArray[rowPos][colPos].getStreetValue() == 'W') {
			rowPos = rowPos - 1;
		}

		fool.setRowPos(rowPos);
		fool.setColPos(colPos);
		fool.setPostion(pos);

		foolLabel.getStyleClass().add("foolLabel");
		gp.add(foolLabel, colPos, rowPos);

		if (pos >= 5)
		{

			sMap.move(fool, bull, cordArray, lastRow, lastCol);
			bullRowPos = bull.getRowPos();
			bullColPos = bull.getColPos();
			bullLabel.getStyleClass().add("bullLabel");
			gp.add(bullLabel, bullColPos, bullRowPos);

		}
		
		if (pos >= 8) {

			sMap.move(fool, bull2, cordArray, lastRow, lastCol);
			bullRowPos2 = bull2.getRowPos();
			bullColPos2 = bull2.getColPos();
			bullLabel2.getStyleClass().add("bullLabel");
			gp.add(bullLabel2, bullColPos2, bullRowPos2);

		}

		if (bullColPos2 == colPos && rowPos == bullRowPos2)
		{
			message.setText("You have Lost");
			gp.getChildren().remove(foolLabel);
			end = true;
			

		}
		

		if(bullColPos == colPos && rowPos == bullRowPos)
		{
			message.setText("You have Lost");
			end = true;

		}
		
		if (pos >= 12) {

			sMap.move(fool, bull3, cordArray, lastRow, lastCol);
			bullRowPos3 = bull3.getRowPos();
			bullColPos3 = bull3.getColPos();
			bullLabel3.getStyleClass().add("bullLabel");
			gp.add(bullLabel3, bullColPos3, bullRowPos3);

		}

		if (bullColPos3 == colPos && rowPos == bullRowPos3)
		{
			message.setText("You have Lost");
			gp.getChildren().remove(foolLabel);
			end = true;
			

		}
		
		if(rowPos==17 && colPos==16)
		{
			message.setText("Cogratulations! you have won");
			gp.getChildren().remove(foolLabel);
			end = true;
			
		}
			
		}

	}

	public void moveRight() {
		if (start && !end) {
			gp.getChildren().remove(foolLabel);
			gp.getChildren().remove(bullLabel);
			gp.getChildren().remove(bullLabel2);
			gp.getChildren().remove(bullLabel3);
			int rowPos, colPos, pos, bullRowPos = 0, bullColPos = 0,bullColPos2=0,bullRowPos2=0,bullColPos3=0,bullRowPos3=0, lastRow, lastCol;
			rowPos = fool.getRowPos();
			colPos = fool.getColPos();
			lastRow = rowPos;
			lastCol = colPos;
			pos = fool.getPosition();
			colPos = colPos + 1;
			pos = pos + 1;

			if (cordArray[rowPos][colPos].getStreetValue() == 'W') {
				colPos = colPos - 1;
			}

			fool.setColPos(colPos);
			fool.setRowPos(rowPos);
			fool.setPostion(pos);

			foolLabel.getStyleClass().add("foolLabel");
			gp.add(foolLabel, colPos, rowPos);
			if (pos >= 5) {

				sMap.move(fool, bull, cordArray, lastRow, lastCol);
				bullRowPos = bull.getRowPos();
				bullColPos = bull.getColPos();
				bullLabel.getStyleClass().add("bullLabel");
				gp.add(bullLabel, bullColPos, bullRowPos);

			}

			if (bullRowPos == rowPos && colPos == bullColPos)
			{
				message.setText("You have Lost");
				gp.getChildren().remove(foolLabel);
				
				end = true;

			}
			
			if (pos >= 8) {

				sMap.move(fool, bull2, cordArray, lastRow, lastCol);
				bullRowPos2 = bull2.getRowPos();
				bullColPos2 = bull2.getColPos();
				bullLabel2.getStyleClass().add("bullLabel");
				gp.add(bullLabel2, bullColPos2, bullRowPos2);

			}

			if (bullColPos2 == colPos && rowPos == bullRowPos2)
			{
				message.setText("You have Lost");
				gp.getChildren().remove(foolLabel);
				end = true;
				

			}
			if (pos >= 12) {

				sMap.move(fool, bull3, cordArray, lastRow, lastCol);
				bullRowPos3 = bull3.getRowPos();
				bullColPos3 = bull3.getColPos();
				bullLabel3.getStyleClass().add("bullLabel");
				gp.add(bullLabel3, bullColPos3, bullRowPos3);

			}

			if (bullColPos3 == colPos && rowPos == bullRowPos3)
			{
				message.setText("You have Lost");
				gp.getChildren().remove(foolLabel);
				end = true;
				

			}
			
			

		}

	}

	public void moveLeft() {
		if (start && !end) {
			gp.getChildren().remove(foolLabel);
			gp.getChildren().remove(bullLabel);
			gp.getChildren().remove(bullLabel2);
			gp.getChildren().remove(bullLabel3);
			int rowPos, colPos, pos, bullRowPos = 0, bullColPos = 0,bullColPos2=0,bullRowPos2=0,bullRowPos3=0,bullColPos3=0, lastRow, lastCol;
			rowPos = fool.getRowPos();
			colPos = fool.getColPos();
			pos = fool.getPosition();
			pos = pos + 1;
			lastRow = rowPos;
			lastCol = colPos;

			if (colPos != 0)
				colPos = colPos - 1;
			if (cordArray[rowPos][colPos].getStreetValue() == 'W') {
				colPos = colPos - 1;
			}

			fool.setColPos(colPos);
			fool.setPostion(pos);
			fool.setRowPos(rowPos);

			foolLabel.getStyleClass().add("foolLabel");
			gp.add(foolLabel, colPos, rowPos);

			if (pos >= 5) {

				sMap.move(fool, bull, cordArray, lastRow, lastCol);
				bullRowPos = bull.getRowPos();
				bullColPos = bull.getColPos();
				bullLabel.getStyleClass().add("bullLabel");
				gp.add(bullLabel, bullColPos, bullRowPos);

			}

			if (bullRowPos == rowPos && colPos == bullColPos)
			{
				message.setText("You have Lost");
			
				/*Alert a = new Alert(AlertType.INFORMATION);
				a.setTitle("RESULT");
				a.setHeaderText(null);
				String content = String.format("Sorry you have Lost!!!");

				a.setContentText(content);
				a.showAndWait();*/
				end = true;

			}
			
			if (pos >= 8) {

				sMap.move(fool, bull2, cordArray, lastRow, lastCol);
				bullRowPos2 = bull2.getRowPos();
				bullColPos2 = bull2.getColPos();
				bullLabel2.getStyleClass().add("bullLabel");
				gp.add(bullLabel2, bullColPos2, bullRowPos2);

			}

			if (bullColPos2 == colPos && rowPos == bullRowPos2)
			{
				message.setText("You have Lost");
				gp.getChildren().remove(foolLabel);
				end = true;
				

			}
			if (pos >= 12) {

				sMap.move(fool, bull3, cordArray, lastRow, lastCol);
				bullRowPos3 = bull3.getRowPos();
				bullColPos3 = bull3.getColPos();
				bullLabel3.getStyleClass().add("bullLabel");
				gp.add(bullLabel3, bullColPos3, bullRowPos3);

			}

			if (bullColPos3 == colPos && rowPos == bullRowPos3)
			{
				message.setText("You have Lost");
				gp.getChildren().remove(foolLabel);
				end = true;
				

			}
			
			
			

		}
	}

	public void moveUP() {
		if (start && !end) {
			gp.getChildren().remove(foolLabel);
			gp.getChildren().remove(bullLabel);
			gp.getChildren().remove(bullLabel2);
			gp.getChildren().remove(bullLabel3);
			int rowPos, colPos, pos, bullRowPos = 0, bullColPos = 0,bullColPos2=0,bullRowPos2=0,bullRowPos3=0,bullColPos3=0, lastRow, lastCol;
			rowPos = fool.getRowPos();
			colPos = fool.getColPos();
			pos = fool.getPosition();
			pos = pos + 1;
			lastRow = rowPos;
			lastCol = colPos;

			if (rowPos != 0)
				rowPos = rowPos - 1;
			if (colPos == 0)
				colPos = colPos + 1;

			if (cordArray[rowPos][colPos].getStreetValue() == 'W') {
				rowPos = rowPos + 1;
			}

			fool.setRowPos(rowPos);
			fool.setPostion(pos);
			fool.setColPos(colPos);
			foolLabel.getStyleClass().add("foolLabel");

			if (rowPos == 0 && colPos == 1) {

				start = false;
				gp.getChildren().remove(foolLabel);

			} else {
				gp.add(foolLabel, colPos, rowPos);

			}

			if (pos >= 5) {

				sMap.move(fool, bull, cordArray, lastRow, lastCol);
				bullRowPos = bull.getRowPos();
				bullColPos = bull.getColPos();
				bullLabel.getStyleClass().add("bullLabel");
				gp.add(bullLabel, bullColPos, bullRowPos);

			}

			if (bullColPos == colPos && rowPos == bullRowPos)
			{
				message.setText("You have Lost");
				gp.getChildren().remove(foolLabel);
				end = true;
				

			}
			
			if (pos >= 8) {

				sMap.move(fool, bull2, cordArray, lastRow, lastCol);
				bullRowPos2 = bull2.getRowPos();
				bullColPos2 = bull2.getColPos();
				bullLabel2.getStyleClass().add("bullLabel");
				gp.add(bullLabel2, bullColPos2, bullRowPos2);

			}

			if (bullColPos == colPos && rowPos == bullRowPos)
			{
				message.setText("You have Lost");
				gp.getChildren().remove(foolLabel);
				end = true;
				

			}
			if (pos >= 12) {

				sMap.move(fool, bull3, cordArray, lastRow, lastCol);
				bullRowPos3 = bull3.getRowPos();
				bullColPos3 = bull3.getColPos();
				bullLabel3.getStyleClass().add("bullLabel");
				gp.add(bullLabel3, bullColPos3, bullRowPos3);

			}

			if (bullColPos3 == colPos && rowPos == bullRowPos3)
			{
				message.setText("You have Lost");
				gp.getChildren().remove(foolLabel);
				end = true;
				

			}
			
			

		}
	}
}
