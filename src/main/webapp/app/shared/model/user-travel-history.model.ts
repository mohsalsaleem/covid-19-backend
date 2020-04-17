import { Moment } from 'moment';

export interface IUserTravelHistory {
  id?: string;
  userId?: string;
  locationName?: string;
  latitude?: number;
  longitude?: number;
  dateAndTimeOfTravel?: Moment;
}

export const defaultValue: Readonly<IUserTravelHistory> = {};
