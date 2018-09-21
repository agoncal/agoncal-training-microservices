import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { BookComponent } from "./book.component";
import { CommonModule } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { bookPopupRoute, bookRoute } from './book.route';
import { BookDeleteDialogComponent, BookDeletePopupComponent } from "./book-delete-dialog.component";
import { BookDetailComponent } from './book-detail.component';
import { BookUpdateComponent } from './book-update.component';
import { FormsModule } from '@angular/forms';

const ENTITY_STATES = [...bookRoute, ...bookPopupRoute];

@NgModule({
    imports: [CommonModule, FormsModule, BrowserModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [BookComponent, BookDetailComponent, BookUpdateComponent, BookDeleteDialogComponent, BookDeletePopupComponent],
    entryComponents: [BookComponent, BookUpdateComponent, BookDeleteDialogComponent, BookDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class BookModule {}
