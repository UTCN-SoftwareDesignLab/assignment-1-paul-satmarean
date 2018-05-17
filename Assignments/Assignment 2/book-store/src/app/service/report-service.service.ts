import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { of } from 'rxjs';
import { Http, Response } from '@angular/http';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap} from 'rxjs/operators';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class ReportServiceService {

  reportUrl = "http://localhost:8080/api/report/"

  constructor(
    private http:HttpClient
  ) { }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin':"*"
    })
  };

  getReport():Observable<any>{
    return this.http.get(this.reportUrl, this.httpOptions)
    .pipe(
      catchError(this.handleError('getReport',[]))
    )
  }

  private handleError<T> (operation = 'operation', result?: T){
    return (error: any): Observable<T> => {
      this.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    }
  }

  private log(message:string){
    console.log(`GetBooks: ${message}`);
  }
}
