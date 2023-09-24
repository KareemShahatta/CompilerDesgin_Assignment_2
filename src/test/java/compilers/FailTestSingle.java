package compilers;

import static org.junit.Assert.assertEquals;
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
public class FailTestSingle {

    @Test
    public void singleTest() {
        String fileName = System.getProperty("fileName");
        String args[] = {fileName};
        try {
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