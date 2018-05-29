import { Component, OnInit } from '@angular/core';
import { Router} from '@angular/router';

import {User} from '../../entity/user';
import {Client} from '../../entity/client';
import {Consult} from '../../entity/consult';
import { AuthService } from '../../service/auth.service';
import { UserService } from '../../service/user.service';
import { ClientService } from '../../service/client.service';
import { ConsultService } from '../../service/consult.service';
@Component({
  selector: 'app-add-consult',
  templateUrl: './add-consult.component.html',
  styleUrls: ['./add-consult.component.css']
})
export class AddConsultComponent implements OnInit {

  users:User[];
  clients:Client[];
  consult:Consult;

  date:string;
  user_id:number;
  user:User;
  details:string;
  client_id:number;
  client:Client;

  constructor(
    private auth:AuthService,
    private userService:UserService,
    private clientService:ClientService,
    private consultSerevice:ConsultService,
    private router:Router
  ) { }

  ngOnInit() {
    if (!this.auth.isAuthenticated()){
      this.router.navigateByUrl('/login');
    }else{
      if(this.auth.isDoctor()){
        this.router.navigateByUrl('/doctor');
      }else if(!this.auth.isSecretary()){
        this.router.navigateByUrl('/admin');
      }
    }
    this.getUsers();
    this.getClients();
  }


  getClients():void{
    this.clientService.getClients()
    .subscribe(
      clients => this.clients = clients
    );
  }

  getUsers():void{
    this.userService.getUsers()
    .subscribe(
      users =>{
        this.users = users.filter(x=> x.role.name == 'DOCTOR');
      }
    );
  }

  addConsult():void{
    this.user = this.users.filter(x=> x.id == this.user_id)[0];
    this.client = this.clients.filter(x=> x.id == this.client_id)[0];
    let body = JSON.stringify(
      {
        "id":null,
        "date":new Date(this.date).getTime(),
        "user":this.user,
        "details":this.details,
        "client":this.client
      }
    );
    console.log(body);
    this.consultSerevice.addConsult(body)
    .subscribe(
      consult =>{
        if(this.consult.id!=null){
          this.router.navigateByUrl('/secretary');
        }
      }
    )  
    
  }

}
