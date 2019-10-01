import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { SignInComponent } from './sign-in/sign-in.component';
import { AppRoutingModule } from '../app-routing.module';
import { AngularFireAuthModule } from 'angularfire2/auth';
import { UnauthorizedComponent } from './unauthorized/unauthorized.component';

@NgModule({
  declarations: [
    SignInComponent,
    UnauthorizedComponent
  ],
  imports: [
    FormsModule,
    AppRoutingModule,
    AngularFireAuthModule
  ]
})
export class AuthenticationModule {}
