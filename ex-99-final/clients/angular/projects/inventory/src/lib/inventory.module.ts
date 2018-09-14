import { NgModule } from '@angular/core';
import { InventoryComponent } from './inventory.component';
import { HomeComponent } from './home/home.component';

@NgModule({
  imports: [
  ],
  declarations: [InventoryComponent, HomeComponent],
  exports: [InventoryComponent]
})
export class InventoryModule { }
