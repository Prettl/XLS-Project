package ua.prettl;

import org.apache.commons.cli.*;

import java.util.Arrays;

public class CommonsCliDemo {
    public static void main(String[] args) {

        Options options = new Options();

        options.addOption(
                Option.builder("t")
                            .argName("t")
                            .hasArgs()
                            .longOpt("long option")
                            .desc("description")
                            .required(false)
                            .build());

        CommandLineParser clp = new DefaultParser();

        String[] args1 = {"-t", "value1", "value2", "value3"};

        try {
            CommandLine cl = clp.parse(options, args1);

            System.out.println(cl.hasOption("t"));
            System.out.println(Arrays.toString(cl.getOptionValues("t")));

        } catch (ParseException e) {
            e.printStackTrace();
        }


    }
}
