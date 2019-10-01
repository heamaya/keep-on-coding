import { Component, OnInit, Input } from '@angular/core';
import { Consultant } from '../../consultant.model';
import { ConsultantService } from '../../../shared/consultant.service';
import { ActivatedRoute, Router } from '@angular/router';
import { UploadService } from '../../../shared/upload.service'
import { ConsultantCustomerService } from '../../../shared/consultant-customer.service';
import { CustomerConsultantService } from '../../../shared/customer-consultant.service';
import { ConsultantCustomer } from '../../../shared/consultant-customer.model';
import { CustomerConsultant } from '../../../shared/customer-consultant.model';
import { Overlay } from 'ngx-modialog';
import { Modal } from 'ngx-modialog/plugins/bootstrap'

@Component({
  selector: 'app-consultant-item',
  templateUrl: './consultant-item.component.html',
  styleUrls: ['./consultant-item.component.css']
})
export class ConsultantItemComponent implements OnInit {
  @Input() consultant : Consultant;

  constructor(private route: ActivatedRoute,
              private consultantService: ConsultantService,
              private consultantCustomerService: ConsultantCustomerService,
              private customerConsultantService: CustomerConsultantService,
              private router: Router,
              private uploadService: UploadService,
              public modal: Modal) {
  }

  ngOnInit() {
  }

  onShow() {
     this.router.navigate(['consultants', this.consultant.id, 'show']);
  }

  onEdit() {
     this.router.navigate(['consultants', this.consultant.id, 'edit']);
  }

  onDelete() {
    const dialogRef = this.modal.confirm()
        .size('sm')
        .showClose(true)
        .title('Borrar Consultor')
        .body(`
          <h5 class="text-danger">
            ¿Está seguro que desea borrar a
            ${this.consultant.name}
            junto a todas sus relaciones a clientes?
          </h5>`)
        .okBtn('Borrar')
        .okBtnClass('btn btn-danger')
        .cancelBtn('Cancelar')
        .open();

    dialogRef.result.then(result => {
      if(result) {
        if(this.consultant.profilePicture) {
          let basePath = "/consultants";
          let databasePath = basePath + '/' + this.consultant.id + '/profilePicture';

          this.uploadService.deleteUpload(this.consultant.profilePicture, basePath, databasePath, null);
        }

        this.consultantCustomerService.get(this.consultant.id).then((consultantCustomer: ConsultantCustomer) => {

          for(let customer of consultantCustomer.customers) {
            this.customerConsultantService.removeConsultant(customer.id, this.consultant);
          }

          this.consultantCustomerService.delete(consultantCustomer.consultantId);
          this.consultantService.delete(this.consultant.id);
          this.consultantService.refresh();
          this.router.navigate(['/consultants']);
        }).catch((exception) => {
          this.consultantService.delete(this.consultant.id);
          this.consultantService.refresh();
          this.router.navigate(['/consultants']);
        });
      }

    }).catch(exception => {});
  }

  getConsultantService() {
    return this.consultantService;
  }
}
