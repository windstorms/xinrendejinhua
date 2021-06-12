package model;

/**
 * SingleMind 类位于model包中，是AI类的其中之一
 *
 * @see User User类及model包中的各个AI类
 * @see Player Player类AI类的抽象父类
 * @see SlotMachine 老虎机类
 * @see Constants 常量类
 */

public class Swindler extends Player{
    /**默认设定 AI编号2*/
    private int id = 2;
    /** @param slotmachine 传递老虎机参数。由于本AI的特性，不需要获取任何参数*/
    @Override
    public boolean strategy(SlotMachine slotmachine) {
        return Constants.DECEPTION;
    }
}
