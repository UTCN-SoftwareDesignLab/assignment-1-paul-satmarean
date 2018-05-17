import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

import {AuthService} from '../../service/auth.service';
import {Author} from '../../entity/author';
import {AuthorService} from '../../service/author.service';

@Component({
  selector: 'app-add-author',
  templateUrl: './add-author.component.html',
  styleUrls: ['./add-author.component.css']
})
export class AddAuthorComponent implements OnInit {

  name:string;
  description:string;
  author:Author;

  constructor(
    private auth:AuthService,
    private authorService:AuthorService,
    private router:Router
  ) { }

  ngOnInit() {
    if(!this.auth.isAdmin()){
      this.router.navigateByUrl("/login");
    }
  }

  addAuthor():void{
    let body = JSON.stringify(
      {
        "idauthor":null,
        "name":this.name,
        "description":this.description
      }
    );

    console.log(body);

    this.authorService.addAuthor(body).
    subscribe(
      (author:Author) =>{
        this.author=author;
        this.router.navigateByUrl("/authors");
      }
    )
  }

}
