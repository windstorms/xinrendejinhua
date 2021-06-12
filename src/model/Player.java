package model;

/**
 * Plater 类位于model包中，是User及AI类的抽象父类
 *
 * @see User User类及model包中的各个AI类
 * @see SlotMachine 老虎机类
 * @see Constants 常量类
 */

public abstract class Player {

    /** */
    protected boolean choose;
    /** 自己的行为*/
    protected boolean action;
    /** 对方行为*/
    protected boolean opponentAction;
    /** 是否被欺骗*/
    protected boolean fooled = false;
    /** AI的ID*/
    private int id = 0;
    /** 得分*/
    private int score = 0;

    /** getter和setter函数们*/

    public boolean isChoose() {
        return choose;
    }

    public void setChoose(boolean choose) {
        this.choose = choose;
    }

    public boolean isOpponentAction() {
        return opponentAction;
    }

    public void setOpponentAction(boolean opponentAction) {
        this.opponentAction = opponentAction;
    }

    public void setFooled(boolean fooled) {
        this.fooled = fooled;
    }

    public boolean isAction() {
        return action;
    }

    public void setAction(boolean action) {
        this.action = action;
    }

    public void addScore(int num){
        score += num;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    /** 抽象方法决定策略，所有的AI类继承整个方法确定各自的策略*/
    abstract public boolean strategy(SlotMachine slotMachine);
}
