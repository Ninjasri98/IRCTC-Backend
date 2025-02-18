package org.example.services;

import java.io.File;
import java.io.IOException;

import java.util.List;
import java.util.Optional;

import org.example.entities.Train;
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
        loadUsers();
    }
    public UserBookingService() throws IOException{
        loadUsers();
    }
    public List<User> loadUsers() throws IOException{
        File users = new File(USERS_PATH);
        return userList = objectMapper.readValue(users, new TypeReference<List<User>>() {});
    }
    public boolean loginUser(){
        Optional<User> foundUser = userList.stream().filter(user1 -> {
            return user1.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashedPassword());
        }).findFirst();
        return foundUser.isPresent();
    }
    public Boolean signup(User user1){
        try {
            userList.add(user1);
            saveUserListToFile();
            return Boolean.TRUE;
        } catch (IOException exp) {
            return Boolean.FALSE;
        }
    }
     public void saveUserListToFile() throws IOException{
        File usersFile = new File(USERS_PATH);
        objectMapper.writeValue(usersFile, userList);
     }
     public void fetchBooking(){
        user.printTickets();
     }
    public boolean cancelBooking(String ticketId){

    }
    public List<Train> getTrains(String source, String destination){
        try{
            TrainService trainService = new TrainService();
            return trainService.searchTrains(source,destination);
        } catch(IOException exp){
            return null;
        }
    }
}
