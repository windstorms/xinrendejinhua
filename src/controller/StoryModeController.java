package controller;
import javafx.animation.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Translate;
import javafx.util.Duration;
import model.*;

import java.io.IOException;
import java.util.ArrayList;
/**
 * StoryModeController 类位于controller包中，比赛模式界面控制器类
 * 该类从初始界面直接进入也可再故事模式结束后自动进入。故不直接写出前置界面。
 * @see FinalScoreController 比赛模式之后的分数结算界面控制器类，从本页面进行完全部5轮比赛跳转
 */
public class StoryModeController {
    /** 人物的表情列表*/
    public ArrayList<Image>  emojiList = new ArrayList<Image>();

    /** 对局数*/
    int [] roundNums = new int[]{5,4,4,5,7};
    /** 右侧AIid*/
    int rightid = 0;
    /** 玩家（左侧）得分*/
    int leftScore = 0;
    /** 一系列图片*/
    @FXML
    private ImageView soloPlace;
    @FXML
    private ImageView leftPlayerView;
    @FXML
    private ImageView rightPlayerView;
    @FXML
    private ImageView leftEmoji;
    @FXML
    private ImageView rightEmoji;

    @FXML
    private ImageView leftCoin;
    @FXML
    private ImageView rightCoin;

    @FXML
    private ImageView rightReward;
    @FXML
    private ImageView leftReward;
    @FXML
    private ImageView leftCover;
    @FXML
    private ImageView rightCover;

    /** 欺骗与合作按钮*/
    @FXML
    private Button dupeButton;
    /** 欺骗与合作按钮*/
    @FXML
    private Button cooperationButton;

    @FXML
    private Label leftScoreLabel;
    @FXML
    private Label rightScoreLabel;
    @FXML
    private Label totalScore;
    @FXML
    private Label opponent;
    @FXML
    private Label backLabel;

    /** 两个玩家，左侧为玩家，右侧是AI*/
    private Player leftPlayer = new User();
    private Player rightPlayer;

    private SequentialTransition leftAction;
    private SequentialTransition rightAction;
    private SequentialTransition coinIn_l;
    private SequentialTransition coinIn_r;

