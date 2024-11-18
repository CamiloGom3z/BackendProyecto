import { IGaleriaImagen, NewGaleriaImagen } from './galeria-imagen.model';

export const sampleWithRequiredData: IGaleriaImagen = {
  id: 16229,
};

export const sampleWithPartialData: IGaleriaImagen = {
  id: 4451,
  nombre: 'blaspheme until yum',
};

export const sampleWithFullData: IGaleriaImagen = {
  id: 8290,
  nombre: 'likewise who geez',
  descripcion: 'dental dearly',
  urlImagen: 'petticoat reporter boo',
  establecimientoId: 25275,
};

export const sampleWithNewData: NewGaleriaImagen = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
