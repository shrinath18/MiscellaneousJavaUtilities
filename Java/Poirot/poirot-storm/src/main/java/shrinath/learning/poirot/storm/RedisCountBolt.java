package shrinath.learning.poirot.storm;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;
import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;


public class RedisCountBolt extends BaseRichBolt{

	Jedis jedis;
	OutputCollector collector;
	public RedisCountBolt() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void prepare(Map stormConf, TopologyContext context,
			OutputCollector collector) {
		// TODO Auto-generated method stub
		jedis = new Jedis("127.0.0.1",6379);
		this.collector = collector;
	}

	@Override
	public void execute(Tuple input) {
		// TODO Auto-generated method stub
		
		String msg = input.getString(0);
		System.out.print("Message is:"+msg);
		jedis.zincrby("productviewcount", 1, msg);
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("/Users/shrinath.kalamdani/kafka.txt", "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writer.println(msg);
		writer.close();
		collector.ack(input);
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub
		
	}

}
