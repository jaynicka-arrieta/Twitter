package com.codepath.apps.restclienttemplate;

import android.annotation.TargetApi;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

import java.util.Locale;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class DetailsActivity extends AppCompatActivity {

    Tweet tweet;
    ImageView ivProfilePic;
    TextView tvUsername;
    TextView tvTagUsername;
    TextView tvDetails;
    TextView tvTimeStamp;

    //String time;


    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ivProfilePic = (ImageView) findViewById(R.id.ivProfilePic);
        tvUsername = (TextView) findViewById(R.id.tvUsername);
        tvTagUsername = (TextView) findViewById(R.id.tvTagUsername);
        tvDetails = (TextView) findViewById(R.id.tvDetails);
        tvTimeStamp = (TextView) findViewById(R.id.tvTimeStamp);

        tweet = (Tweet) Parcels.unwrap(getIntent().getParcelableExtra(Tweet.class.getSimpleName()));
        //time = getRelativeTimeAgo(tweet.createdAt);
        tvUsername.setText(tweet.user.name);
        tvTagUsername.setText("@" + tweet.user.screenName);
        tvDetails.setText(tweet.body);
        tvTimeStamp.setText(getRelativeTimeAgo(tweet.createdAt));

        GlideApp.with(getApplicationContext())
                .load(tweet.user.profileImageUrl)
                .transform(new RoundedCornersTransformation(15, 0))
                .into(ivProfilePic);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String getRelativeTimeAgo(String rawJsonDate) {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";
        try {
            long dateMillis = sf.parse(rawJsonDate).getTime();
            relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
        }
        catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        return relativeDate;
    }
}
