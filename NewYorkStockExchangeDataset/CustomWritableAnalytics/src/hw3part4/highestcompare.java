/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw3part4;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableUtils;

/**
 *
 * @author vivek
 */
public class highestcompare implements Writable, WritableComparable<highestcompare>{

    public String toString(){
        return (new StringBuilder().append(date).append(",").append(volume).append(",").append(adj_close).toString());
    }
       
    String date;
    Long volume;
    String adj_close;
    String stock_Name;
    boolean flag=false;
    
    public highestcompare(){}
    
    public highestcompare(String d,Long v, String a,String s){
        date = d;
        volume = v;
        adj_close = a;
        stock_Name = s;
    }
    
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public String getAdj_close() {
        return adj_close;
    }

    public void setAdj_close(String adj_close) {
        this.adj_close = adj_close;
    }

    public String getStock_Name() {
        return stock_Name;
    }

    public void setStock_Name(String stock_Name) {
        this.stock_Name = stock_Name;
    }

    
    @Override
    public void write(DataOutput d) throws IOException {
        WritableUtils.writeString(d, date);
        WritableUtils.writeVLong(d, volume);
        WritableUtils.writeString(d, adj_close);
        WritableUtils.writeString(d, stock_Name);
    }

    @Override
    public void readFields(DataInput di) throws IOException {
        
     date = WritableUtils.readString(di);
     volume = WritableUtils.readVLong(di);
     adj_close = WritableUtils.readString(di);
     stock_Name = WritableUtils.readString(di);
    }

    @Override
    public int compareTo(highestcompare o) {

        int result = volume.compareTo(o.volume);
       
       if(result == 0)
           return date.compareTo(o.date);

       return result;
       
    }
}
