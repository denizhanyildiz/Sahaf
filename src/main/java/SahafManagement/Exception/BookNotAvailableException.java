package SahafManagement.Exception;

/*
Kitapların kiralandığı durumda hata dönmek için message parametresindeki hatayı verir.
 */

public class BookNotAvailableException extends Exception {
    public BookNotAvailableException(String message) {
        super(message);
    }
}