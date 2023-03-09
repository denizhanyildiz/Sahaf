package SahafManagement.Entity;

import SahafManagement.Utils.BookRentalSerializer;
import SahafManagement.Utils.UserSerializer;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
Veri tabanı sınıfı fieldlerin her biri bir sütuna denktir.
Onetomany anotasyonu ile kitapların sadece bir adet sınıf ile etkileşime girdiğini tanımlarız.
Manytomany anotasyonu ile bir kitabın birden çok kullanıcı ile etkileşime girebileceğinin tanımlarız.
 */

import java.util.List;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    @Column(name = "book_name")
    private String bookName;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "books_bookstores",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "bookstore_id"))
    private List<Bookstore> bookBookstores;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    @JsonSerialize(using = BookRentalSerializer.class)
    private List<BookRental> userBookRental;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "books_users",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> booksUsers;
}
