package controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * FivePlayersController 类位于controller包中，比赛模式末端的五人对局界面的部分控制器类，主要负责左侧对局界面信息的显示
 *
 * @see FinalScoreController 比赛模式开始的对局结束的结算界面，从该页面点击”会发生什么呢？“按钮跳转
 * @see FiveBattleController 同为比赛模式末端的五人对局的部分界面控制器类，本控制器类与其共同构成这部分的界面
 * @see SandBoxController 沙盒模式界面控制器类，从本页面点击”会发生什么呢？“按钮跳转
 */
//画出五个角色对决的场地的控制器
public class FivePlayersController {
    /** 划分区域*/
    @FXML
    private Arc arc1;
    @FXML
    private Arc arc2;
    @FXML
    private Arc arc3;
    @FXML
    private Arc arc4;
    @FXML
    private Arc arc5;
    /** 划线*/
    @FXML
    private Line line1;
    @FXML
    private Line line2;
    @FXML
    private Line line3;
    @FXML
    private Line line4;
    @FXML
    private Line line5;
    /** 玩家选择AI按钮*/
    @FXML
    private Button butn1;
    @FXML
    private Button butn2;
    @FXML
    private Button butn3;
    @FXML
    private Button butn4;
    @FXML
    private Button butn5;

    @FXML
    private AnchorPane mainPane;

    /** 代变对战的黄线*/
    private ArrayList<Shape> battleLine = new ArrayList<Shape>();

    public ArrayList<Shape> getBattleLine() {
        return battleLine;
    }
    /** 由于本类中初始化重复性过强，采用内部类增加代码复用性*/
    class Transfer implements EventHandler{

        @Override
        public void handle(Event event) {
            mainPane.getChildren().remove(mainPane.lookup("#chosePane"));
            FXMLLoader f = new FXMLLoader();
            f.setLocation(f.getClassLoader().getResource("view/FiveBattleView.fxml"));
            AnchorPane an = null;
            try {
                an = (AnchorPane)f.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            FiveBattleController controller = f.getController();
            controller.setBattleLine(battleLine);
            an.setLayoutX(400);
            mainPane.getChildren().add(an);
        }
    }

    /** 初始化函数，采用内部类的方式初始化界面上的各个组件*/
    @FXML
    private void initialize(){
        List list = Arrays.asList(arc1,line1,line2,arc5,arc2,line4,line3,arc3,line5,arc4);
        battleLine.addAll(list);
        butn1.setOnAction(new Transfer());
        butn2.setOnAction(new Transfer());
        butn3.setOnAction(new Transfer());
        butn4.setOnAction(new Transfer());
        butn5.setOnAction(new Transfer());
    }

}
