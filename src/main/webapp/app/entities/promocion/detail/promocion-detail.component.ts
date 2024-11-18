import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatePipe, FormatMediumDatetimePipe } from 'app/shared/date';
import { IPromocion } from '../promocion.model';

@Component({
  standalone: true,
  selector: 'jhi-promocion-detail',
  templateUrl: './promocion-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class PromocionDetailComponent {
  promocion = input<IPromocion | null>(null);

  previousState(): void {
    window.history.back();
  }
}
