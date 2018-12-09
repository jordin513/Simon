
//Simon Style game by Jordin Medina
package Simon;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

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

  String CacheColor;

  boolean GameStart;
  static int RandomNum;
  //array of all color buttons
  ArrayList<Button> ColorArray = new ArrayList<Button>();
  int LastColor;
  //queue of all colors that the computer will flash
  Queue<Integer> Computer = new LinkedList<Integer>();

  //queue that will hold all color button inputs from user
  Queue<Integer> Player = new LinkedList<Integer>();
  String highscoreprint;
  @FXML
  private Button DeleteButton;

  //function that will flash all computer colors in list
  void FlashTempColor(int FlashingColor) {

    CacheColor = ColorArray.get(FlashingColor).getStyle();
    ColorArray.get(FlashingColor).setStyle("-fx-background-color: #7283f4;");
    Circle.setStyle("-fx-fill: " + CacheColor.substring(22));

  }

  void BackToOriginal(int FlashingColor) {
    ColorArray.get(FlashingColor).setStyle(CacheColor);
    Circle.setStyle("-fx-fill: #000000");
  }

  public void ColorWait() {
    System.out.println("start wait");
    try {
      Thread.sleep(3000);
    } catch (Exception e) {

    }
    System.out.println("end wait");
  }

  //begins game
  @FXML
  void StartClick(ActionEvent event) throws Exception {
    GameStart = true;
    ColorArray.add(BlueButton);
    ColorArray.add(GreenButton);
    ColorArray.add(YellowButton);
    ColorArray.add(RedButton);

    System.out.println(GameStart);
    RandomNum = new Random().nextInt(4);
    Computer.add(RandomNum);
    for (Integer FlashingColor : Computer) {
      FlashTempColor(FlashingColor);
      ColorWait();
      Stage stage;
      Parent root;

      //BackToOriginal(FlashingColor);
      System.out.println("about to leave");
    }
    AccessDatabase("Select");



  }

  @FXML
  void Delete(ActionEvent event) {

  }

  public void AccessDatabase(String query) {

    //database
    String QueryString;
    final String DATABASE_URL = "jdbc:derby:lib//books";
    final String SELECT_QUERY =
        "SELECT NAME,SCORE From GAMES.HIGHSCORES order by SCORE DESC";
    final String DELETE_QUERY =
        "DELETE from GAMES.HIGHSCORES where score = (SELECT MIN(SCORE) from GAMES.HIGHSCORES)";

    if (query.equals("Select")) {
      QueryString = SELECT_QUERY;
    } else { //(query=="Delete")
      QueryString = DELETE_QUERY;
    }

    // use try-with-resources to connect to and query the database

    String deitel = "deitel";
    try (
        Connection connection = DriverManager.getConnection(
            DATABASE_URL, deitel, deitel);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(QueryString)) {

      // get ResultSet's meta data
      ResultSetMetaData metaData = resultSet.getMetaData();
      int numberOfColumns = metaData.getColumnCount();

      highscoreprint = "Leaderboard from Database: \n";
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

  @FXML
  void BlueClick(ActionEvent event) {

  }

  @FXML
  void GreenClick(ActionEvent event) {

  }

  @FXML
  void YellowClick(ActionEvent event) {

  }

  @FXML
  void RedClick(ActionEvent event) {

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



  }
}
