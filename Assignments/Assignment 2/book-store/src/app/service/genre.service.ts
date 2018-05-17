import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { of } from 'rxjs';
import { Http, Response } from '@angular/http';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap} from 'rxjs/operators';
import { AuthService } from './auth.service';
import {Router} from '@angular/router';
import {Genre} from '../entity/genre';

@Injectable({
  providedIn: 'root'
})
export class GenreService {
  genreUrl = "http://localhost:8080/api/genres/"

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

  public getGenres():Observable<Genre[]>{
    return this.http.get<Genre[]>(this.genreUrl, this.httpOptions)
    .pipe(
      catchError(this.handleError('getGenres',[]))
    );
  }

  public addGenre(genre:string):Observable<any>{
    return this.http.post(this.genreUrl, genre, this.httpOptions)
    .pipe(
      tap(genre => {
        if(genre!=null){
          this.router.navigateByUrl("/admin");
        }
      }),
      tap(_ => this.log(`Sent genre ${genre}`)),
      catchError(this.handleError<any>("addGenre"))
    )
  }

  private handleError<T> (operation = 'operation', result?: T){
    return (error: any): Observable<T> => {
      this.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    }
  }

  public deleteGenre(id:number):Observable<any>{
    const url = `${this.genreUrl}${id}`;
    return this.http.delete(url, this.httpOptions)
    .pipe(
      catchError(this.handleError<string>("deleteGenre"))
    );
  }


  private log(message:string){
    console.log(`GetGenres: ${message}`);
  }

}
