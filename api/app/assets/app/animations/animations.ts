import { trigger, state, animate, style, transition } from '@angular/core'

export function routeTransitionFadeInOut() {
  return fadeInOut()
}

function fadeInOut() {
  return trigger('fadeInOut', [
    state('void', style({ transform: 'scale(0)' })),
    state('*', style({ transform: 'scale(1)' })),
    transition(':enter', [
      style({ transform: 'scale(0)' }),
      animate('0.5s ease-in-out', style({ transform: 'scale(1)' }))
    ]),
    transition(':leave', [
      style({ transform: 'scale(1)' }),
      animate('0.2s ease-in-out', style({ transform: 'scale(0)' }))
    ])
  ])
}
