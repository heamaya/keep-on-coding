import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { FormGroup, FormControl, FormArray, Validators, ReactiveFormsModule } from '@angular/forms';
import { ConsultantService } from '../../shared/consultant.service';
import { Consultant } from '../consultant.model';
import { UploadService } from '../../shared/upload.service';
import { Upload } from '../../shared/upload.model';
import { CustomerService } from '../../shared/customer.service';
import { Customer } from '../../customer/customer.model';
import { ConsultantCustomerService } from '../../shared/consultant-customer.service';
import { ConsultantCustomer } from '../../shared/consultant-customer.model';
import { CustomerConsultantService} from '../../shared/customer-consultant.service';
import { CustomerConsultant } from '../../shared/customer-consultant.model';
import { Roles } from '../roles.model';

import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';

import 'rxjs/add/observable/of';
import 'rxjs/add/operator/filter';
import 'rxjs/add/operator/debounceTime';

@Component({
  selector: 'app-consultant-edit',
  templateUrl: './consultant-edit.component.html',
  styleUrls: ['./consultant-edit.component.css']
})
export class ConsultantEditComponent implements OnInit {
  editMode: boolean = false;
  consultantId: string;
  consultant: Consultant;
  consultantForm: FormGroup;
  patient: boolean = false;
  selectedFiles: FileList;
  currentUpload: Upload;
  previousUpload: Upload;
  isSaving: boolean = false;
  autocompleteCustomers: Customer[] = [];
  currentCustomers: Customer[] = [];
  customers: Customer[] = [];
  hasAdminRole: boolean = false;

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
          this.consultantId = params['id'];
          this.editMode = params['id'] != null;
          this.patient = false;
          this.autocompleteCustomers = this.customerService.getAll();
          this.consultantCustomerService.get(this.consultantId).then(
            (consultantCustomer: ConsultantCustomer) => {

              if(consultantCustomer) {
                this.currentCustomers = consultantCustomer.customers;
              }

              this.initializeForm();
            }
          ).catch((exception) => {
            this.currentCustomers = [];
            this.initializeForm();
          });
        }
      );
  }

  initializeForm() {
    let firstName: string = '';
    let lastName: string = '';
    let cellphone: string = '';
    let email: string = '';
    let consultancyParticipationReason: string = '';
    let availabilityFrom: string = '';
    let availabilityTo: string = '';
    let professions: FormArray = new FormArray([]);
    let workExperienceAreas: FormArray = new FormArray([]);
    let workExperienceCompanies: FormArray = new FormArray([]);
    let workPositions: FormArray = new FormArray([]);
    let specialties: FormArray = new FormArray([]);

    if(this.editMode) {
      this.consultantService.get(this.consultantId).then(
        (consultant) => {
          this.consultant = consultant;
          firstName = consultant.firstName;
          lastName = consultant.lastName;
          cellphone = consultant.cellphone;
          email = consultant.email;
          consultancyParticipationReason = consultant.consultancyParticipationReason;
          availabilityFrom = consultant.availabilityFrom;
          availabilityTo = consultant.availabilityTo;
          this.hasAdminRole = consultant.roles.admin;

          if(consultant['professions']) {

            for(let profession of consultant.professions) {
              professions.push(
                new FormGroup({
                  'name': new FormControl(profession.name)
                })
              );
            }

          }

          if(consultant['workExperienceAreas']) {

            for(let workExperienceArea of consultant.workExperienceAreas) {
              workExperienceAreas.push(
                new FormGroup({
                  'name': new FormControl(workExperienceArea.name)
                })
              );
            }

          }

          if(consultant['workExperienceCompanies']) {

            for(let workExperienceCompany of consultant.workExperienceCompanies) {
              workExperienceCompanies.push(
                new FormGroup({
                  'name': new FormControl(workExperienceCompany.name)
                })
              );
            }

          }

          if(consultant['workPositions']) {

            for(let workPosition of consultant.workPositions) {
              workPositions.push(
                new FormGroup({
                  'name': new FormControl(workPosition.name)
                })
              );
            }

          }

          if(consultant['specialties']) {

            for(let specialty of consultant.specialties) {
              specialties.push(
                new FormGroup({
                  'name': new FormControl(specialty.name)
                })
              );
            }

          }

          if(consultant['profilePicture']) {
            this.previousUpload = consultant.profilePicture;
          }

          if(this.consultantService.canEditRoles()) {
            this.consultantForm = new FormGroup({
              'firstName': new FormControl(firstName, Validators.required),
              'lastName': new FormControl(lastName, Validators.required),
              'email': new FormControl(email, [Validators.required, Validators.email]),
              'cellphone': new FormControl(cellphone, Validators.pattern('[0-9]*')),
              'consultancyParticipationReason': new FormControl(consultancyParticipationReason),
              'availabilityFrom': new FormControl(availabilityFrom, Validators.pattern('([01]?[0-9]|2[0-3]):[0-5][0-9]')),
              'availabilityTo': new FormControl(availabilityTo, Validators.pattern('([01]?[0-9]|2[0-3]):[0-5][0-9]')),
              'professions': professions,
              'workExperienceAreas': workExperienceAreas,
              'workExperienceCompanies': workExperienceCompanies,
              'workPositions': workPositions,
              'specialties': specialties,
              'customers': new FormControl(this.currentCustomers),
              'adminRole': new FormControl(this.hasAdminRole)
            });

          } else {
            this.consultantForm = new FormGroup({
              'firstName': new FormControl(firstName, Validators.required),
              'lastName': new FormControl(lastName, Validators.required),
              'email': new FormControl(email, [Validators.required, Validators.email]),
              'cellphone': new FormControl(cellphone, Validators.pattern('[0-9]*')),
              'consultancyParticipationReason': new FormControl(consultancyParticipationReason),
              'availabilityFrom': new FormControl(availabilityFrom, Validators.pattern('([01]?[0-9]|2[0-3]):[0-5][0-9]')),
              'availabilityTo': new FormControl(availabilityTo, Validators.pattern('([01]?[0-9]|2[0-3]):[0-5][0-9]')),
              'professions': professions,
              'workExperienceAreas': workExperienceAreas,
              'workExperienceCompanies': workExperienceCompanies,
              'workPositions': workPositions,
              'specialties': specialties,
              'customers': new FormControl(this.currentCustomers)
            });

          }

          this.patient = true;
      });

    } else {

      if(this.consultantService.canEditRoles()) {
        this.consultantForm = new FormGroup({
          'firstName': new FormControl(firstName, Validators.required),
          'lastName': new FormControl(lastName, Validators.required),
          'email': new FormControl(email, [Validators.required, Validators.email]),
          'cellphone': new FormControl(cellphone, Validators.pattern('[0-9]*')),
          'consultancyParticipationReason': new FormControl(consultancyParticipationReason),
          'availabilityFrom': new FormControl(availabilityFrom, Validators.pattern('[0-9][0-9]:[0-9][0-9]')),
          'availabilityTo': new FormControl(availabilityTo, Validators.pattern('[0-9][0-9]:[0-9][0-9]')),
          'professions': professions,
          'workExperienceAreas': workExperienceAreas,
          'workExperienceCompanies': workExperienceCompanies,
          'workPositions': workPositions,
          'specialties': specialties,
          'customers': new FormControl(this.currentCustomers),
          'adminRole': new FormControl(this.hasAdminRole)
        });
      } else {
        this.consultantForm = new FormGroup({
          'firstName': new FormControl(firstName, Validators.required),
          'lastName': new FormControl(lastName, Validators.required),
          'email': new FormControl(email, [Validators.required, Validators.email]),
          'cellphone': new FormControl(cellphone, Validators.pattern('[0-9]*')),
          'consultancyParticipationReason': new FormControl(consultancyParticipationReason),
          'availabilityFrom': new FormControl(availabilityFrom, Validators.pattern('[0-9][0-9]:[0-9][0-9]')),
          'availabilityTo': new FormControl(availabilityTo, Validators.pattern('[0-9][0-9]:[0-9][0-9]')),
          'professions': professions,
          'workExperienceAreas': workExperienceAreas,
          'workExperienceCompanies': workExperienceCompanies,
          'workPositions': workPositions,
          'specialties': specialties,
          'customers': new FormControl(this.currentCustomers)
        });
      }

      this.patient = true;
    }
  }

  onCancel() {
    this.router.navigate(['/consultants']);
  }

  onSubmit() {
    this.isSaving = true;
    const consultantCustomers: Customer[] = this.consultantForm.get("customers").value;
    this.consultantForm.removeControl("customers");

    if(this.consultantService.canEditRoles()) {
      this.hasAdminRole = this.consultantForm.get("adminRole").value;
      this.consultantForm.removeControl("adminRole");
    }

    const consultant: Consultant = this.consultantForm.value;
    consultant.name = consultant.firstName + " " + consultant.lastName;
    consultant.roles = <Roles>{};
    consultant.roles.admin = this.hasAdminRole;
    consultant.roles.consultant = true;

    if(this.editMode) {

      this.consultantCustomerService.get(this.consultantId).then((consultantCustomer: ConsultantCustomer) => {
        this.consultantCustomerService.delete(this.consultantId);
        consultantCustomer.consultantId = this.consultantId;
        consultantCustomer.customers = [];

        for(let aCustomer of consultantCustomers) {
          consultantCustomer.customers.push(aCustomer);
        }

        if(consultantCustomer.customers.length > 0) {
          this.consultantCustomerService.add(consultantCustomer);
        }

        this.mirrorData(consultant, consultantCustomers);
      }).catch((exception) => {
        let consultantCustomer: ConsultantCustomer = new ConsultantCustomer();
        consultantCustomer.consultantId = this.consultantId;
        consultantCustomer.customers = consultantCustomers;
        this.consultantCustomerService.add(consultantCustomer);

        this.mirrorData(consultant, consultantCustomers);
      });

      if(this.currentUpload) {
        consultant.profilePicture = this.currentUpload;

        if(this.previousUpload) {
          let basePath = "/consultants";
          let databasePath = basePath + '/' + this.consultantId + '/profilePicture';
          this.uploadService.deleteUpload(this.previousUpload, basePath, databasePath, null);
        }

        this.consultantService.update(this.consultantId, consultant);
        this.uploadSingle(this.consultant.id);
      } else if(this.previousUpload) {
        this.consultantService.update(this.consultantId, consultant);
        this.updateUploadedData(this.consultantId);
        this.router.navigate(['/consultants']);
        this.consultantService.refresh();

      } else {
        this.consultantService.update(this.consultantId, consultant);
        this.router.navigate(['/consultants']);
        this.consultantService.refresh();
      }

    } else {
      this.consultantId = this.consultantService.add(consultant);

      if(consultantCustomers && consultantCustomers.length > 0) {
        let newConsultantCustomer: ConsultantCustomer = new ConsultantCustomer();
        newConsultantCustomer.consultantId = this.consultantId;
        newConsultantCustomer.customers = [];

        for(let newCustomer of consultantCustomers) {
          newConsultantCustomer.customers.push(newCustomer);
        }

        this.consultantCustomerService.add(newConsultantCustomer);

        for(let newCustomer of consultantCustomers) {
          consultant.id = this.consultantId;

          this.customerConsultantService.get(newCustomer.id).then((customerConsultant) => {
            this.customerConsultantService.addConsultant(newCustomer.id, consultant);
          }).catch((exception) => {
            let customerConsultant: CustomerConsultant = new CustomerConsultant();
            customerConsultant.customerId = newCustomer.id;
            customerConsultant.consultants = [];
            customerConsultant.consultants.push(consultant);
            this.customerConsultantService.add(customerConsultant);
          });
        }
      }

      if(this.currentUpload) {
        this.uploadSingle(this.consultantId);
      } else {
        this.router.navigate(['/consultants']);
        this.consultantService.refresh();
      }
    }

  }

  onAddControl(control: string) {
    (<FormArray> this.consultantForm.get(control)).push(
      new FormGroup({
        'name': new FormControl(null)
      })
    );
  }

  onDeleteControl(control: string, index: number) {
    (<FormArray> this.consultantForm.get(control)).removeAt(index);
  }

  detectFiles(event) {
    this.selectedFiles = event.target.files;
    let file = this.selectedFiles.item(0);
    this.currentUpload = new Upload(file);
  }

  uploadSingle(id: string) {
    let basePath = "/consultants";
    let databasePath = basePath + '/' + id + '/profilePicture';

    this.uploadService.pushUpload(this.currentUpload, basePath, databasePath, '/consultants', this.consultantService);
  }

  updateUploadedData(id: string) {
    let basePath = "/consultants";
    let databasePath = basePath + '/' + id + '/profilePicture';

    this.uploadService.saveFileData(this.previousUpload, databasePath);
  }

  mirrorData(consultant: Consultant, consultantCustomers: Customer[]) {
    let found: boolean = false;
    let customersToRemove: Customer[] = [];

    for(let previousCustomer of this.currentCustomers) {
      found = false;

      for(let newCustomer of consultantCustomers) {

        if(previousCustomer.id == newCustomer.id) {
          found = true;
        }

      }

      if(!found) {
        customersToRemove.push(previousCustomer);
      }

    }

    found = false;
    let customersToAdd: Customer[] = [];

    for(let newCustomer of consultantCustomers) {
      found = false;

      for(let previousCustomer of this.currentCustomers) {

        if(newCustomer.id == previousCustomer.id) {
          found = true;
        }

      }

      if(!found) {
        customersToAdd.push(newCustomer);
      }

    }

    consultant.id = this.consultantId;

    for(let newCustomer of customersToAdd) {
      this.customerConsultantService.get(newCustomer.id).then((customerConsultant) => {
        this.customerConsultantService.addConsultant(newCustomer.id, consultant);
      }).catch((exception) => {
        let customerConsultant: CustomerConsultant = new CustomerConsultant();
        customerConsultant.customerId = newCustomer.id;
        customerConsultant.consultants = [];
        customerConsultant.consultants.push(consultant);
        this.customerConsultantService.add(customerConsultant);
      });
    }

    for(let customerToRemove of customersToRemove) {
      this.customerConsultantService.removeConsultant(customerToRemove.id, consultant);
    }
  }

  getConsultantService() {
    return this.consultantService;
  }
}
