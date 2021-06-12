package model;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Constants 类位于model包中，用于存放游戏初始化前和过程中各种常量的值
 *
 * @see User User类及model包中的各个AI类
 * @see Player Player类AI类的抽象父类
 * @see SlotMachine 老虎机类
 */
public class Constants {
    /**默认设定 合作{@value}*/
    public static final boolean COOPERATION= true;
    /**默认设定 欺骗{@value}*/
    public static final boolean DECEPTION= false;
    /**对局结果得分默认值：*/
    /**默认设定 合作-合作{@value}*/
    public static final int WIN_WIN = 2;
    /**默认设定 欺骗-欺骗{@value}*/
    public static final int DUPE_DUPE = 0;
    /**默认设定 欺骗对合作中欺骗得分{@value}*/
    public static final int DUPE = 3;
    /**默认设定 欺骗对合作中合作得分{@value}*/
    public static final int FOOLED = -1;
    /**默认设定 默认对局数{@value}*/
    public static final int DEFAULT_ROUNDS = 10;
    /**默认设定 AI代号字符数组*/
    public static final String [] PLAYER_NAMES = {"复读机","老油条","小可爱","黑帮老铁",
            "福尔摩斯","复读鸭","一根筋","胡乱来"};
    /**默认设定 AI颜色设定*/
    public static final Color REPEATER_COLOR = Color.rgb(48,93,226);
    public static final Color SWINDLER_COLOR = Color.rgb(17,23,91);
    public static final Color CUTIE_COLOR = Color.rgb(231,100,232);
    public static final Color GANGSTER_COLOR = Color.rgb(247,206,54);
    public static final Color HOLMES_COLOR = Color.rgb(159,125,11);
    public static final Color REREADDUCK_COLOR = Color.rgb(114,119,255);
    public static final Color SINGLEMIND_COLOR = Color.rgb(60,229,35);
    public static final Color RANDOTRON_COLOR = Color.rgb(243,38,57);
    /**默认设定 AI颜色列表*/
    public static final ArrayList<Color> PLAYER_COLORS = new ArrayList<Color>(Arrays.asList(
            REPEATER_COLOR,SWINDLER_COLOR,CUTIE_COLOR,GANGSTER_COLOR,
            HOLMES_COLOR,REREADDUCK_COLOR,SINGLEMIND_COLOR,RANDOTRON_COLOR));
    /**默认设定 图像偏移指数*/
    public static final int XBIAS = -45;
    public static final int YBIAS = -65;
    /**默认设定 沙盒模式中锁定设置*/
    public static final boolean LOCKED = true;
    public static final boolean OPEN = false;
    /**默认设定 AI描述*/
    public static final String[] PLAYER_DESCRIPTION= new String[]{"第一局合作，然后模仿对手行动","永远欺骗","永远合作", "首先合作，被骗后永不合作",
            "前四局合作，欺骗，合作，合作，再往后如果对手反击，我就变成\n「复读机」，如果对手不反击，我就变成「老油条」",
            "连续被骗两次后才会反击","第一轮合作，如果对手合作，我就做出与上一轮\n相同的选择；如果对手欺骗，我就做出与上一轮相反的选择",
            "随机选择「欺骗」或「合作」"};

}
