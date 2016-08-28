< How to run KWIC.jar >

1) Open cmd
2) Change working directory of cmd to the folder containing the KWIC.jar
3) Read below

Usage: java -jar "KWIC.jar" [Architecture Type] [Optional: < inputFile.txt]

Architecture Type (use without square brackets []):
1 for Main/subroutine architecture with shared data
2 for Object-Oriented architecture with observer pattern


Examples:

If you want to use Main/subroutine architecture with shared data with an input file, you can type: java KWIC 1 < exampleInputFileName.txt

If you want to use Main/subroutine architecture with shared data without an input file, you can type: java KWIC 1

If you want to use Object-Oriented architecture with observer pattern with an input file, you can type: java KWIC 2 < exampleInputFileName.txt

If you want to use Object-Oriented architecture with observer pattern without an input file, you can type: java KWIC 2


Example of user input/file input format:
for, a, it
@
Fast and Furious
Man of Steel


So the general user input/file input format (reference to the previous example):
IgnoreWord, IgnoreWord, IgnoreWord, ...
character to indicate end of ignore words and start of lines to process (@)
line to process
line to process
...
(To end manual user input, hold down ctrl + z at the same time and press enter)