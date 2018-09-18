import { NgModule } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { InventoryComponent } from './inventory.component';
import { HomeComponent } from './home/home.component';
import { BookComponent } from './book/book.component';
import { InventoryRoutingModule } from './inventory-routing.module';

@NgModule({
  imports: [
    InventoryRoutingModule,
    NgbModule.forRoot(),
    FontAwesomeModule
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
