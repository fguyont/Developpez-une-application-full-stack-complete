import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { SessionInformation } from '../../app/models/session-information';

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  public sessionInformation: SessionInformation | undefined;

  public logIn(user: SessionInformation): void {
    localStorage.setItem('token', user.token);
  }

  public logOut(): void {
    localStorage.removeItem('token');
  }

}
