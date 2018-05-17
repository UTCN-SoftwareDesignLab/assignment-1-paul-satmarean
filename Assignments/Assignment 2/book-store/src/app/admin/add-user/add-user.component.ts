import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../service/auth.service';
import { UserService } from '../../service/user.service';
import {RoleService} from '../../service/role.service';
import {User} from '../../entity/user';
import { Router} from '@angular/router';

import {Role} from '../../entity/role';
@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit {

  username:string;
  password:string;
  email:string;
  roleid:number;
  message:string;
  role:Role;
  roles:Role[];

  user:User;
  constructor(
    private auth:AuthService,
    private userService:UserService,
    private roleService:RoleService,
    private router:Router
  ) { }

  ngOnInit() {
    if(!this.auth.isAdmin()){
      this.router.navigateByUrl('/books');
    }
    this.getRoles();
    this.password="";
    this.username="";
    this.email="";
  }

  getRoles():void{
    this.roleService.getRoles()
    .subscribe(roles => this.roles = roles);
  }

  public addUser(){
    this.role = this.roles.filter(x=> x.idrole == this.roleid)[0];
    let body = JSON.stringify(
      {
        "iduser":null,
        "username":this.username,
        "password":this.password,
        "email":this.email,
        "role":this.role
      }
    );

    console.log(body);

    this.userService.addUser(body).subscribe(
      (user:User) =>{ 
        this.user = user;
        this.router.navigateByUrl("/admin");
      }
      );

  }

}
