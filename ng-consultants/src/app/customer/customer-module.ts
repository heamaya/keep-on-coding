import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

import { CustomerComponent } from './customer.component';
import { CustomerListComponent } from './customer-list/customer-list.component';
import { CustomerItemComponent } from './customer-list/customer-item/customer-item.component';
import { CustomerRoutingModule } from './customer-routing.module';
import { CustomerEditComponent } from './customer-edit/customer-edit.component';
import { SharedModule } from '../shared/shared.module';
import { CustomerStartComponent } from './customer-start/customer-start.component';
import { CustomerShowComponent } from './customer-show/customer-show.component';

@NgModule({
  declarations: [
    CustomerComponent,
    CustomerListComponent,
    CustomerItemComponent,
    CustomerEditComponent,
    CustomerStartComponent,
    CustomerShowComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    CustomerRoutingModule,
    SharedModule
  ]
})
export class CustomerModule {}
