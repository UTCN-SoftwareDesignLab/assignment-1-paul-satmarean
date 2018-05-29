import { Component, OnInit } from '@angular/core';
import { AuthService } from '../service/auth.service';
import { Router, NavigationEnd} from '@angular/router';
import { NotificationService } from '../service/notification.service';
import { Notification } from '../entity/notification';
@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  hasNotifications:boolean;

  constructor(
    private auth:AuthService,
    private router:Router,
    private notificationService:NotificationService
  ) { }

  ngOnInit() {
    this.hasNotifications==false;

    this.router.events.subscribe((event)=>{
        if(event instanceof NavigationEnd){
          if(this.auth.isDoctor()){
            this.startPolling();
          }
        }
      }
    );
  }

  private startPolling():void{
    this.notificationService.getNewNotifications()
    .subscribe(
      (notifications: Notification[])=>{
        if(notifications!=null){
          console.log(notifications);
          if(notifications.length!=0){
            this.hasNotifications= true;
          }   
        }
      },
      (err:any)=>{
        this.hasNotifications=false;
      }
    );
  }

  private viewNotifications():void{
    this.hasNotifications=false;
    this.router.navigateByUrl('/admin');
  }


  logOut(){
    this.auth.logOut();
  }  

}
