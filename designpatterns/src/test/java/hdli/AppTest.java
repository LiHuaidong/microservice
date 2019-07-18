package hdli;

import static org.junit.Assert.assertTrue;

import hdli.creation.EnumSingleton;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void createSingleton() {
        // Singleton.getSingleton();
        EnumSingleton.Instance.sayHello();
    }


}
