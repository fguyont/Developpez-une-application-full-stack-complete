import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { PostService } from '../../services/post.service';
import { ActivatedRoute } from '@angular/router';
import { Post } from 'src/app/models/post';
import { CommentService } from 'src/app/services/comment.service';
import { CreateCommentRequest } from 'src/app/models/create-comment-request';
import { Comment } from 'src/app/models/comment';
import { faPaperPlane } from '@fortawesome/free-solid-svg-icons';


@Component({
  selector: 'app-detail',
  templateUrl: './post-detail.component.html',
  styleUrls: ['./post-detail.component.scss']
})
export class PostDetailComponent implements OnInit {

  public post: Post | undefined;

  public id!: string | null;

  public comments!: Comment[];

  public linkToBack: string = '/post';

  public faPaperPlaneIcon = faPaperPlane;

  public form = this.fb.group({
    text: [
      '',
      [
        Validators.required
      ]
    ]
  });

  constructor(private postService: PostService,
    private activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    private commentService: CommentService) {
  }

  public ngOnInit(): void {
    this.id = this.activatedRoute.snapshot.paramMap.get('id');
    if (this.id) {
      this.postService
        .getById(this.id)
        .subscribe((post: Post) => this.post = post);
      this.refreshComments();
    }
  }

  public submit() {
    if (this.id) {
      this.commentService.create(this.id, { text: this.form.value.text } as CreateCommentRequest).subscribe(() => {
        this.refreshComments()
      })
    }
  }


  public refreshComments() {
    if (this.id) {
      this.commentService.getAllByPostId(this.id).subscribe((comments) => {
        this.comments = comments
      })
    }

  }
  public back() {
    window.history.back();
  }

}
