import { Component, OnInit } from '@angular/core';
import {NumberGeneratorService} from "../shared/service/number-generator.service";

@Component({
  selector: 'gen-book-number',
  templateUrl: './book-number.component.html',
  styles: []
})
export class BookNumberComponent implements OnInit {

  bookNumber: string;

  // constructor(private numberGeneratorService: NumberGeneratorService) { }
  constructor() { }

  ngOnInit() {
  }

  // generateBookNumber() {
  //   this.numberGeneratorService.generateNumberUsingGET().subscribe(bookNumber => this.bookNumber = bookNumber);
  // }

}
