import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthenticationGuard } from '../authentication/authentication.guard';

import { ConsultantComponent } from './consultant.component'
import { ConsultantEditComponent } from './consultant-edit/consultant-edit.component';
import { ConsultantListComponent } from './consultant-list/consultant-list.component';
import { ConsultantStartComponent } from './consultant-start/consultant-start.component';
import { ConsultantShowComponent } from './consultant-show/consultant-show.component';


const consultantRoutes: Routes = [
  { path: 'consultants', component: ConsultantComponent, canActivate: [AuthenticationGuard],
    children: [
        { path: '', component: ConsultantStartComponent, canActivate: [AuthenticationGuard] },
        { path: 'new', component: ConsultantEditComponent, canActivate: [AuthenticationGuard] },
        { path: ':id/edit', component: ConsultantEditComponent, canActivate: [AuthenticationGuard] },
        { path: ':id/show', component: ConsultantShowComponent, canActivate: [AuthenticationGuard] }
    ]
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(consultantRoutes)
  ],
  exports: [RouterModule],
  providers: [AuthenticationGuard]
})
export class ConsultantRoutingModule {
}
