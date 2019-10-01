import { Injectable } from '@angular/core';
import { Consultant } from '../consultant/consultant.model';
import { Subject } from 'rxjs/Subject';
import * as firebase from 'firebase';
import { Refreshable } from '../shared/refreshable.service';
import { AuthenticationService } from '../authentication/authentication.service';

@Injectable()
export class ConsultantService implements Refreshable {
  consultantsChanged = new Subject<Consultant[]>();

  constructor(private authenticationService: AuthenticationService) {
  }

  getAll() {
    let consultants: Consultant[] = [];

    firebase.database().ref('consultants').
      once('value', (snapshot) => {
        snapshot.forEach(
              (childSnapshot) => {
                let consultant: Consultant = childSnapshot.val();

                if(!childSnapshot.child('professions').exists()) {
                  consultant.professions = [];
                }

                if(!childSnapshot.child('workExperienceAreas')) {
                  consultant.workExperienceAreas = [];
                }

                if(!childSnapshot.child('workExperienceCompanies')) {
                  consultant.workExperienceCompanies = [];
                }

                if(!childSnapshot.child('workPositions')) {
                  consultant.workPositions = [];
                }

                if(!childSnapshot.child('specialties')) {
                  consultant.specialties = [];
                }

                if(childSnapshot.child('profilePicture').exists()) {

                  childSnapshot.child('profilePicture').forEach((childSnapshot) => {
                      consultant.profilePicture = childSnapshot.val();

                      return false;
                  });
                }

                consultants[consultants.length] =  consultant;

                return false;
              }
          );
      });

    return consultants;
  }

  get(id: string): Promise<Consultant> {

    return new Promise<Consultant>((resolve, reject) => {
      let consultant: Consultant = null;

      firebase.database().ref('consultants/' + id).
        once('value', (snapshot) => {
          consultant = snapshot.val();

          if(consultant) {

            if(!snapshot.child('professions').exists()) {
              consultant.professions = [];
            }

            if(!snapshot.child('workExperienceAreas')) {
              consultant.workExperienceAreas = [];
            }

            if(!snapshot.child('workExperienceCompanies')) {
              consultant.workExperienceCompanies = [];
            }

            if(!snapshot.child('workPositions')) {
              consultant.workPositions = [];
            }

            if(!snapshot.child('specialties')) {
              consultant.specialties = [];
            }

            if(snapshot.child('profilePicture').exists()) {

              snapshot.child('profilePicture').forEach((childSnapshot) => {
                  consultant.profilePicture = childSnapshot.val();

                  return false;
              });
            }

            resolve(consultant);
          }

          reject(consultant);
      });
    });
  }

  add(consultant: Consultant): string {
    consultant.id = firebase.database().ref().child('consultants').push().key;
    firebase.database().ref('consultants/' + consultant.id).set(consultant);

    return consultant.id;
  }

  update(id: string, newConsultant: Consultant) {
    newConsultant.id = id;
    firebase.database().ref('consultants/' + newConsultant.id).set(newConsultant);

    if(id == this.authenticationService.user.id) {
      this.authenticationService.user = newConsultant;
    }
  }

  delete(id: string) {
    firebase.database().ref('consultants/' + id).remove();
  }

  canAdd(): boolean {
    let canAdd: boolean = false;

    if(this.authenticationService.user.roles.admin == true) {
      canAdd = true;
    }

    return canAdd;
  }

  canEdit(consultant: Consultant): boolean {
    let canEdit: boolean = false;
    const authenticatedUser = this.authenticationService.user;

    if(consultant.id == authenticatedUser.id ||
      (consultant.roles.admin == false &&
      authenticatedUser.roles.admin == true)) {
      canEdit = true;
    }

    return canEdit;
  }

  canDelete(consultant: Consultant): boolean {
    let canDelete: boolean = false;
    const authenticatedUser = this.authenticationService.user;

    if(authenticatedUser.roles.admin == true &&
      consultant.roles.admin == false) {
      canDelete = true;
    }

    return canDelete;
  }

  canEditRoles() {
    return this.authenticationService.user.roles.admin;
  }

  refresh() {
    this.consultantsChanged.next(this.getAll());
  }
}
