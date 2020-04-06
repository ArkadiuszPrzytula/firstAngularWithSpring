import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username
  password
  errorMessage='Invalid Credentials'
  invalidLogin= false;

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  handleLogin() {
    console.log(this.username + ' and ' + this.password)
    if(this.username==='arek' && this.password ==='demolka'){
      this.invalidLogin=false;
      this.router.navigate(['welcome', this.username]);
    } else {
      this.invalidLogin=true;
    }
  }

}
