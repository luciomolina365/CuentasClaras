import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LoginService } from 'src/app/shared/services/auth/login.service';


@Component({
	selector: 'app-login',
	templateUrl: './login.component.html',
})
export class LoginComponent {

	loginForm: FormGroup;
	constructor(private fb: FormBuilder, private LoginService: LoginService) {
		this.loginForm = this.fb.group({
			username: ['', Validators.required],
			password: ['', Validators.required]
		});
	}

	onSubmit() {
		if (this.loginForm.valid) {
			const username = this.loginForm.value.username;
			const password = this.loginForm.value.password;
			console.log(username);
			console.log(password);

			this.LoginService.login(username, password).subscribe(
				(response) => {
					const token = response.token;


					if (token) {
						console.log('Inicio de sesión exitoso. Token:', token);
						localStorage.setItem('token', token);

						// Aquí puedes almacenar el token en localStorage o en una cookie
						// Luego, puedes redirigir al usuario a otra página o realizar otras acciones después del inicio de sesión
					} else {
						console.log('Inicio de sesión fallido. Token no recibido.');
						// Puedes mostrar un mensaje de error o realizar otras acciones si el inicio de sesión falla
					}
				},
				(error) => {
					console.error('Error durante el inicio de sesión:', error);
					
				}
			);
		}
	}
}