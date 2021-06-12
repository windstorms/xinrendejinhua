package controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
/**
 * IntroduceController 类位于controller包中，是故事背景及规则介绍界面也即故事模式第二个界面控制器类
 *
 * @see BackGroundController 故事背景介绍界面控制器类，从该界面点击”让我们开始“按钮跳转到当前界面
 * @see StoryModeController 比赛模式界面控制器类，从本页面点击“答案会有所不同吗？”按钮跳转
 */
public class IntroduceController {
    /** 下列属性对应整个控制器类界面上的一系列文本*/
    @FXML
    private TextArea intro;
    @FXML
    private TextArea bottom1;
    @FXML
    private TextArea bottom2;
    @FXML
    private TextArea bottom3;
    @FXML
    private TextArea trust1;
    @FXML
    private TextArea trust2;
    @FXML
    private TextArea dupe1;
    @FXML
    private TextArea dupe2;

    /** 欺骗按钮*/
    @FXML
    private Button dupeButton;
    /** 合作按钮*/
    @FXML
    private Button trustButton;
    /** ”答案会有所不同吗？“按钮，点击前往比赛模式界面*/
    @FXML
    private Button willDiff;
    /** 当前回合数，默认值为1*/
    private int roundNum = 1;
    /** 初始化函数，采用匿名类的方式初始化界面上的各个组件*/
    @FXML
    private void initialize(){
        willDiff.setOnAction(new EventHandler(){

            @Override
            public void handle(Event event) {
                FXMLLoader f = new FXMLLoader();
                f.setLocation(f.getClassLoader().getResource("view/StoryView.fxml"));
                AnchorPane an = null;
                try {
                    an = (AnchorPane)f.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Main.changeRoot(an);
            }
        });
        dupeButton.setOnAction(new EventHandler(){

            @Override
            public void handle(Event event) {
                if(roundNum == 1){
                    dupe1.setVisible(true);
                    roundNum++;
                    bottom2.setVisible(true);
                }
                else{
                    dupe2.setVisible(true);
                    dupeButton.setVisible(false);
                    trustButton.setVisible(false);
                    bottom3.setVisible(true);
                    willDiff.setVisible(true);
                }
            }
        });
        trustButton.setOnAction(new EventHandler(){

            @Override
            public void handle(Event event) {
                if(roundNum == 1){
                    trust1.setVisible(true);
                    roundNum++;
                    bottom2.setVisible(true);
                }
                else{
                    trust2.setVisible(true);
                    dupeButton.setVisible(false);
                    trustButton.setVisible(false);
                    bottom3.setVisible(true);
                    willDiff.setVisible(true);
                }
            }
        });
    }
}
