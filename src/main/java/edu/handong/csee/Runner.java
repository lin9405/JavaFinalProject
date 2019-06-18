
package edu.handong.csee;


import org.apache.commons.cli.CommandLine;

import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import edu.handong.csee.util.CLIOption;

import java.io.*;

public class Runner {
	
	public static void main(String[] args) {
		CLIOption myRunner = new CLIOption();
		myRunner.run(args);
		
	}
}

