import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Rx';
// import { HttpService } from './http.service';
import { of } from 'rxjs';
import { Http, Response } from '@angular/http';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap} from 'rxjs/operators';
import { Router } from '@angular/router';
import { Notification} from '../entity/notification';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {
  notificationUrl =  "http://localhost:8080/api/notifications/"

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin':"*"
    })
  };

  constructor(
    private http:HttpClient,
    private router:Router
  ) { }


  getNewNotifications(): Observable<Notification[]> {
    return Observable.interval(1000).switchMap(() => {
      return this.http.get<Notification[]>(this.notificationUrl, this.httpOptions)
      .pipe(
        catchError(this.handleError('getNotifications',[]))
      )
    }
    );
  }


  private handleError<T> (operation = 'operation', result?: T){
    return (error: any): Observable<T> => {
      this.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    }
  }

  private log(message:string){
    console.log(`GetNotifications: ${message}`);
  }

}
