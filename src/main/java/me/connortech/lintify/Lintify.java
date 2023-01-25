package me.connortech.lintify;

import java.io.*;
import java.util.Map;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import me.connortech.lintify.command.Banner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;
import picocli.CommandLine;

@CommandLine.Command(mixinStandardHelpOptions = true, version = "0.0.1-SNAPSHOT") // TODO: Substitute pom version here (Example:
                                                                                  // https://github.com/remkop/picocli/blob/main/picocli-examples/src/main/java/picocli/examples/VersionProviderDemo2.java)
public class Lintify implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(Lintify.class);
    private static final String DEFAULT_RULE_FILE = "default_rule.yaml";

    @CommandLine.Option(names = {"-r", "--rule-file"}, description = "Rule File")
    private File ruleFile;

    @CommandLine.Option(names = {"-w", "--warn-only"}, description = "Show only warnings and not fail validation")
    private boolean warnOnly = false;

    @CommandLine.Parameters(paramLabel = "<input file>", defaultValue = "", description = "Input file to validate against rules")
    private File inputFile;

    @Override
    public void run() {

        // Dump Banner
        dumpBanner();

        // Setup Logging
        setupLogbackForConsole();
        log.info("Lintify running on: {} with rule file: {}", inputFile, ruleFile);

        // Validate inputs

        if (ruleFile == null) {
            ruleFile = new File(DEFAULT_RULE_FILE);
        }

        validateInput();

        // Read in rule file
        // TODO: Extract this out into it's own class or function
        Yaml ruleYaml = new Yaml();
        Map<String, Object> ruleData;
        try (InputStream inputStream = new FileInputStream(ruleFile);) {
            ruleData = ruleYaml.load(inputStream);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.info(ruleData.toString());

        // Check input file against all rules

        // Return output of lintify!!

    }

    private void validateInput() {
        if (!inputFile.exists()) {
            // TODO: Do something because the input file does not exist
            log.error("Input file to validate does not exist");
        } else if (!inputFile.isFile()) {
            // TODO: Do something because the input file is not a file
            log.error("Input file to validate is not a file");
        }
        if (!ruleFile.exists()) {
            // TODO: Do something because the input file does not exist
            log.error("Rule file does not exist");
        } else if (!ruleFile.isFile()) {
            // TODO: Do something because the input file is not a file
            log.error("Rule file is not a file");
        }
    }

    private void dumpBanner() {
        new Banner().dump();
    }

    public static LoggerContext setupLogbackForConsole() {
        // So it looks better when commands are run
        ch.qos.logback.classic.Logger root =
                (ch.qos.logback.classic.Logger) org.slf4j.LoggerFactory.getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME);
        root.detachAndStopAllAppenders();
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        lc.reset();

        PatternLayoutEncoder ple = new PatternLayoutEncoder();
        ple.setPattern("[%date] %highlight([%-5level]) %msg%n");
        ple.setContext(lc);
        ple.start();

        ConsoleAppender<ILoggingEvent> consoleAppender = new ConsoleAppender<>();
        consoleAppender.setEncoder(ple);
        consoleAppender.setContext(lc);
        consoleAppender.start();

        root.addAppender(consoleAppender);
        root.setLevel(Level.INFO);
        root.setAdditive(false);
        return lc;
    }

    public static void main(final String[] args) {
        int exitCode = new CommandLine(new Lintify()).execute(args);
        System.exit(exitCode);
    }
}
