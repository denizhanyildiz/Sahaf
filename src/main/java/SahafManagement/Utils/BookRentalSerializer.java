package SahafManagement.Utils;

import SahafManagement.Entity.BookRental;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;

/*
bookRental listesindeki her BookRental nesnesini yinelemek için bir döngü başlar.
writeStartObject ile her BookRental nesnesi için JSON nesnesi başlatılır.
writeObjectField, BookRental nesnesinin her alanını JSON çıktısına yazmak için kullanırı.
writeEndObject ve writeEndObject ile JSON nesnesi kapatılır.
 */

public class BookRentalSerializer extends JsonSerializer<List<BookRental>> {
    @Override
    public void serialize(List<BookRental> bookRentals, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartArray();
        for (BookRental bookstore : bookRentals) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeObjectField("bookRentalId", bookstore.getBookRentalId());
            jsonGenerator.writeObjectField("rentalDate", bookstore.getRentalDate());
            jsonGenerator.writeObjectField("returnDate", bookstore.getReturnDate());
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
    }
}
