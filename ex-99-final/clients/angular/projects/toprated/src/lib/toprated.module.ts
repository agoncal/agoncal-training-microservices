import { NgModule } from '@angular/core';
import { TopratedComponent } from './toprated.component';
import { HomeComponent } from './home/home.component';

@NgModule({
  imports: [
  ],
  declarations: [TopratedComponent, HomeComponent],
  exports: [TopratedComponent]
})
export class TopratedModule { }
