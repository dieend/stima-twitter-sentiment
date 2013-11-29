/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.List;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

/**
 *
 * @author Okihita
 */
public class TwitterRetriever {
    private Twitter twitter;
    public TwitterRetriever() {
        twitter = new TwitterFactory().getInstance();
        String access_token = null;
        String access_token_secret = null;
        String consumer_key = null;
        String consumer_key_secret = null;
        try {
            Class c = Class.forName("Config");
            consumer_key = (String)c.getDeclaredField("CONSUMER_KEY").get(null);
            access_token= (String)c.getDeclaredField("ACCESS_TOKEN").get(null);
            consumer_key_secret = (String)c.getDeclaredField("CONSUMER_KEY_SECRET").get(null);
            access_token_secret= (String)c.getDeclaredField("ACCESS_TOKEN_SECRET").get(null);
        } catch (Exception e) {
            consumer_key = System.getenv("CONSUMER_KEY");
            access_token= System.getenv("ACCESS_TOKEN");
            consumer_key_secret = System.getenv("CONSUMER_KEY_SECRET");
            access_token_secret= System.getenv("ACCESS_TOKEN_SECRET");
        }
        AccessToken accessToken = new AccessToken(Config.ACCESS_TOKEN, Config.ACCESS_TOKEN_SECRET);
        twitter.setOAuthConsumer(Config.CONSUMER_KEY, Config.CONSUMER_KEY_SECRET);
        twitter.setOAuthAccessToken(accessToken);
    }
    public List<Status> searchQuery(String keyword) throws TwitterException{
        List<Status> tweets = null;
        Query query = new Query(keyword);
        query.setCount(100);
        QueryResult result;
        result = twitter.search(query);
        tweets = result.getTweets();
        return tweets;
    }
    
    public static void main(String[] args) throws TwitterException{
        TwitterRetriever tw = new TwitterRetriever();
        List<Status> result = tw.searchQuery("informatika");
        for (Status r:result) {
            StringBuffer address = new StringBuffer();
            address.append("http://twitter.com/#!/");
            address.append(r.getUser().getScreenName());
            address.append("/status/");
            address.append(r.getId());
            System.out.println(r.getText() + r.getUser().getName() + address);
        }
    }
}
