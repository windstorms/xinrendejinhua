package controller;

import javafx.animation.SequentialTransition;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import model.*;


import java.io.IOException;
import java.util.ArrayList;
/**
 * FiveBattleController 类位于controller包中，比赛模式末端的五人对局界面的部分控制器类，主要负责右侧对局结果信息的显示
 *
 * @see FinalScoreController 比赛模式开始的对局结束的结算界面，从该页面点击”会发生什么呢？“按钮跳转
 * @see FiveBattleController 同为比赛模式末端的五人对局的部分界面控制器类，本控制器类与其共同构成这部分的界面
 * @see SandBoxController 沙盒模式界面控制器类，从本页面点击”会发生什么呢？“按钮跳转
 */
//五个角色对决界面的控制器
public class FiveBattleController {
    /** 对局数*/
    private int roundNum = 0;
    /** 对局结果对应的图片*/
    @FXML
    private ImageView round1v;
    @FXML
    private ImageView round2v;
    @FXML
    private ImageView round3v;
    @FXML
    private ImageView round4v;
    @FXML
    private ImageView round5v;
    @FXML
    private ImageView round6v;
    @FXML
    private ImageView round7v;
    @FXML
    private ImageView round8v;
    @FXML
    private ImageView round9v;
    @FXML
    private ImageView round10v;
    private ImageView[] roundsView;

    /** 代变对战的黄线*/
    private ArrayList<Shape> battleLine = new ArrayList<Shape>();

    /** 文本区域*/
    @FXML
    private TextArea tipArea;
    /** 每局战况*/
    @FXML
    private Label everyRoundLabel;

    /** 选择的AI*/
    @FXML
    public Label userChose;
    /** 右侧文本框，显示下面的故事*/
    @FXML
    private TextArea transitionArea;
    @FXML
    private Label transitionLabel;
    /** 总分*/
    @FXML
    private Label totalScore;
    /** 回合数控制（下一局）*/
    @FXML
    private Button roundControll;
    /** “会发生什么呢？”按钮，点击跳转到下一个界面*/
    @FXML
    private Button whatHappen;
    /** 最后显示结果，并解释原因的文本*/
    @FXML
    private TextArea endTip;

    /** 本模式中对局AI是固定的，所以这里直接加载的默认值，且不可改变*/
    private Player[] players = new Player[]{new Repeater(),new Swindler(),new Cutie(),
                                            new Gangster(),new Holmes()};
    /** 老虎机*/
    private SlotMachine slotMachine = SlotMachine.getInstance();

    /** 对局结果的对应的图象列表*/
    private ArrayList<Image> result = new ArrayList<Image>();
    /** 故事文本*/
    private String[] tips = new String[]{"喔对了...",
                                        "...你可能有点怀疑我开头讲的那个一战战壕里圣\n诞夜休战的故事。那真的是一个小概率事件吗？",
                                        "没错,休战看起来还挺戏剧化的,但是既不能说是\n独一无二的,也不能说是不寻常",
                                        "尽管有明确和严格的军令禁止，但是许多前线部\n队都会不约而同地达成和平",
                                        "而且事实上,即使在圣诞夜前,有一些前线部队就\n已经悄悄达成了非官方的和平",
                                        "他们把这个叫做「互惠宽容(live and let \nlive)」系统,本质上来讲,就是你不打我\n,我也不打你。这种情况在很多地方都适用",
                                        "你可能还有疑惑。一般士兵并不会自发地与敌人\n达成和平，为什么这种情况在阵地战时就\n格外不同了呢?",
                                        "那是因为,阵地战有这样一个与众不同的特性:在\n阵地战中,你每天会面对同一批军人",
                                        "这是一个重复的游戏,这一点使得阵地战与其他\n的战争完全不同",
                                        "嗯,好吧,我们先回到比赛里,这次的赢家就是..\n"};


