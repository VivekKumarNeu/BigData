package hw3part4;

import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class thereducer extends Reducer<Text, highestcompare, Text, Text> {

    long max_value = 0;
    long min_value = Long.MAX_VALUE;
    Text max = new Text();
    Text min = new Text();
    float adj_close = 0;
    
    Text total;

    @Override
    public void reduce(Text key, Iterable<highestcompare> values, Context context) throws IOException, InterruptedException {

        Text datemin = new Text();
        Text datemax = new Text();
        
        for (highestcompare value : values) {
            long temp = value.getVolume();

            if (max_value < temp) {
                max_value = temp;
                max.set(String.valueOf(max_value));
                datemax.set(value.getDate());
            }

            if (min_value > temp) {
                min_value = temp;
                min.set(String.valueOf(min_value));
                datemin.set(value.getDate());
            }

            if(adj_close < Float.parseFloat(value.getAdj_close())){
               adj_close =  Float.parseFloat(value.getAdj_close());
            }
            
            
        }
        
        total = new Text(datemax.toString().concat(",").concat(max.toString()).concat(",").
                concat(datemin.toString()).concat(",").concat(min.toString()).concat(",").
                        concat(String.valueOf(adj_close)));

        context.write(key, total);
        max_value = 0;
        min_value = Long.MAX_VALUE;
        adj_close = 0;

    }
    
}
