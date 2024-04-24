import { Component, OnInit } from '@angular/core';
import { Subject } from 'src/app/models/subject';
import { User } from 'src/app/models/user';
import { SubjectService } from 'src/app/services/subject.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-subject',
  templateUrl: './subject.component.html',
  styleUrls: ['./subject.component.scss']
})
export class SubjectComponent implements OnInit {

  subjects: Subject[] | undefined;
  public user: User | undefined;

  constructor(private subjectService: SubjectService,private userService: UserService,) { }

  ngOnInit(): void {
    this.getUser();
    this.getAll();
  }

  public getUser() {
    this.userService
      // .getById(this.sessionService.sessionInformation!.id.toString())
      .getConnectedUser()
      .subscribe((user: User) => this.user = user );
  }

  public getAll(): void {
    this.subjectService.getAll().subscribe((subjects) => {
      this.subjects = subjects;
    })
  }

  public follow(id: number) {
    this.subjectService.follow(id.toString()).subscribe(() => {
      this.getUser();
      this.getAll();
    });
  }

  public isAlreadyFollowed(subjectId: number): boolean{
    if (this.user) {
    return this.user?.subjectIds.includes(subjectId);
    }
    return false;
  }

}
