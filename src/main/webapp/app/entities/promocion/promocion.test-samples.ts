import dayjs from 'dayjs/esm';

import { IPromocion, NewPromocion } from './promocion.model';

export const sampleWithRequiredData: IPromocion = {
  id: 26175,
};

export const sampleWithPartialData: IPromocion = {
  id: 9151,
  descripcion: 'breed over premeditation',
  porcentajeDescuento: 930.7,
  fechaInicio: dayjs('2024-11-08T14:56'),
  fechaFin: dayjs('2024-11-08T10:40'),
  establecimientoId: 18943,
  servicioId: 14978,
};

export const sampleWithFullData: IPromocion = {
  id: 21063,
  nombre: 'jittery aggressive detective',
  descripcion: 'eek paltry',
  porcentajeDescuento: 959.28,
  fechaInicio: dayjs('2024-11-08T03:50'),
  fechaFin: dayjs('2024-11-08T15:40'),
  tipoPromocion: 'deployment',
  establecimientoId: 29015,
  productoId: 7898,
  servicioId: 29848,
};

export const sampleWithNewData: NewPromocion = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
