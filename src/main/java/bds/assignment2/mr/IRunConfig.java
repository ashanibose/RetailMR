package bds.assignment2.mr;

import java.text.SimpleDateFormat;

public interface IRunConfig {

	public String HDFS_URL = "hdfs://localhost:9000";
	public SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy HH:mm");
}
