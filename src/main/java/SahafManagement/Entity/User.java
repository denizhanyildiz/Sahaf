package SahafManagement.Entity;

import SahafManagement.Utils.BookSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/*
Veri tabanı sınıfı fieldlerin her biri bir sütuna denktir.
Manytomany anotasyonu ile bir kullanıcının birden çok kitap ile etkileşime girebileceğinin tanımlarız.
 */

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_password")
    private String userPassword;
    @Column(name = "user_role")
    private String userRole;
    @ManyToMany(mappedBy = "booksUsers", cascade = CascadeType.ALL)
    @JsonSerialize(using = BookSerializer.class)
    private List<Book> usersBook;
}
