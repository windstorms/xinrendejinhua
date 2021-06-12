package controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import model.SlotMachine;

import java.io.IOException;
import java.util.ArrayList;
/**
 * TransistController 类位于controller包中，五个角色对局结束后的过渡界面控制器类
 * 起过度作用，故没有对应的fxml文件
 */
//五个角色对局结束后的过渡界面控制器
public class TransistController {
    /** 下一页面按钮*/
    @FXML
    private Button nextPage;
    /** 初始化函数，采用匿名类的方式初始化界面上的各个组件*/
    @FXML
    private void initialize(){
        nextPage.setOnAction(new EventHandler(){

            @Override
            public void handle(Event event) {
                FXMLLoader f = new FXMLLoader();
                f.setLocation(f.getClassLoader().getResource("view/SandBoxLearnView.fxml"));
                AnchorPane an = null;
                try {
                    an = (AnchorPane)f.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                SandBoxLearnController sandBoxLearnController = f.getController();

                Main.changeRoot(an);
                FXMLLoader f1 = new FXMLLoader();
                f1.setLocation(f.getClassLoader().getResource("view/SandBoxView.fxml"));

                AnchorPane an1 = null;
                try {
                    an1 = (AnchorPane)f1.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //加载沙盒模式的左半部分到界面里
                SandBoxController sandBoxController =(SandBoxController) f1.getController();
                sandBoxLearnController.setSandBoxController(sandBoxController);
                AnchorPane battlePlace = (AnchorPane)an1.lookup("#battlePlace");
                an.getChildren().add(battlePlace);
                ArrayList<Integer> playerList = sandBoxController.getPlayerList();
                sandBoxController.processBox.setOpacity(0);
                sandBoxController.processBox.setDisable(true);
                playerList.clear();
                for(int i=0;i<5;i++){
                    playerList.add(0);
                }
                for(int i=0;i<5;i++){
                    playerList.add(1);
                }
                for(int i=0;i<15;i++){
                    playerList.add(2);
                }
                sandBoxController.sliderLableInit();
                sandBoxController.updateMiniView();
                SlotMachine slotMachine = SlotMachine.getInstance();

                slotMachine.setMistakeRate(0);
                slotMachine.setMaxRounds(10);
            }
        });

    }
}
