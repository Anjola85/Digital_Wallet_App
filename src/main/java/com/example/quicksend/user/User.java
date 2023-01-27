package com.example.quicksend.user;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity(name="users")
@Table(uniqueConstraints = { @UniqueConstraint(name = "UniqueEmailAndPhone", columnNames = { "phoneNumber", "email" }) })
public class User {
    @Id
    @SequenceGenerator(name="user_id_sequence", sequenceName = "user_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_sequence")
    private Long id;

    @Column
    private Integer age;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String password;
    @Column
    private String phoneNumber;
    @Column
    private String address;
    @Column
    private String city;
    @Column
    private String country;
    @Column
    private String postalCode;
    @Column
    private String email;
    @Column
    private LocalDate dob;

    public User() {}

    public User(Long id, String firstName, String lastName, String email,
                String password, String phoneNumber, String address, String city,
                String country, String postalCode, LocalDate dob) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;
        this.dob = dob;
        setAge();
    }

    public User(String firstName, String lastName, String email, String password,
                String phoneNumber, String address, String city, String country, String postalCode, LocalDate dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;
        this.dob = dob;
        setAge();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public LocalDate getDob() {
        return dob;
    }

    public User setDob(LocalDate dob) {
        this.dob = dob;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public User setAge() {
        this.age = LocalDate.now().getYear() - this.dob.getYear();
        return this;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(email, user.email) && Objects.equals(phoneNumber, user.phoneNumber) && Objects.equals(address, user.address) && Objects.equals(city, user.city) && Objects.equals(country, user.country) && Objects.equals(postalCode, user.postalCode) && Objects.equals(dob, user.dob);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, phoneNumber, address, city, country, postalCode, dob);
    }

    @Override
    public String toString() {
        return "user{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", dob=" + dob +
                '}';
    }
}
