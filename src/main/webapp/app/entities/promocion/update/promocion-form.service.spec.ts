import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../promocion.test-samples';

import { PromocionFormService } from './promocion-form.service';

describe('Promocion Form Service', () => {
  let service: PromocionFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PromocionFormService);
  });

  describe('Service methods', () => {
    describe('createPromocionFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createPromocionFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            nombre: expect.any(Object),
            descripcion: expect.any(Object),
            porcentajeDescuento: expect.any(Object),
            fechaInicio: expect.any(Object),
            fechaFin: expect.any(Object),
            tipoPromocion: expect.any(Object),
            establecimientoId: expect.any(Object),
            productoId: expect.any(Object),
            servicioId: expect.any(Object),
          }),
        );
      });

      it('passing IPromocion should create a new form with FormGroup', () => {
        const formGroup = service.createPromocionFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            nombre: expect.any(Object),
            descripcion: expect.any(Object),
            porcentajeDescuento: expect.any(Object),
            fechaInicio: expect.any(Object),
            fechaFin: expect.any(Object),
            tipoPromocion: expect.any(Object),
            establecimientoId: expect.any(Object),
            productoId: expect.any(Object),
            servicioId: expect.any(Object),
          }),
        );
      });
    });

    describe('getPromocion', () => {
      it('should return NewPromocion for default Promocion initial value', () => {
        const formGroup = service.createPromocionFormGroup(sampleWithNewData);

        const promocion = service.getPromocion(formGroup) as any;

        expect(promocion).toMatchObject(sampleWithNewData);
      });

      it('should return NewPromocion for empty Promocion initial value', () => {
        const formGroup = service.createPromocionFormGroup();

        const promocion = service.getPromocion(formGroup) as any;

        expect(promocion).toMatchObject({});
      });

      it('should return IPromocion', () => {
        const formGroup = service.createPromocionFormGroup(sampleWithRequiredData);

        const promocion = service.getPromocion(formGroup) as any;

        expect(promocion).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IPromocion should not enable id FormControl', () => {
        const formGroup = service.createPromocionFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewPromocion should disable id FormControl', () => {
        const formGroup = service.createPromocionFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
