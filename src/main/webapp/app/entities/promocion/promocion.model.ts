import dayjs from 'dayjs/esm';

export interface IPromocion {
  id: number;
  nombre?: string | null;
  descripcion?: string | null;
  porcentajeDescuento?: number | null;
  fechaInicio?: dayjs.Dayjs | null;
  fechaFin?: dayjs.Dayjs | null;
  tipoPromocion?: string | null;
  establecimientoId?: number | null;
  productoId?: number | null;
  servicioId?: number | null;
}

export type NewPromocion = Omit<IPromocion, 'id'> & { id: null };
