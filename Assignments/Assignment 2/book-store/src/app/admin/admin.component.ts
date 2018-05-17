import { Component, OnInit } from '@angular/core';
import { AuthService } from '../service/auth.service';
import { Router} from '@angular/router';
import * as FileSaver from 'file-saver';

import {ReportServiceService} from '../service/report-service.service';
import { User } from '../entity/user';
import { Role } from '../entity/role';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  users:User[];

  constructor(
    private auth:AuthService,
    private userService:UserService,
    private reportService:ReportServiceService,
    private router:Router
  ) { }

  ngOnInit() {
    if(!this.auth.isAdmin()){
      this.router.navigateByUrl('/books');
    }
    this.getUsers();
  }

  getUsers():void{
    this.userService.getUsers()
    .subscribe(users => this.users = users);
  }

  getReport():void{
    this.reportService.getReport()
    .subscribe((file: Blob) => FileSaver.saveAs(file, "report.pdf"));
  }
  delete(id:number):void{
    this.users = this.users.filter(x => x.iduser!=id);
    this.userService.deleteUser(id).subscribe(
      (err:any)=>{
        console.log("Could not delete")
      }
    );
  }

}
