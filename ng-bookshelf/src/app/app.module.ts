import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { BookshelfService } from './bookshelf/bookshelf.service';

import { AppComponent } from './app.component';
import { BookshelfComponent } from './bookshelf/bookshelf.component';
import { BookshelfListComponent } from './bookshelf/bookshelf-list/bookshelf-list.component';
import { BookshelfItemComponent } from './bookshelf/bookshelf-list/bookshelf-item/bookshelf-item.component';

@NgModule({
  declarations: [
    AppComponent,
    BookshelfComponent,
    BookshelfListComponent,
    BookshelfItemComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule
  ],
  providers: [
    BookshelfService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
