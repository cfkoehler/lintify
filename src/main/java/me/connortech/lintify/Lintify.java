package me.connortech.lintify;

import me.connortech.lintify.command.Banner;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import picocli.CommandLine;

import java.io.File;

@CommandLine.Command(mixinStandardHelpOptions = true, version = "0.0.1-SNAPSHOT") //TODO: Substitute pom version here (Example: https://github.com/remkop/picocli/blob/main/picocli-examples/src/main/java/picocli/examples/VersionProviderDemo2.java)
public class Lintify implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(Lintify.class);
    private boolean bannerDumped = false;


    @CommandLine.Option(names = { "-r", "--rule-file" }, description = "Rule File")
    private File ruleFile;

    @CommandLine.Option(names = { "-w", "--warn-only" }, description = "Show only warnings and not fail validation")
    private boolean warnOnly = false;

    @CommandLine.Parameters(paramLabel = "<input file>", defaultValue = "", description = "Input file to validate against rules")
    private File inputFile;
    @Override
    public void run() {

        // Dump Banner
        dumpBanner();

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

    private void dumpBanner() {
        new Banner().dump();
    }

    public static void main(final String[] args) {
        int exitCode = new CommandLine(new Lintify()).execute(args);
        System.exit(exitCode);
    }
}
