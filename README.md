# language-generator

An Artificial Intelligence project which produces pseudo-random, syntactically correct text based on n-gram language modeling.

[v.042716.1]  
Jacob Ochs  
jochs@westmont.edu  
Artificial Intelligence Spring 2016 -- Westmont College  

## I. Acknowledgments:

Some the concepts behind my code were brainstormed in joint collaboration with Jonathan Skidanov, 
Chris Betsill, and Sam Bentz.

## II. Running Instructions:

This program takes three command line arguments as input: the first being the path to the location
of the folder containing the corpus text document(s) to be processed, the second being an integer representing
the number of n-grams to be modeled, and the third being an integer representing the number of words to be generated.
For example:

    java LanguageGenerator C:\Users\Jacob\Desktop\CorpusTexts 2 30

is a sample command to run the program, processing the files in the CorpusTexts folder with 2(bi)-gram modeling,
resulting in 30 words generated. This project will process any number of text documents, as long as they are 
all .txt files and are grouped in the same folder file path. Any number of words may also be generated.
Runtime may become a factor as text file size become larger.

## III. Artificial Intelligence Strategies:

A primary focus of this deliverable is the generation of n-grams to model the placements of words in relation
to their surrounding context. This can be done for any number of n-grams, although it is recommended to use an 
n between 2-5. Once n-gram models have been generated, the program will pick a word which starts with a capital letter
randomly, then using this as the first word with which to use the n-gram models to recursively generate a pseudo-random
string of words. This string will have syntatic correctness, but will most likely be incorrect semantically.
