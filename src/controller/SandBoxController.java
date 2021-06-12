package controller;

import javafx.animation.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

import java.io.IOException;
import java.util.*;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import model.*;

import static java.lang.Math.floor;
import model.NumChanger;
/**
 * SandBoxController 类位于controller包中，沙盒模式界面控制器类
 * 该类可以从比赛模式最后跳转，也可以从初始界面直接进入。故不直接写出前置界面。
 * @see SandBoxLearnController 沙盒模式升华学习界面控制器类，从本页面点击”看看今天学到什么“按钮跳转
 */
public class SandBoxController {
    /** 步骤状态*/
    private int stepStatus = 0;

    public void setStepStatus(int stepStatus) {
        this.stepStatus = stepStatus;
    }

    private NumChanger nc_up;
    private NumChanger nc_out;
    private NumChanger nc_inside;
    private NumChanger nc_down;
    @FXML
    public AnchorPane battlePlace;
    @FXML
    public VBox processBox;
    @FXML
    private AnchorPane sandBox;
    @FXML
    private Slider repeater_sl;
    @FXML
    private Slider swindler_sl;
    @FXML
    private Slider cutie_sl;
    @FXML
    private Slider gangster_sl;
    @FXML
    private Slider holmes_sl;
    @FXML
    private Slider rereadduck_sl;
    @FXML
    private Slider singlemind_sl;
    @FXML
    private Slider randotron_sl;
    @FXML
    private Slider roundsSlider;
    @FXML
    private Slider evolutionSlider;
    @FXML
    private Slider mistakeSlider;

    @FXML
    private Label lock1;
    @FXML
    private Label lock2;
    @FXML
    private Label lock3;
    @FXML
    private Label lock4;
    @FXML
    private Label lock5;
    @FXML
    private Label lock6;
    @FXML
    private Label lock7;
    @FXML
    private Label lock8;
    @FXML
    private Label repeater_l;
    @FXML
    private Label swindler_l;
    @FXML
    private Label cutie_l;
    @FXML
    private Label gangster_l;
    @FXML
    private Label holmes_l;
    @FXML
    private Label rereadduck_l;
    @FXML
    private Label singlemind_l;
    @FXML
    private Label randotron_l;

    @FXML
    private Label roundsLabel;
    @FXML
    private Label evolutionLabel;
    @FXML
    private Label mistakeLabel;
    @FXML
    private Label arrow1;
    @FXML
    private Label arrow2;
    @FXML
    private Label arrow3;
    @FXML
    private Label arrow4;
    @FXML
    private Label arrow5;
    @FXML
    private Label arrow6;
    @FXML
    private Label arrow7;
    @FXML
    private Label arrow8;
    @FXML
    private Label arrow9;
    @FXML
    private Label arrow10;
    @FXML
    private Label arrow11;
    @FXML
    private Label arrow12;
    @FXML
    private Label arrow13;
    @FXML
    private Label arrow14;
    @FXML
    private Label arrow15;
    @FXML
    private Label arrow16;

    @FXML
    private Label backLabel;

    @FXML
    private Canvas venue;

    @FXML
    private AnchorPane yellowLinePane;
    @FXML
    private AnchorPane labelPane;
    @FXML
    private Button beginEndButton;
    @FXML
    private Button stepButton;
    @FXML
    private Button resetButton;

    public Button getResetButton() {
        return resetButton;
    }
    @FXML
    private Button defaultReward;
    @FXML
    private Button whatWeLearn;

    @FXML
    private ImageView r_winwin_view = new ImageView();
    @FXML
    private ImageView l_winwin_view = new ImageView();
    @FXML
    private ImageView r_dupe_view = new ImageView();
    @FXML
    private ImageView l_dupe_view = new ImageView();
    @FXML
    private ImageView r_fooled_view = new ImageView();
    @FXML
    private ImageView l_fooled_view = new ImageView();
    @FXML
    private ImageView r_dupedupe_view = new ImageView();
    @FXML
    private ImageView l_dupedupe_view = new ImageView();

