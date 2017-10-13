import { Component, OnInit, ViewChildren } from '@angular/core'
import { Router } from '@angular/router'
import { Device } from '../models/device'
import { ApiService } from '../services/api.service'
// import 'rxjs/add/operator/map'

@Component({
  //  moduleId: module.id,
  selector: 'devicelist',
  templateUrl: 'assets/templates/dashboard/devicelist.component.html',
  styleUrls: ['assets/stylesheets/dashboard.component.css']
})

export class DeviceListComponent implements OnInit {

  private device: Device
  private devices: Device[]
  private customerNames: any[]
  private requestError: string = ''
  private selectCustomer: string = 'All'
  private filter = {serialNumber: ''}
  @ViewChildren('numDevices') private filteredList: any
  constructor(
    private apiService: ApiService,
    private router: Router
    ) {}

    public ngOnInit(): void {
      this.getDevices()
    }

    public getDevices(): any {
      this.apiService.getDevices()
        .subscribe(
          response => {
            this.devices = response.sort(this.numericSortBySerial)
            this.customerNames = this.devices
              .map(device => {return device.customer.name})
              .filter(this.uniqueValues)
          },
          error => {
            let requestErrorMessage
            if (error._body) requestErrorMessage = error.json()
            this.requestError = `${error.status}: ${requestErrorMessage.message}`
            console.error(error)
          }
        )
    }

    private uniqueValues(value: any, index: any, self: string[]): boolean {
      return self.indexOf(value) === index
    }

    private filterDeviceByCustomerName(device: Device): any {
      if (this.selectCustomer === 'All') return true
      else return device.customer.name === this.selectCustomer ? true : false
    }

    private numericSortBySerial(a: Device, b: Device): any {
      if (a.serialNumber < b.serialNumber) return -1
      if (a.serialNumber > b.serialNumber) return 1
      return 0
    }

    private selectCustomerValue(v: string): any {
      this.selectCustomer = v
    }

}
