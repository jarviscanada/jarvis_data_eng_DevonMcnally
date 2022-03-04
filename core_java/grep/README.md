# Introduction

The Java Grep project is designed to function the same as the grep command in Linux Bash. It searches a given directory for 
a matching regex pattern. The program is written in Java utilizing the Maven framework as well a git for version control.


# Quick Start
The program must be executed with 3 arguments. The regex pattern to find, the source directory and 
the output directory(including filename for output file). This can be done in the IDE by editing configurations
or can be done using the jar file with 'java -cp target/grep-1.0-SNAPSHOT.jar arg1 arg2 arg3'.


#Implemenation

The program contains 4 key methods that work together. These methods are: 
    
-listFiles(String rootDirectory): This method recursively finds all the files in a given directory 
and returns them a list of files. 

-readLines(File inputFile): This method takes a given file and returns all of its text contents
in a list of strings.

-containsPattern(String line): This method takes a string and checks if it matches the regex pattern
provided by the user at the time of execution. It will return a boolean based on the result of the matching
process.

-writeToFile(List String): This method takes a list of strings which contains the positive matches 
from the previous method. It then creates a file and output those strings to the file path given
by the user as argument #3.
## Pseudocode

for each file in listFiles{
readLines(file)}

for each line in readLines{
containsPattern(line)}

writeToFile(containsPatternList)

## Performance Issue
The original design for the project used a lot of for loops to iterate through every single 
file to find the matching pattern. This means thousands of lines of text needed to be scanned 
everytime the program is executed. The answer to this problem was switching to Java 8 streams.

# Test
All testing was done manually. To test listFiles I made several extra directories and files to 
make sure they were all being found and added to the list. 

# Deployment
The project is available on github and has also been dockerized and pushed to docker hub.

# Improvement
To improve this project I would test each method individually. First using the original for loop approach, next 
using lambda streams. Then I would use whatever approach was fastest for each method. 