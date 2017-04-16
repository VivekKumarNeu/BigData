/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw3part4;

import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 *
 * @author vivek
 */
public class secondmapper extends Mapper<LongWritable, Text, NullWritable,Text>{

    long max_value = 0;
    long min_value = Long.MAX_VALUE;
    float adj_close = 0;
        Text datemin = new Text();
        Text datemax = new Text();

    
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        
        String[] sentence = value.toString().trim().split("\t");
        
        String[] data = sentence[1].split(",");
            
        if (max_value < Long.parseLong(data[1])) {
                    max_value = Long.parseLong(data[1]);
                    datemax.set(data[0]);
            }

        if (min_value > Long.parseLong(data[3])) {
                min_value = Long.parseLong(data[3]);
                datemin.set(data[2]);
            }

        if(adj_close < Float.parseFloat(data[4])){
               adj_close =  Float.parseFloat(data[4]);
            }
        
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        Text result = new Text("Max vol date:"+String.valueOf(datemax)+"("+String.valueOf(max_value)+")"+
                "  Min vol date:"+String.valueOf(datemin)+"("+String.valueOf(min_value)+")"+"   Adj_stock :"+String.valueOf(adj_close));
        context.write(NullWritable.get(), result);
        
        
    }
    
    
    
    
    
    
}
