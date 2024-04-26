import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { PostService } from '../../services/post.service';
import { ActivatedRoute } from '@angular/router';
import { Post } from 'src/app/models/post';
import { CommentService } from 'src/app/services/comment.service';
import { CreateCommentRequest } from 'src/app/models/create-comment-request';
import { Comment } from 'src/app/models/comment';
import { faPaperPlane } from '@fortawesome/free-solid-svg-icons';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-detail',
  templateUrl: './post-detail.component.html',
  styleUrls: ['./post-detail.component.scss']
})

export class PostDetailComponent implements OnInit, OnDestroy {

  post: Post | undefined;
  id!: string | null;
  comments!: Comment[];
  linkToBack: string = '/post';
  faPaperPlaneIcon = faPaperPlane;
  getPostService$: Subscription | undefined;
  createCommentService$: Subscription | undefined;
  getAllCommentsService$: Subscription | undefined;

  constructor(private postService: PostService,
    private activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    private commentService: CommentService) {
  }

  public form = this.fb.group({
    text: [
      '',
      [
        Validators.required
      ]
    ]
  });

  public ngOnInit(): void {
    this.id = this.activatedRoute.snapshot.paramMap.get('id');
    if (this.id) {
      this.getPostService$ = this.postService
        .getById(this.id)
        .subscribe((post: Post) => this.post = post);
      this.refreshComments();
    }
  }

  public submit() {
    if (this.id) {
      let createCommentRequest = this.form.value as CreateCommentRequest;
      this.createCommentService$ = this.commentService.create(this.id, createCommentRequest).subscribe(() => {
        this.refreshComments();
      })
    }
  }

  public refreshComments() {
    if (this.id) {
      this.getAllCommentsService$ = this.commentService.getAllByPostId(this.id).subscribe((comments) => {
        this.comments = comments;
        this.comments?.sort((a, b) => new Date(b.date).getTime() - new Date(a.date).getTime());
      })
    }
  }

  public ngOnDestroy(): void {
    if (this.getPostService$) {
      this.getPostService$.unsubscribe();
    }
    if (this.createCommentService$) {
      this.createCommentService$.unsubscribe();
    }
    if (this.getAllCommentsService$) {
      this.getAllCommentsService$.unsubscribe();
    }
  }

}
