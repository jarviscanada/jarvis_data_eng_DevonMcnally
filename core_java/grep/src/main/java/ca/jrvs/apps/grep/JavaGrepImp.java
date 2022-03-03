package ca.jrvs.apps.grep;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class JavaGrepImp implements  JavaGrep{

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

//works and is recursive. Actually though.
    @Override
    public List<File> listFiles(String rootDir) {

        List<File> proxyList = new ArrayList<>();
        File rootDirectory = new File(rootDir);
        File[] files = rootDirectory.listFiles();

        for (File file: files){

            if(file.isDirectory()){

                proxyList.addAll(listFiles(String.valueOf(file)));

            }
            else if(file.isFile()){
                proxyList.add(file);
            }

        }

        return proxyList;
    }

//This code works but is slow. Must find a better way.
    @Override
    public List<String> readLines(File inputFile) throws FileNotFoundException {

        List<String> proxyList = new ArrayList<>();
        FileReader fileReader = new FileReader(inputFile);

        try {

            StringBuffer stringBuffer = new StringBuffer();
            while (fileReader.ready()){
                char c = (char) fileReader.read();
                if (c == '\n'){
                    proxyList.add(stringBuffer.toString());
                    stringBuffer = new StringBuffer();
                }
                else {
                    stringBuffer.append(c);
                }
            }
            if (stringBuffer.length() < 0){
                proxyList.add(stringBuffer.toString());
            }
            int data = fileReader.read();

            while (data != -1){

            }
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
