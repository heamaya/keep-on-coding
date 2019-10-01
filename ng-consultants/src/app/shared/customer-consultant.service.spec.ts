import { TestBed, inject } from '@angular/core/testing';

import { CustomerConsultantService } from './customer-consultant.service';

describe('CustomerConsultantService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CustomerConsultantService]
    });
  });

  it('should be created', inject([CustomerConsultantService], (service: CustomerConsultantService) => {
    expect(service).toBeTruthy();
  }));
});
