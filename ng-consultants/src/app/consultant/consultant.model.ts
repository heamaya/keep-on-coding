import { Profession } from './profession.model';
import { WorkExperienceArea } from './work-experience-area.model';
import { WorkExperienceCompany } from './work-experience-company.model';
import { WorkPosition } from './work-position.model';
import { Specialty } from './specialty.model';
import { Upload } from './../shared/upload.model';
import { Roles } from './roles.model';

export class Consultant {
  id: string;
  firstName: string;
  lastName: string;
  name: string;
  cellphone: string;
  email: string;
  consultancyParticipationReason: string;
  availabilityFrom: string;
  availabilityTo: string;
  professions: Profession[];
  workExperienceAreas: WorkExperienceArea[];
  workExperienceCompanies: WorkExperienceCompany[];
  workPositions: WorkPosition[];
  specialties: Specialty[];
  profilePicture: Upload;
  roles: Roles;
}
