package controllers;

import helpers.Forms;
import models.Tweet;
import models.TweetAnalyzer;
import models.algorithm.Algorithm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

import java.util.ArrayList;
import java.util.List;

public class Application extends Controller {

    public static Result index() {
        Form<Forms.Tweet> tweetForm = Form.form(Forms.Tweet.class).bindFromRequest();
        return ok(index.render(tweetForm));
    }
    public static Result analyzeTweets(){
        Form<Forms.Tweet> tweetForm = Form.form(Forms.Tweet.class).bindFromRequest();
        Algorithm.S().setAlgorithm(tweetForm.get().algorithm);
        TweetAnalyzer tw = new TweetAnalyzer(tweetForm.get().tweetKeyword,
                                            tweetForm.get().positifPattern,
                                            tweetForm.get().negatifPattern);

        List<Tweet> positifTweets = new ArrayList<Tweet>();
        List<Tweet> negatifTweets = new ArrayList<Tweet>();
        List<Tweet> neutralTweets = new ArrayList<Tweet>();
        tw.divideTweet(positifTweets,negatifTweets,neutralTweets);
        return ok(analyze.render(tw.getPositifKeyword(), tw.getNegatifKeyword(), positifTweets, negatifTweets, neutralTweets, tweetForm.get().algorithm));
    }
}
