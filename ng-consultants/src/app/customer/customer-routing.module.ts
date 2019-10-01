import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthenticationGuard } from '../authentication/authentication.guard';

import { CustomerComponent } from './customer.component'
import { CustomerEditComponent } from './customer-edit/customer-edit.component';
import { CustomerStartComponent } from './customer-start/customer-start.component';
import { CustomerShowComponent } from './customer-show/customer-show.component';


const customerRoutes: Routes = [
  { path: 'customers', component: CustomerComponent, canActivate: [AuthenticationGuard],
    children: [
        { path: '', component: CustomerStartComponent, canActivate: [AuthenticationGuard] },
        { path: 'new', component: CustomerEditComponent, canActivate: [AuthenticationGuard] },
        { path: ':id/edit', component: CustomerEditComponent, canActivate: [AuthenticationGuard] },
        { path: ':id/show', component: CustomerShowComponent, canActivate: [AuthenticationGuard] }
    ]
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(customerRoutes)
  ],
  exports: [RouterModule],
  providers: [AuthenticationGuard]
})
export class CustomerRoutingModule {
}
