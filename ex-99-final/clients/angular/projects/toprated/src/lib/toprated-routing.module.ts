import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TopratedComponent } from './toprated.component';
import { HomeComponent } from './home/home.component';
import { BookComponent } from './book/book.component';

const routes: Routes = [
  {
    path: 'top', component: TopratedComponent, children: [
      {path: '', component: HomeComponent},
      {path: 'home', component: HomeComponent},
      {path: 'book', component: BookComponent},
    ]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class TopratedRoutingModule {
}
