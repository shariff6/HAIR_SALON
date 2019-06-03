import org.sql2o.*;

public class DB {
  String connectionString = "jdbc:  postgres://mjgdptdetrrkcp:c373c5980ac84aa497cbe6ab43f3ca2d2f6503e9f366f528db9f61de7c07b39d@ec2-107-20-155-148.compute-1.amazonaws.com:5432/db32urhq9pdd4d"; //!
    Sql2o sql2o = new Sql2o(connectionString, "mjgdptdetrrkcp", "c373c5980ac84aa497cbe6ab43f3ca2d2f6503e9f366f528db9f61de7c07b39d"); //!

}
