export interface IEmpleado {
  id: number;
  nombre?: string | null;
  apellido?: string | null;
  cargo?: string | null;
  salario?: number | null;
  establecimientoId?: number | null;
}

export type NewEmpleado = Omit<IEmpleado, 'id'> & { id: null };
