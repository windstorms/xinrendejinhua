package controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
/**
 * BackGroundController 类位于controller包中，是最后升华部分界面也即整个游戏最后一个界面的控制器类
 *本页面为最后的升华页面，无后置页面
 * @see SandBoxController 沙盒模式界面控制器类，从该界面点击”看看今天学到了什么“按钮跳转到当前界面
 */
public class FinalLearnController {
    /** 下面的每一个AnchorPane对应这个控制类的一个小界面*/
    /** 第一个总结页面*/
    @FXML
    private AnchorPane concludePane;
    /** 第二个学到的教训页面*/
    @FXML
    private AnchorPane LessonPane;
    /** 第三个升华页面*/
    @FXML
    private AnchorPane endPane;
    /** 总结页面的跳转按钮“最大的教训”*/
    @FXML
    private Button bestLesson;
    /** 教训页面的跳转按钮“所有人都发现了.....”*/
    @FXML
    private Button everyoneFound;
    /** 升华页面的跳转按钮“♥”*/
    @FXML
    private Button backButton;
    /** 初始化函数，采用匿名类的方式初始化界面上的各个组件*/
    @FXML
    private void initialize(){
        bestLesson.setOnAction(new EventHandler(){

            @Override
            public void handle(Event event) {
                concludePane.setVisible(false);
                LessonPane.setVisible(true);
            }
        });
        everyoneFound.setOnAction(new EventHandler(){

            @Override
            public void handle(Event event) {
                LessonPane.setVisible(false);
                endPane.setVisible(true);
            }
        });
        backButton.setOnAction(new EventHandler(){

            @Override
            public void handle(Event event) {
                FXMLLoader f = new FXMLLoader();
                f.setLocation(f.getClassLoader().getResource("view/BeginView.fxml"));
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
