import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable, map } from 'rxjs';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IPromocion, NewPromocion } from '../promocion.model';

export type PartialUpdatePromocion = Partial<IPromocion> & Pick<IPromocion, 'id'>;

type RestOf<T extends IPromocion | NewPromocion> = Omit<T, 'fechaInicio' | 'fechaFin'> & {
  fechaInicio?: string | null;
  fechaFin?: string | null;
};

export type RestPromocion = RestOf<IPromocion>;

export type NewRestPromocion = RestOf<NewPromocion>;

export type PartialUpdateRestPromocion = RestOf<PartialUpdatePromocion>;

export type EntityResponseType = HttpResponse<IPromocion>;
export type EntityArrayResponseType = HttpResponse<IPromocion[]>;

@Injectable({ providedIn: 'root' })
export class PromocionService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/promocions');

  create(promocion: NewPromocion): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(promocion);
    return this.http
      .post<RestPromocion>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(promocion: IPromocion): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(promocion);
    return this.http
      .put<RestPromocion>(`${this.resourceUrl}/${this.getPromocionIdentifier(promocion)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(promocion: PartialUpdatePromocion): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(promocion);
    return this.http
      .patch<RestPromocion>(`${this.resourceUrl}/${this.getPromocionIdentifier(promocion)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestPromocion>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestPromocion[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getPromocionIdentifier(promocion: Pick<IPromocion, 'id'>): number {
    return promocion.id;
  }

  comparePromocion(o1: Pick<IPromocion, 'id'> | null, o2: Pick<IPromocion, 'id'> | null): boolean {
    return o1 && o2 ? this.getPromocionIdentifier(o1) === this.getPromocionIdentifier(o2) : o1 === o2;
  }

  addPromocionToCollectionIfMissing<Type extends Pick<IPromocion, 'id'>>(
    promocionCollection: Type[],
    ...promocionsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const promocions: Type[] = promocionsToCheck.filter(isPresent);
    if (promocions.length > 0) {
      const promocionCollectionIdentifiers = promocionCollection.map(promocionItem => this.getPromocionIdentifier(promocionItem));
      const promocionsToAdd = promocions.filter(promocionItem => {
        const promocionIdentifier = this.getPromocionIdentifier(promocionItem);
        if (promocionCollectionIdentifiers.includes(promocionIdentifier)) {
          return false;
        }
        promocionCollectionIdentifiers.push(promocionIdentifier);
        return true;
      });
      return [...promocionsToAdd, ...promocionCollection];
    }
    return promocionCollection;
  }

  protected convertDateFromClient<T extends IPromocion | NewPromocion | PartialUpdatePromocion>(promocion: T): RestOf<T> {
    return {
      ...promocion,
      fechaInicio: promocion.fechaInicio?.toJSON() ?? null,
      fechaFin: promocion.fechaFin?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restPromocion: RestPromocion): IPromocion {
    return {
      ...restPromocion,
      fechaInicio: restPromocion.fechaInicio ? dayjs(restPromocion.fechaInicio) : undefined,
      fechaFin: restPromocion.fechaFin ? dayjs(restPromocion.fechaFin) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestPromocion>): HttpResponse<IPromocion> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestPromocion[]>): HttpResponse<IPromocion[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
