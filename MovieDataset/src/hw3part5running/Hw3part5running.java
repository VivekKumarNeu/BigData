/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw3part5running;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 *
 * @author vivek
 */
public class Hw3part5running {

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {

        Configuration config = new Configuration();
        Job job1 = Job.getInstance(config, "Chaining");
        job1.setJarByClass(Hw3part5running.class);
        job1.setMapperClass(topmapper.class);
        job1.setMapOutputKeyClass(Text.class);
        job1.setMapOutputValueClass(FloatWritable.class);
        
        job1.setReducerClass(topreducer.class);
        job1.setOutputKeyClass(Text.class);
        job1.setOutputValueClass(FloatWritable.class);
        
        FileInputFormat.addInputPath(job1, new Path(args[0]));
        FileOutputFormat.setOutputPath(job1, new Path(args[1]));
        boolean complete = job1.waitForCompletion(true);
    
        
        if(complete){
        Configuration config1 = new Configuration();
        Job job2 = Job.getInstance(config1, "C");
        job2.setJarByClass(Hw3part5running.class);
        job2.setMapperClass(secondmapper.class);
        job2.setReducerClass(secondreducer.class);
        job2.setMapOutputKeyClass(NullWritable.class);
        job2.setMapOutputValueClass(Text.class);
        job2.setOutputKeyClass(Text.class);
        job2.setOutputValueClass(FloatWritable.class);
   
                
        FileInputFormat.addInputPath(job2, new Path(args[1]));
        FileOutputFormat.setOutputPath(job2, new Path(args[2]));
        System.out.println(job2.waitForCompletion(true)? 0:1);
    }
    
}
    
}
