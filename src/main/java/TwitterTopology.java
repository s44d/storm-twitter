import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.utils.Utils;

/**
 * Created by saadansari on 16/10/2014.
 */
public class TwitterTopology {

    public static void main(String[] args) {
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("spout",new TwitterSpout());
        builder.setBolt("count",new TweetCountBolt()).shuffleGrouping("spout");
        builder.setBolt("mongodb",new MongoBolt()).shuffleGrouping("spout");

        Config conf = new Config();
        conf.setMessageTimeoutSecs(120);

        final LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("test", conf, builder.createTopology());
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                cluster.killTopology("test");
                cluster.shutdown();
            }
        });
    }
}
