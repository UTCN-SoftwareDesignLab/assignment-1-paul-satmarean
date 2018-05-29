import { Component, OnInit } from '@angular/core';
import { AuthService } from '../service/auth.service';
import { Router} from '@angular/router';
import * as FileSaver from 'file-saver';

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
    private router:Router
  ) { }

  ngOnInit() {
    if (!this.auth.isAuthenticated()){
      this.router.navigateByUrl('/login');
    }else{
      if(this.auth.isDoctor()){
        this.router.navigateByUrl('/doctor');
      }else if(this.auth.isSecretary()){
        this.router.navigateByUrl('/secretary');
      }else{
        this.router.navigateByUrl('/admin');
      }
    }
    this.getUsers();
  }
  
  getUsers():void{
    this.userService.getUsers()
    .subscribe(users => this.users = users);
  }

  delete(id:number):void{
    this.users = this.users.filter(x => x.id!=id);
    this.userService.deleteUser(id).subscribe(
      (err:any)=>{
        console.log("Could not delete")
      }
    );
  }

}
