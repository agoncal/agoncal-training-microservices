import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'gen-book-number',
  templateUrl: './book-number.component.html',
  styles: []
})
export class BookNumberComponent implements OnInit {

  bookNumber: string;

  constructor(private numberApi: NumbersApi) { }

  ngOnInit() {
  }

  generateBookNumber() {
    this.numberApi.generateBookNumber().subscribe(bookNumber => this.bookNumber = bookNumber);
  }

}
