import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../service/auth.service';
import { UserService } from '../../service/user.service';
import { Router} from '@angular/router';
import { ClientService } from '../../service/client.service';
import { Client } from '../../entity/client';

@Component({
  selector: 'app-view-clients',
  templateUrl: './view-clients.component.html',
  styleUrls: ['./view-clients.component.css']
})
export class ViewClientsComponent implements OnInit {

  clients:Client[];

  constructor(
    private auth:AuthService,
    private clientService:ClientService,
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
    this.getClients();
  }


  private getClients():void{
    this.clientService.getClients()
    .subscribe(clients => {
      this.clients= clients;
      console.log(clients);
    }
  );
  } 

  private delete(id:number):void{
    this.clients = this.clients.filter(x=> x.id!=id);
    this.clientService.deleteClient(id)
    .subscribe(
      (err:any)=>{
        console.log("Could not delete")
      }
    );
  }

}
