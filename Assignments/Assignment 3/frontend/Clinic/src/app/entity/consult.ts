import {User} from './user';
import {Client} from './client';

export class Consult{
    id:number;
    date:Date;
    user:User;
    details:string;
    client:Client;
}