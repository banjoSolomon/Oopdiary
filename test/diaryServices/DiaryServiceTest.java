package diaryServices;

import dtos.requests.*;
import exceptions.IncorrectPasswordException;
import exceptions.UserAlreadyExistsException;
import model.Entry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.DiaryServiceImpl;
import services.EntryService;
import services.EntryServiceImp;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DiaryServiceTest {
    private DiaryServiceImpl diaryServices;
    private RegisterRequest request;
    private LoginRequest loginRequest;
    private EntryService entryService;
    private CreateEntryRequest entryRequest;
    private UpdateEntryRequest updateEntryRequest;

    @BeforeEach
    public void setUp() {
        diaryServices = new DiaryServiceImpl();
        request = new RegisterRequest();
        request.setUserName("solomon");
        request.setPassword("123456");

        loginRequest = new LoginRequest();
        loginRequest.setUserName("solomon");
        loginRequest.setPassword("123456");
        entryService = new EntryServiceImp();

        entryRequest = new CreateEntryRequest();
        entryRequest.setTitle("Hello World");
        entryRequest.setBody("Love");
        entryRequest.setAuthor("solomon");
        updateEntryRequest = new UpdateEntryRequest();


    }

    @Test
    public void testDiaryServiceCanRegister() {
        diaryServices = new DiaryServiceImpl();
        request = new RegisterRequest();
        request.setUserName("Banjo Solomon");
        request.setPassword("123456");
        diaryServices.register(request);
        assertEquals(1L, diaryServices.getNumberOfUsers());

    }

    @Test
    public void testDiaryServiceCanRegisterTwice() {
        diaryServices = new DiaryServiceImpl();
        request = new RegisterRequest();
        request.setUserName("Banjo Solomon");
        request.setPassword("123456");
        diaryServices.register(request);

        RegisterRequest request2 = new RegisterRequest();
        request2.setUserName("Sam Queen");
        request2.setPassword("1111");
        diaryServices.register(request2);
        assertEquals(2L, diaryServices.getNumberOfUsers());

    }

    @Test
    public void testDiaryService_CannotRegisterTwice() {
        diaryServices = new DiaryServiceImpl();
        request = new RegisterRequest();
        request.setUserName("Banjo Solomon");
        request.setPassword("123456");
        diaryServices.register(request);
        assertThrows(UserAlreadyExistsException.class, () -> diaryServices.register(request));

    }

    @Test
    public void testDiaryRegisterUserWithEmpty_String_ThrowIllegalArgumentException() {
        request.setUserName(" ");
        request.setPassword(" ");
        assertThrows(IllegalArgumentException.class, () -> diaryServices.register(request));
        assertEquals(0L, diaryServices.getNumberOfUsers());


    }

    @Test
    public void testDiaryUserCanLogInIntoAccount() {
        request.setUserName("Solomon");
        request.setPassword("123456");
        diaryServices.register(request);
        assertTrue(diaryServices.findDiaryBy("Solomon").isLocked());
        loginRequest.setUserName("Solomon");
        loginRequest.setPassword("123456");

        diaryServices.login(loginRequest);
        assertFalse(diaryServices.findDiaryBy("Solomon").isLocked());
    }

    @Test
    public void testDiaryUserCanLogOutOFAccount() {
        request.setUserName("Solomon");
        request.setPassword("123456");
        diaryServices.register(request);
        diaryServices.logout("Solomon");
        assertTrue(diaryServices.findDiaryBy("Solomon").isLocked());

    }

    @Test
    public void testDiaryUserLoginWithIncorrectPassword() {
        request.setUserName("Solomon");
        request.setPassword("123456");
        diaryServices.register(request);
        loginRequest.setPassword("1111");
        assertThrows(IncorrectPasswordException.class, () -> diaryServices.login(loginRequest));
    }

    @Test
    public void testDiaryUserCanDeleteAccount() {
        request.setUserName("Solomon");
        request.setPassword("123456");
        diaryServices.register(request);

        RemoveUserRequest removeUserRequest = new RemoveUserRequest();
        removeUserRequest.setUsername("Solomon");
        removeUserRequest.setPassword("123456");

        diaryServices.login(loginRequest);
        diaryServices.removeUser(removeUserRequest);
        assertEquals(0L, diaryServices.getNumberOfUsers());
    }

    @Test
    public void testDiaryUserDeleteWithIncorrectPassword_IncorrectPasswordException() {
        request.setUserName("Solomon");
        request.setPassword("123456");
        diaryServices.register(request);
        RemoveUserRequest removeUserRequest = new RemoveUserRequest();
        removeUserRequest.setUserName("Solomon");
        removeUserRequest.setPassword("1234567");
        diaryServices.login(loginRequest);
        assertThrows(IncorrectPasswordException.class, () -> diaryServices.removeUser(removeUserRequest));
    }

    @Test
    public void testAddNumberOfEntry_NumbersOfEntry() {
        entryRequest.setAuthor("Solomon");
        entryRequest.setTitle("Sample Entry Title");
        entryRequest.setBody("Sample Entry Body");

        request.setUserName("Solomon");
        request.setPassword("123456");
        diaryServices.register(request);
        diaryServices.login(loginRequest);
        entryRequest.setTitle("Hello World");
        entryRequest.setBody("Love");
        entryRequest.setAuthor("solomon");
        diaryServices.createEntryWith(entryRequest);
        assertEquals(1, entryService.getEntries("Solomon").size());
        List<Entry> entries = entryService.getEntries("Solomon");
        assertEquals(1, entries.size());
    }

    @Test
    public void testToUpdateEntry_NumberOfEntries() {
        request.setUserName("Solomon");
        request.setPassword("123456");
        diaryServices.register(request);
        diaryServices.login(loginRequest);

        entryRequest.setTitle("Hello World");
        entryRequest.setBody("Love");
        entryRequest.setAuthor("solomon");
        diaryServices.createEntryWith(entryRequest);

        assertEquals("Hello World", entryService.getEntries("Solomon").getFirst().getTitle());
        assertEquals(1, entryService.getEntries("Solomon").size());

        updateEntryRequest = new UpdateEntryRequest();
        updateEntryRequest.setTitle("My Story");
        updateEntryRequest.setBody("New Life");
        updateEntryRequest.setAuthor("Solomon");
        updateEntryRequest.setId(1);
        diaryServices.updateEntryWith(updateEntryRequest);
        assertEquals("My Story", diaryServices.getEntriesFor("Solomon").getFirst().getTitle());
        assertEquals(1, diaryServices.getEntriesFor("Solomon").size());

    }

    @Test
    public void testDiaryHasTwoEntryDeleteOneRemainOne() {
        request.setUserName("Solomon");
        request.setPassword("123456");
        diaryServices.register(request);
        diaryServices.login(loginRequest);
        diaryServices.createEntryWith(entryRequest);

        request.setUserName("Kent");
        request.setPassword("1234");
        diaryServices.register(request);
        diaryServices.login(loginRequest);
        diaryServices.createEntryWith(entryRequest);

        assertEquals(2, diaryServices.getEntriesFor("Solomon").size());
        diaryServices.deleteEntry(1, "Solomon");
        assertEquals(1, diaryServices.getNumberOfUsers());
    }

}








