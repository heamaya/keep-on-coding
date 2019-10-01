import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DropdownDirective } from './dropdown.directive';
import { UploadService } from './upload.service';
import { ConsultantCustomerService } from './consultant-customer.service';
import { ConsultantService } from './consultant.service';
import { CustomerService } from './customer.service';
import { CustomerConsultantService } from './customer-consultant.service';

import { TagInputModule } from 'ngx-chips';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { ModalModule } from 'ngx-modialog';
import { BootstrapModalModule } from 'ngx-modialog/plugins/bootstrap';

@NgModule({
  declarations: [
    DropdownDirective
  ],
  imports: [
    CommonModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    TagInputModule,
    ModalModule.forRoot(),
    BootstrapModalModule
  ],
  exports: [
    CommonModule,
    DropdownDirective,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    TagInputModule,
    ModalModule,
    BootstrapModalModule
  ],
  providers: [
    ConsultantService,
    CustomerService,
    UploadService,
    ConsultantCustomerService,
    CustomerConsultantService
  ]
})
export class SharedModule {

}
