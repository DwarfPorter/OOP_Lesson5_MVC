package personal.model;

import com.sun.org.apache.bcel.internal.generic.GOTO;

import java.util.concurrent.atomic.AtomicInteger;

public class User {
    private static final AtomicInteger GUID = new AtomicInteger(0);
    private Integer id;
    private String firstName;
    private String lastName;
    private String phone;

    public User(String firstName, String lastName, String phone) {
        this.id = GUID.incrementAndGet();
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public User(String id, String firstName, String lastName, String phone) {
        this(firstName, lastName, phone);
        this.id = Integer.parseInt(id);
    }

    public int getIntId() {
        return id;
    }
    public String getId() {
        return id.toString();
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setId(String id) {
        this.id = Integer.parseInt(id);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return String.format("Идентафикатор: %s\nИмя: %s,\nФамилия: %s,\nТелефон: %s", id, firstName, lastName, phone);
    }
}
