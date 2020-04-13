import { Component, OnInit } from '@angular/core';
import { TodoDataService } from '../service/data/todo-data.service';
import { Router } from '@angular/router';
import { TodoComponent } from '../todo/todo.component';

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
  message: string

  // [new Todo(1, 'Learn to Dance', false, new Date()),
  // new Todo(2, 'Become an Expert at Angular', false, new Date()),
  // new Todo(3, "Visit India", false, new Date())
  // ]

  constructor(
    private todoDataService: TodoDataService,
    private router: Router
    ) { }

  ngOnInit(): void {
    this.refreshTodos();
  }

  refreshTodos() {
    this.todoDataService.retriveAllToDos('arek').subscribe(
      response => {
        console.log(response);
        this.todos = response;
      });
  }
  deleteTodo(id) {
    console.log(`delete todo ${id}`)
    this.todoDataService.deleteTodo('arek', id).subscribe(
      response => {
        console.log(response);
        this.message = 'DELETE SUCCESFUL'
        this.refreshTodos()
      })
      
  }

  updateTodo(id) {
    console.log(`update todo id=${id}`);
    this.router.navigate(['todos', id])
  }

  addTodo(){
    this.router.navigate(['todos', -1])
  }
  
}