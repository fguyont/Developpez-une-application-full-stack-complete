import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Post } from 'src/app/models/post';
import { PostService } from 'src/app/services/post.service';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.scss']
})
export class PostComponent implements OnInit {

  posts: Post[] | undefined;

  constructor(private postService: PostService, private router: Router) { }

  ngOnInit(): void {
    this.getAll();
  }

  public getAll(): void {
    this.postService.getAll().subscribe((posts) => {
      this.posts = posts;
    })
  }

}
