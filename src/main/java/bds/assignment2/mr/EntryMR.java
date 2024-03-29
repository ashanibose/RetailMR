package bds.assignment2.mr;

public class EntryMR {

	public static void main(String[] args) throws Exception {
		if (args.length < 3) {
			System.out.println(
					"Usage: java -jar <jar_file_name>.jar <number_of_runs> <input_dir_root> <output_dir_root> ");
			System.exit(1);
		}

		int numberOfRuns = Integer.parseInt(args[0]);
		String inputRootDir = args[1];
		String outputRootDir = args[2];

		// Query 1 - Platform/Framework 1 - MapReduce
		System.out.println("Executing Query 1 - Platform/Framework 1 - MapReduce");
		double avgExecutionTime1 = new Q1MRTotalSalesRevenue().run(numberOfRuns, inputRootDir, outputRootDir);
		System.out.println("\n-------------------------------------------------------------\n");

		// Wait for 1 second between executions
		Thread.sleep(1000);

		// Query 2 - Platform/Framework 1 - MapReduce
		System.out.println("Executing Query 2 - Platform/Framework 1 - MapReduce");
		double avgExecutionTime3 = new Q2MRTotalSalesVolume().run(numberOfRuns, inputRootDir, outputRootDir);
		System.out.println("\n-------------------------------------------------------------\n");

		// Wait for 1 second between executions
		Thread.sleep(1000);

		System.out.println("\n-------------------------------------------------------------\n");
		System.out.println("Query 1 - Platform/Framework 1 - MapReduce = " + (avgExecutionTime1 / 1000) + " seconds");
		System.out.println("Query 2 - Platform/Framework 1 - MapReduce = " + (avgExecutionTime3 / 1000) + " seconds");

	}
}
