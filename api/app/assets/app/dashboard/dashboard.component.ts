import { Component, OnInit } from '@angular/core'
import { Device } from '../models/device'
import { ApiService } from '../services/api.service'
import 'rxjs/add/operator/map'

@Component({
  //  moduleId: module.id,
  selector: 'my-dashboard',
  templateUrl: 'assets/templates/dashboard/dashboard.component.html',
  styleUrls: ['assets/stylesheets/dashboard.component.css']
})

export class DashboardComponent implements OnInit {

  public device: Device
  public devices: Device[]
  private pageTitle: string = 'Dashboard'
  constructor(
    private apiService: ApiService
    ) {}
    public ngOnInit(): void {
      return
    }

}
