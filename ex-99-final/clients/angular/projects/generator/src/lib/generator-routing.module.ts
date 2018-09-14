import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GeneratorComponent } from './generator.component';
import { HomeComponent } from './home/home.component';
import { BookNumberComponent } from './book-number/book-number.component';

const routes: Routes = [
  {
    path: 'gen', component: GeneratorComponent, children: [
      {path: '', component: HomeComponent},
      {path: 'home', component: HomeComponent},
      {path: 'book-number', component: BookNumberComponent},
    ]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class GeneratorRoutingModule {
}
