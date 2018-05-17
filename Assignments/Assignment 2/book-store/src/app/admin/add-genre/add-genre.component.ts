import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

import {AuthService} from '../../service/auth.service';
import {Genre} from '../../entity/genre';
import {GenreService} from '../../service/genre.service';


@Component({
  selector: 'app-add-genre',
  templateUrl: './add-genre.component.html',
  styleUrls: ['./add-genre.component.css']
})
export class AddGenreComponent implements OnInit {

  name:string;
  description:string;
  genre:Genre;

  constructor(
    private router:Router,
    private genreService:GenreService,
    private auth:AuthService
  ) { }

  ngOnInit() {
    if(!this.auth.isAdmin()){
      this.router.navigateByUrl("/login");
    }
  }

  public addGenre():void{
    let body = JSON.stringify(
      {
        "idgenre":null,
        "name":this.name,
        "description":this.description
      }
    );

    console.log(body);
    this.genreService.addGenre(body)
    .subscribe(
      (genre:Genre) => {
        this.genre = genre;
        this.router.navigateByUrl("/genres");
      }
    )
  }


}
