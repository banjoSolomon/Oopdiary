package diaryFIle.services;

import diaryFIle.requests.*;
import diaryFIle.exceptions.IncorrectPasswordException;
import diaryFIle.exceptions.UserNotFoundException;
import diaryFIle.exceptions.UserAlreadyExistsException;
import diaryFIle.model.Diary;
import diaryFIle.model.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import diaryFIle.repositories.DiaryRepository;
import java.util.List;
import java.util.Optional;

@Service
public class DiaryServiceImpl implements DiaryServices {

    @Autowired
    private DiaryRepository diaryRepository;
    @Autowired
    private EntryService entryService;


    @Override
    public void register(RegisterRequest request) {
        if (request.getUsername().isBlank() || request.getPassword().isBlank()) {
            throw new IllegalArgumentException("Username or password cannot be empty");
        }
        validateUser(request.getUsername());
        Diary myDiary = new Diary();
        myDiary.setUserName(request.getUsername());
        myDiary.setPassword(request.getPassword());
        diaryRepository.save(myDiary);
    }

    @Override
    public void login(LoginRequest request) {
        Diary foundDiary;
        try {
            foundDiary = findDiaryBy(request.getUserName().toLowerCase());
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException("User not found");
        }

        if (isPasswordIncorrect(foundDiary, request.getPassword())) {
            throw new IncorrectPasswordException("Incorrect Password");
        }
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
        if (isPasswordIncorrect(foundDiary, request.getPassword())) {
            throw new IncorrectPasswordException("Incorrect password");
        }
        diaryRepository.delete(foundDiary);
    }

    private boolean isPasswordIncorrect(Diary foundDiary, String password) {
        return !foundDiary.getPassword().equals(password);
    }

    private void validateUser(String username) {
        Optional<Diary> diary = diaryRepository.findById(username);
        if (diary.isPresent()) {
            throw new UserAlreadyExistsException(String.format("%s already exists as a username, kindly reselect", username));
        }
//        //if (diary.isPresent()) {
//            if (diary.get().getUsername().equals(username)) {
//            throw new UserAlreadyExistsException(String.format("%s already exists as a username, kindly reselect", username));
//        }
//        Optional<Diary> diary = diaryRepository.findById(username);
//        if
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

    @Override
    public Entry getEntry(int id, String username) {
        Diary foundDiary = findDiaryBy(username.toLowerCase());
        checkLockState(foundDiary);
        return entryService.getEntry(id);
    }

    public Diary findDiaryBy(String username) {
        Optional<Diary> foundDiaryOptional = diaryRepository.findById(username.toLowerCase());
        Diary foundDiary = foundDiaryOptional.orElseThrow(() -> new UserNotFoundException("User not found"));
        return foundDiary;
    }

    public void createEntryWith(CreateEntryRequest request) {
        Optional<Diary> foundDiaryOptional = Optional.ofNullable(findDiaryBy(request.getAuthor().toLowerCase()));
        Diary foundDiary = foundDiaryOptional.orElseThrow(() -> new UserNotFoundException("User not found"));
        checkLockState(foundDiary);
        Entry entry = new Entry();
        entry.setTitle(request.getTitle());
        entry.setBody(request.getBody());
        entry.setAuthor(request.getAuthor().toLowerCase());
        entryService.save(entry);
    }

    private void checkLockState(Diary diary) {
        if (diary.isLocked()) {
            throw new IllegalArgumentException("You have to login.");
        }
    }
}