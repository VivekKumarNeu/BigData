package hw3part4;

import java.io.IOException;
import java.util.StringTokenizer;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 *
 * @author vivek
 */
public class themapper extends Mapper<LongWritable, Text, Text,highestcompare>{

@Override
public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

            if(value.toString().contains("exchange"))
                return;

            String[] tokens = value.toString().split(",");
            Long volume = Long.parseLong(tokens[7]);
            String datee = tokens[2].trim();
            String adj_close = tokens[8].trim();
            String stock_Name = tokens[1].trim();
            highestcompare high = new highestcompare(datee, volume,adj_close,stock_Name);
            context.write(new Text(stock_Name),high);
            

}

    
}
