export interface IPersona {
  id: number;
  nombre?: string | null;
  apellido?: string | null;
  urlImg?: string | null;
  userId?: number | null;
}

export type NewPersona = Omit<IPersona, 'id'> & { id: null };
