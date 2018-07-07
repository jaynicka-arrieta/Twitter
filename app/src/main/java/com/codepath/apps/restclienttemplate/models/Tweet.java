package com.codepath.apps.restclienttemplate.models;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

@Parcel
public class Tweet {

    public String body;
    //public String fullBody;
    public  long uid; //database ID for the tweet
    public User user;
    public String createdAt;

    //deserialize the JSON
    public static Tweet fromJSON(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();

        //extract all the values from JSON
        //tweet.fullBody = jsonObject.getString();
        tweet.body = jsonObject.getString("full_text");
        tweet.uid = jsonObject.getLong("id");
        tweet.createdAt = jsonObject.getString("created_at");
        tweet.user = User.fromJSON(jsonObject.getJSONObject("user"));

        return tweet;
    }

}
