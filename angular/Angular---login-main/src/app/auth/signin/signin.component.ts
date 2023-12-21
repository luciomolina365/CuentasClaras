import { Inject } from '@angular/core';
import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators, AbstractControl } from '@angular/forms';
import { AuthService } from '../../shared/services/auth/auth.service';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/shared/services/auth/login.service';



@Component({
	selector: 'app-signin',
	templateUrl: './signin.component.html',
})
export class SigninComponent {
	signinForm: FormGroup;

	constructor(private formBuilder: FormBuilder, @Inject(AuthService) private authService: AuthService, private loginService: LoginService, private router: Router) {
		this.signinForm = this.formBuilder.group({
			username: ['', Validators.required],
			lastname: ['', Validators.required],
			name: ['', Validators.required],
			email: ['', [Validators.required, Validators.email]],
			pass: ['', [Validators.required, Validators.minLength(8)]],
			confirmPass: ['', [Validators.required, Validators.minLength(8)]],
		}, {
			validator: this.passwordMatchValidator
		});
	}

	passwordMatchValidator(control: AbstractControl): { [key: string]: boolean } | null {
		const password = control.get('pass');
		const confirmPass = control.get('confirmPass');

		if (password && confirmPass && password.value !== confirmPass.value) {
			return { 'passwordMismatch': true };
		}

		return null;
	}

	signin() {
		if (this.signinForm.valid) {
			const userData = this.signinForm.value;
			this.authService.signup(userData).subscribe(
				response => {
					const { username, pass } = userData;
					this.loginService.login(username, pass);
					this.router.navigate(['/user/login']);
				},
				error => {
					if (error?.error?.passwordMismatch) {
						console.error('Las contraseñas no coinciden.');
					} else {
						console.error('Error durante el registro de usuario: ', error);
					}
				}
			);
		}
	}

}