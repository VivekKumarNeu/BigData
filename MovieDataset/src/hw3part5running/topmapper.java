/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw3part5running;

import java.io.IOException;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 *
 * @author vivek
 */
public class topmapper extends Mapper <Object,Text,Text, FloatWritable> {
    
    private Text moviename = new Text();
    
    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {

        String[] val = value.toString().split("::");
        moviename.set(val[1]);
        FloatWritable movierating = new FloatWritable(Float.parseFloat(val[2]));
        
        context.write(moviename, movierating);
    }
}
