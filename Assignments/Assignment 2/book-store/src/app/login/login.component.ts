import { Component, OnInit } from '@angular/core';
import {AuthService} from '../service/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  private username: string;
  private password: string;
  private message: string = null;
  private result:boolean;


  constructor(
    private auth:AuthService,
    private router:Router
  ) { }

  ngOnInit() {
    if (!this.auth.isAuthenticated()){
      this.router.navigateByUrl('/login');
    }else{
      if(!this.auth.isAdmin()){
        this.router.navigateByUrl('/books');
        console.log("User");
      }else{
        this.router.navigateByUrl('/admin');
        console.log("Admin");
      }
    }

  }
  onSubmit(){
    if(this.username==null || this.username=="" || this.password==null || this.password==""){
      this.message = "Fields cannot be empty.";
    }
    this.auth.login(this.username, this.password)
      .subscribe(
        (res:any) => {
          if(!this.auth.isAdmin()){
            this.router.navigateByUrl('/books');
          }else{
            this.router.navigateByUrl('/admin');
          }
        },
        (err:any) =>{
          this.message = "Invalid username or password.";
        }
        );
  }
}
