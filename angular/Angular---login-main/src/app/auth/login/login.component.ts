import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../shared/services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
})
export class LoginComponent {
  credentials = { username: '', password: '' };

  constructor(private router: Router,private authService: AuthService) {}

  login(): void {
    this.authService.login(this.credentials).subscribe(
      (response) => {
        this.authService.setToken(response.token);
		this.router.navigate(['/']);
        // Redirigir a la página principal u otra página después del login
      },
      (error) => {
        console.error('Error during login', error);
        // Manejar el error de autenticación
      }
    );
  }
}
