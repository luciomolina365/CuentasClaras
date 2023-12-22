import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/shared/services/auth/login.service';


@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
})
export class LoginComponent {

    loginForm: FormGroup;

    constructor(private fb: FormBuilder, private LoginService: LoginService, private router: Router) {
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
                        localStorage.setItem('id', response.id);
                        localStorage.setItem('username', response.username);

                        this.router.navigate(['/home/menu']);
                        
                    } else {
                        console.log('Inicio de sesión fallido. Token no recibido.');
                    }
                },
                (error) => {
                    console.error('Error durante el inicio de sesión:', error);
                }
            );
        }
    }
}