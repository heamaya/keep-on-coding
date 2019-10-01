import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { FormGroup, FormControl, FormArray, Validators } from '@angular/forms';
import { CustomerService } from '../../shared/customer.service';
import { Customer } from '../customer.model';
import { UploadService } from '../../shared/upload.service';
import { Upload } from '../../shared/upload.model';
import { ConsultantService } from '../../shared/consultant.service';
import { Consultant } from '../../consultant/consultant.model';
import { ConsultantCustomerService } from '../../shared/consultant-customer.service';
import { ConsultantCustomer } from '../../shared/consultant-customer.model';
import { CustomerConsultantService} from '../../shared/customer-consultant.service';
import { CustomerConsultant } from '../../shared/customer-consultant.model';

import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';

import 'rxjs/add/observable/of';
import 'rxjs/add/operator/filter';
import 'rxjs/add/operator/debounceTime';

@Component({
  selector: 'app-customer-edit',
  templateUrl: './customer-edit.component.html',
  styleUrls: ['./customer-edit.component.css']
})
export class CustomerEditComponent implements OnInit {
  editMode: boolean = false;
  customerId: string;
  customer: Customer;
  customerForm: FormGroup;
  patient: boolean = false;
  selectedFiles: FileList;
  currentUpload: Upload;
  previousUpload: Upload;
  isSaving: boolean = false;
  autocompleteConsultants: Consultant[] = [];
  currentConsultants: Consultant[] = [];
  consultants: Consultant[] = [];

  constructor(private route: ActivatedRoute,
              private consultantService: ConsultantService,
              private customerService: CustomerService,
              private consultantCustomerService: ConsultantCustomerService,
              private customerConsultantService: CustomerConsultantService,
              private router: Router,
              private uploadService: UploadService) {
  }

  ngOnInit() {
    this.route.params
      .subscribe(
        (params: Params) => {
          this.customerId = params['id'];
          this.editMode = params['id'] != null;
          this.patient = false;
          this.autocompleteConsultants = this.consultantService.getAll();
          this.customerConsultantService.get(this.customerId).then(
            (customerConsultant: CustomerConsultant) => {

              if(customerConsultant) {
                this.currentConsultants = customerConsultant.consultants;
              }

              this.initializeForm();
            }
          ).catch((exception) => {
            this.currentConsultants = [];
            this.initializeForm();
          });
        }
      );
  }

