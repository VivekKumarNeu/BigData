/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw3part4;

import java.io.IOException;
import java.util.Date;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 *
 * @author vivek
 */
public class Hw3part4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {

    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "find date of max volume stock");
    job.setJarByClass(Hw3part4.class);
    job.setMapperClass(secondmapper.class);
    job.setMapOutputKeyClass(NullWritable.class);
    job.setMapOutputValueClass(Text.class);
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    
    System.exit(job.waitForCompletion(true) ? 0 : 1);

        
        
        
        
//    Configuration conf = new Configuration();
//    Job job = Job.getInstance(conf, "find date of max volume stock");
//    job.setJarByClass(Hw3part4.class);
//    job.setMapperClass(themapper.class);
//    job.setReducerClass(thereducer.class);
//    job.setOutputKeyClass(Text.class);
//    job.setOutputValueClass(Text.class);
//    job.setMapOutputKeyClass(Text.class);
//    job.setMapOutputValueClass(highestcompare.class);
//    FileInputFormat.addInputPath(job, new Path(args[0]));
//    FileOutputFormat.setOutputPath(job, new Path(args[1]));
//    
//    System.exit(job.waitForCompletion(true) ? 0 : 1);

    }
    
}
