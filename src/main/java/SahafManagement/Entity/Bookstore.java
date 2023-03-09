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
Manytomany anotasyonu ile bir kitapçının birden çok kitap ile etkileşime girebileceğinin tanımlarız.
 */

@Entity
@Table(name = "bookstore")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bookstore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookstoreId;
    @Column(name = "bookstore_name")
    private String bookstoreName;
    @Column
    private String bookstoreAddress;
    @ManyToMany(mappedBy = "bookBookstores", cascade = CascadeType.ALL)
    @JsonSerialize(using = BookSerializer.class)
    private List<Book> bookstoreBooks;
}
