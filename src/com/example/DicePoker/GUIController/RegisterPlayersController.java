package com.example.DicePoker.GUIController;

import com.example.DicePoker.Logic.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterPlayersController {

    Player player1, player2, player3, player4;

    int check = 0, min_player = 2;

    @FXML
    private Button pc_human1, pc_human2, pc_human3, pc_human4, Start;

    @FXML
    private CheckBox p1_check, p2_check, p3_check, p4_check;

    @FXML
    private TextField name_pc_human1, name_pc_human2, name_pc_human3, name_pc_human4;

    @FXML
    private Label warning_text;

    @FXML
    public void changePlayer1Type() {
        if (pc_human1.getText().equals("PC")) {
            pc_human1.setText("Human");
            name_pc_human1.setEditable(true);
            name_pc_human1.setText("");
        } else {
            pc_human1.setText("PC");
            name_pc_human1.setText("CPU1");
            name_pc_human1.setEditable(false);
        }
    }

    @FXML
    public void changePlayer2Type() {
        if (pc_human2.getText().equals("PC")) {
            pc_human2.setText("Human");
            name_pc_human2.setEditable(true);
            name_pc_human2.setText("");
        } else {
            pc_human2.setText("PC");
            name_pc_human2.setText("CPU1");
            name_pc_human2.setEditable(false);
        }
    }

    @FXML
    public void changePlayer3Type() {
        if (pc_human3.getText().equals("PC")) {
            pc_human3.setText("Human");
            name_pc_human3.setEditable(true);
            name_pc_human3.setText("");
        } else {
            pc_human3.setText("PC");
            name_pc_human3.setText("CPU1");
            name_pc_human3.setEditable(false);
        }
    }

    @FXML
    public void changePlayer4Type() {
        if (pc_human4.getText().equals("PC")) {
            pc_human4.setText("Human");
            name_pc_human4.setEditable(true);
            name_pc_human4.setText("");
        } else {
            pc_human4.setText("PC");
            name_pc_human4.setText("CPU1");
            name_pc_human4.setEditable(false);
        }
    }

    @FXML
    public void startButtonUnlock(){
        if (check >= min_player) {
            Start.setDisable(false);
        } else {
            Start.setDisable(true);
        }
    }

    @FXML
    public void player1Unlock() {
        if (p1_check.isSelected()) {
            pc_human1.setDisable(false);
            name_pc_human1.setDisable(false);

            if (name_pc_human1.getText().isEmpty()) {

            } else {
                check++;
               startButtonUnlock();
            }
        } else {
            pc_human1.setDisable(true);
            name_pc_human1.setDisable(true);
            check--;
            startButtonUnlock();
        }

    }

    @FXML
    public void player2Unlock() {

        if (p2_check.isSelected()) {
            pc_human2.setDisable(false);
            name_pc_human2.setDisable(false);
            if (name_pc_human2.getText().isEmpty()) {

            } else {
                check++;
                startButtonUnlock();
            }
        } else {
            pc_human2.setDisable(true);
            name_pc_human2.setDisable(true);
            check--;
            startButtonUnlock();
        }
    }

    @FXML
    public void player3Unlock() {

        if (p3_check.isSelected()) {
            pc_human3.setDisable(false);
            name_pc_human3.setDisable(false);
            if (name_pc_human3.getText().isEmpty()) {

            } else {
                check++;
                startButtonUnlock();
            }
        } else {
            pc_human3.setDisable(true);
            name_pc_human3.setDisable(true);
            check--;
            startButtonUnlock();
        }
    }

    @FXML
    public void player4Unlock() {

        if (p4_check.isSelected()) {
            pc_human4.setDisable(false);
            name_pc_human4.setDisable(false);
            if (name_pc_human4.getText().isEmpty()) {

            } else {
                check++;
                startButtonUnlock();
            }
        }else{
            pc_human4.setDisable(true);
            name_pc_human4.setDisable(true);
            check--;
            startButtonUnlock();
        }
    }


    @FXML
    public void startGame(){

        if (check >= 2) {
            try {
                handleCloseWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../GUI/NewGame.fxml"));
                Parent startGame = fxmlLoader.load();
                Stage stage = new Stage();

                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("DICE POKER");
                stage.setScene(new Scene(startGame, 620, 480));
                stage.show();

                if(p1_check.isSelected()){
                    if(name_pc_human1.getText().equals("CPU1")) {
                        player1 = new Player(name_pc_human1.getText(), 100, false);
                    } else{
                        player1 = new Player(name_pc_human1.getText(), 100, true);
                    }
                }

                if(p2_check.isSelected()) {
                    if (name_pc_human2.getText().equals("CPU2")) {
                        player2 = new Player(name_pc_human2.getText(), 100, false);
                    } else {
                        player2 = new Player(name_pc_human2.getText(), 100, true);
                    }
                }

                if(p3_check.isSelected()) {
                    if (name_pc_human3.getText().equals("CPU3")) {
                        player3 = new Player(name_pc_human3.getText(), 100, false);
                        //System.out.println(name_pc_human3.getText());
                    } else {
                        player3 = new Player(name_pc_human3.getText(), 100, true);
                    }
                }

                if(p4_check.isSelected()) {
                    if (name_pc_human4.getText().equals("CPU4")) {
                        player4 = new Player(name_pc_human4.getText(), 100, false);
                    } else {
                        player4 = new Player(name_pc_human4.getText(), 100, true);
                    }
                }

                GameController gameController = fxmlLoader.getController();
                gameController.initialize(player1, player2, player3, player4);

            } catch (IOException e) {
                System.out.println("Could not load the page");
                e.printStackTrace();
                return;
            }
        } else {
            warning_text.setText("Not enough players have \nnames and are ready");
        }
    }

    public void handleCloseWindow(){
        Stage stage = (Stage) Start.getScene().getWindow();
        stage.close();
    }
}
