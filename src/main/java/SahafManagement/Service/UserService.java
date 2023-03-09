package SahafManagement.Service;

import SahafManagement.Entity.User;
import SahafManagement.Repository.IUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/*
saveUser() kitapları veri tabanına kaydeder.
deleteUserk() veri tabanında bulunan kitapları siler.
updateUser() veri tabanında kitabın olup olmadığını id parametresiyle kontrol eder ve updateBook parametresiyle veri tabanında bulunan değerlerini günceller.
getAllBooks() Veri tabanında bulunan kitapları geri döndürür.
 */

@Service
public class UserService {

    private IUserRepository iUserRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(IUserRepository iUserRepository, PasswordEncoder passwordEncoder) {
        this.iUserRepository = iUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User saveUser(User user) {
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        return iUserRepository.save(user);
    }

    public void deleteUser(Long id) {
        iUserRepository.deleteById(id);
    }

    public User updateUser(Long id, User updatedUser) {
        User user = iUserRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found."));
        user.setUserName(updatedUser.getUserName());
        user.setUserPassword(updatedUser.getUserPassword());
        user.setUsersBook(updatedUser.getUsersBook());
        return iUserRepository.save(user);
    }

    public List<User> getAllUsers(){
        return iUserRepository.findAll();
    }
}
