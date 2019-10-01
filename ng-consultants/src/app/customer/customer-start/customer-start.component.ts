import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../../authentication/authentication.service';

@Component({
  selector: 'app-customer-start',
  templateUrl: './customer-start.component.html',
  styleUrls: ['./customer-start.component.css']
})
export class CustomerStartComponent implements OnInit {

  constructor(private authenticationService: AuthenticationService) { }

  ngOnInit() {
  }

  isAdmin() {
    return this.authenticationService.user.roles.admin;
  }

}
