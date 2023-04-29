import { AbstractControl, ValidationErrors, ValidatorFn } from "@angular/forms";

/**
 * Validator that checks 'from' and 'to' dates.
 * 
 * @param control form froup
 * @returns error or null
 */
export const fromToDateValidator : ValidatorFn = 
(control: AbstractControl) : ValidationErrors | null => {
    const from = new Date(control.get('fromDate')?.value + ":00.000Z");
    const to = new Date(control.get('toDate')?.value + ":01.000Z");

    return from && to && from >= to ? {periodInvalid:true} : null;
}