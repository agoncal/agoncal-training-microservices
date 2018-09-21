import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';

import { BookService } from './book.service';
import { IBook } from './book.model';

@Component({
    selector: 'jhi-book-delete-dialog',
    templateUrl: './book-delete-dialog.component.html'
})
export class BookDeleteDialogComponent {
    book: IBook;

    constructor(private bookService: BookService, public activeModal: NgbActiveModal) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.bookService.delete(id).subscribe(response => {
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-book-delete-popup',
    template: ''
})
export class BookDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ book }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(BookDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.book = book;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
