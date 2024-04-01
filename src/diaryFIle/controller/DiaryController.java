package diaryFIle.controller;

import diaryFIle.requests.LoginRequest;
import diaryFIle.requests.RegisterRequest;
import diaryFIle.requests.UpdateEntryRequest;
import diaryFIle.exceptions.DiaryAppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import diaryFIle.services.DiaryServices;


import java.util.List;

@RestController
public class DiaryController {
    @Autowired
    private DiaryServices diaryServices;

    @PostMapping("/register")
    public String registerUser(@RequestBody RegisterRequest request) {
        try {
            diaryServices.register(request);
            return "registration successful";
        } catch (Exception e) {
            return e.getMessage();
        }

    }

    @PatchMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        try {
            diaryServices.login(request);
            return "login successful";
        } catch (DiaryAppException e) {
            return e.getMessage();
        }
    }

    @PatchMapping("/logout/{name}")
    public String logout(@PathVariable("name") String username) {
        try {
            diaryServices.logout(username);
            return "logout successful";
        } catch (DiaryAppException e) {
            return e.getMessage();
        }
    }

    @PostMapping("createEntry")
    public String createEntry(@RequestBody RegisterRequest registerRequest) {
        try {
            diaryServices.register(registerRequest);
            return "created successfully";
        } catch (DiaryAppException e) {
            return e.getMessage();
        }
    }

    @PatchMapping("updateEntry")

    public String updateEntry(@RequestBody UpdateEntryRequest updateEntryRequest) {
        try {
            diaryServices.updateEntryWith(updateEntryRequest);
            return "updated successfully";
        } catch (DiaryAppException e) {
            return e.getMessage();
        }
    }

    @DeleteMapping("deleteEntry/{entryId}")
    public String deleteEntryBy(@PathVariable("entryId") String id, @RequestParam(name = "username", defaultValue = "") String username) {
        try {
            diaryServices.deleteEntry(Integer.parseInt(id), username);
        } catch (DiaryAppException e) {
            return e.getMessage();

        }
        return null;
    }

    @GetMapping("getEntry/{entryId}")
    public String getEntryBy(@PathVariable("entryId") String id, @RequestParam(name = "username", defaultValue = "") String username) {
        try {
            return String.valueOf(diaryServices.getEntry(Integer.parseInt(id), username));
        } catch (DiaryAppException e) {
            return e.getMessage();
        }
    }

    @GetMapping("/getEntriesFor/{author}")
    public List<?> getEntries(@PathVariable("author") String username) {
        try {
            return diaryServices.getEntriesFor(username);
        } catch (DiaryAppException e) {
            return List.of(e.getMessage());
        }
    }

}
