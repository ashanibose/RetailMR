# RetailMR - MapReduce Retail Data Analysis

A Java-based MapReduce project for analyzing retail sales data, specifically designed to calculate total sales revenue and sales volume for the year 2010.

## Project Overview

This project implements Apache Hadoop MapReduce jobs to process retail transaction data and perform two main analytical queries:

1. **Query 1**: Calculate total sales revenue for 2010
2. **Query 2**: Calculate total sales volume (quantity) by stock code for 2010

## Project Structure

```
RetailMR/
├── pom.xml                          # Maven configuration
├── README.md                        # Project documentation
└── src/
    ├── main/
    │   ├── java/bds/assignment2/mr/
    │   │   ├── EntryMR.java                    # Main entry point
    │   │   ├── MapReduceQuery.java             # Abstract base class for MapReduce jobs
    │   │   ├── Q1MRTotalSalesRevenue.java      # Query 1: Total revenue calculation
    │   │   ├── Q2MRTotalSalesVolume.java       # Query 2: Sales volume calculation
    │   │   ├── MRUtil.java                     # Utility functions
    │   │   ├── IRunConfig.java                 # Configuration constants
    │   │   └── WordCount.java                  # Example WordCount implementation
    │   └── resources/
    │       └── META-INF/
    │           └── Manifest.txt                # JAR manifest
    └── test/
        └── java/bds/assignment2/mr/
            ├── EntryMRTest.java                # Unit tests
            └── RetaildataMrApplicationTests.java
```

## Core Components

### EntryMR.java
The main entry point that orchestrates the execution of both MapReduce queries. It accepts command-line arguments for:
- Number of runs (for performance benchmarking)
- Input directory root path
- Output directory root path

### MapReduceQuery.java
Abstract base class that provides common functionality for MapReduce jobs including:
- Job configuration and execution
- Performance measurement and averaging
- Warm-up runs for accurate benchmarking
- Output file naming with timestamps

### Q1MRTotalSalesRevenue.java
Implements Query 1 to calculate total sales revenue for 2010:
- **Mapper**: Filters transactions from 2010 and extracts price data
- **Reducer**: Sums all prices to calculate total revenue
- **Output**: Single key-value pair with total revenue

### Q2MRTotalSalesVolume.java
Implements Query 2 to calculate sales volume by stock code for 2010:
- **Mapper**: Filters transactions from 2010 and extracts stock code and quantity
- **Reducer**: Sums quantities for each stock code
- **Output**: Stock code to total quantity mapping

### MRUtil.java
Utility class providing helper functions:
- `is2010(String dateStr)`: Validates if a date string represents a 2010 transaction

### IRunConfig.java
Configuration interface containing:
- HDFS URL configuration (`hdfs://localhost:9000`)
- Date format specification (`dd-MM-yyyy HH:mm`)

## Data Format

The project expects retail transaction data in CSV format with the following structure:
```
[field0],[field1],[stockCode],[field3],[quantity],[date],[price],[field7],...
```

Key fields used:
- **Field 2**: Stock Code
- **Field 4**: Quantity
- **Field 5**: Date (format: dd-MM-yyyy HH:mm)
- **Field 6**: Price

## Prerequisites

- Java 8 or higher
- Apache Hadoop (for MapReduce execution)
- Maven (for building the project)
- JUnit 4.8.1 (for testing)

## Building the Project

```bash
mvn clean compile
```

## Running the Application

### Command Line Usage

```bash
java -jar RetailMR.jar <number_of_runs> <input_dir_root> <output_dir_root>
```

### Parameters

- `number_of_runs`: Number of times to execute each query (for performance averaging)
- `input_dir_root`: HDFS path to input data files (supports wildcards like `retail.*`)
- `output_dir_root`: HDFS path for output directories

### Example

```bash
java -jar RetailMR.jar 3 /user/Hadoop/retaildata/v6 /user/Hadoop/retaildata/output
```

This will:
1. Execute Query 1 (Total Revenue) 3 times and calculate average execution time
2. Execute Query 2 (Sales Volume) 3 times and calculate average execution time
3. Output results to timestamped directories in the specified output path

## Output

The application generates:
- **Query 1 Output**: `Q1_output_mr_[timestamp]_[random].txt` containing total revenue
- **Query 2 Output**: `Q2_output_mr_[timestamp]_[random].txt` containing stock code to quantity mappings
- **Console Output**: Execution times and performance metrics

## Testing

Run the test suite:

```bash
mvn test
```

The test suite includes:
- `EntryMRTest`: Tests the main execution flow
- `RetaildataMrApplicationTests`: Additional application tests

## Performance Features

- **Warm-up Runs**: Performs initial warm-up runs to ensure accurate timing
- **Multiple Executions**: Supports multiple runs for performance averaging
- **Execution Time Measurement**: Tracks and reports execution times for each run
- **Average Calculation**: Computes average execution time across all runs

## Configuration

Key configuration settings in `IRunConfig.java`:
- HDFS URL: `hdfs://localhost:9000`
- Date Format: `dd-MM-yyyy HH:mm`

Modify these settings as needed for your Hadoop cluster configuration.

## Dependencies

- **JUnit 4.8.1**: For unit testing
- **Apache Hadoop**: For MapReduce framework (runtime dependency)

## License

This project is part of a Big Data Systems assignment (BDS Assignment 2).
