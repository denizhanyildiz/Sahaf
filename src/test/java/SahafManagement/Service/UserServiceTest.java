package SahafManagement.Service;

import static org.mockito.Mockito.*;

import SahafManagement.Entity.Book;
import SahafManagement.Entity.User;
import SahafManagement.Repository.IBookRepository;
import SahafManagement.Repository.IUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private IUserRepository iUserRepository;

    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private IBookRepository iBookRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setUserName("User 1");
        user.setUserPassword("12345");
        when(passwordEncoder.encode("12345")).thenReturn("123456");
        when(iUserRepository.save(user)).thenReturn(user);
        User savedUser = userService.saveUser(user);
        assertEquals("123456", savedUser.getUserPassword());
        verify(iUserRepository).save(user);
    }

    @Test
    public void testDeleteUser() {
        Long id = 1L;
        doNothing().when(iUserRepository).deleteById(id);
        userService.deleteUser(id);
        verify(iUserRepository).deleteById(id);
    }

    @Test
    public void testUpdateUser() {
        Long bookId = 1L;
        Book book1 = new Book();
        book1.setBookName("Book");
        book1.setBookBookstores(new ArrayList<>());
        book1.setUserBookRental(new ArrayList<>());
        book1.setBooksUsers(new ArrayList<>());
        iBookRepository.save(book1);

        Book book2 = new Book();
        book2.setBookName("Book 2");
        book2.setBookBookstores(new ArrayList<>());
        book2.setUserBookRental(new ArrayList<>());
        book2.setBooksUsers(new ArrayList<>());
        iBookRepository.save(book2);

        User user = new User();
        user.setUserName("User 1");
        user.setUserPassword("password");
        user.setUserRole("ROLE_ADMIN");
        user.setUsersBook(Arrays.asList(book1, book2));

        when(iUserRepository.findById(bookId)).thenReturn(Optional.of(user));
        when(iUserRepository.save(user)).thenReturn(user);

        User updatedUser = new User();
        updatedUser.setUserName("User 2");

        Book book3 = new Book();
        book3.setBookName("Book 3");
        book3.setBookBookstores(new ArrayList<>());
        book3.setUserBookRental(new ArrayList<>());
        book3.setBooksUsers(new ArrayList<>());

        Book book4 = new Book();
        book4.setBookName("Book 3");
        book4.setBookBookstores(new ArrayList<>());
        book4.setUserBookRental(new ArrayList<>());
        book4.setBooksUsers(new ArrayList<>());
        updatedUser.setUsersBook(Arrays.asList(book3, book4));

        User result = userService.updateUser(bookId, updatedUser);

        assertEquals(updatedUser.getUserName(), result.getUserName());
        assertEquals(updatedUser.getUsersBook(), result.getUsersBook());

        Mockito.verify(iUserRepository).findById(bookId);
        Mockito.verify(iUserRepository).save(argThat(argument -> argument.getUserName().equals("User 2")
                && argument.getUsersBook().equals(Arrays.asList(book3, book4))));


    }

    @Test
    public void testGetAllUsers() {
        List<User> userList = Arrays.asList(new User(),new User(),new User(),new User());

        when(iUserRepository.findAll()).thenReturn(userList);

        List<User> result = userService.getAllUsers();

        assertEquals(userList, result);
        Mockito.verify(iUserRepository).findAll();
    }

}