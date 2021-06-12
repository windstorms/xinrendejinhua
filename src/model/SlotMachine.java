package model;

import java.util.Random;
//老虎机，判定双方一次行动的结果，存储报酬，对局数等规则，单例模式

/**
 * SlotMachine 类位于model包中，用于存放游戏初始化前和过程中各种常量的值
 *
 * @see User User类及model包中的各个AI类
 * @see Player Player类AI类的抽象父类
 * @see Constants 常量类
 */

public class SlotMachine {
    /**默认设定 回合数*/
    private int maxRounds = Constants.DEFAULT_ROUNDS;
    /**默认设定 当前回合*/
    private int rounds = 0;
    /**默认设定 双合作得分*/
    private int win_win = Constants.WIN_WIN;
    /**默认设定 双欺骗得分*/
    private int dupe_dupe = Constants.DUPE_DUPE;
    /**默认设定 欺骗对合作得分*/
    private int dupe = Constants.DUPE;
    private int fooled = Constants.FOOLED;
    /**默认设定 出错概率*/
    private int mistakeRate = 0;
    /**默认设定 每次淘汰的人数*/
    private int eliminateNum = 5;

    public int getEliminateNum() {
        return eliminateNum;
    }

    public void setEliminateNum(int eliminateNum) {
        this.eliminateNum = eliminateNum;
    }

    private Random random = new Random();

    /**老虎机类采用单例模式*/
    private static SlotMachine instance = null;
    private SlotMachine(){

    }
    public static SlotMachine getInstance() {
        if (instance == null) {
            instance = new SlotMachine();
        }
        return instance;
    }


    public void setMistakeRate(int mistakeRate) {
        this.mistakeRate = mistakeRate;
    }

    public void setMaxRounds(int maxRounds) {
        this.maxRounds = maxRounds;
    }

    public void setWin_win(int win_win) {
        this.win_win = win_win;
    }

    public void setDupe_dupe(int dupe_dupe) {
        this.dupe_dupe = dupe_dupe;
    }

    public void setDupe(int dupe) {
        this.dupe = dupe;
    }

    public void setFooled(int fooled) {
        this.fooled = fooled;
    }

    /**将各个属性重置回默认值*/
    public void setDefault(){
        win_win = Constants.WIN_WIN;
        dupe_dupe = Constants.DUPE_DUPE;
        dupe = Constants.DUPE;
        fooled = Constants.FOOLED;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public int getRounds() {
        return rounds;
    }

    /** 每轮对局的对抗函数，向两个对局者传递单例的instance获取行为，随机化之后进行对局，产生结果*/
    /** @param leftPlayer @param rightPlayer 比赛模式中的两个玩家*/
    public void singleFight(Player leftPlayer,Player rightPlayer){
        boolean leftAction = leftPlayer.strategy(this);
        boolean rightAction = rightPlayer.strategy(this);
        int m1 = random.nextInt(100);
        int m2 = random.nextInt(100);
        if(m1 < mistakeRate){
            leftAction = !leftAction;
        }
        if(m2 < mistakeRate){
            rightAction = !rightAction;
        }
        leftPlayer.setAction(leftAction);
        rightPlayer.setAction(rightAction);
        leftPlayer.setOpponentAction(rightAction);
        rightPlayer.setOpponentAction(leftAction);

        if(leftAction == Constants.COOPERATION && rightAction == Constants.COOPERATION){
            leftPlayer.addScore(win_win);
            rightPlayer.addScore(win_win);
        }
        else if(leftAction == Constants.DECEPTION && rightAction == Constants.DECEPTION){
            leftPlayer.addScore(dupe_dupe);
            rightPlayer.addScore(dupe_dupe);
        }
        else if(leftAction = Constants.COOPERATION && rightAction == Constants.DECEPTION){
            leftPlayer.addScore(fooled);
            rightPlayer.addScore(dupe);
        }
        else{
            leftPlayer.addScore(dupe);
            rightPlayer.addScore(fooled);
        }
    }

    /** 对局总体的对抗函数，进行轮次计算，并调用每轮对局函数*/
    /** @param leftPlayer @param rightPlayer 比赛模式中的两个玩家*/
    public void fight(Player leftPlayer, Player rightPlayer){
        rounds++;
        for(int i = 0;i<maxRounds; i++){
            singleFight(leftPlayer, rightPlayer);
            rounds++;
        }
        leftPlayer.setFooled(false);
        rightPlayer.setFooled(false);
        setRounds(0);
        return;
    }
}
