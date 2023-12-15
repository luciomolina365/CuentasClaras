import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-signIn',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent {
  user: any = {}; // Modelo para los datos del usuario

  submitForm() {
    // Lógica para manejar la presentación de datos del formulario
    console.log('Formulario enviado:', this.user);
  }
}


