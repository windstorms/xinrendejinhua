package model;

import java.util.Random;

/**
 * SingleMind 类位于model包中，是AI类的其中之一
 *
 * @see User User类及model包中的各个AI类
 * @see Player Player类AI类的抽象父类
 * @see SlotMachine 老虎机类
 * @see Constants 常量类
 */


public class Randotron extends Player{
    /**默认设定 AI编号8*/
    private int id = 8;
    /** @param slotMachine 传递老虎机参数。由于本AI的特性，不需要获取参数*/
    @Override
    public boolean strategy(SlotMachine slotMachine) {
        Random random = new Random();
        int r = random.nextInt(100);
        if(r <= 50){
            return Constants.COOPERATION;
        }else{
            return Constants.DECEPTION;
        }
    }
}
