import { Component, OnInit } from '@angular/core';
import { User } from '../mdls/user';
import { LogView} from '../mdls/logview';
import { HttpClient } from '@angular/common/http';
import { first } from 'rxjs/operators';
import { Router, ActivatedRoute } from '@angular/router';

import { AuthenticationService } from '../services/authentication.service'


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  model:LogView = {login:'',
                password:''};

  returnUrl: string;
  constructor(
        private http: HttpClient, public serv:AuthenticationService,
        private route: ActivatedRoute,
        private router: Router) { }

  ngOnInit() {

        this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }
  login():void{
        this.serv.login(this.model.login,this.model.password)
            .pipe(first())
            .subscribe(
                data => {
                    this.router.navigate([this.returnUrl]);
                },
                error => {
                  //  this.error = error;
                  //  this.loading = false;
                });

   }
}
