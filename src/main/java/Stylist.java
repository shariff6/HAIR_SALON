public class Stylist {
    private String name;
    private int age;
    private String idNumber;
    private String phoneNumber;
    private String email;
    private int clientCount;
    private int id;

    public Stylist(String name, int age, String idNumber, String phoneNumber, String email, int clientCount, int id) {
        this.setName(name);
        this.setAge(age);
        this.setIdNumber(idNumber);
        this.setPhoneNumber(phoneNumber);
        this.setEmail(email);
        this.setClientCount(clientCount);
        this.setId(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getClientCount() {
        return clientCount;
    }

    public void setClientCount(int clientCount) {
        this.clientCount = clientCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }





}
