package services;

import dtos.requests.LoginRequest;
import dtos.requests.RegisterRequest;
import dtos.requests.RemoveUserRequest;
import dtos.requests.UpdateEntryRequest;
import model.Entry;

import java.util.List;

public interface DiaryServices {
    void register(RegisterRequest request);

    void login(LoginRequest request);

    void logout(String username);

    void removeUser(RemoveUserRequest request);

    long getNumberOfUsers();

    void updateEntryWith(UpdateEntryRequest request);

    void deleteEntry(int id, String username);

    List<Entry> getEntriesFor(String username);

}


