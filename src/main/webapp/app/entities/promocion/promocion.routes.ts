import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import PromocionResolve from './route/promocion-routing-resolve.service';

const promocionRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/promocion.component').then(m => m.PromocionComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () => import('./detail/promocion-detail.component').then(m => m.PromocionDetailComponent),
    resolve: {
      promocion: PromocionResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () => import('./update/promocion-update.component').then(m => m.PromocionUpdateComponent),
    resolve: {
      promocion: PromocionResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () => import('./update/promocion-update.component').then(m => m.PromocionUpdateComponent),
    resolve: {
      promocion: PromocionResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default promocionRoute;
