import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by saadansari on 14/10/2014.
 */
public class MyTwitter{

    public static void main(String[] args) throws TwitterException {
        final LinkedBlockingQueue<Status> queue = null;

        ConfigurationBuilder confBuilder = null;
        confBuilder = new twitter4j.conf.ConfigurationBuilder();
        confBuilder.setDebugEnabled(true);

        confBuilder.setOAuthConsumerKey("9OuoBGUIgpDZvbvFuIiFNJeMw");
        confBuilder.setOAuthConsumerSecret("rFbP66bXMUnoXuvR6ZowC5FFrRVQ6v4SRAK7voBaih5kAn2iRM");
        confBuilder.setOAuthAccessToken("15048942-KatOxWCgUtC0NRiSmzyh0zabUfc8iMzRcbBOMKeLi");
        confBuilder.setOAuthAccessTokenSecret("7KuHPkhGdkZGCEPSlDCJfqZbRWz1LaVf4hnCtSN8uWqpY");

        StatusListener listener = new StatusListener() {

            @Override
            public void onStatus(Status status) {
                queue.offer(status);
                System.out.println(status.getText());
            }

            @Override
            public void onStallWarning(StallWarning stallWarning) {

            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice sdn) {
            }

            @Override
            public void onTrackLimitationNotice(int i) {
            }

            @Override
            public void onScrubGeo(long l, long l1) {
            }

            @Override
            public void onException(Exception e) {
            }

        };

        TwitterStreamFactory fact = new TwitterStreamFactory(confBuilder.build());
        TwitterStream _twitterStream = fact.getInstance();
        _twitterStream.addListener(listener);
        _twitterStream.filter(new FilterQuery().track(new String[]{"java"}));
        //_twitterStream.sample();
    }
}
