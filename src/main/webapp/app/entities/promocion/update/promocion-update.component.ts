import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IPromocion } from '../promocion.model';
import { PromocionService } from '../service/promocion.service';
import { PromocionFormGroup, PromocionFormService } from './promocion-form.service';

@Component({
  standalone: true,
  selector: 'jhi-promocion-update',
  templateUrl: './promocion-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class PromocionUpdateComponent implements OnInit {
  isSaving = false;
  promocion: IPromocion | null = null;

  protected promocionService = inject(PromocionService);
  protected promocionFormService = inject(PromocionFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: PromocionFormGroup = this.promocionFormService.createPromocionFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ promocion }) => {
      this.promocion = promocion;
      if (promocion) {
        this.updateForm(promocion);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const promocion = this.promocionFormService.getPromocion(this.editForm);
    if (promocion.id !== null) {
      this.subscribeToSaveResponse(this.promocionService.update(promocion));
    } else {
      this.subscribeToSaveResponse(this.promocionService.create(promocion));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPromocion>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(promocion: IPromocion): void {
    this.promocion = promocion;
    this.promocionFormService.resetForm(this.editForm, promocion);
  }
}
