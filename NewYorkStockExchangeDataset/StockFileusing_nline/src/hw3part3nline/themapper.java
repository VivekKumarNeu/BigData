/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw3part3nline;

import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 *
 * @author vivek
 */
public class themapper extends Mapper<LongWritable, Text, LongWritable, Text> {

     @Override
     public void map(LongWritable key, Text value, Mapper.Context context
                    ) throws IOException, InterruptedException {
         
         
         context.write(key, value);
        

}
    
}
