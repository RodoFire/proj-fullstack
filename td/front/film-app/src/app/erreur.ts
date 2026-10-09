import { HttpErrorResponse } from '@angular/common/http';

export function messageErreur(err: HttpErrorResponse): string {
  if (err.status === 0) return 'API injoignable, vérifiez que le backend est démarré';
  if (err.status === 400) return 'Données invalides';
  if (err.status === 404 && err.error) return err.error;
  return 'Une erreur est survenue (' + err.status + ')';
}