    private int roudNum = 1;
    /**生成硬币运动的顺序动画*/
    private SequentialTransition coinReward(ImageView coin){
        SequentialTransition st = new SequentialTransition();
        FadeTransition ft = new FadeTransition();
        ft.setDuration(Duration.seconds(0.01));
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.setNode(coin);
        st.getChildren().add(ft);

        int inc = 1;
        if(coin == leftReward){
            inc = -1;
        }
        for(int t = 0; t< 6;t++){
            TranslateTransition parabola = new TranslateTransition();
            parabola.setDuration(Duration.seconds(0.05));
            parabola.setNode(coin);
            parabola.setFromX(22*t*inc);
            parabola.setToX(22*(t+1)*inc);
            parabola.setFromY(t*(t-6)*10);
            parabola.setToY((t+1)*(t-5)*10);
            st.getChildren().add(parabola);
        }
        FadeTransition ft1 = new FadeTransition();
        ft1.setDuration(Duration.seconds(0.01));
        ft1.setFromValue(1);
        ft1.setToValue(0);
        ft1.setNode(coin);
        st.getChildren().add(ft1);

        return st;
    }
    /**玩家走到老虎机前的顺序动画*/
    private SequentialTransition coinOperated(ImageView coin, ImageView player){

        SequentialTransition st = new SequentialTransition();
        int fromY;
        int toY;
        int inc = 1;
        if(player == rightPlayerView){
            inc = -1;
        }
        FadeTransition coinDisplay = new FadeTransition();
        coinDisplay.setNode(coin);
        coinDisplay.setDuration(Duration.seconds(0.07));
        coinDisplay.setFromValue(0);
        coinDisplay.setToValue(1);
        st.getChildren().add(coinDisplay);

        TranslateTransition coinOut = new TranslateTransition();
        coinOut.setNode(coin);
        coinOut.setDuration(Duration.seconds(0.12));
        coinOut.setFromX(-30*inc);
        coinOut.setToX(0);
        coinOut.setFromY(30);
        coinOut.setToY(0);

        st.getChildren().add(coinOut);
        for(int t = 0; t< 6;t++){
            TranslateTransition forward = new TranslateTransition();
            forward.setDuration(Duration.seconds(0.1));
            forward.setNode(player);
            forward.setFromX(12.5*t*inc);
            forward.setToX(12.5*(t+1)*inc);
            if(t % 2 == 0){
                fromY = 0;
                toY = -10;
            }
            else{
                fromY = -10;
                toY = 0;
            }
            forward.setFromY(fromY);
            forward.setToY(toY);
            st.getChildren().add(forward);
        }
        return  st;
    }
    /**1.整合一次行动双方的所有运动动画
     * 2.根据双方不同表现切换表情
     * 3.自动更新积分器，切换对手*/
    private void animGenerate(){
        SlotMachine slotMachine = SlotMachine.getInstance();
        leftAction = new SequentialTransition();
        rightAction = new SequentialTransition();


        leftAction.getChildren().addAll(coinIn_l);
        rightAction.getChildren().add(coinIn_r);

        slotMachine.setRounds(roudNum);
        roudNum++;
        slotMachine.singleFight(leftPlayer,rightPlayer);

        if(leftPlayer.isAction() == Constants.DECEPTION){
            leftAction.getChildren().add(dupeCoinSet(leftCoin));

        }
        else{
            leftAction.getChildren().add(coopCoinSet(leftCoin));
        }
        if(rightPlayer.isAction() == Constants.DECEPTION){
            rightAction.getChildren().add(dupeCoinSet(rightCoin));
        }
        else{
            rightAction.getChildren().add(coopCoinSet(rightCoin));
        }
        TranslateTransition wait = new TranslateTransition();
        wait.setDuration(Duration.seconds(0.5));
        leftAction.getChildren().add(wait);
        rightAction.getChildren().add(wait);
        leftAction.setOnFinished(new EventHandler(){

            @Override
            public void handle(Event event) {
                if(leftPlayer.isAction() == Constants.COOPERATION){
                    leftCover.setOpacity(1);
                    if(rightPlayer.isAction() == Constants.COOPERATION){
                        leftEmoji.setImage(emojiList.get(4));
                        rightEmoji.setImage(emojiList.get(4));
                    }else{
                        leftEmoji.setImage(emojiList.get(0));
                        rightEmoji.setImage(emojiList.get(1));
                    }
                }
                else{
                    if(rightPlayer.isAction() == Constants.COOPERATION){
                        leftEmoji.setImage(emojiList.get(1));
                        rightEmoji.setImage(emojiList.get(0));
                    }else{
                        leftEmoji.setImage(emojiList.get(6));
                        rightEmoji.setImage(emojiList.get(6));
                    }
                }
                if(rightPlayer.isAction() == Constants.COOPERATION){
                    rightCover.setOpacity(1);
                }
                SequentialTransition c = coinOperated(leftCoin,leftPlayerView);
                c.setRate(-1);
                c.play();
                c.setOnFinished(new EventHandler(){
                    @Override
                    public void handle(Event event) {
                        leftCover.setOpacity(0);
                        rightCover.setOpacity(0);
                        SequentialTransition coinSt_l = coinReward(leftReward);
                        SequentialTransition coinSt_r = coinReward(rightReward);
                        TranslateTransition wait = new TranslateTransition();
                        wait.setDuration(Duration.seconds(1));
                        if(leftPlayer.isAction() == Constants.COOPERATION && rightPlayer.isAction() == Constants.COOPERATION){
                            leftEmoji.setImage(emojiList.get(5));
                            rightEmoji.setImage(emojiList.get(5));
                            coinSt_l.setCycleCount(Constants.WIN_WIN);
                            coinSt_r.setCycleCount(Constants.WIN_WIN);
                            leftScore += 2;
                        }
                        if(leftPlayer.isAction() == Constants.COOPERATION && rightPlayer.isAction() == Constants.DECEPTION){
                            leftEmoji.setImage(emojiList.get(2));
                            rightEmoji.setImage(emojiList.get(3));
                            coinSt_l = new SequentialTransition();
                            coinSt_l.getChildren().add(wait);
                            coinSt_r.setCycleCount(Constants.DUPE);
                            leftScore -= 1;
                        }
                        if(leftPlayer.isAction() == Constants.DECEPTION && rightPlayer.isAction() == Constants.COOPERATION){
                            leftEmoji.setImage(emojiList.get(3));
                            rightEmoji.setImage(emojiList.get(2));
                            coinSt_l.setCycleCount(Constants.DUPE);
                            leftScore += 3;
                            coinSt_r = new SequentialTransition();
                            coinSt_r.getChildren().add(wait);
                        }
                        if(leftPlayer.isAction() == Constants.DECEPTION && rightPlayer.isAction() == Constants.DECEPTION){
                            leftEmoji.setImage(emojiList.get(7));
                            rightEmoji.setImage(emojiList.get(7));
                            coinSt_l = new SequentialTransition();
                            coinSt_r = new SequentialTransition();
                            coinSt_l.getChildren().add(wait);
                            coinSt_r.getChildren().add(wait);
                            leftScore += 0;
                        }
                        coinSt_l.play();
                        coinSt_r.play();
                        coinSt_l.setOnFinished(new EventHandler(){

                            @Override
                            public void handle(Event event) {
                                totalScore.setText("你的总分："+String.valueOf(leftScore));
                                dupeButton.setDisable(false);
                                cooperationButton.setDisable(false);
                                if(roudNum == roundNums[rightid]+1){
                                    rightid++;
                                    rightChange();
                                    rightEmoji.setImage(emojiList.get(8));
                                    leftEmoji.setImage(emojiList.get(8));
                                    roudNum = 1;
                                    leftScoreLabel.setText("0");
                                    rightScoreLabel.setText("0");
                                    leftPlayer.setScore(0);
                                    slotMachine.setRounds(1);
                                    if(rightid < 5){
                                        rightPlayerView.setImage(rightImages[rightid]);
                                        opponent.setText("你的第"+String.valueOf(rightid+1)+"/5个对手");
                                    }
                                    else{
                                        FXMLLoader f = new FXMLLoader();
                                        f.setLocation(f.getClassLoader().getResource("view/FinalScoreView.fxml"));
                                        AnchorPane an = null;
                                        try {
                                            an = (AnchorPane)f.load();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        Main.changeRoot(an);
                                        FinalScoreController controller = f.getController();
                                        controller.setScore(leftScore);
                                    }
                                }
                                else{
                                    leftScoreLabel.setText(String.valueOf(leftPlayer.getScore()));
                                    rightScoreLabel.setText(String.valueOf(rightPlayer.getScore()));
                                }
                            }
                        });
                    }
                });
            }
        });
        rightAction.setOnFinished(new EventHandler(){

            @Override
            public void handle(Event event) {
                SequentialTransition c = coinOperated(rightCoin,rightPlayerView);
                c.setRate(-1);
                c.play();
            }
        });

    }

    /** 按钮初始化，采用匿名类的方式*/
    @FXML
    private void buttonInit(){
        dupeButton.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                dupeButton.setDisable(true);
                cooperationButton.setDisable(true);
                leftPlayer.setChoose(Constants.DECEPTION);
                animGenerate();
                leftAction.play();
                rightAction.play();
            }
        });
        cooperationButton.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                dupeButton.setDisable(true);
                cooperationButton.setDisable(true);
                leftPlayer.setChoose(Constants.COOPERATION);
                animGenerate();
                leftAction.play();
                rightAction.play();
            }
        });
    }

    /** 加载更改右侧AI*/
    private void rightChange(){
            switch (rightid){
                case 0:
                    rightPlayer = new Repeater();
                    break;
                case 1:
                    rightPlayer = new Swindler();
                    break;
                case 2:
                    rightPlayer = new Cutie();
                    break;
                case 3:
                    rightPlayer = new Gangster();
                    break;
                case 4:
                    rightPlayer = new Holmes();
                    break;
            }
    }
    /**选择欺骗时硬币的运动动画*/
    private  SequentialTransition dupeCoinSet(ImageView coin){
        int inc = 1;
        if(coin == rightCoin){
            inc = -1;
        }
        SequentialTransition dupeCoin = new SequentialTransition();

        TranslateTransition fake = new TranslateTransition();
        fake.setDuration(Duration.seconds(0.15));
        fake.setNode(coin);
        fake.setFromX(75*inc);
        fake.setToX(80*inc);
        fake.setFromY(0);
        fake.setToY(25);

        TranslateTransition back = new TranslateTransition();
        back.setDuration(Duration.seconds(0.05));
        back.setNode(coin);
        back.setFromX(80*inc);
        back.setToX(75*inc);
        back.setFromY(25);
        back.setToY(-10);

        dupeCoin.getChildren().addAll(fake,back);

        return dupeCoin;

    }
    /**选择合作时硬币的运动动画*/
    private SequentialTransition coopCoinSet(ImageView coin){
        int inc = 1;
        if(coin == rightCoin){
            inc = -1;
        }
        SequentialTransition coopCoin = new SequentialTransition();
        TranslateTransition real = new TranslateTransition();
        real.setDuration(Duration.seconds(0.2));
        real.setNode(coin);
        real.setFromX(75*inc);
        real.setToX(85*inc);
        real.setFromY(0);
        real.setToY(45);
        coopCoin.getChildren().addAll(real);

        return coopCoin;
    }
    Image []rightImages = new Image[5];

    /**从老虎机前走回的顺序动画*/
    private void backStepInit(){
        Tooltip back = new Tooltip("返回主菜单");
        back.setShowDelay(Duration.millis(50));
        back.setShowDuration(Duration.INDEFINITE);
        backLabel.setTooltip(back);
        backLabel.setOnMouseClicked(new EventHandler(){

            @Override
            public void handle(Event event) {
                FXMLLoader f = new FXMLLoader();
                f.setLocation(f.getClassLoader().getResource("view/BeginView.fxml"));
                AnchorPane an = null;
                try {
                    an = (AnchorPane)f.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Main.changeRoot(an);
                Main.littleWindow();
            }
        });
    }

    /**加载图片*/
    private void loadImage(){
        Image img1 = new Image("./img/normal/复读机.png");
        Image img2 = new Image("./img/normal/老油条.png");
        Image img3 = new Image("./img/normal/小可爱.png");
        Image img4 = new Image("./img/normal/黑帮老铁.png");
        Image img5 = new Image("./img/normal/福尔摩斯.png");
        rightImages[0] = img1;
        rightImages[1] = img2;
        rightImages[2] = img3;
        rightImages[3] = img4;
        rightImages[4] = img5;

        emojiList.add(new Image("./img/emoji/被骗.png"));
        emojiList.add(new Image("./img/emoji/得手.png"));
        emojiList.add(new Image("./img/emoji/委屈.png"));
        emojiList.add(new Image("./img/emoji/奸诈.png"));
        emojiList.add(new Image("./img/emoji/愉快.png"));
        emojiList.add(new Image("./img/emoji/得意.png"));
        emojiList.add(new Image("./img/emoji/怀疑.png"));
        emojiList.add(new Image("./img/emoji/生气.png"));
        emojiList.add(new Image("./img/emoji/正常.png"));
    }
    /**把硬币坐标与人物坐标绑定，硬币的cover与硬币坐标绑定*/
    private void coordinateBinding(){
        leftPlayerView.translateXProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                leftCoin.setTranslateX(newValue.doubleValue());
                leftEmoji.setTranslateX(newValue.doubleValue());
            }
        });
        leftPlayerView.translateYProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                leftCoin.setTranslateY(newValue.doubleValue());
                leftEmoji.setTranslateY(newValue.doubleValue());
            }
        });

        rightPlayerView.translateXProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                rightCoin.setTranslateX(newValue.doubleValue());
                rightEmoji.setTranslateX(newValue.doubleValue());
            }
        });
        rightPlayerView.translateYProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                rightCoin.setTranslateY(newValue.doubleValue());
                rightEmoji.setTranslateY(newValue.doubleValue());
            }
        });
        leftCoin.translateXProperty().addListener(new ChangeListener<Number>(){
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                leftCover.setTranslateX(newValue.doubleValue());
            }
        });
        leftCoin.translateYProperty().addListener(new ChangeListener<Number>(){
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                leftCover.setTranslateY(newValue.doubleValue());
            }
        });

        rightCoin.translateXProperty().addListener(new ChangeListener<Number>(){
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                rightCover.setTranslateX(newValue.doubleValue());
            }
        });
        rightCoin.translateYProperty().addListener(new ChangeListener<Number>(){
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                rightCover.setTranslateY(newValue.doubleValue());
            }
        });
    }
    /**界面初始化函数*/
    @FXML
    private void initialize(){
        loadImage();
        buttonInit();
        coinIn_l = coinOperated(leftCoin,leftPlayerView);
        coinIn_r = coinOperated(rightCoin,rightPlayerView);
        coordinateBinding();
        rightPlayer = new Repeater();
        backStepInit();
    }

}
