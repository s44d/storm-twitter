import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;
import org.joda.time.DateTime;
import twitter4j.Status;

import java.util.Date;
import java.util.Map;

/**
 * Created by saadansari on 22/10/2014.
 */
public class TweetCountBolt extends BaseRichBolt {
    private int counter;
    private OutputCollector collector;

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.collector = collector;

    }

    @Override
    public void execute(Tuple tuple) {
        if (tuple != null){
            counter++;
            System.out.println(counter);
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }
}
