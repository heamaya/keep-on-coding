import { Component, OnInit, NgZone } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-unauthorized',
  templateUrl: './unauthorized.component.html',
  styleUrls: ['./unauthorized.component.css']
})
export class UnauthorizedComponent implements OnInit {

  constructor(private router: Router, private zone: NgZone) { }

  ngOnInit() {
  }

  onGoBack() {
    this.zone.run(() => {
      this.router.navigate(['/']);
    });
  }
}
