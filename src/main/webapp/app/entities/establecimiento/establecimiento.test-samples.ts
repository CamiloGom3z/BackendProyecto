import { IEstablecimiento, NewEstablecimiento } from './establecimiento.model';

export const sampleWithRequiredData: IEstablecimiento = {
  id: 12377,
};

export const sampleWithPartialData: IEstablecimiento = {
  id: 12314,
  direccion: 'beside geez blah',
  urlImg: 'since',
  userId: 28061,
};

export const sampleWithFullData: IEstablecimiento = {
  id: 30785,
  nombre: 'procurement equate',
  direccion: 'forage',
  telefono: 'hungrily allegation polite',
  correoElectronico: 'rawhide wheel',
  urlImg: 'presell despite buzzing',
  userId: 8719,
};

export const sampleWithNewData: NewEstablecimiento = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
