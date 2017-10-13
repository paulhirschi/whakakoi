import 'rxjs/add/operator/filter'
import 'rxjs/add/operator/map'
import 'rxjs/add/operator/mergeMap'

import { Component, OnInit }                     from '@angular/core'
import { Router, NavigationEnd, ActivatedRoute } from '@angular/router'
import { Title }                                 from '@angular/platform-browser'

@Component({
  //  moduleId: module.id,
  selector: 'my-app',
  // template: `
  //   <h1>{{title}}</h1>
  //   <nav>
  //     <a routerLink="/dashboard" routerLinkActive="active">Dashboard</a>
  //   </nav>
  //   <router-outlet></router-outlet>
  // `,
  template: '<router-outlet></router-outlet>'
  // styleUrls: ['assets/app/app.component.css'],
})
export class AppComponent implements OnInit {
  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private titleService: Title
   ) { }

  public ngOnInit() {
    // Dynamic page title based on route data
    // https://toddmotto.com/dynamic-page-titles-angular-2-router-events
    this.router.events
      .filter(event => event instanceof NavigationEnd)
      .map(() => this.activatedRoute)
      .map(route => {
        while (route.firstChild) route = route.firstChild
        return route
      })
      .filter(route => route.outlet === 'primary')
      .mergeMap(route => route.data)
      .subscribe((event: { title: string }) => this.titleService.setTitle(event.title))
  }
}
