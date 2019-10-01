import { Component, OnInit, Input} from '@angular/core';
import { Customer } from '../../customer.model';
import { CustomerService } from '../../../shared/customer.service';
import { ActivatedRoute, Router } from '@angular/router';
import { UploadService } from '../../../shared/upload.service';
import { ConsultantCustomerService } from '../../../shared/consultant-customer.service';
import { CustomerConsultantService } from '../../../shared/customer-consultant.service';
import { ConsultantCustomer } from '../../../shared/consultant-customer.model';
import { CustomerConsultant } from '../../../shared/customer-consultant.model';
import { Overlay } from 'ngx-modialog';
import { Modal } from 'ngx-modialog/plugins/bootstrap'

@Component({
  selector: 'app-customer-item',
  templateUrl: './customer-item.component.html',
  styleUrls: ['./customer-item.component.css']
})
export class CustomerItemComponent implements OnInit {
  @Input() customer : Customer;

  constructor(private route: ActivatedRoute,
              private customerService: CustomerService,
              private customerConsultantService: CustomerConsultantService,
              private consultantCustomerService: ConsultantCustomerService,
              private router: Router,
              private uploadService: UploadService,
              public modal: Modal) {
  }

  ngOnInit() {
  }

  onShow() {
     this.router.navigate(['customers', this.customer.id, 'show']);
  }

  onEdit() {
     this.router.navigate(['customers', this.customer.id, 'edit']);
  }

  onDelete() {
    const dialogRef = this.modal.confirm()
        .size('sm')
        .showClose(true)
        .title('Borrar Cliente')
        .body(`
          <h5 class="text-danger">
            ¿Está seguro que desea borrar a
            ${this.customer.name}
            junto a todas sus relaciones a consultores?
          </h5>`)
        .okBtn('Borrar')
        .okBtnClass('btn btn-danger')
        .cancelBtn('Cancelar')
        .open();

    dialogRef.result.then(result => {
      if(result) {
        if(this.customer.interviews) {
          let basePath = "/customers";
          let databasePath = basePath + '/' + this.customer.id + '/interviews';

          this.uploadService.deleteUpload(this.customer.interviews, basePath, databasePath, null);
        }

        this.customerConsultantService.get(this.customer.id).then((customerConsultant: CustomerConsultant) => {

          for(let consultant of customerConsultant.consultants) {
            this.consultantCustomerService.removeCustomer(consultant.id, this.customer);
          }

          this.customerConsultantService.delete(customerConsultant.customerId);
          this.customerService.delete(this.customer.id);
          this.customerService.refresh();
          this.router.navigate(['/customers']);
        }).catch((exception) => {
          this.customerService.delete(this.customer.id);
          this.customerService.refresh();
          this.router.navigate(['/customers']);
        });
      }

    }).catch(exception => {});
  }

  getCustomerService() {
    return this.customerService;
  }
}
