import org.junit.Before;
import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;


public class ClientTest {

    private Client clientUnderTest;

    @Before
    public void setUp() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", null, null);
        clientUnderTest = new Client("firstName", "lastName", "email", "phoneNumber", 0);
    }
    @After
    public void tearDown() {
        try(Connection con = DB.sql2o.open()) {
            String deleteStylistsQuery = "DELETE FROM clients *;";
            con.createQuery(deleteStylistsQuery).executeUpdate();
        }
    }
    @Test
    public void Client_instantiatesCorrectly_true() {
      assertEquals(true, clientUnderTest instanceof Client);
    }
    @Test
    public void all_returnsAllInstancesOfClient_true() {
      Client secondClient = new Client("firstName", "lastName", "email", "phoneNumber", 0);
      clientUnderTest.save();
      secondClient.save();
      assertEquals(true, Client.all().get(0).equals(clientUnderTest));
      assertEquals(true, Client.all().get(1).equals(secondClient));
    }
    public void update_updatesClientDescription_true() {
      clientUnderTest.save();
      clientUnderTest.update("23432343", "email@22.com");
      assertEquals("email@22.com", Client.find(clientUnderTest.getId()).getEmail());
    }


  }
