import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Doctors } from "../model/doctors";

/**
 * Profession management service. Calls REST endpoints.
 */
@Injectable({
  providedIn: 'root'
})
export class DoctorsService {

  /**
   * @param http HTTP client
   */
  constructor(private http: HttpClient) {

  }

  /**
   * Fetches all doctors.
   *
   * @return list of doctors
   */
  getDoctors(): Observable<Doctors> {
    return this.http.get<Doctors>('http://localhost:8083/doctors');
  }

}
