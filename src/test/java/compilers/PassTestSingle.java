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
public class PassTestSingle {

    @Test
    public void singleTest() {
        String fileName = System.getProperty("fileName");
        String args[] = {fileName};
        try {
            RamParser.main(args);
        } catch (Exception e) {
            fail(e.toString());
        }
    }
}