  initializeForm() {
    let name: string = '';
    let type: string = '';
    let activity: string = '';
    let seniority: number = 1;
    let history: string = '';
    let employeesQuantity: number = 1;
    let owners: FormArray = new FormArray([]);
    let managers: FormArray = new FormArray([]);
    let consultantReferrings: FormArray = new FormArray([]);
    let interventionAreas: FormArray = new FormArray([]);
    let consultationReason: string = '';
    let diagnosisSummary: string = '';
    let anotherInformation: string = '';

    if(this.editMode) {
      this.customerService.get(this.customerId).then(
        (customer) => {
          this.customer = customer;
          name = customer.name;
          type = customer.type;
          activity = customer.activity;
          seniority = customer.seniority;
          history = customer.history;
          employeesQuantity = customer.employeesQuantity;

          if(customer['owners']) {

            for(let owner of customer.owners) {
              owners.push(
                new FormGroup({
                  'firstName': new FormControl(owner.firstName),
                  'lastName': new FormControl(owner.lastName)
                })
              );
            }

          }

          if(customer['managers']) {

            for(let manager of customer.managers) {
              managers.push(
                new FormGroup({
                  'firstName': new FormControl(manager.firstName),
                  'lastName': new FormControl(manager.lastName)
                })
              );
            }

          }

          if(customer['consultantReferrings']) {

            for(let consultantReferring of customer.consultantReferrings) {
              consultantReferrings.push(
                new FormGroup({
                  'firstName': new FormControl(consultantReferring.firstName),
                  'lastName': new FormControl(consultantReferring.lastName)
                })
              );
            }

          }

          if(customer['interventionAreas']) {

            for(let interventionArea of customer.interventionAreas) {
              interventionAreas.push(
                new FormGroup({
                  'name': new FormControl(interventionArea.name)
                })
              );
            }

          }

          consultationReason = customer.consultationReason;
          diagnosisSummary = customer.diagnosisSummary;
          anotherInformation = customer.anotherInformation;

          if(customer['interviews']) {
            this.previousUpload = customer.interviews;
          }

          this.customerForm = new FormGroup({
            'name': new FormControl(name, Validators.required),
            'type': new FormControl(type),
            'activity': new FormControl(activity),
            'seniority': new FormControl(seniority, Validators.pattern('[1-9][0-9]*')),
            'history': new FormControl(history),
            'employeesQuantity': new FormControl(employeesQuantity, Validators.pattern('[1-9][0-9]*')),
            'owners': owners,
            'managers': managers,
            'consultantReferrings': consultantReferrings,
            'interventionAreas': interventionAreas,
            'consultationReason' : new FormControl(consultationReason),
            'diagnosisSummary' : new FormControl(diagnosisSummary),
            'anotherInformation' : new FormControl(anotherInformation),
            'consultants': new FormControl(this.currentConsultants)
          });

          this.patient = true;
      });

    } else {
      this.customerForm = new FormGroup({
        'name': new FormControl(name, Validators.required),
        'type': new FormControl(type),
        'activity': new FormControl(activity),
        'seniority': new FormControl(seniority, Validators.pattern('[1-9][0-9]*')),
        'history': new FormControl(history),
        'employeesQuantity': new FormControl(employeesQuantity, Validators.pattern('[1-9][0-9]*')),
        'owners': owners,
        'managers': managers,
        'consultantReferrings': consultantReferrings,
        'interventionAreas': interventionAreas,
        'consultationReason' : new FormControl(consultationReason),
        'diagnosisSummary' : new FormControl(diagnosisSummary),
        'anotherInformation' : new FormControl(anotherInformation),
        'consultants': new FormControl(this.currentConsultants)
      });
      this.patient = true;
    }
  }

  onCancel() {
    this.router.navigate(['/customers']);
  }

  onSubmit() {
    this.isSaving = true;
    const customerConsultants: Consultant[] = this.customerForm.get("consultants").value;
    this.customerForm.removeControl("consultants");
    const customer: Customer = this.customerForm.value;

    if(this.editMode) {

      this.customerConsultantService.get(this.customerId).then((customerConsultant: CustomerConsultant) => {
        this.customerConsultantService.delete(this.customerId);
        customerConsultant.customerId = this.customerId;
        customerConsultant.consultants = [];

        for(let aConsultant of customerConsultants) {
          customerConsultant.consultants.push(aConsultant);
        }

        if(customerConsultant.consultants.length > 0) {
          this.customerConsultantService.add(customerConsultant);
        }

        this.mirrorData(customer, customerConsultants);
      }).catch((exception) => {
        let customerConsultant: CustomerConsultant = new CustomerConsultant();
        customerConsultant.customerId = this.customerId;
        customerConsultant.consultants = customerConsultants;
        this.customerConsultantService.add(customerConsultant);
        this.mirrorData(customer, customerConsultants);
      });

      if(this.currentUpload) {

        if(this.previousUpload) {
          let basePath = "/customers";
          let databasePath = basePath + '/' + this.customerId + '/interviews';
          this.uploadService.deleteUpload(this.previousUpload, basePath, databasePath, null);
        }

        this.customerService.update(this.customerId, customer);
        this.uploadSingle(this.customer.id);
      } else if(this.previousUpload) {
        this.customerService.update(this.customerId, customer);
        this.updateUploadedData(this.customerId);
        this.router.navigate(['/customers']);
        this.customerService.refresh();
      } else {
        this.customerService.update(this.customerId, customer);
        this.router.navigate(['/customers']);
        this.customerService.refresh();
      }

    } else {
      this.customerId = this.customerService.add(customer);

      if(customerConsultants && customerConsultants.length > 0) {
        let newCustomerConsultant: CustomerConsultant = new CustomerConsultant();
        newCustomerConsultant.customerId = this.customerId;
        newCustomerConsultant.consultants = [];

        for(let newConsultant of customerConsultants) {
          newCustomerConsultant.consultants.push(newConsultant);
        }

        this.customerConsultantService.add(newCustomerConsultant);

        for(let newConsultant of customerConsultants) {
          customer.id = this.customerId;

          this.consultantCustomerService.get(newConsultant.id).then((consultantCustomer: ConsultantCustomer) => {
            this.consultantCustomerService.addCustomer(newConsultant.id, customer);
          }).catch((exception) => {
            let consultantCustomer: ConsultantCustomer = new ConsultantCustomer();
            consultantCustomer.consultantId = newConsultant.id;
            consultantCustomer.customers = [];
            consultantCustomer.customers.push(customer);
            this.consultantCustomerService.add(consultantCustomer);
          });
        }
      }

      if(this.currentUpload) {
        this.uploadSingle(this.customerId);
      } else {
        this.router.navigate(['/customers']);
        this.customerService.refresh();
      }
    }

  }

