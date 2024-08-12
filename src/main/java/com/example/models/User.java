package com.example.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
//import org.springframework.data.annotation.Id;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "email", unique = true, nullable = false)
    @Email
    private String email;
    @Column(name = "status")
    private String status;
    @Column(name = "passhash")
    private String passHash;
    @Column(name = "salt")
    private String salt;

    public User() {
    }

    // конструктор для регистрации
    public User(String firstName, String lastName, String email, String status, String passHash, String salt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.status = status;
//        this.passHash = hashPassword(passHash, this.salt);
        this.passHash = passHash;
        this.salt = salt;
    }

    // конструктор с полным набором параметров
    public User(Long id, String firstName, String lastName, String email, String status, String passHash, String salt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.status = status;
        this.passHash = passHash;
        this.salt = salt;
    }

    //getters
    public Long getId() {
        return id;
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
    public String getStatus() {
        return status;
    }
    public String getPassHash() {
        return passHash;
    }
    public String getSalt() {
        return salt;
    }

    //setters
    public void setId(Long id) {
        this.id = id;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setPassHash(String passHash) {
        this.passHash = passHash;
    }
    public void setSalt(String salt) {
        this.salt = salt;
    }

    // Метод для проверки пароля
    public boolean checkPassword(String password) {
        return passHash.equals(hashPassword(password, salt));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id != null ? id.equals(user.id) : user.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Entities.User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", status='" + status + '\'' +
                ", passHash=" + passHash +
                ", salt=" + salt +
                '}';
    }

    // Метод для хэширования пароля с солью
    private String hashPassword(String password, String salt) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] saltedPassword = (salt + password).getBytes("UTF-8");
            byte[] hash = digest.digest(saltedPassword);
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    //salt generation
    private String generateSalt() {
        byte[] salt = new byte[16]; //salt size = 16 bytes
        new Random().nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    // Метод для преобразования массива байтов в строку в шестнадцатеричном формате
//    private String bytesToHex(byte[] hash) {
//        StringBuilder hexString = new StringBuilder();
//        for (byte b : hash) {
//            String hex = Integer.toHexString(0xff & b);
//            if (hex.length() == 1) hexString.append('0');
//            hexString.append(hex);
//        }
//        return hexString.toString();
//    }
}
