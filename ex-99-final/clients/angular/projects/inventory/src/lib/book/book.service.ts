import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from '../inventory.constants';
import { createRequestOption } from '../shared/util/request-util';
import { IBook } from './book.model';

type EntityResponseType = HttpResponse<IBook>;
type EntityArrayResponseType = HttpResponse<IBook[]>;

@Injectable({ providedIn: 'root' })
export class BookService {
    private resourceUrl = SERVER_API_URL + 'api/books';

    constructor(private http: HttpClient) {}

    create(book: IBook): Observable<EntityResponseType> {
        return this.http.post<IBook>(this.resourceUrl, book, { observe: 'response' });
    }

    update(book: IBook): Observable<EntityResponseType> {
        return this.http.put<IBook>(this.resourceUrl, book, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IBook>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IBook[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
