import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { BookService } from './book.service';
import { IBook } from './book.model';

@Component({
    selector: 'jhi-book-update',
    templateUrl: './book-update.component.html'
})
export class BookUpdateComponent implements OnInit {
    private _book: IBook;
    isSaving: boolean;

    constructor(private bookService: BookService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ book }) => {
            this.book = book;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.book.id !== undefined) {
            this.subscribeToSaveResponse(this.bookService.update(this.book));
        } else {
            this.subscribeToSaveResponse(this.bookService.create(this.book));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IBook>>) {
        result.subscribe((res: HttpResponse<IBook>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get book() {
        return this._book;
    }

    set book(book: IBook) {
        this._book = book;
    }
}
