package controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
/**
 * BackGroundController 类位于controller包中，是故事模式背景介绍（故事开始）界面也即故事模式第一个界面控制器类
 *
 * @see BeginController 开始界面控制器类，从开始界面点击按钮跳转的这个控制器类的界面
 * @see IntroduceController 故事背景及规则介绍界面控制器类，从本界面点击”我们先来玩个游戏“按钮跳转
 */
//背景介绍（故事开始）界面控制器
public class BackGroundController {
    /** ”我们先来玩个游戏“按钮，点击前往下一个界面*/
    @FXML
    private Button letUsPlay;
    /** 初始化函数，采用匿名类的方式初始化界面上的各个组件*/
    @FXML
    private void initialize(){
        letUsPlay.setOnAction(new EventHandler(){

            @Override
            public void handle(Event event) {
                FXMLLoader f = new FXMLLoader();
                f.setLocation(f.getClassLoader().getResource("view/IntroduceView.fxml"));
                AnchorPane an = null;
                try {
                    an = (AnchorPane)f.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Main.normalWindow();
                Main.changeRoot(an);
            }
        });
    }
}
