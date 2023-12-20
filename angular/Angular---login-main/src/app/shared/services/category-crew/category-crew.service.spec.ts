import { TestBed } from '@angular/core/testing';

import { CategoryCrewService } from './category-crew.service';

describe('CategoryCrewService', () => {
  let service: CategoryCrewService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CategoryCrewService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
