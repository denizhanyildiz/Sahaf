package SahafManagement.Exception;

/*
Kitapların veri tabanında var olmadığı anda message parametresi ile hata döner.
 */

public class BookNotFoundException extends Exception {

    public BookNotFoundException(String message) {
        super(message);
    }
}
