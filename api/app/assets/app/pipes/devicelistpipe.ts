import { Pipe, PipeTransform } from '@angular/core'

@Pipe({
  name: 'devicelistfilter',
  pure: false
})
export class DeviceListPipe implements PipeTransform {
  public transform(devices: any[], filter: any): any[] {
    if (!devices || !filter) return devices
    // return devices.filter(device => device.serialNumber === filter.serialNumber.toUpperCase())
    return devices.filter(device => device.serialNumber.indexOf(filter.serialNumber.toUpperCase()) !== -1)
  }
}