  onAddControl(control: string) {
    if(control == 'interventionAreas') {
      (<FormArray> this.customerForm.get(control)).push(
        new FormGroup({
          'name': new FormControl(null)
        })
      );
    } else {
      (<FormArray> this.customerForm.get(control)).push(
        new FormGroup({
          'firstName': new FormControl(null),
          'lastName': new FormControl(null),
        })
      );
    }
  }

  onDeleteControl(control: string, index: number) {
    (<FormArray> this.customerForm.get(control)).removeAt(index);
  }

  detectFiles(event) {
    this.selectedFiles = event.target.files;
    let file = this.selectedFiles.item(0);
    this.currentUpload = new Upload(file);
  }

  uploadSingle(id: string) {
    let basePath = "/customers";
    let databasePath = basePath + '/' + id + '/interviews';

    this.uploadService.pushUpload(this.currentUpload, basePath, databasePath, '/customers', this.customerService);
  }

  updateUploadedData(id: string) {
    let basePath = "/customers";
    let databasePath = basePath + '/' + id + '/interviews';

    this.uploadService.saveFileData(this.previousUpload, databasePath);
  }

  mirrorData(customer: Customer, customerConsultants: Consultant[]) {
    let found: boolean = false;
    let consultantsToRemove: Consultant[] = [];

    for(let previousConsultant of this.currentConsultants) {
      found = false;

      for(let newConsultant of customerConsultants) {

        if(previousConsultant.id == newConsultant.id) {
          found = true;
        }

      }

      if(!found) {
        consultantsToRemove.push(previousConsultant);
      }

    }

    found = false;
    let consultantsToAdd: Consultant[] = [];

    for(let newConsultant of customerConsultants) {
      found = false;

      for(let previousConsultant of this.currentConsultants) {

        if(newConsultant.id == previousConsultant.id) {
          found = true;
        }

      }

      if(!found) {
        consultantsToAdd.push(newConsultant);
      }

    }

    customer.id = this.customerId;

    for(let newConsultant of consultantsToAdd) {
      this.consultantCustomerService.get(newConsultant.id).then((consultantCustomer: ConsultantCustomer) => {
        this.consultantCustomerService.addCustomer(newConsultant.id, customer);
      }).catch((exception) => {
        let consultantCustomer: ConsultantCustomer = new ConsultantCustomer();
        consultantCustomer.consultantId = newConsultant.id;
        consultantCustomer.customers = [];
        consultantCustomer.customers.push(customer);
        this.consultantCustomerService.add(consultantCustomer);
      });
    }

    for(let consultantToRemove of consultantsToRemove) {
      this.consultantCustomerService.removeCustomer(consultantToRemove.id, customer);
    }
  }
}
