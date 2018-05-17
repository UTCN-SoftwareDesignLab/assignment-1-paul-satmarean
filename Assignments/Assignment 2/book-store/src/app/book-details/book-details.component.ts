import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { AuthService } from '../service/auth.service';
import { BookService } from '../service/book.service';
import {Book} from '../entity/book';
import {Router} from '@angular/router';

@Component({
  selector: 'app-book-details',
  templateUrl: './book-details.component.html',
  styleUrls: ['./book-details.component.css']
})
export class BookDetailsComponent implements OnInit {

  book:Book;
  ammount:number;
  id:number;
  constructor(
    private route:ActivatedRoute,
    private auth:AuthService,
    private bookService:BookService
  ) { }

 sell():void {
   console.log(this.book.quantity);
   console.log(this.ammount);
   if(this.book.quantity > this.ammount){
     this.book.quantity=this.book.quantity -this.ammount;
     let body = JSON.stringify(
       {
         "idbook":this.book.idbook,
         "title":this.book.title,
         "quantity":this.book.quantity,
         "price":this.book.price,
         "author":this.book.author,
         "genre":this.book.genre
       }
     );
     this.bookService.editBook(body).subscribe(book => this.book = book);
   }
 }


  ngOnInit() {
    this.id = +this.route.snapshot.paramMap.get("id");

    this.bookService.getBook(this.id).subscribe(book => this.book = book);
  }

}
