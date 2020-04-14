import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

export class HelloWorldBean {
  constructor(public message: string) { }
}

@Injectable({
  providedIn: 'root'
})
export class WeclomeDataService {
  constructor(private http: HttpClient) { }




  executeHelloWorldBeanService() {
    return this.http.get<HelloWorldBean>('http://localhost:8080/api/welcome')

  }

  executeHelloWorldBeanServiceWithPathVariable(name: string) {
    return this.http.get<HelloWorldBean>(`http://localhost:8080/api/welcome/${name}`)

  }



}
