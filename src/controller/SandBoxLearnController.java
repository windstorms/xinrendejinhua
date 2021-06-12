package controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.SlotMachine;

import java.io.IOException;
import java.util.ArrayList;
/**
 * FiveBattleController 类位于controller包中，比赛模式末端的五人对局界面的部分控制器类，主要负责右侧对局结果信息的显示
 *
 * @see SandBoxLearnController 沙盒模式的结论升华界面，从该页面点击”看看今天学到了什么“按钮跳转
 * @see FiveBattleController 同为比赛模式末端的五人对局的部分界面控制器类，从该页面点击”会发生什么呢？“按钮跳转
 * @see FiveBattleController 同为比赛模式末端的五人对局的部分界面控制器类，从该页面点击”会发生什么呢？“按钮跳转
 *
 */
public class SandBoxLearnController {
    /** 采用单例模式*/
    private SandBoxController sandBoxController;

    public void setSandBoxController(SandBoxController sandBoxController) {
        this.sandBoxController = sandBoxController;
    }

    @FXML
    public Button step1;
    @FXML
    public Button step2;
    @FXML
    public Button step3;

    @FXML
    private TextArea tip1;
    @FXML
    private TextArea tip2;
    @FXML
    private TextArea tip3;
    @FXML
    private TextArea tip4;
    @FXML
    private TextArea tip5;
    @FXML
    private TextArea tip6;
    @FXML
    private TextArea tip7;
    @FXML
    private TextArea tip8;
    @FXML
    private TextArea tip9;
    @FXML
    private TextArea tip10;
    @FXML
    private TextArea tip11;
    @FXML
    private TextArea tip12;
    @FXML
    private TextArea tip13;
    @FXML
    private TextArea tip14;
    @FXML
    private TextArea tip15;
    @FXML
    private TextArea tip16;
    @FXML
    private TextArea tip17;
    @FXML
    private ImageView changeRule;
    @FXML
    private Button ouch;
    @FXML
    private Button anotherQuestion;
    @FXML
    private Button evolute;
    @FXML
    private Button whatIsThat;
    private int roundNum = 1;
    @FXML
    private Button continueButton;
    @FXML
    private Button worseButton;
    @FXML
    private Button continueButton2;
    @FXML
    private Button mistakeButton;
    @FXML
    private Button continueButton3;
    @FXML
    private Button whatHappen;
    @FXML
    private Button reset;
    @FXML
    private Button continueButton4;
    @FXML
    private Button haveATry;
    @FXML
    private Button continueButton5;
    @FXML
    private Button willSame;
    @FXML
    private Button turnToSand;

    @FXML
    private AnchorPane tipPane;
    @FXML
    private AnchorPane mistakePane;
    @FXML
    private AnchorPane newPlayer;

    @FXML
    private Slider roundBar;
    @FXML
    private Label roundLabel;
    @FXML
    private ImageView winnerView;

    /** 初始化对局，初始化为和善的世界*/
    private void cuteWorld(){
        ArrayList<Integer> playerList = sandBoxController.getPlayerList();
        playerList.clear();
        playerList.add(0);
        playerList.add(1);
        for(int i=1; i<24; i++){
            playerList.add(2);
        }
        sandBoxController.sliderLableInit();
        sandBoxController.updateMiniView();
        sandBoxController.refreshEvolution();
    }
    /** 初始化对局，初始化为正常（默认）的世界*/
    private void world(boolean kind){
        ArrayList<Integer> playerList = sandBoxController.getPlayerList();
        playerList.clear();
        for(int i=0; i<3;i++){
            playerList.add(0);
        }
        for(int i=0; i<13;i++){
            if(kind == true){
                playerList.add(2);
            }
            else{
                playerList.add(1);
            }
        }
        for(int i=0;i<3;i++){
            for(int j=0; j<3; j++){
                playerList.add(i+5);
            }
        }
        sandBoxController.sliderLableInit();
        sandBoxController.updateMiniView();
        sandBoxController.refreshEvolution();
    }


