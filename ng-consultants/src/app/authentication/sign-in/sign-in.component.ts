import {
  Component,
  OnInit
} from '@angular/core';

import { NgForm } from '@angular/forms';
import { AuthenticationService } from '../authentication.service';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit {
  
  constructor(private authenticationService: AuthenticationService) { }

  ngOnInit() {
  }

  onSubmit() {
    this.authenticationService.login();
  }
}
