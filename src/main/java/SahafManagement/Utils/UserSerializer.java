package SahafManagement.Utils;

import SahafManagement.Entity.User;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;

/*
user listesindeki her User nesnesini yinelemek için bir döngü başlar.
writeStartObject ile her User nesnesi için JSON nesnesi başlatılır.
writeObjectField, User nesnesinin her alanını JSON çıktısına yazmak için kullanırı.
writeEndObject ve writeEndObject ile JSON nesnesi kapatılır.
 */

public class UserSerializer extends JsonSerializer<List<User>> {


    @Override
    public void serialize(List<User> userList, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartArray();
        for (User user : userList) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeObjectField("userId", user.getUserId());
            jsonGenerator.writeObjectField("userName", user.getUserName());
            jsonGenerator.writeObjectField("userPassword", user.getUserPassword());
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
    }
}
