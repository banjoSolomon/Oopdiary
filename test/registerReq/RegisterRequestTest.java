package registerReq;

import diaryFIle.requests.RegisterRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterRequestTest {
    private RegisterRequest registerR;

    @BeforeEach
    public void setUp() {
        registerR = new RegisterRequest();
    }

    @Test
    public void testRegisterRequestHasAUserName() {
        registerR = new RegisterRequest();
        String expectedUserName = "banjo solomon";
        registerR.setUserName(expectedUserName);
        assertEquals(expectedUserName.toLowerCase(), registerR.getUserName());

    }

    @Test
    public void testRegisterRequestHasAPassword() {
        registerR = new RegisterRequest();
        String password = "Solomon123";
        registerR.setPassword(password);
        assertEquals(password, registerR.getPassword());

    }
}
