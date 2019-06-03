import org.sql2o.*;
import java.util.List;

public class Stylist {
    private String name;
    private int age;
    private String idNumber;
    private String phoneNumber;
    private String email;
    private int clientCount;
    private int id;

    public Stylist(String name, int age, String idNumber, String phoneNumber, String email, int clientCount) {
        this.name = name;
        this.age = age;
        this.idNumber = idNumber;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.clientCount = clientCount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setClientCount(int clientCount) {
        this.clientCount = clientCount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public int getClientCount() {
        return clientCount;
    }

    public int getId() {
        return id;
    }

    public static List<Stylist> all() {
      String sql = "SELECT id, name FROM stylists";
      try(Connection con = DB.sql2o.open()) {
        return con.createQuery(sql).executeAndFetch(Stylist.class);
      }
    }
    @Override
    public boolean equals(Object otherStylist) {
      if (!(otherStylist instanceof Stylist)) {
        return false;
      } else {
        Stylist newStylist = (Stylist) otherStylist;
        return this.getName().equals(newStylist.getName()) && this.getId() == newStylist.getId();
      }
    }

    public void save() {
      try(Connection con = DB.sql2o.open()) {
        String sql = "INSERT INTO stylists(name, age, idNumber, phoneNumber, email, clientCount) VALUES (:name, :age, :idNumber, :phoneNumber, :email, :clientCount)";
        this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("age", this.age)
        .addParameter("phoneNumber", this.phoneNumber)
        .addParameter("idNumber", this.idNumber)
        .addParameter("email", this.email)
        .addParameter("clientCount", this.clientCount)
        .executeUpdate()
        .getKey();
      }
    }
    public static Stylist find(int id) {
        try(Connection con = DB.sql2o.open()) {
          String sql = "SELECT * FROM stylists where id=:id";
          Stylist stylist = con.createQuery(sql)
            .addParameter("id", id)
            .executeAndFetchFirst(Stylist.class);
          return stylist;
        }
      }
    public List<Client> getClients() {
      try(Connection con = DB.sql2o.open()) {
        String sql = "SELECT * FROM clients where stylistId=:id";
        return con.createQuery(sql)
        .addParameter("id", this.id)
        .executeAndFetch(Client.class);
        }
      }
    public void delete() {
      try(Connection con = DB.sql2o.open()) {
        String sql = "DELETE FROM stylists WHERE id = :id;";
        con.createQuery(sql)
          .addParameter("id", id)
          .executeUpdate();
        }
      }



}
