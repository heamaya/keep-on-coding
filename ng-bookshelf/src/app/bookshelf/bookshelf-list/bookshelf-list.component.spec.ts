import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BookshelfListComponent } from './bookshelf-list.component';

describe('BookshelfListComponent', () => {
  let component: BookshelfListComponent;
  let fixture: ComponentFixture<BookshelfListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BookshelfListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BookshelfListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
