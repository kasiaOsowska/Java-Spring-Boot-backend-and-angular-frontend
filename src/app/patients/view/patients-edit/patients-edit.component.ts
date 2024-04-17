import { Component, OnInit } from '@angular/core';
import { PatientsService } from '../../service/patients.service';
import { ActivatedRoute, Router } from '@angular/router';
import { PatientsForm } from '../../model/patients-form';
import { DoctorsService } from "../../../doctor/service/doctors.service";
import { Doctors } from "../../../doctor/model/doctors";


@Component({
  selector: 'app-patients-edit',
  templateUrl: './patients-edit.component.html',
  styleUrls: ['./patients-edit.component.css']
})
export class PatientsEditComponent {
  uuid: string | undefined;

  /**
   * Single character.
   */
  patient: PatientsForm | undefined;

  /**
   * Single character.
   */
  original: PatientsForm | undefined;

  /**
   * Available professions.
   */
  doctors: Doctors | undefined;

  /**
   * @param characterService character service
   * @param professionService profession service
   * @param route activated route
   * @param router router
   */
  constructor(
    private patientsService: PatientsService,
    private doctorsService: DoctorsService,
    private route: ActivatedRoute,
    private router: Router
  ) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.doctorsService.getDoctors()
        .subscribe(doctors => this.doctors = doctors);

      this.patientsService.getCharacter(params['uuid'])
        .subscribe(patient => {
          this.uuid = patient.id;
          this.patient = {
            name: patient.name,
            id: params['uuid']
          };
          this.original = {...this.patient};
        });
    });
  }

  /**
   * Updates character.
   */
  onSubmit(): void {
    console.log(this.patient)
    this.patientsService.putCharacter(this.patient!)
      .subscribe(() => this.router.navigate(['/patients/' + this.patient!.id]));
  }


}