    private ArrayList<Integer> loser = new ArrayList<Integer>();
    private ArrayList<Integer> winner = new ArrayList<Integer>();
    private ArrayList<Integer> playerList = new ArrayList<Integer>();


    Vector<Double> xi = new Vector<Double>();
    Vector<Double> yi = new Vector<Double>();
    Vector<Double> labelXi = new Vector<Double>();
    Vector<Double> labelYi = new Vector<Double>();

    private ArrayList<Slider> playerSliders = new ArrayList<Slider>();
    private ArrayList<Slider> changeable = new ArrayList<Slider>();
    private ArrayList<Slider> fixed = new ArrayList<Slider>();

    private ArrayList<Label> scoreLabels = new ArrayList<Label>();
    private ArrayList<Image> miniImage = new ArrayList<Image>();
    private ArrayList<ImageView> miniPlayerImgView = new ArrayList<ImageView>();
    private ArrayList<ImageView> lockImageView = new ArrayList<ImageView>();

    private ArrayList<Image> posNumImage = new ArrayList<Image>();
    private ArrayList<Image> negNumImage = new ArrayList<Image>();

    private ArrayList<Boolean> lockStatus = new ArrayList<Boolean>();

    private ArrayList<Player> playerInstance = new ArrayList<Player>();

    private ArrayList<Label> playerLabels = new ArrayList<Label>();
    private ArrayList<Label> lockLabels = new ArrayList<Label>();

    private Image lockImage = new Image("./img/widget/lock.png",25,25,
            true,true,true);
    private Image openImage = new Image("./img/widget/open.png",25,25,
            true,true,true);

    private SliderListener[] listeners = new SliderListener[8];

    private Timeline rotateLines = new Timeline();

    public ArrayList<Integer> getPlayerList() {
        return playerList;
    }

    /**控制玩家数量的滑块的监听器*/
    class SliderListener implements ChangeListener<Number>{
        int i;
        public void setI(int i){
            this.i = i;
        }
        Slider slider = playerSliders.get(i);

