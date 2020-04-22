import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { API_URL } from 'src/app/constants';

export class HelloWorldBean {
  constructor(public message: string) { }
}

@Injectable({
  providedIn: 'root'
})
export class WeclomeDataService {
  constructor(private http: HttpClient) { }




  executeHelloWorldBeanService() {
    return this.http.get<HelloWorldBean>(`${API_URL}/api/welcome`)

  }

  executeHelloWorldBeanServiceWithPathVariable(name: string) {
    return this.http.get<HelloWorldBean>(`${API_URL}/api/welcome/${name}`)

  }



}
