import { Owner } from './owner.model';
import { Manager } from './manager.model';
import { ConsultantReferring } from './consultant-referring.model';
import { Area } from './area.model';
import { Upload } from '../shared/upload.model';

export class Customer {
  id: string;
  name: string;
  type: string;
  activity: string;
  seniority: number;
  history: string;
  employeesQuantity: number;
  owners: Owner[];
  managers: Manager[];
  consultantReferrings: ConsultantReferring[];
  interventionAreas: Area[];
  consultationReason: string;
  diagnosisSummary: string;
  interviews: Upload;
  anotherInformation: string;
}
