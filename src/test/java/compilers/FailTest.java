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
public class FailTest {

    @Parameter
    public String filePath;

    @Parameters(name = "{index}: testFile - {0}")
    public static Object[] data() {
        return new Object[] {
            "src/test/java/compilers/test_programs/fail/fail1.ram23",
            "src/test/java/compilers/test_programs/fail/fail2.ram23",
            "src/test/java/compilers/test_programs/fail/fail3.ram23",
            "src/test/java/compilers/test_programs/fail/fail4.ram23"
        };
    }


    @Test
    public void runFailTests() {
        try {
            String args[] = {filePath};
            RamParser.main(args);
            fail("expected scanner to throw an exception and it didn't");
        } catch (ParseException e) {
            ;  // do nothing
            System.out.println(e);
        } catch (TokenMgrError e) {
            ;  // do nothing
            System.out.println(e);
        } catch (Exception e) {   // fail on an exception that we didn't except
            fail(e.toString());
        }
    }


}
