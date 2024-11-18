import dayjs from 'dayjs/esm';

import { IPago, NewPago } from './pago.model';

export const sampleWithRequiredData: IPago = {
  id: 2573,
};

export const sampleWithPartialData: IPago = {
  id: 7915,
  estado: 'pish',
};

export const sampleWithFullData: IPago = {
  id: 16905,
  monto: 6183.28,
  fechaPago: dayjs('2024-11-08T16:33'),
  metodoPago: 'TRANSFERENCIA',
  estado: 'innocently oh whoa',
  citaId: 17851,
};

export const sampleWithNewData: NewPago = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
