package models;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: adinata
 * Date: 11/28/13
 * Time: 3:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class TweetAnalyzer {
    String tweetKeyword;
    String[] positifKeyword;
    String[] negatifKeyword;
    Tweet[] tweets;
    public TweetAnalyzer(String tweet, String positif, String negatif) {
        tweetKeyword = tweet;
        positifKeyword = positif.trim().split(";");
        negatifKeyword = negatif.trim().split(";");
        initializeTweets();
    }
    //TODO integrate with okihita
    private void initializeTweets(){
        tweets = new Tweet[10];
        for (int i=0; i<tweets.length; i++) {
            tweets[i] = new Tweet();
        }
    }
    public String[] getPositifKeyword(){
        return positifKeyword;
    }
    public String[] getNegatifKeyword(){
        return negatifKeyword;
    }
    public void divideTweet(List<Tweet> positif, List<Tweet> negatif, List<Tweet> neutral){
        for (Tweet t:tweets) {
            int p = t.countExistance(getPositifKeyword());
            int n = t.countExistance(getNegatifKeyword());
            if (p > n) {
                positif.add(t);
            } else if (n > p){
                negatif.add(t);
            } else {
                neutral.add(t);
            }
        }
    }
}
