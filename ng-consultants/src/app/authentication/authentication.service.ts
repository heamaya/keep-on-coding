import { Injectable, NgZone } from '@angular/core';
import { Router } from '@angular/router';
import { AngularFireAuth } from 'angularfire2/auth';
import { AngularFireDatabase } from 'angularfire2/database';
import * as firebase from 'firebase/app';
import { Consultant } from '../consultant/consultant.model';

@Injectable()
export class AuthenticationService {
  user: Consultant = null;

  constructor(private router: Router,
    private zone: NgZone,
    private firebaseAuthentication: AngularFireAuth,
    private angularFireDatabase: AngularFireDatabase) {
  }

  login() {
    this.firebaseAuthentication.auth.signInWithPopup(new firebase.auth.GoogleAuthProvider())
      .then((response) => {
        this.getAuthenticatedUser(response.additionalUserInfo.profile.email).then(
          (consultant: Consultant) => {
            this.user = consultant;

            this.zone.run(() => {
              this.router.navigate(['/consultants']);
            });
          }).catch((exception) => {
            this.user = null;

            this.zone.run(() => {
              this.router.navigate(['/unauthorized']);
            });
          });
        });
  }

  logout() {
    this.firebaseAuthentication.auth.signOut();
    this.user = null;
    this.router.navigate(['/']);
  }

  isAuthenticated() {
    return this.user != null;
  }

  getAuthenticatedUser(email: string): Promise<Consultant> {
    let authenticatedConsultant: Consultant = null;

    return new Promise((resolve, reject) => {
      firebase.database().ref('consultants').
        once('value', (snapshot) => {
          snapshot.forEach(
                (childSnapshot) => {
                  let consultant: Consultant = childSnapshot.val();

                  if(consultant.email == email) {
                      authenticatedConsultant = consultant;
                      resolve(authenticatedConsultant);

                      return true;
                  }

                  return false;
                }
            );

            reject(authenticatedConsultant);
        });
    });
  }
}
