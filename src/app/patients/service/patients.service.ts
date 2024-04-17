import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Patients } from "../model/patients";
import { PatientsDetails } from "../model/patients-details";
import { PatientsForm } from "../model/patients-form";
import { PatientsAdd } from "../model/patients-add";

@Injectable()
export class PatientsService {

  /**
   * @param http HTTP client
   */
  constructor(private http: HttpClient) {

  }

  /**
   * Fetches all characters.
   *
   * @return list of characters
   */
  getCharacters(): Observable<Patients> {
    return this.http.get<Patients>('http://localhost:8083/patients');
  }

  /**
   * Fetches single characters.
   *
   * @param uuid character's id
   * @return single characters
   */
  getCharacter(uuid: string): Observable<PatientsDetails> {
    return this.http.get<PatientsDetails>('http://localhost:8083/patients/' + uuid);
  }

  /**
   * Removes single character.
   *
   * @param uuid character's id
   */
  deleteCharacter(uuid: string): Observable<any> {
    //console.log("Deleting " + uuid);
    return this.http.delete('http://localhost:8083/patients/' + uuid);
  }

  /**
   * Updates single character.
   *
   * @param uuid character's id
   * @param request request body
   */
  putCharacter(request: PatientsForm): Observable<any> {
    console.log("putCharacter request body: " + request.name + request.id);
    return this.http.put('http://localhost:8083/patients/' + request.id, request);
  }
    /**
   * Updates single character.
   *
   * @param uuid character's id
   * @param request request body
   */
  postCharacter(request: PatientsAdd): Observable<any> {
    console.log("PostCharacter: " + request.doctorId + ": " + request.name);
    return this.http.post('http://localhost:8083/patients', request);
  }

}
