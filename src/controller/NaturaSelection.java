package controller;

import java.util.*;
/**
 * NaturaSelection 类位于controller包中，用于执行繁衍规则
 * 在最高分中选择x个繁衍，最低分中选择x个淘汰，如果出现平分则随机选择淘汰或繁衍的玩家
 */
public class NaturaSelection {
    /** 随机选择并进行繁衍与淘汰*/
    public static void randomSelection(ArrayList<Integer>playerScore, int eliminateNum,ArrayList<Integer>loser,ArrayList<Integer>winner){
        ArrayList<idBuilder> idBuilders = new ArrayList<idBuilder>();
        for(int i = 0; i< playerScore.size();i++){
            idBuilders.add(new idBuilder(i, playerScore.get(i)));
        }
        Collections.sort(idBuilders,new Comparator<idBuilder>(){

            @Override
            public int compare(idBuilder o1, idBuilder o2) {
                return o1.score-o2.score;
            }
        });
        int count = 0;
        int countm = 24;
        int min = 0;
        int max = 2500;
        while (count < eliminateNum){
            if(count == eliminateNum - 1){
                min = idBuilders.get(count).getScore();
                max = idBuilders.get(countm).getScore();
            }
            loser.add(idBuilders.get(count).getId());
            winner.add(idBuilders.get(countm).getId());
            count++;
            countm--;
        }
        while (count < idBuilders.size() && min == idBuilders.get(count).getScore()){
            loser.add(idBuilders.get(count).getId());
            count++;
        }
        while (countm >= 0 && max == idBuilders.get(countm).getScore()){
            winner.add(idBuilders.get(countm).getId());
            countm--;
        }

        int winnerRepeat = winner.size();
        int loserRepeat = loser.size();
        while(winnerRepeat>=1 && playerScore.get(winner.get(winnerRepeat-1)) == max){
            winnerRepeat--;
        }
        while(loserRepeat>=1 && playerScore.get(loser.get(loserRepeat-1)) == min){
            loserRepeat--;
        }

        Random random = new Random();

        ArrayList<Integer> removingloser = new ArrayList<Integer>();
        ArrayList<Integer> removingwinner = new ArrayList<Integer>();

        while(loser.size()-removingloser.size() != eliminateNum){
            int x = random.nextInt(loser.size()-loserRepeat)+loserRepeat;
            if( !removingloser.contains(loser.get(x))){
                removingloser.add(loser.get(x));
            }

        }
        while(winner.size()-removingwinner.size() != eliminateNum){
            int x = (random.nextInt(winner.size()-winnerRepeat)+winnerRepeat);
            if(!removingwinner.contains(winner.get(x))){
                removingwinner.add(winner.get(x));
            }
        }
        for(int lose:removingloser){
            loser.remove(loser.indexOf(lose));
        }
        for(int win:removingwinner){
            winner.remove(winner.indexOf(win));
        }
    }
}
class idBuilder{
    int id;
    int score;

    public int getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    idBuilder(int id, int score){
        this.id = id;
        this.score = score;
    }
}