import { UserHealthSeverity } from 'app/shared/model/enumerations/user-health-severity.model';

export interface IUserHealthStatus {
  id?: string;
  userId?: string;
  severity?: UserHealthSeverity;
}

export const defaultValue: Readonly<IUserHealthStatus> = {};
