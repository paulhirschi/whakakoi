import { Component, OnInit, Input, trigger, state, style, transition, animate } from '@angular/core'
import { Location } from '@angular/common'
import { Router } from '@angular/router'
import { AuthService } from '../services/auth.service'

import { routeTransitionFadeInOut } from '../animations/animations'

@Component({
//  moduleId: module.id,
  // selector: 'my-dashboard',
  // templateUrl: 'assets/templates/login/login.component.loginform.html',
  templateUrl: 'assets/templates/login/login.component.loginform.html',
  styleUrls: [ 'assets/stylesheets/login.component.css' ]
  // animations: [routeTransitionFadeInOut()],
  // host: {'[@fadeInOut]': ''},
})

export class LoginForm implements OnInit {

  private loginCreds: {} = {
    email: '',
    password: ''
  }
  private loginWorking: boolean = false
  private formError: string = ''
  private formHelper: string = ''

  constructor(
    private authService: AuthService,
    private location: Location,
    private router: Router
   ) {}

  public ngOnInit(): void {
    return
  }

  public login(): any {
    this.loginWorking = true
    this.formError = ''
    this.formHelper = ''
    this.authService.login(this.loginCreds)
    .subscribe(
      response => {
        console.warn(response)
        this.authService.setCredentials(response)
        // setTimeout(() => {
        //   this.router.navigate(['dashboard'])
        // }, 2000)
      },
      error => {
        let errorHelper = error._body ? error._body : `Ensure your credentials are correct`
        this.formError = `${error.status}: ${error.statusText}`
        this.formHelper = `${errorHelper}`
        this.loginWorking = false
      }
    )
  }

}
