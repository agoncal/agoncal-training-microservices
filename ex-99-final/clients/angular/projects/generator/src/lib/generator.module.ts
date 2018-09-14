import { NgModule } from '@angular/core';
import { GeneratorComponent } from './generator.component';
import { HomeComponent } from './home/home.component';
import { GeneratorRoutingModule } from "./generator-routing.module";
import { NgbModule } from "@ng-bootstrap/ng-bootstrap";

@NgModule({
  imports: [
    GeneratorRoutingModule,
    NgbModule.forRoot()
  ],
  declarations: [
    GeneratorComponent,
    HomeComponent
  ],
  exports: [GeneratorComponent]
})
export class GeneratorModule { }
