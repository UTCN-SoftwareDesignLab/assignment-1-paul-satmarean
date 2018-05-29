import { Component, OnInit } from '@angular/core';
import { AuthService } from '../service/auth.service';
import { Router} from '@angular/router';

import {Client} from '../entity/client';
import {Consult} from '../entity/consult';
import { ConsultService} from '../service/consult.service';
import {User} from '../entity/user';

@Component({
  selector: 'app-doctor',
  templateUrl: './doctor.component.html',
  styleUrls: ['./doctor.component.css']
})
export class DoctorComponent implements OnInit {

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
      if(this.auth.isSecretary()){
        this.router.navigateByUrl('/secretary');
      }else if(!this.auth.isDoctor()){
        this.router.navigateByUrl('/admin');
      }
    }
    this.getConsults();
  }

  public getConsults():void {
    this.consultService.getConsults()
    .subscribe(consults=>this.consults = consults);
  }
}
