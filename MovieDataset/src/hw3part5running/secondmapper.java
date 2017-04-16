package hw3part5running;

import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class secondmapper extends Mapper<LongWritable, Text, NullWritable,Text> {

    TreeMap<Text, FloatWritable> treemap = new TreeMap<>();

    protected void map(LongWritable key, Text value, Mapper.Context context) throws IOException, InterruptedException {

        String[] val = value.toString().split("\\t");
        treemap.put(new Text(val[0]), new FloatWritable(Float.parseFloat(val[1])));

    }

    protected void cleanup(Context context) throws IOException, InterruptedException {

        sortingfile sm = new sortingfile();
        Map sortedMap = sm.sortByValues(treemap);
        Text s;
        
        Set set = sortedMap.entrySet();
        Iterator i = set.iterator();
        int xx=0;
        while(i.hasNext()){
            if(xx == 25)
                break;
            Map.Entry me = (Map.Entry) i.next();
            String temp = me.getKey().toString() + "," +me.getValue().toString();
            context.write(NullWritable.get(), new Text(temp));
            xx++;
        }
    }

    public static class sortingfile {

        public <K, V extends Comparable<V>> Map<K, V> sortByValues(final Map<K, V> map) {

            Comparator<K> valueComparator = new Comparator<K>() {

                public int compare(K k1, K k2) {
                    int compare = map.get(k2).compareTo(map.get(k1));
                    if (compare == 0) {
                        return 1;
                    } else {
                        return compare;
                    }
                }
            };

            Map<K, V> sortedByValues = new TreeMap<K, V>(valueComparator);
            sortedByValues.putAll(map);
            return sortedByValues;
        }
    }
}
