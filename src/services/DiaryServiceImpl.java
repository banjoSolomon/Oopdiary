package services;

import dtos.requests.*;
import exceptions.IncorrectPasswordException;
import exceptions.UserNotFoundException;
import exceptions.UserAlreadyExistsException;
import model.Diary;
import model.Entry;
import repositories.DiaryRepository;
import repositories.DiaryRepositoryImpl;

import java.util.List;

public class DiaryServiceImpl implements DiaryServices {
    private final DiaryRepository diaryRepository = new DiaryRepositoryImpl();
    private static final EntryService entryService = new EntryServiceImp();


    @Override
    public void register(RegisterRequest request) {
        if (request.getUserName().isBlank() || request.getPassword().isBlank()) {
            throw new IllegalArgumentException("Username or password cannot be empty");
        }
        validateUser(request.getUserName());
        Diary myDiary = new Diary();

        myDiary.setUserName(request.getUserName());
        myDiary.setPassword(request.getPassword());
        diaryRepository.save(myDiary);


    }

    @Override
    public void login(LoginRequest request) {
        Diary foundDiary = findDiaryBy(request.getUserName().toLowerCase());
        if (isPasswordIncorrect(foundDiary, request.getPassword()))
            throw new IncorrectPasswordException("Incorrect Password");
        foundDiary.setLocked(false);

    }

    @Override
    public void logout(String username) {
        Diary foundDiary = findDiaryBy(username.toLowerCase());
        foundDiary.setLocked(true);
        diaryRepository.save(foundDiary);

    }

    @Override
    public void removeUser(RemoveUserRequest request) {
        Diary foundDiary = findDiaryBy(request.getUsername().toLowerCase());
        if (isPasswordIncorrect(foundDiary, request.getPassword()))
            throw new IncorrectPasswordException("Incorrect password");
        diaryRepository.delete(foundDiary);

    }


    private static boolean isPasswordIncorrect(Diary foundDiary, String password) {
        return !foundDiary.getPassword().equals(password);
    }


    private void validateUser(String username) {
        var diary = diaryRepository.findById(username);
        if (diary != null)
            throw new UserAlreadyExistsException(String.format("%s already exists as a username, kindly reselect", username));
    }


    @Override
    public long getNumberOfUsers() {
        return diaryRepository.count();
    }

    @Override
    public void updateEntryWith(UpdateEntryRequest request) {
        Diary foundDiary = findDiaryBy(request.getAuthor().toLowerCase());
        checkLockState(foundDiary);

        Entry entry = new Entry();
        entry.setTitle(request.getTitle());
        entry.setBody(request.getBody());
        entry.setAuthor(request.getAuthor().toLowerCase());
        entry.setId(request.getId());

        entryService.save(entry);
    }

    @Override
    public void deleteEntry(int id, String username) {
        Diary foundDiary = findDiaryBy(username.toLowerCase());
        checkLockState(foundDiary);

        entryService.deleteEntry(id);

    }

    @Override
    public List<Entry> getEntriesFor(String username) {
        Diary foundDiary = findDiaryBy(username.toLowerCase());
        checkLockState(foundDiary);

        return entryService.getEntries(username);
    }


    public Diary findDiaryBy(String username) {
        Diary foundDiary = diaryRepository.findById(username.toLowerCase());
        if (foundDiary == null) throw new UserNotFoundException("User not found");
        return foundDiary;
    }

    public void createEntryWith(CreateEntryRequest request) {
        Diary foundDiary = findDiaryBy(request.getAuthor().toLowerCase());
        checkLockState(foundDiary);
        Entry entry = new Entry();
        entry.setTitle(request.getTitle());
        entry.setBody(request.getBody());
        entry.setAuthor(request.getAuthor().toLowerCase());
        entryService.save(entry);
    }

    private void checkLockState(Diary diary) {
        if (diary.isLocked()) throw new IllegalArgumentException("U have to login.");
    }
}
