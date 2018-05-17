import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

import {Book} from '../../entity/book';
import {Author} from '../../entity/author';
import {Genre} from '../../entity/genre';

import { AuthService } from '../../service/auth.service';
import {BookService} from '../../service/book.service';
import {AuthorService} from '../../service/author.service';
import {GenreService} from '../../service/genre.service';

@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  styleUrls: ['./add-book.component.css']
})
export class AddBookComponent implements OnInit {

  title:string;
  price:number;
  quantity:number;
  idauthor:number;
  idgenre:number;

  genre:Genre;
  author:Author;

  authors:Author[];
  genres:Genre[];

  constructor(
    private auth:AuthService,
    private bookService:BookService,
    private router:Router,
    private genreService:GenreService,
    private authorService:AuthorService
  ) { }

  ngOnInit() {
    if(!this.auth.isAdmin()){
      this.router.navigateByUrl("/login");
    }

    this.getAuthors();
    this.getGenres();
  }

  getAuthors():void{
    this.authorService.getAuthors()
    .subscribe(authors => this.authors = authors);

  }

  getGenres():void{
    this.genreService.getGenres()
    .subscribe(genres => this.genres= genres);
  }





  addBook():void{
    this.genre = this.genres.filter(x=>x.idgenre == this.idgenre)[0];
    this.author = this.authors.filter(x=>x.idauthor == this.idauthor)[0];
    console.log(this.idgenre);
    console.log(this.genres);
    // console.log(t);
    // this.genres=  this.genres.filter(function(genre){
    //   console.log(genre);
    //   return genre.idgenre == this.idgenre;
    // });
    console.log(this.genres);
    let body=JSON.stringify(
      {
        "idbook":null,
        "title":this.title,
        "quantity":this.quantity,
        "price":this.price,
        "author":this.author,
        "genre":this.genre
      }
    );

    console.log(body);
    this.bookService.addBook(body).subscribe(
      (book:Book)=>{
        if (book!=null){
          this.router.navigateByUrl("/admin/books");
        }
      }
    )
  }
}
