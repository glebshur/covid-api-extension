import { TestBed } from '@angular/core/testing';

import { StatisticService } from './statistic.service';
import { HttpClientModule } from '@angular/common/http';

describe('StatisticService', () => {
  let service: StatisticService;

  beforeEach(() => {
    TestBed.configureTestingModule({      
      imports: [
      HttpClientModule
    ]}).compileComponents();
    service = TestBed.inject(StatisticService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
