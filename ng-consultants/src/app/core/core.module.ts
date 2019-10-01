import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HeaderComponent } from './header/header.component';
import { AuthenticationService } from '../authentication/authentication.service';
import { SharedModule } from '../shared/shared.module';
import { AppRoutingModule } from '../app-routing.module';

@NgModule({
  declarations: [
    HeaderComponent
  ],
  imports: [
    FormsModule,
    SharedModule,
    AppRoutingModule
  ],
  exports: [
    HeaderComponent
  ],
  providers: [
    AuthenticationService
  ]
})
export class CoreModule {}
