export const enum Warehouse {
    WAREHOUSE_EAST = 'WAREHOUSE_EAST',
    WAREHOUSE_WEST = 'WAREHOUSE_WEST',
    WAREHOUSE_EUROPE = 'WAREHOUSE_EUROPE'
}

export interface IBook {
    id?: number;
    isbn?: string;
    title?: string;
    nbOfCopies?: number;
    warehouse?: Warehouse;
    location?: string;
}

export class Book implements IBook {
    constructor(
        public id?: number,
        public isbn?: string,
        public title?: string,
        public nbOfCopies?: number,
        public warehouse?: Warehouse,
        public location?: string
    ) {}
}
