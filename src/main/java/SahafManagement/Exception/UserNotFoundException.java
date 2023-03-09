package SahafManagement.Exception;

/*
Ver tabanında kullanıcı bulunmadığı anda message parametresi ile hata döner.
 */

public class UserNotFoundException extends Exception {

    public UserNotFoundException(String message) {
        super(message);
    }
}
