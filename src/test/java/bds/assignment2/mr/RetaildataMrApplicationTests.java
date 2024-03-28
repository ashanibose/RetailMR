package bds.assignment2.mr;

import org.junit.Test;

public class RetaildataMrApplicationTests {

	private static String INPUT_ROOT_DIR = "/user/Hadoop/retaildata/v5";
	private static String OUTPUT_ROOT_DIR = "/user/Hadoop/retaildata/output";
	private static int NUMBER_OF_EXECUTION = 1;

	@Test
	public void run_Q1MRTotalSalesRevenue() throws Exception {

		Q1MRTotalSalesRevenue q1mrTotalSalesRevenue = new Q1MRTotalSalesRevenue();

		q1mrTotalSalesRevenue.run(NUMBER_OF_EXECUTION, INPUT_ROOT_DIR, OUTPUT_ROOT_DIR);

		System.out.println("------------------");
	}

	@Test
	public void run_Q2MRTotalSalesVolume() throws Exception {

		Q2MRTotalSalesVolume q2mrTotalSalesVolume = new Q2MRTotalSalesVolume();

		q2mrTotalSalesVolume.run(NUMBER_OF_EXECUTION, INPUT_ROOT_DIR, OUTPUT_ROOT_DIR);

		System.out.println("------------------");

	}
}