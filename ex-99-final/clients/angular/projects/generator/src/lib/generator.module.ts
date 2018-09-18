import { NgModule } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { GeneratorRoutingModule } from './generator-routing.module';
import { GeneratorComponent } from './generator.component';
import { HomeComponent } from './home/home.component';
import { BookNumberComponent } from './book-number/book-number.component';

@NgModule({
  imports: [
    GeneratorRoutingModule,
    NgbModule.forRoot()
  ],
  declarations: [
    GeneratorComponent,
    HomeComponent,
    BookNumberComponent
  ],
  exports: [
    GeneratorComponent
  ]
})
export class GeneratorModule { }
