import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../../authentication/authentication.service';

@Component({
  selector: 'app-consultant-start',
  templateUrl: './consultant-start.component.html',
  styleUrls: ['./consultant-start.component.css']
})
export class ConsultantStartComponent implements OnInit {

  constructor(private authenticationService: AuthenticationService) { }

  ngOnInit() {
  }

  isAdmin() {
    return this.authenticationService.user.roles.admin;
  }
}
