import { Injectable } from '@angular/core';
import { Customer } from '../customer/customer.model';
import { Subject } from 'rxjs/Subject';
import * as firebase from 'firebase';
import { Refreshable } from '../shared/refreshable.service';
import { AuthenticationService } from '../authentication/authentication.service';

@Injectable()
export class CustomerService implements Refreshable {
  customersChanged = new Subject<Customer[]>();

  constructor(private authenticationService: AuthenticationService) {
  }

  getAll() {
    let customers: Customer[] = [];

    firebase.database().ref('customers').
      once('value', (snapshot) => {
        snapshot.forEach(
              (childSnapshot) => {
                let customer: Customer = childSnapshot.val();

                if(!childSnapshot.child('owners').exists()) {
                  customer.owners = [];
                }

                if(!childSnapshot.child('managers')) {
                  customer.managers = [];
                }

                if(!childSnapshot.child('consultantsReferring')) {
                  customer.consultantReferrings = [];
                }

                if(!childSnapshot.child('interventionAreas')) {
                  customer.interventionAreas = [];
                }

                if(childSnapshot.child('interviews').exists()) {

                  childSnapshot.child('interviews').forEach((childSnapshot) => {
                      customer.interviews = childSnapshot.val();

                      return false;
                  });
                }

                customers[customers.length] =  customer;

                return false;
              }
          );
      });

    return customers;
  }

  get(id: string): Promise<Customer> {

    return new Promise<Customer>((resolve, reject) => {
      let customer: Customer = null;

      firebase.database().ref('customers/' + id).
        once('value', (snapshot) => {
          customer = snapshot.val();

          if(customer) {

            if(!snapshot.child('owners').exists()) {
              customer.owners = [];
            }

            if(!snapshot.child('managers')) {
              customer.managers = [];
            }

            if(!snapshot.child('consultantsReferring')) {
              customer.consultantReferrings = [];
            }

            if(!snapshot.child('interventionAreas')) {
              customer.interventionAreas = [];
            }

            if(snapshot.child('interviews').exists()) {

              snapshot.child('interviews').forEach((childSnapshot) => {
                  customer.interviews = childSnapshot.val();

                  return false;
              });
            }

            resolve(customer);
          }

          reject(customer);
      });
    });
  }

  add(customer: Customer): string {
    customer.id = firebase.database().ref().child('customers').push().key;
    firebase.database().ref('customers/' + customer.id).set(customer);

    return customer.id;
  }

  update(id: string, newCustomer: Customer) {
    newCustomer.id = id;
    firebase.database().ref('customers/' + newCustomer.id).set(newCustomer);
  }

  delete(id: string) {
    firebase.database().ref('customers/' + id).remove();
  }

  canAdd(): boolean {
    let canAdd: boolean = false;

    if(this.authenticationService.user.roles.admin == true) {
      canAdd = true;
    }

    return canAdd;
  }

  canEdit(): boolean {
    let canEdit: boolean = false;
    const authenticatedUser = this.authenticationService.user;

    if(authenticatedUser.roles.admin == true) {
      canEdit = true;
    }

    return canEdit;
  }

  canDelete(): boolean {
    let canDelete: boolean = false;
    const authenticatedUser = this.authenticationService.user;

    if(authenticatedUser.roles.admin == true) {
      canDelete = true;
    }

    return canDelete;
  }

  refresh() {
    this.customersChanged.next(this.getAll());
  }
}
