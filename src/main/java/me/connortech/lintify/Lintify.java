package me.connortech.lintify;

import java.io.*;
import java.net.URL;
import java.util.Map;
import java.util.Objects;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import me.connortech.lintify.command.Banner;
import me.connortech.lintify.util.GitRepositoryState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;
import picocli.CommandLine;

@CommandLine.Command(mixinStandardHelpOptions = true, versionProvider = Lintify.ManifestVersionProvider.class)
public class Lintify implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(Lintify.class);
    private static final String DEFAULT_RULE_FILE = "default_rule.yaml";

    @CommandLine.Option(names = {"-r", "--rule-file"}, description = "Rule file that defines rules and parameters to use against the input file")
    private String ruleFilePath;

    @CommandLine.Option(names = {"-w", "--warn-only"}, description = "Show only warnings and not fail validation")
    private boolean warnOnly = false;

    @CommandLine.Parameters(paramLabel = "<input file>", defaultValue = "", description = "Input file to validate against rules")
    private String inputFilePath;

    @Override
    public void run() {

        // Dump Banner
        dumpBanner();

        // Setup Logging
        setupLogbackForConsole();
        log.info("Lintify running on: {} with rule file: {}", inputFilePath, ruleFilePath);

        // Validate inputs
        if (ruleFilePath == null) {
            // TODO: The default rule file is not being found
            URL ruleFileUrl = this.getClass().getClassLoader().getResource(DEFAULT_RULE_FILE);
            log.debug("DEFAULT RULE FILE URL {}", ruleFileUrl);
            ruleFilePath = Objects.requireNonNull(ruleFileUrl).toString();
        }
        File ruleFile = new File(ruleFilePath);
        File inputFile = new File(inputFilePath);
        validateFileInput(ruleFile, "Rule File");
        validateFileInput(inputFile, "Input File");

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

    private void validateFileInput(File file, String fileType) {
        if (!file.exists()) {
            // TODO: Do something because the input file does not exist
            log.error("{} to validate does not exist", fileType);
        } else if (!file.isFile()) {
            // TODO: Do something because the input file is not a file
            log.error("{} to validate is not a file", fileType);
        }
    }

    private void dumpBanner() {
        new Banner().dump();
    }

    public static void setupLogbackForConsole() {
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
    }

    public static void main(final String[] args) {
        int exitCode = new CommandLine(new Lintify()).execute(args);
        System.exit(exitCode);
    }

    static class ManifestVersionProvider implements CommandLine.IVersionProvider {
        public String[] getVersion() throws Exception {
            GitRepositoryState version = GitRepositoryState.getRepositoryState();
            return new String[] {"Lintify version " + version.getBuildVersion()};
        }
    }

}
