import org.junit.Before;
import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.List;
import java.util.Arrays;


public class StylistTest {

    private Stylist stylistUnderTest;

    @Before
    public void setUp() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", null, null);
        stylistUnderTest = new Stylist("name", 0, "idNumber", "phoneNumber", "email", 0);
    }
    @After
    public void tearDown() {
        try(Connection con = DB.sql2o.open()) {
            String deleteStylistsQuery = "DELETE FROM stylists *;";
            con.createQuery(deleteStylistsQuery).executeUpdate();
        }
    }
    @Test
     public void Stylist_instantiatesCorrectly_true() {
        assertEquals(true, stylistUnderTest instanceof Stylist);
    }
    @Test
    public void getName_StylistInstantiatesWithName_name() {
        assertEquals("name", stylistUnderTest.getName());
    }
    @Test
    public void getEmail_StylistInstantiatesWithName_name() {
        assertEquals("email", stylistUnderTest.getEmail());
    }
    @Test
    public void all_returnsAllInstancesOfStylist_true() {
      stylistUnderTest.save();
      Stylist secondStylist = new Stylist("name", 0, "idNumber", "phoneNumber", "email", 0);
      secondStylist.save();
      assertEquals(true, Stylist.all().get(1).equals(secondStylist));
      assertEquals(true, Stylist.all().get(0).equals(stylistUnderTest));
    }
    @Test
    public void find_returnsStylistWithSameId_secondStylist() {
      stylistUnderTest.save();
      Stylist secondStylist = new Stylist("name", 0, "idNumber", "phoneNumber", "email", 0);
      secondStylist.save();
      assertEquals(Stylist.find(secondStylist.getId()), secondStylist);
    }
    @Test
    public void save_assignsIdToObject() {
      stylistUnderTest.save();
      Stylist savedStylist = Stylist.all().get(0);
      assertEquals(stylistUnderTest.getId(), savedStylist.getId());
    }

    @Test
    public void getClients_retrievesALlClientsFromDatabase_clientsList() {
      stylistUnderTest.save();
      Client clientUnderTest1 = new Client("firstName", "lastName", "email", "phoneNumber", stylistUnderTest.getId());
      clientUnderTest1.save();
      Client clientUnderTest2 = new Client("firstName", "lastName", "email", "phoneNumber", stylistUnderTest.getId());
      clientUnderTest2.save();
      Client[] clients = new Client[] { clientUnderTest1, clientUnderTest2 };
      assertTrue(stylistUnderTest.getClients().containsAll(Arrays.asList(clients)));
    }




}
