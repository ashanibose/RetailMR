package bds.assignment2.mr;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

public class Q1MRTotalSalesRevenue extends MapReduceQuery {

	@Override
	protected Class<? extends Mapper> getMapper() {
		return RevenueMapper.class;
	}

	@Override
	protected Class<? extends Reducer> getReducer() {
		return RevenueReducer.class;
	}

	@Override
	protected Class getJarByClass() {
		return Q1MRTotalSalesRevenue.class;
	}

	@Override
	protected String getJobName() {
		return "Q1 Total Revenue in 2010 - MapReduce";
	}

	@Override
	protected String getOutputPrefix() {
		return "Q1_output_mr_";
	}
	
	@Override
	protected Class getOutputValueClass() {
		return DoubleWritable.class;
	}

	@Override
	protected Class getInputValueClass() {
		return Text.class;
	}

	public static class RevenueMapper extends Mapper<LongWritable, Text, Text, DoubleWritable> {

		private Text outputKey = new Text();
		private DoubleWritable outputValue = new DoubleWritable();

		@Override
		protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			String[] fields = value.toString().split(",");

			if (fields.length >= 8) {
				String dateStr = fields[5];
				String priceStr = fields[6];

				try {
					if (Util.is2010(dateStr)) {
						double price = Double.parseDouble(priceStr);
						outputKey.set("TotalRevenue");
						outputValue.set(price);
						context.write(outputKey, outputValue);
					}
				} catch (NumberFormatException e) {
					// System.err.println(e);
				}
			}
		}
	}
	
	public static class RevenueReducer extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {

		private DoubleWritable result = new DoubleWritable();

		@Override
		protected void reduce(Text key, Iterable<DoubleWritable> values, Context context)
				throws IOException, InterruptedException {
			double totalRevenue = 0.0;

			for (DoubleWritable value : values) {
				totalRevenue += value.get();
			}

			result.set(totalRevenue);
			context.write(key, result);
		}
	}
}
