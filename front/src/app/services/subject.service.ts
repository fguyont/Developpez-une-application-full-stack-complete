import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Subject } from '../models/subject';

@Injectable({
  providedIn: 'root'
})
export class SubjectService {

  private pathService = 'http://localhost:8080/api/subject';

  constructor(private httpClient: HttpClient) { }

  public getAll(): Observable<Subject[]> {
    return this.httpClient.get<Subject[]>(this.pathService);
  }

  public getFollowedSubjects(): Observable<Subject[]> {
    return this.httpClient.get<Subject[]>(`${this.pathService}/user`);
  }

  public follow(id: string): Observable<void> {
    return this.httpClient.post<void>(`${this.pathService}/${id}/follow`, null);
  }

  public unfollow(id: string): Observable<void> {
    return this.httpClient.delete<void>(`${this.pathService}/${id}/unfollow`);
  }
}
