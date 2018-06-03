import { Component, OnInit } from '@angular/core';
import { BookshelfService } from '../bookshelf.service';
import { Book } from '../book.model';

@Component({
  selector: 'app-bookshelf-list',
  templateUrl: './bookshelf-list.component.html',
  styleUrls: ['./bookshelf-list.component.css']
})
export class BookshelfListComponent implements OnInit {
  books: Book[];

  constructor(private bookshelfService: BookshelfService) { }

  ngOnInit() {
    this.bookshelfService.getAll().then((bookshelf) => {
      this.books = bookshelf;
    });
  }
}
