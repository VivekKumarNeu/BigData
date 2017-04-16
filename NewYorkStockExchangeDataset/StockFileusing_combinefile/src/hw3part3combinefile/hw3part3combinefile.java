package hw3part3combinefile;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.RunningJob;
import org.apache.hadoop.mapred.TextOutputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.GenericOptionsParser;

public class hw3part3combinefile {

  public static void main(String[] args) throws Exception {
		JobConf conf = new JobConf("DriverCombineFileInputFormat");
                Job job1 = Job.getInstance(conf);
                
		conf.set("mapred.max.split.size", "102400");
		conf.setJarByClass(hw3part3combinefile.class);
		//String[] jobArgs = new GenericOptionsParser(conf, args)
		//		.getRemainingArgs();

		conf.setMapperClass(MapperCombineFileInputFormat.class);
		conf.setInputFormat(ExtendedCombineFileInputFormat.class);
		ExtendedCombineFileInputFormat.addInputPath(job1, new Path(args[0]));

		conf.setNumReduceTasks(0);

		conf.setOutputFormat(TextOutputFormat.class);
		TextOutputFormat.setOutputPath(conf, new Path(args[1]));
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(Text.class);

		RunningJob job = JobClient.runJob(conf);
		while (!job.isComplete()) {
			Thread.sleep(1000);
		}

		System.exit(job.isSuccessful() ? 0 : 2);
	}
}