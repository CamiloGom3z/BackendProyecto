import { IEmpleado, NewEmpleado } from './empleado.model';

export const sampleWithRequiredData: IEmpleado = {
  id: 17953,
};

export const sampleWithPartialData: IEmpleado = {
  id: 568,
  nombre: 'ah',
  apellido: 'stall before',
  salario: 4755.45,
};

export const sampleWithFullData: IEmpleado = {
  id: 32007,
  nombre: 'scarcely than',
  apellido: 'duh solution mostly',
  cargo: 'fuzzy investigate aw',
  salario: 1899.83,
  establecimientoId: 7165,
};

export const sampleWithNewData: NewEmpleado = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
