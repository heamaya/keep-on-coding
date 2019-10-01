import { NgModule } from '@angular/core';
import { Routes, RouterModule, PreloadAllModules } from '@angular/router';
import { SignInComponent } from './authentication/sign-in/sign-in.component';
import { UnauthorizedComponent } from './authentication/unauthorized/unauthorized.component';

const appRoutes: Routes = [
  { path: '', component: SignInComponent },
  { path: 'unauthorized', component: UnauthorizedComponent },
];

@NgModule({
  imports: [
    RouterModule.forRoot(appRoutes, {preloadingStrategy: PreloadAllModules})
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
