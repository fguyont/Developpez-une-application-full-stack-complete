import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { RegisterRequest } from 'src/app/models/register-request';
import { Subject } from 'src/app/models/subject';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { SessionService } from 'src/app/services/session.service';
import { SubjectService } from 'src/app/services/subject.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})

export class UserComponent implements OnInit, OnDestroy {

  user: User | undefined;
  subjects: Subject[] | undefined;
  onError = false;
  form!: FormGroup;
  getMeService$: Subscription | undefined;
  getSubjectsService$: Subscription | undefined;
  unfollowService$: Subscription | undefined;
  updateUserService$: Subscription | undefined;

  constructor(private router: Router,
    private userService: UserService,
    private subjectService: SubjectService,
    private sessionService: SessionService,
    private fb: FormBuilder,) {
  }

  public ngOnInit(): void {
    this.getMeService$ = this.userService
      .getMe()
      .subscribe((user: User) => this.user = user);

    this.form = this.fb.group({
      email: [
        [
          Validators.required,
        ]
      ],
      name: [
        [
          Validators.required,
        ]
      ],
      password: [
        '',
        [
          Validators.required,
          Validators.pattern(new RegExp("((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%?=*&]).{8,50})")),
        ]
      ]
    });

    this.refreshFollowedSubjects();
  }

  public refreshFollowedSubjects(): void {
    this.getSubjectsService$ = this.subjectService
      .getFollowedSubjects()
      .subscribe((subjects: Subject[]) => this.subjects = subjects);
  }

  public unfollow(id: number) {
    this.unfollowService$ = this.subjectService.unfollow(id.toString()).subscribe(() => {
      this.refreshFollowedSubjects();
    });
  }

  public submit(): void {
    const registerRequest = this.form.value as RegisterRequest;
    if (this.user) {
      this.updateUserService$ = this.userService.updates(registerRequest).subscribe({
        next: () => this.router.navigate(['/login']),
        error: _ => this.onError = true,
      }
      );
    }
  }

  public logOut() {
    this.sessionService.logOut();
    this.router.navigateByUrl(`/`);
  }

  public ngOnDestroy(): void {
    if (this.getMeService$) {
      this.getMeService$.unsubscribe();
    }
    if (this.getSubjectsService$) {
      this.getSubjectsService$.unsubscribe();
    }
    if (this.updateUserService$) {
      this.updateUserService$.unsubscribe();
    }
    if (this.unfollowService$) {
      this.unfollowService$.unsubscribe();
    }
  }

}
