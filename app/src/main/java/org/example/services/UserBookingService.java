package org.example.services;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.example.entities.User;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserBookingService {
    private User user;
    private static final String USERS_PATH = "../localDb/users.json";
    private ObjectMapper objectMapper = new ObjectMapper();
    private List<User> userList;
    public UserBookingService(User user) throws IOException{
        this.user = user;
        File users = new File(USERS_PATH);
        userList = objectMapper.readValue(users, new TypeReference<List<User>>() {});
    }
}
