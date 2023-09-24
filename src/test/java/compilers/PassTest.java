package compilers;

import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;


/**
 *
 * @author richardburns
 */
@RunWith(value = Parameterized.class)
public class PassTest {

    @Parameter
    public String filePath;

    @Parameters(name = "{index}: testFile - {0}")
    public static Object[] data() {
        return new Object[] {
                "src/test/java/compilers/test_programs/pass/pass1.ram23",
                "src/test/java/compilers/test_programs/pass/pass2.ram23",
                "src/test/java/compilers/test_programs/pass/pass3.ram23",
                "src/test/java/compilers/test_programs/pass/pass4.ram23",
                "src/test/java/compilers/test_programs/pass/pass5.ram23"
        };
    }


    @Test
    public void runPassTests() {
        try {
            String args[] = {filePath};
            RamParser.main(args);
        } catch (Exception e) {
            fail(e.toString());
        }
    }


    public void runSinglePassTest(){
        String fileName = System.getProperty("fileName");
        String args[] = {fileName};
        try {
            RamParser.main(args);
        } catch (Exception e) {
            fail(e.toString());
        }
    }


}
