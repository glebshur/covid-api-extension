import { Component, OnInit } from '@angular/core';
import { CountryService } from '../shared/country.service';
import { Observable } from 'rxjs';
import { Country } from '../shared/models/country';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {


  form!: FormGroup
  countries$!: Observable<Country[]>

  constructor(
    private countryService : CountryService
  ) { }

  ngOnInit(): void {
    this.form = new FormGroup({
      selectedCountries: new FormControl(null, Validators.required),
      fromDate: new FormControl(null, Validators.required),
      toDate: new FormControl(null, Validators.required)
    })
    this.countries$ = this.countryService.getAll()
  }

  submit() {
    console.log(this.form.value.selectedCountries)
    console.log(this.form.value.fromDate)
    console.log(this.form.value.toDate)
   }

}
