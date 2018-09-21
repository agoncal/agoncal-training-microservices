import { NgModule } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { InventoryComponent } from './inventory.component';
import { HomeComponent } from './home/home.component';
import { InventoryRoutingModule } from './inventory-routing.module';
import { BookModule } from './book/book.module';

@NgModule({
  imports: [
    InventoryRoutingModule,
    BookModule,
    NgbModule.forRoot(),
    FontAwesomeModule
  ],
  declarations: [
    InventoryComponent, 
    HomeComponent, 
  ],
  exports: [
    InventoryComponent
  ]
})
export class InventoryModule { }
