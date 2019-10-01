import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { ConsultantService } from '../../shared/consultant.service';
import { Consultant } from '../consultant.model';
import { ConsultantCustomerService } from '../../shared/consultant-customer.service';
import { ConsultantCustomer } from '../../shared/consultant-customer.model';
import { Customer } from '../../customer/customer.model';

@Component({
  selector: 'app-consultant-show',
  templateUrl: './consultant-show.component.html',
  styleUrls: ['./consultant-show.component.css']
})
export class ConsultantShowComponent implements OnInit {
  consultantId: string;
  consultant: Consultant;
  customers: Customer[];
  patient: boolean = false;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private consultantService: ConsultantService,
              private consultantCustomerService: ConsultantCustomerService) {
  }

  ngOnInit() {
    this.route.params.subscribe(
      (params: Params) => {
        this.consultantId = params['id'];
        this.consultantService.get(this.consultantId).then(
            (consultant: Consultant) => {
              this.consultant = consultant;

              this.consultantCustomerService.get(this.consultantId).then((consultantCustomer: ConsultantCustomer) => {
                this.customers = consultantCustomer.customers;
                this.patient = true;
              }).catch((exception) => {
                this.customers = []
                this.patient = true;
              });
            }
        );
      }
    );
  }

  onReturn() {
    this.router.navigate(['/consultants']);
  }

}
