Bangla-Stemmer
==============
This program converts Bangla words to their corresponsing stems.
```
Sample Input: করিম কাজটি করছে 
Sample Output: করিম কাজ কর 
```

Running the application
-----------------------
Run `Stemmer.java`, passing the rules file and the input file as command line arguments.
```
Stemmer rulesfile inputfile
```
Rules file defines how the stemming will be conducted. The default rules file is ``common.rules``, which is included in this repository.


Structure of the rules file
---------------------------

The rules file is divided into several blocks. Each block is enclosed within curly braces. Each block contains sevaral rules, each in a line. These rules dictates how the stemming will be performed. On first pass of the algorithm, the rules on the first block is applied. On second pass, rules of the second of block is applied and so on.

Consider the following rules:
```
X                       # When X appears at the end of a word, remove it
Y       ->      Z       # When Y appears at the end of a word, replace it with Z
Y.Z     ->      A.B     # When Y, followed by some character a, followed by Z 
                        # appears at the end of a word, replace it with AaB                        
```

Here, X, Y, Z etc can consist of one or more characters. Rules within the same block are arranged in decreasing order of priority. So if a word matches more than one rule within the same block, first matching rule will be applied.
