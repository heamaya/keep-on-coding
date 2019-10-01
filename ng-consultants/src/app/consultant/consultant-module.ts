import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

import { ConsultantComponent } from './consultant.component';
import { ConsultantListComponent } from './consultant-list/consultant-list.component';
import { ConsultantItemComponent } from './consultant-list/consultant-item/consultant-item.component';
import { ConsultantRoutingModule } from './consultant-routing.module';
import { ConsultantEditComponent } from './consultant-edit/consultant-edit.component';
import { SharedModule } from '../shared/shared.module';
import { ConsultantStartComponent } from './consultant-start/consultant-start.component';
import { ConsultantShowComponent } from './consultant-show/consultant-show.component';


@NgModule({
  declarations: [
    ConsultantComponent,
    ConsultantListComponent,
    ConsultantItemComponent,
    ConsultantEditComponent,
    ConsultantStartComponent,
    ConsultantShowComponent
  ],
  imports: [
    ConsultantRoutingModule,
    SharedModule
  ]
})
export class ConsultantModule {}
