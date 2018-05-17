import { Component, OnInit } from '@angular/core';
import { AuthService } from '../service/auth.service';
import { Http, Response } from '@angular/http';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router} from '@angular/router';
import {BookService} from '../service/book.service';
import {Book} from '../entity/book';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  books:Book[];

  searchTitle:string;
  searchGenre:string;
  searchAuthor:string;
  
  constructor(
    private bookService:BookService,
    private auth:AuthService,
    private router:Router,
    private http:HttpClient
  ) { }

  ngOnInit() {
    if(!this.auth.isAuthenticated()){
      this.router.navigateByUrl("/login");
    }
    this.getBooks();
  }



  search():void{
    console.log("O intrat");
    if(this.searchTitle!="" && this.searchTitle!=null){
      this.books = this.books.filter(x => x.title.includes(this.searchTitle));
    }
    if(this.searchGenre!="" && this.searchGenre!=null){
      this.books = this.books.filter(x => x.genre.name.includes(this.searchGenre));
    }
    if(this.searchAuthor!="" && this.searchAuthor!=null){
      this.books = this.books.filter(x => x.author.name.includes(this.searchAuthor));
    }
    
  }
  private getBooks(){
    this.bookService.getBooks()
    .subscribe(books => this.books= books);
  }
}
