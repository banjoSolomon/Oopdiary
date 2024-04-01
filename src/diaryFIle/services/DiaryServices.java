package diaryFIle.services;

import diaryFIle.requests.LoginRequest;
import diaryFIle.requests.RegisterRequest;
import diaryFIle.requests.RemoveUserRequest;
import diaryFIle.requests.UpdateEntryRequest;
import diaryFIle.model.Entry;

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
    Entry getEntry(int id, String username);

}


