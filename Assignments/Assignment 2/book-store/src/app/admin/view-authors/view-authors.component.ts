import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../service/auth.service';
import { Http, Response } from '@angular/http';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router} from '@angular/router';
import {AuthorService} from '../../service/author.service';
import {Author} from '../../entity/author';

@Component({
  selector: 'app-view-authors',
  templateUrl: './view-authors.component.html',
  styleUrls: ['./view-authors.component.css']
})
export class ViewAuthorsComponent implements OnInit {

  authors:Author[];

  constructor(
    private authorService:AuthorService,
    private auth:AuthService,
    private router:Router,
    private http:HttpClient
  ) { }

  ngOnInit() {
    if(!this.auth.isAdmin()){
      this.router.navigateByUrl("/login");
    }
    this.getAuthors();
  }

  getAuthors():void{
      this.authorService.getAuthors()
      .subscribe(authors => this.authors= authors);
  }

  deleteAuthor(id:number):void{
    this.authors = this.authors.filter(h => h.idauthor!== id);
    this.authorService.deleteAuthor(id).subscribe();
  }

}
