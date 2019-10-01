import { Injectable } from '@angular/core';
import { CustomerConsultant } from './customer-consultant.model';
import { Consultant } from '../consultant/consultant.model';
import * as firebase from 'firebase';

@Injectable()
export class CustomerConsultantService {

  get(customerId: string) : Promise<CustomerConsultant> {

    return new Promise<CustomerConsultant>((resolve, reject) => {
      let customerConsultant: CustomerConsultant = null;

      firebase.database()
        .ref('customerConsultants/' + customerId)
        .once('value', (snapshot) => {
          customerConsultant = snapshot.val();

          if(customerConsultant) {

            if(!snapshot.child('consultants').exists()) {
              customerConsultant.consultants = [];
            }

            return resolve(customerConsultant);
          }

          reject(customerConsultant);

        });

    });

  }

  add(customerConsultant: CustomerConsultant) {
    let customerId = customerConsultant.customerId;
    firebase.database().ref().child('customerConsultants/' + customerId).set(customerConsultant);
  }

  update(customerId: string, newCustomerConsultant: CustomerConsultant) {
    newCustomerConsultant.customerId = customerId;
    firebase.database().ref().child('customerConsultants/' + customerId).set(newCustomerConsultant);
  }

  addConsultant(customerId: string, consultant: Consultant) {
    this.get(customerId).then((customerConsultant: CustomerConsultant) => {

      if(customerConsultant) {
          customerConsultant.consultants[customerConsultant.consultants.length] = consultant;
          this.update(customerConsultant.customerId, customerConsultant);
      }

    });
  }

  removeConsultant(customerId: string, consultant: Consultant) {
    this.get(customerId).then((customerConsultant: CustomerConsultant) => {

      if(customerConsultant) {
        let newConsultants: Consultant[] = [];

        for(let aConsultant of customerConsultant.consultants) {

          if(aConsultant.id != consultant.id) {
            newConsultants[newConsultants.length] = aConsultant;
          }
        }

        if(newConsultants.length == 0) {
          this.delete(customerId);
        } else if(customerConsultant.consultants.length > newConsultants.length) {
            customerConsultant.consultants = newConsultants;
            this.update(customerConsultant.customerId, customerConsultant);
        }
      }
    });
  }

  delete(customerId: string) {
    firebase.database().ref().child('customerConsultants/' + customerId).remove();
  }

  deleteConsultant(customerId: string, consultant: Consultant) {
    let customerConsultants: CustomerConsultant[] = [];

    firebase.database().ref('customerConsultants/').
        once('value', (snapshot) => {
          snapshot.forEach(
                (childSnapshot) => {
                  let customerConsultant: CustomerConsultant = childSnapshot.val();

                  if(!childSnapshot.child('consultants').exists()) {
                    customerConsultant.consultants = [];
                  }

                  let newConsultants: Consultant[] = [];

                  for(let aConsultant of customerConsultant.consultants) {

                    if(aConsultant.id != consultant.id) {
                      newConsultants[newConsultants.length] = aConsultant;
                    }
                  }

                  if(customerConsultant.consultants.length > newConsultants.length) {
                    customerConsultant.consultants =  newConsultants;
                    this.update(customerConsultant.customerId, customerConsultant);
                  }

                  return false;
                });
            });
      }
}
