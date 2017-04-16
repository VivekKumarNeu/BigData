/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock;

import java.io.IOException;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 *
 * @author vivek
 */
public class Stock_Reducer extends Reducer<Text, FloatWritable, Text, FloatWritable>{
    
    private FloatWritable result = new FloatWritable();
    static long Reducerend;
    protected void reduce(Text key, Iterable<FloatWritable> values, Context context) throws IOException, InterruptedException {
     
        int totalsum=0;
        int numberofvalues=0;
        
        for(FloatWritable val : values){
            totalsum += val.get();
            numberofvalues++;
        }
        
        result.set(totalsum/numberofvalues);
        context.write(key, result);
    
        Reducerend = System.currentTimeMillis();
    }
    
}
