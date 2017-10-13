import './rxjs-extensions'

import { NgModule }           from '@angular/core'
import { BrowserModule }      from '@angular/platform-browser'
import { FormsModule }        from '@angular/forms'
import { HttpModule }         from '@angular/http'

import { AppRoutingModule }   from './app-routing.module'
import { AppComponent }       from './app.component'

import { DeviceListPipe }     from './pipes/devicelistpipe'

import { LoginComponent }     from './login/login.component'
import { LoginForm }          from './login/login.component.loginform'
import { ForgotPasswordForm } from './login/login.component.forgotpasswordform'
import { SignupForm }         from './login/login.component.signupform'

import { DashboardComponent } from './dashboard/dashboard.component'
import { DeviceListComponent } from './dashboard/devicelist.component'
import { DeviceDetailComponent } from './dashboard/devicedetail.component'

import { ApiService }         from './services/api.service'
import { AuthService }         from './services/auth.service'
import { AuthGuard }         from './services/authguard.service'

// import { HeroesComponent }      from './heroes.component';
// import { HeroDetailComponent }  from './hero-detail.component';
// import { HeroService }          from './hero.service';
// import { HeroSearchComponent }  from './hero-search.component';

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule
  ],
  declarations: [
    AppComponent,
    DeviceListPipe,
    LoginComponent,
    LoginForm,
    ForgotPasswordForm,
    SignupForm,
    DashboardComponent,
    DeviceListComponent,
    DeviceDetailComponent
    // HeroDetailComponent,
    // HeroesComponent,
    // HeroSearchComponent,
  ],
  providers: [ ApiService, AuthService, AuthGuard ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
