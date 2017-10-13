import { Component, OnInit } from '@angular/core'
import { ActivatedRoute } from '@angular/router'
import { Location } from '@angular/common'
import { Device } from '../models/device'
import { ApiService } from '../services/api.service'
import { AuthService } from '../services/auth.service'
import 'rxjs/add/operator/map'
import 'rxjs/add/operator/switchMap'

@Component({
  //  moduleId: module.id,
  selector: 'devicedetail',
  templateUrl: 'assets/templates/dashboard/devicedetail.component.html',
  styleUrls: ['assets/stylesheets/dashboard.component.css']
})

export class DeviceDetailComponent implements OnInit {

  public device: Device
  public devices: Device[]
  private pageTitle: string = 'Dashboard'
  private sub: any
  constructor(
    private apiService: ApiService,
    private authService: AuthService,
    private route: ActivatedRoute,
    private location: Location
    ) {}
    public ngOnInit() {
      // console.warn(`devicedetail oninit: ${this.route.params.toString()}`)
      this.sub = this.route.params.subscribe(params => { // tslint:disable-next-line:no-string-literal
        let id: number = +params['id'] // tslint:disable-next-line:no-string-literal
        if (id) this.getDevice(id)
      },
      error => console.error('error')
      )
      // this.routes.params
      //   .switchMap((params: Params) => this.apiService.getDevice(+params['id']))
      //   .subscribe(device => this.device = device)
    }

    private getDevice(id: number) {
      this.apiService.getDevice(id)
        .subscribe(
          device => this.device = device,
          error => console.warn(error)
        )
    }

    private logout() {
      this.authService.logout()
    }

}
