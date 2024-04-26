import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Subject } from 'src/app/models/subject';
import { User } from 'src/app/models/user';
import { SubjectService } from 'src/app/services/subject.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-subject',
  templateUrl: './subject.component.html',
  styleUrls: ['./subject.component.scss']
})
export class SubjectComponent implements OnInit, OnDestroy {

  subjects: Subject[] | undefined;
  user: User | undefined;
  getUserService$: Subscription | undefined;
  getAllService$: Subscription | undefined;
  followService$: Subscription | undefined;

  constructor(private subjectService: SubjectService, private userService: UserService) { }

  ngOnInit(): void {
    this.getUser();
    this.getAll();
  }

  public getUser() {
    this.getUserService$ = this.userService
      .getMe()
      .subscribe((user: User) => this.user = user);
  }

  public getAll(): void {
    this.getAllService$ = this.subjectService.getAll().subscribe((subjects) => {
      this.subjects = subjects;
    })
  }

  public follow(id: number) {
    this.followService$ = this.subjectService.follow(id.toString()).subscribe(() => {
      this.getUser();
      this.getAll();
    });
  }

  public isAlreadyFollowed(subjectId: number): boolean {
    if (this.user) {
      return this.user?.subjectIds.includes(subjectId);
    }
    return false;
  }

  public ngOnDestroy(): void {
    if (this.getUserService$) {
      this.getUserService$.unsubscribe();
    }
    if (this.getAllService$) {
      this.getAllService$.unsubscribe();
    }
    if (this.followService$) {
      this.followService$.unsubscribe();
    }
  }

}
