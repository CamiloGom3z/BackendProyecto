import dayjs from 'dayjs/esm';
import { EstadoCitaEnum } from 'app/entities/enumerations/estado-cita-enum.model';

export interface ICita {
  id: number;
  fechaCita?: dayjs.Dayjs | null;
  fechaFinCita?: dayjs.Dayjs | null;
  estado?: keyof typeof EstadoCitaEnum | null;
  personaId?: number | null;
  establecimientoId?: number | null;
  servicioId?: number | null;
}

export type NewCita = Omit<ICita, 'id'> & { id: null };
