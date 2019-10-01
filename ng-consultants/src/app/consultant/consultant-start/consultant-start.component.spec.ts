import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultantStartComponent } from './consultant-start.component';

describe('ConsultantStartComponent', () => {
  let component: ConsultantStartComponent;
  let fixture: ComponentFixture<ConsultantStartComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConsultantStartComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConsultantStartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
