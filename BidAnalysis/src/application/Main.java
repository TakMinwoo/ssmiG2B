package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent login = FXMLLoader.load(getClass().getResource("/View/login.fxml"));
			Scene loginScene = new Scene(login);
			
			primaryStage.setTitle("삼성계기공업 투찰관리");
			primaryStage.setScene(loginScene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
