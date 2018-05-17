import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { of } from 'rxjs';
import {Router} from '@angular/router';
import { Http, Response } from '@angular/http';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap} from 'rxjs/operators';
import { AuthService } from './auth.service';
import {Author} from '../entity/author';
@Injectable({
  providedIn: 'root'
})
export class AuthorService {
  authorUrl = "http://localhost:8080/api/authors/"


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

  public getAuthors():Observable<Author[]>{
    return this.http.get<Author[]>(this.authorUrl, this.httpOptions)
    .pipe(
      catchError(this.handleError('getAuthors',[]))
    );
  }

  public deleteAuthor(id:number):Observable<any>{
    const url = `${this.authorUrl}${id}`;
    return this.http.delete(url, this.httpOptions)
    .pipe(
      catchError(this.handleError<string>("deleteAuthor"))
    );
  }

  public addAuthor(author:string):Observable<any>{
    return this.http.post(this.authorUrl, author, this.httpOptions)
    .pipe(
      tap(author => {
        if(author !=null){
          this.router.navigateByUrl("/admin");
        }
      }),
      tap(_ => this.log(`Sent author ${author}`)),
      catchError(this.handleError<any>("addAuthor"))
    )
  }

  private handleError<T> (operation = 'operation', result?: T){
    return (error: any): Observable<T> => {
      this.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    }
  }

  private log(message:string){
    console.log(`GetAuthors: ${message}`);
  }
}

