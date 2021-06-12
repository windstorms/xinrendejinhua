package controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
/**
 * FinalScoreController 类位于controller包中，是比赛模式最后的结算和讲解界面控制器类
 *
 * @see StoryModeController 比赛模式对战控制类，在该页面进行完全部5轮对局后自动跳转
 * @see FiveBattleController 比赛模式末端的五人对局界面的部分控制器类
 * @see FivePlayersController 比赛模式末端的五人对局界面的部分控制器类，从本界面点击”会发生什么呢？“按钮跳转
 */
//故事模式后统计用户得分的界面控制器
public class FinalScoreController {
    /** 最终得分的标签，起突出显示的作用*/
    @FXML
    private Label finalScore;
    /** ”会发生什么呢“按钮，点击跳转到5AI对战界面*/
    @FXML
    private Button next;
    /** 标签的凸显的得分的设定*/
    public void setScore(int score){
        finalScore.setText(String.valueOf(score));
    }
    /** 初始化函数，采用匿名类的方式初始化界面上的各个组件*/
    @FXML
    private void initialize(){
        next.setOnAction(new EventHandler(){

            @Override
            public void handle(Event event) {
                FXMLLoader f = new FXMLLoader();
                f.setLocation(f.getClassLoader().getResource("view/FivePlayersView.fxml"));
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
