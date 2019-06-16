package edu.handong.csee.util;

import org.apache.commons.cli.CommandLine;

import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import java.io.*;

public class CLIOption {



	String input;
	String output;
	
	boolean help;
	

	public void run(String[] args) {
	
		Options options = createOptions();
		
		if(parseOptions(options, args)){
			if (help){
				System.out.println("help");
				printHelp(options);
				return;
			}
			System.out.println(input+"\n"+output+"\n");
			
		}
	}

	private boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();

		try {

			CommandLine cmd = parser.parse(options, args);

			input = cmd.getOptionValue("i");
			output = cmd.getOptionValue("o");
			help = cmd.hasOption("h");

		} catch (Exception e) {
			printHelp(options);
			System.out.println("??");
			return false;
		}

		return true;
	}

	// Definition Stage
	private Options createOptions() {
		Options options = new Options();
		options.addOption(Option.builder("i").longOpt("input")
		        .desc("Set an input file path")
		        .required()
		        .hasArg()
		        .argName("input path")
		        .build());
		
		options.addOption(Option.builder("o").longOpt("output")
		        .desc("Set an output file path")
		        .required()
		        .hasArg()
		        .argName("output path")
		        .build());
		
		options.addOption(Option.builder("h").longOpt("help")
		        .desc("Show a Help page")
		        .argName("Help")
		        .build());

		return options;
	}
	
	private void printHelp(Options options) {
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		String header = "Java Final Project";
		String footer ="";
		formatter.printHelp("Java Final Project", header, options, footer, true);
	}
}

