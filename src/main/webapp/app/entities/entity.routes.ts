import { Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'authority',
    data: { pageTitle: 'backendStyleSuiteApp.adminAuthority.home.title' },
    loadChildren: () => import('./admin/authority/authority.routes'),
  },
  {
    path: 'categoria-producto',
    data: { pageTitle: 'backendStyleSuiteApp.categoriaProducto.home.title' },
    loadChildren: () => import('./categoria-producto/categoria-producto.routes'),
  },
  {
    path: 'cita',
    data: { pageTitle: 'backendStyleSuiteApp.cita.home.title' },
    loadChildren: () => import('./cita/cita.routes'),
  },
  {
    path: 'empleado',
    data: { pageTitle: 'backendStyleSuiteApp.empleado.home.title' },
    loadChildren: () => import('./empleado/empleado.routes'),
  },
  {
    path: 'establecimiento',
    data: { pageTitle: 'backendStyleSuiteApp.establecimiento.home.title' },
    loadChildren: () => import('./establecimiento/establecimiento.routes'),
  },
  {
    path: 'galeria-imagen',
    data: { pageTitle: 'backendStyleSuiteApp.galeriaImagen.home.title' },
    loadChildren: () => import('./galeria-imagen/galeria-imagen.routes'),
  },
  {
    path: 'pago',
    data: { pageTitle: 'backendStyleSuiteApp.pago.home.title' },
    loadChildren: () => import('./pago/pago.routes'),
  },
  {
    path: 'persona',
    data: { pageTitle: 'backendStyleSuiteApp.persona.home.title' },
    loadChildren: () => import('./persona/persona.routes'),
  },
  {
    path: 'producto',
    data: { pageTitle: 'backendStyleSuiteApp.producto.home.title' },
    loadChildren: () => import('./producto/producto.routes'),
  },
  {
    path: 'promocion',
    data: { pageTitle: 'backendStyleSuiteApp.promocion.home.title' },
    loadChildren: () => import('./promocion/promocion.routes'),
  },
  {
    path: 'servicios',
    data: { pageTitle: 'backendStyleSuiteApp.servicios.home.title' },
    loadChildren: () => import('./servicios/servicios.routes'),
  },
  /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
];

export default routes;
