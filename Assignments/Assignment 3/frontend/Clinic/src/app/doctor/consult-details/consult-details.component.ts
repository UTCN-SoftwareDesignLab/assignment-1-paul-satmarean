import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { AuthService } from '../../service/auth.service';
import { ConsultService } from '../../service/consult.service';
import { Consult } from '../../entity/consult';

import {Router} from '@angular/router';

@Component({
  selector: 'app-consult-details',
  templateUrl: './consult-details.component.html',
  styleUrls: ['./consult-details.component.css']
})
export class ConsultDetailsComponent implements OnInit {

  id:number;

  consult:Consult;

  constructor(
    private route:ActivatedRoute,
    private auth:AuthService,
    private consultService:ConsultService,
    private router:Router
  ) { }

  getConsult():void{
    this.consultService.getConsult(this.id)
    .subscribe(
      consult=>{
        this.consult = consult;
        console.log(consult);
      }
    )
  }
  ngOnInit() {
    this.id = +this.route.snapshot.paramMap.get("id");
    this.getConsult();
  }

  addConsult():void{
    let body = JSON.stringify(
      {
        "id":null,
        "date":this.consult.date,
        "user":this.consult.user,
        "details":this.consult.details,
        "client":this.consult.client
      }
    );
    console.log(body);
    this.consultService.editConsult(body)
    .subscribe(
      consult =>{
        if(this.consult.id!=null){
          this.router.navigateByUrl('/doctor');
        }
      }
    )  
  }

}
