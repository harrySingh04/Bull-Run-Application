package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			MazeGuiPane mp = new MazeGuiPane(root);

			Scene scene = new Scene(root, 640, 660);
			scene.setOnKeyPressed(e -> {

				switch (e.getCode()) {
				case DOWN:
					mp.moveDown();
					break;

				case RIGHT:
					mp.moveRight();
					break;
				case LEFT:
					mp.moveLeft();
					break;

				case UP:
					mp.moveUP();
					break;
				}

			});

			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			// primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