        boolean lock = lockStatus.get(i);
        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            evolutePause();
            int difference = (int)floor((double)newValue) - (int)floor((double)oldValue);
            int inc;
            if (difference != 0) {
                Slider slideri = playerSliders.get(i);
                removeChangeableListener(slideri);
                int tmp = difference;
                int j = 0;
                int newVal;

                if(tmp > 0){
                    while(tmp != 0){
                        Slider sliderj = changeable.get(j);
                        if(sliderj == playerSliders.get(i)){
                            j++;
                            sliderj = changeable.get(j);
                        }
                        int p = (int)(sliderj.getValue()-sliderj.getMin());
                        int id = playerSliders.indexOf(sliderj);
                        if(tmp > p){
                            tmp -= p;
                            newVal = (int)sliderj.getMin();
                            sliderj.setValue(newVal);
                            updateLabel(id,newVal);
                        }
                        else if((int)sliderj.getValue() != 0){
                            newVal = (int)sliderj.getValue()-tmp;
                            sliderj.setValue(newVal);
                            updateLabel(id,newVal);
                            tmp = 0;
                        }
                        j++;
                    }
                }
                else{
                    while(tmp != 0){
                        Slider sliderj = changeable.get(j);
                        if(sliderj == playerSliders.get(i)){
                            j++;
                            sliderj = changeable.get(j);
                        }
                        int p = (int)(sliderj.getMax()-sliderj.getValue());
                        int id = playerSliders.indexOf(sliderj);
                        if(-tmp > p){
                            tmp += p;
                            newVal = (int)sliderj.getMax();
                            sliderj.setValue(newVal);
                            updateLabel(id,newVal);
                        }
                        else if((int)sliderj.getValue() != sliderj.getMax()){
                            newVal = (int)sliderj.getValue()-tmp;
                            sliderj.setValue(newVal);
                            updateLabel(id,newVal);
                            tmp = 0;
                        }
                        j++;
                    }
                }
                int current = (int)floor((double)oldValue);
                int playerNum = current+difference;
                updateLabel(i,playerNum);
                updatePlayerList();
                updateMiniView();
                returnChangeableListener(slideri);
            }
        }
    }

    /**加载图片资源*/
    private void loadHats(){
        for (int i = 0; i < 8; i++) {
            Image img = new Image("./img/hat/" + Constants.PLAYER_NAMES[i] + "hat.png",
                    45,45,true,true,true);
            ImageView imageView = new ImageView(img);
            playerLabels.get(i).setGraphic(imageView);
        }
    }

    /**加载图片资源*/
    private void loadMiniImage(){
        for(int i=0;i <8 ;i++){
            Image img = new Image("./img/mini/" + Constants.PLAYER_NAMES[i] + "mini.png",
                    90,0,true,true,true);
            miniImage.add(img);
        }
    }

    /**加载图片资源*/
    private void loadMiniView(){
        for(int i=0 ; i<25 ; i++){
            ImageView imageView = new ImageView();
            imageView.setLayoutX(xi.get(i)+Constants.XBIAS);
            imageView.setLayoutY(yi.get(i)+Constants.YBIAS);
            miniPlayerImgView.add(imageView);
            labelPane.getChildren().add(imageView);
            //sandBox.getChildren().add(imageView);
        }
        updateMiniView();
    }
    /**加载图片资源*/
    private void loadArry(){
        playerLabels.addAll(Arrays.asList(repeater_l,swindler_l,cutie_l,gangster_l,
                holmes_l,rereadduck_l,singlemind_l,randotron_l));
        playerSliders.addAll(Arrays.asList(repeater_sl,swindler_sl,cutie_sl,gangster_sl,
                holmes_sl,rereadduck_sl,singlemind_sl,randotron_sl));
        lockLabels.addAll(Arrays.asList(lock1,lock2,lock3,lock4,lock5,lock6,lock7,lock8));
    }
    /**设定玩家比例默认值*/
    private void playListInit(){
        playerList.clear();
        for(int i=0; i <8 ;i++){
            int j = 3;
            while(j > 0){
                playerList.add(i);
                j--;
            }
        }
        playerList.add(7);
    }
    /**规则模式初始化*/
    private void rulesInite(){
        roundsLabel.setText("每一局进行10轮比赛");
        DiscreteListener roundslis = new DiscreteListener(roundsLabel);
        roundslis.setRule(0);
        roundsSlider.valueProperty().addListener(roundslis);

        evolutionLabel.setText("每次大赛后淘汰最低分的5名玩家,繁殖最高分的5位玩家");
        DiscreteListener evolis = new DiscreteListener(evolutionLabel);
        evolutionSlider.valueProperty().addListener(evolis);
        evolis.setRule(1);

        mistakeLabel.setText("每轮有5%的几率会犯错");
        DiscreteListener mislis = new DiscreteListener(mistakeLabel);
        mistakeSlider.valueProperty().addListener(mislis);
        mislis.setRule(2);
        SlotMachine.getInstance().setMistakeRate(5);
    }
    /**玩家对决黄线的动画效果初始化*/
    private void yellowLinePaneInit(){
        double x0 = xi.get(0);
        double y0 = yi.get(0);
        for(int i=1; i<25; i++){
            Line line = new Line(x0,y0,xi.get(i),yi.get(i));
            line.setStrokeWidth(2);
            line.setStroke(Color.rgb(255,218,97));
            yellowLinePane.getChildren().add(line);
        }

        Rotate rotate = new Rotate(0,200,200);

        KeyValue angle1 = new KeyValue(rotate.angleProperty(),0);
        KeyFrame kf1 = new KeyFrame(Duration.seconds(0),angle1);
        KeyValue angle2 = new KeyValue(rotate.angleProperty(),360);
        KeyFrame kf2 = new KeyFrame(Duration.seconds(0.5),angle2);
        KeyValue opacity3 = new KeyValue(yellowLinePane.opacityProperty(),0);
        KeyFrame kf3 = new KeyFrame(Duration.seconds(0.8),opacity3);

        yellowLinePane.getTransforms().add(rotate);
        yellowLinePane.setOpacity(0);
        rotateLines.getKeyFrames().addAll(kf1,kf2,kf3);

    }
    private boolean begin = true;
    private Animation automaticEvolution;

    public Button getStepButton() {
        return stepButton;
    }

    /**按钮初始化，采用匿名类模式*/
    public void buttonInit(){
        beginEndButton.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {


                if(begin == true){
                    automaticEvolution = new Timeline(new KeyFrame(Duration.seconds(0.6), e -> Event.fireEvent(stepButton,event)));
                    automaticEvolution.setCycleCount(Timeline.INDEFINITE);
                    automaticEvolution.play();
                    evolutionSlider.setDisable(true);
                    beginEndButton.setText("暂停");
                    stepButton.setDisable(true);
                    resetButton.setDisable(true);
                }
                else{
                    automaticEvolution.stop();
                    beginEndButton.setText("开始");
                    evolutionSlider.setDisable(false);
                    stepButton.setDisable(false);
                    resetButton.setDisable(false);
                }
                begin= !begin;
            }
        });
        stepButton.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                stepButton.setDisable(true);
                TranslateTransition wait = new TranslateTransition(Duration.seconds(0.5),stepButton);
                wait.play();
                wait.setOnFinished(new EventHandler() {
                    @Override
                    public void handle(Event event) {
                        if (begin == true){
                            stepButton.setDisable(false);
                        }
                    }
                });
                if(stepStatus == 0){
                    yellowLinePane.setOpacity(1);
                    rotateLines.play();
                    playerInstantiation();
                    roundRobin();
                    stepStatus++;
                }
                else if(stepStatus == 1){

                    eliminate();
                    updateCanvas();
                    stepStatus++;
                }
                else{
                    reproduct();
                    updateCanvas();
                    for(Label label : scoreLabels){
                        label.setText("");
                    }
                    for(ImageView imgview : miniPlayerImgView){
                        imgview.setOpacity(1);
                    }
                    stepStatus = 0;
                }
            }
        });
        resetButton.setOnAction(new EventHandler(){

            @Override
            public void handle(Event event) {
                updatePlayerList();
                updateMiniView();

                for(ImageView imv :miniPlayerImgView){
                    imv.setOpacity(1);
                }
                for(Label label : scoreLabels){
                    label.setText("");
                }
                loser.clear();
                winner.clear();
                updateCanvas();
                stepStatus = 0;
            }
        });
        whatWeLearn.setOnAction(new EventHandler(){

            @Override
            public void handle(Event event) {
                FXMLLoader f = new FXMLLoader();
                f.setLocation(f.getClassLoader().getResource("view/FinalLearnView.fxml"));
                AnchorPane an = null;
                try {
                    an = (AnchorPane)f.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Main.littleWindow();
                Main.changeRoot(an);
            }
        });
    }
    /**滑块和标签初始化*/
    public void sliderLableInit(){
        for(int i=0; i<8; i++){
            Slider slider = playerSliders.get(i);
            Label label = playerLabels.get(i);
            slider.setValue(3+i/7);
            String name = Constants.PLAYER_NAMES[i];
            label.setText(name+"     "+(3+i/7));
            label.setTextFill(Constants.PLAYER_COLORS.get(i));
            Tooltip tip = new Tooltip(Constants.PLAYER_DESCRIPTION[i]);
            label.setTooltip(tip);
            tip.setShowDelay(Duration.millis(100));
            tip.setShowDuration(Duration.INDEFINITE);
        }
        for(Slider slider : playerSliders){
            adjustSlider(slider);
        }
        for(int i=0; i<8; i++){
            Slider slider = playerSliders.get(i);
            Label label = playerLabels.get(i);
            String name = Constants.PLAYER_NAMES[i];
            boolean lock = lockStatus.get(i);
            SliderListener listener = new SliderListener();
            listener.setI(i);
            slider.valueProperty().addListener(listener);
            listeners[i] = listener;
        }
    }
    /**画布上迷你玩家所处位置初始化*/
    private void coordinateInit(double r, double x, double y, int n){
        double theta = Math.PI*2/n;
        for(int i=0; i<n; i++){
            xi.add(r*Math.cos(theta*i) + x);
            yi.add(r*Math.sin(theta*i) + y);
            double labelxi  = (r-35)*Math.cos(theta*i) + x - 15;
            double labelyi = (r-35)*Math.sin(theta*i) + y - 15;
            Label label = new Label();
            scoreLabels.add(label);
            labelPane.getChildren().add(label);
            label.setLayoutX(labelxi);
            label.setLayoutY(labelyi);
        }
    }
    /**滑块锁的初始化*/
    private void lockInit(){
        for(int i=0; i<8; i++){
            ImageView open = new ImageView(openImage);
            lockImageView.add(open);
            lockStatus.add(Constants.OPEN);
            Label lock = lockLabels.get(i);
            lock.setGraphic(open);
        }
        for(int i=0; i<8; i++){
            Label lock = lockLabels.get(i);
            ImageView lockState = lockImageView.get(i);
            Slider slider = playerSliders.get(i);
            int index = i;
            lock.setOnMouseClicked(new EventHandler(){

                @Override
                public void handle(Event event) {
                    boolean flag = lockStatus.get(index);
                    if(flag == Constants.OPEN){
                        lockState.setImage(lockImage);
                        lockStatus.set(index,Constants.LOCKED);
                        slider.setDisable(true);
                        changeable.remove(slider);
                        fixed.add(slider);
                    }
                    else{
                        lockState.setImage(openImage);
                        lockStatus.set(index,Constants.OPEN);
                        slider.setDisable(false);
                        changeable.add(slider);
                        fixed.remove(slider);
                    }
                    for(Slider slider: playerSliders){
                        adjustSlider(slider);
                    }
                }
            });
        }
    }

    /**可变数字初始化，也即各个AI的得分初始化*/
    private void rewardInit(){
        nc_up = new NumChanger(arrow1,arrow2,arrow3,arrow4,l_winwin_view,r_winwin_view,Constants.WIN_WIN,0);
        nc_out = new NumChanger(arrow5,arrow6,arrow11,arrow12,l_dupe_view,r_dupe_view,Constants.DUPE,1);
        nc_inside = new NumChanger(arrow7,arrow8,arrow9,arrow10,l_fooled_view,r_fooled_view,Constants.FOOLED,2);
        nc_down = new NumChanger(arrow13,arrow14,arrow15,arrow16,l_dupedupe_view,r_dupedupe_view,Constants.DUPE_DUPE,3);
        defaultReward.setOnAction(new EventHandler(){

            @Override
            public void handle(Event event) {
                rewardDefault();
            }
        });
    }
    /**用户操作后更新界面，更新画布*/
    private void updateCanvas(){
        GraphicsContext gc = venue.getGraphicsContext2D();
        gc.clearRect(0,0,400,400);
        gc.setFill(Color.rgb(244,244,244));
        InsidePolygon(gc, 180, 200, 200, 25);
    }
    /**用户操作后更新界面，更新AI*/
    private void updatePlayerList(){
        playerList.clear();
        for(int i=0;i<8;i++){
            Slider slider = playerSliders.get(i);
            int playerNum = (int)slider.getValue();
            for(int j=0; j<playerNum; j++){
                playerList.add(i);
            }
        }
    }
    /**用户操作后更新界面，更新图象*/
    public void updateMiniView(){
        for(int i=0 ; i<25 ; i++){
            Image img = miniImage.get((int)playerList.get(i));
            ImageView imgView = miniPlayerImgView.get(i);
            imgView.setImage(img);
        }
    }
    /**用户操作后更新界面，更新标签*/
    private void updateLabel(int i, int playerNum){
        Label label = playerLabels.get(i);
        String name = Constants.PLAYER_NAMES[i];
        label.setText(name+"    "+playerNum);
    }
    /**取消和还原滑块的监听器*/
    private void removeChangeableListener(Slider current){
        for(Slider slider : changeable){
            if(slider != current){
                int id = playerSliders.indexOf(slider);
                slider.valueProperty().removeListener(listeners[id]);
            }
        }
    }
    private void returnChangeableListener(Slider current){
        for(Slider slider : changeable){
            if(slider != current){
                int id = playerSliders.indexOf(slider);
                slider.valueProperty().addListener(listeners[id]);
            }
        }
    }
    /**停止自动演化*/
    public void evolutePause(){
        for(ImageView imgv :miniPlayerImgView){
            imgv.setOpacity(1);
        }
        evolutionSlider.setDisable(false);
        stepButton.setDisable(false);
        resetButton.setDisable(false);
        if(automaticEvolution != null){
            automaticEvolution.stop();
        }
        beginEndButton.setText("开始");
        begin = true;
    }
    /**画出二十五边形各点连线*/
    private void draw(Canvas canvas){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.rgb(244,244,244));
        gc.fillRect(0,0,canvas.getWidth(), canvas.getHeight());
        InsidePolygon(gc,180,200,200,25);
    }

    private void InsidePolygon(GraphicsContext gc, double r, double x, double y, int n){
        for(int i=0; i<n; i++){
            for(int j=i+1; j<n ; j++){
                if(!loser.contains(i) && !loser.contains(j)){
                    gc.setStroke(Color.rgb(180,180,180));
                    gc.setLineWidth(0.5);
                    gc.strokeLine(xi.get(i),yi.get(i),xi.get(j),yi.get(j));
                }
            }
        }
    }
    /**返回给定滑块当前上锁情况下的最大值和最小值*/
    private int changeableIncrement(Slider current){
        int num = 0;
        for(Slider slider:changeable){
            if(slider != current){
                num += (int)slider.getValue();
            }
        }
        return num;
    }
    private int changeableDecrement(Slider current){
        int num = 0;
        for(Slider slider:changeable){
            if(slider != current) {
                num += 25 - (int) slider.getValue();
            }
        }
        return num;
    }

    /** 调整滑块*/
    private void adjustSlider(Slider slider){
        int currentValue = (int)slider.getValue();
        int maxValue = currentValue + changeableIncrement(slider);
        int minValue = currentValue - changeableDecrement(slider);
        maxValue = maxValue <= 25 ? maxValue : 25;
        minValue = minValue >= 0 ? minValue : 0;
        slider.setMax((double)maxValue);
        slider.setMin((double)minValue);
    }

    /**AI列表更新与初始化*/
    private void playerInstantiation(){
        playerInstance.clear();
        for(int i:playerList){
            switch(i){
                case 0:
                    playerInstance.add(new Repeater());
                    break;
                case 1:
                    playerInstance.add(new Swindler());
                    break;
                case 2:
                    playerInstance.add(new Cutie());
                    break;
                case 3:
                    playerInstance.add(new Gangster());
                    break;
                case 4:
                    playerInstance.add(new Holmes());
                    break;
                case 5:
                    playerInstance.add(new RereadDuck());
                    break;
                case 6:
                    playerInstance.add(new SingleMind());
                    break;
                case 7:
                    playerInstance.add(new Randotron());
                    break;
            }
        }
    }
    /**整体对局一轮的分数计算*/
    private void roundRobin(){
        loser.clear();
        winner.clear();
        ArrayList<Integer> scores = new ArrayList<Integer>();
        SlotMachine slotMachine = SlotMachine.getInstance();
        for(int i=0; i<25; i++){
            Player p1 = playerInstance.get(i);
            for(int j=i+1; j<25; j++){
                Player p2 = playerInstance.get(j);
                slotMachine.fight(p1,p2);
            }
            int score = p1.getScore();
            scoreLabels.get(i).setText(String.valueOf(score));
            scores.add(score);
        }
        NaturaSelection.randomSelection(scores,SlotMachine.getInstance().getEliminateNum(),loser,winner);
    }
    /**淘汰玩家*/
    private void eliminate(){
        for(int i:loser){
            ImageView imgview = miniPlayerImgView.get(i);
            KeyValue kp1 = new KeyValue(imgview.opacityProperty(),1);
            KeyFrame kf1 = new KeyFrame(Duration.seconds(0),kp1);
            KeyValue kp2 = new KeyValue(imgview.opacityProperty(),0);
            KeyFrame kf2 = new KeyFrame(Duration.seconds(0.5),kp2);

            Timeline disapper = new Timeline();
            disapper.getKeyFrames().addAll(kf1,kf2);
            disapper.play();
        }
    }
    /**繁衍赢家*/
    private void reproduct(){
        for(int i=0; i< SlotMachine.getInstance().getEliminateNum(); i++){
            playerList.set(loser.get(i),playerList.get(winner.get(i)));
        }

        Collections.sort(playerList,new Comparator<Integer>(){

            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        updateCanvas();
        for(int i:loser){
            ImageView imgview = miniPlayerImgView.get(i);
            imgview.setOpacity(1);
        }
        winner.clear();
        loser.clear();
        updateMiniView();
    }
    /**以下三个public方法都由故事模式中其它Controller调用*/
    /** 刷新迷你玩家界面*/
    public void refreshEvolution(){
        for(Label label: scoreLabels){
            label.setText("");
        }
        for(ImageView img:miniPlayerImgView){
            img.setOpacity(1);
        }
        ArrayList<Integer> copy = new ArrayList<Integer>();
        for(int l:loser){
            copy.add(l);
        }
        loser.clear();
        updateCanvas();
        for(int c:copy){
            loser.add(c);
        }
    }
    /**手动触发步进按钮*/
    public void activeStep(){
        Event.fireEvent(stepButton,new MouseEvent(MouseEvent.MOUSE_CLICKED,
                stepButton.getLayoutX(), stepButton.getLayoutY(), stepButton.getLayoutX(), stepButton.getLayoutY(), MouseButton.PRIMARY, 1,
                true, true, true, true, true, true, true, true, true, true, null));
    }
    /**手动触发自动演化按钮*/
    public void activeEvolute(){
        Event.fireEvent(beginEndButton,new MouseEvent(MouseEvent.MOUSE_CLICKED,
                beginEndButton.getLayoutX(), beginEndButton.getLayoutY(), beginEndButton.getLayoutX(), beginEndButton.getLayoutY(), MouseButton.PRIMARY, 1,
                true, true, true, true, true, true, true, true, true, true, null));
    }
    /**默认奖励加载初始化*/
    private void rewardDefault(){
        nc_up.setNumber(Constants.WIN_WIN);
        nc_out.setNumber(Constants.DUPE);
        nc_inside.setNumber(Constants.FOOLED);
        nc_down.setNumber(Constants.DUPE_DUPE);
        SlotMachine.getInstance().setDefault();
    }
    /**回退回主界面的按钮对应初始化函数*/
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
    /**界面初始化函数*/
    @FXML
    private void initialize(){

        loadArry();
        playListInit();
        for(Slider slider:playerSliders){
            changeable.add(slider);
        }
        lockInit();
        sliderLableInit();
        rulesInite();
        loadHats();
        coordinateInit(180,200,200,25);
        draw(venue);
        loadMiniImage();
        loadMiniView();
        yellowLinePaneInit();
        buttonInit();
        rewardInit();
        backStepInit();
    }
}
