
import { Component } from '@angular/core';
import { SigninService } from '../../shared/services/signin.service';
import { AuthService } from '../../shared/services/auth.service';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
})
export class SigninComponent {
  userData = {
    username: '',
    lastname: '',
    name: '',
    email: '',
    pass: '',
  };

  constructor(private SigninService: SigninService, private authService: AuthService) {}

  signup(): void {
    this.SigninService.signin(this.userData).subscribe(
      (response) => {
        this.authService.setToken(response.token);
        // Redirigir a la página principal u otra página después del registro
      },
      (error) => {
        console.error('Error durante el registro', error);
        // Manejar el error de registro
      }
    );
  }
}
