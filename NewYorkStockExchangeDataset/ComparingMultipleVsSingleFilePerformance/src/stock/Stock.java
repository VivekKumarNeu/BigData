/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock;

import java.io.IOException;
import java.util.Date;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import static stock.Stock_Mapper.MapperStart;
import static stock.Stock_Reducer.Reducerend;

/**
 *
 * @author vivek
 */
public class Stock {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException  {

    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "Stocks");
    job.setJarByClass(Stock.class);
    job.setMapperClass(Stock_Mapper.class);
    job.setCombinerClass(Stock_Reducer.class);
    job.setReducerClass(Stock_Reducer.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(LongWritable.class);
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    
   long starttime = new Date().getTime();
   job.waitForCompletion(true);            
   long endtime = new Date().getTime();
   System.out.println("Time taken to finish the job :: "+(endtime-starttime) + "ms");
    //System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
    
}
