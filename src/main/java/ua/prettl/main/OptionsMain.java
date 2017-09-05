package ua.prettl.main;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class OptionsMain {

	public static void main(String[] args) {
		Options options = new Options();
		
		//1. mode
		options.addOption(
				Option.builder("m")
					.hasArg()
					.argName("mode")
					.longOpt("mode")
					.build());
		
		//2. file
	
		
		CommandLineParser parser = new DefaultParser();
		
		try {
			CommandLine cl = parser.parse(options, args);
			
			if (cl.hasOption("m")) {
				String mode  = cl.getOptionValue("m");
				
				if (mode.toUpperCase().equals("MAP")) {
					//generate map
				} else if (mode.toUpperCase().equals("TIMETABLE")) {
					//crete timetable
				} else {
					//print help
					
				}
				
				
				
				
			} else {
				HelpFormatter formatter = new HelpFormatter();
				formatter.printHelp("optionssmain", options);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

}
