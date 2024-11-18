import { IAuthority, NewAuthority } from './authority.model';

export const sampleWithRequiredData: IAuthority = {
  name: 'b4268543-2a2b-48d4-a7cf-e9e5c2178baf',
};

export const sampleWithPartialData: IAuthority = {
  name: 'dd23a053-daad-42aa-ba00-b055b4e740f1',
};

export const sampleWithFullData: IAuthority = {
  name: '5985d4cc-891b-478e-98ee-b9f6e6afccee',
};

export const sampleWithNewData: NewAuthority = {
  name: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
