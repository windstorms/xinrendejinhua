package model;

/**
 * SingleMind 类位于model包中，是AI类的其中之一
 *
 * @see User User类及model包中的各个AI类
 * @see Player Player类AI类的抽象父类
 * @see SlotMachine 老虎机类
 * @see Constants 常量类
 */

public class Repeater extends Player{
    /**默认设定 AI编号1*/
    private int id = 1;
    /** @param slotmachine 传递老虎机参数。由于本AI的特性，仅需要获取对手行为*/
    @Override
    public boolean strategy(SlotMachine slotmachine) {
        if(slotmachine.getRounds() == 1){
            return Constants.COOPERATION;
        }
        else{
            return opponentAction;
        }
    }
}
