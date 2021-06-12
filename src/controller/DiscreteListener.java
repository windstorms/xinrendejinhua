package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import model.SlotMachine;

import static java.lang.Math.floor;
/**
 * DiscreteListener 类位于controller包中，用于使滑块的变化离散化（只显示整数）
 */
public class DiscreteListener implements ChangeListener<Number> {
    /**更改对应的标签*/
    public static void replaceLabel(Label label, int newVal){
        String message = label.getText().toString();
        label.setText( message.replaceAll("\\d+",String.valueOf(newVal)));
    }
    private Label label;
    /**滑块规则（类型）*/
    private int rule = -1;

    public void setRule(int rule) {
        this.rule = rule;
    }

    DiscreteListener(Label label){
        this.label = label;
    }
    /**更改滑块值时调用的函数，确保滑块值为整数*/
    @Override
    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        int difference = (int)floor((double)newValue) - (int)floor((double)oldValue);
        if(difference != 0){
            int current = (int)floor((double)oldValue);
            int newVal = current+difference;
            replaceLabel(this.label,newVal);
            SlotMachine slotMachine = SlotMachine.getInstance();
            switch(rule){
                case 0:
                    slotMachine.setMaxRounds(newVal);
                    break;
                case 1:
                    slotMachine.setEliminateNum(newVal);
                    break;
                case 2:
                    slotMachine.setMistakeRate(newVal);
                    break;
            }
        }
    }
}
