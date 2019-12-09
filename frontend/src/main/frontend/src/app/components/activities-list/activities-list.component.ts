import {Component, OnInit} from '@angular/core';
import {StorageService} from "../../services/storage/storage.service";
import {HttpClient} from "@angular/common/http";
import {UserService} from "../../services/user/user.service";
import {Observable} from 'rxjs';
@Component({
    selector: 'app-activities-list',
    templateUrl: './activities-list.component.html',
    styleUrls: ['./activities-list.component.css']
})
export class ActivitiesListComponent implements OnInit {
    //private localhost: string = 'http://localhost:8080';
    private siteUrl: string = 'https://nc-group1-2019-project.herokuapp.com';

    activities:string[]=[];

    constructor(private storage: StorageService,
                private http: HttpClient,
                private userService:UserService) {
    }

    ngOnInit() {
       if(this.storage.getUser()!=null){
           this.getAllActivitiesByUserId(this.storage.getUser().id).subscribe(
               res=>{
                   this.activities.push(...res||[]);
                   console.log(this.activities)
               }
           )
       }
    }


    getAllActivitiesByUserId(userId:number):Observable<string[]>{
        //const url = `${this.siteUrl}/notification/getUser/userId?userId=${userId}`+ '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token;
        const url = `${this.siteUrl}/notification/getUser/${userId}`;
        console.log(url);
        return this.http.get<string[]>(url);
    }
}