    /** 对战函数，由于这里对战的参数固定且与沙盒模式中存在一定区别，而且需要获得参数，所以另写了一个对战函数
     * @param left 左侧对战AI的ID
     * @param right 右侧对战AI的ID*/
    private void battle(int left, int right){
        Player leftPlayer = players[left];
        Player rightPlayer = players[right];
        leftPlayer.setFooled(false);
        rightPlayer.setFooled(false);
        rightPlayer.setScore(0);
        leftPlayer.setScore(0);

        int round;
        for(round=1; round<=10; round++){
            slotMachine.setRounds(round);
            slotMachine.singleFight(leftPlayer,rightPlayer);
            if(leftPlayer.isAction() == Constants.COOPERATION && rightPlayer.isAction() == Constants.COOPERATION){
                roundsView[round-1].setImage(result.get(0));
            }
            if(leftPlayer.isAction() == Constants.COOPERATION && rightPlayer.isAction() == Constants.DECEPTION){
                roundsView[round-1].setImage(result.get(2));
            }
            if(leftPlayer.isAction() == Constants.DECEPTION && rightPlayer.isAction() == Constants.COOPERATION){
                roundsView[round-1].setImage(result.get(1));
            }
            if(leftPlayer.isAction() == Constants.DECEPTION && rightPlayer.isAction() == Constants.DECEPTION){
                roundsView[round-1].setImage(result.get(3));
            }
            totalScore.setText("总分  "+Constants.PLAYER_NAMES[left]+" : "+String.valueOf(leftPlayer.getScore())+"  对  "+
                                Constants.PLAYER_NAMES[right]+" : "+String.valueOf(rightPlayer.getScore()));
        }
    }

    public ArrayList<Shape> getBattleLine() {
        return battleLine;
    }
    public void setBattleLine(ArrayList<Shape> battleLine) {
        this.battleLine = battleLine;
    }
    /** 初始化函数，采用匿名类的方式初始化界面上的各个组件*/
    @FXML
    private void initialize(){
        roundsView = new ImageView[]{round1v,round2v,round3v,round4v,round5v,
                round6v,round7v,round8v,round9v,round10v};

        result.add(new Image("./img/widget/condition1.png"));
        result.add(new Image("./img/widget/condition2.png"));
        result.add(new Image("./img/widget/condition3.png"));
        result.add(new Image("./img/widget/condition4.png"));

        slotMachine.setDefault();
        slotMachine.setMistakeRate(0);
        slotMachine.setMaxRounds(10);
        roundControll.setOnAction(new EventHandler(){
            @Override
            public void handle(Event event) {

                if(roundNum < 10){
                    tipArea.setText(tips[roundNum]);

                    battleLine.get(roundNum).setStroke(Color.rgb(255,218,57));
                    if(roundNum == 0){
                        transitionArea.setOpacity(0);

                        tipArea.setOpacity(1);
                        everyRoundLabel.setOpacity(1);

                        roundControll.setLayoutY(450);
                    }
                    if(roundNum <4){
                        battle(0,roundNum+1);
                    }else if(roundNum < 7){
                        battle(1,roundNum-2);
                    }else if(roundNum<9){
                        battle(2,roundNum-4);
                    }else{
                        battle(3,roundNum-5);
                    }
                }
                if(roundNum>0 && roundNum<10){
                    battleLine.get(roundNum-1).setStroke(Color.rgb(0,0,0));
                    battleLine.get(roundNum-1).setOpacity(0.27);
                }
                if(roundNum == 10){
                    battleLine.get(9).setStroke(Color.rgb(0,0,0));
                    battleLine.get(9).setOpacity(0.27);
                    tipArea.setOpacity(0);
                    endTip.setOpacity(1);
                    roundControll.setOpacity(0);
                    roundControll.setDisable(true);
                    whatHappen.setOpacity(1);
                    whatHappen.setDisable(false);
                    totalScore.setText("");

                }else{
                    if(roundNum == 9){

                        roundControll.setText("噔噔噔噔....");
                    }
                    else {
                        roundControll.setText("下一局");
                    }
                }
                roundNum++;
            }
        });
        whatHappen.setOnAction(new EventHandler(){

            @Override
            public void handle(Event event) {

                SlotMachine slotMachine = SlotMachine.getInstance();
                slotMachine.setMaxRounds(10);
                slotMachine.setMistakeRate(5);
                slotMachine.setWin_win(2);
                FXMLLoader f = new FXMLLoader();
                f.setLocation(f.getClassLoader().getResource("view/RuleView.fxml"));
                AnchorPane an = null;
                try {
                    an = (AnchorPane)f.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Main.changeRoot(an);
            }
        });
    }
}
