import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { CreatePostRequest } from 'src/app/models/create-post-request';
import { Subject } from 'src/app/models/subject';
import { PostService } from 'src/app/services/post.service';
import { SubjectService } from 'src/app/services/subject.service';

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.scss']
})

export class CreatePostComponent implements OnInit, OnDestroy {

  subjects: Subject[] | undefined;
  linkToBack = '/post';
  createService$: Subscription | undefined;

  constructor(private router: Router,
    private postService: PostService,
    private subjectService: SubjectService,
    private fb: FormBuilder) { }

  form = this.fb.group({
    subjectId:
      '',
    title:
      '',
    text:
      '',
  });

  ngOnInit(): void {
    this.subjectService
      .getAll()
      .subscribe((subjects: Subject[]) => this.subjects = subjects);
  }

  public submit() {
    let createPostRequest = this.form.value as CreatePostRequest;
    this.createService$ = this.postService.create(createPostRequest).subscribe(() => {
      this.router.navigateByUrl('post');
    })
  }

  public ngOnDestroy(): void {
    if (this.createService$) {
      this.createService$.unsubscribe();
    }
  }

}
