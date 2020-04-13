import { Component, OnInit } from '@angular/core';
import { TodoDataService } from '../service/data/todo-data.service';
import { Todo } from '../list-to-dos/list-to-dos.component';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.css']
})
export class TodoComponent implements OnInit {
  id: number
  todo: Todo

  constructor(
    private todoDataService: TodoDataService,
    private routeActive: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.id = this.routeActive.snapshot.params['id'];
    this.todo = new Todo(this.id, '', false, new Date())
    if (this.todo.id != -1) {
      this.todoDataService.retriveTodo('arek', this.id).subscribe
        (value => this.todo = value);
    }
  }

  saveTodo() {
    if (this.todo.id === -1) {
      this.todoDataService.addTodo('arek', this.todo).subscribe(
        data => {
          console.log(data)
          this.router.navigate(['todos'])
        }
      )
    } else {
      this.todoDataService.updateTodo('arek', this.id, this.todo).subscribe(
        data => {
          console.log(data)
          this.router.navigate(['todos'])
        }
      )
    }
  }

}
