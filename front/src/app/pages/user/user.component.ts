import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RegisterRequest } from 'src/app/models/register-request';
import { Subject } from 'src/app/models/subject';
import { User } from 'src/app/models/user';
import { SessionService } from 'src/app/services/session.service';
import { SubjectService } from 'src/app/services/subject.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {

  public user: User | undefined;
  public subjects: Subject[] | undefined;

  constructor(private router: Router,
              private sessionService: SessionService,
              private userService: UserService,
              private subjectService: SubjectService,
              private fb: FormBuilder,) {
  }

  public onError = false;

  public form:any;

  // public form = this.fb.group({
  //   email: [
  //     this.user?.email,
  //     [

  //     ]
  //   ],
  //   name: [
  //     this.user?.name,
  //     [

  //     ]
  //   ],
  //   password: [
  //     this.user?.password,
  //     [
  //       Validators.pattern('((?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%?=*&]).{8,50})'),
  //     ]
  //   ]
  // });

  public ngOnInit(): void {
    this.userService
      // .getById(this.sessionService.sessionInformation!.id.toString())
      .getConnectedUser()
      .subscribe((user: User) => {this.user = user; console.log(this.user)});

    this.subjectService
      .getFollowedSubjects()
      .subscribe((subjects: Subject[]) => this.subjects = subjects);

      this.form = this.fb.group({
        email: [
          '',
          [

          ]
        ],
        name: [
          '',
          [

          ]
        ],
        password: [
          '',
          [
            Validators.pattern('((?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%?=*&]).{8,50})'),
          ]
        ]
      });
  }

  public unfollow(id: number) {
    this.subjectService.unfollow(id.toString()).subscribe(() => {
    });
  }

  public submit(): void {
    const registerRequest = this.form.value as RegisterRequest;
    console.log(registerRequest)
    if(this.user) {
    // this.userService.update(this.sessionService.sessionInformation!.id.toString(), registerRequest).subscribe({
      this.userService.update(this.user.id.toString(), registerRequest).subscribe({
        next: (_: void) => this.router.navigate(['/login']),
        error: _ => this.onError = true,
      }
    );
    }

  }
}
