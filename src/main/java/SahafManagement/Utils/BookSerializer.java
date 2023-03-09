package SahafManagement.Utils;

import SahafManagement.Entity.Book;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;

/*
book listesindeki her Book nesnesini yinelemek için bir döngü başlar.
writeStartObject ile her Book nesnesi için JSON nesnesi başlatılır.
writeObjectField, Book nesnesinin her alanını JSON çıktısına yazmak için kullanırı.
writeEndObject ve writeEndObject ile JSON nesnesi kapatılır.
 */

public class BookSerializer extends JsonSerializer<List<Book>> {
    @Override
    public void serialize(List<Book> books, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartArray();
        for (Book book : books) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeObjectField("bookId", book.getBookId());
            jsonGenerator.writeObjectField("bookName", book.getBookName());
            //jsonGenerator.writeObjectField("bookBookstores", book.getBookBookstores());
            //jsonGenerator.writeObjectField("userBookRental", book.getUserBookRental());
            //jsonGenerator.writeObjectField("bookUsers", book.getBooksUsers());

            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
    }
}
