package SahafManagement.Exception;

/*
Kitapçının veri tabanında bulunmadığı anda message parametresi ile hata döner.
 */

public class BookstoreNotFoundException extends Exception  {
    public BookstoreNotFoundException(String message) {
        super(message);
    }
}
