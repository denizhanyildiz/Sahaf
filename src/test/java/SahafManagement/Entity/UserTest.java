package SahafManagement.Entity;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {

    @InjectMocks
    private User user;
    @Mock
    private User mockUser;
    @Mock
    private Book mockBook;

    @Before
    public void setUp() {
        mockUser = new User();
        mockUser.setUserName("User 1");
        mockUser.setUserPassword("12345");
        mockUser.setUserRole("ROLE_ADMIN");
        mockUser.setUsersBook(Collections.singletonList(mockBook));

    }

    @Test
    public void testUserId() {
        Long id = 1L;
        user.setUserId(id);
        assertEquals(id, user.getUserId());
    }

    @Test
    public void testUserName() {
        String name = "User 1";
        user.setUserName(name);
        assertEquals(name, user.getUserName());
    }

    @Test
    public void testUserPassword() {
        String password = "12345";
        user.setUserPassword(password);
        assertEquals(password, user.getUserPassword());
    }

    @Test
    public void testUserRole() {
        String role = "ROLE_ADMIN";
        user.setUserRole(role);
        assertEquals(role, user.getUserRole());
    }

    @Test
    public void testUsersBook() {
        Book book = new Book();
        List<Book> books = new ArrayList<>();
        books.add(book);
        user.setUsersBook(books);
        assertNotNull(user.getUsersBook());
        assertEquals(1, user.getUsersBook().size());
        assertEquals(book, user.getUsersBook().get(0));
    }

}
