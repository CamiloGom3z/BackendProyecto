import { IUser } from './user.model';

export const sampleWithRequiredData: IUser = {
  id: 6885,
  login: 'C^nbk@cR\\$hIT\\Ex',
};

export const sampleWithPartialData: IUser = {
  id: 25959,
  login: 'nI@w',
};

export const sampleWithFullData: IUser = {
  id: 24513,
  login: 'M7',
};
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
