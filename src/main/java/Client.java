import java.util.List;
import org.sql2o.*;


public class Client {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private int id;
    private int stylistId;

    public Client(String firstName, String lastName, String email, String phoneNumber, int stylistId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.stylistId = stylistId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getId() {
        return id;
    }

    public int getStylistId() {
        return stylistId;
    }

    public static List<Client> all() {
      String sql = "SELECT id, firstName, stylistId FROM clients";
      try(Connection con = DB.sql2o.open()) {
        return con.createQuery(sql).executeAndFetch(Client.class);
      }
    }
    @Override
    public boolean equals(Object otherClient){
      if (!(otherClient instanceof Client)) {
        return false;
      } else {
        Client newClient = (Client) otherClient;
        return this.getFirstName().equals(newClient.getFirstName()) && this.getId() == newClient.getId();

      }
    }

    public void save() {
      try(Connection con = DB.sql2o.open()) {
        String sql = "INSERT INTO clients(firstName, lastName, email, phoneNumber, stylistId) VALUES (:firstName, :lastName, :email, :phoneNumber, :stylistId)";
        this.id = (int) con.createQuery(sql, true)
          .addParameter("firstName", this.firstName)
          .addParameter("lastName", this.lastName)
          .addParameter("email", this.email)
          .addParameter("phoneNumber", this.phoneNumber)
          .addParameter("stylistId", this.stylistId)
          .executeUpdate()
          .getKey();
      }
    }
    public static Client find(int id) {
      try(Connection con = DB.sql2o.open()) {
        String sql = "SELECT * FROM clients where id=:id";
        Client client = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Client.class);
        return client;
      }
    }

    public void update(String phoneNumber, String email, int stylistId) {
      try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE clients SET email = :email, phoneNumber = :phoneNumber, stylistId = :stylistId WHERE id = :id";
      con.createQuery(sql)
        .addParameter("phoneNumber", phoneNumber)
        .addParameter("email", email)
        .addParameter("stylistId", stylistId)
        .addParameter("id", id)
        .executeUpdate();
      }
    }
}
