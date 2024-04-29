import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { PostComponent } from './pages/post/post.component';
import { SubjectComponent } from './pages/subject/subject.component';
import { UserComponent } from './pages/user/user.component';
import { CreatePostComponent } from './pages/create-post/create-post.component';
import { PostDetailComponent } from './pages/post-detail/post-detail.component';
import { UnauthGuard } from './guards/unauth.guard';
import { AuthGuard } from './guards/auth.guard';

const routes: Routes = [{ path: '', component: HomeComponent },
{ path: 'login', component: LoginComponent, canActivate: [UnauthGuard] },
{ path: 'register', component: RegisterComponent, canActivate: [UnauthGuard] },
{ path: 'post', component: PostComponent, canActivate: [AuthGuard] },
{ path: 'subject', component: SubjectComponent, canActivate: [AuthGuard] },
{ path: 'user', component: UserComponent, canActivate: [AuthGuard] },
{ path: 'createpost', component: CreatePostComponent, canActivate: [AuthGuard] },
{ path: 'post/:id', component: PostDetailComponent, canActivate: [AuthGuard] }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule { }
