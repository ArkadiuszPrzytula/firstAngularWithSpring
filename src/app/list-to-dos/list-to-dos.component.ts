import { Component, OnInit } from '@angular/core';
import { TodoDataService } from '../service/data/todo-data.service';

export class Todo {
  constructor(
    public id: number,
    public description: string,
    public done: boolean,
    public targetDate: Date
  ) {

  }

}

@Component({
  selector: 'app-list-to-dos',
  templateUrl: './list-to-dos.component.html',
  styleUrls: ['./list-to-dos.component.css']
})
export class ListToDosComponent implements OnInit {

  todos: Todo[]

  // [new Todo(1, 'Learn to Dance', false, new Date()),
  // new Todo(2, 'Become an Expert at Angular', false, new Date()),
  // new Todo(3, "Visit India", false, new Date())
  // ]

  constructor(private todoDataService: TodoDataService) { }

  ngOnInit(): void {
    this.todoDataService.retriveAllToDos('arek').subscribe(
      response => {
      console.log(response)
      this.todos = response;
    });
  }

}