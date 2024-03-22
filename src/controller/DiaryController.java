package controller;

import dtos.requests.LoginRequest;
import dtos.requests.RegisterRequest;
import exceptions.DiaryAppException;
import services.DiaryServiceImpl;
import services.DiaryServices;

public class DiaryController {
    private static final DiaryServices diaryServices = new DiaryServiceImpl();

    public static String registerUser(RegisterRequest request) {
        try {
            diaryServices.register(request);
            return "registration successful";
        } catch (DiaryAppException e) {
            return e.getMessage();
        }

    }

    public static String login(LoginRequest request) {
        try {
            diaryServices.login(request);
            return "login successful";
        } catch (DiaryAppException e) {
            return e.getMessage();
        }
    }

    public static String logout(String username) {
        try {
            diaryServices.logout(username);
            return "logout successful";
        } catch (DiaryAppException e) {
            return e.getMessage();
        }
    }


}
