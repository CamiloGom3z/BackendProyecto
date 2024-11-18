import { IProducto, NewProducto } from './producto.model';

export const sampleWithRequiredData: IProducto = {
  id: 1225,
};

export const sampleWithPartialData: IProducto = {
  id: 7457,
  descripcion: 'gadzooks graceful investigate',
  urlImg: 'hence',
  establecimientoId: 16977,
};

export const sampleWithFullData: IProducto = {
  id: 17315,
  nombre: 'drat honorable elevation',
  descripcion: 'although ugh qua',
  precio: 28758.78,
  cantidad: 1978,
  urlImg: 'necklace',
  categoriaProductoId: 10287,
  establecimientoId: 507,
};

export const sampleWithNewData: NewProducto = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
