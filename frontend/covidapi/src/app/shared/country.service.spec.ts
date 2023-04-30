import { TestBed } from '@angular/core/testing';

import { CountryService } from './country.service';
import { HttpClientModule } from '@angular/common/http';
import { Country } from './models/country';

describe('CountryService', () => {
  let service: CountryService;

  beforeEach(() => {
    TestBed.configureTestingModule({      
      imports: [
      HttpClientModule
    ]}).compileComponents();
    service = TestBed.inject(CountryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
