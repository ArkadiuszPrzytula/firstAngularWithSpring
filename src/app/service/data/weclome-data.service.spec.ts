import { TestBed } from '@angular/core/testing';

import { WeclomeDataService } from './weclome-data.service';

describe('WeclomeDataService', () => {
  let service: WeclomeDataService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WeclomeDataService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
