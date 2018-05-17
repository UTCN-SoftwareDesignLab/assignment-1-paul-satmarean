import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../service/auth.service';
import { Http, Response } from '@angular/http';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router} from '@angular/router';
import {GenreService} from '../../service/genre.service';
import {Genre} from '../../entity/genre';

@Component({
  selector: 'app-view-genres',
  templateUrl: './view-genres.component.html',
  styleUrls: ['./view-genres.component.css']
})
export class ViewGenresComponent implements OnInit {

  genres:Genre[];

  constructor(
    private genreService:GenreService,
    private auth:AuthService,
    private router:Router,
    private http:HttpClient
  ) { }

  ngOnInit() {
    if(!this.auth.isAdmin()){
      this.router.navigateByUrl("/login");
    }
    this.getGenres();
  }

  deleteGenre(id:number):void{
    this.genres = this.genres.filter(x=> x.idgenre !=id);
    this.genreService.deleteGenre(id).subscribe();
  }

  getGenres():void{
    this.genreService.getGenres()
    .subscribe(genres => this.genres = genres);
  }

}
