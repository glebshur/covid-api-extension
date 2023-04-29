/**
 * Represents country statistic entity.
 */
export class Statistic{
    country : string;
    minConfirmed : number;
    maxConfirmed : number;

    constructor(country:string, minConfirmed:number, maxConfirmed:number){
        this.country = country
        this.maxConfirmed = maxConfirmed
        this.minConfirmed = minConfirmed
    }
}