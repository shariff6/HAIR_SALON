import org.junit.Before;
import org.junit.*;
import static org.junit.Assert.*;

public class StylistTest {

    private Stylist stylistUnderTest;

    @Before
    public void setUp() {
        stylistUnderTest = new Stylist("name", 0, "idNumber", "phoneNumber", "email", 0, 0);
    }
    @Test
     public void Stylist_instantiatesCorrectly_true() {
        assertEquals(true, stylistUnderTest instanceof Stylist);
    }
    @Test
    public void getName_StylistInstantiatesWithName_name() {
        assertEquals("name", stylistUnderTest.getName());
    }


}
