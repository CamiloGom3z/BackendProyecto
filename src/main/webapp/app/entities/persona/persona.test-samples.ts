import { IPersona, NewPersona } from './persona.model';

export const sampleWithRequiredData: IPersona = {
  id: 3568,
};

export const sampleWithPartialData: IPersona = {
  id: 32634,
  nombre: 'regarding',
  userId: 14901,
};

export const sampleWithFullData: IPersona = {
  id: 17886,
  nombre: 'below',
  apellido: 'since',
  urlImg: 'huge pleased patiently',
  userId: 20528,
};

export const sampleWithNewData: NewPersona = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
