import { Injectable }                    from '@angular/core'
import { Http, Headers, RequestOptions } from '@angular/http'
import { ActivatedRoute, Params }        from '@angular/router'
import { Observable }                    from 'rxjs/Observable'
import { Device }                        from '../models/device'
import 'rxjs/add/operator/map'
import 'rxjs/add/observable/of'
import 'rxjs/add/operator/toPromise'

@Injectable()
export class ApiService {
  // private baseUrl: string = 'https://tu2ye61vyg.execute-api.us-west-2.amazonaws.com/dev'
  private getDeviceUrl: string = '/api/device/'
  private getDevicesUrl: string = '/api/devices'
  private creds: any = JSON.parse(sessionStorage.getItem('credentials'))
  private headers: Headers = new Headers({ 'Authorization': this.creds.token,
                                           'x-api-key': this.creds.apiKey })
  constructor(private http: Http,
              private route: ActivatedRoute) { }

  public getDevices(): Observable<any> {
    let options = new RequestOptions({ headers: this.headers })
    return this.http.get(`${this.getDevicesUrl}`, options)
      .map(response => response.json())
  }

  public getDevice(id: number): Observable<any> {
    let options = new RequestOptions({ headers: this.headers })
    return this.http.get(`${this.getDeviceUrl}${id}`, options)
      .map(response => response.json())
  }

}
