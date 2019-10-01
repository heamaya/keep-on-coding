import { Injectable } from '@angular/core';
import { ConsultantCustomer } from './consultant-customer.model';
import { Customer } from '../customer/customer.model';
import * as firebase from 'firebase';

@Injectable()
export class ConsultantCustomerService {

  get(consultantId: string) : Promise<ConsultantCustomer> {

    return new Promise<ConsultantCustomer>((resolve, reject) => {
      let consultantCustomer: ConsultantCustomer = null;

      firebase.database()
        .ref('consultantCustomers/' + consultantId)
        .once('value', (snapshot) => {
          consultantCustomer = snapshot.val();

          if(consultantCustomer) {

            if(!snapshot.child('customers').exists()) {
              consultantCustomer.customers = [];
            }

            return resolve(consultantCustomer);
          }

          reject(consultantCustomer);

        });

    });

  }

  add(consultantCustomer: ConsultantCustomer) {
    let consultantId = consultantCustomer.consultantId;
    firebase.database().ref().child('consultantCustomers/' + consultantId).set(consultantCustomer);
  }

  update(consultantId: string, newConsultantCustomer: ConsultantCustomer) {
    newConsultantCustomer.consultantId = consultantId;
    firebase.database().ref().child('consultantCustomers/' + consultantId).set(newConsultantCustomer);
  }

  addCustomer(consultantId: string, customer: Customer) {
    this.get(consultantId).then((consultantCustomer: ConsultantCustomer) => {

      if(consultantCustomer) {
          consultantCustomer.customers[consultantCustomer.customers.length] = customer;
          this.update(consultantCustomer.consultantId, consultantCustomer);
      }

    });
  }

  removeCustomer(consultantId: string, customer: Customer) {
    this.get(consultantId).then((consultantCustomer: ConsultantCustomer) => {

      if(consultantCustomer) {
        let newCustomers: Customer[] = [];

        for(let aCustomer of consultantCustomer.customers) {

          if(aCustomer.id != customer.id) {
            newCustomers[newCustomers.length] = aCustomer;
          }
        }

        if(newCustomers.length == 0) {
          this.delete(consultantId);
        } else if(consultantCustomer.customers.length > newCustomers.length) {
            consultantCustomer.customers = newCustomers;
            this.update(consultantCustomer.consultantId, consultantCustomer);
        }
      }
    });
  }

  delete(consultantId: string) {
    firebase.database().ref().child('consultantCustomers/' + consultantId).remove();
  }

  deleteCustomer(customer: Customer) {
    let consultantCustomers: ConsultantCustomer[] = [];

    firebase.database().ref('consultantCustomers').
        once('value', (snapshot) => {
          snapshot.forEach(
                (childSnapshot) => {
                  let consultantCustomer: ConsultantCustomer = childSnapshot.val();

                  if(!childSnapshot.child('customers').exists()) {
                    consultantCustomer.customers = [];
                  }

                  let newCustomers: Customer[] = [];

                  for(let aCustomer of consultantCustomer.customers) {

                    if(aCustomer.id != customer.id) {
                      newCustomers[newCustomers.length] = aCustomer;
                    }
                  }

                  if(consultantCustomer.customers.length > newCustomers.length) {
                    consultantCustomer.customers =  newCustomers;
                    this.update(consultantCustomer.consultantId, consultantCustomer);
                  }

                  return false;
                });
            });
      }
}
