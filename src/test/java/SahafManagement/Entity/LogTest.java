package SahafManagement.Entity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class LogTest {

    private Log log;

    @Before
    public void setUp() {
        log = new Log();
        log.setRequestUrl("http://example.com/test");
        log.setRequestMethod("GET");
        log.setResponseStatus(200);
        log.setResponseBody("Test Response Body 1");
        log.setCreatedAt(new Date());
    }

    @Test
    public void testGetId() {
        Long expectedId = 1L;
        log.setId(expectedId);
        Long actualId = log.getId();
        assertEquals(expectedId, actualId);
    }

    @Test
    public void testGetRequestUrl() {
        String expectedUrl = "http://example.com/test";
        String actualUrl = log.getRequestUrl();
        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void testGetRequestMethod() {
        String expectedMethod = "GET";
        String actualMethod = log.getRequestMethod();
        assertEquals(expectedMethod, actualMethod);
    }

    @Test
    public void testGetResponseStatus() {
        int expectedStatus = 200;
        int actualStatus = log.getResponseStatus();
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    public void testGetResponseBody() {
        String expectedBody = "Test Response Body 1";
        String actualBody = log.getResponseBody();
        assertEquals(expectedBody, actualBody);
    }

    @Test
    public void testGetCreatedAt() {
        Date expectedDate = new Date();
        log.setCreatedAt(expectedDate);
        Date actualDate = log.getCreatedAt();
        assertEquals(expectedDate, actualDate);
    }
}
