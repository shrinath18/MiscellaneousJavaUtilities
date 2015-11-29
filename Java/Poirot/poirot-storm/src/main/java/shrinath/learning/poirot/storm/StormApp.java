package shrinath.learning.poirot.storm;


import storm.kafka.KafkaSpout;
import storm.kafka.SpoutConfig;
import storm.kafka.StringScheme;
import storm.kafka.ZkHosts;
import backtype.storm.Config;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.spout.SchemeAsMultiScheme;
import backtype.storm.topology.TopologyBuilder;

/**
 * Hello world!
 *
 */
public class StormApp 
{
    public static void main( String[] args )
    {
       ZkHosts zkHosts = new ZkHosts("127.0.0.1:2181");
       SpoutConfig kafkaConfig = new SpoutConfig(zkHosts, "test_topic_2", "", "consumer_group_1");
       kafkaConfig.forceFromStart = false;
       kafkaConfig.scheme = new SchemeAsMultiScheme(new StringScheme());
       TopologyBuilder topologyBuilder = new TopologyBuilder();
       topologyBuilder.setSpout("KafkaSpout", new KafkaSpout(kafkaConfig),2);
       topologyBuilder.setBolt("RedisCountBolt", new RedisCountBolt(),2).shuffleGrouping("KafkaSpout");
       Config config = new Config();
       config.setDebug(true);
       config.setNumWorkers(2);
       try {
		StormSubmitter.submitTopology(args[0], config, topologyBuilder.createTopology());
	} catch (AlreadyAliveException | InvalidTopologyException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
}
