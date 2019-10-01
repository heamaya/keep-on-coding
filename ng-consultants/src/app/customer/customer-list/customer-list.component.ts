import { Component, OnInit } from '@angular/core';
import { Customer } from '../customer.model';
import { Subscription } from 'rxjs/Subscription';
import { CustomerService } from '../../shared/customer.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-customer-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.css']
})
export class CustomerListComponent implements OnInit {
  customers: Customer[];
  subscription: Subscription;
  currentCustomer: Customer;

  constructor(private customerService: CustomerService,
    private router: Router,
    private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.subscription = this.customerService.
      customersChanged.
      subscribe(
        (customers: Customer[]) => {
          this.customers = customers;
        }
    );

    this.customers = this.customerService.getAll();
  }

  onNewCustomer() {
    this.router.navigate(['new'], {relativeTo: this.route});
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  getCustomerService() {
    return this.customerService;
  }
}
