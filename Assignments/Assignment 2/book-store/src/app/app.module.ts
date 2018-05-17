import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { AppRoutingModule } from './app-routing/app-routing.module';
import { UserComponent } from './user/user.component';
import { AuthService } from './service/auth.service';
import {HTTP_INTERCEPTORS} from '@angular/common/http';
import {TokenInterceptor} from './service/token.interceptor';
import { BookService } from './service/book.service';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { AdminComponent } from './admin/admin.component';
import { AddUserComponent } from './admin/add-user/add-user.component';
import { AddBookComponent } from './admin/add-book/add-book.component';
import { ViewBooksComponent } from './admin/view-books/view-books.component';
import { ViewGenresComponent } from './admin/view-genres/view-genres.component';
import { ViewAuthorsComponent } from './admin/view-authors/view-authors.component';
import { AddGenreComponent } from './admin/add-genre/add-genre.component';
import { AddAuthorComponent } from './admin/add-author/add-author.component';
import { BookDetailsComponent } from './book-details/book-details.component';
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    UserComponent,
    NavBarComponent,
    AdminComponent,
    AddUserComponent,
    AddBookComponent,
    ViewBooksComponent,
    ViewGenresComponent,
    ViewAuthorsComponent,
    AddGenreComponent,
    AddAuthorComponent,
    BookDetailsComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [
    AuthService,
    BookService,
    {
      provide:HTTP_INTERCEPTORS,
      useClass:TokenInterceptor,
      multi:true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
