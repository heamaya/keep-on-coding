import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { CustomerService } from '../../shared/customer.service';
import { Customer } from '../customer.model';
import { CustomerConsultantService } from '../../shared/customer-consultant.service';
import { CustomerConsultant } from '../../shared/customer-consultant.model';
import { Consultant } from '../../consultant/consultant.model';

@Component({
  selector: 'app-customer-show',
  templateUrl: './customer-show.component.html',
  styleUrls: ['./customer-show.component.css']
})
export class CustomerShowComponent implements OnInit {
  customerId: string;
  customer: Customer;
  consultants: Consultant[];
  patient: boolean = false;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private customerService: CustomerService,
              private customerConsultantService: CustomerConsultantService) {
  }

  ngOnInit() {
    this.route.params.subscribe(
      (params: Params) => {
        this.customerId = params['id'];
        this.customerService.get(this.customerId).then(
            (customer: Customer) => {
              this.customer = customer;

              this.customerConsultantService.get(this.customerId).then((customerConsultant: CustomerConsultant) => {
                this.consultants = customerConsultant.consultants;
                this.patient = true;
              }).catch((exception) => {
                this.consultants = []
                this.patient = true;
              });
            }
        );
      }
    );
  }

  onReturn() {
    this.router.navigate(['/customers']);
  }

}
