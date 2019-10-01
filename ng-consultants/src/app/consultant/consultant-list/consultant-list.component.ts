import { Component, OnInit, OnDestroy } from '@angular/core';
import { Consultant } from '../consultant.model';
import { Subscription } from 'rxjs/Subscription';
import { ConsultantService } from '../../shared/consultant.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-consultant-list',
  templateUrl: './consultant-list.component.html',
  styleUrls: ['./consultant-list.component.css']
})
export class ConsultantListComponent implements OnInit, OnDestroy {
  consultants: Consultant[];
  subscription: Subscription;
  currentConsultant: Consultant;

  constructor(private consultantService: ConsultantService,
    private router: Router,
    private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.subscription = this.consultantService.
      consultantsChanged.
      subscribe(
        (consultants: Consultant[]) => {
          this.consultants = consultants;
        }
    );

    this.consultants = this.consultantService.getAll();
  }

  onNewConsultant() {
    this.router.navigate(['new'], {relativeTo: this.route});
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  getConsultantService() {
    return this.consultantService;
  }
}
