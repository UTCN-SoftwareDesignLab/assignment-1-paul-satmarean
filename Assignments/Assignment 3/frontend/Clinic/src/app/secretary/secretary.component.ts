import { Component, OnInit } from '@angular/core';
import { AuthService } from '../service/auth.service';
import { Router} from '@angular/router';

import {Client} from '../entity/client';
import {Consult} from '../entity/consult';
import { ConsultService} from '../service/consult.service';
import {User} from '../entity/user';

@Component({
  selector: 'app-secretary',
  templateUrl: './secretary.component.html',
  styleUrls: ['./secretary.component.css']
})
export class SecretaryComponent implements OnInit {

  consults:Consult[];

  constructor(
    private auth:AuthService,
    private consultService:ConsultService,
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
    this.getConsults();
  }

  public getConsults():void {
    this.consultService.getConsults()
    .subscribe(consults=>this.consults = consults);
  }
  
  delete(id:number){
    this.consults = this.consults.filter(x=>x.id!=id);
    this.consultService.deleteConsult(id)
    .subscribe(
    (err:any)=>{
      console.log("Could not delete.");
    }
    );
  }
}
