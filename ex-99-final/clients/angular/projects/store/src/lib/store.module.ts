import { NgModule } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { StoreRoutingModule } from './store-routing.module';
import { StoreComponent } from './store.component';
import { HomeComponent } from './home/home.component';
import { AuthorComponent } from './author/author.component';
import { BookComponent } from './book/book.component';
import { CategoryComponent } from './category/category.component';
import { PublisherComponent } from './publisher/publisher.component';

@NgModule({
  imports: [
    StoreRoutingModule,
    NgbModule.forRoot()
  ],
  declarations: [
    StoreComponent,
    HomeComponent,
    AuthorComponent,
    BookComponent,
    CategoryComponent,
    PublisherComponent],
  exports: [
    StoreComponent
  ]
})
export class StoreModule { }
