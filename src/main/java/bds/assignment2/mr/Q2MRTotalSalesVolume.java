package bds.assignment2.mr;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

public class Q2MRTotalSalesVolume extends MapReduceQuery {

	@Override
	protected Class<? extends Mapper> getMapper() {
		return SalesVolumeMapper.class;
	}

	@Override
	protected Class<? extends Reducer> getReducer() {
		return SalesVolumeReducer.class;
	}

	@Override
	protected Class getJarByClass() {
		return Q2MRTotalSalesVolume.class;
	}

	@Override
	protected String getJobName() {
		return "Q2 Total Sales Volume in 2010 - MapReduce";
	}

	@Override
	protected String getOutputPrefix() {
		return "Q2_output_mr_";
	}
	
	@Override
	protected Class getOutputValueClass() {
		return LongWritable.class;
	}

	@Override
	protected Class getInputValueClass() {
		return Text.class;
	}

	public static class SalesVolumeMapper extends Mapper<LongWritable, Text, Text, LongWritable> {

		private Text outputKey = new Text();
		private LongWritable outputValue = new LongWritable();

		@Override
		protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			String[] fields = value.toString().split(",");

			if (fields.length >= 8) {
				String dateStr = fields[5];
				String quantityStr = fields[4];
				String stockCode = fields[2];

				try {
					if (Util.is2010(dateStr)) {
						long quantity = Long.parseLong(quantityStr);
						outputKey.set(stockCode);
						outputValue.set(quantity);
						context.write(outputKey, outputValue);
					}
				} catch (NumberFormatException e) {
					// System.err.println(e);
				}
			}
		}
	}

	public static class SalesVolumeReducer extends Reducer<Text, LongWritable, Text, LongWritable> {

		private LongWritable result = new LongWritable();

		@Override
		protected void reduce(Text key, Iterable<LongWritable> values, Context context)
				throws IOException, InterruptedException {
			long totalSalesVolume = 0;

			for (LongWritable value : values) {
				totalSalesVolume += value.get();
			}

			result.set(totalSalesVolume);
			context.write(key, result);
		}
	}
}
