package Simon;


import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("Simon.fxml"));
    primaryStage.setTitle("Simon");
    primaryStage.setScene(new Scene(root, 600, 650));

  primaryStage.show();
  }


  public static void main(String[] args) {
    launch(args);

    boolean gameStart = false;




  }
}
