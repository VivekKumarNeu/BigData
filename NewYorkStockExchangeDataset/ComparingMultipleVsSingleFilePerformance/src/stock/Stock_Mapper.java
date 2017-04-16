/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock;

import java.io.IOException;
import java.util.StringTokenizer;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

/**
 *
 * @author vivek
 */
public class Stock_Mapper extends Mapper<Object,Text,Text,FloatWritable>{
    
    static long MapperStart = System.currentTimeMillis();
    FloatWritable stockVal = new FloatWritable();
    Text stockName = new Text();

    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        
        StringTokenizer stockpriceiter = new StringTokenizer(value.toString().split(",")[4]);
        StringTokenizer stockNameiter = new StringTokenizer(value.toString().split(",")[1]);
        
        
        if(value.toString().contains("exchange"))
            return;

	while(stockNameiter.hasMoreTokens()){
            stockName.set(stockNameiter.nextToken());
            stockVal.set(Float.parseFloat(stockpriceiter.nextToken()));
            context.write(stockName, stockVal);
            
        }
        
        }
        
    }