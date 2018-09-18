import { NgModule } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { TopratedComponent } from './toprated.component';
import { HomeComponent } from './home/home.component';
import { BookComponent } from './book/book.component';
import { TopratedRoutingModule } from './toprated-routing.module';

@NgModule({
  imports: [
    TopratedRoutingModule,
    NgbModule.forRoot()
  ],
  declarations: [
    TopratedComponent,
    HomeComponent,
    BookComponent
  ],
  exports: [
    TopratedComponent
  ]
})
export class TopratedModule { }
