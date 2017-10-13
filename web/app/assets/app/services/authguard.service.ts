import { Injectable } from '@angular/core'
import { Router, CanActivate } from '@angular/router'
import { ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router'
import { AuthService } from './auth.service'

@Injectable()
export class AuthGuard implements CanActivate {

  constructor(private authService: AuthService,
              private router: Router) {}

  public canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    if (this.authService.hasCredentials()) {
      return true
    } else {
      this.router.navigate(['login'])
      return false
    }
  }

}
