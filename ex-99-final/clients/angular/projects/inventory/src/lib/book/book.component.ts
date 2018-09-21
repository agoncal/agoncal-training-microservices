import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {HttpErrorResponse, HttpResponse} from "@angular/common/http";
import {IBook} from "./book.model";
import {BookService} from "./book.service";

@Component({
  selector: 'inv-book',
  templateUrl: './book.component.html',
  styles: []
})
export class BookComponent implements OnInit, OnDestroy {
  books: IBook[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    private bookService: BookService
  ) {}

  loadAll() {
    this.bookService.query().subscribe(
      (res: HttpResponse<IBook[]>) => {
        this.books = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInBooks();
  }

  ngOnDestroy() {
    // this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IBook) {
    return item.id;
  }

  registerChangeInBooks() {
    // this.eventSubscriber = this.eventManager.subscribe('bookListModification', response => this.loadAll());
  }

  private onError(errorMessage: string) {
    // this.jhiAlertService.error(errorMessage, null, null);
  }
}
