import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IPromocion } from '../promocion.model';
import { PromocionService } from '../service/promocion.service';

const promocionResolve = (route: ActivatedRouteSnapshot): Observable<null | IPromocion> => {
  const id = route.params.id;
  if (id) {
    return inject(PromocionService)
      .find(id)
      .pipe(
        mergeMap((promocion: HttpResponse<IPromocion>) => {
          if (promocion.body) {
            return of(promocion.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default promocionResolve;
