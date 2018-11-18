
//Simon Style game by Jordin Medina
package Simon;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class SimonController {

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  public Button YellowButton;

  @FXML
  public Button RedButton;

  @FXML
  public Button BlueButton;

  @FXML
  public Button GreenButton;

  @FXML
  public Button StartButton;

  @FXML
  public Label ScoreLabel;

  @FXML
  public Circle Circle;

  @FXML
  private Label HighScores;

  boolean GameStart;
  static int RandomNum;
  //array of all color buttons
  ArrayList<Button> ColorArray = new ArrayList<Button>();
  int LastColor;
  //queue of all colors that the computer will flash
  Queue<Integer> Computer = new LinkedList<Integer>();

  //queue that will hold all color button inputs from user
  Queue<Integer> Player = new LinkedList<Integer>();

//function that will flash all computer colors in list
  void FlashColors() {
    String CacheColor;
    for (Integer FlashingColor : Computer) {
      CacheColor = ColorArray.get(FlashingColor).getStyle();
      ColorArray.get(FlashingColor).setStyle("-fx-background-color: #7283f4;");

//     Main.
//      .requestLayout();
//Main.primaryStage.refresh();

      System.out.println("start wait");
      try {
        Thread.sleep(5000);
      } catch (Exception e) {

      }
      System.out.println("end wait");
      ColorArray.get(FlashingColor).setStyle(CacheColor);
    }


  }

  @FXML
  void BlueClick(ActionEvent event) {

  }

  @FXML
  void GreenClick(ActionEvent event) {

  }

  @FXML
  void RedClick(ActionEvent event) {

  }

  //begins game
  @FXML
  void StartClick(ActionEvent event) {
    GameStart = true;
    ColorArray.add(BlueButton);
    ColorArray.add(GreenButton);
    ColorArray.add(YellowButton);
    ColorArray.add(RedButton);

    System.out.println(GameStart);
    RandomNum = new Random().nextInt(4);
    Computer.add(RandomNum);
    FlashColors();


  }


  @FXML
  void YellowClick(ActionEvent event) {

  }

  @FXML
  void initialize() {
    assert YellowButton
        != null : "fx:id=\"YellowButton\" was not injected: check your FXML file 'Simon.fxml'.";
    assert RedButton
        != null : "fx:id=\"RedButton\" was not injected: check your FXML file 'Simon.fxml'.";
    assert BlueButton
        != null : "fx:id=\"BlueButton\" was not injected: check your FXML file 'Simon.fxml'.";
    assert GreenButton
        != null : "fx:id=\"GreenButton\" was not injected: check your FXML file 'Simon.fxml'.";
    assert StartButton
        != null : "fx:id=\"StartButton\" was not injected: check your FXML file 'Simon.fxml'.";
    assert ScoreLabel
        != null : "fx:id=\"ScoreLabel\" was not injected: check your FXML file 'Simon.fxml'.";
    assert Circle != null : "fx:id=\"Circle\" was not injected: check your FXML file 'Simon.fxml'.";
    GameStart = false;

    //database
    final String DATABASE_URL = "jdbc:derby:lib//books";
    final String SELECT_QUERY =
        "SELECT NAME,SCORE from GAMES.HIGHSCORES";

    // use try-with-resources to connect to and query the database
    try (
        Connection connection = DriverManager.getConnection(
            DATABASE_URL, "deitel", "deitel");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_QUERY)) {
      // get ResultSet's meta data
      ResultSetMetaData metaData = resultSet.getMetaData();
      int numberOfColumns = metaData.getColumnCount();

      String highscoreprint = "Leaderboard from Database: \n";
      int count = 0;

      // display query results
      while (resultSet.next()) {
        for (int i = 1; i <= numberOfColumns; i++) {
          highscoreprint += resultSet.getObject(i).toString() + " ";
          count++;

          if (count == 2) {
            highscoreprint += "\n";
            count = 0;
          }
        }
      }
      HighScores.setText(highscoreprint);
    } // AutoCloseable objects' close methods are called now
    catch (SQLException sqlException) {
      sqlException.printStackTrace();
    }

  }
}
