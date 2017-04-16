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
import org.apache.hadoop.mapreduce.Reducer;

/**
 *
 * @author vivek
 */
public class topreducer extends Reducer<Text,FloatWritable,Text,FloatWritable>{
    
    private FloatWritable result = new FloatWritable();

    @Override
    protected void reduce(Text key, Iterable<FloatWritable> values, Context context) throws IOException, InterruptedException {
               float total = 0;
        
            for (FloatWritable val : values) {
                total = total + val.get();
            }
        
        result.set(total);
        context.write(key,result);

    }
    }
    
    
 
