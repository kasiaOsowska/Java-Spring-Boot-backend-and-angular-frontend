import { Component, OnInit } from '@angular/core';
import { PatientsService } from "../../../service/patients.service";
import { Patients } from "../../../model/patients";
import { Patient } from "../../../model/patient";


@Component({
  selector: 'app-patients-list',
  templateUrl: './patients-list.component.html',
  styleUrls: ['./patients-list.component.css']
})
export class PatientsListComponent {
  constructor(private service: PatientsService) {
  }

  patients: Patients | undefined;

  ngOnInit(): void {
    this.service.getCharacters().subscribe(patients => this.patients = patients);
  }

  /**
   * Deletes selected character.
   *
   * @param user character to be removed
   */
  onDelete(patient: Patient): void {
    this.service.deleteCharacter(patient.id).subscribe(() => this.ngOnInit());
  }

}
