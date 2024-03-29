package bds.assignment2.mr;

import org.junit.Test;

public class EntryMRTest {

	@Test
	public void testMainMethod() throws Exception {
		// Create arguments for testing
		String[] args = { "1", "/user/Hadoop/retaildata/v6", "/user/Hadoop/retaildata/output" };

		// Call the main method
		EntryMR.main(args);

	}
}
