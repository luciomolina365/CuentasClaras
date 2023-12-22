// menu.component.ts
import { Component } from '@angular/core';
import { LoginService } from 'src/app/shared/services/auth/login.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['menu.component.scss'],
})
export class MenuComponent {
  isAuthenticated$ = this.loginService.isAuthenticated$();

  constructor(private loginService: LoginService) {}
  
  
    logout(): void {
    this.loginService.logout();
  }
  
}
