package model;

/**
 * SingleMind 类位于model包中，是AI类的其中之一
 *
 * @see User User类及model包中的各个AI类
 * @see Player Player类AI类的抽象父类
 * @see SlotMachine 老虎机类
 * @see Constants 常量类
 */

public class Holmes extends Player{
    /**默认设定 AI编号5*/
    private int id = 5;

    /** @param slotmachine 传递老虎机参数。由于本AI的特性，需要获取轮次数及对手行为*/
    @Override
    public boolean strategy(SlotMachine slotmachine) {
        int rounds = slotmachine.getRounds();
        if(rounds <= 4){
            if(rounds > 1 && opponentAction == Constants.DECEPTION){
                fooled = true;
            }
            if(rounds != 2){
                return Constants.COOPERATION;
            }else{
                return Constants.DECEPTION;
            }
        }

        else{
            if(fooled == false){
                return Constants.DECEPTION;
            }else{
                return opponentAction;
            }
        }
    }
}
