import { TestBed, inject } from '@angular/core/testing';

import { ConsultService } from './consult.service';

describe('ConsultService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ConsultService]
    });
  });

  it('should be created', inject([ConsultService], (service: ConsultService) => {
    expect(service).toBeTruthy();
  }));
});
