package ca.jrvs.apps.grep;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaGrepLambdaImp implements JavaGrep{


    final Logger logger = LoggerFactory.getLogger(JavaGrep.class);


    private String regex;
    private String rootPath;
    private String outFile;



    public static void main(String[] args) throws IOException {
        if(args.length !=3){
            throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
        }


        BasicConfigurator.configure();

        JavaGrepImp javaGrepImp = new JavaGrepImp();
        javaGrepImp.setRegex(args[0]);
        javaGrepImp.setRootPath(args[1]);
        javaGrepImp.setOutFile(args[2]);

        javaGrepImp.process();


    }



    @Override
    public void process() throws IOException {

        List<String> matchedLines = new ArrayList<>();
        List<File> fileList = listFiles(getRootPath());
        List<String> readList;



        for (File file: fileList){
            readList = readLines(file);
            for (String line: readList){
                if (containsPattern(line)){
                    matchedLines.add(line);
                }
            }
        }

        writeToFile(matchedLines);
    }

    @Override
    public List<File> listFiles(String rootDir) {

        List<File> proxyList = new ArrayList<>();
        File rootDirectory = new File(rootDir);
        File[] files = rootDirectory.listFiles();


        try {
            Files.walk(Paths.get(String.valueOf(rootDirectory)))
                    .map(s -> s.toFile())
                    .forEach(s ->proxyList.add(s));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return proxyList;
    }

    @Override
    public List<String> readLines(File inputFile) throws FileNotFoundException {

        List<String> proxyList = new ArrayList<>();

        try {
            Stream<String> fileLines = Files.lines(Paths.get(inputFile.getAbsolutePath()));

            proxyList = fileLines.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }


        return proxyList;
    }

    @Override
    public boolean containsPattern(String line) {

        Pattern pattern = Pattern.compile(getRegex());
        Matcher matcher = pattern.matcher(line);
        boolean result = false;


        while (matcher.find()){


            result=true;
        }

        return result;


    }

    @Override
    public void writeToFile(List<String> lines) throws IOException {


        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;

        try {
            fileWriter = new FileWriter(getOutFile(), false);
            bufferedWriter = new BufferedWriter(fileWriter);


            for (String line : lines) {
                bufferedWriter.write(line);
                bufferedWriter.write("\n");
            }

            bufferedWriter.flush();
        } finally {
            if(fileWriter != null){
                fileWriter.close();
            }
            if(bufferedWriter!= null){
                bufferedWriter.close();
            }

        }
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
    public String getRegex() {
        return regex;
    }

    @Override
    public void setRegex(String regex) {
        this.regex =regex;
    }

    @Override
    public String getOutFile() {
        return outFile;
    }

    @Override
    public void setOutFile(String outFile) {
        this.outFile = outFile;
    }
}

