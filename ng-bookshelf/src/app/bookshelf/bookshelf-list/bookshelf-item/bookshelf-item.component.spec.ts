import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BookshelfItemComponent } from './bookshelf-item.component';

describe('BookshelfItemComponent', () => {
  let component: BookshelfItemComponent;
  let fixture: ComponentFixture<BookshelfItemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BookshelfItemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BookshelfItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
