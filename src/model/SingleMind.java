package model;

import java.awt.*;
/**
 * SingleMind 类位于model包中，是AI类的其中之一
 *
 * @see User User类及model包中的各个AI类
 * @see Player Player类AI类的抽象父类
 * @see SlotMachine 老虎机类
 * @see Constants 常量类
 */
public class SingleMind extends Player{
    private int id = 7;

    /** @param slotMachine 传递老虎机参数。由于本AI的特性，需要获取轮次数及对手行为*/
    @Override
    public boolean strategy(SlotMachine slotMachine) {
        if(slotMachine.getRounds() == 1){
            return Constants.COOPERATION;
        }
        else if(opponentAction == Constants.DECEPTION){
            return !action;
        }
        else{
            return action;
        }
    }
}
