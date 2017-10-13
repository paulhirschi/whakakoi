import { NgModule }              from '@angular/core'
import { RouterModule, Routes }  from '@angular/router'

import { LoginComponent }        from './login/login.component'
import { LoginForm }             from './login/login.component.loginform'
import { ForgotPasswordForm }    from './login/login.component.forgotpasswordform'
import { SignupForm }            from './login/login.component.signupform'
import { DashboardComponent }    from './dashboard/dashboard.component'
import { DeviceDetailComponent } from './dashboard/devicedetail.component'
import { AuthGuard }             from './services/authguard.service'

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: '',  component: LoginComponent, children: [
    { path: 'login', component: LoginForm, data: { title: 'Whakakoi Login' } },
    { path: 'forgotpassword', component: ForgotPasswordForm, data: { title: 'Whakakoi Forgot' } },
    { path: 'signup', component: SignupForm, data: { title: 'Whakakoi Signup' } }
  ] },
  { path: 'dashboard',  component: DashboardComponent, data: { title: 'Spire Dashbaord' }, canActivate: [AuthGuard]},
  { path: 'dashboard/:id', component: DashboardComponent, data: { title: 'Spire Dashbaord' }, canActivate: [AuthGuard] }
  // { path: 'detail/:id', component: HeroDetailComponent },
  // { path: 'heroes',     component: HeroesComponent },
]

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}

/*
Copyright 2016 Google Inc. All Rights Reserved.
Use of this source code is governed by an MIT-style license that
can be found in the LICENSE file at http://angular.io/license
*/
