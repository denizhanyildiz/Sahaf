# Sahaf
Sahaf Manager Application

## Docker
Uygulamayı Docker ile başlatmak için bilgisayarınızda docker yüklü olması gerekmektedir.
Ardından Maven/Sahaf/Lifecycle install çalıştırılarak Sahaf/Target klasörü altında "Sahaf-0.0.1-SNAPSHOT.jar" ve "Sahaf-0.0.1-SNAPSHOT.jar.original" dosyaları yaratılır.

* Kullanılan IDE sisteminin terminalinde docker'ı build etmek için ilk olarak aşağıdaki kodu yazarak çalıştırınız.
```sh  
docker build .
```

* Kullanılan IDE terminalinde Docker build tamamlandıktan sonra, containerların ilişkili şekilde ayağa kalkması için kullanılan IDE terminaline aşağıdaki kodu yazarak çalıştırırız.
```sh
docker-compose up
```

Sahaf uygulamasıyla mysql conteinerları aynı network ile iletişim kurarak bilgisayarımızda ayağa kalkmış olur. Postman veya tarayıcıdan ilgili API ler test edilebilir.
Docker Desktop uygulamasında ayağakalkan Container lari görebiliriz.

Not: Burada dikkat edilmesi gereken unsur uygulamaların ilişkili olduğu portların kulanılmıyor olması gerekmektedir. 
(Sahaf Application 8080, mysql 3307)

## Manuel Test

Uygulamada /Sahaf dosya yolunun altında bulunan Postman Collection dosyasını Postman'e import ederek test edebilirsiniz.

Uygulamayı manuel olarak Postman ile düzgün test edilebilmesi için aşağıdaki adımların sırayla yapılması gerekmektedir.

1./user/save APIsi ile Postman den sisteme ilk başta kullanıcı tanımlanmalıdır, zira /user/save BasicAuth olmayan tek API dir ve öteki APIlern kullanılması için kullanıcıya ROLE_ADMIN rolü tanımlanmaldır. /save/user API si POST türünde çalıştırılmalıdır. 

http://localhost:8080/user/save

Örnek Body:
```sh
{
    "userName":"Denizhan",
    "userPassword":"12345",
    "userRole":"ROLE_ADMIN"
}
```

2.Bu API ile sisteme bir adet kitap kaydedilmelidir ve POST türünde çalıştırılmalıdır. 

http://localhost:8080/book/save

Örnek Body:
```sh
{
    "bookName":"Ali Okulda"
}
```

3.Bu API ile sisteme kitapçı kaydedilmelidir ve POST türünde çalıştırılmalıdır. 

http://localhost:8080/bookstore/save

Örnek Body:
```sh
{
    "bookstoreName":"DenizKitapEvi",
    "bookstoreAddress":"Istanbul"
}
```
4.Kaydedilen kitabın, kitapçıya tanımlanması gerekmektdir. Path variable olarak sırasıyla book id ve bookstore id verilmelidir ve PUT türünde çalıştırılmalıdır. 

http://localhost:8080/bookstorepurchase/book/{bookId}/add-to-bookstore/{booksotreId}

Örnek URL:
```sh
http://localhost:8080/bookstorepurchase/book/1/add-to-bookstore/1
```

5.Ilgili kitapçıya kitap tanımlandıktan sonra kullanıcılar ilgili kitaçıdan kitabı kiralamak için aşağıdaki API kullanılır. Bu API headerına Param key value bilgileri yazılmalıdır ve POST türünde çalıştırılmalıdır. 

http://localhost:8080/bookstorerent/rent

Query Param API Sırasıyla Key ve örnek Valuelar,

```sh
KEYS: userId, bookstoreId, bookId, rentalDate, returnDate
```
```sh
VALUE: 1, 1, 1, 2023-03-07, 2023-03-14
```

6.Kitapçıların günlük kiraladığı total kitap raporunu görebilmek için aşağıdaki apiyi ullanmamız gerekmektedir. Bu API headerına Param key value bilgileri yazılmalıdır ve GET türünde çalıştırılmalıdır.

http://localhost:8080/bookstorereport

```sh
KEY: rentalDate
```
```sh
VALUE: 2023-03-07
```
## NOTLAR
Not1: Kitap kitapçıda kiralanmışsa başka bir kullanıcı tarafından kiralanamaz fakat aynı kitap başka kitapçıda varsa kullanıcı başka kitapçıdan kitabı kiralayabilir. 

Not2: Uygulama içerisinde Unit Testler sadece istenen APIlerin servislerine yazılmıştır.

Not3: Sisteme gelen her request ve sistemin döndüğü her respons veri tabanında log tablosunda görüntülenebilir, bu işlemde AOP kullanılmıştır.
