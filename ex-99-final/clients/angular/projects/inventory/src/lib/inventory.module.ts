import { NgModule } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { InventoryComponent } from './inventory.component';
import { HomeComponent } from './home/home.component';
import { BookComponent } from './book/book.component';
import {InventoryRoutingModule} from './inventory-routing.module';

@NgModule({
  imports: [
    InventoryRoutingModule,
    NgbModule.forRoot()
  ],
  declarations: [
    InventoryComponent, 
    HomeComponent, 
    BookComponent
  ],
  exports: [
    InventoryComponent
  ]
})
export class InventoryModule { }
