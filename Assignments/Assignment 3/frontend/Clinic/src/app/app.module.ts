import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import {HTTP_INTERCEPTORS} from '@angular/common/http';
import {TokenInterceptor} from './service/token.interceptor';
import { AuthService } from './service/auth.service';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { AppRoutingModule } from './app-routing/app-routing.module';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { DoctorComponent } from './doctor/doctor.component';
import { AdminComponent } from './admin/admin.component';
import { SecretaryComponent } from './secretary/secretary.component';
import { AddUserComponent } from './admin/add-user/add-user.component';
import { UserService } from './service/user.service';
import { ConsultService } from './service/consult.service';
import { RoleService } from './service/role.service';
import { ClientService} from './service/client.service';
import { ViewClientsComponent } from './secretary/view-clients/view-clients.component';
import { AddClientComponent } from './secretary/add-client/add-client.component';
import { AddConsultComponent } from './secretary/add-consult/add-consult.component';
import { ConsultDetailsComponent } from './doctor/consult-details/consult-details.component';

@NgModule({
  declarations: [
    
    AppComponent,
    LoginComponent,
    NavBarComponent,
    DoctorComponent,
    AdminComponent,
    SecretaryComponent,
    AddUserComponent,
    ViewClientsComponent,
    AddClientComponent,
    AddConsultComponent,
    ConsultDetailsComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [
    AuthService,
    UserService,
    ConsultService,
    RoleService,
    ClientService,
    {
      provide:HTTP_INTERCEPTORS,
      useClass:TokenInterceptor,
      multi:true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
