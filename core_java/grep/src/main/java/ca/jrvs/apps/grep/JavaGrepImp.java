package ca.jrvs.apps.grep;

import java.io.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaGrepImp implements JavaGrep {

    final Logger logger = LoggerFactory.getLogger(JavaGrep.class);

    private String regex;
    private String rootPath;
    private String outFile;

    @Override
    public String getRegex() {
        return regex;
    }

    @Override
    public void setRegex(String regex) {
        this.regex = regex;
    }

    @Override
    public String getRootPath() {
        return rootPath;
    }

    @Override
    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    @Override
    public String getOutFile() {
        return outFile;
    }

    @Override
    public void setOutFile(String outFile) {
        this.outFile = outFile;
    }

    @Override
    public void process() throws IOException {
        List<File> files = this.listFiles(this.getRootPath());
        List<String> matchedLines = new ArrayList<String>();

        for (File file : files) {
           logger.debug("{} is a directory: {}", file.toString(), file.isDirectory());

           if (!file.isDirectory()) {
               List<String> lines = this.readLines(file);

               for (String line : lines) {
                   if (this.containsPattern(line)) {
                     matchedLines.add(line);
                   }
               }
           }
        }

        for (String line : matchedLines) {
            logger.debug(line);
        }

        this.writeToFile(matchedLines);

    }

    @Override
    public List<File> listFiles(String rootDir) {
        File inputFile = new File(rootDir);
        List<File> files = new ArrayList<File>();
        File[] listFiles = inputFile.listFiles();

        for (File file : listFiles) {
            files.add(file);
        }
        return files;
    }

    @Override
    public List<String> readLines(File inputFile) throws IllegalArgumentException {
        if (!inputFile.isFile()) {
            throw new IllegalArgumentException(inputFile + " is not a file");
        }

        try {
            FileReader fileReader = new FileReader(inputFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String thisLine = null;

            List<String> lines = new ArrayList<String>();

            while ((thisLine = bufferedReader.readLine()) != null) {
                lines.add(thisLine);
            }
            bufferedReader.close();
            return lines;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean containsPattern(String line) {
        String regexPattern = this.getRegex();
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(line);
        boolean matchFound = matcher.find();
        if (matchFound) {
            return true;
        }
        return false;
    }

    @Override
    public void writeToFile(List<String> lines) throws IOException {
        File file = new File(this.getOutFile());
        try {
            boolean result = file.createNewFile();
            if (result) {
                logger.debug("{} has been created", this.getOutFile());
            }
            else {
                logger.debug("{} already exists", this.getOutFile());
            }
        }
        catch (Exception e) {
            e.getStackTrace();
        }
        FileWriter output = new FileWriter(this.getOutFile());
        BufferedWriter buffer = new BufferedWriter(output);

        for (String line : lines) {
            try {
                buffer.write(line);
                buffer.newLine();
            }
            catch (Exception e) {
                e.getStackTrace();
            }
        }
        buffer.close();
    }

    public static void main(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
        }

        BasicConfigurator.configure();

        JavaGrepImp javaGrepImp = new JavaGrepImp();
        javaGrepImp.setRegex(args[0]);;
        javaGrepImp.setRootPath(args[1]);;
        javaGrepImp.setOutFile(args[2]);

        try {
            javaGrepImp.process();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
