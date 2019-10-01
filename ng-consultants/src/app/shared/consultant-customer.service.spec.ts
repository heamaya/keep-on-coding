import { TestBed, inject } from '@angular/core/testing';

import { ConsultantCustomerService } from './consultant-customer.service';

describe('ConsultantCustomerService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ConsultantCustomerService]
    });
  });

  it('should be created', inject([ConsultantCustomerService], (service: ConsultantCustomerService) => {
    expect(service).toBeTruthy();
  }));
});
