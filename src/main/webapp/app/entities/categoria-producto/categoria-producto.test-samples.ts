import { ICategoriaProducto, NewCategoriaProducto } from './categoria-producto.model';

export const sampleWithRequiredData: ICategoriaProducto = {
  id: 15799,
};

export const sampleWithPartialData: ICategoriaProducto = {
  id: 32430,
};

export const sampleWithFullData: ICategoriaProducto = {
  id: 27219,
  nombre: 'at seriously',
  establecimientoId: 10951,
};

export const sampleWithNewData: NewCategoriaProducto = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
