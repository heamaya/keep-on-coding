import { Injectable } from '@angular/core';
import { Bookshelf } from './bookshelf.model';
import { Book } from './book.model';
import { Author } from './author.model';
import { Subject } from 'rxjs/index';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class BookshelfService {

  constructor(private httpClient: HttpClient) { }

  getAll(): Promise<Book[]> {
    return new Promise<Book[]>((resolve, reject) => {
      this.httpClient.get<Bookshelf>("http://localhost:8080/bookshelf/books").subscribe(
          (bookshelf) => {
            bookshelf.bookshelf.forEach((book) => {
                if(!book['authors'] || book['authors'] === null) {
                  book['authors'] = [];
                } else {
                    let count: number = 0;
                    book.authors.forEach((author) =>{
                      if(author === null) {
                          author = new Author();
                          author.name = 'the author is missing!';
                          count++;
                      }
                    });

                    if(book.authors.length == count) {
                      book.authors = [];
                    }
                }
            });
            console.log(bookshelf);
            resolve(bookshelf.bookshelf);
          }
        );
    });
  }
}
