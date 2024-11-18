import dayjs from 'dayjs/esm';

import { ICita, NewCita } from './cita.model';

export const sampleWithRequiredData: ICita = {
  id: 19920,
};

export const sampleWithPartialData: ICita = {
  id: 22272,
  establecimientoId: 5162,
  servicioId: 26449,
};

export const sampleWithFullData: ICita = {
  id: 15853,
  fechaCita: dayjs('2024-11-08T11:36'),
  fechaFinCita: dayjs('2024-11-08T10:38'),
  estado: 'OTRO',
  personaId: 27750,
  establecimientoId: 16243,
  servicioId: 440,
};

export const sampleWithNewData: NewCita = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
