import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ErrorPageComponent } from './shared/error-page/error-page.component';
import { AppRoutingModule } from './app-routing.module';
import { TokenInterceptor} from './interceptor.service';

import { LoginComponent } from './auth/login/login.component';
import {ReactiveFormsModule} from '@angular/forms';

import { UserService } from 'src/app/shared/services/user/user.service';
import { LoginService } from 'src/app/shared/services/auth/login.service';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
  ],
  //providers: [
   // {provide:HTTP_INTERCEPTORS,useClass:JwtInterceptorService,multi:true},
   // {provide:HTTP_INTERCEPTORS,useClass:ErrorInterceptorService,multi:true}
  //],
  bootstrap: [AppComponent]
})
export class AppModule { }