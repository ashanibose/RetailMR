package bds.assignment2.mr;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public abstract class MapReduceQuery {

	protected static Configuration conf;
	protected String inputRootDir;
	protected String outputRootDir;
	protected int numberOfRuns = 1;
	protected SimpleDateFormat printDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

	static {
		conf = new Configuration();
		conf.set("fs.defaultFS", IRunConfig.HDFS_URL);
	}

	protected abstract Class<? extends Mapper> getMapper();

	protected abstract Class<? extends Reducer> getReducer();

	protected abstract Class getJarByClass();

	protected abstract String getJobName();

	protected abstract String getOutputPrefix();

	protected abstract Class getOutputValueClass();

	protected abstract Class getInputValueClass();
	

	public double run(int numberOfRuns, String inputRootDir, String outputRootDir) throws Exception {
		this.numberOfRuns = numberOfRuns;
		this.inputRootDir = inputRootDir;
		this.outputRootDir = outputRootDir;
		
		// Print arguments
        System.out.println("Number of runs: " + numberOfRuns);
        System.out.println("Input directory root: " + inputRootDir);
        System.out.println("Output directory root: " + outputRootDir);

		// Warm-up phase
		for (int i = 0; i < 1; i++) {
			System.out.println("Executing a wramup pass...");
			runMapReduceJob();
		}

		// Create a matrix to store execution times for each run
		long[] executionTimes = new long[numberOfRuns];

		System.out.println("\nExecuting - " + getJobName() + "\n");

		for (int i = 0; i < numberOfRuns; i++) {
			long startTime = System.currentTimeMillis();

			// Run the MapReduce job
			runMapReduceJob();

			long endTime = System.currentTimeMillis();
			long executionTime = endTime - startTime;
			executionTimes[i] = executionTime;

			System.out.println("Execution time for run " + (i + 1) + ": " + executionTime + " ms");
		}

		// Calculate the average execution time
		double totalExecutionTime = 0;
		for (long time : executionTimes) {
			totalExecutionTime += time;
		}
		double averageExecutionTime = totalExecutionTime / numberOfRuns;

		System.out.println("\nAverage Execution Time: " + averageExecutionTime + " ms");

		return averageExecutionTime;
	}

	private void runMapReduceJob() throws Exception {
		Job job = Job.getInstance(conf, getJobName());
		job.setJarByClass(getJarByClass());
		job.setMapperClass(getMapper());
		job.setReducerClass(getReducer());

		job.setOutputKeyClass(getInputValueClass());
		job.setOutputValueClass(getOutputValueClass());

		String timestamp = printDateFormat.format(new Date());
		Random random = new Random();
		int randomNumber = random.nextInt(10000); // Generate a random number between 0 and 9999

		String inputPathStr = inputRootDir + "/retail.*";
		String outputDirName = getOutputPrefix() + timestamp + "_" + randomNumber;
		String outputPathStr = outputRootDir + "/" + outputDirName;
		
        System.out.println("Final input path: " + inputPathStr);
        System.out.println("Final output path: " + outputPathStr);

		FileInputFormat.addInputPath(job, new Path(inputPathStr));
		FileOutputFormat.setOutputPath(job, new Path(outputPathStr));

		job.waitForCompletion(true);
	}

}
