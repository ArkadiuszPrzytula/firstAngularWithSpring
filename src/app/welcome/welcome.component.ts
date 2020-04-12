import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { WeclomeDataService } from '../service/data/weclome-data.service';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})


export class WelcomeComponent implements OnInit {

  message = 'Somme Welcome Message'
  welcomeMessageFromService: string
  name=''
  constructor(private route: ActivatedRoute , private welcomeDataService: WeclomeDataService) { }

  ngOnInit(): void {
    console.log(this.message);
    this.name = this.route.snapshot.params['name'];
  }

  getWelcomeMessage() {

this.welcomeDataService.executeHelloWorldBeanService().subscribe(
  value=>this.hendleSuccesfullRespone(value),
  error=> this.hendleErrorResponse(error)
  );
  }


  getWelcomeMessageWithPatameter() {

    this.welcomeDataService.executeHelloWorldBeanServiceWithPathVariable(this.name).subscribe(
      value=>this.hendleSuccesfullRespone(value),
      error=> this.hendleErrorResponse(error)
      );
      }

  hendleSuccesfullRespone(response) {
    this.welcomeMessageFromService = response.message
  }

  hendleErrorResponse(response) {
    this.welcomeMessageFromService = response.error.message
  }
}
