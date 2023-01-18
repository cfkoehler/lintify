package me.connortech.lintify;

import picocli.CommandLine;

import java.io.File;

@CommandLine.Command(name = "Lintify", version = "BETA", mixinStandardHelpOptions = true)
public class lintify implements Runnable {

    @CommandLine.Option(names = { "-r", "--rule-file" }, description = "Rule File")
    private File ruleFile;

    @CommandLine.Option(names = { "-w", "--warn-only" }, description = "Show only warnings and not fail validation")
    private boolean warnOnly = false;

    @CommandLine.Parameters(paramLabel = "<input file>", defaultValue = "", description = "Input file to validate against rules")
    private File inputFile;
    @Override
    public void run() {

        // Validate inputs
        validateInput();

        // Read in rule file

        // Check input file against all rules

        // Return output of lintify!!

    }

    private void validateInput() {
        if (!inputFile.exists()) {
            // TODO: Do something because the input file does not exist
        } else if (!inputFile.isFile()) {
           // TODO: Do something because the input file is not a file
        }
    }


    public static void main(final String[] args) {
        int exitCode = new CommandLine(new lintify()).execute(args);
        System.exit(exitCode);
    }
}
