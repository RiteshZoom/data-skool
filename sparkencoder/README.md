# Project Title
Spark Application to compress the the content of the file through Encoder Algorithm.

## Guidelines
1. We are providing generic template, you need to just fill in the missing components.
2. Feel free to change the project structure or version of the spark as per your convenience
3. input sample can found under input folder(input.txt)


## Project Description

Data Compressor algorithm
1. Input requirements:
a. This algorithm will work only on certain kinds of data. In particular, the input files
must be text files contain ONLY the letters “a-z” and “A-Z”.
b. Legal: “abcdqwertyABCMNLPP”
c. Illegal: “abc123” (numbers aren’t allowed in input)
2. Data that doesn’t conform to the input requirements must be rejected.
The compression algorithm works as follows: any repeated characters in the input string should be replaced with a number (the number of repeated characters) followed by the character. Here are some examples:
1. “aaa” becomes “3a”
2. “aaaabbbccd” becomes “4a3b2c1d”
3. “aabbaabb” becomes “2a2b2a2b”
4. “abcd” becomes “1a1b1c1d”


Spark application


1. Read the input file.
2. Compress it using the Run-Length-Encoder.
3. If the compressed file size smaller than the input file, then it should write the data to the
specified output location.
4. If the compressed file size is greater than or equal than the input file, then the application
should NOT write the output file


### Installing

Go to the root folder run command "sbt clean pacakge"


## Running the tests


## Runnng the application

1. git clone git@github.com:okcredit/sparkencoder.git
2. Do your changes and run as below
2. sbt command: from root folder run the below command
2. sbt "run <app_name> <input_file_path> <output_folder>"
3. example : sbt "run encoder /tmp/inputfile /tmp/encodedoutput"





