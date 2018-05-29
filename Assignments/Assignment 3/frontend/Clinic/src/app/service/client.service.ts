import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { of } from 'rxjs';
import { Http, Response } from '@angular/http';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap} from 'rxjs/operators';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';
import { Client } from '../entity/client';

@Injectable({
  providedIn: 'root'
})
export class ClientService {
  clientUrl = "http://localhost:8080/api/clients/"

  constructor(
    private http:HttpClient,
    private router:Router
  ) { }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin':"*"
    })
  };

  public getClients():Observable<Client[]>{
    return this.http.get<Client[]>(this.clientUrl, this.httpOptions)
    .pipe(
      catchError(this.handleError('getClients',[]))
    );
  }

  public addClient(client :string):Observable<any>{
    return this.http.post(this.clientUrl, client, this.httpOptions)
      .pipe(
        tap(client =>{
          if(client!=null){
            this.router.navigateByUrl("/secretary");
          }
        }
        ),
        tap(_ => this.log(`Sent user ${client}`)),
        catchError(this.handleError<any>("addClient"))
      );
  }

  public deleteClient(id:number):Observable<any>{
    const url = `${this.clientUrl}${id}`;
    return this.http.delete<any>(url, this.httpOptions)
      .pipe(
        catchError(this.handleError<string>("deleteClient"))
      );
  }

  private handleError<T> (operation = 'operation', result?: T){
    return (error: any): Observable<T> => {
      this.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    }
  }

  private log(message:string){
    console.log(`GetClients: ${message}`);
  }
}
