import { Component, OnInit } from '@angular/core';
import { CountryService } from '../shared/country.service';
import { Observable } from 'rxjs';
import { Country } from '../shared/models/country';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Statistic } from '../shared/models/statistic';
import { StatisticService } from '../shared/statistic.service';
import { fromToDateValidator } from '../shared/fromToDateValidator';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {


  form!: FormGroup
  submitted = false
  countries$!: Observable<Country[]>
  statistics!: Statistic[]

  constructor(
    private countryService : CountryService,
    private statisticService : StatisticService
  ) { }

  ngOnInit(): void {
    this.form = new FormGroup({
      selectedCountries: new FormControl(null, Validators.required),
      fromDate: new FormControl(null, Validators.required),
      toDate: new FormControl(null, Validators.required)
    }, {validators : fromToDateValidator})
    this.countries$ = this.countryService.getAll()
  }

  submit() {
    if(this.form.invalid){
      return;
    }

    this.submitted = true

    let from : Date = new Date(this.form.value.fromDate + ":00.000Z")
    let to : Date = new Date(this.form.value.toDate + ":01.000Z")

    this.statisticService.getAll(this.form.value.selectedCountries, from, to)
    .subscribe(statistics => {
      this.statistics = statistics
      this.submitted = false
    }, error => {
      this.submitted = false
      this.statistics = new Array<Statistic>()
      this.form.setErrors({badRequest:true})
    })
   }

}
