/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw3part5running;

import java.io.IOException;
import java.util.TreeMap;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author vivek
 */
public class secondreducer extends Reducer<NullWritable, Text, Text, FloatWritable> {

    TreeMap<Text, FloatWritable> treemap = new TreeMap<>();

    @Override
    protected void reduce(NullWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        
        
        for(Text ff : values){
            String[] temp = ff.toString().split(",");
            treemap.put(new Text(temp[0]), new FloatWritable(Float.parseFloat(temp[1])));
        }
        
    }
    
    
    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {

    sortingfile sm = new sortingfile();
    Map sortedMap = sm.sortByValues(treemap);

        Set set = sortedMap.entrySet();
        Iterator i = set.iterator();
        int xx = 0;
        while(i.hasNext()){
            if(xx==25)
                break;
            Map.Entry me = (Map.Entry) i.next();
            context.write((Text) me.getKey(), (FloatWritable) me.getValue());
        }
    }
    
        public static class sortingfile {

        public <K, V extends Comparable<V>> Map<K, V> sortByValues(final Map<K, V> map) {

            Comparator<K> valueComparator = new Comparator<K>() {

                @Override
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
