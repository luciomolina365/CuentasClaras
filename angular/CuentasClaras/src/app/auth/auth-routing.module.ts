import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { LogInComponent } from './log-in/log-in.component';
import { SignInComponent } from './sign-in/sign-in.component';

const routes: Routes = [
  {
    path: '',
    children: [
      {
        path: 'login',
        component: LogInComponent
      },
      {
        path: 'signIn',
        component: SignInComponent
      },
      {
        path: '**',
        redirectTo: 'login'
      }
    ]
  }
];

@NgModule({  
  imports: [
    RouterModule.forChild( routes )
  ],
  exports: [
    RouterModule
  ]
})
export class AuthRoutingModule { }