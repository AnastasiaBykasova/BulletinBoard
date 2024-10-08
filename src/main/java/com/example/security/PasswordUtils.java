package com.example.security;

import com.example.models.User;
import com.example.repositories.UserRepository;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.Optional;

@Service
public class PasswordUtils {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
    private static final int SALT_SIZE = 16;
    private static final int ITERATION_COUNT = 65536;
    private static final int KEY_LENGTH = 128; // 128 bits
    private final UserRepository userRepository;

    public PasswordUtils(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        StringBuilder salt = new StringBuilder(SALT_SIZE);
        for (int i = 0; i < SALT_SIZE; i++) {
            salt.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return salt.toString();
    }

    public static String generateHash(String passwordSalt) {
        try {
            String[] parts = passwordSalt.split(":");
            if (parts.length != 2) {
                throw new IllegalArgumentException("Invalid format: expected 'password:salt'");
            }

            String password = parts[0];
            String salt = parts[1];

            PBEKeySpec spec = new PBEKeySpec(
                    password.toCharArray(),
                    salt.getBytes(),
                    ITERATION_COUNT,
                    KEY_LENGTH
            );

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = factory.generateSecret(spec).getEncoded();

            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Error generating hash", e);
        }
    }

    public long checkEmail(String enteredEmail) {
        Optional<User> optionalUser = userRepository.findByEmail(enteredEmail);

        if (optionalUser.isPresent()) {
            return optionalUser.get().getId();
        }
        else {
            return -1;
        }

    }

    public long checkPassword(long id, String enteredPassword) {
        Optional<User> optionalUser = userRepository.findById(id);
        String passHashDB = optionalUser.get().getPassHash();
        String salt = optionalUser.get().getSalt();

        String passHash = generateHash(enteredPassword + ":" + salt);
        if (passHash.equals(passHashDB)) {
            return id;
        }
        else {
            return -1;
        }
    }

}
