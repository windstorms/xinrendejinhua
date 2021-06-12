package model;

/**
 * User 类位于model包中，作为各个AI类的共同父类存在
 *
 * @see Cutie 等model包中的各个AI类
 * @see Player Player类AI类的抽象父类
 * @see SlotMachine 老虎机类
 * @see Constants 常量类
 */
public class User extends Player{

    /** @param slotMachine 向各个AI的策略中传递整个老虎机作为参数，让其更便利的获取回合数等参数*/
    @Override
    public boolean strategy(SlotMachine slotMachine) {
        return choose;
    }

}
