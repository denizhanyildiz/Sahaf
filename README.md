# Sahaf
Sahaf Manager Application

Uygulamayı Docker ile başlatmak için bilgisayaranızda docker yüklü olması gerekmektedir;

1- Terminale aşağıdaki kodu yazınız.
  
'docker build .' 

2-Docker build olduktan sonra
  
'docker-compose up' 

kodu yazılır ve sahaf uygulamasıyla mysql conteinerları aynı network ile iletişim kurarak bilgisayarımızda ayağa kalkmış olur.

Uygulama /Sahaf dosya yolunun altında bulunan Postman Collection dosyasını Postman'e import ederek test edebilirsiniz.

Uygulama içerisinde Unit Testler sadece istenen APIlerin servislerine yazılmıştır.

Uygulamayı manuel olarak po düzgün test edilebilmesi için aşağıdaki adımların sırayla yapılması gerekmektedir.

Bu API ile sisteme ilk başta kullanıcı tanımlanmalıdır, zira BasicAuth olmayan tek API dir ve öteki APIlern kullanılması için kullanıcıya ROLE_ADMIN rolü tanımlanmaldır.

http://localhost:8080/user/save

Bu API ile sisteme bir adet kitap kaydedilmelidir.

http://localhost:8080/book/save

Bu API ile sisteme kitapçı kaydedilmelidir.

http://localhost:8080/bookstore/save

Kaydedilen 1 idli kitabın, kitapçıya tanımlanması gerekmektdir.

http://localhost:8080/bookstorepurchase/book/{bookId}/add-to-bookstore/{booksotreId}

Ilgili kitapçıya kitap tanımlandıktan sonra kullanıcılar ilgili kitaçıdan kitabı kiralamak için aşağıdaki API kullanılır. Bu API headerına Param key value bilgileri yazılmalıdır. 

http://localhost:8080/bookstorerent/rent

Query Param API Sırasıyla Key ve örnek Valuelar,

KEYS: userId, bookstoreId, bookId, rentalDate, returnDate

VALUE: 1, 1, 1, 2023-03-07, 2023-03-14

Not: Kitap başka bir kitapçıda kiralanmışsa başka bir kullanıcı tarafından kiralanamaz fakat aynı kitap başka kitapçıda varsa kullanıcı başka kitapçıdan kitabı kiralayabilir. 
  
  
  
  
  
  
  
  
