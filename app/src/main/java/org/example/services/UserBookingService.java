package org.example.services;

import java.io.File;
import java.io.IOException;
import java.lang.classfile.ClassFile.Option;
import java.util.List;
import java.util.Optional;

import org.example.entities.User;
import org.example.util.UserServiceUtil;

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
    public boolean loginUser(){
        Optional<User> foundUser = userList.stream().filter(user1 -> {
            return user1.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashedPassword());
        }).findFirst();
        return foundUser.isPresent();
    }
}
