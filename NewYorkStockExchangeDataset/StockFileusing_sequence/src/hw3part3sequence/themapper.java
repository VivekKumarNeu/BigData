/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw3part3sequence;

import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.Text;

/**
 *
 * @author vivek
 */
public class themapper extends Mapper<LongWritable, Text, LongWritable, Text>{

    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            
            String[] val = value.toString().split(",");
            context.write(new LongWritable(Long.parseLong(val[0])), new Text(val[1]));
        }   
    
}
