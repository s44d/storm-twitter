import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;
import com.mongodb.*;
import twitter4j.Status;

import java.net.UnknownHostException;
import java.util.Map;

/**
 * Created by saadansari on 04/11/2014.
 */
public class MongoBolt extends BaseRichBolt {
    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {

    }

    @Override
    public void execute(Tuple tuple) {
        try{
            MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));
            DB database = client.getDB("storm");
            final DBCollection collection = database.getCollection("tweets");

            Status status = (Status)tuple.getValueByField("tweet");
            BasicDBObject dbObject = new BasicDBObject();
            dbObject.put("user",status.getUser().getName());
            dbObject.put("createdAt",status.getCreatedAt());
            dbObject.put("tweet",status.getText());
            dbObject.put("retweetedCount",status.getRetweetCount());
            collection.insert(dbObject);
            client.close();

        }catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }
}
