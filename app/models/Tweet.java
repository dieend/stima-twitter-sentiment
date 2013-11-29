package models;

import models.algorithm.Algorithm;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: adinata
 * Date: 11/28/13
 * Time: 4:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class Tweet {
    private static Random r = new Random();
    private static String[] sample = {"this is a sample tweet negatif","this is a sample tweet positif", "this is a sample tweet positif negatif"};
    String content = null;
    //TODO this should be fixed and integrate with okihita
    public Tweet() {
        content = sample[r.nextInt(3)];
    }
    public String getContent(){
        return content;
    }
    public int countExistance(String[] word){
        int ret = 0;
        for (int i=0; i<word.length; i++) {
            ret += Algorithm.S().countWord(getContent(), word[i]);
        }
        return ret;
    }
}
