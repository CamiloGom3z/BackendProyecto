import { IServicios, NewServicios } from './servicios.model';

export const sampleWithRequiredData: IServicios = {
  id: 28977,
};

export const sampleWithPartialData: IServicios = {
  id: 4732,
  valorServicio: 27696.98,
  establecimientoId: 26650,
};

export const sampleWithFullData: IServicios = {
  id: 15233,
  valorServicio: 25694.11,
  tipoServicio: 'why procurement yuppify',
  descripcion: 'pro only',
  establecimientoId: 28683,
};

export const sampleWithNewData: NewServicios = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
