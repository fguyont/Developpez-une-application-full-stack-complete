import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { CreatePostRequest } from 'src/app/models/create-post-request';
import { Subject } from 'src/app/models/subject';
import { PostService } from 'src/app/services/post.service';
import { SessionService } from 'src/app/services/session.service';
import { SubjectService } from 'src/app/services/subject.service';

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.scss']
})
export class CreatePostComponent implements OnInit {

  public subjects: Subject[] | undefined;

  constructor(private router: Router,
    private sessionService: SessionService,
    private postService: PostService,
    private subjectService: SubjectService,
    private fb: FormBuilder) { }

    public form = this.fb.group({
      subjectId:
        '',
      title:
        '',
      text:
        '',
    });

  ngOnInit(): void {
    this.subjectService
      .getFollowedSubjects()
      .subscribe((subjects: Subject[]) => this.subjects = subjects);
  }

  public submit() {
    this.postService.create(this.form.value as CreatePostRequest).subscribe(() => {
      this.router.navigateByUrl('post');
    })
  }

}
