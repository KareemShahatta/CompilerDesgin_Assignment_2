# Programming Assignment 2: Scanner / Lexer

-----

### Date Out: Tuesday, September 19
### Due Date: Tuesday, October 10 (start of class)

-----

## Introduction

This programming assignment creates the first module in our complier, the lexical analyzer!

We will use the popular [Java Compiler Compiler (JavaCC)](http://javacc.org/) tool to facilitate the lexical analysis--and eventually the parsing-- of our source files.

JavaCC takes a *lexical specification* and automatically generates several Java files. One of these files, `TokenManager.java`, defines a program that implements a state machine; this a custom-built scanner/lexer based on our lexical specification!

(While JavaCC is a popular lexing and parsing tool, others are also very well known. Flex is a free version of Lex (which was originally proprietary). JFlex is a Java version of Flex. Another alternative Java scanner is SableCC.)

## The Tasks

### Setup

1. **Understand.** As always, think before you code. There's a bit of new IntelliJ project infrastructure in this assignment. But once you get going, this assignment is much easier than Programming Assignment #1. Utilize our discord channel for questions and help.

2. **Ready to Start Coding?** Follow the same steps as in Programming Assignment #0 to "accept" the GHC assignment, and then clone your GH repo into your local IntelliJ.

3. **Start Coding and Use Maven** Initially, IntelliJ won't be happy with your starter code. But don't worry, everything will still work:

* `compilers.Ram23Compiler` (located in `src/main/java/compilers/Ram23Compiler.java`) may not be able to find `compilers.RamParser` (which will be generated and saved into `target/generated-sources/javacc/compilers/RamParser.java`) -- this is fine. This file is created automatically by JavaCC using our lexical specification.

We'll be interacting with our project solely through `Maven`, as demonstrated in class. You can either do this from the terminal, or via IntelliJ. The following is the general workflow of the project:

1. `mvn clean` -- will erase everything from the `target` directory in your project, including any generated `.class` files and `.java` source files generated by `JavaCC`
2. `mvn javacc:javacc` -- runs JavaCC against your lexical specification, automatically creating `.java` files that are placed in the `target/generated-sources/javacc/compilers/` directory. **Whenever you edit/modify the `.jj` file, you need to re-generate the scanner by executing this step.**
3. running the `compilers.Ram23Compiler` driver -- tests your scanner against a single `.ram23` program
4. `mvn test` -- runs your scanner against multiple test cases in batch

Notive that the `pom.xml` Maven configuration file now includes a JavaCC dependency (so JavaCC is automatically downloaded and 
included in your project), as well as a JavaCC Maven plugin (so that we can call a `javacc` goal from within Maven). You don't have to 
modify anything in this file. 

* The only file that you need to modify for this project is `src/main/javacc/RamGrammar.jj`
* **The only file that you need to modify for this project is `src/main/javacc/RamGrammar.jj`**
* <b><u>The only file that you need to modify for this project is `src/main/javacc/RamGrammar.jj`</u></b>

### JavaCC

Familiarize yourself with JavaCC. Some additional resources:
* Our Thain textbook is generating a compiler for the C language, using C tools. In section 3.7, Thain talks about a scanner generator
named Flex. You'll notice that JavaCC works the same way as the original Flex tool.
<!--* The Appel text has a very dense, terse, intro on JavaCC. Start there.-->
* A more complete documentation/readme can be found here [Theodore Norvell's JavaCC FAQ](http://www.engr.mun.ca/~theo/JavaCC-FAQ/javacc-faq-moz.htm). Chapters 1-3, and especially Ch. 3, are the most relevant, but you probably won't need to read this.

### Create a Lexical Specification for our Ram23 Programming Language

Let's create our lexical specification! For our course project, we'll modify the "MiniJava" language (which is a subset of Java). We'll call our language the **Ram23** language.
* [MiniJava Core Language](MiniJava.html) *(contains a sample programs at the bottom of the page)*
* **[Our Ram23 Language Specification](Ram23.html)**
* And, if you're curious, [The Java Language Specification, Java SE 20 Edition](http://docs.oracle.com/javase/specs/) (~800 pages!)

Modify the skeleton `RamGrammar.jj` file according to the lexical specification for our Ram language. You should insert your lexical specification code where I specify in the comments. It will not be necessary to insert any grammar into the bottom area for the lexical analysis -- that will be for the parser, which is a subsequent project!

Notice where our Ram language differs from MiniJava, and where MiniJava differs from Java (e.g. block comments may not be nested, the print argument is an expression list, && is "and", etc.).

Define whitespace in the `SKIP` JavaCC section.

Define tokens in the `TOKEN` section. (You will need quite a few tokens just for recognizing each of the possible keywords in the Ram language.)

Define comments in the `SPECIAL_TOKEN` section. Special tokens are very similar to skipped productions, except that these tokens will be created, but won't participate in the parsing process.

### Generate the Lexer

Run the `mvn javacc:javacc` Maven plugin goal. Notice the created Java files in the `target/generated-sources/javacc/compilers/` directory.

### Run the Lexer on a single file:

Examine and run the `compilers.Ram23Compiler` driver.

### Testing the Lexer on multiple files:

Create a good sample of Ram programs and run your generated Lexer on them using the `test` Maven lifecycle. Is the output of the Lexer what you expected?

For the sample program at the end of the MiniJava Language Specification, the Scanner would output something close to:

```
    <CLASS, "class">
    <IDENTIFIER, "Factorial">
    <LCURLY, "{">
    <PUBLIC, "public">
    <STATIC, "static">
    <VOID, "void">
    <MAIN, "main">
    <LPAREN, "(">
    <STRING, "String">
    <LBRACKET, "[">
    <RBRACKET, "]">
    <IDENTIFIER, "a">
    <LCURLY, "{">
    <PRINTLN, "println">
    <LPAREN, "(">
    <NEW, "new">
    <IDENTIFIER, "Fac">
    <RPAREN, ")">
    <DOT, ".">
    <IDENTIFIER, "ComputeFac">
    <LPAREN, "(">
    <INTEGER, "10">
    <RPAREN, ")">
    <RPAREN, ")">
    <SEMICOLON, ";">
    ... and so on
```

To get more detailed output about your tokens, you could substitute

```
( RamToken() {System.out.println("Kind: " + token.kind + " line " + token.beginLine + ", column " + token.beginColumn + " - line " + token.endLine + ", column " + token.endColumn + " : " + token.image);} )*
```
for
```
( RamToken() )*
```
in `RamGrammar.jj`.

## Project Structure

Ram23Compiler:

* `pom.xml` (Maven configuration file)
* `src/main/java/compilers/Ram23Compiler.java` (driver file to test a single .ram23 source file)
* `src/main/javacc/RamGrammar.jj` (lexical specification for Ram23)
* `src/test/java/compilres/test_programs/pass/`(Ram programs with no lexical errors)
* `src/test/java/compilres/test_programs/fail/`(Ram programs that have lexical errors)
* `src/test/java/compilres/test_programs/PassTest.java` (a junit class for running ram23 programs that should pass the lexer)   
* `target/generated-sources/javacc/compilers/` (directory to hold the automatically generated files)
* `target/classes/` (compiled files)


## CSC416 vs CSC565

Additional work for **CSC565 students only**:

1. Add three pass tests (`.ram23` files) and three fail tests to the `test_programs` folder. You'll also need to modifty the `PassTest.java` and `FailTest.java` test suites.
1. You'll notice for the fail tests, and when the lexer fails in general, that JavaCC outputs an error similar to the following:  
`compilers.TokenMgrError: Lexical error at line 3, column 9.  Encountered: '34' (34),`  
This error is useful in that it reports the line and column number; but, the remainder of the line "encountered: '34' (34)" seems like nonsence. Figure out what this integer is representing. Write your answer in a file that you add to your project, named `weird_int.md`.

## Submission instructions

Push your completed Java Maven project to your GitHub. Verify the success of the autograding GitHub Action.