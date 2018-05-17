import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes} from '@angular/router';
import { LoginComponent } from '../login/login.component';
import { UserComponent } from '../user/user.component';
import { AdminComponent} from '../admin/admin.component';
import { AddUserComponent } from '../admin/add-user/add-user.component';
import { ViewBooksComponent } from '../admin/view-books/view-books.component';
import { AddBookComponent } from '../admin/add-book/add-book.component';
import { ViewAuthorsComponent } from '../admin/view-authors/view-authors.component';
import { ViewGenresComponent } from '../admin/view-genres/view-genres.component';
import { AddAuthorComponent } from '../admin/add-author/add-author.component';
import { AddGenreComponent } from '../admin/add-genre/add-genre.component';
import { BookDetailsComponent } from '../book-details/book-details.component';

const routes: Routes =  [
  {path: '', redirectTo: '/login', pathMatch: 'full'},
  {path: 'login', component: LoginComponent},
  {path: 'books', component: UserComponent},
  {path: 'admin', component: AdminComponent},
  {path: 'addUser', component: AddUserComponent},
  {path: 'admin/books', component: ViewBooksComponent},
  {path: 'addBook', component: AddBookComponent},
  {path: 'authors', component: ViewAuthorsComponent},
  {path: 'genres', component: ViewGenresComponent},
  {path: 'addAuthor', component: AddAuthorComponent},
  {path: 'addGenre', component:AddGenreComponent},
  {path: 'book/:id', component:BookDetailsComponent}
]

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports:[
    RouterModule
  ],
  declarations: []
})
export class AppRoutingModule { }
