import { Injectable } from '@angular/core'
import { Http } from '@angular/http'
import { Router } from '@angular/router'
import { Observable } from 'rxjs/Observable'
import { Device } from '../models/device'
import 'rxjs/add/operator/map'
import 'rxjs/add/operator/catch'
import 'rxjs/add/observable/of'
import 'rxjs/add/operator/toPromise'

@Injectable()
export class AuthService {
  private loginUrl: string = '/auth/authenticate'
  constructor(
    private http: Http,
    private router: Router
    ) {}

  public login(creds: {}): Observable<any> {
    return this.http.post(this.loginUrl, JSON.stringify(creds))
      .map(response => response.json())
      // .catch((error: any) => Observable.throw(error || 'Server error'))
  }

  public logout() {
    // this.router.navigate(['login'])
    this.setCredentials()
  }

  public setCredentials(credentials?: any) {
    let creds = JSON.stringify(credentials)
    if (credentials) sessionStorage.setItem('credentials', creds)
    else sessionStorage.removeItem('credentials')
  }

  public getCredentials() {
    let creds = sessionStorage.getItem('credentials')
    return JSON.parse(creds)
  }

  public hasCredentials(): boolean {
    if (this.getCredentials()) return true
    else return false
  }

}
