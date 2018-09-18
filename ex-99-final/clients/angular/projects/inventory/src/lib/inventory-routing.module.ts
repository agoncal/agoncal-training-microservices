import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { InventoryComponent } from './inventory.component';
import { HomeComponent } from './home/home.component';
import { BookComponent } from "./book/book.component";

const routes: Routes = [
  {
    path: 'inv', component: InventoryComponent, children: [
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
export class InventoryRoutingModule {
}
