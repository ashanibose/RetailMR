package bds.assignment2.mr;

import org.junit.Test;

import bds.assignment2.mr.EntryMR;

public class EntryMRTest {

//	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//	private final PrintStream originalOut = System.out;

//	@Before
//	public void setUpStreams() {
//		System.setOut(new PrintStream(outContent));
//	}
//
//	@After
//	public void restoreStreams() {
//		System.setOut(originalOut);
//	}

	@Test
	public void testMainMethod() throws Exception {
		// Create arguments for testing
		String[] args = { "1", "/user/Hadoop/retaildata/v5", "/user/Hadoop/retaildata/output" };

		// Call the main method
		EntryMR.main(args);

		// Verify the output
//		String expectedOutput = "Executing Query 1 - Platform/Framework 1 - MapReduce\n" + "Number of runs: 1\n"
//				+ "Input directory root: /user/Hadoop/retaildata/v5\n"
//				+ "Output directory root: /user/Hadoop/retaildata/output\n"
//				+ "Final input path: /user/Hadoop/retaildata/v5/retail.*\n"
//				+ "Final output path: /user/Hadoop/retaildata/output/Q1_output_mr_";
//		assertTrue(outContent.toString().contains(expectedOutput));
	}
}
