import {Author} from './author';
import {Genre} from './genre';

export class Book {
    idbook:number;
    title:string;
    quantity:number;
    price:number;
    author:Author;
    genre:Genre;
}