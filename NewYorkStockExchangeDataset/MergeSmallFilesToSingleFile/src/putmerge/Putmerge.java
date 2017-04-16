/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package putmerge;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

/**
 *
 * @author vivek
 */
public class Putmerge {
	public static void main(String args[]) throws IOException {

		Configuration conf = new Configuration();
		FileSystem hdfs = FileSystem.get(conf);
		FileSystem local = FileSystem.getLocal(conf);
		
		Path inputDir = new Path(args[0]);
		Path hdfsFile = new Path(args[1]);
		
		try{
				FileStatus[] inputfiles = local.listStatus(inputDir);
				FSDataOutputStream out = hdfs.create(hdfsFile);
				
				for(int i=0 ; i<inputfiles.length ; i++){
					FSDataInputStream in = local.open(inputfiles[i].getPath());
					byte[] buffer = new byte[256];
					int bytesRead = 0;
					while((bytesRead = in.read(buffer)) > 0) {
						out.write(buffer,0,bytesRead);
						
					}
					
					in.close();
				}
			
				out.close();
		}
		
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
