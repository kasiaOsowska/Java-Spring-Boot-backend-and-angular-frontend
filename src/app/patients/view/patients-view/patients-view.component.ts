import { Component, OnInit } from '@angular/core';
import { PatientsService } from "../../service/patients.service";
import { ActivatedRoute, Router } from "@angular/router";
import { PatientsDetails } from "../../model/patients-details";


@Component({
  selector: 'app-patients-view',
  templateUrl: './patients-view.component.html',
  styleUrls: ['./patients-view.component.css']
})
export class PatientsViewComponent {
  patient: PatientsDetails | undefined;

  /**
   *
   * @param service character service
   * @param route activated route
   * @param router router
   */
  constructor(private service: PatientsService, private route: ActivatedRoute, private router: Router) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.service.getCharacter(params['uuid'])
        .subscribe(patient => this.patient = patient)
    });
  }

}
