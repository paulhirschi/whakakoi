import { Component, OnInit } from '@angular/core'
import { Location } from '@angular/common'
import { Router } from '@angular/router'
import { Device } from '../models/device'
import { AuthService } from '../services/auth.service'
import 'rxjs/add/operator/map'

@Component({
//  moduleId: module.id,
  // selector: 'my-dashboard',
  templateUrl: 'assets/templates/login/login.component.html',
  styleUrls: [ 'assets/stylesheets/login.component.css' ]
})
export class LoginComponent implements OnInit {
  private year: number = new Date().getFullYear()

  constructor(
    private authService: AuthService,
    private router: Router
    ) {}

    public ngOnInit(): void {
      return
    }

    // public getCredentials(): any {
    //   this.loginWorking = true
    //   this.authService.login()
    //   .subscribe(
    //     response => {
    //       this.authService.setCredentials(response)
    //       setTimeout(() => {
    //         this.router.navigate(['dashboard'])
    //       }, 4000)
    //     },
    //     error => console.error(`Error: ${error}`)
    //   )
    // }

  // public toDashboard(): void {
  //   this.router.navigate(['dashboard'])
  // }
}
