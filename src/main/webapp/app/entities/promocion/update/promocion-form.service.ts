import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IPromocion, NewPromocion } from '../promocion.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IPromocion for edit and NewPromocionFormGroupInput for create.
 */
type PromocionFormGroupInput = IPromocion | PartialWithRequiredKeyOf<NewPromocion>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IPromocion | NewPromocion> = Omit<T, 'fechaInicio' | 'fechaFin'> & {
  fechaInicio?: string | null;
  fechaFin?: string | null;
};

type PromocionFormRawValue = FormValueOf<IPromocion>;

type NewPromocionFormRawValue = FormValueOf<NewPromocion>;

type PromocionFormDefaults = Pick<NewPromocion, 'id' | 'fechaInicio' | 'fechaFin'>;

type PromocionFormGroupContent = {
  id: FormControl<PromocionFormRawValue['id'] | NewPromocion['id']>;
  nombre: FormControl<PromocionFormRawValue['nombre']>;
  descripcion: FormControl<PromocionFormRawValue['descripcion']>;
  porcentajeDescuento: FormControl<PromocionFormRawValue['porcentajeDescuento']>;
  fechaInicio: FormControl<PromocionFormRawValue['fechaInicio']>;
  fechaFin: FormControl<PromocionFormRawValue['fechaFin']>;
  tipoPromocion: FormControl<PromocionFormRawValue['tipoPromocion']>;
  establecimientoId: FormControl<PromocionFormRawValue['establecimientoId']>;
  productoId: FormControl<PromocionFormRawValue['productoId']>;
  servicioId: FormControl<PromocionFormRawValue['servicioId']>;
};

export type PromocionFormGroup = FormGroup<PromocionFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class PromocionFormService {
  createPromocionFormGroup(promocion: PromocionFormGroupInput = { id: null }): PromocionFormGroup {
    const promocionRawValue = this.convertPromocionToPromocionRawValue({
      ...this.getFormDefaults(),
      ...promocion,
    });
    return new FormGroup<PromocionFormGroupContent>({
      id: new FormControl(
        { value: promocionRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      nombre: new FormControl(promocionRawValue.nombre),
      descripcion: new FormControl(promocionRawValue.descripcion),
      porcentajeDescuento: new FormControl(promocionRawValue.porcentajeDescuento),
      fechaInicio: new FormControl(promocionRawValue.fechaInicio),
      fechaFin: new FormControl(promocionRawValue.fechaFin),
      tipoPromocion: new FormControl(promocionRawValue.tipoPromocion),
      establecimientoId: new FormControl(promocionRawValue.establecimientoId),
      productoId: new FormControl(promocionRawValue.productoId),
      servicioId: new FormControl(promocionRawValue.servicioId),
    });
  }

  getPromocion(form: PromocionFormGroup): IPromocion | NewPromocion {
    return this.convertPromocionRawValueToPromocion(form.getRawValue() as PromocionFormRawValue | NewPromocionFormRawValue);
  }

  resetForm(form: PromocionFormGroup, promocion: PromocionFormGroupInput): void {
    const promocionRawValue = this.convertPromocionToPromocionRawValue({ ...this.getFormDefaults(), ...promocion });
    form.reset(
      {
        ...promocionRawValue,
        id: { value: promocionRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): PromocionFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      fechaInicio: currentTime,
      fechaFin: currentTime,
    };
  }

  private convertPromocionRawValueToPromocion(rawPromocion: PromocionFormRawValue | NewPromocionFormRawValue): IPromocion | NewPromocion {
    return {
      ...rawPromocion,
      fechaInicio: dayjs(rawPromocion.fechaInicio, DATE_TIME_FORMAT),
      fechaFin: dayjs(rawPromocion.fechaFin, DATE_TIME_FORMAT),
    };
  }

  private convertPromocionToPromocionRawValue(
    promocion: IPromocion | (Partial<NewPromocion> & PromocionFormDefaults),
  ): PromocionFormRawValue | PartialWithRequiredKeyOf<NewPromocionFormRawValue> {
    return {
      ...promocion,
      fechaInicio: promocion.fechaInicio ? promocion.fechaInicio.format(DATE_TIME_FORMAT) : undefined,
      fechaFin: promocion.fechaFin ? promocion.fechaFin.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
