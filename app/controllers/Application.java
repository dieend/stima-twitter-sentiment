package controllers;

import helpers.Forms;
import models.MyTweet;
import models.TweetAnalyzer;
import models.algorithm.Algorithm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.List;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
        Form<Forms.Tweet> tweetForm = Form.form(Forms.Tweet.class).bindFromRequest();
        return ok(index.render(tweetForm));
    }
    public static Result analyzeTweets(){
        Form<Forms.Tweet> tweetForm = Form.form(Forms.Tweet.class).bindFromRequest();
        Algorithm.S().setAlgorithm(tweetForm.get().algorithm);
        try {
            TweetAnalyzer tw = new TweetAnalyzer(tweetForm.get().tweetKeyword,
                                                tweetForm.get().positifPattern,
                                                tweetForm.get().negatifPattern);

            List<MyTweet> positifTweets = new ArrayList<MyTweet>();
            List<MyTweet> negatifTweets = new ArrayList<MyTweet>();
            List<MyTweet> neutralTweets = new ArrayList<MyTweet>();
            tw.divideTweet(positifTweets,negatifTweets,neutralTweets);
            return ok(analyze.render(tw.getKeyword(),tw.getPositifKeyword(), tw.getNegatifKeyword(), positifTweets, negatifTweets, neutralTweets, tweetForm.get().algorithm));
        } catch (Exception e) {
            return Controller.badRequest();
        }
    }
}
