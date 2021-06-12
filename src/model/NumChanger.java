package model;

import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

import model.SlotMachine;
/**
 * NumChanger 类位于model包中，是改变游戏报酬的版面控制器
 */
public  class NumChanger {
    /** 图象设置与加载*/
    public static ArrayList<Image>posNumImage = new ArrayList<Image>();
    public static ArrayList<Image>negNumImage = new ArrayList<Image>();
    static {
            int width = 1800;
            int height = 1800;
            Image zero = new Image("./img/number/0.png",width,height,
                    true,true,true);
            posNumImage.add(zero);
            negNumImage.add(zero);
            for(int i=1; i<=5; i++){
                Image img = new Image("./img/number/"+i+".png",width,height,
                        true,true,true);
                Image nimg = new Image("./img/number/-"+i+".png",width,height,
                        true,true,true);
                posNumImage.add(img);
                negNumImage.add(nimg);
            }
    }

    /** 数字设定的标签和图象*/
    private Label addl;
    private Label reducel;
    private Label addr;
    private Label reducer;
    private ImageView numViewl;
    private ImageView numViewr;
    private int number;
    /** 变量类型*/
    private int param = -1;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
        setImage();
    }

    /**图象初始化与更改、设置*/
    public void setImage(){
        if(number < 0){
            numViewl.setImage(negNumImage.get(Math.abs(number)));
            numViewr .setImage(negNumImage.get(Math.abs(number)));
        }else{
            numViewl.setImage(posNumImage.get(number));
            numViewr.setImage(posNumImage.get(number));
        }
    }
    /**图象数组初始化*/
    private void arrowInit(){

        addl.setOnMouseClicked(new EventHandler(){

            @Override
            public void handle(Event event) {
                if(number < 5){
                    number++;
                    setImage();
                    changeParam();
                }
            }
        });
        reducer.setOnMouseClicked(new EventHandler(){
            @Override
            public void handle(Event event) {
                if(number > -5){
                    number--;
                    setImage();
                    changeParam();
                }
            }
        });
        addr.setOnMouseClicked(new EventHandler(){

            @Override
            public void handle(Event event) {
                if(number < 5){
                    number++;
                    setImage();
                    changeParam();
                }
            }
        });
        reducel.setOnMouseClicked(new EventHandler(){
            @Override
            public void handle(Event event) {
                if(number > -5){
                    number--;
                    setImage();
                    changeParam();
                }
            }
        });
    }
    /**对应变量值的更改*/
    public void changeParam(){
        switch(param){
            case 0:
                SlotMachine.getInstance().setWin_win(number);
                break;
            case 1:
                SlotMachine.getInstance().setDupe(number);
                break;
            case 2:
                SlotMachine.getInstance().setFooled(number);
                break;
            case 3:
                SlotMachine.getInstance().setDupe_dupe(number);
                break;
        }
    }
    public NumChanger(Label addl, Label reducel, Label addr, Label reducer, ImageView numViewl, ImageView numViewr, int number, int param) {
        this.addl = addl;
        this.reducel = reducel;
        this.addr = addr;
        this.reducer = reducer;
        this.numViewl = numViewl;
        this.numViewr = numViewr;
        this.number = number;
        this.param = param;
        arrowInit();
        setImage();
    }
}
