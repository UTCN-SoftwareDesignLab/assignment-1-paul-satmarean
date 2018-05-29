import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { of } from 'rxjs';
import { Http, Response } from '@angular/http';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap} from 'rxjs/operators';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';
import { Consult} from '../entity/consult';

@Injectable({
  providedIn: 'root'
})
export class ConsultService {

  consultUrl = "http://localhost:8080/api/consults/"


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

  public getConsults():Observable<Consult[]>{
    return this.http.get<Consult[]>(this.consultUrl, this.httpOptions)
    .pipe(
      catchError(this.handleError('getConsults',[]))
    );
  }

  public getConsult(id:number):Observable<Consult>{
    const url = `${this.consultUrl}${id}`;
    return this.http.get<Consult>(url, this.httpOptions)
    .pipe(
      catchError(this.handleError<Consult>("GetConsult"))
    );
  }

  public getConsultByUserId(id:number):Observable<Consult[]>{
    const url = `${this.consultUrl}user/${id}`;
    return this.http.get<Consult[]>(url, this.httpOptions)
    .pipe(
      catchError(this.handleError<Consult[]>("GetConsult"))
    );
  }

  //ADD
  public addConsult(consult :string):Observable<any>{
    return this.http.post(this.consultUrl, consult, this.httpOptions)
      .pipe(
        tap(consult =>{
          if(consult!=null){
            this.router.navigateByUrl("/secretary");
          }
        }
        ),
        tap(_ => this.log(`Sent user ${consult}`)),
        catchError(this.handleError<any>("addConsult"))
      );
  }
   //EDIT
   public editConsult(consult :string):Observable<any>{
    return this.http.put(this.consultUrl, consult, this.httpOptions)
      .pipe(
        tap(consult =>{
          if(consult!=null){
            this.router.navigateByUrl("/secretary");
          }
        }
        ),
        tap(_ => this.log(`Sent user ${consult}`)),
        catchError(this.handleError<any>("addConsult"))
      );
  }

  //delete
  public deleteConsult(id:number):Observable<any>{
    const url = `${this.consultUrl}${id}`;
    return this.http.delete<any>(url, this.httpOptions)
      .pipe(
        catchError(this.handleError<string>("deleteConsult"))
      );
  }

  public getConsultByClientId(id:number):Observable<Consult[]>{
    const url = `${this.consultUrl}client/${id}`;
    return this.http.get<Consult[]>(url, this.httpOptions)
    .pipe(
      catchError(this.handleError<Consult[]>("GetConsult"))
    );
  }

  private handleError<T> (operation = 'operation', result?: T){
    return (error: any): Observable<T> => {
      this.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    }
  }

  private log(message:string){
    console.log(`GetConsults: ${message}`);
  }

}
