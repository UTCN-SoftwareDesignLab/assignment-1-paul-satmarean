import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { of } from 'rxjs';
import { Http, Response } from '@angular/http';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap} from 'rxjs/operators';
import { AuthService } from './auth.service';

import {Book} from '../entity/book';
@Injectable({
  providedIn: 'root'
})
export class BookService {
  bookUrl = "http://localhost:8080/api/books/"


  constructor(
    private auth:AuthService,
    private http:HttpClient
  ) { }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin':"*"
    })
  };

  getBooks():Observable<Book[]>{
    return this.http.get<Book[]>(this.bookUrl, this.httpOptions)
    .pipe(
      catchError(this.handleError('getBooks',[]))
    );
  }

  getBook(id):Observable<Book>{
    const url = `${this.bookUrl}${id}`

    return this.http.get<Book>(url, this.httpOptions)
      .pipe(
        catchError(this.handleError('getBook',null))
      )
  }
  editBook(book:string):Observable<any>{
    return this.http.put(this.bookUrl, book, this.httpOptions)
    .pipe(
      tap(_ => this.log(`Sent book ${book}`)),
      catchError(this.handleError<any>("editBook"))
    )
  }

  public deleteBook(id:number):Observable<any>{
    const url = `${this.bookUrl}${id}`;
    return this.http.delete(url, this.httpOptions)
    .pipe(
      catchError(this.handleError<string>("deleteBook"))
    );
  }


  public addBook(book:string):Observable<any>{
    return this.http.post(this.bookUrl, book, this.httpOptions)
    .pipe(
      tap(_ => this.log(`Sent book ${book}`)),
      catchError(this.handleError<any>("addBook"))
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
