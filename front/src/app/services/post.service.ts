import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Post } from '../models/post';
import { Observable } from 'rxjs';
import { CreatePostRequest } from '../models/create-post-request';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  private pathService = 'http://localhost:8080/api/post';

  constructor(private httpClient: HttpClient) { }

  public getAll(): Observable<Post[]> {
    return this.httpClient.get<Post[]>(this.pathService);
  }

  public getById(id: string): Observable<Post> {
    return this.httpClient.get<Post>(`${this.pathService}/${id}`);
  }

  public create(postRequest: CreatePostRequest): Observable<Post> {
    return this.httpClient.post<Post>(`${this.pathService}`, { ...postRequest });
  }
}
