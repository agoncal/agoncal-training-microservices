import { TestBed } from '@angular/core/testing';

import { TopratedService } from './toprated.service';

describe('TopratedService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TopratedService = TestBed.get(TopratedService);
    expect(service).toBeTruthy();
  });
});
