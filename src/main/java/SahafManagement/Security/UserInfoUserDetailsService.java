package SahafManagement.Security;

import SahafManagement.Entity.User;
import SahafManagement.Repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

/*
Kullanıcı adına göre, kullanıcının ayrıntılarını getirmek için loadUserByUsername metodu kullanılır.
Kullanıcı adına göre kullanıcı arar ve bunu geri döndürür kullanıcı yoksa UsernameNotFoundException hatası döndürülür.

 */

public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserRepository iUserRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = iUserRepository.findByUserName(userName);
        return user.map(UserInfoUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + userName));

    }
}
