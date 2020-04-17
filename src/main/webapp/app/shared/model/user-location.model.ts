export interface IUserLocation {
  id?: string;
  userId?: string;
  latitude?: number;
  longitude?: number;
  locationName?: string;
}

export const defaultValue: Readonly<IUserLocation> = {};
