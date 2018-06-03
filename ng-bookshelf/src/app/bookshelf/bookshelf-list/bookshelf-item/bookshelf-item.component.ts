import { Component, OnInit, Input } from '@angular/core';
import { Book } from '../../book.model';

@Component({
  selector: 'app-bookshelf-item',
  templateUrl: './bookshelf-item.component.html',
  styleUrls: ['./bookshelf-item.component.css']
})
export class BookshelfItemComponent implements OnInit {
  @Input() book: Book;

  constructor() { }

  ngOnInit() {
  }

}
