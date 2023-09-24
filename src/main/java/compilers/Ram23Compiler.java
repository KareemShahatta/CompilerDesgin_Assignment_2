package compilers;

import java.io.*;

public class Ram23Compiler {

    static PrintWriter debug = new PrintWriter(System.out);

    public static void main(String[] args) throws ParseException, TokenMgrError, FileNotFoundException {

        if (args.length == 0) {
            RamParser parser = new RamParser( System.in ) ;
            parser.Goal();
        } else {
            java.io.InputStream is = new java.io.FileInputStream(new java.io.File(args[0]));
            RamParser parser = new RamParser( is ) ;
            parser.Goal();
            // Program root = parser.Goal();

            System.out.println("Program lexed successfully");
        }           
    }
}
