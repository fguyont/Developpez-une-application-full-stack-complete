import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/user';
import { RegisterRequest } from '../models/register-request';
import { SessionInformation } from '../models/session-information';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private pathService = 'http://localhost:8080/api/user';

  constructor(private httpClient: HttpClient) { }

  public getById(id: string): Observable<User> {
    return this.httpClient.get<User>(`${this.pathService}/${id}`);
  }

  public update(id:string, registerRequest: RegisterRequest): Observable<void> {
    return this.httpClient.put<void>(`${this.pathService}/${id}`, registerRequest);
  }

  public getConnectedUser():Observable<User> {
    return this.httpClient.get<User>(`${this.pathService}/me`)
  }
}
