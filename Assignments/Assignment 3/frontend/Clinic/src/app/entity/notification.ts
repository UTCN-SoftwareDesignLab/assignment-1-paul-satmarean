import {Consult} from './consult';
import {User} from './user';

export class Notification{
    id:number;
    user:User;
    message:string;
    consult:Consult;
}