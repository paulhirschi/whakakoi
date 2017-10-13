import { Component, OnInit, Input, trigger, state, style, transition, animate } from '@angular/core'
import { Location } from '@angular/common'
import { Router } from '@angular/router'

import { routeTransitionFadeInOut } from '../animations/animations'

@Component({
//  moduleId: module.id,
  // selector: 'my-dashboard',
  templateUrl: 'assets/templates/login/login.component.forgotpasswordform.html',
  styleUrls: [ 'assets/stylesheets/login.component.css' ]
  // animations: [routeTransitionFadeInOut()],
  // host: {'[@fadeInOut]': ''},
})
export class ForgotPasswordForm implements OnInit {

  constructor(
    private location: Location,
    private router: Router
   ) {}

  public ngOnInit(): void {
    console.warn('wuddup from forgotpassword oninit')
  }
}

/*
Copyright 2016 Google Inc. All Rights Reserved.
Use of this source code is governed by an MIT-style license that
can be found in the LICENSE file at http://angular.io/license
*/
