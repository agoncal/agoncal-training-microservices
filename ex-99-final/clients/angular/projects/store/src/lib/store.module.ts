import { NgModule } from '@angular/core';
import { StoreComponent } from './store.component';
import { HomeComponent } from './home/home.component';

@NgModule({
  imports: [
  ],
  declarations: [StoreComponent, HomeComponent],
  exports: [StoreComponent]
})
export class StoreModule { }
