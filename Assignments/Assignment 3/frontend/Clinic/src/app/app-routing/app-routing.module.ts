import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes, Router} from '@angular/router';
import { LoginComponent } from '../login/login.component';
import { AdminComponent} from '../admin/admin.component';
import { DoctorComponent } from '../doctor/doctor.component';
import { SecretaryComponent } from '../secretary/secretary.component';
import { AddUserComponent } from '../admin/add-user/add-user.component';
import { ViewClientsComponent } from '../secretary/view-clients/view-clients.component';
import { AddClientComponent } from '../secretary/add-client/add-client.component';
import { AddConsultComponent } from '../secretary/add-consult/add-consult.component';
import { ConsultDetailsComponent } from '../doctor/consult-details/consult-details.component';

const routes: Routes = [
  {path: '', redirectTo: '/login', pathMatch: 'full'},
  {path: 'login', component: LoginComponent},
  {path: 'addUser', component: AddUserComponent},
  {path: 'admin', component:AdminComponent},
  {path: 'doctor', component:DoctorComponent},
  {path: 'secretary', component:SecretaryComponent},
  {path: 'secretary/clients', component:ViewClientsComponent},
  {path: 'addClient', component:AddClientComponent},
  {path: 'addConsult', component:AddConsultComponent},
  {path: 'doctor/:id', component:ConsultDetailsComponent}
  
]




@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports:[
    RouterModule
  ],
  declarations: []
})
export class AppRoutingModule { }
