import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../../authentication/authentication.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  isIn: boolean = false;

  constructor(private authenticationService: AuthenticationService) {
  }

  ngOnInit() {
  }

  onLogout() {
    this.authenticationService.logout();
  }

  getAuthenticationService() {
    return this.authenticationService;
  }

  toggle() {
      this.isIn = !this.isIn;
  }

}
