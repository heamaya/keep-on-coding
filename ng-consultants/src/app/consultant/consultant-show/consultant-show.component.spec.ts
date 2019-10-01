import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultantShowComponent } from './consultant-show.component';

describe('ConsultantShowComponent', () => {
  let component: ConsultantShowComponent;
  let fixture: ComponentFixture<ConsultantShowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConsultantShowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConsultantShowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
