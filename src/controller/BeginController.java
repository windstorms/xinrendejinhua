package controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
/**
 * BeginController 类位于controller包中，是故事模式开始界面也即整个游戏的第一个界面控制器类
 * 该类是初始界面，无前置界面
 * @see BackGroundController 故事模式背景介绍控制器类，是该类的后置界面之一，从开始界面点击”故事开始“按钮跳转的这个控制器类的界面
 * @see SandBoxController 沙盒模式控制器类，是该类的后置界面知一，从开始界面点击”沙盒模式“按钮跳转的这个控制器类的界面
 * @see StoryModeController 比赛模式控制器类，是该类的后置界面知一，从开始界面点击”比赛模式“按钮跳转的这个控制器类的界面
 */
//主菜单界面控制器
public class BeginController {
    /** ”故事开始“按钮，点击前往背景故事介绍界面*/
    @FXML
    private Button backGround;
    /** ”比赛模式“按钮，点击前往对战模式界面*/
    @FXML
    private Button battle;
    /** ”沙盒模式“按钮，点击前往沙盒模式界面*/
    @FXML
    private Button sandBox;
    /** ”退出“标签，在页面右上角显示一个图标，点击退出程序，该标签在各个界面中都存在*/
    @FXML
    private Label quitLabel;
    /** 需要指向其他类的按钮的加载函数*/
    private void rootLoader(String root){

        FXMLLoader f = new FXMLLoader();
        f.setLocation(f.getClassLoader().getResource(root));
        AnchorPane an = null;
        try {
            an = (AnchorPane)f.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.changeRoot(an);
    }
    /** 初始化函数，采用匿名类的方式初始化界面上的各个组件*/
    @FXML
    private void initialize(){
        backGround.setOnAction(new EventHandler(){

            @Override
            public void handle(Event event) {
                rootLoader("view/BackGroundView.fxml");
            }
        });
        battle.setOnAction(new EventHandler(){

            @Override
            public void handle(Event event) {
                rootLoader("view/StoryView.fxml");
                Main.normalWindow();
            }
        });
        sandBox.setOnAction(new EventHandler(){

            @Override
            public void handle(Event event) {
                rootLoader("view/SandBoxView.fxml");
                Main.normalWindow();
            }
        });
        quitLabel.setOnMouseClicked(new EventHandler(){

            @Override
            public void handle(Event event) {
                System.exit(0);
            }
        });
        Tooltip quit = new Tooltip("退出游戏");
        quit.setShowDuration(Duration.INDEFINITE);
        quit.setShowDelay(Duration.millis(50));
        quitLabel.setTooltip(quit);
    }
}
