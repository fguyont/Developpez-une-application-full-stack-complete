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

  public form = this.fb.group({
    email: [
      '',
      [
        Validators.required,
        Validators.email
      ]
    ],
    name: [
      '',
      [
        Validators.required,
        Validators.min(3),
        Validators.max(20)
      ]
    ],
    password: [
      '',
      [
        Validators.pattern('((?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%?=*&]).{8,50})'),
      ]
    ]
  });

  public ngOnInit(): void {
    this.userService
      .getById(this.sessionService.sessionInformation!.id.toString())
      .subscribe((user: User) => this.user = user);
    this.subjectService
      .getFollowedSubjects()
      .subscribe((subjects: Subject[]) => this.subjects = subjects);
  }

  public back(): void {
    window.history.back();
  }

  public submit(): void {
    const registerRequest = this.form.value as RegisterRequest;
    this.userService.update(this.sessionService.sessionInformation!.id.toString(), registerRequest).subscribe({
        next: (_: void) => this.router.navigate(['/login']),
        error: _ => this.onError = true,
      }
    );
  }
}
