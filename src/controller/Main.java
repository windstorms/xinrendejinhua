package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**注意，程序入口是Enter文件
 */
public class Main extends Application {
    /** 背景音乐*/
    public static MediaPlayer bgmPlayer;
    private static Scene scene;
    private static Stage stage;
    /** 改变当前页面*/
    public static void changeRoot(AnchorPane root){
        scene.setRoot(root);
    }
    /** 设置窗口大小*/
    public static void littleWindow(){
        stage.setWidth(671.3);
    }
    /** 设置窗口大小*/
    public static void normalWindow(){
        stage.setWidth(800);
    }
    public static void main(String[] args) {
        launch(args);
    }

    /** 页面加载初始化*/
    @Override
    public void start(Stage primaryStage) throws Exception {
        File media = new File("src/music/bgm.mp3");
        String mURL = media.toURL().toString();

        Media medi = new Media(mURL);
        bgmPlayer = new MediaPlayer(medi);
        bgmPlayer.setAutoPlay(true);
        bgmPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        MediaView mv = new MediaView(bgmPlayer);

        FXMLLoader f = new FXMLLoader();
        stage = primaryStage;
        f.setLocation(f.getClassLoader().getResource("view/BeginView.fxml"));
        AnchorPane an = null;
        try {
            an = (AnchorPane)f.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
