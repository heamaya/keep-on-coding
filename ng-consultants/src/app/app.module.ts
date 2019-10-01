import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { AngularFireModule } from 'angularfire2';
import { AngularFireDatabase } from 'angularfire2/database';
import { CoreModule } from './core/core.module';
import { AuthenticationModule } from './authentication/authentication.module';
import { SharedModule } from './shared/shared.module';
import { ConsultantModule } from './consultant/consultant-module';
import { CustomerModule } from './customer/customer-module'

import { environment } from '../environments/environment';
import { BdComponent } from './bd/bd.component';

@NgModule({
  declarations: [
    AppComponent,
    BdComponent
  ],
  imports: [
    BrowserModule,
    SharedModule,
    AppRoutingModule,
    CoreModule,
    AuthenticationModule,
    AngularFireModule,
    ConsultantModule,
    CustomerModule,
    AngularFireModule.initializeApp(environment.firebase),
  ],
  providers: [AngularFireDatabase],
  bootstrap: [AppComponent]
})
export class AppModule { }
