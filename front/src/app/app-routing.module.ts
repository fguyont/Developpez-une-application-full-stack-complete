import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { PostComponent } from './pages/post/post.component';
import { SubjectComponent } from './pages/subject/subject.component';
import { UserComponent } from './pages/user/user.component';
import { CreatePostComponent } from './pages/create-post/create-post.component';

// consider a guard combined with canLoad / canActivate route option
// to manage unauthenticated user to access private routes
const routes: Routes = [{ path: '', component: HomeComponent },
{ path: 'login', component: LoginComponent },
{ path: 'register', component: RegisterComponent },
{ path: 'post', component: PostComponent },
{ path: 'subject', component: SubjectComponent },
{ path: 'user', component: UserComponent},
{ path: 'createpost', component: CreatePostComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule { }
