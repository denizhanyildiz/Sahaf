package SahafManagement.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/*
Veri tabanı sınıfı fieldlerin her biri bir sütuna denktir.
 */

@Entity
@Table(name = "log")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "request_url")
    private String requestUrl;
    @Column(name = "request_method")
    private String requestMethod;
    @Column(name = "response_status")
    private int responseStatus;
    @Column(name = "response_body")
    private String responseBody;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
}
