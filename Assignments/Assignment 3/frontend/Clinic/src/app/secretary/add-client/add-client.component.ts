import { Component, OnInit } from '@angular/core';
import { Router} from '@angular/router';
import { AuthService } from '../../service/auth.service';
import {ClientService} from '../../service/client.service';
import {Client} from '../../entity/client';
@Component({
  selector: 'app-add-client',
  templateUrl: './add-client.component.html',
  styleUrls: ['./add-client.component.css']
})
export class AddClientComponent implements OnInit {

  name:string;
  id_card_no:string;
  pnc:string;
  birth_date:string;
  address:string;

  client:Client;
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
  }


  private addClient():void{
    let body = JSON.stringify(
    {
      "id":null,
      "name":this.name,
      "id_card_no":this.id_card_no,
      "pnc":this.pnc,
      "birth_date":new Date(this.birth_date).getTime(),
      "address":this.address
    }
    );
    console.log(body)
    this.clientService.addClient(body)
    .subscribe(
      client=>{
        if(this.client.id!=null){
          this.router.navigateByUrl('/secretary/clients');
        }
      }
    )
  }   

}
