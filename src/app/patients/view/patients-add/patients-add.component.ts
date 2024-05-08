import { Component, OnInit } from '@angular/core';
import { PatientsService } from '../../service/patients.service';
import { ActivatedRoute, Router } from '@angular/router';
import { PatientsAdd } from '../../model/patients-add';
import { DoctorsService } from "../../../doctor/service/doctors.service";
import { Doctors } from "../../../doctor/model/doctors";

@Component({
  selector: 'app-patients-add',
  templateUrl: './patients-add.component.html',
  styleUrls: ['./patients-add.component.css']
})
export class PatientsAddComponent {
  /**
   * Newly added patient.
   */
  patient: PatientsAdd = {
    name: '',
    doctorId: '' // Assuming this is a field in your PatientsForm interface
  };

  /**
   * Available doctors.
   */
  doctors: Doctors | undefined;

  constructor(
    private patientsService: PatientsService,
    private doctorsService: DoctorsService,
    private router: Router
  ) {}

  ngOnInit() {
    this.doctorsService.getDoctors()
      .subscribe(doctors => this.doctors = doctors);
  }

  /**
   * Adds a new patient.
   */
  onSubmit(): void {
    console.log(this.patient.doctorId + ": " + this.patient.name)
    console.log("xd");
    this.patientsService.postCharacter(this.patient!)
      .subscribe(() => this.router.navigate(['/patients']));
  }
}