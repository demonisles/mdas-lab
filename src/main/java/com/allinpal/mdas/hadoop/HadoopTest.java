package com.allinpal.mdas.hadoop;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HadoopTest {

	public static void main(String[] args) throws Exception {
		String hdfs = "hdfs://10.56.201.71:9000";
//		System.setProperty("HADOOP_USER_NAME", "appadm");
		
		System.out.println(System.getenv("HADOOP_USER_NAME"));
		mkdir(hdfs,"mdas");

		touchFile(hdfs,"mdas/apms/loginfo.txt");

		//rmdir(hdfs, "zookeeper_server.pid");

		//appendFile(hdfs, "gjs/1.log");

		//readFile(hdfs);
	}

	public static final FileSystem getFileSystem() throws Exception {
		String hdfs = "hdfs://10.56.201.71:9000";
		Configuration conf = new Configuration();
		conf.set("dfs.client.block.write.replace-datanode-on-failure.policy", "NEVER");
		conf.set("dfs.client.block.write.replace-datanode-on-failure.enable", "true");
		FileSystem fileSystem = FileSystem.get(URI.create(hdfs), conf);
		System.out.println(fileSystem);
		return fileSystem;
	}

	private static void appendFile(String hdfs, String fullNasme) throws Exception {
		FileSystem fileSystem = getFileSystem();
		OutputStream out = fileSystem.append(new Path(hdfs + "/" + fullNasme));
		out.write(("I am gaojs, who are you" + System.currentTimeMillis() + "\r\n").getBytes("UTF-8"));
		out.flush();
		out.close();
	}
	
	private static void readFile(String hdfs) throws Exception {
        FileSystem fileSystem = getFileSystem();
        InputStream in = fileSystem.open(new Path(hdfs + "/"
                + "zookeeper_server.pid"));
        IOUtils.copy(in, System.out);
        fileSystem.close();
    }
	
	private static void touchFile(String hdfs, String fullNasme)
            throws Exception {
        FileSystem fileSystem = getFileSystem();
        boolean res = fileSystem
                .createNewFile(new Path(hdfs + "/" + fullNasme));
        if (res) {
            System.out.println("-------create File Success------" + fullNasme);
        } else {
            System.out.println("-------create File Fail------" + fullNasme);
        }
        fileSystem.close();
    }
	  private static void rmdir(String hdfs, String fullNasme) throws Exception {
	        FileSystem fileSystem = getFileSystem();
	        boolean res = fileSystem.delete(new Path(hdfs + "/" + fullNasme),true);
	        if (res) {
	            System.out.println("------rmdir Success------" + fullNasme);
	        } else {
	            System.out.println("------rmdir Fail------" + fullNasme);
	        }
	        fileSystem.close();
	    }
	  
	  private static void mkdir(String hdfs, String fullName) throws Exception {
	        FileSystem fileSystem = getFileSystem();
	        boolean res = fileSystem.mkdirs(new Path(hdfs + "/" + fullName));
	        if (res) {
	            System.out.println("-------mkdir Success------" + fullName);
	        } else {
	            System.out.println("-------mkdir Fail------" + fullName);
	        }
	    }
	  
	 /* protected static void setEnv(Map<String, String> newenv)
	  {
	    try
	      {
	          Class<?> processEnvironmentClass = Class.forName("java.lang.ProcessEnvironment");
	          Field theEnvironmentField = processEnvironmentClass.getDeclaredField("theEnvironment");
	          theEnvironmentField.setAccessible(true);
	          Map<String, String> env = (Map<String, String>) theEnvironmentField.get(null);
	          env.putAll(newenv);
	          Field theCaseInsensitiveEnvironmentField = processEnvironmentClass.getDeclaredField("theCaseInsensitiveEnvironment");
	          theCaseInsensitiveEnvironmentField.setAccessible(true);
	          Map<String, String> cienv = (Map<String, String>)     theCaseInsensitiveEnvironmentField.get(null);
	          cienv.putAll(newenv);
	      }
	      catch (NoSuchFieldException e)
	      {
	        try {
	          Class[] classes = Collections.class.getDeclaredClasses();
	          Map<String, String> env = System.getenv();
	          for(Class cl : classes) {
	              if("java.util.Collections$UnmodifiableMap".equals(cl.getName())) {
	                  Field field = cl.getDeclaredField("m");
	                  field.setAccessible(true);
	                  Object obj = field.get(env);
	                  Map<String, String> map = (Map<String, String>) obj;
	                  map.clear();
	                  map.putAll(newenv);
	              }
	          }
	        } catch (Exception e2) {
	          e2.printStackTrace();
	        }
	      } catch (Exception e1) {
	          e1.printStackTrace();
	      } 
	  }*/
}