    /** 初始化函数，采用匿名类的方式初始化界面上的各个组件*/
    @FXML
    private void initialize(){
        DiscreteListener roundListener = new DiscreteListener(roundLabel);
        roundListener.setRule(0);
        roundBar.valueProperty().addListener(roundListener);

        anotherQuestion.setDisable(true);
        evolute.setDisable(true);
        step2.setDisable(true);
        step3.setDisable(true);
        step1.setOnAction(new EventHandler(){

            @Override
            public void handle(Event event) {
                if(roundNum == 1){
                    tip2.setOpacity(1);
                }
                if(roundNum == 16){
                    tip5.setOpacity(1);
                }
                sandBoxController.activeStep();
                step1.setDisable(true);
                step2.setDisable(false);
                step3.setDisable(true);
                roundNum++;
            }
        });
        step2.setOnAction(new EventHandler(){

            @Override
            public void handle(Event event) {
                if(roundNum == 14){
                    tip4.setOpacity(1);
                }

                sandBoxController.activeStep();
                step1.setDisable(true);
                step2.setDisable(true);
                step3.setDisable(false);
                roundNum++;
            }
        });
        step3.setOnAction(new EventHandler(){

            @Override
            public void handle(Event event) {
                if(roundNum == 9){
                    tip3.setOpacity(1);
                }
                sandBoxController.activeStep();
                step1.setDisable(false);
                step2.setDisable(true);
                step3.setDisable(true);
                if(roundNum == 21){
                    tip6.setOpacity(1);
                    step1.setOpacity(0);
                    step2.setOpacity(0);
                    step3.setOpacity(0);
                    step1.setDisable(true);
                    ouch.setOpacity(1);
                    ouch.setDisable(false);
                }
                roundNum++;
            }
        });
        evolute.setOnAction(new EventHandler(){

            @Override
            public void handle(Event event) {
                if(roundNum % 2 == 1){
                    evolute.setText("停止进化流程!");
                }
                else{
                    evolute.setText("开始进化流程!");
                }
                roundNum++;
                sandBoxController.activeEvolute();
            }
        });
        ouch.setOnAction(new EventHandler(){

            @Override
            public void handle(Event event) {
                ouch.setOpacity(0);
                ouch.setDisable(true);
                anotherQuestion.setOpacity(1);
                evolute.setOpacity(1);
                anotherQuestion.setDisable(false);
                evolute.setDisable(false);
                roundNum = 1;
                tip7.setOpacity(1);

                ArrayList<Integer> playerList = sandBoxController.getPlayerList();
                playerList.clear();
                for(int i=0; i<25; i++){
                    playerList.add(i/5);
                }
                sandBoxController.sliderLableInit();
                sandBoxController.updateMiniView();
            }
        });
        anotherQuestion.setOnAction(new EventHandler(){

            @Override
            public void handle(Event event) {
                ArrayList<Integer> playerList = sandBoxController.getPlayerList();
                playerList.clear();
                playerList.add(0);
                for(int i=1; i<25; i++){
                    playerList.add(1);
                }
                sandBoxController.sliderLableInit();
                sandBoxController.updateMiniView();
                sandBoxController.refreshEvolution();
                if(roundNum % 2 == 0){
                    sandBoxController.activeEvolute();
                }
                whatIsThat.setOpacity(1);
                whatIsThat.setDisable(false);
                evolute.setOpacity(0);
                evolute.setDisable(true);
                anotherQuestion.setOpacity(0);
                anotherQuestion.setDisable(true);
                tip8.setOpacity(1);
            }

        });

        whatIsThat.setOnAction(new EventHandler(){

            @Override
            public void handle(Event event) {
                whatIsThat.setOpacity(0);
                whatIsThat.setDisable(true);
                cuteWorld();

                sandBoxController.processBox.setOpacity(1);
                sandBoxController.processBox.setDisable(false);
                sandBoxController.getResetButton().setOpacity(0);
                sandBoxController.getResetButton().setDisable(true);
                tip9.setOpacity(1);
                roundBar.setOpacity(1);
                roundBar.setDisable(false);
                roundLabel.setOpacity(1);
                continueButton.setOpacity(1);
                continueButton.setDisable(false);
                reset.setOpacity(1);
                reset.setDisable(false);
            }
        });
        reset.setOnAction(new EventHandler(){

            @Override
            public void handle(Event event) {
                cuteWorld();
                sandBoxController.setStepStatus(0);
            }
        });
        continueButton.setOnAction(new EventHandler(){

            @Override
            public void handle(Event event) {
                roundBar.setOpacity(0);
                roundBar.setDisable(true);
                roundLabel.setOpacity(0);
                continueButton.setOpacity(0);
                continueButton.setDisable(true);
                reset.setOpacity(0);
                reset.setDisable(true);

                tip10.setOpacity(1);
                continueButton.setOpacity(0);
                continueButton.setDisable(true);
                worseButton.setOpacity(1);
                worseButton.setDisable(false);
            }
        });
        worseButton.setOnAction(new EventHandler(){

            @Override
            public void handle(Event event) {
                cuteWorld();
                tip11.setOpacity(1);
                worseButton.setDisable(true);
                worseButton.setOpacity(0);
                continueButton2.setOpacity(1);
                continueButton2.setDisable(false);
                changeRule.setVisible(true);
                changeRule.setOpacity(1);
                SlotMachine.getInstance().setWin_win(1);
                SlotMachine.getInstance().setMaxRounds(10);
            }
        });
        continueButton2.setOnAction(new EventHandler(){

            @Override
            public void handle(Event event) {
                tip12.setOpacity(1);
                continueButton2.setOpacity(0);
                continueButton2.setDisable(true);
                changeRule.setVisible(false);
                changeRule.setOpacity(0);
                mistakeButton.setOpacity(1);
                mistakeButton.setDisable(false);
            }
        });
        mistakeButton.setOnAction(new EventHandler(){

            @Override
            public void handle(Event event) {
                mistakeButton.setOpacity(0);
                mistakeButton.setDisable(true);
                mistakePane.setOpacity(1);
                mistakePane.setVisible(true);
                mistakePane.setDisable(false);
                sandBoxController.battlePlace.setOpacity(0);
                sandBoxController.battlePlace.setDisable(true);

            }
        });
        continueButton3.setOnAction(new EventHandler(){

            @Override
            public void handle(Event event) {
                mistakePane.setOpacity(0);
                mistakePane.setDisable(true);
                newPlayer.setVisible(true);
                newPlayer.setDisable(false);
                newPlayer.setOpacity(1);
                tipPane.setOpacity(0);
                tipPane.setDisable(true);
            }
        });
        whatHappen.setOnAction(new EventHandler(){

            @Override
            public void handle(Event event) {
                tip12.setOpacity(0);
                SlotMachine.getInstance().setWin_win(2);
                mistakePane.setVisible(false);
                mistakePane.setDisable(true);
                mistakePane.setOpacity(0);
                tipPane.setOpacity(1);
                tipPane.setDisable(false);
                newPlayer.setVisible(false);
                newPlayer.setDisable(true);
                newPlayer.setOpacity(0);
                sandBoxController.battlePlace.setOpacity(1);
                sandBoxController.battlePlace.setDisable(false);
                world(true);
                SlotMachine.getInstance().setMaxRounds(10);
                SlotMachine.getInstance().setMistakeRate(5);
                tip13.setOpacity(1);
                continueButton4.setOpacity(1);
                continueButton4.setDisable(false);
            }
        });

        continueButton4.setOnAction(new EventHandler(){

            @Override
            public void handle(Event event) {
                continueButton4.setOpacity(0);
                continueButton4.setDisable(true);
                tip14.setOpacity(1);
                haveATry.setOpacity(1);
                haveATry.setDisable(false);

            }
        });
        haveATry.setOnAction(new EventHandler(){

            @Override
            public void handle(Event event) {
                world(false);
                tip15.setOpacity(1);
                haveATry.setOpacity(0);
                haveATry.setDisable(true);
                continueButton5.setOpacity(1);
                continueButton5.setDisable(false);
            }
        });

        continueButton5.setOnAction(new EventHandler(){

            @Override
            public void handle(Event event) {
                tip16.setOpacity(1);
                continueButton5.setOpacity(0);
                continueButton5.setDisable(true);
                willSame.setDisable(false);
                willSame.setOpacity(1);

            }
        });
        willSame.setOnAction(new EventHandler(){

            @Override
            public void handle(Event event) {
                sandBoxController.battlePlace.setOpacity(0);
                sandBoxController.battlePlace.setDisable(true);
                tip17.setOpacity(1);
                willSame.setOpacity(0);
                willSame.setDisable(true);
                turnToSand.setOpacity(1);
                turnToSand.setDisable(false);
                winnerView.setVisible(true);
                winnerView.setOpacity(1);
            }
        });
        turnToSand.setOnAction(new EventHandler(){

            @Override
            public void handle(Event event) {
                SlotMachine slotMachine = SlotMachine.getInstance();
                slotMachine.setMaxRounds(10);
                slotMachine.setMistakeRate(5);
                slotMachine.setWin_win(2);
                FXMLLoader f = new FXMLLoader();
                f.setLocation(f.getClassLoader().getResource("view/SandBoxView.fxml"));
                AnchorPane an = null;
                try {
                    an = (AnchorPane)f.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Main.changeRoot(an);
            }
        });
    }
}
